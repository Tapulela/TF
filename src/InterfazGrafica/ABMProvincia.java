/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Pais;
import LogicaDeNegocio.Proveedor;
import LogicaDeNegocio.Provincia;
import Persistencia.ExcepcionPersistencia;
import java.awt.Component;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author usuario
 */
public class ABMProvincia extends javax.swing.JFrame implements TransferenciaInstancias {
    
    /**
     * Creates new form ABMProveedor
     */
    private Organizacion organizacion;
    private JFrame ventanaAnterior;
    private Proveedor unObjetoSeleccionado;
    private String trayectoriaActual;
    private String operacionActual;
    
    private final String textoAlta = "Dar de alta una Provincia";
    private final String textoBaja = "Dar de baja una Provincia";
    private final String textoModificacion = "Modificar una Provincia";
    
    public void setUnObjetoSeleccionado(Proveedor unObjetoSeleccionado) {
        this.unObjetoSeleccionado = unObjetoSeleccionado;
    }
    public ABMProvincia() {
        initComponents();
    }



    public ABMProvincia(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior) {
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.organizacion = organizacion;
        this.getContentPane().setBackground(ParametrosDeInterfaz.colorFondo);
        setIconImage(new ImageIcon(getClass().getResource(ParametrosDeInterfaz.rutaIcono)).getImage());
        
        trayectoriaActual = trayectoriaAnterior+" - Gestión de provincias";
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Gestión de provincias", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
        
        

        this.setVisible(true); 
        jCB1.setVisible(false);
        jCB1.setEnabled(false);
        jLStaticCB1.setVisible(false);
        jLStaticCB1.setVisible(false);
        
        
        this.ventanaAnterior = ventanaAnterior;
        cargarEstados();
        cargarPaisesActivos();
        habilitarCamposIniciales();
        ParametrosDeInterfaz.configurarVentana(this);
    }
    private void organizarElementos(){
        this.deshabilitarTodo();
        switch((String)jCBOperacion.getSelectedItem()){
            case "Alta":
                prepararAlta();
                break;
            case "Baja":
                prepararBaja();
                break;
            case "Modificacion":
                prepararModificacion();
                break;
            default:
                limpiarCampos();
                break;
                
        }
        //this.pack();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLStaticCampo1 = new javax.swing.JLabel();
        jTFCampo1 = new javax.swing.JTextField();
        jBConcretarAccion = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jCBOperacion = new javax.swing.JComboBox<>();
        jLEstado = new javax.swing.JLabel();
        jCBEstado = new javax.swing.JComboBox<>();
        cabeceraDeVentana = new InterfazGrafica.CabeceraDeVentana();
        jCB1 = new javax.swing.JComboBox<>();
        jLStaticCB1 = new javax.swing.JLabel();
        jCB2 = new javax.swing.JComboBox<>();
        jLStaticCB2 = new javax.swing.JLabel();
        jLOperacionSeleccionada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));

        jLStaticCampo1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticCampo1.setText("Nombre");
        jLStaticCampo1.setEnabled(false);

        jTFCampo1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFCampo1.setEnabled(false);

        jBConcretarAccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBConcretarAccion.setText("Aceptar");
        jBConcretarAccion.setEnabled(false);
        jBConcretarAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConcretarAccionActionPerformed(evt);
            }
        });

        jBCancelar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBCancelar.setText("Cancelar");
        jBCancelar.setEnabled(false);
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel12.setText("Seleccione una operacion");

        jCBOperacion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBOperacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Alta", "Baja", "Modificacion" }));
        jCBOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBOperacionItemStateChanged(evt);
            }
        });

        jLEstado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstado.setText("Estado");
        jLEstado.setEnabled(false);

        jCBEstado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Baja" }));
        jCBEstado.setEnabled(false);

        jCB1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jCB1.setEnabled(false);
        jCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB1ItemStateChanged(evt);
            }
        });

        jLStaticCB1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticCB1.setText("Seleccione una provincia");
        jLStaticCB1.setEnabled(false);

        jCB2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jCB2.setEnabled(false);

        jLStaticCB2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticCB2.setText("País asociado");
        jLStaticCB2.setEnabled(false);

        jLOperacionSeleccionada.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLOperacionSeleccionada.setText("Operación sobre un Concepto");
        jLOperacionSeleccionada.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.DEFAULT_SIZE, 1453, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBConcretarAccion)
                        .addGap(18, 18, 18)
                        .addComponent(jBCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCBOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLStaticCB1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLOperacionSeleccionada)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLStaticCB2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLStaticCampo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFCampo1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBCancelar)
                            .addComponent(jBConcretarAccion))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jCBOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLStaticCB1)
                            .addComponent(jCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLOperacionSeleccionada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLEstado)
                            .addComponent(jCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLStaticCB2)
                            .addComponent(jCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLStaticCampo1)
                            .addComponent(jTFCampo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(36, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jBConcretarAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConcretarAccionActionPerformed
       
        try {
            switch ((String)jCBOperacion.getSelectedItem()){
                case "Alta":
                    if (jCB2.getSelectedItem() instanceof Pais)
                        organizacion.registrarProvincia((Pais)jCB2.getSelectedItem(), jTFCampo1.getText());
                    else
                        throw new ExcepcionCargaParametros("No se ha seleccionado una Provincia para dar de baja.");
                    break;                    
                case "Baja":
                    if (jCB1.getSelectedItem() instanceof Provincia)
                        organizacion.darDeBajaUnaProvincia((Provincia)jCB1.getSelectedItem());
                    else
                        throw new ExcepcionCargaParametros("No se ha seleccionado una Provincia para dar de baja.");
                    break;
                case "Modificacion":
                    if (jCB1.getSelectedItem() instanceof Provincia)
                        organizacion.modificarProvincia((Provincia)jCB1.getSelectedItem(), jTFCampo1.getText(), (String) jCBEstado.getSelectedItem());
                    else
                        throw new ExcepcionCargaParametros("No se ha seleccionado una Provincia para dar de baja.");
                    break;
            }
            JOptionPane.showMessageDialog(null, "Operacion realizada con exito.");
            deshabilitarTodo();
            limpiarCampos();
            habilitarCamposIniciales();
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: "+ex.getMessage());
        } catch (ExcepcionPersistencia ex) {
            JOptionPane.showMessageDialog(null, "Error en la persistencia: "+ex.getMessage());
        } catch (ClassCastException ex) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Provincia");
        }
        
    }//GEN-LAST:event_jBConcretarAccionActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.deshabilitarTodo();
        this.limpiarCampos();
        this.habilitarCamposIniciales();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jCBOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBOperacionItemStateChanged
        if (!jCBOperacion.getSelectedItem().equals("Seleccionar")){
            this.organizarElementos();
        }
    }//GEN-LAST:event_jCBOperacionItemStateChanged

    private void jCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB1ItemStateChanged
        if (!(jCB1.getSelectedItem() instanceof Provincia))
            return;
        Provincia unaProvincia = (Provincia) jCB1.getSelectedItem();
        jTFCampo1.setText(unaProvincia.getNombre());
        jCBEstado.setSelectedItem(unaProvincia.getEstado());
        jCB2.setSelectedItem(unaProvincia.getPaisAsociado());
        organizarElementos();
        //this.pack();
    }//GEN-LAST:event_jCB1ItemStateChanged

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
            java.util.logging.Logger.getLogger(ABMProvincia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ABMProvincia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ABMProvincia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ABMProvincia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMProvincia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JComboBox<Object> jCB1;
    private javax.swing.JComboBox<Object> jCB2;
    private javax.swing.JComboBox<String> jCBEstado;
    private javax.swing.JComboBox<String> jCBOperacion;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLOperacionSeleccionada;
    private javax.swing.JLabel jLStaticCB1;
    private javax.swing.JLabel jLStaticCB2;
    private javax.swing.JLabel jLStaticCampo1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JTextField jTFCampo1;
    // End of variables declaration//GEN-END:variables

    private void deshabilitarTodo(){
        //TAMBIEN SE HACEN INVISIBLE LOS ELEMENTOS QUE SEAN NECESARIOS
        
        jLOperacionSeleccionada.setEnabled(false);
        
        jLStaticCampo1.setEnabled(false);
        
        jTFCampo1.setEnabled(false);
        
        jLEstado.setEnabled(false);
        jLStaticCB1.setEnabled(false);
        jLStaticCB2.setEnabled(false);
        
        jCBEstado.setEnabled(false);
        jCB2.setEnabled(false);
        
        jCBOperacion.setEnabled(false);
        jCB1.setVisible(false);
        jCB1.setEnabled(false);
        jLStaticCB1.setVisible(false);
        jLStaticCB1.setVisible(false);
        jBConcretarAccion.setEnabled(false);
        jBCancelar.setEnabled(false);
        
    }
    
    private void limpiarCampos() {
                this.operacionActual = "";
                
                jTFCampo1.setText("");
                
                jCB1.setSelectedItem("Seleccionar");
                jCB2.setSelectedItem("Seleccionar");
                this.unObjetoSeleccionado = null;
    }

    @Override
    public void actualizarUnObjeto(Object UnObjeto) {
        Proveedor unProveedor = (Proveedor) UnObjeto;
        this.unObjetoSeleccionado = unProveedor;
        jTFCampo1.setText(unProveedor.getRazonSocial());
        jCBEstado.setSelectedItem(unProveedor.getEstado());
        
        organizarElementos();
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
        //this.pack();
    }

    private void prepararAlta() {
        this.operacionActual = "Alta";
        jBConcretarAccion.setText("Dar de alta");
        jLOperacionSeleccionada.setText(this.textoAlta);
        
        jLOperacionSeleccionada.setEnabled(true);
        
        jLStaticCampo1.setEnabled(true);
        
        jTFCampo1.setEnabled(true);
        
        jLStaticCB1.setEnabled(true);
        jLStaticCB2.setEnabled(true);
        
        jCB2.setEnabled(true);
        
        
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
        
        
        
    }
    
    private void prepararBaja() {
        this.operacionActual = "Baja";
        
        jCB1.setVisible(true);
        jCB1.setEnabled(true);
        jLStaticCB1.setVisible(true);
        jLStaticCB1.setVisible(true);
        
        jLOperacionSeleccionada.setText(textoBaja);
        jLOperacionSeleccionada.setEnabled(true);
        
        jLStaticCampo1.setEnabled(true);
        
        jLEstado.setEnabled(true);
        jLStaticCB1.setEnabled(true);
        jLStaticCB2.setEnabled(true);
        
        jBCancelar.setEnabled(true);
        
        if (jCB1.getSelectedItem().equals("Seleccionar")){
            cargarProvinciasActivas();
            return;
        }
        jBConcretarAccion.setEnabled(true);
        jBConcretarAccion.setText("Dar de baja");
        
    }

    private void prepararModificacion() {
        this.operacionActual = "Modificacion";
        jLOperacionSeleccionada.setText(this.textoModificacion);
        jLOperacionSeleccionada.setEnabled(true);
        jLStaticCB1.setEnabled(true);
        
        jCB1.setVisible(true);
        jCB1.setEnabled(true);
        jLStaticCB1.setVisible(true);
        jLStaticCB1.setVisible(true);
        jBCancelar.setEnabled(true);
        
        
        if (jCB1.getSelectedItem().equals("Seleccionar")){
            cargarProvincias();
            return;
        }
        
        jLOperacionSeleccionada.setEnabled(false);
        
        jLStaticCampo1.setEnabled(true);
        
        jTFCampo1.setEnabled(true); //SI ALGUN CAMPO NO SE PUDIERA MODIFICAR, (POR EJEMPLO, FECHA DE CREACION, CONCEPTO ASOCIADO, ETC. BORRAR ESTAS LINEAS)
        
        jLEstado.setEnabled(true);
        jLStaticCB2.setEnabled(true);
        
        jCBEstado.setEnabled(true);
        jCB1.setEnabled(true);
        
        jBConcretarAccion.setEnabled(true);
        jBConcretarAccion.setText("Guardar cambios");

    }

    public String getOperacionActual() {
        return operacionActual;
    }
    private void cargarPaisesActivos() {
        jCB2.removeAllItems();
        jCB2.addItem("Seleccionar");
        Iterator paises = organizacion.getPaises().keySet().iterator();
        while (paises.hasNext()){
            int unId = (int) paises.next();
            Pais unPais = (Pais) organizacion.getPaises().get(unId);
            if (unPais.seEncuentraActivo())
                jCB2.addItem(unPais);
        }
    }
    private void cargarProvincias() {
        jCB1.removeAllItems();
        jCB1.addItem("Seleccionar");
        Iterator provincias = organizacion.getProvincias().keySet().iterator();
        while (provincias.hasNext()){
            int unId = (int) provincias.next();
            Provincia unaProvincia = (Provincia) organizacion.getProvincias().get(unId);
            jCB1.addItem(unaProvincia);
        }
    }
    
    private void cargarProvinciasActivas() {
        jCB1.removeAllItems();
        jCB1.addItem("Seleccionar");
        Iterator provincias = organizacion.getProvincias().keySet().iterator();
        while (provincias.hasNext()){
            int unId = (int) provincias.next();
            Provincia unaProvincia = (Provincia) organizacion.getProvincias().get(unId);
            if (unaProvincia.seEncuentraActiva())
                jCB1.addItem(unaProvincia);
        }
    }
    private void habilitarCamposIniciales() {
        jLabel12.setEnabled(true);
        jCBOperacion.setEnabled(true);
        this.jCBOperacion.setSelectedItem("Seleccionar");
    }

    private void cargarEstados() {
        jCBEstado.removeAllItems();
        jCBEstado.addItem(Provincia.ESTADO_ACTIVO);
        jCBEstado.addItem(Provincia.ESTADO_BAJA);
    }
    

}
