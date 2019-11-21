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
    private float cantidadAProducir;
    private String unidadDeMedida;
    private Calendar fechaEntregaProductoTerminado;
    private String estado;
    private String descripcion;
    
    private ArrayList ordenesCompraImplicadas;
    private ArrayList estacionamientos;
    private ArrayList moliendas;
    private ArrayList analisisLaboratorio;
    private ArrayList eventosImplicados;
    private ArrayList criteriosDeAnalisisAsociados;
    
    public OrdenDeProduccion(int id, java.sql.Date fechaOrigen, float cantidadAProducir, String unidadDeMedida, java.sql.Date fechaEntregaProductoTerminado, String estado, String unaDescripcion, int idEvento, Usuario unUsuario) {
        super(idEvento, estado, unUsuario, fechaOrigen);
        this.id = id;
        this.cantidadAProducir = cantidadAProducir;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaEntregaProductoTerminado = Calendar.getInstance();
        this.fechaEntregaProductoTerminado.setTime(fechaOrigen);
        
        this.estado = estado;
        this.descripcion = unaDescripcion;
        
        this.ordenesCompraImplicadas = new ArrayList();
        this.analisisLaboratorio = new ArrayList();
        this.estacionamientos = new ArrayList();
        this.moliendas = new ArrayList();
        this.eventosImplicados = new ArrayList();
        this.criteriosDeAnalisisAsociados = new ArrayList();
    }

    public OrdenDeProduccion(Calendar fechaOrigen, float cantidadAProducir, String unidadDeMedida, Calendar fechaEntregaProductoTerminado, String descripcion, Usuario unUsuario) {
        super(Evento.ESTADO_REGULAR, unUsuario);
        this.cantidadAProducir = cantidadAProducir;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaEntregaProductoTerminado = fechaEntregaProductoTerminado;
        this.descripcion = descripcion.toUpperCase();
        this.estado = ESTADO_REGULAR;
        
        this.ordenesCompraImplicadas = new ArrayList();
        this.analisisLaboratorio = new ArrayList();
        this.estacionamientos = new ArrayList();
        this.moliendas = new ArrayList();
        this.eventosImplicados = new ArrayList();
        this.criteriosDeAnalisisAsociados = new ArrayList();
    }

    public ArrayList getCriteriosDeAnalisisAsociados() {
        return criteriosDeAnalisisAsociados;
    }

    public ArrayList getEventosImplicados() {
        return eventosImplicados;
    }

    public void setEventosImplicados(ArrayList eventosImplicados) {
        this.eventosImplicados = eventosImplicados;
    }

    public ArrayList getEstacionamientos() {
        return estacionamientos;
    }

    public ArrayList getMoliendas() {
        return moliendas;
    }

    public ArrayList getAnalisisLaboratorio() {
        return analisisLaboratorio;
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


    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    private void agregarOrdenDeCompra(OrdenDeCompra unaOrdenDeCompra){
        this.ordenesCompraImplicadas.add(unaOrdenDeCompra);
    }
    
    private void agregarEstacionamiento (Estacionamiento unEstacionamiento){
        this.estacionamientos.add(unEstacionamiento);
        
    }
    private void agregarMolienda (Molienda unaMolienda){
        this.moliendas.add(unaMolienda);
        
    }
    private void agregarAnalisisLaboratior (AnalisisLaboratorio unAnalisis){
        this.analisisLaboratorio.add(unAnalisis);
    }
    

    public void agregarEvento (Evento unEvento){//Este metodo se encarga de agregar al contenedor correspondiente
        
        if (eventosImplicados.contains(unEvento)){
            return;
        }
        this.eventosImplicados.add(unEvento);
        if (unEvento instanceof OrdenDeCompra)
            agregarOrdenDeCompra((OrdenDeCompra) unEvento);
        if (unEvento instanceof Estacionamiento)
            agregarEstacionamiento((Estacionamiento) unEvento);
        if (unEvento instanceof Molienda)
            agregarMolienda((Molienda) unEvento);
        if (unEvento instanceof AnalisisLaboratorio)
            agregarAnalisisLaboratior((AnalisisLaboratorio) unEvento);
        
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

    boolean entregaEstaEntre(Calendar fechaEntregaInferior, Calendar fechaEntregaSuperior) {
        fechaEntregaInferior.set(Calendar.HOUR, 0);
        fechaEntregaInferior.set(Calendar.MINUTE, 0);
        fechaEntregaInferior.set(Calendar.SECOND, 0);

        fechaEntregaSuperior.set(Calendar.HOUR_OF_DAY, 24);
        fechaEntregaSuperior.set(Calendar.MINUTE, 59);
        fechaEntregaSuperior.set(Calendar.SECOND, 59);        
        return (this.fechaEntregaProductoTerminado.after(fechaEntregaInferior) && this.fechaEntregaProductoTerminado.before(fechaEntregaSuperior));
    }

    public void agregarCriterioLaboratorio(CriterioAnalisisLaboratorio unCriterio) {
        this.criteriosDeAnalisisAsociados.add(unCriterio);
    }

    void removerTodosLosCriterios() {
        this.criteriosDeAnalisisAsociados = new ArrayList();
    }

    void removerEvento(Evento unEvento) {
        this.eventosImplicados.remove(unEvento);
        if (unEvento instanceof AnalisisLaboratorio)
            removerAnalisisDeLaboratorio((AnalisisLaboratorio) unEvento);
        
    }

    private void removerAnalisisDeLaboratorio(AnalisisLaboratorio unAnalisis) {
        this.analisisLaboratorio.remove(unAnalisis);
    }
}
