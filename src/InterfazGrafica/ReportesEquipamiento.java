/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import LogicaDeNegocio.Bascula;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.Organizacion;
import Reportes.GeneradorDeReportes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;


/**
 *
 * @author usuario
 */
public class ReportesEquipamiento extends javax.swing.JFrame {

    private JFrame ventanaAnterior;
    private String trayectoriaActual;
    private Organizacion organizacion;
    private Map<String, Boolean> criteriosSeleccionados;
    private Equipamiento unEquipamientoSeleccionado;
    /**
     * Creates new form BuscarEquipamiento
     */
    
    
    public ReportesEquipamiento() {
        initComponents();
    }

    public ReportesEquipamiento(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior) {
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.trayectoriaActual = trayectoriaAnterior + " - Reportes de Equipamiento";
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Reporte de Equipamientos", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
        setIconImage(new ImageIcon(getClass().getResource("../InterfazGrafica/Assets/Icono.png")).getImage());
        
        this.setVisible(true); 
        this.getContentPane().setBackground(ParametrosDeInterfaz.colorFondo);
        this.organizacion = organizacion;
        this.cargaBasculas();
        
        criteriosSeleccionados = new HashMap<String, Boolean>();
        criteriosSeleccionados.put("Nombre", false);
        criteriosSeleccionados.put("Tipo", false);
        criteriosSeleccionados.put("Direccion", false);
        criteriosSeleccionados.put("FechaAdquisicion", false);
        criteriosSeleccionados.put("FechaUltimoMantenimiento", false);
        criteriosSeleccionados.put("Estado", false);
        criteriosSeleccionados.put("BasculaAsociada", false);
        
        
        configurarInterfaz(this.getContentPane().getComponents());
        
    }
    private void configurarInterfaz(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            unComponente.setFont(ParametrosDeInterfaz.fuentePorDefecto);
            if (unComponente instanceof JPanel){
                configurarInterfaz(((JPanel) unComponente).getComponents());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCBNombre = new javax.swing.JCheckBox();
        jCheckBoxTipoEquipamiento = new javax.swing.JCheckBox();
        jCBDireccion = new javax.swing.JCheckBox();
        jCBFechaAdquisicion = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jTFNombre = new javax.swing.JTextField();
        jCBTipoEquipamiento = new javax.swing.JComboBox<>();
        jTFDireccion = new javax.swing.JTextField();
        jCFechaAdquisicion = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCFechaAdquisicion1 = new com.toedter.calendar.JDateChooser();
        jCheckBox11 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jCFechaAdquisicion2 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jCFechaAdquisicion3 = new com.toedter.calendar.JDateChooser();
        jCBBasculaAsociada = new javax.swing.JCheckBox();
        jCBBascula = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jBConcretarAccion = new javax.swing.JButton();
        cabeceraDeVentana = new InterfazGrafica.CabeceraDeVentana();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Criterios de Filtrado");

        jCBNombre.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBNombre.setText("Nombre");
        jCBNombre.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBNombre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBNombreItemStateChanged(evt);
            }
        });

        jCheckBoxTipoEquipamiento.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCheckBoxTipoEquipamiento.setText("Tipo de Equipamiento");
        jCheckBoxTipoEquipamiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxTipoEquipamientoItemStateChanged(evt);
            }
        });

        jCBDireccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBDireccion.setText("Direccion");
        jCBDireccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBDireccionItemStateChanged(evt);
            }
        });

        jCBFechaAdquisicion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBFechaAdquisicion.setText("Fecha de Adquisicion");
        jCBFechaAdquisicion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBFechaAdquisicionItemStateChanged(evt);
            }
        });

        jCheckBox10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCheckBox10.setText("Estado");
        jCheckBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox10ItemStateChanged(evt);
            }
        });

        jTFNombre.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFNombre.setText("Ingrese un Nombre");
        jTFNombre.setEnabled(false);

        jCBTipoEquipamiento.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBTipoEquipamiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Molino", "Camara de estacionamiento acelerado", "Deposito", "Bascula" }));
        jCBTipoEquipamiento.setEnabled(false);
        jCBTipoEquipamiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBTipoEquipamientoItemStateChanged(evt);
            }
        });

        jTFDireccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFDireccion.setText("Ingrese una direccion");
        jTFDireccion.setEnabled(false);

        jCFechaAdquisicion.setEnabled(false);
        jCFechaAdquisicion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setText("Desde");
        jLabel4.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setText("Hasta");
        jLabel5.setEnabled(false);

        jCFechaAdquisicion1.setEnabled(false);
        jCFechaAdquisicion1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jCheckBox11.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCheckBox11.setText("Fecha de ultimo mantenimiento");
        jCheckBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox11ItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel6.setText("Desde");
        jLabel6.setEnabled(false);

        jCFechaAdquisicion2.setEnabled(false);
        jCFechaAdquisicion2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel7.setText("Hasta");
        jLabel7.setEnabled(false);

        jCFechaAdquisicion3.setEnabled(false);
        jCFechaAdquisicion3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jCBBasculaAsociada.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBBasculaAsociada.setText("Bascula asociada");
        jCBBasculaAsociada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBBasculaAsociadaItemStateChanged(evt);
            }
        });

        jCBBascula.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBBascula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jCBBascula.setEnabled(false);

        jComboBox2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Baja" }));
        jComboBox2.setEnabled(false);

        jBConcretarAccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBConcretarAccion.setText("Generar reporte");
        jBConcretarAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConcretarAccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBBasculaAsociada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBBascula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBConcretarAccion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBDireccion)
                                .addGap(18, 18, 18)
                                .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBNombre)
                                .addGap(18, 18, 18)
                                .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBoxTipoEquipamiento)
                                .addGap(18, 18, 18)
                                .addComponent(jCBTipoEquipamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox11)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(6, 6, 6)
                                .addComponent(jCFechaAdquisicion2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCFechaAdquisicion3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBFechaAdquisicion)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(6, 6, 6)
                                .addComponent(jCFechaAdquisicion, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCFechaAdquisicion1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBNombre)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxTipoEquipamiento)
                    .addComponent(jCBTipoEquipamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBDireccion)
                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCFechaAdquisicion1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jCFechaAdquisicion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jCBFechaAdquisicion)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCFechaAdquisicion3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jCFechaAdquisicion2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jCheckBox11)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox10)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBBasculaAsociada)
                            .addComponent(jCBBascula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBConcretarAccion)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBConcretarAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConcretarAccionActionPerformed
        try {

            
            Bascula unaBascula = this.organizacion.getUnaBascula((String) jCBBascula.getSelectedItem());
            ArrayList listaFiltrada = null;
            try {
                listaFiltrada = this.organizacion.filtrarEquipamientos(this.criteriosSeleccionados, jTFNombre.getText(), (String)jCBTipoEquipamiento.getSelectedItem(), jTFDireccion.getText(), jCFechaAdquisicion.getCalendar(), jCFechaAdquisicion1.getCalendar(), jCFechaAdquisicion2.getCalendar(), jCFechaAdquisicion3.getCalendar(),(String) jComboBox2.getSelectedItem(), unaBascula);
            } catch (ExcepcionCargaParametros ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
            Map<String, String> criterios = getCriterios();
            GeneradorDeReportes.generarReporteEquipamiento(this.criteriosSeleccionados, criterios, listaFiltrada, "Usuario");
            JOptionPane.showMessageDialog(null, "Reporte de Equipamiento generado exitosamente");
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jBConcretarAccionActionPerformed

    private void jCBTipoEquipamientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBTipoEquipamientoItemStateChanged

    }//GEN-LAST:event_jCBTipoEquipamientoItemStateChanged

    private void jCBNombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBNombreItemStateChanged
        jTFNombre.setEnabled(jCBNombre.isSelected());
        criteriosSeleccionados.put("Nombre", jCBNombre.isSelected());
        
    }//GEN-LAST:event_jCBNombreItemStateChanged

    private void jCheckBoxTipoEquipamientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxTipoEquipamientoItemStateChanged
        jCBTipoEquipamiento.setEnabled(jCheckBoxTipoEquipamiento.isSelected());
        criteriosSeleccionados.put("Tipo", jCheckBoxTipoEquipamiento.isSelected());
    }//GEN-LAST:event_jCheckBoxTipoEquipamientoItemStateChanged

    private void jCBDireccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBDireccionItemStateChanged
        jTFDireccion.setEnabled(jCBDireccion.isSelected());
        criteriosSeleccionados.put("Direccion", jCBDireccion.isSelected());
    }//GEN-LAST:event_jCBDireccionItemStateChanged

    private void jCBFechaAdquisicionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBFechaAdquisicionItemStateChanged
        jLabel4.setEnabled(jCBFechaAdquisicion.isSelected());
        jCFechaAdquisicion.setEnabled(jCBFechaAdquisicion.isSelected());
        jLabel5.setEnabled(jCBFechaAdquisicion.isSelected());
        jCFechaAdquisicion1.setEnabled(jCBFechaAdquisicion.isSelected());
        criteriosSeleccionados.put("FechaAdquisicion", jCBFechaAdquisicion.isSelected());
    }//GEN-LAST:event_jCBFechaAdquisicionItemStateChanged

    private void jCheckBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox11ItemStateChanged
        jLabel6.setEnabled(jCheckBox11.isSelected());
        jCFechaAdquisicion2.setEnabled(jCheckBox11.isSelected());
        jLabel7.setEnabled(jCheckBox11.isSelected());
        jCFechaAdquisicion3.setEnabled(jCheckBox11.isSelected());
        criteriosSeleccionados.put("FechaUltimoMantenimiento", jCheckBox11.isSelected());
        
    }//GEN-LAST:event_jCheckBox11ItemStateChanged

    private void jCheckBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox10ItemStateChanged
        jComboBox2.setEnabled(jCheckBox10.isSelected());
        criteriosSeleccionados.put("Estado", jCheckBox10.isSelected());
    }//GEN-LAST:event_jCheckBox10ItemStateChanged

    private void jCBBasculaAsociadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBBasculaAsociadaItemStateChanged
        jCBBascula.setEnabled(jCBBasculaAsociada.isSelected());
        criteriosSeleccionados.put("BasculaAsociada", jCBBasculaAsociada.isSelected());
    }//GEN-LAST:event_jCBBasculaAsociadaItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReportesEquipamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportesEquipamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportesEquipamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportesEquipamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportesEquipamiento().setVisible(true);
            }
        });
    }
    private void cargaBasculas(){
        this.jCBBascula.removeAllItems();
        this.jCBBascula.addItem("Seleccionar");
        ArrayList basculasActivas = organizacion.getBasculasActivas();
        Iterator recorredorDeBasculas = basculasActivas.iterator();
        while (recorredorDeBasculas.hasNext()){
            Bascula unaBascula = (Bascula) recorredorDeBasculas.next();
            this.jCBBascula.addItem(unaBascula.toString());
        }
        
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JComboBox<String> jCBBascula;
    private javax.swing.JCheckBox jCBBasculaAsociada;
    private javax.swing.JCheckBox jCBDireccion;
    private javax.swing.JCheckBox jCBFechaAdquisicion;
    private javax.swing.JCheckBox jCBNombre;
    private javax.swing.JComboBox<String> jCBTipoEquipamiento;
    private com.toedter.calendar.JDateChooser jCFechaAdquisicion;
    private com.toedter.calendar.JDateChooser jCFechaAdquisicion1;
    private com.toedter.calendar.JDateChooser jCFechaAdquisicion2;
    private com.toedter.calendar.JDateChooser jCFechaAdquisicion3;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBoxTipoEquipamiento;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFNombre;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        jTFNombre.setText("");
        jTFDireccion.setText("");
        
        jBConcretarAccion.setEnabled(false);

        this.unEquipamientoSeleccionado = null;
    }

    private Map<String, String> getCriterios(){
        Map<String, String> retorno = new HashMap<String, String>();
        Boolean criterioNombre = criteriosSeleccionados.get("Nombre");
        Boolean criterioTipo = criteriosSeleccionados.get("Tipo");
        Boolean criterioDireccion = criteriosSeleccionados.get("Direccion");
        Boolean criteriofechaAdquisicion = criteriosSeleccionados.get("FechaAdquisicion");
        Boolean criteriofechaUltimoMantenimiento = criteriosSeleccionados.get("FechaUltimoMantenimiento");
        Boolean criterioEstado = criteriosSeleccionados.get("Estado");
        Boolean criterioBasculaAsociada = criteriosSeleccionados.get("BasculaAsociada");
        
        if (criterioNombre)
            retorno.put("Nombre", jTFNombre.getText());
        if (criterioTipo)
            retorno.put("Tipo", (String) jCBTipoEquipamiento.getSelectedItem());
        if (criterioDireccion)
            retorno.put("Direccion", jTFDireccion.getText());
        if (criteriofechaAdquisicion){
            String fechaAdDesde = ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( jCFechaAdquisicion.getCalendar().getTime() );
            String fechaAdHasta = ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( jCFechaAdquisicion1.getCalendar().getTime() );
            retorno.put("FechaAdquisicion", "Desde "+fechaAdDesde+" hasta "+fechaAdHasta);
        }
        if (criteriofechaUltimoMantenimiento){
            String fechaUlDesde = ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( jCFechaAdquisicion2.getCalendar().getTime() );
            String fechaUlHasta = ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( jCFechaAdquisicion3.getCalendar().getTime() );
            retorno.put("FechaUltimoMantenimiento", "Desde "+fechaUlDesde+" hasta "+fechaUlHasta);   
        }
        if (criterioEstado)
            retorno.put("Estado", (String) jComboBox2.getSelectedItem());
        if (criterioBasculaAsociada){
            Bascula unaBascula = this.organizacion.getUnaBascula((String) jCBBascula.getSelectedItem());
            if (unaBascula != null){
                retorno.put("BasculaAsociada", unaBascula.getNombre());
            }else{
                retorno.put("BasculaAsociada", "");
            }
        }
            
        return retorno;
    }

}
