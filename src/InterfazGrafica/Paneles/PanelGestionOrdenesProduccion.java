/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Paneles;

import InterfazGrafica.GestionOrdenesProduccion;
import InterfazGrafica.Inicio;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.UtilidadesInterfazGrafica;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import LogicaDeNegocio.OrdenDeProduccion;
import LogicaDeNegocio.Organizacion;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author usuario
 */
public class PanelGestionOrdenesProduccion extends Panel {

    /**
     * Creates new form PanelGestionEstacionamientos
     */
    public PanelGestionOrdenesProduccion() {
        initComponents();
    }
    
    
    private OrdenDeProduccion unObjetoSeleccionado;
    
    
    
    public static final String[] criterios = new String[] {"Descripción","Estado", "Feha de origen", "Fecha de entrega"};
    /*private final String criterio1 = "descripcion";
    private final String criterio2 = "estado";
    private final String criterio3 = "fechaOrigen";
    private final String criterio4 = "fechaEntrega";*/
    
    public PanelGestionOrdenesProduccion(JFrame ventanaContenedora, String trayectoriaActual, Organizacion unaOrganizacion, String tituloReporte) {
        super(ventanaContenedora, trayectoriaActual, unaOrganizacion, criterios, tituloReporte);
        initComponents();
        this.setVisible(true);
        
        setBackground(ParametrosDeInterfaz.colorFondo);
        establecerAlineacionDeTabla(jTable1, SwingConstants.CENTER);
        UtilidadesInterfazGrafica.configurarTabla(jTable1);
        this.organizacion = ((Inicio)this.ventanaContenedora).getOrganizacion();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton20 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBVerDetalle = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLObjetoSeleccionado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCBCriterio1 = new javax.swing.JCheckBox();
        jCBCriterio2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jCBEstado = new javax.swing.JComboBox<>();
        jTFDescripcion = new javax.swing.JTextField();
        jCBCriterio3 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        dato1Criterio3 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        dato2Criterio3 = new com.toedter.calendar.JDateChooser();
        jCBCriterio4 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        dato1Criterio4 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        dato2Criterio4 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        subPanelConsultaOrdenProduccion1 = new InterfazGrafica.Paneles.subPanelConsultaOrdenProduccion(ventanaContenedora);

        setBackground(new java.awt.Color(204, 204, 204));

        jButton20.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/icono-Opcion.png"))); // NOI18N
        jButton20.setText("Gestionar Ordenes de producción");
        jButton20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Consultas de ordenes de producción");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Para ver el detalle de una orden de producción, seleccione una en la tabla y presione el botón Ver detalle");

        jBVerDetalle.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBVerDetalle.setText("Ver detalle");
        jBVerDetalle.setEnabled(false);
        jBVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerDetalleActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("ID de orden de producción seleccionada:");

        jLObjetoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLObjetoSeleccionado.setForeground(new java.awt.Color(0, 0, 0));
        jLObjetoSeleccionado.setText("#");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Criterios de búsqueda");

        jCBCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio1.setText("Descripcion");
        jCBCriterio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio1ItemStateChanged(evt);
            }
        });

        jCBCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio2.setText("Estado");
        jCBCriterio2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio2ItemStateChanged(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("Realizar consulta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCBEstado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Regular", "Anulado" }));
        jCBEstado.setEnabled(false);

        jTFDescripcion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFDescripcion.setText("Ingrese una Descripcion");
        jTFDescripcion.setEnabled(false);

        jCBCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio3.setText("Fecha de origen");
        jCBCriterio3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio3ItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel7.setText("Desde");
        jLabel7.setEnabled(false);

        dato1Criterio3.setEnabled(false);
        dato1Criterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel8.setText("Hasta");
        jLabel8.setEnabled(false);

        dato2Criterio3.setEnabled(false);
        dato2Criterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jCBCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio4.setText("Fecha de entrega");
        jCBCriterio4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio4ItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel9.setText("Desde");
        jLabel9.setEnabled(false);

        dato1Criterio4.setEnabled(false);
        dato1Criterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel10.setText("Hasta");
        jLabel10.setEnabled(false);

        dato2Criterio4.setEnabled(false);
        dato2Criterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jButton2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton2.setText("Generar reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCBCriterio3)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel7)
                            .addGap(6, 6, 6)
                            .addComponent(dato1Criterio3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dato2Criterio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCBCriterio1)
                            .addGap(18, 18, 18)
                            .addComponent(jTFDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCBCriterio2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCBCriterio4)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9)
                            .addGap(6, 6, 6)
                            .addComponent(dato1Criterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dato2Criterio4, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBCriterio1)
                            .addComponent(jTFDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBCriterio2)
                            .addComponent(jCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(dato2Criterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(dato1Criterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jCBCriterio3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jCBCriterio4)
                            .addComponent(jLabel9)
                            .addComponent(dato1Criterio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(dato2Criterio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18))))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cantidad", "Unidad de medida", "Fecha de Origen", "Entrega", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setMinWidth(200);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(4).setMinWidth(200);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(5).setMinWidth(180);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(180);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLObjetoSeleccionado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBVerDetalle)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(subPanelConsultaOrdenProduccion1, javax.swing.GroupLayout.DEFAULT_SIZE, 1545, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton20)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBVerDetalle)
                    .addComponent(jLObjetoSeleccionado)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(subPanelConsultaOrdenProduccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        GestionOrdenesProduccion ventana = new GestionOrdenesProduccion(((Inicio) ventanaContenedora).getOrganizacion(), ventanaContenedora, ((Inicio) ventanaContenedora).getTrayectoriaActual());
        this.ventanaContenedora.dispose();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jCBCriterio1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio1ItemStateChanged
        jTFDescripcion.setEnabled(jCBCriterio1.isSelected());
        //criteriosSeleccionados.put("descripcion", jCBCriterio1.isSelected());
        asignarCriterio(0, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio1ItemStateChanged

    private void jCBCriterio2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio2ItemStateChanged
        jCBEstado.setEnabled(jCBCriterio2.isSelected());
        asignarCriterio(1, (JCheckBox) evt.getSource());
        //criteriosSeleccionados.put("estado", jCBCriterio2.isSelected());
    }//GEN-LAST:event_jCBCriterio2ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*try {
            ArrayList listaFiltrada = null;
            listaFiltrada = this.organizacion.filtrarOrdenesProduccion(this.criteriosSeleccionados, jTFDescripcion.getText(), (String)jCBEstado.getSelectedItem(), dato1Criterio3.getCalendar(), dato2Criterio3.getCalendar(), dato1Criterio4.getCalendar(), dato2Criterio4.getCalendar());

            ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
            Iterator ordenesProduccion = listaFiltrada.iterator();
            while (ordenesProduccion.hasNext()){
                OrdenDeProduccion unaOrdenP = (OrdenDeProduccion) ordenesProduccion.next();
                ((DefaultTableModel)this.jTable1.getModel()).addRow(unaOrdenP.devolverVector());
            }
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }*/
        cargarTabla(jTable1, organizacion.getOrdenesProduccion());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCBCriterio3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio3ItemStateChanged
        jLabel4.setEnabled(jCBCriterio3.isSelected());
        dato1Criterio3.setEnabled(jCBCriterio3.isSelected());
        jLabel5.setEnabled(jCBCriterio3.isSelected());
        dato2Criterio3.setEnabled(jCBCriterio3.isSelected());
        asignarCriterio(2, (JCheckBox) evt.getSource());
        //criteriosSeleccionados.put("fechaOrigen", jCBCriterio3.isSelected());
    }//GEN-LAST:event_jCBCriterio3ItemStateChanged

    private void jCBCriterio4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio4ItemStateChanged
        jLabel6.setEnabled(jCBCriterio4.isSelected());
        dato1Criterio4.setEnabled(jCBCriterio4.isSelected());
        jLabel7.setEnabled(jCBCriterio4.isSelected());
        dato2Criterio4.setEnabled(jCBCriterio4.isSelected());
        //criteriosSeleccionados.put("fechaEntrega", jCBCriterio4.isSelected());
        asignarCriterio(3, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio4ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        this.unObjetoSeleccionado = this.organizacion.getOrdenesProduccion().get(id);
        actualizarObjetoSeleccionado();
        habilitarBotones();

    }//GEN-LAST:event_jTable1MouseClicked

    private void jBVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerDetalleActionPerformed
        if (this.unObjetoSeleccionado == null)
            return;
        this.subPanelConsultaOrdenProduccion1.actualizarUnObjeto(unObjetoSeleccionado);
    }//GEN-LAST:event_jBVerDetalleActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /*try {
            ArrayList listaFiltrada = null;
            listaFiltrada = this.organizacion.filtrarOrdenesProduccion(this.criteriosSeleccionados, jTFDescripcion.getText(), (String)jCBEstado.getSelectedItem(), dato1Criterio3.getCalendar(), dato2Criterio3.getCalendar(), dato1Criterio4.getCalendar(), dato2Criterio4.getCalendar());
            Map<String, String> criterios = getCriterios();
            GeneradorDeReportes.generarReporteGenerico("Orden de producción",this.criteriosSeleccionados, criterios, listaFiltrada, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
            JOptionPane.showMessageDialog(null, "Reporte de ordenes de producción generado exitosamente");
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }*/
        generarReporte(organizacion.getOrdenesProduccion());
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dato1Criterio3;
    private com.toedter.calendar.JDateChooser dato1Criterio4;
    private com.toedter.calendar.JDateChooser dato2Criterio3;
    private com.toedter.calendar.JDateChooser dato2Criterio4;
    private javax.swing.JButton jBVerDetalle;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JCheckBox jCBCriterio4;
    private javax.swing.JComboBox<String> jCBEstado;
    private javax.swing.JLabel jLObjetoSeleccionado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFDescripcion;
    private javax.swing.JTable jTable1;
    private InterfazGrafica.Paneles.subPanelConsultaOrdenProduccion subPanelConsultaOrdenProduccion1;
    // End of variables declaration//GEN-END:variables

    private void habilitarBotones() {
        jBVerDetalle.setEnabled(true);
    }

    private void actualizarObjetoSeleccionado() {
        jLObjetoSeleccionado.setText(""+this.unObjetoSeleccionado.getId());
    }

    /*private Map<String, String> getCriterios() {
        Map<String, String> retorno = new HashMap<String, String>();
        Iterator recorredorDeClaves = this.criteriosSeleccionados.keySet().iterator();
        while (recorredorDeClaves.hasNext()){
            String unaClave = (String) recorredorDeClaves.next();
            boolean unCriterio = this.criteriosSeleccionados.get(unaClave);
            if (unCriterio){
                switch (unaClave){
                    case criterio1:
                        retorno.put(unaClave, ""+ jTFDescripcion.getText());
                        break;
                    case criterio2:
                        retorno.put(unaClave, (String) jCBEstado.getSelectedItem());
                        break;
                    case criterio3:
                        String fechaOrigenDesde = Organizacion.expresarCalendario(dato1Criterio3.getCalendar());
                        String fechaOrigenHasta = Organizacion.expresarCalendario(dato2Criterio3.getCalendar());
                        retorno.put(unaClave, "Desde "+fechaOrigenDesde+" hasta "+fechaOrigenHasta);   
                        break;
                    case criterio4:
                        fechaOrigenDesde = Organizacion.expresarCalendario(dato1Criterio4.getCalendar());
                        fechaOrigenHasta = Organizacion.expresarCalendario(dato2Criterio4.getCalendar());
                        retorno.put(unaClave, "Desde "+fechaOrigenDesde+" hasta "+fechaOrigenHasta);   
                        break;
                    default:
                    break;
                }
            }else{
                retorno.put(unaClave, "indistinto");
            }
        }
        
        
        return retorno;
    }*/

    @Override
    protected void generarObjetos() {
        asignarObjetoACriterio(0, jTFDescripcion.getText());
        asignarObjetoACriterio(1, (String) jCBEstado.getSelectedItem());
        asignarObjetoACriterio(2, generarListaFecha(dato1Criterio3.getCalendar(), dato2Criterio3.getCalendar()));
        asignarObjetoACriterio(3, generarListaFecha(dato1Criterio4.getCalendar(), dato2Criterio4.getCalendar()));
    }
}
