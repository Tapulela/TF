/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import InterfazGrafica.Busqueda.BuscarEstacionamiento;
import InterfazGrafica.Busqueda.BuscarEquipamiento;
import InterfazGrafica.Busqueda.BuscarLote;
import InterfazGrafica.Busqueda.BuscarMolienda;
import InterfazGrafica.Busqueda.BuscarOrdenDeProduccion;
import LogicaDeNegocio.DetalleTransformacion;
import LogicaDeNegocio.Estacionamiento;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.Lote;
import LogicaDeNegocio.Molienda;
import LogicaDeNegocio.Molino;
import LogicaDeNegocio.OrdenDeProduccion;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Proveedor;
import LogicaDeNegocio.Validaciones;
import Persistencia.ExcepcionPersistencia;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author usuario
 */
public class GestionMoliendas extends javax.swing.JFrame implements TransferenciaInstancias {
    
    /**
     * Creates new form ABMProveedor
     */
    private Organizacion organizacion;
    private JFrame ventanaAnterior;
    private Molienda unaMoliendaSeleccionada;
    private Lote unLoteSeleccionado;
    private String trayectoriaActual;
    private String operacionActual;
    
    private OrdenDeProduccion unaOrdenProduccionSeleccionada;
    private Molino unMolino;
    private DetalleTransformacion unDetalle;
    
    private final String textoAlta = "Registrar una molienda";
    private final String textoBaja = "anular una molienda";
    private final String textoModificacion = "Modificar un CONCEPTO";
    
    
    private ArrayList detallesAsociados = new ArrayList();
    private ArrayList lotesAsociados = new ArrayList();
    
    private Lote loteSeleccionadoDeTabla1 = null;
    
    public void setUnaMoliendaSeleccionada(Molienda unaMoliendaSeleccionada) {
        this.unaMoliendaSeleccionada = unaMoliendaSeleccionada;
    }
    public GestionMoliendas() {
        initComponents();
    }



    public GestionMoliendas(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior) {
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.organizacion = organizacion;
        this.getContentPane().setBackground(ParametrosDeInterfaz.colorFondo);
        setIconImage(new ImageIcon(getClass().getResource(ParametrosDeInterfaz.rutaIcono)).getImage());
        
        trayectoriaActual = trayectoriaAnterior+" - Gestión de moliendas";
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Gestión de moliendas", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
        
        

        this.setVisible(true); 
        jBBuscar.setVisible(false);
        
        jTable1.setRowHeight(30);
        
        this.ventanaAnterior = ventanaAnterior;
        habilitarCamposIniciales();
        
        UtilidadesInterfazGrafica.establecerAlineacionDeTabla(jTable1, SwingConstants.CENTER);
        ParametrosDeInterfaz.configurarVentana(this);
        this.detallesAsociados = new ArrayList();
        
        jTFCampo1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                actualizarPeso();
            }
            public void removeUpdate(DocumentEvent e) {
                actualizarPeso();
            }
            public void insertUpdate(DocumentEvent e) {
                actualizarPeso();
            }

