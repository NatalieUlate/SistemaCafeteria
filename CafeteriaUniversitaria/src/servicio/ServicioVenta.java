/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;
import dominio.DetalleVenta;
import dominio.Producto;
import dominio.Usuario;
import dominio.Venta;
import infraestructura.LogRepositorio;
import infraestructura.VentaRepositorio;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author Natalie Ulate Rojas
 */
public class ServicioVenta {

    private final VentaRepositorio ventaRepositorio;
    private final LogRepositorio logRepositorio;
    
    public ServicioVenta() {
        this.ventaRepositorio = new VentaRepositorio();
        this.logRepositorio = new LogRepositorio();
    }

    /**
     * Agrega un producto a la venta actual y recalcula los totales.utili
     */
    public void agregarProductoAVenta(Venta ventaActual, Producto producto, int cantidad) {
        DetalleVenta nuevoDetalle = new DetalleVenta(producto.getId(), cantidad, producto.getPrecioUnitario());
        ventaActual.agregarDetalle(nuevoDetalle);
        recalcularTotales(ventaActual);
    }

    /**
     * Recalcula todos los montos de la venta (subtotal, impuestos, total).
     */
    public void recalcularTotales(Venta venta) {
     double subtotal = 0;
        // Calcula el subtotal sumando el total de cada línea de detalle
        for (dominio.DetalleVenta detalle : venta.getDetalles()) {
            subtotal += detalle.getTotalLinea();
        }
        venta.setSubtotal(subtotal);

        // Lógica de Impuestos según el documento del proyecto 
        double iva = subtotal * 0.07;
        double ivi = subtotal * 0.13;
        venta.setImpuestoIVA(iva);
        venta.setImpuestoIVI(ivi);
        
        // Calcula el total final
        double total = subtotal + iva + ivi - venta.getDescuento();
        venta.setTotal(total);
    }
    
    /**
     * Finaliza y persiste la venta en la base de datos.
     */
    public boolean registrarVenta(Venta venta) {
        if (venta.getDetalles().isEmpty()) {
            throw new IllegalStateException("No se puede registrar una venta sin productos.");
        }
        
        // Asignar datos finales antes de guardar
        venta.setUsuarioId(ServicioAutenticacion.getUsuarioConectado().getId());
        venta.setFechaHora(LocalDateTime.now());
        
        boolean exito = ventaRepositorio.guardarVenta(venta);
        
        if (exito) {
            logRepositorio.registrarSuceso("Venta Registrada", "Venta con un total de " + venta.getTotal() + " registrada exitosamente.");
        } else {
            logRepositorio.registrarError("Fallo al Registrar Venta", new Exception("La transacción de la venta no pudo completarse."));
        }
        
        return exito;
    }
    
    /**
     * Consulta las ventas registradas en el día actual.
     */
    public List<Venta> consultarVentasDelDia() {
        return ventaRepositorio.listarVentasDelDia();
    }
    
    
}
