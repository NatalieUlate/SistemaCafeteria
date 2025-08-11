/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura;

import dominio.Categoria;
import dominio.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class ProductoRepositorio {

    /**
     * Obtiene una lista con todos los productos de la base de datos.
     */
    public List<Producto> listar(boolean soloActivos) {
        String sql = "SELECT p.*, c.nombre AS nombre_categoria "
                + "FROM PRODUCTOS p "
                + "JOIN CATEGORIAS c ON p.categoria_id = c.id ";

        if (soloActivos) {
            sql += "WHERE p.activo = TRUE ";
        }

        sql += "ORDER BY p.nombre_completo ASC";

        List<Producto> productos = new ArrayList<>();
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productos.add(mapearResultSetAProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los productos: " + e.getMessage());
        }
        return productos;
    }

    public List<Producto> buscarPorNombre(String textoBusqueda) {
        String sql = "SELECT p.*, c.nombre AS nombre_categoria "
                + "FROM PRODUCTOS p "
                + "JOIN CATEGORIAS c ON p.categoria_id = c.id "
                + "WHERE LOWER(p.nombre_completo) LIKE ? "
                + "ORDER BY p.nombre_completo ASC";

        List<Producto> productos = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Se añaden los '%' para buscar el texto en cualquier parte del nombre
            pstmt.setString(1, "%" + textoBusqueda.toLowerCase() + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Reutilizamos el método que ya teníamos para no repetir código
                    productos.add(mapearResultSetAProducto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar productos por nombre: " + e.getMessage());
        }
        return productos;
    }

    /**
     * Busca un producto específico por su ID.
     */
    public Producto buscarPorId(int id) {
        String sql = "SELECT p.*, c.nombre AS nombre_categoria "
                + "FROM PRODUCTOS p "
                + "JOIN CATEGORIAS c ON p.categoria_id = c.id "
                + "WHERE p.id = ?";
        Producto producto = null;

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto = mapearResultSetAProducto(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto por ID: " + e.getMessage());
        }
        return producto;
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     */
    public boolean crear(Producto producto) {
        String sql = "INSERT INTO PRODUCTOS(nombre_completo, precio_unitario, activo, categoria_id) VALUES(?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombreCompleto());
            pstmt.setDouble(2, producto.getPrecioUnitario());
            pstmt.setBoolean(3, producto.isActivo());
            pstmt.setInt(4, producto.getCategoria().getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza la información de un producto existente.
     */
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE PRODUCTOS SET nombre_completo = ?, precio_unitario = ?, activo = ?, categoria_id = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombreCompleto());
            pstmt.setDouble(2, producto.getPrecioUnitario());
            pstmt.setBoolean(3, producto.isActivo());
            pstmt.setInt(4, producto.getCategoria().getId());
            pstmt.setInt(5, producto.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Cambia el estado (activo/inactivo) de un producto.
     */
    public boolean cambiarEstado(int productoId, boolean nuevoEstado) {
        String sql = "UPDATE PRODUCTOS SET activo = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, nuevoEstado);
            pstmt.setInt(2, productoId);

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al cambiar el estado del producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene una lista de todas las categorías activas de la base de datos.
     */
    public List<Categoria> obtenerCategorias() {
        String sql = "SELECT * FROM CATEGORIAS WHERE activa = TRUE ORDER BY nombre";
        List<Categoria> categorias = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categorias.add(new Categoria(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getBoolean("activa")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las categorías: " + e.getMessage());
        }
        return categorias;
    }

    /**
     * Método de ayuda privado para convertir una fila del ResultSet en un
     * objeto Producto.
     */
    private Producto mapearResultSetAProducto(ResultSet rs) throws SQLException {

        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("categoria_id"));
        categoria.setNombre(rs.getString("nombre_categoria"));

        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombreCompleto(rs.getString("nombre_completo"));
        producto.setPrecioUnitario(rs.getDouble("precio_unitario"));
        producto.setActivo(rs.getBoolean("activo"));

        producto.setCategoria(categoria);

        return producto;
    }
}
