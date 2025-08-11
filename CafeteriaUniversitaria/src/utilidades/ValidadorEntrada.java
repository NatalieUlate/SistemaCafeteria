/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;
import javax.swing.JComboBox;
import javax.swing.JTextField;
/**
 *
 * @author Natalie Ulate Rojas
 */
public final class ValidadorEntrada {

    private ValidadorEntrada() {
    }

    /**
     * Verifica si un campo de texto está vacío o solo contiene espacios.
     */
    public static boolean esTextoVacio(JTextField campo) {
        return campo.getText().trim().isEmpty();
    }

    /**
     * Verifica si el texto de un campo puede ser convertido a un número decimal (double).
     */
    public static boolean esNumeroValido(JTextField campo) {
        try {
            Double.parseDouble(campo.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Verifica si un JComboBox tiene un ítem seleccionado.
     */
    public static boolean esSeleccionValida(JComboBox<?> combo) {
        // el índice de -1 significa que no hay nada seleccionado.
        return combo.getSelectedIndex() != -1;
    }
}
