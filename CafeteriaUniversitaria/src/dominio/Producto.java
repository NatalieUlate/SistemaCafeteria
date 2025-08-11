/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import java.time.LocalDateTime;
/**
 *
 * @author Natalie Ulate Rojas
 */
public class Producto {

    private int id;
    private String nombreCompleto;
    private double precioUnitario;
    private boolean activo;
    private Categoria categoria; // Relación de objeto, no solo un ID
    private LocalDateTime creado;
    
    public Producto() {
    }

    public Producto(int id, String nombreCompleto, double precioUnitario, boolean activo, Categoria categoria) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.precioUnitario = precioUnitario;
        this.activo = activo;
        this.categoria = categoria;
    }

    // --- Getters y Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }
}
