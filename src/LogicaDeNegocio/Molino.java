/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Bascula;
import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class Molino extends Equipamiento{

    
    private Map<Integer, SectorMolino> sectores;

    public Molino(int id, String nombre, String direccion, Date fechaAdquisicion, Date fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, String estado) { //Constructor para la recuperacion
        super(id, nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida, estado);
    }
    
    public Molino(String nombre, String direccion, Calendar fechaAdquisicion, Calendar fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, Bascula unaBascula) throws ExcepcionCargaParametros { //Constructor de la bd
        super(nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida, unaBascula);
    }




}
