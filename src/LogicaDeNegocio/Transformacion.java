/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Lote;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class Transformacion {
    private int id;
    private Calendar fechaOrigen;
    private String estado;
    private ArrayList detalles;

    public boolean estaRegular() {
        return this.estado.equals("Regular");
    }
    

}
