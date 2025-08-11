/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Natalie Ulate Rojas
 */
public class ConexionBD {

  
    private static final String URL = "jdbc:postgresql://localhost:5432/cafeteria";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "admin"; 

 
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

 
    public static Connection getConnection(boolean autoCommit) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        connection.setAutoCommit(autoCommit);
        return connection;
    }
}
