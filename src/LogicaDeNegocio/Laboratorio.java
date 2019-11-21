/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class Laboratorio extends Equipamiento{

    private ArrayList analisisRealizados;
    
    public Laboratorio(int id, String nombre, String direccion, Date fechaAdquisicion, Date fechaUltimoMantenimiento, String estado) {
        super(id, nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, 0, "Kilogramo", estado);
        this.analisisRealizados = new ArrayList();
    }

    public Laboratorio(String nombre, String direccion, Calendar fechaAdquisicion, Calendar fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida) {
        super(nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida);
        this.analisisRealizados = new ArrayList();
    }

    public void agregarAnalisis(AnalisisLaboratorio unAnalisis) {
        this.analisisRealizados.add(unAnalisis);
    }
    
    
}
