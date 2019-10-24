/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Provincia {
    public static final String ESTADO_ACTIVO = "Activo";
    public static final String ESTADO_BAJA = "Baja";
    private int id;
    private String nombre;
    private String estado;
    private ArrayList localidades;
    private Pais paisAsociado;

    public Provincia (int id, String nombre, String estado,Pais paisAsociado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.paisAsociado = paisAsociado;
        this.localidades = new ArrayList();
    }    

    public Provincia(String nombre, Pais paisAsociado) {
        this.nombre = nombre;
        this.estado = ESTADO_ACTIVO;
        this.paisAsociado = paisAsociado;
        this.localidades = new ArrayList();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPaisAsociado() {
        return paisAsociado;
    }

    public void setPaisAsociado(Pais paisAsociado) {
        this.paisAsociado = paisAsociado;
    }

    public ArrayList getLocalidades() {
        return localidades;
    }
    
    

    public void agregarLocalidad(Localidad unaLocalidad) {
        this.localidades.add(unaLocalidad);
    }

    public boolean seLlama(String unNombre) {
        return this.nombre.toUpperCase().equals(unNombre.toUpperCase());
    }

    public boolean seEncuentraActiva() {
        return this.estado.equals(ESTADO_ACTIVO);
    }

    public boolean poseeLocalidadesActivas() {
        boolean retorno = false;
        Iterator localidades = this.localidades.iterator();
        while (localidades.hasNext() && !retorno){
            Localidad unaLocalidad = (Localidad) localidades.next();
            retorno = unaLocalidad.seEncuentraActiva();
        }
        return retorno;
    }

    public void darDeBaja() {
        this.estado = ESTADO_BAJA;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    
    
}
