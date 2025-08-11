/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import javax.swing.JOptionPane;
import servicio.ServicioAutenticacion;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class LoginVentana extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginVentana.class.getName());
    private final ServicioAutenticacion servicioAutenticacion;

    /**
     * Creates new form LoginVentana
     */
    public LoginVentana() {
        initComponents();
        this.servicioAutenticacion = new ServicioAutenticacion();
    }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelIzquierdo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTituloApp = new javax.swing.JLabel();
        panelDerecho = new javax.swing.JPanel();
        lblTituloLogin = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión - Cafetería UTN");
        setPreferredSize(new java.awt.Dimension(700, 450));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        panelIzquierdo.setBackground(new java.awt.Color(230, 220, 245));
        panelIzquierdo.setLayout(new java.awt.BorderLayout());

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/taza1.png"))); // NOI18N
        panelIzquierdo.add(lblLogo, java.awt.BorderLayout.CENTER);

        lblTituloApp.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTituloApp.setForeground(new java.awt.Color(85, 65, 118));
        lblTituloApp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloApp.setText("Cafetería UTN");
        lblTituloApp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 20, 1));
        panelIzquierdo.add(lblTituloApp, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panelIzquierdo);

        panelDerecho.setBackground(new java.awt.Color(255, 255, 255));
        panelDerecho.setLayout(new java.awt.GridLayout(6, 1, 0, 5));

        lblTituloLogin.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTituloLogin.setForeground(new java.awt.Color(85, 65, 118));
        lblTituloLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloLogin.setText("Iniciar Sesión");
        lblTituloLogin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panelDerecho.add(lblTituloLogin);

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuario.setText("Usuario:");
        panelDerecho.add(lblUsuario);

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelDerecho.add(txtUsername);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(102, 102, 102));
        lblPassword.setText("Contraseña:");
        panelDerecho.add(lblPassword);

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelDerecho.add(txtPassword);

        btnIngresar.setBackground(new java.awt.Color(123, 104, 238));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        panelDerecho.add(btnIngresar);

        getContentPane().add(panelDerecho);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim(); 

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El usuario y la contraseña no pueden estar vacíos.", "Campos Requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        dominio.Usuario usuarioValidado = servicioAutenticacion.login(username, password);

        if (usuarioValidado != null) {

            JOptionPane.showMessageDialog(this, "¡Bienvenido, " + usuarioValidado.getUsername() + "!", "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();

            ventanaPrincipal vp = new ventanaPrincipal();
            vp.setVisible(true);
            vp.setResizable(false);
            vp.setLocationRelativeTo(null);
            
        } else {

            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTituloApp;
    private javax.swing.JLabel lblTituloLogin;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel panelIzquierdo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
