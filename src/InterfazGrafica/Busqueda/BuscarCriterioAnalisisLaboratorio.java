/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Busqueda;


import InterfazGrafica.ABMProveedor;
import InterfazGrafica.GestionAnalisisLaboratorio;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.TransferenciaInstancias;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import LogicaDeNegocio.AnalisisLaboratorio;
import LogicaDeNegocio.CriterioAnalisisLaboratorio;
import LogicaDeNegocio.ExcepcionCargaParametros;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author usuario
 */
public class BuscarCriterioAnalisisLaboratorio extends javax.swing.JFrame implements TransferenciaInstancias{

    private JFrame ventanaAnterior;
    private Organizacion organizacion;
    private Map<String, Boolean> criteriosSeleccionados;
    private CriterioAnalisisLaboratorio unObjetoSeleccionado;
    private String trayectoriaActual;
    
    private OrdenDeProduccion unaOrdenSeleccionada;
    private AnalisisLaboratorio unAnalisisSeleccionado;
    
    private final String criterio1 = "nombre";
    private final String criterio2 = "descripcion";
    private final String criterio3 = "estado";
    private final String criterio4 = "ordenProduccion";
    private final String criterio5 = "fechaOrigen";
    private final String criterio6 = "analisisLaboratorio";
    
    /**
     * Creates new form BuscarProveedor
     */
    
    
    public BuscarCriterioAnalisisLaboratorio() {
        initComponents();
    }

