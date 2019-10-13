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
public class OrdenDeCompra {
    private int id;
    private Calendar fechaOrigen;
    private float cantidadComprada;
    private String unidadDeMedida;
    private float costoPorUnidad;
    private String estado;
    
    private ArrayList lotesAsociados;
    private Proveedor proveedorAsociado;
    private OrdenDeProduccion ordenDeProduccionAsociada;

    public OrdenDeCompra(int id, java.sql.Date fechaOrigen, float cantidadComprada, String unidadDeMedida, float costoPorUnidad, String estado, Proveedor proveedorAsociado, OrdenDeProduccion unaOrdenDeProduccion) {
        this.id = id;
        this.fechaOrigen = Calendar.getInstance();
        this.fechaOrigen.setTime(fechaOrigen);
        this.cantidadComprada = cantidadComprada;
        this.unidadDeMedida = unidadDeMedida;
        this.costoPorUnidad = costoPorUnidad;
        this.estado = estado;
        this.proveedorAsociado = proveedorAsociado;
        this.ordenDeProduccionAsociada = unaOrdenDeProduccion;
        this.lotesAsociados = new ArrayList();
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

    public void setFechaOrigen(Calendar fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    public float getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(float cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public float getCostoPorUnidad() {
        return costoPorUnidad;
    }

    public void setCostoPorUnidad(float costoPorUnidad) {
        this.costoPorUnidad = costoPorUnidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList getLotesAsociados() {
        return lotesAsociados;
    }

    public void setLotesAsociados(ArrayList lotesAsociados) {
        this.lotesAsociados = lotesAsociados;
    }

    public Proveedor getProveedorAsociado() {
        return proveedorAsociado;
    }

    public void setProveedorAsociado(Proveedor proveedorAsociado) {
        this.proveedorAsociado = proveedorAsociado;
    }

    public OrdenDeProduccion getOrdenDeProduccionAsociada() {
        return ordenDeProduccionAsociada;
    }

    public void setOrdenDeProduccionAsociada(OrdenDeProduccion ordenDeProduccionAsociada) {
        this.ordenDeProduccionAsociada = ordenDeProduccionAsociada;
    }

    public void agregarLote(Lote unLote) {
        this.lotesAsociados.add(unLote);
    }

    public boolean poseeProveedorAsociado() {
        return (this.proveedorAsociado!=null);
    }
    
    
}
