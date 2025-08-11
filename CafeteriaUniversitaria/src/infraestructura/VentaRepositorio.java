/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura;
import dominio.DetalleVenta;
import dominio.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Natalie Ulate Rojas
 */
public class VentaRepositorio {

    /**
     * Guarda una venta completa (cabecera y detalles) usando una transacción.
     */
    public boolean guardarVenta(Venta venta) {
    
        String sqlVenta = "INSERT INTO VENTAS (usuario_id, subtotal, impuesto_iva, impuesto_ivi, descuento, total, fecha_hora) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO DETALLES_VENTA (venta_id, producto_id, cantidad, precio_unitario_venta, total_linea) VALUES (?, ?, ?, ?, ?)";
        
        Connection conn = null;

        try {       
            conn = ConexionBD.getConnection(false);

            int ventaId;
            try (PreparedStatement pstmtVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                pstmtVenta.setInt(1, venta.getUsuarioId());
                pstmtVenta.setDouble(2, venta.getSubtotal());
                pstmtVenta.setDouble(3, venta.getImpuestoIVA());
                pstmtVenta.setDouble(4, venta.getImpuestoIVI());
                pstmtVenta.setDouble(5, venta.getDescuento());
                pstmtVenta.setDouble(6, venta.getTotal());
                pstmtVenta.setTimestamp(7, Timestamp.valueOf(venta.getFechaHora()));
                
                pstmtVenta.executeUpdate();

                // Obtiene el ID de la venta recién creada para usarlo en los detalles
                try (ResultSet generatedKeys = pstmtVenta.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ventaId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Falló la creación de la venta, no se pudo obtener el ID.");
                    }
                }
            }

        
            try (PreparedStatement pstmtDetalle = conn.prepareStatement(sqlDetalle)) {
                for (DetalleVenta detalle : venta.getDetalles()) {
                    pstmtDetalle.setInt(1, ventaId); 
                    pstmtDetalle.setInt(2, detalle.getProductoId());
                    pstmtDetalle.setInt(3, detalle.getCantidad());
                    pstmtDetalle.setDouble(4, detalle.getPrecioUnitarioVenta());
                    pstmtDetalle.setDouble(5, detalle.getTotalLinea());
                    pstmtDetalle.addBatch(); 
                }
                pstmtDetalle.executeBatch(); 
            }

  
            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en la transacción de venta, aplicando rollback: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error crítico al intentar hacer rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Obtiene una lista con todas las ventas realizadas en el día actual.
     * Cumple con el requisito de "Consultar ventas del día".
     * @return Una lista de objetos Venta (solo con datos de cabecera).
     */
    public List<Venta> listarVentasDelDia() {
        String sql = "SELECT * FROM VENTAS WHERE DATE(fecha_hora) = ? ORDER BY fecha_hora DESC";
        List<Venta> ventas = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Se establece la fecha actual para la consulta
            pstmt.setObject(1, LocalDate.now());
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Venta venta = new Venta();
                    venta.setId(rs.getInt("id"));
                    venta.setUsuarioId(rs.getInt("usuario_id"));
                    venta.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                    venta.setSubtotal(rs.getDouble("subtotal"));
                    venta.setImpuestoIVA(rs.getDouble("impuesto_iva"));
                    venta.setImpuestoIVI(rs.getDouble("impuesto_ivi"));
                    venta.setDescuento(rs.getDouble("descuento"));
                    venta.setTotal(rs.getDouble("total"));
                    ventas.add(venta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las ventas del día: " + e.getMessage());
        }
        return ventas;
    }
}
