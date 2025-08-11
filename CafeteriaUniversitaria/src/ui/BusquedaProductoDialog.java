/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import dominio.Producto;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import utilidades.Formateador;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class BusquedaProductoDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BusquedaProductoDialog.class.getName());
    private final List<Producto> listaDeResultados;
    private Producto productoSeleccionado;


    public BusquedaProductoDialog(java.awt.Frame parent, boolean modal, List<Producto> resultados) {
        super(parent, modal);
        initComponents();
        this.listaDeResultados = resultados;
        this.productoSeleccionado = null;

        cargarResultadosEnTabla();

        this.setLocationRelativeTo(parent);
    }

    public Producto getProductoSeleccionado() {
        return this.productoSeleccionado;
    }

    private void cargarResultadosEnTabla() {
        DefaultTableModel model = (DefaultTableModel) tablaResultados.getModel();
        model.setRowCount(0);

        for (Producto producto : this.listaDeResultados) {
            Object[] row = {
                producto.getId(),
                producto.getNombreCompleto(),
                Formateador.formatearMoneda(producto.getPrecioUnitario())
            };
            model.addRow(row);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnSeleccionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre del Producto", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaResultados);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSeleccionar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        int filaSeleccionada = tablaResultados.getSelectedRow();
        if (filaSeleccionada != -1) {
            this.productoSeleccionado = this.listaDeResultados.get(filaSeleccionada);
            this.dispose(); 
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.productoSeleccionado = null; 
        this.dispose(); 
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaResultados;
    // End of variables declaration//GEN-END:variables
}
