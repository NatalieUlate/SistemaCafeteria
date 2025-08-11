/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura;
import dominio.Usuario;
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
public class UsuarioRepositorio {

    /**
     * Busca un usuario específico por su nombre de usuario.
     */
    public Usuario buscarPorUsername(String username) {
        String sql = "SELECT * FROM USUARIOS WHERE username = ?";
        Usuario usuario = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username); 
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPasswordHash(rs.getString("password_hash"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setActivo(rs.getBoolean("activo"));
                }
            }
        } catch (SQLException e) {
            
            System.err.println("Error al buscar usuario por username: " + e.getMessage());
        }
        return usuario;
    }

    /**
     * Obtiene una lista con todos los usuarios registrados en el sistema.
     */
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM USUARIOS ORDER BY username ASC";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPasswordHash(rs.getString("password_hash"));
                usuario.setRol(rs.getString("rol"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     */
    public boolean crear(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS(username, password_hash, rol, activo) VALUES(?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPasswordHash());
            pstmt.setString(3, usuario.getRol());
            pstmt.setBoolean(4, usuario.isActivo());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza la información de un usuario existente en la base de datos.
     */
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE USUARIOS SET username = ?, rol = ?, activo = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getRol());
            pstmt.setBoolean(3, usuario.isActivo());
            pstmt.setInt(4, usuario.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }
}
