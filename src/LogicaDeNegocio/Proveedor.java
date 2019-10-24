/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author usuario
 */
public class Proveedor {
    private int id;
    private String razonSocial;
    private String cuit;
    private String estado;
    private Localidad localidad;
    
    private ArrayList ordenesDeCompraAsociadas;
    
    public Proveedor(int id, String razonSocial, String cuit, String estado, Localidad localidad) { //Constructor para la base de datos
        this.id = id;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.estado = estado;
        this.localidad = localidad;
        
        this.ordenesDeCompraAsociadas = new ArrayList();
    }

    public Proveedor(String razonSocial, String cuit, Localidad localidad) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.estado = "Activo";
        this.localidad = localidad;
        
        this.ordenesDeCompraAsociadas = new ArrayList();
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    
    

    public int getId() {
        return id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void agregarOrdenDeCompra(OrdenDeCompra unaOrdenDeCompra){
        this.ordenesDeCompraAsociadas.add(unaOrdenDeCompra);
    }
    
    public boolean seEncuentraActivo(){
        return this.estado.equals("Activo");
    }

    public boolean poseeCuit(String unCuit) {
        return this.cuit.equals(unCuit);
    }

    public boolean poseeRazonSocial(String unaRazonSocial) {
        return this.razonSocial.toUpperCase().equals(unaRazonSocial.toUpperCase());
    }

    public Object[] devolverVector() {
        Object[] vec ={this.getId(),this.getRazonSocial(), this.getCuit(), this.getEstado(), this.localidad.getNombre()};
        return vec;
    }
}
