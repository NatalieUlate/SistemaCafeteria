/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/**
 *
 * @author Natalie Ulate Rojas
 */
public final class Formateador {

    private Formateador() {}

    /**
     * Formatea un monto double a una cadena de texto con el símbolo de colones.
     */
    public static String formatearMoneda(double monto) {
        // se usa el Locale de Costa Rica para obtener el formato de colones (₡)
        Locale localeCR = new Locale("es", "CR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeCR);
        return currencyFormatter.format(monto);
    }

    /**
     * Formatea un objeto LocalDateTime a una cadena de texto legible.
     */
    public static String formatearFecha(LocalDateTime fechaHora) {
        if (fechaHora == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        return fechaHora.format(formatter);
    }
}
