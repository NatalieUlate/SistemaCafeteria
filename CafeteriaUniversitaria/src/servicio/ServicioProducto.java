/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dominio.Categoria;
import dominio.Producto;
import infraestructura.LogRepositorio;
import infraestructura.ProductoRepositorio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class ServicioProducto {

    private final ProductoRepositorio productoRepositorio;
    private final LogRepositorio logRepositorio;

    public ServicioProducto() {
        this.productoRepositorio = new ProductoRepositorio();
        this.logRepositorio = new LogRepositorio();
    }

    /**
     * Obtiene la lista de todos los productos.
     */
    public List<Producto> listarProductos(boolean soloActivos) {
        return this.productoRepositorio.listar(soloActivos);
    }

    public Producto buscarPorId(int id) {
        return this.productoRepositorio.buscarPorId(id);
    }

    /**
     * Obtiene la lista de todas las categorías activas.
     */
    public List<Categoria> listarCategoriasActivas() {
        return this.productoRepositorio.obtenerCategorias();
    }

    public List<Producto> buscarPorNombre(String textoBusqueda) {

        if (textoBusqueda == null || textoBusqueda.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return this.productoRepositorio.buscarPorNombre(textoBusqueda);
    }

    /**
     * Valida y guarda un producto (ya sea creando uno nuevo o actualizando uno
     * existente).
     */
    public boolean guardarProducto(Producto producto) throws IllegalArgumentException {

        if (producto.getNombreCompleto() == null || producto.getNombreCompleto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (producto.getPrecioUnitario() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        if (producto.getCategoria() == null) {
            throw new IllegalArgumentException("Debe seleccionar una categoría para el producto.");
        }

        boolean resultado;
        // Si el ID es 0, es un producto nuevo. Si no, es una actualización.
        if (producto.getId() == 0) {
            resultado = this.productoRepositorio.crear(producto);
            if (resultado) {
                logRepositorio.registrarSuceso("Producto Creado", "Se creó el producto: " + producto.getNombreCompleto());
            }
        } else {
            resultado = this.productoRepositorio.actualizar(producto);
            if (resultado) {
                logRepositorio.registrarSuceso("Producto Actualizado", "Se actualizó el producto ID: " + producto.getId());
            }
        }

        return resultado;
    }

    /**
     * Cambia el estado de un producto a activo o inactivo.
     */
    public boolean cambiarEstadoProducto(int productoId, boolean nuevoEstado) {
        boolean resultado = this.productoRepositorio.cambiarEstado(productoId, nuevoEstado);
        if (resultado) {
            String estado = nuevoEstado ? "Activado" : "Desactivado";
            logRepositorio.registrarSuceso("Estado de Producto Cambiado", "Se ha " + estado + " el producto ID: " + productoId);
        }
        return resultado;
    }
}
