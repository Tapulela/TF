/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Busqueda;


import InterfazGrafica.GestionAnalisisLaboratorio;
import InterfazGrafica.GestionIngresoMP;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.TransferenciaInstancias;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.OrdenDeCompra;
import LogicaDeNegocio.OrdenDeProduccion;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Proveedor;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class BuscarOrdenCompra extends javax.swing.JFrame implements TransferenciaInstancias {

    private JFrame ventanaAnterior;
    private Organizacion organizacion;
    private Map<String, Boolean> criteriosSeleccionados;
    private OrdenDeCompra unObjetoSeleccionado;
    private String trayectoriaActual;
    
    private Proveedor unProveedorSeleccionado;
    private OrdenDeProduccion unaOrdenProduccionSeleccionada;
    
    
    
    private final String criterio1 = "ordenProduccion";
    private final String criterio2 = "proveedor";
    private final String criterio3 = "estado";
    private final String criterio4 = "fechaOrigen";
    
    /**
     * Creates new form BuscarProveedor
     */
    
    
    public BuscarOrdenCompra() {
        initComponents();
    }

    public BuscarOrdenCompra(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior)  {
        
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true); 
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaAnterior.setFocusable(false);
        this.organizacion = organizacion;
        this.trayectoriaActual = trayectoriaAnterior + " - Busqueda de Ordenes de Compra";
        
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Busqueda de una Orden de Compra", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
        jTable1.setRowHeight(30);
        setIconImage(new ImageIcon(getClass().getResource("../"+ParametrosDeInterfaz.rutaIcono)).getImage());
        this.getContentPane().setBackground(ParametrosDeInterfaz.colorFondo);
        
        
        
        
        criteriosSeleccionados = new HashMap<String, Boolean>();
        criteriosSeleccionados.put(criterio1, false);
        criteriosSeleccionados.put(criterio3, false);
        criteriosSeleccionados.put(criterio2, false);
        criteriosSeleccionados.put(criterio4, false);

        if (ventanaAnterior instanceof GestionIngresoMP || ventanaAnterior instanceof GestionAnalisisLaboratorio){   
            //Si voy a dar registrar un ingreso, solo puedo elegir ordenes de compra activas.
                jCBCriterio3.doClick();
                jCBCriterio3.setEnabled(false);
                datoCriterio3.setSelectedItem("Regular");
                datoCriterio3.setEnabled(false);
        }        
        establecerAlineacionDeTabla(jTable1, SwingConstants.CENTER);
        ParametrosDeInterfaz.configurarVentana(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBConcretarAccion = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCBCriterio1 = new javax.swing.JCheckBox();
        jCBCriterio3 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        datoCriterio3 = new javax.swing.JComboBox<>();
        jCBCriterio4 = new javax.swing.JCheckBox();
        jCBCriterio2 = new javax.swing.JCheckBox();
        jLEstatico1Criterio5 = new javax.swing.JLabel();
        dato1Criterio5 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio5 = new javax.swing.JLabel();
        dato2Criterio5 = new com.toedter.calendar.JDateChooser();
        botonCriterio4 = new javax.swing.JButton();
        botonCriterio1 = new javax.swing.JButton();
        jLEstaticoCriterio1 = new javax.swing.JLabel();
        jLCriterio1 = new javax.swing.JLabel();
        jLEstaticoCriterio4 = new javax.swing.JLabel();
        jLCriterio4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLStaticObjetoSeleccionado = new javax.swing.JLabel();
        jLObjetoSeleccionado = new javax.swing.JLabel();
        cabeceraDeVentana = new InterfazGrafica.CabeceraDeVentana();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(153, 255, 153));

        jBConcretarAccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBConcretarAccion.setText("Seleccionar");
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

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Criterios de búsqueda");

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

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        jLEstatico1Criterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstatico1Criterio5.setText("Desde");
        jLEstatico1Criterio5.setEnabled(false);

        dato1Criterio5.setEnabled(false);
        dato1Criterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstatico2Criterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstatico2Criterio5.setText("Hasta");
        jLEstatico2Criterio5.setEnabled(false);

        dato2Criterio5.setEnabled(false);
        dato2Criterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        botonCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        botonCriterio4.setText("Buscar un Proveedor");
        botonCriterio4.setEnabled(false);
        botonCriterio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio4ActionPerformed(evt);
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

        jLEstaticoCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio4.setText("Proveedor seleccionado:");

        jLCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

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
                        .addComponent(botonCriterio4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLEstaticoCriterio4)
                        .addGap(18, 18, 18)
                        .addComponent(jLCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBCriterio4)
                        .addGap(18, 18, 18)
                        .addComponent(jLEstatico1Criterio5)
                        .addGap(6, 6, 6)
                        .addComponent(dato1Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLEstatico2Criterio5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dato2Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBCriterio3)
                        .addGap(18, 18, 18)
                        .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(15, Short.MAX_VALUE))
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
                    .addComponent(botonCriterio4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEstaticoCriterio4)
                    .addComponent(jLCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio3)
                    .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCBCriterio4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dato2Criterio5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLEstatico2Criterio5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dato1Criterio5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLEstatico1Criterio5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTable1.setAutoCreateRowSorter(true);
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
        }

        jLStaticObjetoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticObjetoSeleccionado.setText("Objeto seleccionado:");

        jLObjetoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLStaticObjetoSeleccionado)
                        .addGap(18, 18, 18)
                        .addComponent(jLObjetoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBConcretarAccion)
                        .addGap(18, 18, 18)
                        .addComponent(jBCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLObjetoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBConcretarAccion)
                        .addComponent(jBCancelar)
                        .addComponent(jLStaticObjetoSeleccionado)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBConcretarAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConcretarAccionActionPerformed
        TransferenciaInstancias ventanaAnterior = (TransferenciaInstancias) this.ventanaAnterior;
        ventanaAnterior.actualizarUnObjeto(unObjetoSeleccionado);
        
        this.ventanaAnterior.setVisible(true);
        this.ventanaAnterior.setFocusable(true);
        this.dispose();
    }//GEN-LAST:event_jBConcretarAccionActionPerformed
    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed

        this.limpiarCampos();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jCBCriterio1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio1ItemStateChanged
        botonCriterio1.setEnabled(true);
        jLEstaticoCriterio1.setEnabled(jCBCriterio1.isSelected());
        jLCriterio1.setEnabled(jCBCriterio1.isSelected());
        criteriosSeleccionados.put(criterio1, jCBCriterio1.isSelected());
    }//GEN-LAST:event_jCBCriterio1ItemStateChanged

    private void jCBCriterio3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio3ItemStateChanged
        datoCriterio3.setEnabled(jCBCriterio3.isSelected());
        criteriosSeleccionados.put(criterio3, jCBCriterio3.isSelected());
    }//GEN-LAST:event_jCBCriterio3ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            ArrayList listaFiltrada = null;
            if (this.ventanaAnterior instanceof GestionIngresoMP){
                listaFiltrada = this.organizacion.filtrarOrdenesDeCompraConProveedorAsociado(this.criteriosSeleccionados, unaOrdenProduccionSeleccionada, unProveedorSeleccionado, (String)datoCriterio3.getSelectedItem(), dato1Criterio5.getCalendar(), dato2Criterio5.getCalendar());
            }else{
                listaFiltrada = this.organizacion.filtrarOrdenesDeCompra(this.criteriosSeleccionados, unaOrdenProduccionSeleccionada, unProveedorSeleccionado, (String)datoCriterio3.getSelectedItem(), dato1Criterio5.getCalendar(), dato2Criterio5.getCalendar());

            }
            
            ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
            Iterator proveedores = listaFiltrada.iterator();
            while (proveedores.hasNext()){
                OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) proveedores.next();    
                ((DefaultTableModel)this.jTable1.getModel()).addRow(unaOrdenDeCompra.devolverVector());
            }
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        
        this.unObjetoSeleccionado = this.organizacion.getOrdenesCompra().get(id);
        jLObjetoSeleccionado.setText(""+this.unObjetoSeleccionado.getId());
        habilitarBotones();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jCBCriterio4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio4ItemStateChanged
        jLEstatico1Criterio5.setEnabled(jCBCriterio4.isSelected());
        dato1Criterio5.setEnabled(jCBCriterio4.isSelected());
        jLEstatico2Criterio5.setEnabled(jCBCriterio4.isSelected());
        dato2Criterio5.setEnabled(jCBCriterio4.isSelected());
        criteriosSeleccionados.put(criterio4, jCBCriterio4.isSelected());
    }//GEN-LAST:event_jCBCriterio4ItemStateChanged

    private void jCBCriterio2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio2ItemStateChanged
        botonCriterio4.setEnabled(jCBCriterio2.isSelected());
        jLEstaticoCriterio4.setEnabled(jCBCriterio2.isSelected());
        jLCriterio4.setEnabled(jCBCriterio2.isSelected());
        criteriosSeleccionados.put(criterio2, jCBCriterio2.isSelected());
        
    }//GEN-LAST:event_jCBCriterio2ItemStateChanged

    private void botonCriterio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio4ActionPerformed
        BuscarProveedor unaVentana = new BuscarProveedor(this.organizacion, this, this.trayectoriaActual);
        
        this.dispose();
    }//GEN-LAST:event_botonCriterio4ActionPerformed

    private void botonCriterio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio1ActionPerformed
        
        BuscarOrdenDeProduccion unaVentana = new BuscarOrdenDeProduccion(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio1ActionPerformed

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
            java.util.logging.Logger.getLogger(BuscarOrdenCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarOrdenCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarOrdenCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarOrdenCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarOrdenCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCriterio1;
    private javax.swing.JButton botonCriterio4;
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private com.toedter.calendar.JDateChooser dato1Criterio5;
    private com.toedter.calendar.JDateChooser dato2Criterio5;
    private javax.swing.JComboBox<String> datoCriterio3;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JCheckBox jCBCriterio4;
    private javax.swing.JLabel jLCriterio1;
    private javax.swing.JLabel jLCriterio4;
    private javax.swing.JLabel jLEstatico1Criterio5;
    private javax.swing.JLabel jLEstatico2Criterio5;
    private javax.swing.JLabel jLEstaticoCriterio1;
    private javax.swing.JLabel jLEstaticoCriterio4;
    private javax.swing.JLabel jLObjetoSeleccionado;
    private javax.swing.JLabel jLStaticObjetoSeleccionado;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        botonCriterio1.setText("");
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
        jBConcretarAccion.setEnabled(false);
        jBCancelar.setEnabled(false);
        jLObjetoSeleccionado.setText("");
        this.unObjetoSeleccionado = null;
    }

    private void actualizarObjetoSeleccionado(Object unObjeto) {
        
        if (unObjeto instanceof OrdenDeProduccion){
            OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
            jLEstaticoCriterio1.setEnabled(true);
            jLCriterio1.setEnabled(true);
            jLCriterio1.setText(""+unaOrdenProduccion.getId());
        }
        if (unObjeto instanceof Proveedor){
            Proveedor unProveedor = (Proveedor) unObjeto;
            jLEstaticoCriterio4.setEnabled(true);
            jLCriterio4.setEnabled(true);
            jLCriterio4.setText(""+unProveedor.getCuit());
        }        
        
    }
    private void habilitarBotones(){
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
    }

    @Override
    public void actualizarUnObjeto(Object UnObjeto) {
        if (UnObjeto instanceof OrdenDeProduccion){
            this.unaOrdenProduccionSeleccionada = (OrdenDeProduccion) UnObjeto;
            this.actualizarObjetoSeleccionado(UnObjeto);
        }
        if (UnObjeto instanceof Proveedor){
            this.unProveedorSeleccionado = (Proveedor) UnObjeto;
            this.actualizarObjetoSeleccionado(UnObjeto);
        }
    }
}
