/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Busqueda;


import InterfazGrafica.GestionMoliendas;
import InterfazGrafica.GestionMovimientos;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.TransferenciaInstancias;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDerechaDeTabla;
import LogicaDeNegocio.DetalleTransformacion;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.Lote;
import LogicaDeNegocio.OrdenDeCompra;
import LogicaDeNegocio.OrdenDeProduccion;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Proveedor;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class BuscarLote extends javax.swing.JFrame implements TransferenciaInstancias{

    private JFrame ventanaAnterior;
    private Organizacion organizacion;
    private Map<String, Boolean> criteriosSeleccionados;
    
    private Lote unObjetoSeleccionado;
    
    private OrdenDeProduccion unaOrdenDeProduccionSeleccionada;
    private OrdenDeCompra unaOrdenDeCompraSeleccionada;
    private Equipamiento unEquipamientoSeleccionado;
    private Proveedor unProveedorSeleccionado;

    private String trayectoriaActual;
    
    private final String criterio1 = "etiqueta";
    private final String criterio2 = "ordenProduccion";
    private final String criterio3 = "ordenCompra";
    private final String criterio4 = "proveedorOrdenCompra";
    private final String criterio5 = "fechaOrigen";
    private final String criterio6 = "equipamiento";
    private final String criterio7 = "tipoLote";
    
    
    
    /**
     * Creates new form BuscarProveedor
     */
    
    
    public BuscarLote() {
        initComponents();
    }

    public BuscarLote(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior, Equipamiento unEquipamiento) {
        
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true); 
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaAnterior.setFocusable(false);
        this.organizacion = organizacion;
        this.trayectoriaActual = trayectoriaAnterior + " - Busqueda de Lote";
        this.unEquipamientoSeleccionado = unEquipamiento;
        
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Busqueda de un Lote", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
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
        if (ventanaAnterior instanceof GestionMovimientos){
            
            //No voy a poder elegir equipamiento, porque fue elegido previamente.
            jCBCriterio6.doClick();
            jCBCriterio6.setEnabled(false);
            botonCriterio6.setEnabled(false);
            actualizarEquipamiento();
        }
        if (ventanaAnterior instanceof GestionMoliendas){
            GestionMoliendas gestionMoliendas = (GestionMoliendas) ventanaAnterior;
            
            //No voy a poder elegir equipamiento, porque fue elegido previamente.
            this.unEquipamientoSeleccionado = gestionMoliendas.getUnMolino();
            jCBCriterio6.doClick();
            jCBCriterio6.setEnabled(false);
            botonCriterio6.setEnabled(false);
            actualizarEquipamiento();
            
            //La orden de produccion ya esta impuesta.
            OrdenDeProduccion unaOrdenProduccion = gestionMoliendas.getUnaOrdenProduccionSeleccionada();
            this.unaOrdenDeProduccionSeleccionada = unaOrdenProduccion;
            jCBCriterio2.doClick();
            jCBCriterio2.setEnabled(false);
            botonCriterio2.setEnabled(false);
            jLCriterio2.setText(""+unaOrdenProduccion.getId());
            
            //El tipo de lote debe ser exclusivamente de estacionamiento
            jCBCriterio7.doClick();
            jCBCriterio7.setEnabled(false);
            jCB3.setSelectedItem(Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA);
            jCB3.setEnabled(false);
        }
        
        
        ParametrosDeInterfaz.configurarVentana(this);
        establecerAlineacionDerechaDeTabla(jTable1, 9);
        
        
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
        jCBCriterio2 = new javax.swing.JCheckBox();
        jCBCriterio3 = new javax.swing.JCheckBox();
        datoCriterio1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCBCriterio5 = new javax.swing.JCheckBox();
        jLEstatico1Criterio5 = new javax.swing.JLabel();
        dato1Criterio5 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio5 = new javax.swing.JLabel();
        botonCriterio2 = new javax.swing.JButton();
        jLEstaticoCriterio2 = new javax.swing.JLabel();
        jLCriterio2 = new javax.swing.JLabel();
        botonCriterio3 = new javax.swing.JButton();
        jLEstaticoCriterio3 = new javax.swing.JLabel();
        jLCriterio3 = new javax.swing.JLabel();
        dato2Criterio5 = new com.toedter.calendar.JDateChooser();
        botonCriterio4 = new javax.swing.JButton();
        jLCriterio4 = new javax.swing.JLabel();
        jLEstaticoCriterio4 = new javax.swing.JLabel();
        jCBCriterio4 = new javax.swing.JCheckBox();
        jCBCriterio6 = new javax.swing.JCheckBox();
        botonCriterio6 = new javax.swing.JButton();
        jLCriterio6 = new javax.swing.JLabel();
        jLEstaticoCriterio6 = new javax.swing.JLabel();
        jCBCriterio7 = new javax.swing.JCheckBox();
        jCB3 = new javax.swing.JComboBox<>();
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

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLabel3.setText("Criterios de búsqueda");

        jCBCriterio1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jCBCriterio1.setText("Etiqueta");
        jCBCriterio1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio1ItemStateChanged(evt);
            }
        });

        jCBCriterio2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jCBCriterio2.setText("Orden de produccion asociada");
        jCBCriterio2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio2ItemStateChanged(evt);
            }
        });

        jCBCriterio3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jCBCriterio3.setText("Orden de compra asociada");
        jCBCriterio3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio3ItemStateChanged(evt);
            }
        });

        datoCriterio1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        datoCriterio1.setEnabled(false);

        jButton1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCBCriterio5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jCBCriterio5.setText("Fecha de origen");
        jCBCriterio5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio5ItemStateChanged(evt);
            }
        });

        jLEstatico1Criterio5.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLEstatico1Criterio5.setText("Desde");
        jLEstatico1Criterio5.setEnabled(false);

        dato1Criterio5.setEnabled(false);
        dato1Criterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstatico2Criterio5.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLEstatico2Criterio5.setText("Hasta");
        jLEstatico2Criterio5.setEnabled(false);

        botonCriterio2.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio2.setText("Buscar una Orden de Produccion");
        botonCriterio2.setEnabled(false);
        botonCriterio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio2ActionPerformed(evt);
            }
        });

        jLEstaticoCriterio2.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLEstaticoCriterio2.setText("ID de orden de producción seleccionada");

        jLCriterio2.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N

        botonCriterio3.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio3.setText("Buscar una Orden de Compra");
        botonCriterio3.setEnabled(false);
        botonCriterio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio3ActionPerformed(evt);
            }
        });

        jLEstaticoCriterio3.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLEstaticoCriterio3.setText("ID de orden de compra seleccionada");

        jLCriterio3.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N

        dato2Criterio5.setEnabled(false);
        dato2Criterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        botonCriterio4.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio4.setText("Buscar un Proveedor");
        botonCriterio4.setEnabled(false);
        botonCriterio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio4ActionPerformed(evt);
            }
        });

        jLCriterio4.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N

        jLEstaticoCriterio4.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLEstaticoCriterio4.setText("Proveedor asociado");

        jCBCriterio4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jCBCriterio4.setText("Proveedor del producto asociado");
        jCBCriterio4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio4ItemStateChanged(evt);
            }
        });

        jCBCriterio6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jCBCriterio6.setText("Equipamiento asociado");
        jCBCriterio6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio6ItemStateChanged(evt);
            }
        });

        botonCriterio6.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio6.setText("Buscar un equipamiento");
        botonCriterio6.setEnabled(false);
        botonCriterio6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio6ActionPerformed(evt);
            }
        });

        jLCriterio6.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N

        jLEstaticoCriterio6.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLEstaticoCriterio6.setText("Equipamiento asociado");

        jCBCriterio7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jCBCriterio7.setText("Tipo de Lote");
        jCBCriterio7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio7ItemStateChanged(evt);
            }
        });

        jCB3.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "YCV", "YCE", "YM" }));
        jCB3.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCriterio4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio1)
                                        .addGap(18, 18, 18)
                                        .addComponent(datoCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio2)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonCriterio2))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLEstatico1Criterio5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dato1Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLEstatico2Criterio5)
                                        .addGap(24, 24, 24)
                                        .addComponent(dato2Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio6)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonCriterio6)))))
                        .addGap(0, 45, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLEstaticoCriterio3)
                                .addGap(18, 18, 18)
                                .addComponent(jLCriterio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLEstaticoCriterio2)
                                .addGap(18, 18, 18)
                                .addComponent(jLCriterio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLEstaticoCriterio6)
                                .addGap(18, 18, 18)
                                .addComponent(jLCriterio6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio3)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonCriterio3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio4)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonCriterio4))
                                    .addComponent(jLEstaticoCriterio4))
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio2)
                    .addComponent(botonCriterio2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLEstaticoCriterio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio3)
                    .addComponent(botonCriterio3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLEstaticoCriterio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLCriterio3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio4)
                    .addComponent(botonCriterio4))
                .addGap(18, 18, 18)
                .addComponent(jLEstaticoCriterio4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCBCriterio5)
                        .addComponent(jLEstatico1Criterio5))
                    .addComponent(jLEstatico2Criterio5)
                    .addComponent(dato1Criterio5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dato2Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCriterio6)
                    .addComponent(jCBCriterio6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLEstaticoCriterio6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLCriterio6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCriterio7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Estado", "ID O,P.", "ID O.C.", "Proveedor", "fecha de llegada", "Etiqueta", "Tipo", "Bolsas disponibles", "Peso disponible"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(40);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(43);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(45);
            jTable1.getColumnModel().getColumn(1).setMinWidth(82);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(85);
            jTable1.getColumnModel().getColumn(2).setMinWidth(60);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(3).setMinWidth(60);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(5).setMinWidth(155);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(152);
            jTable1.getColumnModel().getColumn(6).setMinWidth(300);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(300);
        }

        jLStaticObjetoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticObjetoSeleccionado.setText("ID Lote seleccionado:");

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
                        .addComponent(jLStaticObjetoSeleccionado)
                        .addGap(18, 18, 18)
                        .addComponent(jLObjetoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
                        .addComponent(jBConcretarAccion)
                        .addGap(18, 18, 18)
                        .addComponent(jBCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLStaticObjetoSeleccionado)
                    .addComponent(jLObjetoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBConcretarAccion)
                    .addComponent(jBCancelar))
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

    private void jCBCriterio3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio3ItemStateChanged
        botonCriterio3.setEnabled(jCBCriterio3.isSelected());
        jLEstaticoCriterio3.setEnabled(jCBCriterio3.isSelected());
        jLCriterio3.setEnabled(jCBCriterio3.isSelected());
        
        criteriosSeleccionados.put(criterio3, jCBCriterio3.isSelected());
        
        /*if (jCBCriterio4.isSelected() && jCBCriterio7.isSelected()){
            jCBCriterio7.doClick();
        }*/
    }//GEN-LAST:event_jCBCriterio3ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (!(ventanaAnterior instanceof GestionMoliendas)){
                ArrayList listaFiltrada = null;
                listaFiltrada = this.organizacion.filtrarLotes(this.criteriosSeleccionados, unEquipamientoSeleccionado, datoCriterio1.getText(),unaOrdenDeProduccionSeleccionada, unaOrdenDeCompraSeleccionada, unProveedorSeleccionado, dato1Criterio5.getCalendar(), dato2Criterio5.getCalendar(), (String) jCB3.getSelectedItem());

                ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
                Iterator ingresos = listaFiltrada.iterator();
                while (ingresos.hasNext()){
                    Lote unLote = (Lote) ingresos.next();    
                    ((DefaultTableModel)this.jTable1.getModel()).addRow(unLote.devolverVector());
                }
            }else{
                ArrayList listaFiltrada = null;
                GestionMoliendas unaVentanaAnterior = (GestionMoliendas) ventanaAnterior;
                ArrayList lotesAOmitir = DetalleTransformacion.obtenerLotesImplicados(unaVentanaAnterior.getDetallesAsociados());
                listaFiltrada = this.organizacion.filtrarLotesOmitiendoLotes(this.criteriosSeleccionados, unEquipamientoSeleccionado, datoCriterio1.getText(),unaOrdenDeProduccionSeleccionada, unaOrdenDeCompraSeleccionada, unProveedorSeleccionado, dato1Criterio5.getCalendar(), dato2Criterio5.getCalendar(), lotesAOmitir, (String) jCB3.getSelectedItem());

                ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
                Iterator ingresos = listaFiltrada.iterator();
                while (ingresos.hasNext()){
                    Lote unLote = (Lote) ingresos.next();    
                    ((DefaultTableModel)this.jTable1.getModel()).addRow(unLote.devolverVector());
                }
            }
            
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        this.unObjetoSeleccionado = this.organizacion.getLotes().get(id);
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

    private void botonCriterio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio3ActionPerformed
        BuscarOrdenCompra unaVentana = new BuscarOrdenCompra(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio3ActionPerformed

    private void botonCriterio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio4ActionPerformed
        BuscarProveedor unaVentana = new BuscarProveedor(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio4ActionPerformed

    private void jCBCriterio4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio4ItemStateChanged
        botonCriterio4.setEnabled(jCBCriterio4.isSelected());
        jLEstaticoCriterio4.setEnabled(jCBCriterio4.isSelected());
        jLCriterio4.setEnabled(jCBCriterio4.isSelected());
        
        criteriosSeleccionados.put(criterio4, jCBCriterio4.isSelected());
        
        
    }//GEN-LAST:event_jCBCriterio4ItemStateChanged

    private void botonCriterio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio2ActionPerformed

        BuscarOrdenDeProduccion unaVentana = new BuscarOrdenDeProduccion(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_botonCriterio2ActionPerformed

    private void jCBCriterio2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio2ItemStateChanged
        botonCriterio2.setEnabled(jCBCriterio2.isSelected());
        jLEstaticoCriterio2.setEnabled(jCBCriterio2.isSelected());
        jLCriterio2.setEnabled(jCBCriterio2.isSelected());

        criteriosSeleccionados.put(criterio2, jCBCriterio2.isSelected());
    }//GEN-LAST:event_jCBCriterio2ItemStateChanged

    private void jCBCriterio6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio6ItemStateChanged
        botonCriterio6.setEnabled(jCBCriterio6.isSelected());
        jLEstaticoCriterio6.setEnabled(jCBCriterio6.isSelected());
        jLCriterio6.setEnabled(jCBCriterio6.isSelected());

        criteriosSeleccionados.put(criterio6, jCBCriterio6.isSelected());
    }//GEN-LAST:event_jCBCriterio6ItemStateChanged

    private void botonCriterio6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio6ActionPerformed
        BuscarEquipamiento ventana = new BuscarEquipamiento(organizacion, this, trayectoriaActual);
        dispose();
    }//GEN-LAST:event_botonCriterio6ActionPerformed

    private void jCBCriterio7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio7ItemStateChanged
        jCB3.setEnabled(jCBCriterio7.isSelected());
        criteriosSeleccionados.put(criterio7, jCBCriterio7.isSelected());
    }//GEN-LAST:event_jCBCriterio7ItemStateChanged

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
            java.util.logging.Logger.getLogger(BuscarLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarLote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCriterio2;
    private javax.swing.JButton botonCriterio3;
    private javax.swing.JButton botonCriterio4;
    private javax.swing.JButton botonCriterio6;
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private com.toedter.calendar.JDateChooser dato1Criterio5;
    private com.toedter.calendar.JDateChooser dato2Criterio5;
    private javax.swing.JTextField datoCriterio1;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCB3;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JCheckBox jCBCriterio4;
    private javax.swing.JCheckBox jCBCriterio5;
    private javax.swing.JCheckBox jCBCriterio6;
    private javax.swing.JCheckBox jCBCriterio7;
    private javax.swing.JLabel jLCriterio2;
    private javax.swing.JLabel jLCriterio3;
    private javax.swing.JLabel jLCriterio4;
    private javax.swing.JLabel jLCriterio6;
    private javax.swing.JLabel jLEstatico1Criterio5;
    private javax.swing.JLabel jLEstatico2Criterio5;
    private javax.swing.JLabel jLEstaticoCriterio2;
    private javax.swing.JLabel jLEstaticoCriterio3;
    private javax.swing.JLabel jLEstaticoCriterio4;
    private javax.swing.JLabel jLEstaticoCriterio6;
    private javax.swing.JLabel jLObjetoSeleccionado;
    private javax.swing.JLabel jLStaticObjetoSeleccionado;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
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
        jLObjetoSeleccionado.setText(this.unObjetoSeleccionado.getId()+", etiqueta asociada: "+ this.unObjetoSeleccionado.getEtiqueta());
        
    }
    private void habilitarBotones(){
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
    }

    @Override
    public void actualizarUnObjeto(Object unObjeto) {
        if (unObjeto instanceof OrdenDeProduccion){
            this.unaOrdenDeProduccionSeleccionada = (OrdenDeProduccion) unObjeto;
            actualizarOrdenDeProduccion();
        }
        if (unObjeto instanceof OrdenDeCompra){
            this.unaOrdenDeCompraSeleccionada = (OrdenDeCompra) unObjeto;
            actualizarOrdenDeCompra();
        }
        if (unObjeto instanceof Proveedor){
            this.unProveedorSeleccionado = (Proveedor) unObjeto;
            actualizarProveedor();   
        }
        
        if (unObjeto instanceof Equipamiento){
            this.unEquipamientoSeleccionado = (Equipamiento) unObjeto;
            actualizarEquipamiento();   
        }
    }

    private void actualizarOrdenDeProduccion() {
        jLEstaticoCriterio2.setEnabled(true);
        jLCriterio2.setEnabled(true);
        jLCriterio2.setText(""+this.unaOrdenDeProduccionSeleccionada.getId());
    }

    private void actualizarOrdenDeCompra() {
        jLEstaticoCriterio3.setEnabled(true);
        jLCriterio3.setEnabled(true);
        jLCriterio3.setText(""+this.unaOrdenDeCompraSeleccionada.getId());
    }


    private void actualizarProveedor() {
        jLEstaticoCriterio4.setEnabled(true);
        jLCriterio4.setEnabled(true);
        jLCriterio4.setText(unProveedorSeleccionado.getRazonSocial());
    }

    private void actualizarEquipamiento() {
        jLEstaticoCriterio6.setEnabled(true);
        jLCriterio6.setEnabled(true);
        jLCriterio6.setText(unEquipamientoSeleccionado.getNombre());
    }

}
