/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author usuario
 */
public class OrdenDeProduccion extends Evento{
    private static final String ESTADO_REGULAR = "Regular";
    private static final String ESTADO_ANULADO = "Anulado";
    private int id;
    private Calendar fechaOrigen;
    private float cantidadAProducir;
    private String unidadDeMedida;
    private Calendar fechaEntregaProductoTerminado;
    private String estado;
    private String descripcion;
    
    private ArrayList ordenesCompraImplicadas;
    
    public OrdenDeProduccion(int id, java.sql.Date fechaOrigen, float cantidadAProducir, String unidadDeMedida, java.sql.Date fechaEntregaProductoTerminado, String estado, String unaDescripcion, int idEvento, Usuario unUsuario) {
        super(idEvento, estado, unUsuario);
        this.id = id;
        this.fechaOrigen = Calendar.getInstance();
        this.fechaOrigen.setTime(fechaOrigen);
        this.cantidadAProducir = cantidadAProducir;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaEntregaProductoTerminado = Calendar.getInstance();
        this.fechaEntregaProductoTerminado.setTime(fechaOrigen);
        
        this.estado = estado;
        this.descripcion = unaDescripcion;
        
        this.ordenesCompraImplicadas = new ArrayList();
    }

    public OrdenDeProduccion(Calendar fechaOrigen, float cantidadAProducir, String unidadDeMedida, Calendar fechaEntregaProductoTerminado, String descripcion, Usuario unUsuario) {
        super(Evento.ESTADO_REGULAR, unUsuario);
        this.fechaOrigen = fechaOrigen;
        this.cantidadAProducir = cantidadAProducir;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaEntregaProductoTerminado = fechaEntregaProductoTerminado;
        this.descripcion = descripcion.toUpperCase();
        this.estado = ESTADO_REGULAR;
        this.ordenesCompraImplicadas = new ArrayList();
        
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

    public String getEstadoEvento() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void agregarOrdenDeCompra(OrdenDeCompra unaOrdenDeCompra){
        this.ordenesCompraImplicadas.add(unaOrdenDeCompra);
    }

    public boolean seEncuentraRegular(){
        return this.estado.equals(ESTADO_REGULAR);
    }
    public Object[] devolverVector() {
        Object[] vec ={this.getId(),this.getCantidadAProducir(), this.getUnidadDeMedida(), ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( this.getFechaOrigen().getTime() ), ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( this.getFechaEntregaProductoTerminado().getTime() ),this.getEstadoEvento()};
        return vec;
    }

    public ArrayList getOrdenesCompraImplicadasActivas(){
        ArrayList retorno = new ArrayList();
        Iterator ordenesC = this.ordenesCompraImplicadas.iterator();
        while (ordenesC.hasNext()){
            OrdenDeCompra unaOrdenCompra = (OrdenDeCompra) ordenesC.next();
            if (!unaOrdenCompra.seEncuentraAnulada())
                retorno.add(unaOrdenCompra);
        }
        return retorno;
    }
    
    boolean poseeOrdenesCompraImplicadasActivas() {
        return (!getOrdenesCompraImplicadasActivas().isEmpty());
    }
    
    public ArrayList getLotesAsociados(){ //Devuelve todos los lotes no anulados.
        ArrayList retorno = new ArrayList();
        Iterator ordenesDeCompra = this.ordenesCompraImplicadas.iterator();
        while (ordenesDeCompra.hasNext()){
            OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) ordenesDeCompra.next();
            if (!unaOrdenDeCompra.seEncuentraAnulada()){
                Iterator recorredorLotesOrdenesDeCompra = unaOrdenDeCompra.getLotesAsociados().iterator();
                while (recorredorLotesOrdenesDeCompra.hasNext()){
                    Lote unLote = (Lote) recorredorLotesOrdenesDeCompra.next();
                    if (!unLote.estaAnulado())
                        retorno.add(unLote);
                }    
            }
        }
        return retorno;
    }

    public void anular() {
        super.anularEsteEvento();
        this.estado = ESTADO_ANULADO;
        
    }

    public float getPesoRestanteAComprar() throws ExcepcionCargaParametros {
        float retorno = this.cantidadAProducir;
        Iterator ordenesDeCompra = this.ordenesCompraImplicadas.iterator();
        while (ordenesDeCompra.hasNext()){
            OrdenDeCompra unaOrden = (OrdenDeCompra) ordenesDeCompra.next();
            if (unaOrden.seEncuentraRegular())
                retorno = retorno - Organizacion.convertirUnidadPeso(unaOrden.getUnidadDeMedida(), unaOrden.getCantidadAComprar(), this.unidadDeMedida);
        }
        return retorno;
    }
}
