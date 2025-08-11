/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 *
 * @author Natalie Ulate Rojas
 */
public class LogRepositorio {

    /**
     * Método principal y genérico para registrar cualquier tipo de evento en la base de datos.
     */
    public void registrar(String nivel, String evento, String detalle, String stacktrace) {
        String sql = "INSERT INTO LOGS (fecha_hora, nivel, evento, detalle, stacktrace) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(2, nivel);
            pstmt.setString(3, evento);
            pstmt.setString(4, detalle);
            pstmt.setString(5, stacktrace);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("FALLO CRÍTICO DEL SISTEMA DE LOGS: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método de ayuda para registrar un suceso simple (no un error).
     */
    public void registrarSuceso(String evento, String detalle) {
        registrar("INFO", evento, detalle, null);
    }
    
    /**
     * Método de ayuda para registrar una excepción ocurrida en un bloque try-catch.
     */
    public void registrarError(String evento, Exception ex) {
        
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String stacktraceComoString = sw.toString();

   
        registrar("ERROR", evento, ex.getMessage(), stacktraceComoString);
    }
}
