/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Natalie Ulate Rojas
 */
public final class CodificadorContrasena {

    // Constructor privado para evitar que la clase sea instanciada.
    private CodificadorContrasena() {
    }

    /**
     * Aplica el algoritmo de hash SHA-256 a una contraseña dada.
     */
    public static String hash(String contrasena) {
        if (contrasena == null) {
            return null;
        }
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Genera el hash de bytes a partir de la contraseña
            byte[] hashBytes = digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
            
            // Convierte el arreglo de bytes a una representación hexadecimal
            StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // Este error no debería ocurrir si SHA-256 está disponible en el JRE
            System.err.println("Error crítico: Algoritmo SHA-256 no encontrado.");
            e.printStackTrace();
            // En un caso real, se podría lanzar una excepción de runtime
            throw new RuntimeException("No se pudo encontrar el algoritmo de hashing", e);
        }
    }
}
