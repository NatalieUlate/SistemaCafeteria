/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import dominio.Venta;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import servicio.ServicioVenta;
import utilidades.Formateador;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class HistorialVentasDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HistorialVentasDialog.class.getName());
    private final ServicioVenta servicioVenta;


    public HistorialVentasDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.servicioVenta = new ServicioVenta();

        configurarTabla();
        cargarVentasDelDia();

        this.setLocationRelativeTo(parent);
    }

    private void configurarTabla() {
        String[] columnas = {"ID Venta", "Fecha y Hora", "Total"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaHistorial.setModel(model);
    }

    /**
     * Llama al servicio para obtener las ventas del día y las muestra en la
     * tabla.
     */
    private void cargarVentasDelDia() {
        DefaultTableModel model = (DefaultTableModel) tablaHistorial.getModel();
        model.setRowCount(0); 

        List<Venta> ventas = servicioVenta.consultarVentasDelDia();

        for (Venta venta : ventas) {
            Object[] row = {
                venta.getId(),
                Formateador.formatearFecha(venta.getFechaHora()), 
                Formateador.formatearMoneda(venta.getTotal()) 
            };
            model.addRow(row);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Venta", "Fecha y Hora", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaHistorial);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
       this.dispose(); 
    }//GEN-LAST:event_btnCerrarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHistorial;
    // End of variables declaration//GEN-END:variables
}
