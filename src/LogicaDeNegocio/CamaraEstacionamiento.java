/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Bascula;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class CamaraEstacionamiento extends Equipamiento{
    private float duracionMaximaEstacionamiento;

    public CamaraEstacionamiento(int id, String nombre, String direccion, java.sql.Date fechaAdquisicion, java.sql.Date fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, String estado, float duracionMaximaEstacionamiento) {
        super(id, nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida, estado);
        this.duracionMaximaEstacionamiento = duracionMaximaEstacionamiento;
    }

    public CamaraEstacionamiento(String nombre, String direccion, Calendar fechaAdquisicion, Calendar fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, float duracionMaximaEstacionamiento, Bascula unaBascula) throws ExcepcionCargaParametros {
        super(nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida, unaBascula);
        this.duracionMaximaEstacionamiento = duracionMaximaEstacionamiento;        
    }

    public float getDuracionMaximaEstacionamiento() {
        return duracionMaximaEstacionamiento;
    }
    
    /*@Override
    public Object[] devolverVector() {
        String unaBascula = "----";
        if (super.getBasculaAsociada() != null){
            unaBascula = this.getBasculaAsociada().getNombre();
        }
        Object[] vec ={this.getNombre(), this.getTipo(), this.getDireccion(), this.obtenerFechaAdquisicion(), this.obtenerFechaUltimoMantenimiento(), this.getCapacidadMaxima(), this.getUnidadDeMedida(), this.duracionMaximaEstacionamiento, this.getEstado(), unaBascula};
        return vec;
    }*/

    public void setDuracionMaximaEstacionamiento(float duracionMaximaEstacionamiento) {
        this.duracionMaximaEstacionamiento = duracionMaximaEstacionamiento;
    }
    


 
    
}
