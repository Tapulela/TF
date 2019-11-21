/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Organizacion;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
    public static final String ESTADO_REGULAR = "Regular";
    
    public static final String TIPO_LOTE_YERBA_CANCHADA_VERDE = "YCV";
    public static final String TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA = "YCE";
    public static final String TIPO_LOTE_YERBA_CANCHADA_MOLIDA = "YM";
    
    private int id;
    private String etiqueta;
    private float cantidad;
    private String tipo_Lote;
    private String unidadDeMedida;
    private Calendar fechaAdquisicion;
    
    private OrdenDeProduccion ordenDeProduccionAsociada;
    private OrdenDeCompra ordenDeCompraAsociada;
    private Equipamiento equipamientoDondeReside;
    private MovimientoInternoMateriaPrima movimientoDeIngreso;
    private MovimientoInternoMateriaPrima ultimoMovimientoRegular;
    private AnalisisLaboratorio analisisDeIngreso;
    
    private ArrayList movimientosAsociados;
    private ArrayList transformacionesAsociadas;
    private ArrayList estacionamientosAsociados;
    private ArrayList analisisAsociados;
    
    private ArrayList<Evento> eventosImplicadosPosteriores;
    
    
    private String estado;

    public Lote(int id, String etiqueta, float cantidad, String tipo_Lote, String unidadDeMedida, String estado, OrdenDeCompra unaOrdenDeCompra, java.sql.Date fechaAdquisicion) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.cantidad = cantidad;
        this.tipo_Lote = tipo_Lote;
        this.unidadDeMedida = unidadDeMedida;
        this.estado = estado;
        
        this.fechaAdquisicion = Calendar.getInstance();
        this.fechaAdquisicion.setTime(fechaAdquisicion);
        
        //this.equipamientoDondeReside = unEquipamiento; //EL EQUIPAMIENTO DONDE RESIDE SE DEFINE A PARTIR DE LA RECUPERACIÓN DE MOVIMIENTOS
        this.ordenDeCompraAsociada = unaOrdenDeCompra;
        this.ordenDeProduccionAsociada = unaOrdenDeCompra.getOrdenDeProduccionAsociada();
        
        this.movimientosAsociados = new ArrayList();
        this.transformacionesAsociadas = new ArrayList();
        this.estacionamientosAsociados = new ArrayList();
        this.analisisAsociados = new ArrayList();
        this.eventosImplicadosPosteriores = new ArrayList();
    }

    public Lote(float cantidad, String tipo_Lote, String unidadDeMedida, Calendar fechaAdquisicion, OrdenDeCompra ordenDeCompraAsociada, Equipamiento equipamientoDondeReside) {
        this.cantidad = cantidad;
        this.tipo_Lote = tipo_Lote;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaAdquisicion = fechaAdquisicion;
        this.ordenDeCompraAsociada = ordenDeCompraAsociada;
        this.equipamientoDondeReside = equipamientoDondeReside;
        this.estado = ESTADO_REGULAR;
        this.ordenDeProduccionAsociada = ordenDeCompraAsociada.getOrdenDeProduccionAsociada();
        this.movimientosAsociados = new ArrayList();
        this.transformacionesAsociadas = new ArrayList();
        
        eventosImplicadosPosteriores = new ArrayList();
        this.transformacionesAsociadas = new ArrayList();
        this.estacionamientosAsociados = new ArrayList();
        this.analisisAsociados = new ArrayList();
    }

    public ArrayList<Evento> getEventosImplicadosPosteriores() {
        return eventosImplicadosPosteriores;
    }

    

    public MovimientoInternoMateriaPrima getMovimientoDeIngreso() {
        return movimientoDeIngreso;
    }

    public void setMovimientoDeIngreso(MovimientoInternoMateriaPrima movimientoDeIngreso) {
        this.movimientoDeIngreso = movimientoDeIngreso;
    }

    public MovimientoInternoMateriaPrima getUltimoMovimientoRegular() {
        return ultimoMovimientoRegular;
    }

    public void setUltimoMovimientoRegular(MovimientoInternoMateriaPrima ultimoMovimientoRegular) {
        this.ultimoMovimientoRegular = ultimoMovimientoRegular;
    }

    
    public java.sql.Date getFechaAdquisicion() {
        return new Date(this.fechaAdquisicion.getTimeInMillis());
    }
    
    public Calendar getFechaAdquisicionC() {
        return this.fechaAdquisicion;
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

    public AnalisisLaboratorio getAnalisisDeIngreso() {
        return analisisDeIngreso;
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
    
    public boolean estaRegular(){
        return this.estado.equals(ESTADO_REGULAR);
    }

    boolean estaAnulado() {
        boolean retorno = false;
        retorno = (this.estado != null && this.estado.equals(ESTADO_ANULADO));
        return retorno;
    }


    public void asignarUltimoMovimiento(MovimientoInternoMateriaPrima unMovimiento) {
        this.ultimoMovimientoRegular = unMovimiento;
    }
    
    public void agregarMovimiento (MovimientoInternoMateriaPrima unMovimiento){
        if (!unMovimiento.poseeEquipamientoOrigen())
            this.movimientoDeIngreso = unMovimiento; //SE TRATA DE UN MOVIMIENTO DE INGRESO
        this.movimientosAsociados.add(unMovimiento);
        agregarEvento(unMovimiento);
    }
    
    public void agregarEvento(Evento unEvento){
        this.eventosImplicadosPosteriores.add(unEvento);
    }
    
    public void agregarEstacionamiento (Estacionamiento unEstacionamiento){
        this.estacionamientosAsociados.add(unEstacionamiento);
        this.transformacionesAsociadas.add(unEstacionamiento);
        agregarEvento(unEstacionamiento);
    }
    
    public boolean poseeUnoOMasEventosRegularesPosterioresA(Evento unEventoAComparar){
        boolean seEncontroEventoRegularPosterior = false;
        Iterator eventos = this.eventosImplicadosPosteriores.iterator();
        System.out.println("mi id de evento: "+unEventoAComparar.getIdEvento());
        while (eventos.hasNext() && !seEncontroEventoRegularPosterior){
            Evento unEvento = (Evento) eventos.next();
            System.err.println("Id Evento: "+unEvento.getIdEvento()+", estado: "+unEvento.getEstadoEvento());
            seEncontroEventoRegularPosterior = (unEvento.eventoEstaRegular() && unEvento.esPosteriorA(unEventoAComparar));
        }
        return seEncontroEventoRegularPosterior;
    }

    /*public boolean poseeUnoOMasMovimientosAsociadosRegulares() {
        //SE IGNORA EL MOVIMIENTO DE INGRESO.
        boolean seEncontroMovimientoRegular = false;
        Iterator movimientos = this.movimientosAsociados.iterator();
        while (movimientos.hasNext() && !seEncontroMovimientoRegular){
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) movimientos.next();
            seEncontroMovimientoRegular = (unMovimiento.estaRegular() && unMovimiento.poseeEquipamientoOrigen());
        }
        return seEncontroMovimientoRegular;
    }
    boolean poseeUnoOMasEventosAsociadosRegularesPosteriores() {
        boolean seEncontroEventoRegular = false;
        Iterator eventos = this.movimientosAsociados.iterator();
        while (eventos.hasNext() && !seEncontroEventoRegular){
            Evento unEvento = (Evento) eventos.next();
            seEncontroEventoRegular = unEvento.eventoEstaRegular();
        }
        return seEncontroEventoRegular;
    }*/

    public MovimientoInternoMateriaPrima calcularUltimoMovimientoRegular() {
        MovimientoInternoMateriaPrima retorno = null;
        int idEvento = 0;
        Iterator movimientos = this.movimientosAsociados.iterator();
        while (movimientos.hasNext()){
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) movimientos.next();
            if (unMovimiento.estaRegular() && unMovimiento.getIdEvento()>idEvento)
                retorno = unMovimiento;
        }
        return retorno;
    }

    public void setEquipamientoDondeReside(Equipamiento equipamientoDondeReside) {
        this.equipamientoDondeReside = equipamientoDondeReside;
    }
    
    

    public boolean poseeOrdenDeProduccionAsociada(OrdenDeProduccion unaOrdenProduccionSeleccionada) {
        return this.ordenDeProduccionAsociada.equals(unaOrdenProduccionSeleccionada);
    }

    boolean poseeEtiqueta(String unaEtiqueta) {
        return this.etiqueta.contains(unaEtiqueta);
    }

    public boolean poseeOrdenDeCompraAsociada(OrdenDeCompra unaOrdenCompraSeleccionada) {
        return this.ordenDeCompraAsociada.equals(unaOrdenCompraSeleccionada);
    }

    public boolean poseeProveedorAsociado(Proveedor unProveedorSeleccionado) {
        return this.ordenDeCompraAsociada.getProveedorAsociado().equals(unProveedorSeleccionado);
    }

    public boolean ultimoMovimientoEstaEntre(Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) {
        Calendar fechaUltimoMovimiento = this.ultimoMovimientoRegular.getFechaOrigenC();
        
        fechaOrigenInferior.set(Calendar.HOUR, 0);
        fechaOrigenInferior.set(Calendar.MINUTE, 0);
        fechaOrigenInferior.set(Calendar.SECOND, 0);
        fechaOrigenInferior.set(Calendar.MILLISECOND, 0);

        fechaOrigenSuperior.set(Calendar.HOUR_OF_DAY, 24);
        fechaOrigenSuperior.set(Calendar.MINUTE, 59);
        fechaOrigenSuperior.set(Calendar.SECOND, 59);
        
        return ((fechaUltimoMovimiento.compareTo(fechaOrigenInferior)>=0 )&& fechaUltimoMovimiento.compareTo(fechaOrigenSuperior)<=0);
        //return (fechaUltimoMovimiento.after(fechaOrigenInferior) && fechaUltimoMovimiento.before(fechaOrigenSuperior));
    }
    
    public Object[] devolverVector() {
        String fechaLlegada = ( new SimpleDateFormat( "dd/MM/yyyy" ) ).format( this.ultimoMovimientoRegular.getFechaOrigenC().getTime() );
        Object[] vec ={this.getId(), this.getEstado(), ordenDeProduccionAsociada.getId(), ordenDeCompraAsociada.getId(), this.ordenDeCompraAsociada.getProveedorAsociado().getRazonSocial(), fechaLlegada, etiqueta};
        return vec;
    }    
    
    public Object[] devolverVectorEstacionamiento() {
                Object[] vec ={this.getId(), etiqueta, ordenDeProduccionAsociada.getId(), ordenDeCompraAsociada.getId()};
        return vec;
    }   

    public void anular() {
        this.estado = ESTADO_ANULADO;
    }

    boolean esDeYerbaCancadaVerde() {
        return this.tipo_Lote.equals(TIPO_LOTE_YERBA_CANCHADA_VERDE);
    }

    void estacionar() {
        this.tipo_Lote = TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA;
    }

    void anularEstacionamiento() {
        this.tipo_Lote = TIPO_LOTE_YERBA_CANCHADA_VERDE;
    }

    public void agregarAnalisis(AnalisisLaboratorio unAnalisis) {
        if (this.analisisAsociados.isEmpty()){
            this.analisisDeIngreso = unAnalisis;
        }
        this.analisisAsociados.add(unAnalisis);
        agregarEvento(unAnalisis);
    }

    void removerAnalisis(AnalisisLaboratorio unAnalisis) {
        this.analisisAsociados.remove(unAnalisis);
        removerEvento(unAnalisis);
    }

    private void removerEvento(Evento unEvento) {
        this.eventosImplicadosPosteriores.remove(unEvento);
    }

    public ArrayList getAnalisisAsociados() {
        return analisisAsociados;
    }

    public boolean poseeAnalisisDeYCVRegularYAprobado() {
        boolean seEncontro = false;
        Iterator analisis = this.analisisAsociados.iterator();
        while (analisis.hasNext()){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) analisis.next();
            seEncontro = (unAnalisis.estaRegular() && unAnalisis.estaAprobado() && unAnalisis.esDeYerbaCanchadaVerde());
        }
        return seEncontro;
    }


    
    
    
}