            public void actualizarPeso() {
                Float pesoAsociado = null;
                if (Validaciones.esUnNumeroEnteroValido(jTFCampo1.getText()) && unLoteSeleccionado != null){
                        int cantidadBolsasIngresada = Integer.parseInt(jTFCampo1.getText());
                        pesoAsociado = (Math.min(cantidadBolsasIngresada*unLoteSeleccionado.obtenerRazonKgBolsa(), unLoteSeleccionado.getCantidadDisponibleParaMolerKg()));
                        
                        jLStaticEtiqueta18.setText(""+String.format("%.2f", pesoAsociado)+" Kg(s).");
                    
                    
                }else{
                    jLStaticEtiqueta18.setText("");
                }
            }
        });
        FiltroTextosSoloNumeros filtroTexto = new FiltroTextosSoloNumeros(){
            @Override
            public void actualizarPeso(){
                /*Float pesoAsociado = null;
                if (Validaciones.esUnNumeroEnteroValido(jTFCampo1.getText())){
                    pesoAsociado = (unLoteSeleccionado.getCantidad()/unLoteSeleccionado.getCantidadUnidadesDeTransporte())*Integer.parseInt(jTFCampo1.getText());
                    jLStaticEtiqueta18.setText(""+pesoAsociado);
                }else{
                    jLStaticEtiqueta18.setText("");
                }*/
                     
            }
        };
        ((PlainDocument) jTFCampo1.getDocument()).setDocumentFilter(filtroTexto);
    }
    private void organizarElementos(){
        this.deshabilitarTodo();
        switch((String)jCBOperacion.getSelectedItem()){
            case "Registrar":
                prepararAlta();
                break;
            case "Anular":
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

        jBConcretarAccion = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jCBOperacion = new javax.swing.JComboBox<>();
        jBBuscar = new javax.swing.JButton();
        cabeceraDeVentana = new InterfazGrafica.CabeceraDeVentana();
        jLStaticEtiqueta9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLStaticEtiqueta1 = new javax.swing.JLabel();
        jLStaticCalendar1 = new javax.swing.JLabel();
        jC1 = new com.toedter.calendar.JDateChooser();
        jLOperacionSeleccionada = new javax.swing.JLabel();
        jBBuscarMolino = new javax.swing.JButton();
        jLStaticEtiqueta2 = new javax.swing.JLabel();
        jLStaticEtiqueta7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLStaticEtiqueta3 = new javax.swing.JLabel();
        jLStaticEtiqueta4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLStaticEtiqueta6 = new javax.swing.JLabel();
        jLStaticEtiqueta10 = new javax.swing.JLabel();
        jBBuscarLote = new javax.swing.JButton();
        jLStaticEtiqueta11 = new javax.swing.JLabel();
        jLStaticEtiqueta12 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLStaticEtiqueta13 = new javax.swing.JLabel();
        jLStaticEtiqueta14 = new javax.swing.JLabel();
        jLStaticEtiqueta15 = new javax.swing.JLabel();
        jLStaticEtiqueta16 = new javax.swing.JLabel();
        jLStaticEtiqueta17 = new javax.swing.JLabel();
        jLStaticEtiqueta18 = new javax.swing.JLabel();
        jTFCampo1 = new javax.swing.JTextField();
        jLStaticEtiqueta20 = new javax.swing.JLabel();
        jLStaticEtiqueta21 = new javax.swing.JLabel();
        jLStaticEtiqueta22 = new javax.swing.JLabel();
        jBAgregarDetalle = new javax.swing.JButton();
        jBRemoverDetalle = new javax.swing.JButton();
        jLStaticEtiqueta8 = new javax.swing.JLabel();
        jBRemover = new javax.swing.JButton();
        jLStaticEtiqueta5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLStaticEtiqueta19 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jBBuscarOrdenProduccion = new javax.swing.JButton();
        jLStaticEtiqueta23 = new javax.swing.JLabel();
        jLStaticEtiqueta24 = new javax.swing.JLabel();
        jLOrdenProduccionSeleccionada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));

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
        jCBOperacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Registrar", "Anular" }));
        jCBOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBOperacionItemStateChanged(evt);
            }
        });

        jBBuscar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBBuscar.setText("Buscar una molienda");
        jBBuscar.setEnabled(false);
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jLStaticEtiqueta9.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta9.setText("Verifique que los detalles ingresados son los correctos, luego presione el botón aceptar.");
        jLStaticEtiqueta9.setEnabled(false);

        jLStaticEtiqueta1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta1.setText("Molino donde se registrara la molienda");
        jLStaticEtiqueta1.setEnabled(false);

        jLStaticCalendar1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticCalendar1.setText("fecha de origen");
        jLStaticCalendar1.setEnabled(false);

        jC1.setEnabled(false);
        jC1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLOperacionSeleccionada.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLOperacionSeleccionada.setText("Operación sobre un Concepto");
        jLOperacionSeleccionada.setEnabled(false);

        jBBuscarMolino.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBBuscarMolino.setText("Buscar un Molino");
        jBBuscarMolino.setEnabled(false);
        jBBuscarMolino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarMolinoActionPerformed(evt);
            }
        });

        jLStaticEtiqueta2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta2.setText("Seleccione los lotes presentes en este molino que seran utilizados para registrar la molienda");
        jLStaticEtiqueta2.setEnabled(false);

        jLStaticEtiqueta7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta7.setText("Lotes implicados");
        jLStaticEtiqueta7.setEnabled(false);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Etiqueta", "O.P.", "O.C.", "Cantidad de bolsas a utilizar", "Total bolsas disponibles", "Cantidad de Kgs a utilizar", "Total kgs disponibles"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

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
            jTable1.getColumnModel().getColumn(0).setMinWidth(200);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(1).setMinWidth(200);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(2).setMinWidth(200);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(3).setMinWidth(200);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(4).setMinWidth(200);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(5).setMinWidth(200);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(6).setMinWidth(200);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
        }

        jLStaticEtiqueta3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta3.setText("Molino escogido");
        jLStaticEtiqueta3.setEnabled(false);

        jLStaticEtiqueta4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta4.setText("un molino");
        jLStaticEtiqueta4.setEnabled(false);

        jLStaticEtiqueta6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta6.setText("Agregar detalle");
        jLStaticEtiqueta6.setEnabled(false);

        jLStaticEtiqueta10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta10.setText("Lote asociado");
        jLStaticEtiqueta10.setEnabled(false);

        jBBuscarLote.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBBuscarLote.setText("Buscar un lote");
        jBBuscarLote.setEnabled(false);
        jBBuscarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarLoteActionPerformed(evt);
            }
        });

        jLStaticEtiqueta11.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta11.setText("un lote");
        jLStaticEtiqueta11.setEnabled(false);

        jLStaticEtiqueta12.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta12.setText("Lote seleccionado");
        jLStaticEtiqueta12.setEnabled(false);

        jCheckBox1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCheckBox1.setText("Utilizar solo una parte del lote.");
        jCheckBox1.setEnabled(false);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLStaticEtiqueta13.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta13.setText("Cantidad de bolsas a utilizar para la molienda");
        jLStaticEtiqueta13.setEnabled(false);

        jLStaticEtiqueta14.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta14.setText("de un total de");
        jLStaticEtiqueta14.setEnabled(false);

        jLStaticEtiqueta15.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta15.setText("#");
        jLStaticEtiqueta15.setEnabled(false);

        jLStaticEtiqueta16.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta16.setText("kgs. disponibles");
        jLStaticEtiqueta16.setEnabled(false);

        jLStaticEtiqueta17.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta17.setText("Cantidad equivalente en kilogramos:");
        jLStaticEtiqueta17.setEnabled(false);

        jLStaticEtiqueta18.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta18.setText("una cantidad");
        jLStaticEtiqueta18.setEnabled(false);

        jTFCampo1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFCampo1.setEnabled(false);

        jLStaticEtiqueta20.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta20.setText("de un total de");
        jLStaticEtiqueta20.setEnabled(false);

        jLStaticEtiqueta21.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta21.setText("#");
        jLStaticEtiqueta21.setEnabled(false);

        jLStaticEtiqueta22.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta22.setText("bolsas disponibles");
        jLStaticEtiqueta22.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLStaticEtiqueta13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFCampo1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLStaticEtiqueta14)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta15)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLStaticEtiqueta17)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta18)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta20)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta21)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta16)))
                .addContainerGap(542, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLStaticEtiqueta22)
                    .addComponent(jLStaticEtiqueta15)
                    .addComponent(jLStaticEtiqueta14)
                    .addComponent(jTFCampo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLStaticEtiqueta13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLStaticEtiqueta16)
                    .addComponent(jLStaticEtiqueta21)
                    .addComponent(jLStaticEtiqueta20)
                    .addComponent(jLStaticEtiqueta18)
                    .addComponent(jLStaticEtiqueta17))
                .addGap(20, 20, 20))
        );

        jBAgregarDetalle.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBAgregarDetalle.setText("Agregar detalle");
        jBAgregarDetalle.setEnabled(false);
        jBAgregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBAgregarDetalle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jLStaticEtiqueta6)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLStaticEtiqueta10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jBBuscarLote))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLStaticEtiqueta12)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLStaticEtiqueta11)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLStaticEtiqueta6)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBBuscarLote)
                    .addComponent(jLStaticEtiqueta10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLStaticEtiqueta12)
                    .addComponent(jLStaticEtiqueta11))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBAgregarDetalle)
                .addGap(18, 18, 18))
        );

        jBRemoverDetalle.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBRemoverDetalle.setText("Remover Detalle");
        jBRemoverDetalle.setEnabled(false);
        jBRemoverDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRemoverDetalleActionPerformed(evt);
            }
        });

        jLStaticEtiqueta8.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta8.setText("Seleccione un detalle en la tabla y luego presione el botón remover.");
        jLStaticEtiqueta8.setEnabled(false);

        jBRemover.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBRemover.setText("Remover");
        jBRemover.setEnabled(false);
        jBRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRemoverActionPerformed(evt);
            }
        });

        jLStaticEtiqueta5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta5.setText("Sector");
        jLStaticEtiqueta5.setEnabled(false);

        jComboBox2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1", "2" }));
        jComboBox2.setEnabled(false);

        jLStaticEtiqueta19.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta19.setText("Turno");
        jLStaticEtiqueta19.setEnabled(false);

        jComboBox3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Mañana", "Tarde", "Noche" }));
        jComboBox3.setEnabled(false);

        jBBuscarOrdenProduccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBBuscarOrdenProduccion.setText("Buscar una Orden de Producción");
        jBBuscarOrdenProduccion.setEnabled(false);
        jBBuscarOrdenProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarOrdenProduccionActionPerformed(evt);
            }
        });

        jLStaticEtiqueta23.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta23.setText("Orden de Produccion Asociada");
        jLStaticEtiqueta23.setEnabled(false);

        jLStaticEtiqueta24.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta24.setText("Orden de Producción seleccionada:");
        jLStaticEtiqueta24.setEnabled(false);

        jLOrdenProduccionSeleccionada.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLOrdenProduccionSeleccionada.setText("          ");
        jLOrdenProduccionSeleccionada.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLStaticEtiqueta7)
                            .addComponent(jLOperacionSeleccionada)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLStaticEtiqueta1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBBuscarMolino))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLStaticEtiqueta3)
                                .addGap(18, 18, 18)
                                .addComponent(jLStaticEtiqueta4)
                                .addGap(18, 18, 18)
                                .addComponent(jLStaticEtiqueta5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLStaticEtiqueta19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLStaticEtiqueta2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBRemoverDetalle)
                                .addGap(18, 18, 18)
                                .addComponent(jLStaticEtiqueta8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBRemover))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLStaticCalendar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jC1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLStaticEtiqueta23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBBuscarOrdenProduccion)))
                        .addGap(0, 512, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLStaticEtiqueta24)
                        .addGap(18, 18, 18)
                        .addComponent(jLOrdenProduccionSeleccionada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLOperacionSeleccionada)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jC1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLStaticCalendar1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLStaticEtiqueta23)
                    .addComponent(jBBuscarOrdenProduccion))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLStaticEtiqueta24)
                    .addComponent(jLOrdenProduccionSeleccionada))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLStaticEtiqueta1)
                    .addComponent(jBBuscarMolino))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLStaticEtiqueta19)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLStaticEtiqueta5)
                    .addComponent(jLStaticEtiqueta4)
                    .addComponent(jLStaticEtiqueta3))
                .addGap(18, 18, 18)
                .addComponent(jLStaticEtiqueta2)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLStaticEtiqueta7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBRemover)
                    .addComponent(jLStaticEtiqueta8)
                    .addComponent(jBRemoverDetalle))
                .addGap(18, 18, 18))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.DEFAULT_SIZE, 1610, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBBuscar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBConcretarAccion)
                .addGap(18, 18, 18)
                .addComponent(jBCancelar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLStaticEtiqueta9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jCBOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jLStaticEtiqueta9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCancelar)
                    .addComponent(jBConcretarAccion))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jBConcretarAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConcretarAccionActionPerformed
       
        try {
            switch ((String)jCBOperacion.getSelectedItem()){
                case "Registrar":
                    this.organizacion.registrarMolienda(detallesAsociados, unMolino, jC1.getCalendar(), (String)jComboBox2.getSelectedItem(), (String) jComboBox3.getSelectedItem(), unaOrdenProduccionSeleccionada);
                    break;
                case "Anular":
                    this.organizacion.anularMolienda(this.unaMoliendaSeleccionada);
                    break;
                case "Modificacion":
                    //Se va a modificar
                    break;
            }
            JOptionPane.showMessageDialog(null, "Operación realizada con exito.");
            deshabilitarTodo();
            limpiarCampos();
            habilitarCamposIniciales();
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la Base de datos: "+ ex.getMessage());
        } catch (ExcepcionPersistencia ex) {
            JOptionPane.showMessageDialog(null, "Error en la persistencia: "+ ex.getMessage());
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

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        BuscarMolienda unaVentana = new BuscarMolienda(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBBuscarMolinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarMolinoActionPerformed
        if (this.unaOrdenProduccionSeleccionada == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una orden de producción para poder buscar un molino.");
            deshabilitarSeleccionMolino();
            return;
        }
        BuscarEquipamiento unaVentana = new BuscarEquipamiento(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_jBBuscarMolinoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (!jTable1.isEnabled())
            return;
        this.unDetalle = (DetalleTransformacion) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jBBuscarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarLoteActionPerformed
        BuscarLote unaVentana = new BuscarLote(this.organizacion, this, this.trayectoriaActual, null);
        this.dispose();
    }//GEN-LAST:event_jBBuscarLoteActionPerformed

    private void jBAgregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarDetalleActionPerformed
        try {
            if (unLoteSeleccionado == null)
                return;
            DetalleTransformacion unDetalle;
            if (seUtilizaSoloUnaParteDelLote())
                unDetalle = organizacion.generarDetalleMoliendaEnKg(jTFCampo1.getText(), unLoteSeleccionado);
            else
                unDetalle = organizacion.generarDetalleMoliendaEnKg(""+unLoteSeleccionado.getCantidadUnidadesDisponibleParaMoler(), unLoteSeleccionado);
            this.detallesAsociados.add(unDetalle);
            actualizarTablas();
            jBAgregarDetalle.setEnabled(false);
            jBRemoverDetalle.setEnabled(true);
            habilitarSeleccionDeLotes();
            desHabilitarSeleccionDetalle();
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_jBAgregarDetalleActionPerformed

    private void jBRemoverDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRemoverDetalleActionPerformed
        jLStaticEtiqueta8.setEnabled(true);
        jBRemover.setEnabled(true);
        jBRemoverDetalle.setEnabled(false);
    }//GEN-LAST:event_jBRemoverDetalleActionPerformed

    private void jBRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRemoverActionPerformed
        if (this.unDetalle == null)
            return;
        detallesAsociados.remove(unDetalle);
        actualizarTablas();
        jLStaticEtiqueta8.setEnabled(false);
        jBRemover.setEnabled(false);
    }//GEN-LAST:event_jBRemoverActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED){
            UtilidadesInterfazGrafica.habilitarCamposEditablesContenedorSingular(jPanel3.getComponents());
            UtilidadesInterfazGrafica.habilitarEtiquetasContenedorSingular(jPanel3.getComponents());
        }else{
            UtilidadesInterfazGrafica.deshabilitarCamposEditablesContenedorSingular(jPanel3.getComponents());
            UtilidadesInterfazGrafica.deshabilitarEtiquetasContenedorSingular(jPanel3.getComponents());
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jBBuscarOrdenProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarOrdenProduccionActionPerformed
        BuscarOrdenDeProduccion unaVentana = new BuscarOrdenDeProduccion(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_jBBuscarOrdenProduccionActionPerformed

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
            java.util.logging.Logger.getLogger(GestionMoliendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionMoliendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionMoliendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionMoliendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new GestionMoliendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private javax.swing.JButton jBAgregarDetalle;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBBuscarLote;
    private javax.swing.JButton jBBuscarMolino;
    private javax.swing.JButton jBBuscarOrdenProduccion;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JButton jBRemover;
    private javax.swing.JButton jBRemoverDetalle;
    private com.toedter.calendar.JDateChooser jC1;
    private javax.swing.JComboBox<String> jCBOperacion;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLOperacionSeleccionada;
    private javax.swing.JLabel jLOrdenProduccionSeleccionada;
    private javax.swing.JLabel jLStaticCalendar1;
    private javax.swing.JLabel jLStaticEtiqueta1;
    private javax.swing.JLabel jLStaticEtiqueta10;
    private javax.swing.JLabel jLStaticEtiqueta11;
    private javax.swing.JLabel jLStaticEtiqueta12;
    private javax.swing.JLabel jLStaticEtiqueta13;
    private javax.swing.JLabel jLStaticEtiqueta14;
    private javax.swing.JLabel jLStaticEtiqueta15;
    private javax.swing.JLabel jLStaticEtiqueta16;
    private javax.swing.JLabel jLStaticEtiqueta17;
    private javax.swing.JLabel jLStaticEtiqueta18;
    private javax.swing.JLabel jLStaticEtiqueta19;
    private javax.swing.JLabel jLStaticEtiqueta2;
    private javax.swing.JLabel jLStaticEtiqueta20;
    private javax.swing.JLabel jLStaticEtiqueta21;
    private javax.swing.JLabel jLStaticEtiqueta22;
    private javax.swing.JLabel jLStaticEtiqueta23;
    private javax.swing.JLabel jLStaticEtiqueta24;
    private javax.swing.JLabel jLStaticEtiqueta3;
    private javax.swing.JLabel jLStaticEtiqueta4;
    private javax.swing.JLabel jLStaticEtiqueta5;
    private javax.swing.JLabel jLStaticEtiqueta6;
    private javax.swing.JLabel jLStaticEtiqueta7;
    private javax.swing.JLabel jLStaticEtiqueta8;
    private javax.swing.JLabel jLStaticEtiqueta9;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFCampo1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void deshabilitarTodo(){
        //TAMBIEN SE HACEN INVISIBLE LOS ELEMENTOS QUE SEAN NECESARIOS
        
        
        jBBuscar.setVisible(false);
        jBBuscarLote.setEnabled(false);
        jBBuscarMolino.setEnabled(false);
        
        
        UtilidadesInterfazGrafica.deshabilitarCamposEditablesContenedor(jPanel1.getComponents());
        UtilidadesInterfazGrafica.deshabilitarEtiquetasContenedor(jPanel1.getComponents());
        
        /*jTFCampo1.setEnabled(false);
        
        
        jLStaticEtiqueta1.setEnabled(false);
        jLStaticEtiqueta2.setEnabled(false);
        jLStaticEtiqueta3.setEnabled(false);
        jLStaticEtiqueta4.setEnabled(false);
        jLStaticEtiqueta7.setEnabled(false);
        jLStaticEtiqueta8.setEnabled(false);
        jLStaticEtiqueta9.setEnabled(false);
        
        jLStaticCalendar1.setEnabled(false);
        
        jC1.setEnabled(false);
        
        jCBOperacion.setEnabled(false);
        jBBuscar.setEnabled(false);
        */
        desHabilitarTablas();
        
        jBConcretarAccion.setEnabled(false);
        jBCancelar.setEnabled(false);
        
    }
    
    private void limpiarCampos() {
                this.operacionActual = "";
                jLStaticEtiqueta4.setText("");
                jTFCampo1.setText("");
                jComboBox2.setSelectedItem("Seleccionar");
                jComboBox3.setSelectedItem("Seleccionar");
                this.unaMoliendaSeleccionada = null;
                
                this.loteSeleccionadoDeTabla1 = null;
                this.detallesAsociados = new ArrayList();
                habilitarTablas();
                limpiarTablas();
                desHabilitarTablas();
    }

    @Override
    public void actualizarUnObjeto(Object unObjeto) {
        if (unObjeto instanceof OrdenDeProduccion){
            OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
            this.unaOrdenProduccionSeleccionada = unaOrdenProduccion;
            exhibirOrdenProduccion();
            habilitarSeleccionMolino();
        }
        if (unObjeto instanceof Molino){
            this.unMolino = (Molino) unObjeto;
            exhibirMolinoSeleccionado();
            habilitarSeleccionDeSectorYTurno();
            habilitarSeleccionDeLotes();
            habilitarTablas();
            actualizarTablas();
        }
        if (unObjeto instanceof Molienda){
            this.unaMoliendaSeleccionada = (Molienda) unObjeto;
            this.detallesAsociados = this.unaMoliendaSeleccionada.getDetallesAsociados();
            habilitarTablas();
            actualizarTablas();
            //desHabilitarTablas();
            exhibirUnaMolienda();
        }
        
        if (unObjeto instanceof Lote){
            this.unLoteSeleccionado = (Lote) unObjeto;
            exhibirUnLote();
            habilitarInsercionDetalle();
        }
        
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
    }

    private void prepararAlta() {
        this.operacionActual = "Alta";
        jBConcretarAccion.setText("Registrar una molienda");
        jLStaticEtiqueta9.setText("Verifique que los lotes ingresados son los correctos, luego presione el botón "+jBConcretarAccion.getText()+".");
        jLOperacionSeleccionada.setText(this.textoAlta);
        
        
        habilitarSeleccionOrdenProduccion();
        jLOperacionSeleccionada.setEnabled(true);
        jLStaticCalendar1.setEnabled(true);
        jC1.setCalendar(Calendar.getInstance());
        
        UtilidadesInterfazGrafica.habilitarCamposEditablesContenedorSingular(jPanel1.getComponents());
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
        
        jC1.setEnabled(false);
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
        
        
        
    }
    
    private void prepararBaja() {
        this.operacionActual = "Baja";
        jBBuscar.setEnabled(true);
        jBBuscar.setVisible(true);
        
        jLOperacionSeleccionada.setText(textoBaja);
        jLOperacionSeleccionada.setEnabled(true);
        
        
        
        jLStaticEtiqueta1.setEnabled(true);
        jLStaticEtiqueta3.setEnabled(true);
        jLStaticEtiqueta4.setEnabled(true);
        jLStaticEtiqueta7.setEnabled(true);
        jLStaticEtiqueta8.setEnabled(true);
        jLStaticEtiqueta9.setEnabled(true);
        
        jLStaticCalendar1.setEnabled(true);
        
        jBCancelar.setEnabled(true);
        if (this.unaMoliendaSeleccionada == null)
            return;
        jBConcretarAccion.setEnabled(true);
        jBConcretarAccion.setText("Anular una molienda");
        jLStaticEtiqueta9.setText("Verifique que los lotes ingresados son los correctos, luego presione el botón "+jBConcretarAccion.getText()+".");

    }

    private void prepararModificacion() {
        this.operacionActual = "Modificacion";
        jLOperacionSeleccionada.setText(this.textoModificacion);
        jLOperacionSeleccionada.setEnabled(true);

        jBBuscar.setVisible(true);
        jBBuscar.setEnabled(true);
        jBCancelar.setEnabled(true);
        
        
        if (unaMoliendaSeleccionada == null)
            return;
        
        /*
        jTFCampo1.setEnabled(true); //SI ALGUN CAMPO NO SE PUDIERA MODIFICAR, (POR EJEMPLO, FECHA DE CREACION, CONCEPTO ASOCIADO, ETC. BORRAR ESTAS LINEAS)
        
        
        
        jLStaticEtiqueta1.setEnabled(true);
        jLStaticEtiqueta2.setEnabled(true);
        jLStaticEtiqueta3.setEnabled(true);
        jLStaticEtiqueta4.setEnabled(true);
        jLStaticEtiqueta7.setEnabled(true);
        jLStaticEtiqueta8.setEnabled(true);
        jLStaticEtiqueta9.setEnabled(true);
        
        jLStaticCalendar1.setEnabled(true);
        
        jC1.setEnabled(true);
        
        jBConcretarAccion.setEnabled(true);
        jBConcretarAccion.setText("Guardar cambios");*/

    }

    public String getOperacionActual() {
        return operacionActual;
    }

    private void habilitarCamposIniciales() {
        jLabel12.setEnabled(true);
        jCBOperacion.setEnabled(true);
        this.jCBOperacion.setSelectedItem("Seleccionar");
    }

    private void habilitarSeleccionDeLotes() {
        UtilidadesInterfazGrafica.habilitarCamposEditablesContenedorSingular(jPanel2.getComponents());
        UtilidadesInterfazGrafica.habilitarEtiquetasContenedorSingular(jPanel2.getComponents());
        UtilidadesInterfazGrafica.deshabilitarEtiquetasContenedorSingular(jPanel3.getComponents());
        jBBuscarLote.setEnabled(true);
        jCheckBox1.setEnabled(false);
        jBAgregarDetalle.setEnabled(false);
        jLStaticEtiqueta2.setEnabled(true);
    }

    public ArrayList getDetallesAsociados() {
        return detallesAsociados;
    }
    
    


    private void actualizarTablas() {
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
        Iterator recorredorLotesTabla1 = this.detallesAsociados.iterator();
        while (recorredorLotesTabla1.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) recorredorLotesTabla1.next();
            ((DefaultTableModel)this.jTable1.getModel()).addRow(unDetalle.devolverVector());
        }
    }

    private void exhibirMolinoSeleccionado() {
        jLStaticEtiqueta3.setEnabled(true);
        jLStaticEtiqueta4.setEnabled(true);
        jLStaticEtiqueta4.setText(this.unMolino.getNombre());
    }

    private void limpiarTablas() {
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
    }

    private void exhibirUnaMolienda() {
        UtilidadesInterfazGrafica.habilitarEtiquetasContenedor(jPanel1.getComponents());
        jC1.setCalendar(this.unaMoliendaSeleccionada.getFechaOrigenC());
    }

    private void desHabilitarTablas() {
        jTable1.setEnabled(false);
    }
    private void habilitarTablas() {
        jTable1.setEnabled(true);
    }    

    private void habilitarInsercionDetalle() {
        UtilidadesInterfazGrafica.habilitarCamposEditablesContenedorSingular(jPanel2.getComponents());
        UtilidadesInterfazGrafica.habilitarEtiquetasContenedorSingular(jPanel2.getComponents());
        jBAgregarDetalle.setEnabled(true);
        habilitarTablas();
    }

    private void exhibirUnLote() {
        
        if (unLoteSeleccionado == null)
            return;
        jLStaticEtiqueta12.setEnabled(true);
        jLStaticEtiqueta11.setEnabled(true);
        jLStaticEtiqueta11.setText(unLoteSeleccionado.getEtiqueta());
        jTFCampo1.setText(""+unLoteSeleccionado.getCantidadUnidadesDisponibleParaMoler());
        jLStaticEtiqueta15.setText(""+unLoteSeleccionado.getCantidadUnidadesDisponibleParaMoler());
        //jLStaticEtiqueta21.setText(""+unLoteSeleccionado.getCantidadDisponibleParaMolerKg());
        jLStaticEtiqueta21.setText(""+String.format("%.2f", unLoteSeleccionado.getCantidadDisponibleParaMolerKg())+" Kg(s).");
    }


    private void habilitarSeleccionDeSectorYTurno() {
        jLStaticEtiqueta5.setEnabled(true);
        jComboBox2.setEnabled(true);
        jLStaticEtiqueta19.setEnabled(true);
        jComboBox3.setEnabled(true);
    }

    private void desHabilitarSeleccionDetalle() {
        UtilidadesInterfazGrafica.deshabilitarCamposEditablesContenedorSingular(jPanel3.getComponents());
        UtilidadesInterfazGrafica.deshabilitarEtiquetasContenedorSingular(jPanel3.getComponents());
    }

    private void habilitarSeleccionMolino() {
        jLStaticEtiqueta1.setEnabled(true);
        jBBuscarMolino.setEnabled(true);
    }

    private void habilitarSeleccionOrdenProduccion() {
        jBBuscarOrdenProduccion.setEnabled(true);
        jLStaticEtiqueta23.setEnabled(true);
    }

    private void deshabilitarSeleccionMolino() {
        jLStaticEtiqueta1.setEnabled(false);
        jBBuscarMolino.setEnabled(false);
    }

    public OrdenDeProduccion getUnaOrdenProduccionSeleccionada() {
        return unaOrdenProduccionSeleccionada;
    }

    private void exhibirOrdenProduccion() {
        if (this.unaOrdenProduccionSeleccionada == null)
            return;
        jLStaticEtiqueta24.setEnabled(true);
        jLOrdenProduccionSeleccionada.setEnabled(true);
        jLOrdenProduccionSeleccionada.setText(""+unaOrdenProduccionSeleccionada.getId());
    }

    public Molino getUnMolino() {
        return unMolino;
    }

    private boolean seUtilizaSoloUnaParteDelLote() {
        return jCheckBox1.isSelected();
    }
    
    

}
