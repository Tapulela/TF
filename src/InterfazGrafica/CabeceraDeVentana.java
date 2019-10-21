/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import javax.swing.JFrame;

/**
 *
 * @author usuario
 */
public class CabeceraDeVentana extends javax.swing.JPanel {
    private JFrame ventanaAnterior;
    private JFrame ventanaActual;
    /**
     * Creates new form CabeceraDeVentana
     */
    public CabeceraDeVentana() {
        initComponents();
        this.setBackground(ParametrosDeInterfaz.colorFondo);
    }
    
    public void configurarCabecera (JFrame ventanaAnterior, JFrame ventanaActual, String nombreVentana, String trayectoria){
        this.ventanaActual = ventanaActual;
        this.ventanaAnterior = ventanaAnterior;
        setNombreVentana(nombreVentana);
        setTrayectoria(trayectoria);
    }

    public void setVentanaAnterior(JFrame ventanaAnterior) {
        this.ventanaAnterior = ventanaAnterior;
    }

    public void setVentanaActual(JFrame ventanaActual) {
        this.ventanaActual = ventanaActual;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jBVolver = new javax.swing.JButton();
        jLTrayectoriaVentanas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLCabeceraNombreVentana = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/Logo.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBVolver.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/boton atras - pequeno.png"))); // NOI18N
        jBVolver.setText("Volver");
        jBVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVolverActionPerformed(evt);
            }
        });

        jLTrayectoriaVentanas.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLTrayectoriaVentanas.setText("Trayectoria de Ventanas");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("Sistema de Gestión de Producción de Yerba");

        jLCabeceraNombreVentana.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLCabeceraNombreVentana.setText("Nombre de Ventana");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                        .addComponent(jLCabeceraNombreVentana))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBVolver)
                        .addGap(18, 18, 18)
                        .addComponent(jLTrayectoriaVentanas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLCabeceraNombreVentana))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBVolver)
                            .addComponent(jLTrayectoriaVentanas)))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setNombreVentana(String unNombre){
        jLCabeceraNombreVentana.setText(unNombre);
    }
    public void setTrayectoria (String unaTrayectoria){
        jLTrayectoriaVentanas.setText(unaTrayectoria);
    }
    private void jBVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVolverActionPerformed
        this.ventanaAnterior.setVisible(true);
        this.ventanaActual.dispose();
    }//GEN-LAST:event_jBVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBVolver;
    private javax.swing.JLabel jLCabeceraNombreVentana;
    private javax.swing.JLabel jLTrayectoriaVentanas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
