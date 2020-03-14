/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import InterfazGrafica.Busqueda.BuscarEgreso;
import InterfazGrafica.Busqueda.BuscarEstacionamiento;
import InterfazGrafica.Busqueda.BuscarEquipamiento;
import InterfazGrafica.Busqueda.BuscarLote;
import InterfazGrafica.Busqueda.BuscarMolienda;
import InterfazGrafica.Busqueda.BuscarOrdenDeProduccion;
import LogicaDeNegocio.DetalleTransformacion;
import LogicaDeNegocio.Egreso;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author usuario
 */
public class GestionEgresos extends javax.swing.JFrame implements TransferenciaInstancias {
    
    /**
     * Creates new form ABMProveedor
     */
    private Organizacion organizacion;
    private JFrame ventanaAnterior;
    
    private Molienda unaMoliendaSeleccionada;
    private String trayectoriaActual;
    private String operacionActual;
    private Egreso unEgresoSeleccionado;
    
    private OrdenDeProduccion unaOrdenProduccionSeleccionada;
    private Molino unMolino;
    
    private final String textoAlta = "Registrar un egreso";
    private final String textoBaja = "anular un egreso";
    private final String textoModificacion = "Modificar un CONCEPTO";
    
    
    private ArrayList detallesAsociados = new ArrayList();
    
    public void setUnaMoliendaSeleccionada(Molienda unaMoliendaSeleccionada) {
        this.unaMoliendaSeleccionada = unaMoliendaSeleccionada;
    }
    public GestionEgresos() {
        initComponents();
    }



