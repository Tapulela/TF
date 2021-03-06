/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Paneles;

import InterfazGrafica.Busqueda.BuscarEquipamiento;
import InterfazGrafica.Busqueda.BuscarLote;
import InterfazGrafica.Busqueda.BuscarMolienda;
import InterfazGrafica.Busqueda.BuscarOrdenDeProduccion;
import InterfazGrafica.GestionEgresos;
import InterfazGrafica.Inicio;
import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.TransferenciaInstancias;
import InterfazGrafica.UtilidadesInterfazGrafica;
import static InterfazGrafica.UtilidadesInterfazGrafica.establecerAlineacionDeTabla;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.Evento;
import LogicaDeNegocio.Lote;
import LogicaDeNegocio.Molienda;
import LogicaDeNegocio.OrdenDeProduccion;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Salida;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author usuario
 */
public class PanelGestionSalidas extends Panel implements TransferenciaInstancias{

    /**
     * Creates new form PanelGestionEstacionamientos
     */
    
    
    public static final String[] criterios = new String[] {"Equipamiento asociado", "Orden de producción asociada", "Lote implicado", "Estado", "Fecha de origen", "Evento implicado", "Tipo de salida"};
    
    private Salida unObjetoSeleccionado;
    
    private Equipamiento unEquipamientoSeleccionado;
    private OrdenDeProduccion unaOrdenDeProduccion;
    private Lote unLoteSeleccionado;
    private Evento unEventoSeleccionado;    
    
    
    public PanelGestionSalidas() {
        initComponents();
    }

