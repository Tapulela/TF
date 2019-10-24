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
public class Localidad {
    public static final String ESTADO_ACTIVO = "Activo";
    public static final String ESTADO_BAJA = "Baja";
    private int id;
    private String nombre;
    private String codigoPostal;
    private Provincia provinciaAsociada;
    private String estado;
    
    private ArrayList proveedoresAsociados;
    
    public Localidad (int id, String nombre, String estado, String codigoPostal,Provincia unaProvincia) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.provinciaAsociada = unaProvincia;
        
        this.proveedoresAsociados = new ArrayList();
    }
    public Localidad (String nombre, String codigoPostal,Provincia unaProvincia) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.provinciaAsociada = unaProvincia;
        this.estado = ESTADO_ACTIVO;
        this.proveedoresAsociados = new ArrayList();
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

    public Provincia getProvinciaAsociada() {
        return provinciaAsociada;
    }

    public void setProvinciaAsociada(Provincia provinciaAsociada) {
        this.provinciaAsociada = provinciaAsociada;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList getProveedoresAsociados() {
        return proveedoresAsociados;
    }
    
    
    public void agregarProveedor (Proveedor unProveedor){
        this.proveedoresAsociados.add(unProveedor);
    }

    public boolean seEncuentraActiva() {
        return this.estado.equals(ESTADO_ACTIVO);
    }

    public boolean seLlama(String unNombre) {
        return this.nombre.toUpperCase().equals(unNombre.toUpperCase());
    }

    boolean poseeProveedoresActivos() {
        boolean retorno = false;
        Iterator proveedores = this.proveedoresAsociados.iterator();
        while (proveedores.hasNext() && !retorno){
            Proveedor unProveedor = (Proveedor) proveedores.next();
            retorno = unProveedor.seEncuentraActivo();
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
    
    
    
}
