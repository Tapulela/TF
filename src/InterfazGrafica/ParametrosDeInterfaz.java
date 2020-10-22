/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.Validaciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingConstants;

/**
 *
 * @author usuario
 */
public class ParametrosDeInterfaz {
    //public static final Color colorFondo = new Color(119, 221, 119);
    public static final Color colorFondo = new Color(204,204,204);
    public static final Color colorLetra = new Color(0, 0, 0);
    
    
    //Colores para el menu principal
    /*public static final Color COLOR_BARRA_SUPERIOR = new Color(122,72,221);
    public static final Color COLOR_BARRA_LATERAL = new Color(54,33,89);
    public static final Color COLOR_PANEL_PRESIONADO = new Color(85,65,118);
    public static final Color COLOR_PANEL_SIN_PRESIONAR = new Color(64,43,100);*/
    
    
    
    public static final Color COLOR_BARRA_SUPERIOR = new Color(0,153,51);
    public static final Color COLOR_BARRA_LATERAL = new Color(0,102,51);
    public static final Color COLOR_PANEL_PRESIONADO = new Color(0,51,0);
    public static final Color COLOR_PANEL_SIN_PRESIONAR = new Color(0,102,51);
    
    
    
    //Verde pastel: new Color(119, 221, 119);
    //Verde oscuro: new Color(5, 68, 23);
    public static final String rutaIcono = "../InterfazGrafica/Assets/Icono.png";
    //public static final String rutaLogo = "C:\\Users\\usuario\\Documents\\NetBeansProjects\\TrabajoFinal\\src\\InterfazGrafica\\Assets\\Logo.png";
    public static final String rutaLogo = "C:/Users/usuario/Documents/NetBeansProjects/TrabajoFinal/src/InterfazGrafica/Assets/Logo.png";
    public static Font fuentePorDefecto = new Font("Arial", 0 , 24);
    
    
    public static void configurarVentana(JFrame unaVentana){
        Component[] componentes = unaVentana.getContentPane().getComponents();
        confeccionarComponentes(componentes);
    }
    
    public static void confeccionarComponentes(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            unComponente.setFont(ParametrosDeInterfaz.fuentePorDefecto);
            //unComponente.setForeground(colorLetra);
            if (unComponente instanceof JPanel)
                confeccionarComponentes(((JPanel) unComponente).getComponents());
            if (unComponente instanceof javax.swing.JScrollPane){
                JViewport viewport = ((javax.swing.JScrollPane) unComponente).getViewport(); 
                confeccionarComponentes(viewport.getComponents());
            }
            if (unComponente instanceof javax.swing.JScrollPane)
                ((JScrollPane) unComponente).getVerticalScrollBar().setUnitIncrement(20);
            if (unComponente instanceof javax.swing.JTable){
                UtilidadesInterfazGrafica.configurarTabla((JTable) unComponente);
                UtilidadesInterfazGrafica.establecerAlineacionDeTabla((JTable) unComponente, SwingConstants.CENTER);
            }
            if (unComponente instanceof javax.swing.JTabbedPane){
                 confeccionarComponentes(((javax.swing.JTabbedPane)unComponente).getComponents());
            }
            
        }
    }
    
    
    public static void habilitarContenedor(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            unComponente.setEnabled(true);
            if (unComponente instanceof JPanel){
                habilitarContenedor(((JPanel) unComponente).getComponents());
            }
        }
    }
    
    public static void cambiarTamanoFuente(String unTamano) throws ExcepcionCargaParametros{
        if (Validaciones.esUnNumeroEnteroValido(unTamano)){
            Integer unTamanoInt = Integer.parseInt(unTamano);
            if (unTamanoInt > 10 && unTamanoInt <40){
                Font fuentePorDefecto = new Font("Arial", 0 , unTamanoInt);
                ParametrosDeInterfaz.fuentePorDefecto = fuentePorDefecto;
            }
        }else{
            throw new ExcepcionCargaParametros("Debe ingresarse un numero entero para especificar el tamaño de letra.");
        }
    }
}
