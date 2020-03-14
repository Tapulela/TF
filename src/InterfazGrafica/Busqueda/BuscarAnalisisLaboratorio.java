/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Busqueda;


import InterfazGrafica.ABMProveedor;
import InterfazGrafica.GestionIngresoMP;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.TransferenciaInstancias;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import LogicaDeNegocio.AnalisisLaboratorio;
import LogicaDeNegocio.CriterioAnalisisLaboratorio;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.Lote;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author usuario
 */
public class BuscarAnalisisLaboratorio extends javax.swing.JFrame implements TransferenciaInstancias{

    private JFrame ventanaAnterior;
    private Organizacion organizacion;
    private Map<String, Boolean> criteriosSeleccionados;
    private AnalisisLaboratorio unObjetoSeleccionado;
    private String trayectoriaActual;
    
    private Lote unLoteSeleccionado;
    private CriterioAnalisisLaboratorio unCriterioSeleccionado;
    private OrdenDeProduccion unaOrdenDeProduccion;
    private Equipamiento unLaboratorio;
    private OrdenDeCompra unaOrdenDeCompra;
    private boolean poseeLote = false;
    
    private final String criterio1 = "lote";
    private final String criterio2 = "comentario";
    private final String criterio3 = "tipoLote";
    private final String criterio4 = "criterioImplicado";
    private final String criterio5 = "estado";
    private final String criterio6 = "fechaOrigen";
    private final String criterio7 = "ordenProduccion";
    private final String criterio8 = "laboratorio";
    private final String criterio9 = "ordenCompra";
    private final String criterio10 = "poseeLote";
    
    /**
     * Creates new form BuscarProveedor
     */
    
    
    public BuscarAnalisisLaboratorio() {
        initComponents();
    }