    public GestionEgresos(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior) {
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.organizacion = organizacion;
        this.getContentPane().setBackground(ParametrosDeInterfaz.colorFondo);
        setIconImage(new ImageIcon(getClass().getResource(ParametrosDeInterfaz.rutaIcono)).getImage());
        
        trayectoriaActual = trayectoriaAnterior+" - Gestión de egresos";
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Gestión de egresos", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
        
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
                if (Validaciones.esUnNumeroFraccionarioValido(jTFCampo1.getText())){
                    Float pesoIngresado = 0f;
                    try {
                        pesoIngresado = Organizacion.convertirAFlotante(jTFCampo1.getText(), "Peso a egresar");
                    } catch (ExcepcionCargaParametros ex) {System.err.println(ex.getMessage());}
                        pesoAsociado = (Math.min(unaMoliendaSeleccionada.getPesoDisponibleAEgresarKg(), pesoIngresado));
                        jLStaticEtiqueta16.setText(UtilidadesInterfazGrafica.formatearFlotante(pesoAsociado));
                }else{
                    jLStaticEtiqueta16.setText("0");
                }
            }
        });

        this.setVisible(true); 
        jBBuscar.setVisible(false);
        
        this.ventanaAnterior = ventanaAnterior;
        habilitarCamposIniciales();
        
        ParametrosDeInterfaz.configurarVentana(this);
        this.detallesAsociados = new ArrayList();
        
       
        
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
        jLStaticCalendar1 = new javax.swing.JLabel();
        jC1 = new com.toedter.calendar.JDateChooser();
        jLOperacionSeleccionada = new javax.swing.JLabel();
        jLStaticEtiqueta2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLStaticEtiqueta10 = new javax.swing.JLabel();
        jBBuscarLote = new javax.swing.JButton();
        jLStaticEtiqueta11 = new javax.swing.JLabel();
        jLStaticEtiqueta12 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLStaticEtiqueta13 = new javax.swing.JLabel();
        jLStaticEtiqueta14 = new javax.swing.JLabel();
        jLStaticEtiqueta15 = new javax.swing.JLabel();
        jLStaticEtiqueta22 = new javax.swing.JLabel();
        jLStaticEtiqueta25 = new javax.swing.JLabel();
        jLStaticEtiqueta16 = new javax.swing.JLabel();
        jLStaticEtiqueta26 = new javax.swing.JLabel();
        NumberFormat formateadorCampoFloat = NumberFormat.getInstance();
        formateadorCampoFloat.setGroupingUsed(true);
        formateadorCampoFloat.setMinimumFractionDigits(3);
        formateadorCampoFloat.setParseIntegerOnly(false);
        jTFCampo1 = new javax.swing.JFormattedTextField(formateadorCampoFloat);
        jBBuscarOrdenProduccion = new javax.swing.JButton();
        jLStaticEtiqueta23 = new javax.swing.JLabel();
        jLStaticEtiqueta24 = new javax.swing.JLabel();
        jLOrdenProduccionSeleccionada = new javax.swing.JLabel();
        jBBuscarMolino = new javax.swing.JButton();
        jLStaticEtiqueta1 = new javax.swing.JLabel();
        jLStaticEtiqueta3 = new javax.swing.JLabel();
        jLStaticEtiqueta4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTAComentario = new javax.swing.JTextArea();
        jLDescripcion = new javax.swing.JLabel();

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
        jBBuscar.setText("Buscar una egreso");
        jBBuscar.setEnabled(false);
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jLStaticEtiqueta9.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta9.setText("Verifique que los datos ingresados son los correctos, luego presione el botón aceptar.");
        jLStaticEtiqueta9.setEnabled(false);

        jLStaticCalendar1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticCalendar1.setText("fecha de origen");
        jLStaticCalendar1.setEnabled(false);

        jC1.setEnabled(false);
        jC1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLOperacionSeleccionada.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLOperacionSeleccionada.setText("Operación sobre un Concepto");
        jLOperacionSeleccionada.setEnabled(false);

        jLStaticEtiqueta2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta2.setText("Seleccione la molienda sobre la que se va a registrar un egreso");
        jLStaticEtiqueta2.setEnabled(false);

        jLStaticEtiqueta10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta10.setText("Molienda Asociada");
        jLStaticEtiqueta10.setEnabled(false);

        jBBuscarLote.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBBuscarLote.setText("Buscar una molienda");
        jBBuscarLote.setEnabled(false);
        jBBuscarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarLoteActionPerformed(evt);
            }
        });

        jLStaticEtiqueta11.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta11.setText("una molienda");
        jLStaticEtiqueta11.setEnabled(false);

        jLStaticEtiqueta12.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta12.setText("Molienda seleccionada");
        jLStaticEtiqueta12.setEnabled(false);

        jCheckBox1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCheckBox1.setText("Egresar solo una parte de la molienda.");
        jCheckBox1.setEnabled(false);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLStaticEtiqueta13.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta13.setText("Cantidad de kgs a egresar de la molienda");
        jLStaticEtiqueta13.setEnabled(false);

        jLStaticEtiqueta14.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta14.setText("de un total de");
        jLStaticEtiqueta14.setEnabled(false);

        jLStaticEtiqueta15.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta15.setText("#");
        jLStaticEtiqueta15.setEnabled(false);

        jLStaticEtiqueta22.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta22.setText("kgs. disponibles");
        jLStaticEtiqueta22.setEnabled(false);

        jLStaticEtiqueta25.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta25.setText("Total a egresar");
        jLStaticEtiqueta25.setEnabled(false);

        jLStaticEtiqueta16.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta16.setText("#");
        jLStaticEtiqueta16.setEnabled(false);

        jLStaticEtiqueta26.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta26.setText("kgs.");
        jLStaticEtiqueta26.setEnabled(false);

        jTFCampo1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLStaticEtiqueta25)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta16)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLStaticEtiqueta13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCampo1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLStaticEtiqueta14)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta15)
                        .addGap(18, 18, 18)
                        .addComponent(jLStaticEtiqueta22)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLStaticEtiqueta22)
                    .addComponent(jLStaticEtiqueta15)
                    .addComponent(jLStaticEtiqueta14)
                    .addComponent(jLStaticEtiqueta13)
                    .addComponent(jTFCampo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLStaticEtiqueta25)
                    .addComponent(jLStaticEtiqueta16)
                    .addComponent(jLStaticEtiqueta26))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLStaticEtiqueta10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBBuscarLote))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLStaticEtiqueta12)
                                .addGap(18, 18, 18)
                                .addComponent(jLStaticEtiqueta11)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
                .addGap(18, 18, 18))
        );

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

        jBBuscarMolino.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jBBuscarMolino.setText("Buscar un Molino");
        jBBuscarMolino.setEnabled(false);
        jBBuscarMolino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarMolinoActionPerformed(evt);
            }
        });

        jLStaticEtiqueta1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta1.setText("Molino donde se registrara la molienda");
        jLStaticEtiqueta1.setEnabled(false);

        jLStaticEtiqueta3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta3.setText("Molino escogido");
        jLStaticEtiqueta3.setEnabled(false);

        jLStaticEtiqueta4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLStaticEtiqueta4.setText("un molino");
        jLStaticEtiqueta4.setEnabled(false);

        jTAComentario.setColumns(20);
        jTAComentario.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTAComentario.setRows(5);
        jScrollPane3.setViewportView(jTAComentario);

        jLDescripcion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLDescripcion.setText("Comentario");
        jLDescripcion.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLOperacionSeleccionada)
                            .addComponent(jLStaticEtiqueta2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLStaticCalendar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jC1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLStaticEtiqueta1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBBuscarMolino))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLStaticEtiqueta3)
                                .addGap(18, 18, 18)
                                .addComponent(jLStaticEtiqueta4))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLStaticEtiqueta24)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLOrdenProduccionSeleccionada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLStaticEtiqueta23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jBBuscarOrdenProduccion)))
                            .addComponent(jLDescripcion))
                        .addGap(0, 395, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
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
                    .addComponent(jLStaticEtiqueta4)
                    .addComponent(jLStaticEtiqueta3))
                .addGap(18, 18, 18)
                .addComponent(jLStaticEtiqueta2)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLDescripcion)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cabeceraDeVentana, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1165, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBBuscar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
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
                    if (jCheckBox1.isSelected())
                        this.organizacion.registrarEgreso("", jTAComentario.getText().toUpperCase(), Lote.UNIDAD_MEDIDA_Tranporte, "0", Lote.UNIDAD_MEDIDA_KILOGRAMO, jTFCampo1.getText(), this.unaMoliendaSeleccionada);
                    else
                        this.organizacion.registrarEgreso("", jTAComentario.getText().toUpperCase(), Lote.UNIDAD_MEDIDA_Tranporte, "0", Lote.UNIDAD_MEDIDA_KILOGRAMO, ""+UtilidadesInterfazGrafica.formatearFlotante(this.unaMoliendaSeleccionada.getPesoDisponibleAEgresarKg()), this.unaMoliendaSeleccionada);
                    break;
                case "Anular":
                    this.organizacion.anularEgreso(this.unEgresoSeleccionado);
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
        BuscarEgreso unaVentana = new BuscarEgreso(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBBuscarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarLoteActionPerformed
        if (this.unaOrdenProduccionSeleccionada == null || this.unMolino == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una orden de producción y un molino para poder buscar una molienda.");
            deshabilitarSeleccionMolienda();
            return;
        }
        BuscarMolienda unaVentana = new BuscarMolienda(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_jBBuscarLoteActionPerformed

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

    private void jBBuscarMolinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarMolinoActionPerformed
        if (this.unaOrdenProduccionSeleccionada == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una orden de producción para poder buscar un molino.");
            deshabilitarSeleccionMolino();
            return;
        }
        BuscarEquipamiento unaVentana = new BuscarEquipamiento(this.organizacion, this, this.trayectoriaActual);
        this.dispose();
    }//GEN-LAST:event_jBBuscarMolinoActionPerformed

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
            java.util.logging.Logger.getLogger(GestionEgresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionEgresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionEgresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionEgresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionEgresos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBBuscarLote;
    private javax.swing.JButton jBBuscarMolino;
    private javax.swing.JButton jBBuscarOrdenProduccion;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private com.toedter.calendar.JDateChooser jC1;
    private javax.swing.JComboBox<String> jCBOperacion;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLDescripcion;
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
    private javax.swing.JLabel jLStaticEtiqueta2;
    private javax.swing.JLabel jLStaticEtiqueta22;
    private javax.swing.JLabel jLStaticEtiqueta23;
    private javax.swing.JLabel jLStaticEtiqueta24;
    private javax.swing.JLabel jLStaticEtiqueta25;
    private javax.swing.JLabel jLStaticEtiqueta26;
    private javax.swing.JLabel jLStaticEtiqueta3;
    private javax.swing.JLabel jLStaticEtiqueta4;
    private javax.swing.JLabel jLStaticEtiqueta9;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTAComentario;
    private javax.swing.JFormattedTextField jTFCampo1;
    // End of variables declaration//GEN-END:variables

    private void deshabilitarTodo(){
        //TAMBIEN SE HACEN INVISIBLE LOS ELEMENTOS QUE SEAN NECESARIOS
        
        
        jBBuscar.setVisible(false);
        jBBuscarLote.setEnabled(false);
        
        
        UtilidadesInterfazGrafica.deshabilitarCamposEditablesContenedor(jPanel1.getComponents());
        UtilidadesInterfazGrafica.deshabilitarEtiquetasContenedor(jPanel1.getComponents());
        
        jBConcretarAccion.setEnabled(false);
        jBCancelar.setEnabled(false);
        
    }
    
    private void limpiarCampos() {
                this.operacionActual = "";
                jTFCampo1.setText("");
                this.unaMoliendaSeleccionada = null;
                
                this.detallesAsociados = new ArrayList();
    }

    @Override
    public void actualizarUnObjeto(Object unObjeto) {
        if (unObjeto instanceof Egreso){
            this.unEgresoSeleccionado = (Egreso) unObjeto;
            exhibirEgreso();
            UtilidadesInterfazGrafica.habilitarEtiquetasContenedor(jPanel1.getComponents());
        }
        if (unObjeto instanceof OrdenDeProduccion){
            OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
            this.unaOrdenProduccionSeleccionada = unaOrdenProduccion;
            exhibirOrdenProduccion();
            habilitarSeleccionMolino();
        }
        if (unObjeto instanceof Molino){
            this.unMolino = (Molino) unObjeto;
            exhibirMolinoSeleccionado();
            habilitarSeleccionDeLotes();
        }
        if (unObjeto instanceof Molienda){
            this.unaMoliendaSeleccionada = (Molienda) unObjeto;
            this.detallesAsociados = this.unaMoliendaSeleccionada.getDetallesAsociados();
            exhibirUnaMolienda();
            habilitarSeleccionDetalle();
            habilitarComentario();
        }
        
        
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
    }

    private void prepararAlta() {
        this.operacionActual = "Alta";
        jBConcretarAccion.setText("Registrar un egreso");
        jLStaticEtiqueta9.setText("Verifique que la molienda ingresada es la correcta, luego presione el botón "+jBConcretarAccion.getText()+".");
        jLOperacionSeleccionada.setText(this.textoAlta);
        
        exhibirCamposIniciales();
        
        jLOperacionSeleccionada.setEnabled(true);
        jC1.setCalendar(Calendar.getInstance());
        
        UtilidadesInterfazGrafica.habilitarCamposEditablesContenedorSingular(jPanel1.getComponents());
        
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
        
        jLStaticEtiqueta9.setEnabled(true);
        
        jLStaticCalendar1.setEnabled(true);
        
        jBCancelar.setEnabled(true);
        if (this.unaMoliendaSeleccionada == null)
            return;
        jBConcretarAccion.setEnabled(true);
        jBConcretarAccion.setText("Anular un egreso");
        jLStaticEtiqueta9.setText("Verifique que el egreso ingresado es el correcto, luego presione el botón "+jBConcretarAccion.getText()+".");

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

        jLStaticEtiqueta2.setEnabled(true);
        UtilidadesInterfazGrafica.habilitarCamposEditablesContenedorSingular(jPanel2.getComponents());
        UtilidadesInterfazGrafica.habilitarEtiquetasContenedorSingular(jPanel2.getComponents());
        UtilidadesInterfazGrafica.deshabilitarEtiquetasContenedorSingular(jPanel3.getComponents());
        jBBuscarLote.setEnabled(true);
        jCheckBox1.setEnabled(false);
    }

    public ArrayList getDetallesAsociados() {
        return detallesAsociados;
    }
    
    



    private void exhibirMolinoSeleccionado() {
        if (unMolino == null)
            return;
        jLStaticEtiqueta3.setEnabled(true);
        jLStaticEtiqueta4.setEnabled(true);
        jLStaticEtiqueta4.setText(unMolino.getNombre());
    }


    private void exhibirUnaMolienda() {
        if (this.unaMoliendaSeleccionada == null)
            return;
        jLStaticEtiqueta12.setEnabled(true);
        jLStaticEtiqueta11.setEnabled(true);
        jLStaticEtiqueta11.setText(""+this.unaMoliendaSeleccionada.getId());
        jTFCampo1.setText(UtilidadesInterfazGrafica.formatearFlotante(unaMoliendaSeleccionada.getPesoDisponibleAEgresarKg()));
        jLStaticEtiqueta15.setText(UtilidadesInterfazGrafica.formatearFlotante(unaMoliendaSeleccionada.getPesoDisponibleAEgresarKg()));
    }




    private void exhibirCamposIniciales() {
        jLStaticCalendar1.setEnabled(true);
        jLStaticEtiqueta23.setEnabled(true);
        jBBuscarOrdenProduccion.setEnabled(true);
    }


    private void desHabilitarSeleccionDetalle() {
        UtilidadesInterfazGrafica.deshabilitarCamposEditablesContenedorSingular(jPanel3.getComponents());
        UtilidadesInterfazGrafica.deshabilitarEtiquetasContenedorSingular(jPanel3.getComponents());
    }

    private void deshabilitarSeleccionMolino() {
        jLStaticEtiqueta1.setEnabled(false);
        jBBuscarMolino.setEnabled(false);
        jLStaticEtiqueta3.setEnabled(false);
        jLStaticEtiqueta4.setEnabled(false);
    }

    private void exhibirOrdenProduccion() {
        if (this.unaOrdenProduccionSeleccionada == null)
            return;
        jLStaticEtiqueta24.setEnabled(true);
        jLOrdenProduccionSeleccionada.setEnabled(true);
        jLOrdenProduccionSeleccionada.setText(""+unaOrdenProduccionSeleccionada.getId());
    }

    private void habilitarSeleccionMolino() {
        jLStaticEtiqueta1.setEnabled(true);
        jBBuscarMolino.setEnabled(true);
    }

    public OrdenDeProduccion getUnaOrdenProduccionSeleccionada() {
        return unaOrdenProduccionSeleccionada;
    }

    public Molino getUnMolino() {
        return unMolino;
    }

    private void deshabilitarSeleccionMolienda() {
        jLStaticEtiqueta10.setEnabled(false);
        jBBuscarLote.setEnabled(false);
    }

    private void habilitarSeleccionDetalle() {
        UtilidadesInterfazGrafica.habilitarCamposEditablesContenedorSingular(jPanel2.getComponents());
        UtilidadesInterfazGrafica.habilitarEtiquetasContenedorSingular(jPanel2.getComponents());
        jBConcretarAccion.setEnabled(true);
    }

    private void habilitarComentario() {
        jLDescripcion.setEnabled(true);
        jTAComentario.setEnabled(true);
    }

    private void exhibirEgreso() {
        if (this.unEgresoSeleccionado == null)
            return;
        jLOrdenProduccionSeleccionada.setText(""+this.unEgresoSeleccionado.getMoliendaImplicada().getOrdenDeProduccionAsociada().getId());
        jLStaticEtiqueta4.setText(unEgresoSeleccionado.getMoliendaImplicada().getEquipamientoAsociado().getNombre());
        jTFCampo1.setText(""+Organizacion.convertirUnidadPeso(unEgresoSeleccionado.getUnidadMedidaPeso(), unEgresoSeleccionado.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO));
        jTAComentario.setText(unEgresoSeleccionado.getComentario());
    }
    
}
