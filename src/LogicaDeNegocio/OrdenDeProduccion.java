/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

/**
 *
 * @author usuario
 */
public class OrdenDeProduccion {
    private int id;
    private Calendar fechaOrigen;
    private float cantidadAProducir;
    private String unidadDeMedida;
    private Calendar fechaEntregaProductoTerminado;
    private String estado;
    private String descripcion;
    
    private ArrayList lotesImplicados;
    private ArrayList ordenesCompraImplicadas;

    public OrdenDeProduccion(int id, java.sql.Date fechaOrigen, float cantidadAProducir, String unidadDeMedida, java.sql.Date fechaEntregaProductoTerminado, String estado, String unaDescripcion) {
        this.id = id;
        this.fechaOrigen = Calendar.getInstance();
        this.fechaOrigen.setTime(fechaOrigen);
        this.cantidadAProducir = cantidadAProducir;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaEntregaProductoTerminado = Calendar.getInstance();
        this.fechaEntregaProductoTerminado.setTime(fechaOrigen);
        this.estado = estado;
        this.descripcion = unaDescripcion;
        
        this.lotesImplicados = new ArrayList();
        this.ordenesCompraImplicadas = new ArrayList();
    }

    public OrdenDeProduccion(Calendar fechaOrigen, float cantidadAProducir, String unidadDeMedida, Calendar fechaEntregaProductoTerminado, String descripcion) {
        this.fechaOrigen = fechaOrigen;
        this.cantidadAProducir = cantidadAProducir;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaEntregaProductoTerminado = fechaEntregaProductoTerminado;
        this.descripcion = descripcion.toUpperCase();
        
        this.estado = "Regular";
        this.lotesImplicados = new ArrayList();
        this.ordenesCompraImplicadas = new ArrayList();
    }
    
    

    public ArrayList getLotesImplicados() {
        return lotesImplicados;
    }



    public ArrayList getOrdenesCompraImplicadas() {
        return ordenesCompraImplicadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaOrigen() {
        return new Date(this.fechaOrigen.getTimeInMillis());
    }
    
    public Calendar getFechaOrigenC() {
        return this.fechaOrigen;
    }

    public void setFechaOrigen(Calendar fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    public float getCantidadAProducir() {
        return cantidadAProducir;
    }

    public void setCantidadAProducir(float cantidadAProducir) {
        this.cantidadAProducir = cantidadAProducir;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public Date getFechaEntregaProductoTerminado() {
        return new Date(this.fechaEntregaProductoTerminado.getTimeInMillis());
    }
    
    public Calendar getFechaEntregaProductoTerminadoC() {
        return this.fechaEntregaProductoTerminado;
    }
    
    public void setFechaEntregaProductoTerminado(Calendar fechaEntregaProductoTerminado) {
        this.fechaEntregaProductoTerminado = fechaEntregaProductoTerminado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void agregarOrdenDeCompra(OrdenDeCompra unaOrdenDeCompra){
        this.ordenesCompraImplicadas.add(unaOrdenDeCompra);
    }

    public void agregarLote(Lote unLote) {
        this.lotesImplicados.add(unLote);
    }

    public Object[] devolverVector() {
        Object[] vec ={this.getId(),this.getCantidadAProducir(), this.getUnidadDeMedida(), ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( this.getFechaOrigen().getTime() ), ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( this.getFechaEntregaProductoTerminado().getTime() ),this.getEstado()};
        return vec;
    }
}
