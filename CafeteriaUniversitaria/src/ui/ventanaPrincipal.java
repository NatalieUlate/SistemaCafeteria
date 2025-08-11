/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import dominio.DetalleVenta;
import dominio.Producto;
import dominio.Venta;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import servicio.ServicioAutenticacion;
import servicio.ServicioProducto;
import servicio.ServicioVenta;
import utilidades.Formateador;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class ventanaPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName());
    private Producto productoSeleccionadoParaVenta;
    private final ServicioProducto servicioProducto;
    private final ServicioVenta servicioVenta;
    private Venta ventaActual;
    private DefaultTableModel ventaTableModel;
    private LoginVentana vp;

    public ventanaPrincipal() {
        initComponents();
        this.servicioProducto = new ServicioProducto();
        this.servicioVenta = new ServicioVenta();
        panelContenedor.add(panelBienvenida, "bienvenida");
        panelContenedor.add(panelVentas, "ventas");
        configurarTablaVenta();
        this.vp = new LoginVentana();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        panelBienvenida = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelVentas = new javax.swing.JPanel();
        panelAdicion = new javax.swing.JPanel();
        lblProductoBusqueda = new javax.swing.JLabel();
        txtProductoBusqueda = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        spinnerCantidad = new javax.swing.JSpinner();
        btnAgregarProducto = new javax.swing.JButton();
        panelDetalleVenta = new javax.swing.JPanel();
        scrollPaneVentaActual = new javax.swing.JScrollPane();
        tablaVentaActual = new javax.swing.JTable();
        panelResumen = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        lblImpuestoIVA = new javax.swing.JLabel();
        txtImpuestoIVA = new javax.swing.JTextField();
        lblImpuestoIVI = new javax.swing.JLabel();
        txtImpuestoIVI = new javax.swing.JTextField();
        lblDescuento = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        panelAccionesVenta = new javax.swing.JPanel();
        btnFinalizarVenta = new javax.swing.JButton();
        btnCancelarVenta = new javax.swing.JButton();
        menuBarPrincipal = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuItemSalir = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenu();
        menuItemGestionarProductos = new javax.swing.JMenuItem();
        menuVentas = new javax.swing.JMenu();
        menuItemNuevaVenta = new javax.swing.JMenuItem();
        menuItemHistorialVentas = new javax.swing.JMenuItem();
        menuHerramientas = new javax.swing.JMenu();
        menuItemCalculadora = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        menuItemAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cafetería Universitaria ");
        setPreferredSize(new java.awt.Dimension(1024, 768));

        panelContenedor.setLayout(new java.awt.CardLayout());

        jLabel1.setText("BIENVENIDO AL SISTEMA");

        javax.swing.GroupLayout panelBienvenidaLayout = new javax.swing.GroupLayout(panelBienvenida);
        panelBienvenida.setLayout(panelBienvenidaLayout);
        panelBienvenidaLayout.setHorizontalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 867, Short.MAX_VALUE)
            .addGroup(panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBienvenidaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelBienvenidaLayout.setVerticalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
            .addGroup(panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBienvenidaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelContenedor.add(panelBienvenida, "panelBienvenida");

        panelVentas.setLayout(new java.awt.BorderLayout());

        panelAdicion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblProductoBusqueda.setText("Buscar Producto:");
        panelAdicion.add(lblProductoBusqueda);

        txtProductoBusqueda.setColumns(25);
        txtProductoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoBusquedaActionPerformed(evt);
            }
        });
        panelAdicion.add(txtProductoBusqueda);

        lblCantidad.setText("Cantidad:");
        panelAdicion.add(lblCantidad);

        spinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        panelAdicion.add(spinnerCantidad);

        btnAgregarProducto.setText("Agregar a la Venta");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        panelAdicion.add(btnAgregarProducto);

        panelVentas.add(panelAdicion, java.awt.BorderLayout.PAGE_START);

        tablaVentaActual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cantidad", "Descripción", "P. Unitario", "Total"
            }
        ));
        tablaVentaActual.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPaneVentaActual.setViewportView(tablaVentaActual);

        javax.swing.GroupLayout panelDetalleVentaLayout = new javax.swing.GroupLayout(panelDetalleVenta);
        panelDetalleVenta.setLayout(panelDetalleVentaLayout);
        panelDetalleVentaLayout.setHorizontalGroup(
            panelDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
            .addGroup(panelDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDetalleVentaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPaneVentaActual, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelDetalleVentaLayout.setVerticalGroup(
            panelDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
            .addGroup(panelDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDetalleVentaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPaneVentaActual, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        panelVentas.add(panelDetalleVenta, java.awt.BorderLayout.CENTER);

        panelResumen.setLayout(new java.awt.GridLayout(6, 2, 5, 5));

        jLabel2.setText("Subtotal:");
        panelResumen.add(jLabel2);

        txtSubtotal.setEditable(false);
        txtSubtotal.setText("₡0.00");
        panelResumen.add(txtSubtotal);

        lblImpuestoIVA.setText("IVA (7%):");
        panelResumen.add(lblImpuestoIVA);

        txtImpuestoIVA.setEditable(false);
        txtImpuestoIVA.setText("₡0.00");
        panelResumen.add(txtImpuestoIVA);

        lblImpuestoIVI.setText("IVI (13%):");
        panelResumen.add(lblImpuestoIVI);

        txtImpuestoIVI.setEditable(false);
        txtImpuestoIVI.setText("₡0.00");
        panelResumen.add(txtImpuestoIVI);

        lblDescuento.setText("Descuento:");
        panelResumen.add(lblDescuento);

        txtDescuento.setEditable(false);
        txtDescuento.setText("₡0.00");
        panelResumen.add(txtDescuento);

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotal.setText("TOTAL A PAGAR:");
        panelResumen.add(lblTotal);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(51, 51, 255));
        txtTotal.setText("₡0.00");
        panelResumen.add(txtTotal);

        panelVentas.add(panelResumen, java.awt.BorderLayout.EAST);

        panelAccionesVenta.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnFinalizarVenta.setText("Finalizar e Imprimir");
        btnFinalizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarVentaActionPerformed(evt);
            }
        });
        panelAccionesVenta.add(btnFinalizarVenta);

        btnCancelarVenta.setText("Cancelar Venta");
        btnCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaActionPerformed(evt);
            }
        });
        panelAccionesVenta.add(btnCancelarVenta);

        panelVentas.add(panelAccionesVenta, java.awt.BorderLayout.PAGE_END);

        panelContenedor.add(panelVentas, "card3");

        menuArchivo.setText("Archivo");

        menuItemSalir.setActionCommand("Salir");
        menuItemSalir.setLabel("Salir");
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemSalir);

        menuBarPrincipal.add(menuArchivo);

        menuProductos.setText("Productos");

        menuItemGestionarProductos.setLabel("Gestionar Productos");
        menuItemGestionarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGestionarProductosActionPerformed(evt);
            }
        });
        menuProductos.add(menuItemGestionarProductos);

        menuBarPrincipal.add(menuProductos);

        menuVentas.setText("Ventas");

        menuItemNuevaVenta.setLabel("Registrar Nueva Venta");
        menuItemNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNuevaVentaActionPerformed(evt);
            }
        });
        menuVentas.add(menuItemNuevaVenta);

        menuItemHistorialVentas.setLabel("Historial de Ventas");
        menuItemHistorialVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemHistorialVentasActionPerformed(evt);
            }
        });
        menuVentas.add(menuItemHistorialVentas);

        menuBarPrincipal.add(menuVentas);

        menuHerramientas.setText("Herramientas");

        menuItemCalculadora.setLabel("Calculadora");
        menuItemCalculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCalculadoraActionPerformed(evt);
            }
        });
        menuHerramientas.add(menuItemCalculadora);

        menuBarPrincipal.add(menuHerramientas);

        menuAyuda.setText("Ayuda");

        menuItemAcercaDe.setLabel("Acerca de");
        menuItemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAcercaDeActionPerformed(evt);
            }
        });
        menuAyuda.add(menuItemAcercaDe);

        menuBarPrincipal.add(menuAyuda);

        setJMenuBar(menuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        this.dispose();
        vp.setVisible(true);
        vp.setResizable(false);
        vp.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuItemSalirActionPerformed
    private void configurarTablaVenta() {
        this.ventaTableModel = (DefaultTableModel) tablaVentaActual.getModel();
    }
    private void menuItemGestionarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGestionarProductosActionPerformed
        GestionarProductosDialog dialogo = new GestionarProductosDialog(this, true);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }//GEN-LAST:event_menuItemGestionarProductosActionPerformed

    private void menuItemNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNuevaVentaActionPerformed

        iniciarNuevaVenta();
        java.awt.CardLayout cl = (java.awt.CardLayout) (panelContenedor.getLayout());
        cl.show(panelContenedor, "ventas");

    }//GEN-LAST:event_menuItemNuevaVentaActionPerformed

    private void menuItemHistorialVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemHistorialVentasActionPerformed

        HistorialVentasDialog dialogoHistorial = new HistorialVentasDialog(this, true);
        dialogoHistorial.setLocationRelativeTo(this);
        dialogoHistorial.setVisible(true);
    }//GEN-LAST:event_menuItemHistorialVentasActionPerformed

    private void txtProductoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoBusquedaActionPerformed
        String textoBusqueda = txtProductoBusqueda.getText().trim();
        if (textoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un texto para buscar.", "Búsqueda vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Producto> resultados = servicioProducto.buscarPorNombre(textoBusqueda);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron productos que coincidan con '" + textoBusqueda + "'.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        BusquedaProductoDialog dialogoBusqueda = new BusquedaProductoDialog(this, true, resultados);
        dialogoBusqueda.setVisible(true);

        Producto productoSeleccionado = dialogoBusqueda.getProductoSeleccionado();
        if (productoSeleccionado != null) {
            this.productoSeleccionadoParaVenta = productoSeleccionado;
            txtProductoBusqueda.setText(productoSeleccionado.getNombreCompleto());
            spinnerCantidad.requestFocus();
        }
    }//GEN-LAST:event_txtProductoBusquedaActionPerformed

    private void btnFinalizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarVentaActionPerformed

        if (ventaActual.getDetalles().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede finalizar una venta sin productos.", "Venta Vacía", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea finalizar esta venta?", "Confirmar Venta", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {

            try {
                double descuento = Double.parseDouble(txtDescuento.getText());
                if (descuento < 0) {
                    JOptionPane.showMessageDialog(this, "El descuento no puede ser un número negativo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ventaActual.setDescuento(descuento);
                servicioVenta.recalcularTotales(ventaActual);
                actualizarVistaDeVenta();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El formato del descuento no es válido. Use solo números.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean exito = servicioVenta.registrarVenta(this.ventaActual);

            if (exito) {
                generarTiqueteTXT(this.ventaActual);
                JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                iniciarNuevaVenta();
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error y la venta no pudo ser registrada.", "Error de Guardado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnFinalizarVentaActionPerformed

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed

        if (!ventaActual.getDetalles().isEmpty()) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea cancelar la venta actual? Se perderán los productos agregados.",
                    "Confirmar Cancelación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                iniciarNuevaVenta();
            }
            iniciarNuevaVenta();
        }
    }//GEN-LAST:event_btnCancelarVentaActionPerformed

    private void menuItemCalculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCalculadoraActionPerformed

        CalculadoraDialog dialogoCalculadora = new CalculadoraDialog(this, false);
        dialogoCalculadora.setLocationRelativeTo(this);
        dialogoCalculadora.setVisible(true);
    }//GEN-LAST:event_menuItemCalculadoraActionPerformed

    private void menuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAcercaDeActionPerformed

        acercaDeDialog dialogoAcercaDe = new acercaDeDialog(this, true);
        dialogoAcercaDe.setLocationRelativeTo(this);
        dialogoAcercaDe.setVisible(true);
    }//GEN-LAST:event_menuItemAcercaDeActionPerformed
    private void limpiarParaSiguienteProducto() {
        this.productoSeleccionadoParaVenta = null;
        txtProductoBusqueda.setText("");
        spinnerCantidad.setValue(1);
        txtProductoBusqueda.requestFocusInWindow();
    }
    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed

        if (this.productoSeleccionadoParaVenta == null) {
            JOptionPane.showMessageDialog(this, "Primero debe buscar y seleccionar un producto.", "Producto no seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cantidad = (Integer) spinnerCantidad.getValue();
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero.", "Cantidad inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        servicioVenta.agregarProductoAVenta(this.ventaActual, this.productoSeleccionadoParaVenta, cantidad);

        actualizarVistaDeVenta();
        limpiarParaSiguienteProducto();
    }//GEN-LAST:event_btnAgregarProductoActionPerformed
    private void generarTiqueteTXT(Venta venta) {

        StringBuilder sb = new StringBuilder();
        sb.append("--- TIQUETE DE VENTA ---\n");
        sb.append("Cafetería UTN\n");
        sb.append("Fecha: ").append(Formateador.formatearFecha(venta.getFechaHora())).append("\n");
        sb.append("Venta ID: ").append(venta.getId()).append("\n");
        sb.append("Cajero: ").append(ServicioAutenticacion.getUsuarioConectado().getUsername()).append("\n");
        sb.append("----------------------------------------\n");
        sb.append(String.format("%-4s %-20s %8s\n", "Cant", "Producto", "Total"));
        sb.append("----------------------------------------\n");

        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = servicioProducto.buscarPorId(detalle.getProductoId());
            String nombreProducto = (producto != null) ? producto.getNombreCompleto() : "N/A";

            if (nombreProducto.length() > 20) {
                nombreProducto = nombreProducto.substring(0, 17) + "...";
            }

            String totalLinea = Formateador.formatearMoneda(detalle.getTotalLinea());
            sb.append(String.format("%-4d %-20s %8s\n", detalle.getCantidad(), nombreProducto, totalLinea));
        }

        sb.append("----------------------------------------\n");
        sb.append(String.format("%-25s %8s\n", "Subtotal:", Formateador.formatearMoneda(venta.getSubtotal())));
        sb.append(String.format("%-25s %8s\n", "IVA (7%):", Formateador.formatearMoneda(venta.getImpuestoIVA())));
        sb.append(String.format("%-25s %8s\n", "IVI (13%):", Formateador.formatearMoneda(venta.getImpuestoIVI())));
        sb.append(String.format("%-25s %8s\n", "Descuento:", Formateador.formatearMoneda(venta.getDescuento())));
        sb.append(String.format("%-25s %8s\n", "TOTAL:", Formateador.formatearMoneda(venta.getTotal())));
        sb.append("----------------------------------------\n");
        sb.append("¡Gracias por su compra!\n");

        // Lógica para guardar el archivo
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Guardar Tiquete");
        fileChooser.setSelectedFile(new java.io.File("tiquete_venta_" + venta.getId() + ".txt"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File archivoParaGuardar = fileChooser.getSelectedFile();
            try (java.io.PrintWriter out = new java.io.PrintWriter(archivoParaGuardar)) {
                out.println(sb.toString());
                JOptionPane.showMessageDialog(this, "Tiquete guardado exitosamente en:\n" + archivoParaGuardar.getAbsolutePath(), "Tiquete Guardado", JOptionPane.INFORMATION_MESSAGE);
            } catch (java.io.IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el tiquete: " + e.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void iniciarNuevaVenta() {

        this.ventaActual = new Venta();

        if (this.ventaTableModel != null) {
            this.ventaTableModel.setRowCount(0);
        }
        txtSubtotal.setText(utilidades.Formateador.formatearMoneda(0));
        txtImpuestoIVA.setText(utilidades.Formateador.formatearMoneda(0));
        txtImpuestoIVI.setText(utilidades.Formateador.formatearMoneda(0));
        txtDescuento.setText("0");
        txtTotal.setText(utilidades.Formateador.formatearMoneda(0));

        txtProductoBusqueda.setText("");
        txtProductoBusqueda.requestFocusInWindow();
    }

    private void actualizarVistaDeVenta() {
        ventaTableModel.setRowCount(0);

        for (dominio.DetalleVenta detalle : ventaActual.getDetalles()) {
            dominio.Producto producto = servicioProducto.buscarPorId(detalle.getProductoId());
            String nombreProducto = (producto != null) ? producto.getNombreCompleto() : "Producto no encontrado";

            Object[] row = {
                detalle.getCantidad(),
                nombreProducto,
                utilidades.Formateador.formatearMoneda(detalle.getPrecioUnitarioVenta()),
                utilidades.Formateador.formatearMoneda(detalle.getTotalLinea())
            };
            ventaTableModel.addRow(row);
        }

        // 3. Actualizar los campos de texto del resumen financiero
        txtSubtotal.setText(utilidades.Formateador.formatearMoneda(ventaActual.getSubtotal()));
        txtImpuestoIVA.setText(utilidades.Formateador.formatearMoneda(ventaActual.getImpuestoIVA()));
        txtImpuestoIVI.setText(utilidades.Formateador.formatearMoneda(ventaActual.getImpuestoIVI()));
        txtTotal.setText(utilidades.Formateador.formatearMoneda(ventaActual.getTotal()));
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnFinalizarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblImpuestoIVA;
    private javax.swing.JLabel lblImpuestoIVI;
    private javax.swing.JLabel lblProductoBusqueda;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuBar menuBarPrincipal;
    private javax.swing.JMenu menuHerramientas;
    private javax.swing.JMenuItem menuItemAcercaDe;
    private javax.swing.JMenuItem menuItemCalculadora;
    private javax.swing.JMenuItem menuItemGestionarProductos;
    private javax.swing.JMenuItem menuItemHistorialVentas;
    private javax.swing.JMenuItem menuItemNuevaVenta;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenu menuVentas;
    private javax.swing.JPanel panelAccionesVenta;
    private javax.swing.JPanel panelAdicion;
    private javax.swing.JPanel panelBienvenida;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JPanel panelDetalleVenta;
    private javax.swing.JPanel panelResumen;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JScrollPane scrollPaneVentaActual;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JTable tablaVentaActual;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtImpuestoIVA;
    private javax.swing.JTextField txtImpuestoIVI;
    private javax.swing.JTextField txtProductoBusqueda;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
