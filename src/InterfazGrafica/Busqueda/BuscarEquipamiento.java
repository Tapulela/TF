/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Busqueda;

import InterfazGrafica.ABMEquipamiento;
import InterfazGrafica.GestionAnalisisLaboratorio;
import InterfazGrafica.GestionEgresos;
import InterfazGrafica.GestionEstacionamientos;
import InterfazGrafica.GestionIngresoMP;
import InterfazGrafica.GestionMoliendas;
import InterfazGrafica.GestionMovimientos;
import InterfazGrafica.Inicio;
import InterfazGrafica.Paneles.PanelGestionAnalisisLaboratorio;
import InterfazGrafica.Paneles.PanelGestionEstacionamientos;
import InterfazGrafica.Paneles.PanelGestionIngresos;
import InterfazGrafica.Paneles.PanelGestionMoliendas;
import InterfazGrafica.Paneles.PanelGestionSalidas;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.TransferenciaInstancias;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import LogicaDeNegocio.Bascula;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.Organizacion;
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
public class BuscarEquipamiento extends javax.swing.JFrame {

    private JFrame ventanaAnterior;
    private Organizacion organizacion;
    private Map<String, Boolean> criteriosSeleccionados;
    private Equipamiento unEquipamientoSeleccionado;
    private String trayectoriaActual;
    /**
     * Creates new form BuscarEquipamiento
     */
    
    
    public BuscarEquipamiento() {
        initComponents();
    }

