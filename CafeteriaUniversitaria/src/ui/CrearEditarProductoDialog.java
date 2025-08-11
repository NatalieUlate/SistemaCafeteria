/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import dominio.Categoria;
import dominio.Producto;
import servicio.ServicioProducto;
import utilidades.ValidadorEntrada;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class CrearEditarProductoDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CrearEditarProductoDialog.class.getName());
    private final ServicioProducto servicioProducto;
    private final Producto productoAEditar;

 
    public CrearEditarProductoDialog(java.awt.Dialog parent, boolean modal, Producto producto) {
        super(parent, modal);
        initComponents();
        this.servicioProducto = new ServicioProducto();
        this.productoAEditar = producto;

        cargarCategorias();
        configurarParaModoEdicion();

        this.setLocationRelativeTo(parent);
    }

    private void cargarCategorias() {
        DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel<>();
        List<Categoria> categorias = servicioProducto.listarCategoriasActivas();

        for (Categoria categoria : categorias) {
            model.addElement(categoria); 
        }
        comboCategoriaProducto.setModel(model);
    }

    /**
     * Configura la ventana para el modo de edición si es necesario.
     */
    private void configurarParaModoEdicion() {
        if (this.productoAEditar != null) {
    
            this.setTitle("Editar Producto");
            txtNombreProducto.setText(productoAEditar.getNombreCompleto());
            spinnerPrecio.setValue(productoAEditar.getPrecioUnitario());
            checkActivo.setSelected(productoAEditar.isActivo());

        
            DefaultComboBoxModel<Categoria> model = (DefaultComboBoxModel<Categoria>) comboCategoriaProducto.getModel();
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).getId() == productoAEditar.getCategoria().getId()) {
                    comboCategoriaProducto.setSelectedIndex(i);
                    break;
                }
            }
        } else {
           
            this.setTitle("Crear Nuevo Producto");
            checkActivo.setSelected(true); // Por defecto, un producto nuevo está activo
        }
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombreProducto = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        spinnerPrecio = new javax.swing.JSpinner();
        lblCategoriaProducto = new javax.swing.JLabel();
        comboCategoriaProducto = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        checkActivo = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Producto");
        setModal(true);
        setName("productoDialog"); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(5, 2, 10, 10));

        lblNombreProducto.setText("Nombre completo:");
        getContentPane().add(lblNombreProducto);

        txtNombreProducto.setToolTipText("Nombre descriptivo del producto, ejemplo: Cheesecake de Fresa");
        getContentPane().add(txtNombreProducto);

        lblPrecio.setText("Precio:");
        getContentPane().add(lblPrecio);

        spinnerPrecio.setModel(new javax.swing.SpinnerNumberModel(1000, null, null, 100));
        spinnerPrecio.setToolTipText("Precio de venta al público");
        getContentPane().add(spinnerPrecio);

        lblCategoriaProducto.setText("Categoría:");
        getContentPane().add(lblCategoriaProducto);

        comboCategoriaProducto.setToolTipText("Seleccione la categoría a la que pertenece el producto");
        getContentPane().add(comboCategoriaProducto);

        lblEstado.setText("Estado:");
        getContentPane().add(lblEstado);

        checkActivo.setText("Activo");
        checkActivo.setToolTipText("Marque si el producto está disponible para la venta");
        getContentPane().add(checkActivo);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (ValidadorEntrada.esTextoVacio(txtNombreProducto)) {
            JOptionPane.showMessageDialog(this, "El nombre del producto no puede estar vacío.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!ValidadorEntrada.esSeleccionValida(comboCategoriaProducto)) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = txtNombreProducto.getText().trim();
        double precio = ((Number) spinnerPrecio.getValue()).doubleValue();
        Categoria categoria = (Categoria) comboCategoriaProducto.getSelectedItem();
        boolean activo = checkActivo.isSelected();

        try {
            boolean exito;
            if (this.productoAEditar == null) {
              
                Producto nuevoProducto = new Producto(0, nombre, precio, activo, categoria);
                exito = servicioProducto.guardarProducto(nuevoProducto);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Producto creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
        
                this.productoAEditar.setNombreCompleto(nombre);
                this.productoAEditar.setPrecioUnitario(precio);
                this.productoAEditar.setCategoria(categoria);
                this.productoAEditar.setActivo(activo);
                exito = servicioProducto.guardarProducto(this.productoAEditar);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (exito) {
                this.dispose(); 
            }

        } catch (IllegalArgumentException e) {
            
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al guardar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JComboBox<Categoria> comboCategoriaProducto;
    private javax.swing.JLabel lblCategoriaProducto;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JSpinner spinnerPrecio;
    private javax.swing.JTextField txtNombreProducto;
    // End of variables declaration//GEN-END:variables
}
