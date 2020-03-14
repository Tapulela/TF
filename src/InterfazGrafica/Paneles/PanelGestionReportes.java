/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Paneles;

import InterfazGrafica.Inicio;
import InterfazGrafica.ParametrosDeInterfaz;
import LogicaDeNegocio.ExcepcionCargaParametros;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author usuario
 */
public class PanelGestionReportes extends javax.swing.JPanel {

    /**
     * Creates new form PanelGestionEstacionamientos
     */
    public PanelGestionReportes() {
        initComponents();
    }
    private JFrame ventanaContenedora;

    public PanelGestionReportes(JFrame ventanaContenedora) {
        this.ventanaContenedora = ventanaContenedora;
        initComponents();
        this.setVisible(true);
        ParametrosDeInterfaz.confeccionarComponentes(this.getComponents());
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton10 = new javax.swing.JButton();
        jLEstaticoCriterio1 = new javax.swing.JLabel();
        jLEstaticoCriterio2 = new javax.swing.JLabel();
        jLEstaticoCriterio3 = new javax.swing.JLabel();
        dato1Criterio6 = new com.toedter.calendar.JDateChooser();

        jButton10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/icono-Opcion.png"))); // NOI18N
        jButton10.setText("Generar reporte de lotes en existencia para el INYM");
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLEstaticoCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio1.setText("Reportes");

        jLEstaticoCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio2.setText("Para generar el reporte de existencia de yerba en la organización, seleccione una fecha y presione el botón para generar el reporte");

        jLEstaticoCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio3.setText("Fecha a evaluar");

        dato1Criterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        dato1Criterio6.setMaximumSize(new java.awt.Dimension(2147483647, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLEstaticoCriterio1)
                    .addComponent(jLEstaticoCriterio2)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLEstaticoCriterio3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dato1Criterio6, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLEstaticoCriterio1)
                .addGap(18, 18, 18)
                .addComponent(jLEstaticoCriterio2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLEstaticoCriterio3)
                    .addComponent(dato1Criterio6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addContainerGap(557, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            
            ((Inicio)ventanaContenedora).getOrganizacion().generarReporteINYMv4(dato1Criterio6.getCalendar());
            JOptionPane.showMessageDialog(null, "Reporte de existencia de INYM generado exitosamente.");
        } catch (ExcepcionCargaParametros | JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dato1Criterio6;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLEstaticoCriterio1;
    private javax.swing.JLabel jLEstaticoCriterio2;
    private javax.swing.JLabel jLEstaticoCriterio3;
    // End of variables declaration//GEN-END:variables
}
