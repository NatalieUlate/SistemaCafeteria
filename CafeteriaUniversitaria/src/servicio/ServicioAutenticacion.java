/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;
import dominio.Usuario;
import infraestructura.UsuarioRepositorio;
import utilidades.CodificadorContrasena;
/**
 *
 * @author Natalie Ulate Rojas
 */
public class ServicioAutenticacion {

    private final UsuarioRepositorio usuarioRepositorio;
    private static Usuario usuarioConectado; // Mantiene al usuario que ha iniciado sesión

    public ServicioAutenticacion() {
        this.usuarioRepositorio = new UsuarioRepositorio();
    }

    /**
     * Valida las credenciales de un usuario contra la base de datos.
     */
    public Usuario login(String username, String contrasena) {
        // 1. Valida que la entrada no sea nula o vacía
        if (username == null || username.trim().isEmpty() || contrasena == null || contrasena.isEmpty()) {
            return null;
        }

        // 2. Busca al usuario en la base de datos
        Usuario usuarioBD = usuarioRepositorio.buscarPorUsername(username.trim());
        if (usuarioBD == null || !usuarioBD.isActivo()) {
            return null; // El usuario no existe o está inactivo
        }

        // 3. Hashea la contraseña ingresada para compararla con la de la BD
        String hashContrasenaIngresada = CodificadorContrasena.hash(contrasena);

        // 4. Comparar los hashes
        if (hashContrasenaIngresada.equals(usuarioBD.getPasswordHash())) {
            usuarioConectado = usuarioBD; // Guarda el usuario que inició sesión
            return usuarioBD; 
        }

        return null; // La contraseña es incorrecta
    }

    /**
     * Obtiene el usuario que ha iniciado sesión actualmente en el sistema.
     */
    public static Usuario getUsuarioConectado() {
        return usuarioConectado;
    }
    
    /**
     * Cierra la sesión del usuario actual.
     */
    public void logout() {
        usuarioConectado = null;
    }
}
