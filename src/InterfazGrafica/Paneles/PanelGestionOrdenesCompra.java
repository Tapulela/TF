/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Paneles;

import InterfazGrafica.Busqueda.BuscarOrdenDeProduccion;
import InterfazGrafica.Busqueda.BuscarProveedor;
import InterfazGrafica.GestionOrdenesCompra;
import InterfazGrafica.Inicio;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.TransferenciaInstancias;
import InterfazGrafica.UtilidadesInterfazGrafica;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDerechaDeTabla;
import LogicaDeNegocio.OrdenDeProduccion;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Proveedor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author usuario
 */
public class PanelGestionOrdenesCompra extends Panel implements TransferenciaInstancias{

    /**
     * Creates new form PanelGestionEstacionamientos
     */
    public PanelGestionOrdenesCompra() {
        initComponents();
    }
    
    public static final String[] criterios = new String[] {"Orden de produccion asociada", "Proveedor asociado", "Estado", "Fecha de origen"};
    
    //private Map<String, Boolean> criteriosSeleccionados;
    
    private String claveABuscar;
    
    public PanelGestionOrdenesCompra(JFrame ventanaContenedora, String trayectoriaActual, Organizacion unaOrganizacion, String tituloReporte) {
        super(ventanaContenedora, trayectoriaActual, unaOrganizacion, criterios, tituloReporte);
        initComponents();
        this.setVisible(true);
        establecerAlineacionDeTabla(jTable1, SwingConstants.CENTER);
        ParametrosDeInterfaz.confeccionarComponentes(this.getComponents());

        UtilidadesInterfazGrafica.configurarTabla(jTable1);
        
        establecerAlineacionDerechaDeTabla(jTable1, 2);
        establecerAlineacionDerechaDeTabla(jTable1, 4);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCBCriterio1 = new javax.swing.JCheckBox();
        jCBCriterio3 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        datoCriterio3 = new javax.swing.JComboBox<>();
        jCBCriterio4 = new javax.swing.JCheckBox();
        jCBCriterio2 = new javax.swing.JCheckBox();
        jLEstatico1Criterio4 = new javax.swing.JLabel();
        dato1Criterio4 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio4 = new javax.swing.JLabel();
        dato2Criterio4 = new com.toedter.calendar.JDateChooser();
        botonCriterio2 = new javax.swing.JButton();
        botonCriterio1 = new javax.swing.JButton();
        jLEstaticoCriterio1 = new javax.swing.JLabel();
        jLCriterio1 = new javax.swing.JLabel();
        jLEstaticoCriterio2 = new javax.swing.JLabel();
        jLCriterio2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jButton5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/icono-Opcion.png"))); // NOI18N
        jButton5.setText("Gestionar Ordenes de Compra");
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Cantidad", "Unidad de medida", "Precio unitario", "Estado", "ID Orden Produccion", "Proveedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(26);
        jTable1.setSelectionBackground(new java.awt.Color(0, 153, 51));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Criterios de consulta");

        jCBCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio1.setText("Orden de Produccion asociada");
        jCBCriterio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio1ItemStateChanged(evt);
            }
        });

        jCBCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio3.setText("Estado");
        jCBCriterio3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio3ItemStateChanged(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton2.setText("Realizar consulta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        datoCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Regular", "Anulado" }));
        datoCriterio3.setEnabled(false);

        jCBCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio4.setText("Fecha de Origen");
        jCBCriterio4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio4ItemStateChanged(evt);
            }
        });

        jCBCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio2.setText("Proveedor asociado");
        jCBCriterio2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio2ItemStateChanged(evt);
            }
        });

        jLEstatico1Criterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstatico1Criterio4.setText("Desde");
        jLEstatico1Criterio4.setEnabled(false);

        dato1Criterio4.setEnabled(false);
        dato1Criterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstatico2Criterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstatico2Criterio4.setText("Hasta");
        jLEstatico2Criterio4.setEnabled(false);

        dato2Criterio4.setEnabled(false);
        dato2Criterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        botonCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        botonCriterio2.setText("Buscar un Proveedor");
        botonCriterio2.setEnabled(false);
        botonCriterio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio2ActionPerformed(evt);
            }
        });

        botonCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        botonCriterio1.setText("Buscar una Orden de Produccion");
        botonCriterio1.setEnabled(false);
        botonCriterio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio1ActionPerformed(evt);
            }
        });

        jLEstaticoCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio1.setText("ID orden seleccionada");

        jLCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstaticoCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio2.setText("Proveedor seleccionado:");

        jLCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jButton3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton3.setText("Generar Reporte");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBCriterio1)
                        .addGap(18, 18, 18)
                        .addComponent(botonCriterio1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLEstaticoCriterio1)
                        .addGap(18, 18, 18)
                        .addComponent(jLCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBCriterio2)
                        .addGap(18, 18, 18)
                        .addComponent(botonCriterio2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLEstaticoCriterio2)
                        .addGap(18, 18, 18)
                        .addComponent(jLCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBCriterio4)
                        .addGap(18, 18, 18)
                        .addComponent(jLEstatico1Criterio4)
                        .addGap(6, 6, 6)
                        .addComponent(dato1Criterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLEstatico2Criterio4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dato2Criterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBCriterio3)
                        .addGap(18, 18, 18)
                        .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio1)
                    .addComponent(botonCriterio1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLEstaticoCriterio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLCriterio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio2)
                    .addComponent(botonCriterio2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEstaticoCriterio2)
                    .addComponent(jLCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio3)
                    .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCBCriterio4)
                    .addComponent(jLEstatico1Criterio4)
                    .addComponent(jLEstatico2Criterio4)
                    .addComponent(dato1Criterio4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dato2Criterio4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18))
        );

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Consultas de Ordenes de compra");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton5))
                        .addGap(0, 338, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        GestionOrdenesCompra ventana = new GestionOrdenesCompra(organizacion, ventanaContenedora, ((Inicio) ventanaContenedora).getTrayectoriaActual());
        this.ventanaContenedora.dispose();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jCBCriterio1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio1ItemStateChanged
        botonCriterio1.setEnabled(true);
        jLEstaticoCriterio1.setEnabled(jCBCriterio1.isSelected());
        jLCriterio1.setEnabled(jCBCriterio1.isSelected());
        asignarCriterio(0, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio1ItemStateChanged

    private void jCBCriterio3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio3ItemStateChanged
        datoCriterio3.setEnabled(jCBCriterio3.isSelected());
        asignarCriterio(2, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio3ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarTabla(jTable1, organizacion.getOrdenesCompra());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCBCriterio4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio4ItemStateChanged
        jLEstatico1Criterio4.setEnabled(jCBCriterio4.isSelected());
        dato1Criterio4.setEnabled(jCBCriterio4.isSelected());
        jLEstatico2Criterio4.setEnabled(jCBCriterio4.isSelected());
        dato2Criterio4.setEnabled(jCBCriterio4.isSelected());
        //criteriosSeleccionados.put(criterio4, jCBCriterio4.isSelected());
        asignarCriterio(3, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio4ItemStateChanged

    private void jCBCriterio2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio2ItemStateChanged
        botonCriterio2.setEnabled(jCBCriterio2.isSelected());
        jLEstaticoCriterio2.setEnabled(jCBCriterio2.isSelected());
        jLCriterio2.setEnabled(jCBCriterio2.isSelected());
        //criteriosSeleccionados.put(criterio2, jCBCriterio2.isSelected());
        asignarCriterio(1, (JCheckBox) evt.getSource());

    }//GEN-LAST:event_jCBCriterio2ItemStateChanged

    private void botonCriterio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio2ActionPerformed
        BuscarProveedor unaVentana = new BuscarProveedor(this.organizacion, ventanaContenedora, this.trayectoriaActual);
        ventanaContenedora.dispose();
    }//GEN-LAST:event_botonCriterio2ActionPerformed

    private void botonCriterio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio1ActionPerformed
        BuscarOrdenDeProduccion unaVentana = new BuscarOrdenDeProduccion(this.organizacion, ventanaContenedora, this.trayectoriaActual);
        ventanaContenedora.dispose();
    }//GEN-LAST:event_botonCriterio1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        generarReporte(organizacion.getOrdenesCompra());
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCriterio1;
    private javax.swing.JButton botonCriterio2;
    private com.toedter.calendar.JDateChooser dato1Criterio4;
    private com.toedter.calendar.JDateChooser dato2Criterio4;
    private javax.swing.JComboBox<String> datoCriterio3;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JCheckBox jCBCriterio4;
    private javax.swing.JLabel jLCriterio1;
    private javax.swing.JLabel jLCriterio2;
    private javax.swing.JLabel jLEstatico1Criterio4;
    private javax.swing.JLabel jLEstatico2Criterio4;
    private javax.swing.JLabel jLEstaticoCriterio1;
    private javax.swing.JLabel jLEstaticoCriterio2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void actualizarObjetoSeleccionado(Object unObjeto) {
        
        if (unObjeto instanceof OrdenDeProduccion){
            asignarObjetoACriterio(0, unObjeto);
            OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
            jLEstaticoCriterio1.setEnabled(true);
            jLCriterio1.setEnabled(true);
            jLCriterio1.setText(""+unaOrdenProduccion.getId());
        }
        if (unObjeto instanceof Proveedor){
            asignarObjetoACriterio(1, unObjeto);
            Proveedor unProveedor = (Proveedor) unObjeto;
            jLEstaticoCriterio2.setEnabled(true);
            jLCriterio2.setEnabled(true);
            jLCriterio2.setText(""+unProveedor.getCuit());
        }
        
    }

    @Override
    public void actualizarUnObjeto(Object unObjeto) {
        if (unObjeto instanceof OrdenDeProduccion){
            asignarObjetoACriterio(0, unObjeto);
            OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
            jLEstaticoCriterio1.setEnabled(true);
            jLCriterio1.setEnabled(true);
            jLCriterio1.setText(""+unaOrdenProduccion.getId());
        }
        if (unObjeto instanceof Proveedor){
            asignarObjetoACriterio(1, unObjeto);
            Proveedor unProveedor = (Proveedor) unObjeto;
            jLEstaticoCriterio2.setEnabled(true);
            jLCriterio2.setEnabled(true);
            jLCriterio2.setText(""+unProveedor.getCuit());
        }
    }

    @Override
    protected void generarObjetos() {
        asignarObjetoACriterio(2, datoCriterio3.getSelectedItem());
        asignarObjetoACriterio(3, generarListaFecha(dato1Criterio4.getCalendar(), dato2Criterio4.getCalendar()));
    }

    
}
