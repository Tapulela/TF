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
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class OrdenDeCompra {
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    private int id;
    private Calendar fechaOrigen;
    private float cantidadAComprar;
    private String unidadDeMedida;
    private float costoPorUnidad;
    private String estado;
    
    private ArrayList lotesAsociados;
    private Proveedor proveedorAsociado;
    private OrdenDeProduccion ordenDeProduccionAsociada;

    public OrdenDeCompra(int id, java.sql.Date fechaOrigen, float cantidadAComprar, String unidadDeMedida, float costoPorUnidad, String estado, Proveedor proveedorAsociado, OrdenDeProduccion unaOrdenDeProduccion) {
        this.id = id;
        this.fechaOrigen = Calendar.getInstance();
        this.fechaOrigen.setTime(fechaOrigen);
        this.cantidadAComprar = cantidadAComprar;
        this.unidadDeMedida = unidadDeMedida;
        this.costoPorUnidad = costoPorUnidad;
        this.estado = estado;
        this.proveedorAsociado = proveedorAsociado;
        this.ordenDeProduccionAsociada = unaOrdenDeProduccion;
        this.lotesAsociados = new ArrayList();
    }
    
    public OrdenDeCompra(float cantidadComprada, String unidadDeMedida, float costoPorUnidad, Proveedor proveedorAsociado, OrdenDeProduccion ordenDeProduccionAsociada) {
        this.fechaOrigen = Calendar.getInstance();
        this.cantidadAComprar = cantidadComprada;
        this.unidadDeMedida = unidadDeMedida;
        this.costoPorUnidad = costoPorUnidad;
        this.proveedorAsociado = proveedorAsociado;
        this.estado = ESTADO_REGULAR;
        this.ordenDeProduccionAsociada = ordenDeProduccionAsociada;
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

    public float getCantidadAComprar() {
        return cantidadAComprar;
    }

    public void setCantidadAComprar(float cantidadAComprar) {
        this.cantidadAComprar = cantidadAComprar;
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
    public boolean poseeLotesAsociados(){
        return (!this.lotesAsociados.isEmpty());
    }
    public boolean poseeLotesRegularesAsociados(){
        boolean retorno = false;
        Iterator recorredorLotes = this.lotesAsociados.iterator();
        while (!retorno && recorredorLotes.hasNext()){
            Lote unLote = (Lote) recorredorLotes.next();
            retorno = !unLote.estaAnulado();
        }
        return retorno;
    }
    
    
    boolean seEncuentraRegular(){
        return this.estado.equals(ESTADO_REGULAR);
    }
    
    boolean seEncuentraAnulada() {
        return this.estado.equals(ESTADO_ANULADO);
    }
    
    public void anular(){
        this.estado = ESTADO_ANULADO;
    }

    public Calendar getFechaOrigenC() {
        return this.fechaOrigen;
    }

    Boolean poseeOrdenDeProduccionAsociada(OrdenDeProduccion unaOrdenDeProduccion) {
        return this.ordenDeProduccionAsociada==unaOrdenDeProduccion;
    }

    Boolean origenEstaEntre(Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) {
        return (this.fechaOrigen.after(fechaOrigenInferior) && this.fechaOrigen.before(fechaOrigenSuperior));
    }
    
    public Object[] devolverVector() {
        String unProveedor = "No posee";
        if (this.proveedorAsociado != null){
            unProveedor = this.proveedorAsociado.getRazonSocial();
        }
        Object[] vec ={this.getId(), ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( this.fechaOrigen.getTime() ), this.getCantidadAComprar(), this.getUnidadDeMedida(), this.getCostoPorUnidad(),this.getEstado(), this.getOrdenDeProduccionAsociada().getId(), unProveedor};
        return vec;
    }
    
    private ArrayList getLotesAsociadosNoAnulados() {
        ArrayList retorno = new ArrayList();
        Iterator lotes = this.lotesAsociados.iterator();
        while (lotes.hasNext()){
            Lote unLote = (Lote) lotes.next();
            if (!unLote.estaAnulado())
                retorno.add(unLote);
        }
        return retorno;
    }

    float getCantidadComprada(String unaUnidadMedida) throws ExcepcionCargaParametros {
        float retorno = 0;
        Iterator lotesNoAnulados = getLotesAsociadosNoAnulados().iterator();
        while (lotesNoAnulados.hasNext()){
            Lote unLote = (Lote) lotesNoAnulados.next();
            retorno = retorno + Organizacion.convertirUnidadPeso(unLote.getUnidadDeMedida(), unLote.getCantidad(), unaUnidadMedida);
        }
        return retorno;
    }

    public float getCantidadRestanteARecibir() throws ExcepcionCargaParametros {
        float retorno = this.cantidadAComprar - this.getCantidadComprada(this.unidadDeMedida);
        
        return retorno;
    }
    
    
}