    public BuscarEquipamiento(Organizacion organizacion, JFrame ventanaAnterior, String trayectoriaAnterior) {
        
        this.setUndecorated(true);
        initComponents();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        setIconImage(new ImageIcon(getClass().getResource("../"+ParametrosDeInterfaz.rutaIcono)).getImage());
        this.setVisible(true); 
        this.ventanaAnterior = ventanaAnterior;
        this.ventanaAnterior.setFocusable(false);
        this.organizacion = organizacion;
        this.trayectoriaActual = trayectoriaAnterior + " - Busqueda de Equipamiento";
        cabeceraDeVentana.configurarCabecera(ventanaAnterior, this, "Busqueda de un equipamiento", this.trayectoriaActual, organizacion.getUsuarioActivo().getApellido()+", "+organizacion.getUsuarioActivo().getNombre());
        jTable1.setRowHeight(30);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(60);
        //setIconImage(new ImageIcon(getClass().getResource(ParametrosDeInterfaz.rutaIcono)).getImage());
        this.getContentPane().setBackground(ParametrosDeInterfaz.colorFondo);
        
        this.cargaBasculas();
        
        
        
        criteriosSeleccionados = new HashMap<String, Boolean>();
        criteriosSeleccionados.put("Nombre", false);
        criteriosSeleccionados.put("Tipo", false);
        criteriosSeleccionados.put("Direccion", false);
        criteriosSeleccionados.put("FechaAdquisicion", false);
        criteriosSeleccionados.put("FechaUltimoMantenimiento", false);
        criteriosSeleccionados.put("Estado", false);
        criteriosSeleccionados.put("BasculaAsociada", false);
        if (ventanaAnterior instanceof ABMEquipamiento){
            ABMEquipamiento abmEquipamiento = (ABMEquipamiento) ventanaAnterior;
            if (abmEquipamiento.getOperacionActual().equals("Baja")){
                //Si voy a dar de baja, solo puedo seleccionar equipamiento activo.
                jCBEstado.doClick();
                jCBEstado.setEnabled(false);
                jComboBox2.setSelectedItem("Activo");
                jComboBox2.setEnabled(false);
            }
        }
        if (ventanaAnterior instanceof GestionMovimientos || ventanaAnterior instanceof GestionIngresoMP || (ventanaAnterior instanceof Inicio && ((Inicio)ventanaAnterior).getSubPanel() instanceof PanelGestionIngresos)){
            //Solo voy a poder seleccionar equipamiento en estado activo.
            jCBTipoEquipamiento.removeItem("Bascula");
            jCBEstado.doClick();
            jCBEstado.setEnabled(false);
            jComboBox2.setSelectedItem("Activo");
            jComboBox2.setEnabled(false);
        }
        
        if (ventanaAnterior instanceof GestionEstacionamientos || ventanaAnterior instanceof BuscarEstacionamiento || (ventanaAnterior instanceof Inicio && ((Inicio)ventanaAnterior).getSubPanel() instanceof PanelGestionEstacionamientos)){
            //Solo voy a poder seleccionar equipamiento en estado activo.
            //Solo voy a poder seleccionar equipamiento que sea camara de estacionamiento.
            jCheckBoxTipoEquipamiento.doClick();
            jCBTipoEquipamiento.setSelectedItem("Camara de estacionamiento acelerado");
            jCheckBoxTipoEquipamiento.setEnabled(false);
            jCBTipoEquipamiento.setEnabled(false);
            jCBEstado.doClick();
            jCBEstado.setEnabled(false);
            jComboBox2.setSelectedItem("Activo");
            jComboBox2.setEnabled(false);
        }
        if (ventanaAnterior instanceof GestionMoliendas || ventanaAnterior instanceof BuscarMolienda || ventanaAnterior instanceof GestionEgresos || (ventanaAnterior instanceof Inicio && ((Inicio)ventanaAnterior).getSubPanel() instanceof PanelGestionMoliendas) || (ventanaAnterior instanceof Inicio && ((Inicio)ventanaAnterior).getSubPanel() instanceof PanelGestionSalidas)){
            //Solo voy a poder seleccionar equipamiento en estado activo.
            //Solo voy a poder seleccionar equipamiento que sea molino.
            jCheckBoxTipoEquipamiento.doClick();
            jCBTipoEquipamiento.setSelectedItem("Molino");
            jCheckBoxTipoEquipamiento.setEnabled(false);
            jCBTipoEquipamiento.setEnabled(false);
            jCBEstado.doClick();
            jCBEstado.setEnabled(false);
            jComboBox2.setSelectedItem("Activo");
            jComboBox2.setEnabled(false);
        }
        if (ventanaAnterior instanceof GestionAnalisisLaboratorio || ventanaAnterior instanceof BuscarAnalisisLaboratorio || (ventanaAnterior instanceof Inicio && ((Inicio)ventanaAnterior).getSubPanel() instanceof PanelGestionAnalisisLaboratorio)){
            //Solo voy a poder seleccionar equipamiento en estado activo.
            //Solo voy a poder seleccionar equipamiento que sea Laboratorio.
            jCheckBoxTipoEquipamiento.doClick();
            jCBTipoEquipamiento.setSelectedItem("Laboratorio");
            jCheckBoxTipoEquipamiento.setEnabled(false);
            jCBTipoEquipamiento.setEnabled(false);
            jCBEstado.doClick();
            jCBEstado.setEnabled(false);
            jComboBox2.setSelectedItem("Activo");
            jComboBox2.setEnabled(false);
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
        jCBNombre = new javax.swing.JCheckBox();
        jCheckBoxTipoEquipamiento = new javax.swing.JCheckBox();
        jCBDireccion = new javax.swing.JCheckBox();
        jCBFechaAdquisicion = new javax.swing.JCheckBox();
        jCBEstado = new javax.swing.JCheckBox();
        jTFNombre = new javax.swing.JTextField();
        jCBTipoEquipamiento = new javax.swing.JComboBox<>();
        jTFDireccion = new javax.swing.JTextField();
        jCFechaAdquisicionInferior = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCFechaAdquisicionSuperior = new com.toedter.calendar.JDateChooser();
        jCheckBox11 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jCFechaUltimoMantenimientoInferior = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jCFechaUltimoMantenimientoSuperior = new com.toedter.calendar.JDateChooser();
        jCBBasculaAsociada = new javax.swing.JCheckBox();
        jCBBascula = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLEquipamientoSeleccionado = new javax.swing.JLabel();
        jLEquipamientoSeleccionado1 = new javax.swing.JLabel();
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

        jCBNombre.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBNombre.setText("Nombre");
        jCBNombre.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBNombre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBNombreItemStateChanged(evt);
            }
        });

        jCheckBoxTipoEquipamiento.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCheckBoxTipoEquipamiento.setText("Tipo de Equipamiento");
        jCheckBoxTipoEquipamiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxTipoEquipamientoItemStateChanged(evt);
            }
        });

        jCBDireccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBDireccion.setText("Direccion");
        jCBDireccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBDireccionItemStateChanged(evt);
            }
        });

        jCBFechaAdquisicion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBFechaAdquisicion.setText("Fecha de Adquisicion");
        jCBFechaAdquisicion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBFechaAdquisicionItemStateChanged(evt);
            }
        });

        jCBEstado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBEstado.setText("Estado");
        jCBEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBEstadoItemStateChanged(evt);
            }
        });

        jTFNombre.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFNombre.setText("Ingrese un Nombre");
        jTFNombre.setEnabled(false);

        jCBTipoEquipamiento.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBTipoEquipamiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Molino", "Camara de estacionamiento acelerado", "Deposito", "Bascula", "Laboratorio" }));
        jCBTipoEquipamiento.setEnabled(false);
        jCBTipoEquipamiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBTipoEquipamientoItemStateChanged(evt);
            }
        });

        jTFDireccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFDireccion.setText("Ingrese una direccion");
        jTFDireccion.setEnabled(false);

        jCFechaAdquisicionInferior.setEnabled(false);
        jCFechaAdquisicionInferior.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setText("Desde");
        jLabel4.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setText("Hasta");
        jLabel5.setEnabled(false);

        jCFechaAdquisicionSuperior.setEnabled(false);
        jCFechaAdquisicionSuperior.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jCheckBox11.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCheckBox11.setText("Fecha de ultimo mantenimiento");
        jCheckBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox11ItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel6.setText("Desde");
        jLabel6.setEnabled(false);

        jCFechaUltimoMantenimientoInferior.setEnabled(false);
        jCFechaUltimoMantenimientoInferior.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel7.setText("Hasta");
        jLabel7.setEnabled(false);

        jCFechaUltimoMantenimientoSuperior.setEnabled(false);
        jCFechaUltimoMantenimientoSuperior.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jCBBasculaAsociada.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBBasculaAsociada.setText("Bascula asociada");
        jCBBasculaAsociada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBBasculaAsociadaItemStateChanged(evt);
            }
        });

        jCBBascula.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBBascula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jCBBascula.setEnabled(false);

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Activo", "Baja" }));
        jComboBox2.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBDireccion)
                        .addGap(18, 18, 18)
                        .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBNombre)
                        .addGap(18, 18, 18)
                        .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxTipoEquipamiento)
                        .addGap(18, 18, 18)
                        .addComponent(jCBTipoEquipamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBFechaAdquisicion)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCFechaAdquisicionInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCFechaAdquisicionSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(jCFechaUltimoMantenimientoInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCFechaUltimoMantenimientoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBBasculaAsociada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBBascula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBNombre))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCBTipoEquipamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxTipoEquipamiento))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBDireccion))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jCFechaAdquisicionSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCFechaAdquisicionInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jCBFechaAdquisicion))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCFechaUltimoMantenimientoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jCFechaUltimoMantenimientoInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jCheckBox11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBEstado))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCBBascula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBBasculaAsociada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Direccion", "Estado", "Bascula"
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
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(25);
        }

        jLEquipamientoSeleccionado.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEquipamientoSeleccionado.setText("Equipamiento seleccionado:");

        jLEquipamientoSeleccionado1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

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
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLEquipamientoSeleccionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLEquipamientoSeleccionado1, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBConcretarAccion)
                        .addComponent(jBCancelar)
                        .addComponent(jLEquipamientoSeleccionado))
                    .addComponent(jLEquipamientoSeleccionado1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBConcretarAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConcretarAccionActionPerformed
        TransferenciaInstancias ventanaAnterior = (TransferenciaInstancias) this.ventanaAnterior;
        ventanaAnterior.actualizarUnObjeto(unEquipamientoSeleccionado);
        
        this.ventanaAnterior.setVisible(true);
        this.ventanaAnterior.setFocusable(true);
        this.dispose();
    }//GEN-LAST:event_jBConcretarAccionActionPerformed
    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed

        this.limpiarCampos();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jCBTipoEquipamientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBTipoEquipamientoItemStateChanged

    }//GEN-LAST:event_jCBTipoEquipamientoItemStateChanged

    private void jCBNombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBNombreItemStateChanged
        jTFNombre.setEnabled(jCBNombre.isSelected());
        criteriosSeleccionados.put("Nombre", jCBNombre.isSelected());
        
    }//GEN-LAST:event_jCBNombreItemStateChanged

    private void jCheckBoxTipoEquipamientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxTipoEquipamientoItemStateChanged
        jCBTipoEquipamiento.setEnabled(jCheckBoxTipoEquipamiento.isSelected());
        criteriosSeleccionados.put("Tipo", jCheckBoxTipoEquipamiento.isSelected());
    }//GEN-LAST:event_jCheckBoxTipoEquipamientoItemStateChanged

    private void jCBDireccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBDireccionItemStateChanged
        jTFDireccion.setEnabled(jCBDireccion.isSelected());
        criteriosSeleccionados.put("Direccion", jCBDireccion.isSelected());
    }//GEN-LAST:event_jCBDireccionItemStateChanged

    private void jCBFechaAdquisicionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBFechaAdquisicionItemStateChanged
        jLabel4.setEnabled(jCBFechaAdquisicion.isSelected());
        jCFechaAdquisicionInferior.setEnabled(jCBFechaAdquisicion.isSelected());
        jLabel5.setEnabled(jCBFechaAdquisicion.isSelected());
        jCFechaAdquisicionSuperior.setEnabled(jCBFechaAdquisicion.isSelected());
        criteriosSeleccionados.put("FechaAdquisicion", jCBFechaAdquisicion.isSelected());
    }//GEN-LAST:event_jCBFechaAdquisicionItemStateChanged

    private void jCheckBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox11ItemStateChanged
        jLabel6.setEnabled(jCheckBox11.isSelected());
        jCFechaUltimoMantenimientoInferior.setEnabled(jCheckBox11.isSelected());
        jLabel7.setEnabled(jCheckBox11.isSelected());
        jCFechaUltimoMantenimientoSuperior.setEnabled(jCheckBox11.isSelected());
        criteriosSeleccionados.put("FechaUltimoMantenimiento", jCheckBox11.isSelected());
    }//GEN-LAST:event_jCheckBox11ItemStateChanged

    private void jCBEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBEstadoItemStateChanged
        jComboBox2.setEnabled(jCBEstado.isSelected());
        criteriosSeleccionados.put("Estado", jCBEstado.isSelected());
    }//GEN-LAST:event_jCBEstadoItemStateChanged

    private void jCBBasculaAsociadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBBasculaAsociadaItemStateChanged
        jCBBascula.setEnabled(jCBBasculaAsociada.isSelected());
        criteriosSeleccionados.put("BasculaAsociada", jCBBasculaAsociada.isSelected());
    }//GEN-LAST:event_jCBBasculaAsociadaItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Bascula unaBascula = this.organizacion.getUnaBascula((String) jCBBascula.getSelectedItem());
        ArrayList listaFiltrada = null;
        try {
            if (ventanaAnterior instanceof ABMEquipamiento || ventanaAnterior instanceof GestionAnalisisLaboratorio || ventanaAnterior instanceof BuscarAnalisisLaboratorio || (ventanaAnterior instanceof Inicio && ((Inicio)ventanaAnterior).getSubPanel() instanceof PanelGestionAnalisisLaboratorio)){
                listaFiltrada = this.organizacion.filtrarEquipamientos(this.criteriosSeleccionados, jTFNombre.getText(), (String)jCBTipoEquipamiento.getSelectedItem(), jTFDireccion.getText(), jCFechaAdquisicionInferior.getCalendar(), jCFechaAdquisicionSuperior.getCalendar(), jCFechaUltimoMantenimientoInferior.getCalendar(), jCFechaUltimoMantenimientoSuperior.getCalendar(),(String) jComboBox2.getSelectedItem(), unaBascula);
            }else{
                listaFiltrada = this.organizacion.filtrarEquipamientosSinBasculasNiLaboratorios(this.criteriosSeleccionados, jTFNombre.getText(), (String)jCBTipoEquipamiento.getSelectedItem(), jTFDireccion.getText(), jCFechaAdquisicionInferior.getCalendar(), jCFechaAdquisicionSuperior.getCalendar(), jCFechaUltimoMantenimientoInferior.getCalendar(), jCFechaUltimoMantenimientoSuperior.getCalendar(),(String) jComboBox2.getSelectedItem(), unaBascula);
            }
                
        } catch (ExcepcionCargaParametros ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
        Iterator equipamientos = listaFiltrada.iterator();
        while (equipamientos.hasNext()){
            Equipamiento unEquipamiento = (Equipamiento) equipamientos.next();
            ((DefaultTableModel)this.jTable1.getModel()).addRow(unEquipamiento.devolverVector());    
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        this.unEquipamientoSeleccionado = this.organizacion.getEquipamientos().get(id);
        actualizarEquipamientoSeleccionado();
        habilitarBotones();
        
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(BuscarEquipamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarEquipamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarEquipamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarEquipamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarEquipamiento().setVisible(true);
            }
        });
    }
    private void cargaBasculas(){
        this.jCBBascula.removeAllItems();
        ArrayList basculasActivas = organizacion.getBasculasActivas();
        Iterator recorredorDeBasculas = basculasActivas.iterator();
        while (recorredorDeBasculas.hasNext()){
            Bascula unaBascula = (Bascula) recorredorDeBasculas.next();
            this.jCBBascula.addItem(unaBascula.toString());
        }
        
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private InterfazGrafica.CabeceraDeVentana cabeceraDeVentana;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConcretarAccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBBascula;
    private javax.swing.JCheckBox jCBBasculaAsociada;
    private javax.swing.JCheckBox jCBDireccion;
    private javax.swing.JCheckBox jCBEstado;
    private javax.swing.JCheckBox jCBFechaAdquisicion;
    private javax.swing.JCheckBox jCBNombre;
    private javax.swing.JComboBox<String> jCBTipoEquipamiento;
    private com.toedter.calendar.JDateChooser jCFechaAdquisicionInferior;
    private com.toedter.calendar.JDateChooser jCFechaAdquisicionSuperior;
    private com.toedter.calendar.JDateChooser jCFechaUltimoMantenimientoInferior;
    private com.toedter.calendar.JDateChooser jCFechaUltimoMantenimientoSuperior;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBoxTipoEquipamiento;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLEquipamientoSeleccionado;
    private javax.swing.JLabel jLEquipamientoSeleccionado1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        jTFNombre.setText("");
        jTFDireccion.setText("");
        ((DefaultTableModel)this.jTable1.getModel()).setRowCount(0);
        jBConcretarAccion.setEnabled(false);
        jBCancelar.setEnabled(false);
        jLEquipamientoSeleccionado1.setText("");
        this.unEquipamientoSeleccionado = null;
    }

    private void actualizarEquipamientoSeleccionado() {
        jLEquipamientoSeleccionado1.setText(this.unEquipamientoSeleccionado.getNombre());
        
    }
    private void habilitarBotones(){
        jBConcretarAccion.setEnabled(true);
        jBCancelar.setEnabled(true);
    }
}
