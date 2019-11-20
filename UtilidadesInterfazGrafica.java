/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import java.awt.Component;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author usuario
 */
public class UtilidadesInterfazGrafica {
    public static void establecerAlineacionDeTabla(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
    
    public static void configurarTabla(JTable table){
        table.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        table.setRowHeight(28);
        table.setSelectionBackground(ParametrosDeInterfaz.COLOR_BARRA_SUPERIOR);
        table.setSelectionForeground(new java.awt.Color(255, 255, 255));
    }
    
    /**
     *  Deshabilita JTextArea, JtextField, calendarios, javax.swing.JRadioButton y JFormattedTextField
     * @param componentes
     */
    public static void deshabilitarCamposEditablesContenedor(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            if (unComponente instanceof javax.swing.JTextArea || unComponente instanceof javax.swing.JTextField || unComponente instanceof com.toedter.calendar.JDateChooser || unComponente instanceof javax.swing.JFormattedTextField || unComponente instanceof javax.swing.JComboBox || unComponente instanceof javax.swing.JRadioButton)
                unComponente.setEnabled(false);
            if (unComponente instanceof JPanel)
                deshabilitarCamposEditablesContenedor(((JPanel) unComponente).getComponents());
            if (unComponente instanceof javax.swing.JScrollPane){
                JViewport viewport = ((javax.swing.JScrollPane) unComponente).getViewport(); 
                deshabilitarCamposEditablesContenedor(viewport.getComponents());
            }
        }
    }
    
    public static void deshabilitarFocusCamposEditablesContenedor(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            if (unComponente instanceof javax.swing.JTextArea || unComponente instanceof javax.swing.JTextField || unComponente instanceof com.toedter.calendar.JDateChooser || unComponente instanceof javax.swing.JFormattedTextField || unComponente instanceof javax.swing.JComboBox || unComponente instanceof javax.swing.JRadioButton || unComponente instanceof javax.swing.JComboBox)
                unComponente.setFocusable(false);
            if (unComponente instanceof javax.swing.JComboBox){
                javax.swing.JComboBox unComboBox = (javax.swing.JComboBox) unComponente;
                unComboBox.setEnabled(false);
            }
            if (unComponente instanceof JPanel)
                deshabilitarFocusCamposEditablesContenedor(((JPanel) unComponente).getComponents());
            if (unComponente instanceof javax.swing.JScrollPane){
                JViewport viewport = ((javax.swing.JScrollPane) unComponente).getViewport(); 
                deshabilitarFocusCamposEditablesContenedor(viewport.getComponents());
                
            }
            
        }
    }
    
    public static void habilitarFocusCamposEditablesContenedor(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            if (unComponente instanceof javax.swing.JTextArea || unComponente instanceof javax.swing.JTextField || unComponente instanceof com.toedter.calendar.JDateChooser || unComponente instanceof javax.swing.JFormattedTextField || unComponente instanceof javax.swing.JComboBox || unComponente instanceof javax.swing.JRadioButton || unComponente instanceof javax.swing.JComboBox)
                unComponente.setFocusable(true);
            if (unComponente instanceof javax.swing.JComboBox){
                javax.swing.JComboBox unComboBox = (javax.swing.JComboBox) unComponente;
                unComboBox.setEnabled(true);
            }
            if (unComponente instanceof JPanel)
                habilitarFocusCamposEditablesContenedor(((JPanel) unComponente).getComponents());
            if (unComponente instanceof javax.swing.JScrollPane){
                JViewport viewport = ((javax.swing.JScrollPane) unComponente).getViewport(); 
                habilitarFocusCamposEditablesContenedor(viewport.getComponents());
                
            }
            
        }
    }    
    
    /**
     *  Habilita JTextArea, JtextField, calendarios, y JFormattedTextField
     * @param componentes
     */
    public static void habilitarCamposEditablesContenedor(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            if (unComponente instanceof javax.swing.JTextArea || unComponente instanceof javax.swing.JTextField || unComponente instanceof com.toedter.calendar.JDateChooser || unComponente instanceof javax.swing.JFormattedTextField || unComponente instanceof javax.swing.JComboBox || unComponente instanceof javax.swing.JRadioButton){
                unComponente.setEnabled(true);
            }
                
            if (unComponente instanceof JPanel){
                habilitarCamposEditablesContenedor(((JPanel) unComponente).getComponents());
            }
            if (unComponente instanceof javax.swing.JScrollPane){
                JViewport viewport = ((javax.swing.JScrollPane) unComponente).getViewport(); 
                habilitarCamposEditablesContenedor(viewport.getComponents());
            }
        }
    }
    
    public static void habilitarEtiquetasContenedor(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            if (unComponente instanceof javax.swing.JLabel)
                unComponente.setEnabled(true);
            if (unComponente instanceof JPanel){
                habilitarEtiquetasContenedor(((JPanel) unComponente).getComponents());
            }
            if (unComponente instanceof javax.swing.JScrollPane){
                JViewport viewport = ((javax.swing.JScrollPane) unComponente).getViewport(); 
                habilitarEtiquetasContenedor(viewport.getComponents());
            }
        }
    }
    
    public static void deshabilitarEtiquetasContenedor(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            if (unComponente instanceof javax.swing.JLabel)
                unComponente.setEnabled(false);
            if (unComponente instanceof JPanel){
                deshabilitarEtiquetasContenedor(((JPanel) unComponente).getComponents());
            }
            if (unComponente instanceof javax.swing.JScrollPane){
                JViewport viewport = ((javax.swing.JScrollPane) unComponente).getViewport(); 
                deshabilitarEtiquetasContenedor(viewport.getComponents());
            }
        }
    }
}
