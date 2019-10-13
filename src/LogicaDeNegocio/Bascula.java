/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Bascula extends Equipamiento {
    private ArrayList equipamientosAsociados;

    public Bascula(int id, String nombre, String direccion, Date fechaAdquisicion, Date fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, String estado) {
        super(id, nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida, estado);
        equipamientosAsociados = new ArrayList();
    }

    public Bascula(String nombre, String direccion, Calendar fechaAdquisicion, Calendar fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida) {
        super(nombre, direccion, fechaAdquisicion, fechaUltimoMantenimiento, capacidadMaxima, unidadDeMedida);
        equipamientosAsociados = new ArrayList();
    }

    public ArrayList getEquipamientosAsociados() {
        return equipamientosAsociados;
    }

    public void setEquipamientosAsociados(ArrayList equipamientosAsociados) {
        this.equipamientosAsociados = equipamientosAsociados;
    }

    public void agregarEquipamiento(Equipamiento unEquipamiento) throws ExcepcionCargaParametros {
        if (unEquipamiento instanceof Bascula){
            throw new ExcepcionCargaParametros("No se puede asociar una bascula a una bascula.");
        }
        this.equipamientosAsociados.add(unEquipamiento);
    }

    public ArrayList obtenerEquipamientoActivosAsociados() {
        ArrayList retorno = new ArrayList();
        Iterator recorredorEquipamientosAsociados = this.equipamientosAsociados.iterator();
        while (recorredorEquipamientosAsociados.hasNext()){
            Equipamiento unEquipamiento = (Equipamiento) recorredorEquipamientosAsociados.next();
            if (unEquipamiento.estaActivo())
                retorno.add(unEquipamiento);
        }
        return retorno;
    }
    
    public boolean poseeEquipamientosActivosAsociados(){
        return !obtenerEquipamientoActivosAsociados().isEmpty();
    }

    void removerEquipamiento(Equipamiento unEquipamiento) {
        this.equipamientosAsociados.remove(unEquipamiento);
        
    }


    
    
}
