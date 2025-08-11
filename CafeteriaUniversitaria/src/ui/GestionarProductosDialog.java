/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import dominio.Categoria;
import dominio.Producto;
import java.util.ArrayList;
import servicio.ServicioProducto;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilidades.Formateador;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class GestionarProductosDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GestionarProductosDialog.class.getName());
    private final ServicioProducto servicioProducto;
    private List<Producto> listaActualDeProductos;

    public GestionarProductosDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.servicioProducto = new ServicioProducto();
        this.cargarCategoriasEnComboBox();
        this.refrescarTabla();
        this.setLocationRelativeTo(parent);
    }

    /**
     * Carga todas las categorías activas en el JComboBox de filtros.
     */
    private void cargarCategoriasEnComboBox() {
        comboCategorias.removeAllItems(); // Limpiar items anteriores
        comboCategorias.addItem("Todas"); // Opción para no filtrar

        List<Categoria> categorias = servicioProducto.listarCategoriasActivas();
        for (Categoria categoria : categorias) {
            comboCategorias.addItem(categoria.getNombre());
        }
    }

    /**
     * Pide al servicio la lista completa de productos y actualiza la tabla.
     */
    private void refrescarTabla() {
        boolean soloActivos = !checkMostrarInactivos.isSelected();

        List<Producto> productosDesdeBD = servicioProducto.listarProductos(soloActivos);
        String textoBusqueda = txtBuscarProducto.getText().trim().toLowerCase();
        String categoriaSeleccionada = "";
        if (comboCategorias.getSelectedItem() != null) {
            categoriaSeleccionada = comboCategorias.getSelectedItem().toString();
        }

        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productosDesdeBD) {
            boolean coincideNombre = textoBusqueda.isEmpty() || producto.getNombreCompleto().toLowerCase().contains(textoBusqueda);
            boolean coincideCategoria = categoriaSeleccionada.equals("Todas") || producto.getCategoria().getNombre().equals(categoriaSeleccionada);

            if (coincideNombre && coincideCategoria) {
                productosFiltrados.add(producto);
            }
        }

        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setRowCount(0);

        this.listaActualDeProductos = productosFiltrados; 
        for (Producto producto : productosFiltrados) {
            Object[] row = {
                producto.getId(),
                producto.getNombreCompleto(),
                producto.getCategoria().getNombre(),
                utilidades.Formateador.formatearMoneda(producto.getPrecioUnitario()),
                producto.isActivo() ? "Sí" : "No"
            };
            model.addRow(row);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBusqueda = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscarProducto = new javax.swing.JTextField();
        lblFiltrarCategoria = new javax.swing.JLabel();
        comboCategorias = new javax.swing.JComboBox<>();
        checkMostrarInactivos = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        panelTablaProductos = new javax.swing.JPanel();
        scrollPaneProductos = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        panelBotonesAccion = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Productos y Categorías");
        setModal(true);

        panelBusqueda.setLayout(new java.awt.GridLayout(1, 1, 5, 5));

        lblBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBuscar.setText("Buscar:");
        panelBusqueda.add(lblBuscar);

        txtBuscarProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarProducto.setToolTipText("Escriba el nombre o código del producto");
        panelBusqueda.add(txtBuscarProducto);

        lblFiltrarCategoria.setText("Categoría:");
        panelBusqueda.add(lblFiltrarCategoria);

        comboCategorias.setToolTipText("Filtre los productos por categoría");
        panelBusqueda.add(comboCategorias);

        checkMostrarInactivos.setText("Mostrar inactivos");
        checkMostrarInactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkMostrarInactivosActionPerformed(evt);
            }
        });
        panelBusqueda.add(checkMostrarInactivos);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        panelBusqueda.add(btnBuscar);

        getContentPane().add(panelBusqueda, java.awt.BorderLayout.PAGE_START);

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre del Producto", "Categoría", "Precio Unitario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPaneProductos.setViewportView(tablaProductos);
        if (tablaProductos.getColumnModel().getColumnCount() > 0) {
            tablaProductos.getColumnModel().getColumn(0).setResizable(false);
            tablaProductos.getColumnModel().getColumn(1).setResizable(false);
            tablaProductos.getColumnModel().getColumn(2).setResizable(false);
            tablaProductos.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout panelTablaProductosLayout = new javax.swing.GroupLayout(panelTablaProductos);
        panelTablaProductos.setLayout(panelTablaProductosLayout);
        panelTablaProductosLayout.setHorizontalGroup(
            panelTablaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
            .addGroup(panelTablaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTablaProductosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPaneProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelTablaProductosLayout.setVerticalGroup(
            panelTablaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
            .addGroup(panelTablaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTablaProductosLayout.createSequentialGroup()
                    .addComponent(scrollPaneProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        getContentPane().add(panelTablaProductos, java.awt.BorderLayout.CENTER);

        btnCrear.setText("Crear Producto");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        panelBotonesAccion.add(btnCrear);

        btnEditar.setText("Editar Producto");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        panelBotonesAccion.add(btnEditar);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelBotonesAccion.add(btnEliminar);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        panelBotonesAccion.add(btnCerrar);

        getContentPane().add(panelBotonesAccion, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // Abre el diálogo de creación, pasándole 'null' porque es un producto nuevo.
        CrearEditarProductoDialog dialogoCrear = new CrearEditarProductoDialog(this, true, null);
        dialogoCrear.setVisible(true);

        // Después de que el diálogo se cierra, actualizamos la tabla para ver el nuevo producto.
       refrescarTabla();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto de la tabla para editar.", "Ningún producto seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtiene el producto de nuestra lista local usando el índice de la fila
        Producto productoSeleccionado = this.listaActualDeProductos.get(filaSeleccionada);

        // Abre el diálogo de edición, pasándole el producto que se va a modificar.
        CrearEditarProductoDialog dialogoEditar = new CrearEditarProductoDialog(this, true, productoSeleccionado);
        dialogoEditar.setVisible(true);

        // Actualizamos la tabla para reflejar cualquier cambio.
        refrescarTabla();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto de la tabla para cambiar su estado.", "Ningún producto seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Producto productoSeleccionado = this.listaActualDeProductos.get(filaSeleccionada);

        // Mensaje de confirmación
        String accion = productoSeleccionado.isActivo() ? "desactivar" : "activar";
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea " + accion + " el producto '" + productoSeleccionado.getNombreCompleto() + "'?",
                "Confirmar Acción",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llama al servicio para cambiar el estado (se envía el estado contrario al actual)
            boolean exito = servicioProducto.cambiarEstadoProducto(productoSeleccionado.getId(), !productoSeleccionado.isActivo());
            if (exito) {
                JOptionPane.showMessageDialog(this, "El estado del producto ha sido cambiado exitosamente.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                refrescarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cambiar el estado del producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        boolean soloActivos = !checkMostrarInactivos.isSelected();
        String textoBusqueda = txtBuscarProducto.getText().trim().toLowerCase();
        String categoriaSeleccionada = (String) comboCategorias.getSelectedItem();

        List<Producto> todosLosProductos = servicioProducto.listarProductos(soloActivos);
        List<Producto> productosFiltrados = new ArrayList<>();

        for (Producto producto : todosLosProductos) {
            boolean coincideNombre = textoBusqueda.isEmpty() || producto.getNombreCompleto().toLowerCase().contains(textoBusqueda);
            boolean coincideCategoria = categoriaSeleccionada.equals("Todas") || producto.getCategoria().getNombre().equals(categoriaSeleccionada);

            if (coincideNombre && coincideCategoria) {
                productosFiltrados.add(producto);
            }
        }
        refrescarTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void checkMostrarInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMostrarInactivosActionPerformed
        refrescarTabla();
    }//GEN-LAST:event_checkMostrarInactivosActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JCheckBox checkMostrarInactivos;
    private javax.swing.JComboBox<String> comboCategorias;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblFiltrarCategoria;
    private javax.swing.JPanel panelBotonesAccion;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelTablaProductos;
    private javax.swing.JScrollPane scrollPaneProductos;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtBuscarProducto;
    // End of variables declaration//GEN-END:variables
}
