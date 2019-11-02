/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 *
 * @author usuario
 */
public class ParametrosDeInterfaz {
    public static final Color colorFondo = new Color(119, 221, 119);
    public static final Color colorLetra = new Color(0, 0, 0);
    //Verde pastel: new Color(119, 221, 119);
    //Verde oscuro: new Color(5, 68, 23);
    public static final String rutaIcono = "../InterfazGrafica/Assets/Icono.png";
    public static Font fuentePorDefecto = new Font("Arial", 0 , 22);
    
    
    public static void configurarVentana(JFrame unaVentana){
        Component[] componentes = unaVentana.getContentPane().getComponents();
        configurarContenedor(componentes);
    }
    
    private static void configurarContenedor(Component[] componentes){
        for (int i=0; i<componentes.length ;i++ ){
            Component unComponente = componentes[i];
            unComponente.setFont(ParametrosDeInterfaz.fuentePorDefecto);
            //unComponente.setForeground(colorLetra);
            if (unComponente instanceof JPanel){
                configurarContenedor(((JPanel) unComponente).getComponents());
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
}