    public BuscarCriterioAnalisisLaboratorio(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior) {
        
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true); 
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaAnterior.setFocusable(false);
        this.organizacion = organizacion;
        this.trayectoriaActual = trayectoriaAnterior + " - Busqueda de un criterio de analisis de laboratorio";
        
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Busqueda de un criterio de analisis de laboratorio", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
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
        if (ventanaAnterior instanceof GestionAnalisisLaboratorio){   //EJEMPLO GENERICO, ESTO DEBERIA CAMBIARSE
            GestionAnalisisLaboratorio gestionAnalisisLab = (GestionAnalisisLaboratorio) ventanaAnterior;
                jCBCriterio3.doClick();
                jCBCriterio3.setEnabled(false);
                datoCriterio3.setSelectedItem(CriterioAnalisisLaboratorio.ESTADO_ALTA);
                datoCriterio3.setEnabled(false);
                
                jCBCriterio4.doClick();
                jCBCriterio4.setEnabled(false);
                botonCriterio4.setEnabled(false);
                if (gestionAnalisisLab.getUnaOrdenDeCompra() != null)
                    this.unaOrdenSeleccionada = gestionAnalisisLab.getUnaOrdenDeCompra().getOrdenDeProduccionAsociada();
                if (gestionAnalisisLab.getUnLote()!= null)
                    this.unaOrdenSeleccionada = gestionAnalisisLab.getUnLote().getOrdenDeProduccionAsociada();
                exhibirOrdenDeProduccion();
            
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
        jCBCriterio5 = new javax.swing.JCheckBox();
        jCBCriterio4 = new javax.swing.JCheckBox();
        jLEstatico1Criterio5 = new javax.swing.JLabel();
        dato1Criterio5 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio5 = new javax.swing.JLabel();
        dato2Criterio5 = new com.toedter.calendar.JDateChooser();
        botonCriterio4 = new javax.swing.JButton();
        jLEstaticoCriterio4 = new javax.swing.JLabel();
        jLCriterio4 = new javax.swing.JLabel();
        jCBCriterio6 = new javax.swing.JCheckBox();
        jLEstaticoCriterio6 = new javax.swing.JLabel();
        jLCriterio6 = new javax.swing.JLabel();
        botonCriterio6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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

        jLStaticObjetoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticObjetoSeleccionado.setText("Objeto seleccionado:");

        jLObjetoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Criterios de búsqueda");

        jCBCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio1.setText("Nombre");
        jCBCriterio1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio1ItemStateChanged(evt);
            }
        });

        jCBCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio2.setText("Descripcion");
        jCBCriterio2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio2ItemStateChanged(evt);
            }
        });

        jCBCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio3.setText("Estado");
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

        jCBCriterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio5.setText("Fecha de Origen");
        jCBCriterio5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio5ItemStateChanged(evt);
            }
        });

        jCBCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio4.setText("Orden de produccion asociada");
        jCBCriterio4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio4ItemStateChanged(evt);
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
        botonCriterio4.setText("Buscar una Orden de Produccion");
        botonCriterio4.setEnabled(false);
        botonCriterio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio4ActionPerformed(evt);
            }
        });

        jLEstaticoCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio4.setText("ID orden seleccionada");

        jLCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jCBCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio6.setText("Analisis de Laboratorio asociado");
        jCBCriterio6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio6ItemStateChanged(evt);
            }
        });

        jLEstaticoCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio6.setText("ID analisis seleccionado");

        jLCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        botonCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        botonCriterio6.setText("Buscar un analisis de laboratorio");
        botonCriterio6.setEnabled(false);
        botonCriterio6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio5)
                                .addGap(18, 18, 18)
                                .addComponent(jLEstatico1Criterio5)
                                .addGap(6, 6, 6)
                                .addComponent(dato1Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLEstatico2Criterio5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dato2Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio3)
                                .addGap(18, 18, 18)
                                .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio4)
                                .addGap(18, 18, 18)
                                .addComponent(botonCriterio4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLEstaticoCriterio4)
                                .addGap(18, 18, 18)
                                .addComponent(jLCriterio4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jCBCriterio2)
                                    .addGap(18, 18, 18)
                                    .addComponent(datoCriterio2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jCBCriterio1)
                                    .addGap(18, 18, 18)
                                    .addComponent(datoCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio6)
                                .addGap(18, 18, 18)
                                .addComponent(botonCriterio6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLEstaticoCriterio6)
                                .addGap(18, 18, 18)
                                .addComponent(jLCriterio6, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(datoCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCriterio1))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(datoCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCriterio2))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCriterio3))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(botonCriterio4)
                    .addComponent(jCBCriterio4))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstaticoCriterio4))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dato2Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstatico2Criterio5)
                    .addComponent(dato1Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstatico1Criterio5)
                    .addComponent(jCBCriterio5))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(botonCriterio6)
                    .addComponent(jCBCriterio6))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLCriterio6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstaticoCriterio6))
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addGap(20, 20, 20))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Fecha de origen", "Tipo de Lote", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
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
            jTable1.getColumnModel().getColumn(0).setMinWidth(70);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(25);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 907, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLStaticObjetoSeleccionado)
                        .addGap(18, 18, 18)
                        .addComponent(jLObjetoSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBConcretarAccion)
                        .addGap(18, 18, 18)
                        .addComponent(jBCancelar))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        try {
            ArrayList listaFiltrada = null;
            listaFiltrada = this.organizacion.filtrarCriterios(this.criteriosSeleccionados, datoCriterio1.getText(), datoCriterio2.getText(), (String)datoCriterio3.getSelectedItem(), unaOrdenSeleccionada, unAnalisisSeleccionado, dato1Criterio5.getCalendar(), dato2Criterio5.getCalendar());
            
            ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
            Iterator criterios = listaFiltrada.iterator();
            while (criterios.hasNext()){
                CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) criterios.next();    
                ((DefaultTableModel)this.jTable1.getModel()).addRow(unCriterio.devolverVector());
            }
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        this.unObjetoSeleccionado = this.organizacion.getCriteriosAnalisisLaboratorio().get(id);
        actualizarObjetoSeleccionado();
        habilitarBotones();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jCBCriterio5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio5ItemStateChanged
        jLEstatico1Criterio5.setEnabled(jCBCriterio5.isSelected());
        dato1Criterio5.setEnabled(jCBCriterio5.isSelected());
        jLEstatico2Criterio5.setEnabled(jCBCriterio5.isSelected());
        dato2Criterio5.setEnabled(jCBCriterio5.isSelected());
        criteriosSeleccionados.put(criterio5, jCBCriterio5.isSelected());
    }//GEN-LAST:event_jCBCriterio5ItemStateChanged

    private void jCBCriterio4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio4ItemStateChanged
        jLEstaticoCriterio4.setEnabled(jCBCriterio4.isSelected());
        jLCriterio4.setEnabled(jCBCriterio4.isSelected());
        botonCriterio4.setEnabled(jCBCriterio4.isSelected());
        criteriosSeleccionados.put(criterio4, jCBCriterio4.isSelected());
    }//GEN-LAST:event_jCBCriterio4ItemStateChanged

    private void botonCriterio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio4ActionPerformed
        BuscarOrdenDeProduccion unaVentana = new BuscarOrdenDeProduccion(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio4ActionPerformed

    private void jCBCriterio6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio6ItemStateChanged
        jLEstaticoCriterio6.setEnabled(jCBCriterio6.isSelected());
        jLCriterio6.setEnabled(jCBCriterio6.isSelected());
        botonCriterio6.setEnabled(jCBCriterio6.isSelected());
        criteriosSeleccionados.put(criterio6, jCBCriterio6.isSelected());
    }//GEN-LAST:event_jCBCriterio6ItemStateChanged

    private void botonCriterio6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio6ActionPerformed
        BuscarAnalisisLaboratorio unaVentana = new BuscarAnalisisLaboratorio(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio6ActionPerformed

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
            java.util.logging.Logger.getLogger(BuscarCriterioAnalisisLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarCriterioAnalisisLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarCriterioAnalisisLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarCriterioAnalisisLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new BuscarCriterioAnalisisLaboratorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCriterio4;
    private javax.swing.JButton botonCriterio6;
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private com.toedter.calendar.JDateChooser dato1Criterio5;
    private com.toedter.calendar.JDateChooser dato2Criterio5;
    private javax.swing.JTextField datoCriterio1;
    private javax.swing.JTextField datoCriterio2;
    private javax.swing.JComboBox<String> datoCriterio3;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JCheckBox jCBCriterio4;
    private javax.swing.JCheckBox jCBCriterio5;
    private javax.swing.JCheckBox jCBCriterio6;
    private javax.swing.JLabel jLCriterio4;
    private javax.swing.JLabel jLCriterio6;
    private javax.swing.JLabel jLEstatico1Criterio5;
    private javax.swing.JLabel jLEstatico2Criterio5;
    private javax.swing.JLabel jLEstaticoCriterio4;
    private javax.swing.JLabel jLEstaticoCriterio6;
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
        jLObjetoSeleccionado.setText(this.unObjetoSeleccionado.getNombre());
        
    }
    private void habilitarBotones(){
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
    }

    @Override
    public void actualizarUnObjeto(Object unObjeto) {
        if (unObjeto instanceof OrdenDeProduccion){
            unaOrdenSeleccionada = (OrdenDeProduccion) unObjeto;
            exhibirOrdenDeProduccion();
        }
        if (unObjeto instanceof AnalisisLaboratorio){
            unAnalisisSeleccionado = (AnalisisLaboratorio) unObjeto;
            jLCriterio6.setText(""+ unAnalisisSeleccionado.getId());
        }
    }

    private void exhibirOrdenDeProduccion() {
        if (unaOrdenSeleccionada == null)
            return;
        jLCriterio4.setText(""+unaOrdenSeleccionada.getId());
    }

}