    public BuscarAnalisisLaboratorio(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior) {
        
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true); 
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaAnterior.setFocusable(false);
        this.organizacion = organizacion;
        this.trayectoriaActual = trayectoriaAnterior + " - Busqueda de análisis de laboratorio";
        
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Busqueda de un análisis de laboratorio", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
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
        criteriosSeleccionados.put(criterio7, false);
        criteriosSeleccionados.put(criterio8, false);
        criteriosSeleccionados.put(criterio9, false);
        criteriosSeleccionados.put(criterio10, false);
        if (ventanaAnterior instanceof GestionIngresoMP){   //Si se trata de un ingreso, solo puedo seleccionar segun la orden de compra ingresada en el constructor
                this.unaOrdenDeCompra = ((GestionIngresoMP)ventanaAnterior).getUnaOrdenDeCompraSeleccionada();
                if (this.unaOrdenDeCompra == null){
                    JOptionPane.showMessageDialog(null, "Error: Debe seleccionar una orden de compra para acudir seleccionar un analisis de laboratorio.");
                    this.dispose();
                }
                jCBCriterio9.doClick();
                jCBCriterio9.setEnabled(false);
                botonCriterio9.setEnabled(false);
                jLEstaticoCriterio9.setEnabled(false);
                jLCriterio9.setEnabled(false);
                exhibirOrdenDeCompra();
                
                jCBCriterio5.doClick();
                jCBCriterio5.setEnabled(false);
                datoCriterio5.setSelectedItem("Regular");
                datoCriterio5.setEnabled(false);
                
                jCBCriterio10.doClick();
                jCBCriterio10.setEnabled(false);
                dato1Criterio10.setEnabled(false);
                dato2Criterio10.setEnabled(false);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jBConcretarAccion = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jLStaticObjetoSeleccionado = new javax.swing.JLabel();
        jLObjetoSeleccionado = new javax.swing.JLabel();
        cabeceraDeVentana = new InterfazGrafica.CabeceraDeVentana();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        botonCriterio8 = new javax.swing.JButton();
        jLEstaticoCriterio7 = new javax.swing.JLabel();
        datoCriterio2 = new javax.swing.JTextField();
        botonCriterio7 = new javax.swing.JButton();
        botonCriterio9 = new javax.swing.JButton();
        jCBCriterio4 = new javax.swing.JCheckBox();
        jCBCriterio1 = new javax.swing.JCheckBox();
        jCBCriterio2 = new javax.swing.JCheckBox();
        jCBCriterio8 = new javax.swing.JCheckBox();
        jCBCriterio9 = new javax.swing.JCheckBox();
        jLEstaticoCriterio4 = new javax.swing.JLabel();
        jCBCriterio7 = new javax.swing.JCheckBox();
        jLCriterio8 = new javax.swing.JLabel();
        jLEstaticoCriterio9 = new javax.swing.JLabel();
        botonCriterio4 = new javax.swing.JButton();
        jLEstaticoCriterio8 = new javax.swing.JLabel();
        jLCriterio4 = new javax.swing.JLabel();
        jLEstaticoCriterio1 = new javax.swing.JLabel();
        botonCriterio1 = new javax.swing.JButton();
        jLCriterio7 = new javax.swing.JLabel();
        jLCriterio9 = new javax.swing.JLabel();
        jLCriterio1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLEstatico1Criterio6 = new javax.swing.JLabel();
        dato1Criterio6 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio6 = new javax.swing.JLabel();
        dato2Criterio6 = new com.toedter.calendar.JDateChooser();
        datoCriterio5 = new javax.swing.JComboBox<>();
        jCBCriterio6 = new javax.swing.JCheckBox();
        jCBCriterio3 = new javax.swing.JCheckBox();
        jCBCriterio5 = new javax.swing.JCheckBox();
        datoCriterio3 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jCBCriterio10 = new javax.swing.JCheckBox();
        dato1Criterio10 = new javax.swing.JRadioButton();
        dato2Criterio10 = new javax.swing.JRadioButton();
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

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botonCriterio8.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio8.setText("Buscar un laboratorio");
        botonCriterio8.setEnabled(false);
        botonCriterio8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio8ActionPerformed(evt);
            }
        });

        jLEstaticoCriterio7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio7.setText("Orden de produccion seleccionada:");

        datoCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio2.setText("Ingrese un comentario");
        datoCriterio2.setEnabled(false);

        botonCriterio7.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio7.setText("Buscar una Orden de produccion");
        botonCriterio7.setEnabled(false);
        botonCriterio7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio7ActionPerformed(evt);
            }
        });

        botonCriterio9.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio9.setText("Buscar una orden de compra");
        botonCriterio9.setEnabled(false);
        botonCriterio9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio9ActionPerformed(evt);
            }
        });

        jCBCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio4.setText("Criterio implicado");
        jCBCriterio4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio4ItemStateChanged(evt);
            }
        });

        jCBCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio1.setText("Lote implicado");
        jCBCriterio1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio1ItemStateChanged(evt);
            }
        });

        jCBCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio2.setText("comentario");
        jCBCriterio2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio2ItemStateChanged(evt);
            }
        });

        jCBCriterio8.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio8.setText("Laboratorio implicado");
        jCBCriterio8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio8ItemStateChanged(evt);
            }
        });

        jCBCriterio9.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio9.setText("Orden de compra implicada");
        jCBCriterio9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio9ItemStateChanged(evt);
            }
        });

        jLEstaticoCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio4.setText("Criterio de análisis seleccionado:");

        jCBCriterio7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio7.setText("Orden de produccion implicada");
        jCBCriterio7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio7ItemStateChanged(evt);
            }
        });

        jLCriterio8.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstaticoCriterio9.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio9.setText("Orden de compra seleccionada");

        botonCriterio4.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio4.setText("Buscar un criterio de analisis");
        botonCriterio4.setEnabled(false);
        botonCriterio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio4ActionPerformed(evt);
            }
        });

        jLEstaticoCriterio8.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio8.setText("Laboratorio seleccionado");

        jLCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstaticoCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio1.setText("Lote seleccionado:");

        botonCriterio1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio1.setText("Buscar un lote");
        botonCriterio1.setEnabled(false);
        botonCriterio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio1ActionPerformed(evt);
            }
        });

        jLCriterio7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLCriterio9.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jCBCriterio9)
                        .addGap(18, 18, 18)
                        .addComponent(botonCriterio9))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLEstaticoCriterio7)
                            .addGap(18, 18, 18)
                            .addComponent(jLCriterio7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jCBCriterio8)
                            .addGap(18, 18, 18)
                            .addComponent(botonCriterio8))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jCBCriterio7)
                            .addGap(18, 18, 18)
                            .addComponent(botonCriterio7))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLEstaticoCriterio8)
                            .addGap(18, 18, 18)
                            .addComponent(jLCriterio8, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLEstaticoCriterio9)
                            .addGap(18, 18, 18)
                            .addComponent(jLCriterio9, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jCBCriterio1)
                        .addGap(18, 18, 18)
                        .addComponent(botonCriterio1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLEstaticoCriterio1)
                        .addGap(18, 18, 18)
                        .addComponent(jLCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jCBCriterio4)
                        .addGap(18, 18, 18)
                        .addComponent(botonCriterio4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jCBCriterio2)
                        .addGap(18, 18, 18)
                        .addComponent(datoCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLEstaticoCriterio4)
                    .addComponent(jLCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio1)
                    .addComponent(botonCriterio1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstaticoCriterio1))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio2)
                    .addComponent(datoCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCBCriterio4)
                    .addComponent(botonCriterio4))
                .addGap(18, 18, 18)
                .addComponent(jLEstaticoCriterio4)
                .addGap(18, 18, 18)
                .addComponent(jLCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(botonCriterio7)
                            .addComponent(jCBCriterio7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLCriterio7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLEstaticoCriterio7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBCriterio8)
                            .addComponent(botonCriterio8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLEstaticoCriterio8)
                            .addComponent(jLCriterio8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(botonCriterio9)
                            .addComponent(jCBCriterio9))
                        .addGap(18, 18, 18)
                        .addComponent(jLEstaticoCriterio9))
                    .addComponent(jLCriterio9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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

        datoCriterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Regular", "Anulado" }));
        datoCriterio5.setEnabled(false);

        jCBCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio6.setText("Fecha de origen");
        jCBCriterio6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio6ItemStateChanged(evt);
            }
        });

        jCBCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio3.setText("Tipo de lote");
        jCBCriterio3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio3ItemStateChanged(evt);
            }
        });

        jCBCriterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio5.setText("Estado");
        jCBCriterio5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio5ItemStateChanged(evt);
            }
        });

        datoCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "YCV", "YCE", "YM" }));
        datoCriterio3.setEnabled(false);

        jCBCriterio10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio10.setText("Posee lote asociado");
        jCBCriterio10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio10ItemStateChanged(evt);
            }
        });

        buttonGroup1.add(dato1Criterio10);
        dato1Criterio10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        dato1Criterio10.setText("Si");
        dato1Criterio10.setEnabled(false);
        dato1Criterio10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dato1Criterio10ItemStateChanged(evt);
            }
        });

        buttonGroup1.add(dato2Criterio10);
        dato2Criterio10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        dato2Criterio10.setSelected(true);
        dato2Criterio10.setText("No");
        dato2Criterio10.setEnabled(false);
        dato2Criterio10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dato2Criterio10ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCBCriterio10)
                .addGap(18, 18, 18)
                .addComponent(dato1Criterio10)
                .addGap(18, 18, 18)
                .addComponent(dato2Criterio10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dato1Criterio10)
                        .addComponent(dato2Criterio10))
                    .addComponent(jCBCriterio10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jCBCriterio3)
                                .addGap(18, 18, 18)
                                .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jCBCriterio5)
                                .addGap(18, 18, 18)
                                .addComponent(datoCriterio5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jCBCriterio6)
                                .addGap(18, 18, 18)
                                .addComponent(jLEstatico1Criterio6)
                                .addGap(6, 6, 6)
                                .addComponent(dato1Criterio6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLEstatico2Criterio6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dato2Criterio6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio3)
                    .addComponent(datoCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBCriterio5)
                    .addComponent(datoCriterio5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCBCriterio6)
                    .addComponent(jLEstatico1Criterio6)
                    .addComponent(dato1Criterio6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstatico2Criterio6)
                    .addComponent(dato2Criterio6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(679, 679, 679)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Resultado", "Estado", "Lote implicado", "O.C.", "Criterio utilizado"
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
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(70);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(2).setMinWidth(250);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(250);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(4).setMinWidth(80);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1637, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBConcretarAccion)
                        .addComponent(jBCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLStaticObjetoSeleccionado)
                        .addComponent(jLObjetoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
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
        botonCriterio1.setEnabled(jCBCriterio1.isSelected());
        jLEstaticoCriterio1.setEnabled(jCBCriterio1.isSelected());
        jLCriterio1.setEnabled(jCBCriterio1.isSelected());
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
            listaFiltrada = this.organizacion.filtrarAnalisisDeLaboratorio(this.criteriosSeleccionados, unLoteSeleccionado, datoCriterio2.getText(), (String)datoCriterio3.getSelectedItem(), unCriterioSeleccionado, (String) datoCriterio5.getSelectedItem(), dato1Criterio6.getCalendar(), dato2Criterio6.getCalendar(), unaOrdenDeProduccion, unLaboratorio, unaOrdenDeCompra, poseeLote);
            
            ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
            Iterator analisisLaboratorio = listaFiltrada.iterator();
            while (analisisLaboratorio.hasNext()){
                AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) analisisLaboratorio.next();    
                ((DefaultTableModel)this.jTable1.getModel()).addRow(unAnalisis.devolverVector());
            }
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        this.unObjetoSeleccionado = this.organizacion.getAnalisisLaboratorio().get(id);
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
        datoCriterio5.setEnabled(jCBCriterio5.isSelected());
        criteriosSeleccionados.put(criterio5, jCBCriterio5.isSelected());
    }//GEN-LAST:event_jCBCriterio5ItemStateChanged

    private void botonCriterio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio1ActionPerformed
        BuscarLote unaVentana = new BuscarLote(organizacion, this, trayectoriaActual, null);
        this.dispose();
    }//GEN-LAST:event_botonCriterio1ActionPerformed

    private void botonCriterio7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio7ActionPerformed
        BuscarOrdenDeProduccion unaVentana = new BuscarOrdenDeProduccion(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio7ActionPerformed

    private void jCBCriterio7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio7ItemStateChanged
        botonCriterio7.setEnabled(jCBCriterio7.isSelected());
        jLEstaticoCriterio7.setEnabled(jCBCriterio7.isSelected());
        jLCriterio7.setEnabled(jCBCriterio7.isSelected());
        criteriosSeleccionados.put(criterio7, jCBCriterio7.isSelected());
    }//GEN-LAST:event_jCBCriterio7ItemStateChanged

    private void botonCriterio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio4ActionPerformed
        BuscarCriterioAnalisisLaboratorio ventana = new BuscarCriterioAnalisisLaboratorio(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio4ActionPerformed

    private void jCBCriterio4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio4ItemStateChanged
        botonCriterio4.setEnabled(jCBCriterio4.isSelected());
        jLEstaticoCriterio4.setEnabled(jCBCriterio4.isSelected());
        jLCriterio4.setEnabled(jCBCriterio4.isSelected());
        criteriosSeleccionados.put(criterio4, jCBCriterio4.isSelected());
    }//GEN-LAST:event_jCBCriterio4ItemStateChanged

    private void jCBCriterio8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio8ItemStateChanged
        botonCriterio8.setEnabled(jCBCriterio8.isSelected());
        jLEstaticoCriterio8.setEnabled(jCBCriterio8.isSelected());
        jLCriterio8.setEnabled(jCBCriterio8.isSelected());
        criteriosSeleccionados.put(criterio8, jCBCriterio8.isSelected());
    }//GEN-LAST:event_jCBCriterio8ItemStateChanged

    private void botonCriterio8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio8ActionPerformed
        BuscarEquipamiento ventana = new BuscarEquipamiento(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio8ActionPerformed

    private void botonCriterio9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio9ActionPerformed
        BuscarOrdenCompra ventana = new BuscarOrdenCompra(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio9ActionPerformed

    private void jCBCriterio9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio9ItemStateChanged
        botonCriterio9.setEnabled(jCBCriterio9.isSelected());
        jLEstaticoCriterio9.setEnabled(jCBCriterio9.isSelected());
        jLCriterio9.setEnabled(jCBCriterio9.isSelected());
        criteriosSeleccionados.put(criterio9, jCBCriterio9.isSelected());
    }//GEN-LAST:event_jCBCriterio9ItemStateChanged

    private void jCBCriterio10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio10ItemStateChanged
        dato1Criterio10.setEnabled(((JCheckBox)evt.getItemSelectable()).isSelected());
        dato2Criterio10.setEnabled(((JCheckBox)evt.getItemSelectable()).isSelected());
        criteriosSeleccionados.put(criterio10, jCBCriterio10.isSelected());
    }//GEN-LAST:event_jCBCriterio10ItemStateChanged

    private void dato1Criterio10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dato1Criterio10ItemStateChanged
        if (((JRadioButton)evt.getItemSelectable()).isSelected())
            this.poseeLote = true;
    }//GEN-LAST:event_dato1Criterio10ItemStateChanged

    private void dato2Criterio10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dato2Criterio10ItemStateChanged
        if (((JRadioButton)evt.getItemSelectable()).isSelected())
            this.poseeLote = false;
    }//GEN-LAST:event_dato2Criterio10ItemStateChanged

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
            java.util.logging.Logger.getLogger(BuscarAnalisisLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarAnalisisLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarAnalisisLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarAnalisisLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new BuscarAnalisisLaboratorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCriterio1;
    private javax.swing.JButton botonCriterio4;
    private javax.swing.JButton botonCriterio7;
    private javax.swing.JButton botonCriterio8;
    private javax.swing.JButton botonCriterio9;
    private javax.swing.ButtonGroup buttonGroup1;
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private javax.swing.JRadioButton dato1Criterio10;
    private com.toedter.calendar.JDateChooser dato1Criterio6;
    private javax.swing.JRadioButton dato2Criterio10;
    private com.toedter.calendar.JDateChooser dato2Criterio6;
    private javax.swing.JTextField datoCriterio2;
    private javax.swing.JComboBox<String> datoCriterio3;
    private javax.swing.JComboBox<String> datoCriterio5;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio10;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JCheckBox jCBCriterio4;
    private javax.swing.JCheckBox jCBCriterio5;
    private javax.swing.JCheckBox jCBCriterio6;
    private javax.swing.JCheckBox jCBCriterio7;
    private javax.swing.JCheckBox jCBCriterio8;
    private javax.swing.JCheckBox jCBCriterio9;
    private javax.swing.JLabel jLCriterio1;
    private javax.swing.JLabel jLCriterio4;
    private javax.swing.JLabel jLCriterio7;
    private javax.swing.JLabel jLCriterio8;
    private javax.swing.JLabel jLCriterio9;
    private javax.swing.JLabel jLEstatico1Criterio6;
    private javax.swing.JLabel jLEstatico2Criterio6;
    private javax.swing.JLabel jLEstaticoCriterio1;
    private javax.swing.JLabel jLEstaticoCriterio4;
    private javax.swing.JLabel jLEstaticoCriterio7;
    private javax.swing.JLabel jLEstaticoCriterio8;
    private javax.swing.JLabel jLEstaticoCriterio9;
    private javax.swing.JLabel jLObjetoSeleccionado;
    private javax.swing.JLabel jLStaticObjetoSeleccionado;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        jLCriterio1.setText("");
        this.unLoteSeleccionado = null;
        datoCriterio2.setText("");
        jLCriterio4.setText("");
        this.unCriterioSeleccionado = null;
        datoCriterio5.setSelectedItem("Seleccionar");
        dato1Criterio6.setCalendar(null); dato2Criterio6.setCalendar(null);
        jLCriterio7.setText("");
        this.unaOrdenDeProduccion = null;
        
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
        jBConcretarAccion.setEnabled(false);
        jBCancelar.setEnabled(false);
        jLObjetoSeleccionado.setText("");
        this.unObjetoSeleccionado = null;
    }

    private void actualizarObjetoSeleccionado() {
        jLObjetoSeleccionado.setText(""+this.unObjetoSeleccionado.getId());
        
    }
    private void habilitarBotones(){
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
    }

    @Override
    public void actualizarUnObjeto(Object unObjeto) {
        if (unObjeto instanceof Lote){
            this.unLoteSeleccionado = (Lote) unObjeto;
            exhibirLote();
        }
        if (unObjeto instanceof CriterioAnalisisLaboratorio){
            this.unCriterioSeleccionado = (CriterioAnalisisLaboratorio) unObjeto;
            exhibirCriterio();
        }
        if (unObjeto instanceof OrdenDeProduccion){
            this.unaOrdenDeProduccion = (OrdenDeProduccion) unObjeto;
            exhibirOrdenDeProduccion();
        }
        if (unObjeto instanceof Equipamiento){
            this.unLaboratorio = (Equipamiento) unObjeto;
            exhibirLaboratorio();
        }
        if (unObjeto instanceof OrdenDeCompra){
            this.unaOrdenDeCompra = (OrdenDeCompra) unObjeto;
            exhibirOrdenDeCompra();
        }
    }

    private void exhibirLote() {
        jLCriterio1.setText("ID "+unLoteSeleccionado.getId()+", etiqueta: "+unLoteSeleccionado.getEtiqueta());
    }

    private void exhibirCriterio() {
        jLCriterio4.setText(unCriterioSeleccionado.getNombre());
    }

    private void exhibirOrdenDeProduccion() {
        jLCriterio7.setText(""+this.unaOrdenDeProduccion.getId());
    }

    private void exhibirLaboratorio() {
        jLCriterio8.setText(unLaboratorio.getNombre());
    }

    private void exhibirOrdenDeCompra() {
        if (unaOrdenDeCompra == null)
            return;
        jLCriterio9.setText(""+unaOrdenDeCompra.getId());
    }

}