    public PanelGestionSalidas(JFrame ventanaContenedora, String trayectoriaActual, Organizacion unaOrganizacion, String tituloReporte) {
        super(ventanaContenedora, trayectoriaActual, unaOrganizacion, criterios, tituloReporte);
        initComponents();
        this.setVisible(true);
        
        
        setBackground(ParametrosDeInterfaz.colorFondo);
        establecerAlineacionDeTabla(jTable1, SwingConstants.CENTER);
        UtilidadesInterfazGrafica.configurarTabla(jTable1);
        
        
        jTable1.setRowHeight(30);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(60);
        establecerAlineacionDeTabla(jTable1, SwingConstants.CENTER);
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

        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCBCriterio1 = new javax.swing.JCheckBox();
        jCBCriterio2 = new javax.swing.JCheckBox();
        jCBCriterio4 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        datoCriterio4 = new javax.swing.JComboBox<>();
        jCBCriterio5 = new javax.swing.JCheckBox();
        jLEstatico1Criterio5 = new javax.swing.JLabel();
        dato1Criterio5 = new com.toedter.calendar.JDateChooser();
        jLEstatico2Criterio5 = new javax.swing.JLabel();
        dato2Criterio5 = new com.toedter.calendar.JDateChooser();
        botonCriterio1 = new javax.swing.JButton();
        jLCriterio1 = new javax.swing.JLabel();
        jLEstaticoCriterio1 = new javax.swing.JLabel();
        botonCriterio2 = new javax.swing.JButton();
        jLEstaticoCriterio2 = new javax.swing.JLabel();
        jLCriterio2 = new javax.swing.JLabel();
        jLCriterio3 = new javax.swing.JLabel();
        jLEstaticoCriterio3 = new javax.swing.JLabel();
        jCBCriterio3 = new javax.swing.JCheckBox();
        botonCriterio3 = new javax.swing.JButton();
        jCBCriterio6 = new javax.swing.JCheckBox();
        jLEstaticoCriterio6 = new javax.swing.JLabel();
        jLCriterio6 = new javax.swing.JLabel();
        botonCriterio6 = new javax.swing.JButton();
        jCBCriterio7 = new javax.swing.JCheckBox();
        datoCriterio7 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        jButton10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/icono-Opcion.png"))); // NOI18N
        jButton10.setText("Gestionar Egresos");
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setText("Criterios de búsqueda");

        jCBCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio1.setText("Equipamiento asociado");
        jCBCriterio1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/InterfazGrafica/Assets/CheckTic.png"))); // NOI18N
        jCBCriterio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio1ItemStateChanged(evt);
            }
        });

        jCBCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio2.setText("Orden de produccion implicada");
        jCBCriterio2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio2ItemStateChanged(evt);
            }
        });

        jCBCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio4.setText("Estado");
        jCBCriterio4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio4ItemStateChanged(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("Realizar consulta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        datoCriterio4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Regular", "Anulado" }));
        datoCriterio4.setEnabled(false);

        jCBCriterio5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio5.setText("Fecha de origen");
        jCBCriterio5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio5ItemStateChanged(evt);
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

        botonCriterio1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio1.setText("Buscar un molino");
        botonCriterio1.setEnabled(false);
        botonCriterio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio1ActionPerformed(evt);
            }
        });

        jLCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstaticoCriterio1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio1.setText("Equipamiento seleccionado:");

        botonCriterio2.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio2.setText("Buscar una Orden de produccion");
        botonCriterio2.setEnabled(false);
        botonCriterio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio2ActionPerformed(evt);
            }
        });

        jLEstaticoCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio2.setText("Orden de produccion seleccionada:");

        jLCriterio2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jLEstaticoCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio3.setText("Lote seleccionado:");

        jCBCriterio3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio3.setText("Lote implicado");
        jCBCriterio3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio3ItemStateChanged(evt);
            }
        });

        botonCriterio3.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio3.setText("Buscar un lote");
        botonCriterio3.setEnabled(false);
        botonCriterio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio3ActionPerformed(evt);
            }
        });

        jCBCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio6.setText("Molienda implicada");
        jCBCriterio6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio6ItemStateChanged(evt);
            }
        });

        jLEstaticoCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLEstaticoCriterio6.setText("ID evento seleccionado");

        jLCriterio6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        botonCriterio6.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonCriterio6.setText("Buscar una molienda");
        botonCriterio6.setEnabled(false);
        botonCriterio6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriterio6ActionPerformed(evt);
            }
        });

        jCBCriterio7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jCBCriterio7.setText("Tipo de salida");
        jCBCriterio7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCriterio7ItemStateChanged(evt);
            }
        });

        datoCriterio7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datoCriterio7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Egreso", "Merma", "Perdida" }));
        datoCriterio7.setEnabled(false);

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
                        .addComponent(jLEstaticoCriterio1)
                        .addGap(18, 18, 18)
                        .addComponent(jLCriterio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio1)
                                .addGap(18, 18, 18)
                                .addComponent(botonCriterio1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio2)
                                .addGap(18, 18, 18)
                                .addComponent(botonCriterio2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio3)
                                .addGap(18, 18, 18)
                                .addComponent(botonCriterio3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBCriterio4)
                                .addGap(18, 18, 18)
                                .addComponent(datoCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLEstaticoCriterio6)
                                .addGap(18, 18, 18)
                                .addComponent(jLCriterio6, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLEstatico1Criterio5)
                                        .addGap(6, 6, 6)
                                        .addComponent(dato1Criterio5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCBCriterio6)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonCriterio6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLEstatico2Criterio5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dato2Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 412, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBCriterio7)
                        .addGap(18, 18, 18)
                        .addComponent(datoCriterio7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLEstaticoCriterio1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBCriterio2)
                            .addComponent(botonCriterio2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLEstaticoCriterio2)
                            .addComponent(jLCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBCriterio3)
                            .addComponent(botonCriterio3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLEstaticoCriterio3)
                            .addComponent(jLCriterio3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLCriterio1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio4)
                    .addComponent(datoCriterio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dato2Criterio5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstatico2Criterio5)
                    .addComponent(dato1Criterio5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLEstatico1Criterio5)
                    .addComponent(jCBCriterio5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCriterio6)
                    .addComponent(botonCriterio6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLCriterio6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstaticoCriterio6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(datoCriterio7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCriterio7)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(19, 19, 19))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo de salida", "Equipamiento", "Estado", "Fecha de origen", "Lote", "Evento asociado", "Kgs egresados", "Bolsas egresadas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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
            jTable1.getColumnModel().getColumn(0).setMinWidth(120);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(1).setMinWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(2).setMinWidth(400);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(400);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(400);
            jTable1.getColumnModel().getColumn(3).setMinWidth(150);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(4).setMinWidth(180);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(180);
            jTable1.getColumnModel().getColumn(5).setMinWidth(200);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(6).setMinWidth(200);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(7).setMinWidth(120);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(8).setMinWidth(120);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(120);
        }

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Consultas de Salidas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jButton10))
                        .addGap(0, 1369, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton10)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        GestionEgresos ventana = new GestionEgresos(((Inicio) ventanaContenedora).getOrganizacion(), ventanaContenedora, ((Inicio) ventanaContenedora).getTrayectoriaActual());
        this.ventanaContenedora.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jCBCriterio1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio1ItemStateChanged
        botonCriterio1.setEnabled(jCBCriterio1.isSelected());
        jLEstaticoCriterio1.setEnabled(jCBCriterio1.isSelected());
        jLCriterio1.setEnabled(jCBCriterio1.isSelected());
        asignarCriterio(0, (JCheckBox) evt.getSource());

    }//GEN-LAST:event_jCBCriterio1ItemStateChanged

    private void jCBCriterio2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio2ItemStateChanged
        botonCriterio2.setEnabled(jCBCriterio2.isSelected());
        jLEstaticoCriterio2.setEnabled(jCBCriterio2.isSelected());
        jLCriterio2.setEnabled(jCBCriterio2.isSelected());
        asignarCriterio(1, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio2ItemStateChanged

    private void jCBCriterio4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio4ItemStateChanged
        datoCriterio4.setEnabled(jCBCriterio4.isSelected());
        asignarCriterio(3, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio4ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarTabla(jTable1, organizacion.getSalidas());

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCBCriterio5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio5ItemStateChanged
        jLEstatico1Criterio5.setEnabled(jCBCriterio5.isSelected());
        dato1Criterio5.setEnabled(jCBCriterio5.isSelected());
        jLEstatico2Criterio5.setEnabled(jCBCriterio5.isSelected());
        dato2Criterio5.setEnabled(jCBCriterio5.isSelected());
        asignarCriterio(4, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio5ItemStateChanged

    private void botonCriterio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio1ActionPerformed
        BuscarEquipamiento unaVentana = new BuscarEquipamiento(this.organizacion, ventanaContenedora, this.trayectoriaActual);
        ventanaContenedora.dispose();
    }//GEN-LAST:event_botonCriterio1ActionPerformed

    private void botonCriterio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio2ActionPerformed
        BuscarOrdenDeProduccion unaVentana = new BuscarOrdenDeProduccion(this.organizacion, ventanaContenedora, this.trayectoriaActual);
        ventanaContenedora.dispose();
    }//GEN-LAST:event_botonCriterio2ActionPerformed

    private void jCBCriterio3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio3ItemStateChanged
        botonCriterio3.setEnabled(jCBCriterio3.isSelected());
        jLEstaticoCriterio3.setEnabled(jCBCriterio3.isSelected());
        jLCriterio3.setEnabled(jCBCriterio3.isSelected());
        asignarCriterio(2, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio3ItemStateChanged

    private void botonCriterio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio3ActionPerformed
        BuscarLote unaVentana = new BuscarLote(this.organizacion, ventanaContenedora, this.trayectoriaActual, null);
        ventanaContenedora.dispose();
        //        BuscarLote unaVentana = new BuscarLote(this.organizacion, this, this.trayectoriaActual);
        //      TENGO QUE HACER UNA BUSQUEDA DE LOTES QUE PERMITA ELEGIR COMO CRITERIO A LOS EQUIPAMIENTOS.
    }//GEN-LAST:event_botonCriterio3ActionPerformed

    private void jCBCriterio6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio6ItemStateChanged
        botonCriterio6.setEnabled(jCBCriterio6.isSelected());
        jLEstaticoCriterio6.setEnabled(jCBCriterio6.isSelected());
        jLCriterio6.setEnabled(jCBCriterio6.isSelected());
        asignarCriterio(5, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio6ItemStateChanged

    private void botonCriterio6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCriterio6ActionPerformed
        BuscarMolienda unaVentana = new BuscarMolienda(this.organizacion, ventanaContenedora, this.trayectoriaActual);
        ventanaContenedora.dispose();
    }//GEN-LAST:event_botonCriterio6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int id = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        this.unObjetoSeleccionado = this.organizacion.getSalidas().get(id);
        //exhibirObjetoSeleccionado();
        //habilitarBotones();
    }//GEN-LAST:event_jTable1MouseClicked

    /*private void habilitarBotones(){
        jBVerDetalle.setEnabled(true);
    }*/
    
    private void jCBCriterio7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCriterio7ItemStateChanged
        datoCriterio7.setEnabled(jCBCriterio7.isSelected());
        asignarCriterio(6, (JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCBCriterio7ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        generarReporte(organizacion.getSalidas());
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCriterio1;
    private javax.swing.JButton botonCriterio2;
    private javax.swing.JButton botonCriterio3;
    private javax.swing.JButton botonCriterio6;
    private com.toedter.calendar.JDateChooser dato1Criterio5;
    private com.toedter.calendar.JDateChooser dato2Criterio5;
    private javax.swing.JComboBox<String> datoCriterio4;
    private javax.swing.JComboBox<String> datoCriterio7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCBCriterio1;
    private javax.swing.JCheckBox jCBCriterio2;
    private javax.swing.JCheckBox jCBCriterio3;
    private javax.swing.JCheckBox jCBCriterio4;
    private javax.swing.JCheckBox jCBCriterio5;
    private javax.swing.JCheckBox jCBCriterio6;
    private javax.swing.JCheckBox jCBCriterio7;
    private javax.swing.JLabel jLCriterio1;
    private javax.swing.JLabel jLCriterio2;
    private javax.swing.JLabel jLCriterio3;
    private javax.swing.JLabel jLCriterio6;
    private javax.swing.JLabel jLEstatico1Criterio5;
    private javax.swing.JLabel jLEstatico2Criterio5;
    private javax.swing.JLabel jLEstaticoCriterio1;
    private javax.swing.JLabel jLEstaticoCriterio2;
    private javax.swing.JLabel jLEstaticoCriterio3;
    private javax.swing.JLabel jLEstaticoCriterio6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

/*    private void exhibirObjetoSeleccionado() {
        jLObjetoSeleccionado.setText(""+this.unObjetoSeleccionado.getId());
        
    }*/

    @Override
    public void actualizarUnObjeto(Object unObjeto) {
        if (unObjeto instanceof Lote){
            asignarObjetoACriterio(2, unObjeto);
            this.unLoteSeleccionado = (Lote) unObjeto;
            exhibirLote();
        }
        if (unObjeto instanceof Equipamiento){
            asignarObjetoACriterio(0, unObjeto);
            this.unEquipamientoSeleccionado = (Equipamiento) unObjeto;
            exhibirEquipamiento();
        }
        if (unObjeto instanceof OrdenDeProduccion){
            asignarObjetoACriterio(1, unObjeto);
            this.unaOrdenDeProduccion = (OrdenDeProduccion) unObjeto;
            exhibirOrdenProduccion();
        }
        if (unObjeto instanceof Molienda){
            asignarObjetoACriterio(5, unObjeto);
            this.unEventoSeleccionado = (Evento) unObjeto;
            exhibirEvento();
        }
    }
    
    private void exhibirLote() {
        jLCriterio3.setText("ID de Lote: "+unLoteSeleccionado.getId()+" , etiqueta: "+unLoteSeleccionado.getEtiqueta());
    }

    private void exhibirEquipamiento() {
        jLCriterio1.setText(unEquipamientoSeleccionado.getNombre());
    }

    private void exhibirOrdenProduccion() {
        jLCriterio2.setText("ID de orden de produccion: "+this.unaOrdenDeProduccion.getId());
    }

    private void exhibirEvento() {
        if (this.unEventoSeleccionado == null)
            return;
        jLCriterio6.setText(""+unEventoSeleccionado.getId());
    }    

    @Override
    protected void generarObjetos() {
        asignarObjetoACriterio(3, datoCriterio4.getSelectedItem());
        asignarObjetoACriterio(4, generarListaFecha(dato1Criterio5.getCalendar(), dato2Criterio5.getCalendar()));
        asignarObjetoACriterio(6, datoCriterio7.getSelectedItem());
    }
}
