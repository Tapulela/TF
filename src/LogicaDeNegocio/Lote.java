/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Organizacion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class Lote {
    public static final String ESTADO_ANULADO = "Anulado";
    
    
    private int id;
    private String etiqueta;
    private float cantidad;
    private String tipo_Lote;
    private String unidadDeMedida;
    private Calendar fechaAdquisicion;
    
    private OrdenDeProduccion ordenDeProduccionAsociada;
    private OrdenDeCompra ordenDeCompraAsociada;
    private Equipamiento equipamientoDondeReside;
    
    private ArrayList movimientosAsociados;
    private ArrayList transformacionesAsociadas;
    
    
    private String estado;

    public Lote(int id, String etiqueta, float cantidad, String tipo_Lote, String unidadDeMedida, String estado, OrdenDeCompra unaOrdenDeCompra) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.cantidad = cantidad;
        this.tipo_Lote = tipo_Lote;
        this.unidadDeMedida = unidadDeMedida;
        this.estado = estado;
        
        //this.equipamientoDondeReside = unEquipamiento; //EL EQUIPAMIENTO DONDE RESIDE SE DEFINE A PARTIR DE LA RECUPERACIÓN DE MOVIMIENTOS
        this.ordenDeCompraAsociada = unaOrdenDeCompra;
        this.ordenDeProduccionAsociada = unaOrdenDeCompra.getOrdenDeProduccionAsociada();
        
        this.movimientosAsociados = new ArrayList();
        this.transformacionesAsociadas = new ArrayList();
    }

    public Lote(String etiqueta, float cantidad, String tipo_Lote, String unidadDeMedida, Calendar fechaAdquisicion, OrdenDeCompra ordenDeCompraAsociada, Equipamiento equipamientoDondeReside) {
        this.etiqueta = etiqueta;
        this.cantidad = cantidad;
        this.tipo_Lote = tipo_Lote;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaAdquisicion = fechaAdquisicion;
        this.ordenDeCompraAsociada = ordenDeCompraAsociada;
        this.ordenDeProduccionAsociada = ordenDeCompraAsociada.getOrdenDeProduccionAsociada();
        this.movimientosAsociados = new ArrayList();
        this.transformacionesAsociadas = new ArrayList();
    }

    
    public Calendar getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public OrdenDeProduccion getOrdenDeProduccionAsociada() {
        return ordenDeProduccionAsociada;
    }

    public OrdenDeCompra getOrdenDeCompraAsociada() {
        return ordenDeCompraAsociada;
    }

    public Equipamiento getEquipamientoDondeReside() {
        return equipamientoDondeReside;
    }


    
    
    

    public int getId() {
        return id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public String getTipo_Lote() {
        return tipo_Lote;
    }

    public void setTipo_Lote(String tipo_Lote) {
        this.tipo_Lote = tipo_Lote;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public ArrayList getMovimientosAsociados() {
        return movimientosAsociados;
    }

    public void setMovimientosAsociados(ArrayList movimientosAsociados) {
        this.movimientosAsociados = movimientosAsociados;
    }

    public ArrayList getTransformacionesAsociadas() {
        return transformacionesAsociadas;
    }

    public void setTransformacionesAsociadas(ArrayList transformacionesAsociadas) {
        this.transformacionesAsociadas = transformacionesAsociadas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean poseeEquipamientoAsociado() {
        return (this.equipamientoDondeReside != null);
    }

    boolean estaAnulado() {
        return this.estado.equals(ESTADO_ANULADO);
    }
    
    
    
}
