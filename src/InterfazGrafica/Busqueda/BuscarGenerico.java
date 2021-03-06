/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Busqueda;


import InterfazGrafica.ABMProveedor;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.TransferenciaInstancias;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Proveedor;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author usuario
 */
public class BuscarGenerico extends javax.swing.JFrame {

    private JFrame ventanaAnterior;
    private Organizacion organizacion;
    private Map<String, Boolean> criteriosSeleccionados;
    private Proveedor unObjetoSeleccionado;
    private String trayectoriaActual;
    
    
    private final String criterio1 = "identificadorCriterio1";
    private final String criterio2 = "identificadorCriterio2";
    private final String criterio3 = "identificadorCriterio3";
    private final String criterio4 = "identificadorCriterio4";
    private final String criterio5 = "identificadorCriterio5";
    private final String criterio6 = "identificadorCriterio6";
    
    /**
     * Creates new form BuscarProveedor
     */
    
    
    public BuscarGenerico() {
        initComponents();
    }

    public BuscarGenerico(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior) {
        
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true); 
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaAnterior.setFocusable(false);
        this.organizacion = organizacion;
        this.trayectoriaActual = trayectoriaAnterior + " - Busqueda de GENERICO";
        
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Busqueda de un CONCEPTO", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
        jTable1.setRowHeight(30);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(60);
        
        setIconImage(new ImageIcon(getClass().getResource("../"+ParametrosDeInterfaz.rutaIcono)).getImage());
        this.getContentPane().setBackground(ParametrosDeInterfaz.colorFondo);
        
        
        
        
        criteriosSeleccionados = new HashMap<String, Boolean>();
        criteriosSeleccionados.put(criterio1, false);
        criteriosSeleccionados.put(criterio2, false);
        criteriosSeleccionados.put(criterio3, false);
        criteriosSeleccionados.put(criterio4, false);
        criteriosSeleccionados.put(criterio5, false);
        criteriosSeleccionados.put(criterio6, false);
        if (ventanaAnterior instanceof ABMProveedor){   //EJEMPLO GENERICO, ESTO DEBERIA CAMBIARSE
            ABMProveedor abmProveedor = (ABMProveedor) ventanaAnterior;
            if (abmProveedor.getOperacionActual().equals("Baja")){
                //Si voy a dar de baja, solo puedo seleccionar proveedor activo.
                jCBCriterio3.doClick();
                jCBCriterio3.setEnabled(false);
                datoCriterio3.setSelectedItem("Activo");
                datoCriterio3.setEnabled(false);
            }
            
        }
        ParametrosDeInterfaz.configurarVentana(this);
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

        jBConcretarAccion = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jLStaticObjetoSeleccionado = new javax.swing.JLabel();
        jLObjetoSeleccionado = new javax.swing.JLabel();
        cabeceraDeVentana = new InterfazGrafica.CabeceraDeVentana();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCBCriterio1 = new javax.swing.JCheckBox();
        jCBCriterio2 = new javax.swing.JCheckBox();
        jCBCriterio3 = new javax.swing.JCheckBox();
        datoCriterio1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        datoCriterio3 = new javax.swing.JComboBox<>();
        datoCriterio2 = new javax.swing.JTextField();
        jCBCriterio6 = new javax.swing.JCheckBox();
        jCBCriterio5 = new javax.swing.JCheckBox();
        jCBCriterio4 = new javax.swing.JCheckBox();
        datoCriterio4 = new javax.swing.JComboBox<>();
        jLEstatico1Criterio5 = new javax.swing.JLabel();
        dato1Criterio5 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio5 = new javax.swing.JLabel();
        dato2Criterio5 = new com.toedter.calendar.JDateChooser();
        jLEstatico1Criterio6 = new javax.swing.JLabel();
        dato1Criterio6 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio6 = new javax.swing.JLabel();
        dato2Criterio6 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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

        jLStaticObjetoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticObjetoSeleccionado.setText("Objeto seleccionado:");

        jLObjetoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Criterios de búsqueda");

        jCBCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio1.setText("Criterio1");
        jCBCriterio1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio1ItemStateChanged(evt);
            }
        });

        jCBCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio2.setText("Criterio2");
        jCBCriterio2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio2ItemStateChanged(evt);
            }
        });

        jCBCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio3.setText("Criterio3");
        jCBCriterio3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio3ItemStateChanged(evt);
            }
        });

        datoCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio1.setText("Ingrese un criterio");
        datoCriterio1.setEnabled(false);

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        datoCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Baja" }));
        datoCriterio3.setEnabled(false);

        datoCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio2.setText("Ingrese un criterio");
        datoCriterio2.setEnabled(false);

        jCBCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio6.setText("Criterio6");
        jCBCriterio6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio6ItemStateChanged(evt);
            }
        });

        jCBCriterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio5.setText("Criterio5");
        jCBCriterio5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio5ItemStateChanged(evt);
            }
        });

        jCBCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio4.setText("Criterio4");
        jCBCriterio4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio4ItemStateChanged(evt);
            }
        });

        datoCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Baja" }));
        datoCriterio4.setEnabled(false);

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

        jLEstatico1Criterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstatico1Criterio6.setText("Desde");
        jLEstatico1Criterio6.setEnabled(false);

        dato1Criterio6.setEnabled(false);
        dato1Criterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstatico2Criterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstatico2Criterio6.setText("Hasta");
        jLEstatico2Criterio6.setEnabled(false);

        dato2Criterio6.setEnabled(false);
        dato2Criterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBCriterio6)
                        .addGap(18, 18, 18)
                        .addComponent(jLEstatico1Criterio6)
                        .addGap(6, 6, 6)
                        .addComponent(dato1Criterio6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLEstatico2Criterio6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dato2Criterio6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio1)
                                .addGap(18, 18, 18)
                                .addComponent(datoCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio2)
                                .addGap(18, 18, 18)
                                .addComponent(datoCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio3)
                                .addGap(18, 18, 18)
                                .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio5)
                                .addGap(18, 18, 18)
                                .addComponent(jLEstatico1Criterio5)
                                .addGap(6, 6, 6)
                                .addComponent(dato1Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLEstatico2Criterio5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dato2Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio4)
                                .addGap(18, 18, 18)
                                .addComponent(datoCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 222, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio1)
                    .addComponent(datoCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio2)
                    .addComponent(datoCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio3)
                    .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCBCriterio4)
                                    .addComponent(datoCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jCBCriterio5))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dato2Criterio5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLEstatico2Criterio5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dato1Criterio5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLEstatico1Criterio5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCBCriterio6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dato2Criterio6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLEstatico2Criterio6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dato1Criterio6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLEstatico1Criterio6)))
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
                "ID", "Campo1", "Campo2", "Campo3", "Campo4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(25);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1683, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLStaticObjetoSeleccionado)
                        .addGap(18, 18, 18)
                        .addComponent(jLObjetoSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBConcretarAccion)
                        .addGap(18, 18, 18)
                        .addComponent(jBCancelar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1707, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBConcretarAccion)
                        .addComponent(jBCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLStaticObjetoSeleccionado)
                        .addComponent(jLObjetoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        datoCriterio1.setEnabled(jCBCriterio1.isSelected());
        criteriosSeleccionados.put(criterio1, jCBCriterio1.isSelected());
        
    }//GEN-LAST:event_jCBCriterio1ItemStateChanged

    private void jCBCriterio2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio2ItemStateChanged
        datoCriterio2.setEnabled(jCBCriterio2.isSelected());
        criteriosSeleccionados.put(criterio2, jCBCriterio2.isSelected());
    }//GEN-LAST:event_jCBCriterio2ItemStateChanged

    private void jCBCriterio3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio3ItemStateChanged
        datoCriterio3.setEnabled(jCBCriterio3.isSelected());
        criteriosSeleccionados.put(criterio3, jCBCriterio3.isSelected());
    }//GEN-LAST:event_jCBCriterio3ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList listaFiltrada = null;
        listaFiltrada = this.organizacion.filtrarProveedores(this.criteriosSeleccionados, datoCriterio1.getText(), datoCriterio2.getText(), (String)datoCriterio3.getSelectedItem());
      
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
        Iterator proveedores = listaFiltrada.iterator();
        while (proveedores.hasNext()){
            Proveedor unProveedor = (Proveedor) proveedores.next();
            ((DefaultTableModel)this.jTable1.getModel()).addRow(unProveedor.devolverVector());    
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        this.unObjetoSeleccionado = this.organizacion.getProveedores().get(id);
        actualizarObjetoSeleccionado();
        habilitarBotones();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jCBCriterio6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio6ItemStateChanged
        jLEstatico1Criterio6.setEnabled(jCBCriterio6.isSelected());
        dato1Criterio6.setEnabled(jCBCriterio6.isSelected());
        jLEstatico2Criterio6.setEnabled(jCBCriterio6.isSelected());
        dato2Criterio6.setEnabled(jCBCriterio6.isSelected());
        criteriosSeleccionados.put(criterio6, jCBCriterio6.isSelected());
    }//GEN-LAST:event_jCBCriterio6ItemStateChanged

    private void jCBCriterio5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio5ItemStateChanged
        jLEstatico1Criterio5.setEnabled(jCBCriterio5.isSelected());
        dato1Criterio5.setEnabled(jCBCriterio5.isSelected());
        jLEstatico2Criterio5.setEnabled(jCBCriterio5.isSelected());
        dato2Criterio5.setEnabled(jCBCriterio5.isSelected());
        criteriosSeleccionados.put(criterio5, jCBCriterio5.isSelected());
    }//GEN-LAST:event_jCBCriterio5ItemStateChanged

    private void jCBCriterio4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio4ItemStateChanged
        datoCriterio4.setEnabled(jCBCriterio4.isSelected());
        criteriosSeleccionados.put(criterio4, jCBCriterio4.isSelected());
    }//GEN-LAST:event_jCBCriterio4ItemStateChanged

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
            java.util.logging.Logger.getLogger(BuscarGenerico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarGenerico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarGenerico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarGenerico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarGenerico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private com.toedter.calendar.JDateChooser dato1Criterio5;
    private com.toedter.calendar.JDateChooser dato1Criterio6;
    private com.toedter.calendar.JDateChooser dato2Criterio5;
    private com.toedter.calendar.JDateChooser dato2Criterio6;
    private javax.swing.JTextField datoCriterio1;
    private javax.swing.JTextField datoCriterio2;
    private javax.swing.JComboBox<String> datoCriterio3;
    private javax.swing.JComboBox<String> datoCriterio4;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JCheckBox jCBCriterio4;
    private javax.swing.JCheckBox jCBCriterio5;
    private javax.swing.JCheckBox jCBCriterio6;
    private javax.swing.JLabel jLEstatico1Criterio5;
    private javax.swing.JLabel jLEstatico1Criterio6;
    private javax.swing.JLabel jLEstatico2Criterio5;
    private javax.swing.JLabel jLEstatico2Criterio6;
    private javax.swing.JLabel jLObjetoSeleccionado;
    private javax.swing.JLabel jLStaticObjetoSeleccionado;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        datoCriterio1.setText("");
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
        jBConcretarAccion.setEnabled(false);
        jBCancelar.setEnabled(false);
        jLObjetoSeleccionado.setText("");
        this.unObjetoSeleccionado = null;
    }

    private void actualizarObjetoSeleccionado() {
        jLObjetoSeleccionado.setText(this.unObjetoSeleccionado.getRazonSocial());
        
    }
    private void habilitarBotones(){
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
    }

}
