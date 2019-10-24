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
public class Pais {
    public static final String ESTADO_ACTIVO = "Activo";

    /**
     * Constante que representa el estado de baja de un pais
     */
    public static final String ESTADO_BAJA = "Baja";
    private int id;
    private String nombre;
    private String estado;
    private ArrayList provincias;

    public Pais(int id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.provincias = new ArrayList();
    }

    public Pais(String nombre) {
        this.nombre = nombre;
        this.estado = ESTADO_ACTIVO;
        this.provincias = new ArrayList();
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

    public ArrayList getProvincias() {
        return provincias;
    }

    public void setProvincias(ArrayList provincias) {
        this.provincias = provincias;
    }

    public void agregarProvincia(Provincia unaProvincia) {
        this.provincias.add(unaProvincia);
    }

    boolean seLlama(String unNombre) {
        return this.nombre.toUpperCase().equals(unNombre.toUpperCase());
    }

    boolean poseeProvinciasActivas() {
        boolean retorno = false;
        Iterator provincias = this.provincias.iterator();
        while (provincias.hasNext() && !retorno){
            Provincia unaProvincia = (Provincia) provincias.next();
            retorno = unaProvincia.seEncuentraActiva();
        }
        return retorno;
    }

    void darDeBaja() {
        this.estado = ESTADO_BAJA;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public boolean seEncuentraActivo() {
        return this.estado.equals(ESTADO_ACTIVO);
    }
    
    
    
    
}
