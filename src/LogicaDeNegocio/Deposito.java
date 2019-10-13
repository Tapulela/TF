/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Bascula;
import LogicaDeNegocio.Equipamiento;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class Deposito extends Equipamiento {


    public Deposito(int id, String nombre, String direccion, java.sql.Date fechaAdquisicion, java.sql.Date fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, String estado) { //Constructor para la base de datos
        super(id, nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida, estado);
    }

    public Deposito(String nombre, String direccion, Calendar fechaAdquisicion, Calendar fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, Bascula unaBascula) throws ExcepcionCargaParametros {
        super(nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida, unaBascula);
    }


    

    
    
}
