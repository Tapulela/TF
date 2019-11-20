/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Paneles;

import InterfazGrafica.Busqueda.BuscarOrdenDeProduccion;
import InterfazGrafica.GestionIngresoMP;
import InterfazGrafica.GestionOrdenesProduccion;
import InterfazGrafica.Inicio;
import InterfazGrafica.TransferenciaInstancias;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import LogicaDeNegocio.Evento;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.OrdenDeProduccion;
import LogicaDeNegocio.Organizacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class PanelGestionOrdenProduccion extends javax.swing.JPanel implements TransferenciaInstancias{

    private Map<String, Boolean> criteriosSeleccionados;
    /**
     * Creates new form PanelGestionEstacionamientos
     */
    public PanelGestionOrdenProduccion() {
        initComponents();
    }
    private JFrame ventanaContenedora;
    
    private final String criterio1 = "estado";
    private final String criterio2 = "tipoEvento";
    private final String criterio3 = "fechaOrigen";
    
    private OrdenDeProduccion unaOrdenDeProduccionSeleccionada;
    
    private Organizacion organizacion;
    
    public PanelGestionOrdenProduccion(JFrame ventanaContenedora) {
        this.ventanaContenedora = ventanaContenedora;
        initComponents();
        this.setVisible(true);
        
        this.organizacion = ((Inicio)this.ventanaContenedora).getOrganizacion();
        
        jTable1.setRowHeight(30);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(60);
        criteriosSeleccionados = new HashMap<String, Boolean>();
        criteriosSeleccionados.put(criterio1, false);
        criteriosSeleccionados.put(criterio2, false);
        criteriosSeleccionados.put(criterio3, false);
        establecerAlineacionDeTabla(jTable1, SwingConstants.CENTER);
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
        jCBCriterio1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        datoCriterio1 = new javax.swing.JComboBox<>();
        jCBCriterio3 = new javax.swing.JCheckBox();
        jCBCriterio2 = new javax.swing.JCheckBox();
        datoCriterio2 = new javax.swing.JComboBox<>();
        jLEstatico1Criterio3 = new javax.swing.JLabel();
        dato1Criterio2 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio2 = new javax.swing.JLabel();
        dato2Criterio2 = new com.toedter.calendar.JDateChooser();
        botonBusqueda = new javax.swing.JButton();
        jLEstaticoCriterio1 = new javax.swing.JLabel();
        jLCriterio1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Seleccione una orden de producción");

        jCBCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio1.setText("estado de eventos posteriores");
        jCBCriterio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio1ItemStateChanged(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        datoCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Regular", "Anulado" }));
        datoCriterio1.setEnabled(false);

        jCBCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio3.setText("fecha de origen");
        jCBCriterio3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio3ItemStateChanged(evt);
            }
        });

        jCBCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio2.setText("tipo de evento");
        jCBCriterio2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio2ItemStateChanged(evt);
            }
        });

        datoCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Ingresos", "Movimientos", "Estacionamientos", "Analisis de laboratorios", "Moliendas" }));
        datoCriterio2.setEnabled(false);

        jLEstatico1Criterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstatico1Criterio3.setText("Desde");
        jLEstatico1Criterio3.setEnabled(false);

        dato1Criterio2.setEnabled(false);
        dato1Criterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstatico2Criterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstatico2Criterio2.setText("Hasta");
        jLEstatico2Criterio2.setEnabled(false);

        dato2Criterio2.setEnabled(false);
        dato2Criterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        botonBusqueda.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonBusqueda.setText("Buscar una Orden de Produccion");
        botonBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBusquedaActionPerformed(evt);
            }
        });

        jLEstaticoCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio1.setText("ID de orden de producción seleccionada");

        jLCriterio1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonBusqueda))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio1)
                                        .addGap(18, 18, 18)
                                        .addComponent(datoCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio2)
                                        .addGap(18, 18, 18)
                                        .addComponent(datoCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLEstaticoCriterio1)
                                .addGap(18, 18, 18)
                                .addComponent(jLCriterio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCBCriterio3)
                .addGap(18, 18, 18)
                .addComponent(jLEstatico1Criterio3)
                .addGap(6, 6, 6)
                .addComponent(dato1Criterio2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLEstatico2Criterio2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dato2Criterio2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(botonBusqueda))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLEstaticoCriterio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBCriterio1)
                            .addComponent(datoCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBCriterio2)
                            .addComponent(datoCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBCriterio3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLEstatico2Criterio2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                .addComponent(jLEstatico1Criterio3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dato1Criterio2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dato2Criterio2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(150, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Accion registrada", "Fecha", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(0, 153, 51));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton7.setText("Gestionar Ordenes de Produccion");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(48, 48, 48))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCBCriterio1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio1ItemStateChanged
        datoCriterio1.setEnabled(jCBCriterio1.isSelected());
        criteriosSeleccionados.put(criterio1, jCBCriterio1.isSelected());
    }//GEN-LAST:event_jCBCriterio1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            ArrayList listaFiltrada = null;
            listaFiltrada = this.organizacion.filtrarEventos(this.criteriosSeleccionados, this.unaOrdenDeProduccionSeleccionada, (String)datoCriterio1.getSelectedItem(), (String)datoCriterio2.getSelectedItem(), dato1Criterio2.getCalendar(), dato2Criterio2.getCalendar());

            ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
            Iterator eventos = listaFiltrada.iterator();
            while (eventos.hasNext()){
                Evento unEvento = (Evento) eventos.next();
                ((DefaultTableModel)this.jTable1.getModel()).addRow(unEvento.devolverVectorEvento());
            }
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCBCriterio3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio3ItemStateChanged
        jLEstatico1Criterio3.setEnabled(jCBCriterio3.isSelected());
        dato1Criterio2.setEnabled(jCBCriterio3.isSelected());
        jLEstatico2Criterio2.setEnabled(jCBCriterio3.isSelected());
        dato2Criterio2.setEnabled(jCBCriterio3.isSelected());
        criteriosSeleccionados.put(criterio3, jCBCriterio3.isSelected());
    }//GEN-LAST:event_jCBCriterio3ItemStateChanged

    private void jCBCriterio2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio2ItemStateChanged
        datoCriterio2.setEnabled(jCBCriterio2.isSelected());
        criteriosSeleccionados.put(criterio2, jCBCriterio2.isSelected());
    }//GEN-LAST:event_jCBCriterio2ItemStateChanged

    private void botonBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBusquedaActionPerformed

        BuscarOrdenDeProduccion unaVentana = new BuscarOrdenDeProduccion(this.organizacion, this.ventanaContenedora,((Inicio)this.ventanaContenedora).getTrayectoriaActual());
        this.ventanaContenedora.dispose();
    }//GEN-LAST:event_botonBusquedaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        /*int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        this.unaOrdenDeProduccionSeleccionada = this.organizacion.getProveedores().get(id);
        actualizarObjetoSeleccionado();
        habilitarBotones();*/

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        GestionOrdenesProduccion ventana = new GestionOrdenesProduccion(((Inicio) ventanaContenedora).getOrganizacion(), ventanaContenedora, ((Inicio) ventanaContenedora).getTrayectoriaActual());
        this.ventanaContenedora.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBusqueda;
    private com.toedter.calendar.JDateChooser dato1Criterio2;
    private com.toedter.calendar.JDateChooser dato2Criterio2;
    private javax.swing.JComboBox<String> datoCriterio1;
    private javax.swing.JComboBox<String> datoCriterio2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JLabel jLCriterio1;
    private javax.swing.JLabel jLEstatico1Criterio3;
    private javax.swing.JLabel jLEstatico2Criterio2;
    private javax.swing.JLabel jLEstaticoCriterio1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizarUnObjeto(Object unObjeto) {
        if (unObjeto instanceof OrdenDeProduccion){
            this.unaOrdenDeProduccionSeleccionada = (OrdenDeProduccion) unObjeto;
            exhibirOrdenDeProduccion();
        }
    }
    
    private void exhibirOrdenDeProduccion() {
        jLEstaticoCriterio1.setEnabled(true);
        jLCriterio1.setEnabled(true);
        jLCriterio1.setText(""+this.unaOrdenDeProduccionSeleccionada.getId());
    }    
}
