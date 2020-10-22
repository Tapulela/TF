/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.Organizacion;
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
public class Lote {
    public static final String ESTADO_ANULADO = "Anulado";
    public static final String ESTADO_REGULAR = "Regular";
    
    public static final String TIPO_LOTE_YERBA_CANCHADA_VERDE = "YCV";
    public static final String TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA = "YCE";
    public static final String TIPO_LOTE_YERBA_CANCHADA_MOLIDA = "YM";
    
    public static final String[] TIPO_LOTES = {TIPO_LOTE_YERBA_CANCHADA_VERDE, TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA, TIPO_LOTE_YERBA_CANCHADA_MOLIDA};
    
    public static final String UNIDAD_MEDIDA_KILOGRAMO = "Kilogramo";
    public static final String UNIDAD_MEDIDA_Tranporte = "Bolsas";
    private int id;
    private String etiqueta;
    private float cantidadTotalPesoIngresado;
    private String tipo_Lote;
    private String unidadDeMedida;
    private Calendar fechaAdquisicion;
    private String unidadDeMedidaTransporte;
    private int cantidadTotalUnidadesDeTransporteIngresadas;
    
    private OrdenDeProduccion ordenDeProduccionAsociada;
    private OrdenDeCompra ordenDeCompraAsociada;
    private Equipamiento equipamientoDondeReside;
    private MovimientoInternoMateriaPrima movimientoDeIngreso;
    private MovimientoInternoMateriaPrima ultimoMovimientoRegular;
    private AnalisisLaboratorio analisisDeIngreso;
    
    private ArrayList movimientosAsociados;
    private ArrayList transformacionesAsociadas;
    private ArrayList estacionamientosAsociados;
    private ArrayList moliendasAsociadas;
    private ArrayList analisisAsociados;
    private ArrayList salidasAsociadas;
    
    private ArrayList<Evento> eventosImplicadosPosteriores;
    
    
    private String estado;

    public Lote(int id, String etiqueta, float cantidad, String tipo_Lote, String unidadDeMedida, String estado, OrdenDeCompra unaOrdenDeCompra, java.sql.Date fechaAdquisicion, String unidadDeMedidaTransporte, int cantidadUnidadesDeTransporte) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.cantidadTotalPesoIngresado = cantidad;
        this.tipo_Lote = tipo_Lote;
        this.unidadDeMedida = unidadDeMedida;
        this.estado = estado;
        
        this.unidadDeMedidaTransporte = unidadDeMedidaTransporte;
        this.cantidadTotalUnidadesDeTransporteIngresadas = cantidadUnidadesDeTransporte;
        
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
        this.salidasAsociadas = new ArrayList();
        this.moliendasAsociadas = new ArrayList();
    }

    public Lote(float cantidad, String tipo_Lote, String unidadDeMedida, Calendar fechaAdquisicion, OrdenDeCompra ordenDeCompraAsociada, Equipamiento equipamientoDondeReside, String unidadDeMedidaTransporte, int cantidadUnidadesDeTransporte) {
        this.cantidadTotalPesoIngresado = cantidad;
        this.tipo_Lote = tipo_Lote;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaAdquisicion = fechaAdquisicion;
        this.ordenDeCompraAsociada = ordenDeCompraAsociada;
        this.equipamientoDondeReside = equipamientoDondeReside;
        this.estado = ESTADO_REGULAR;
        this.ordenDeProduccionAsociada = ordenDeCompraAsociada.getOrdenDeProduccionAsociada();
        this.unidadDeMedidaTransporte = unidadDeMedidaTransporte;
        this.cantidadTotalUnidadesDeTransporteIngresadas = cantidadUnidadesDeTransporte;
        
        this.movimientosAsociados = new ArrayList();
        eventosImplicadosPosteriores = new ArrayList();
        this.transformacionesAsociadas = new ArrayList();
        this.estacionamientosAsociados = new ArrayList();
        this.analisisAsociados = new ArrayList();
        this.salidasAsociadas = new ArrayList();
        this.moliendasAsociadas = new ArrayList();
    }

    public ArrayList getSalidasAsociadas() {
        return salidasAsociadas;
    }
    
    public ArrayList<Evento> getEventosImplicadosPosteriores() {
        return eventosImplicadosPosteriores;
    }

    public String getUnidadDeMedidaTransporte() {
        return unidadDeMedidaTransporte;
    }

    public int getCantidadTotalUnidadesDeTransporteIngresadas() {
        return cantidadTotalUnidadesDeTransporteIngresadas;
    }

    public ArrayList getEstacionamientosAsociados() {
        return estacionamientosAsociados;
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

    public float getCantidadTotalPesoIngresado() {
        return cantidadTotalPesoIngresado;
    }

    public void setCantidadTotalPesoIngresado(float cantidadTotalPesoIngresado) {
        this.cantidadTotalPesoIngresado = cantidadTotalPesoIngresado;
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
        if (this.ultimoMovimientoRegular != null)
            this.ultimoMovimientoRegular.setMovimientoInmediatamentePosterior(unMovimiento);
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
    
    public void agregarMolienda (Molienda unaMolienda){
        this.moliendasAsociadas.add(unaMolienda);
        this.transformacionesAsociadas.add(unaMolienda);
        agregarEvento(unaMolienda);
    }
    
    public boolean poseeUnoOMasEventosRegularesPosterioresA(Evento unEventoAComparar){
        boolean seEncontroEventoRegularPosterior = false;
        Iterator eventos = this.eventosImplicadosPosteriores.iterator();
        //System.out.println("mi id de evento: "+unEventoAComparar.getIdEvento());
        while (eventos.hasNext() && !seEncontroEventoRegularPosterior){
            Evento unEvento = (Evento) eventos.next();
            //System.err.println("Id Evento: "+unEvento.getIdEvento()+", estado: "+unEvento.getEstadoEvento());
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
    
    public Object[] devolverVector() throws ExcepcionCargaParametros {
        String fechaLlegada = ( new SimpleDateFormat( "dd/MM/yyyy" ) ).format( this.ultimoMovimientoRegular.getFechaOrigenC().getTime() );
        Object[] vec ={this.getId(), this.getEstado(), ordenDeProduccionAsociada.getId(), ordenDeCompraAsociada.getId(), this.ordenDeCompraAsociada.getProveedorAsociado().getRazonSocial(), fechaLlegada, etiqueta, tipo_Lote, getCantidadUnidadesDisponibleParaTransformar(), UtilidadesInterfazGrafica.formatearFlotante(getCantidadDisponibleParaTransformarKg())+" Kg(s)."};
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

    public boolean esDeYerbaCancadaVerde(Calendar unaFecha) {
        boolean seEncontroTransformacionPosterior = false;
        boolean seEncontroEstacionamiento = false;
        Iterator estacionamientos = this.estacionamientosAsociados.iterator();
        while (estacionamientos.hasNext() && !seEncontroEstacionamiento){
            Estacionamiento unEstacionamiento = (Estacionamiento) estacionamientos.next();
            seEncontroEstacionamiento = unEstacionamiento.estaRegular() && unEstacionamiento.esAnteriorOIgualA(unaFecha);
        }
        if (seEncontroEstacionamiento)
            seEncontroTransformacionPosterior = true;
        boolean seEncontroMolienda = false;
        Iterator moliendas = this.moliendasAsociadas.iterator();
        while (moliendas.hasNext() && !seEncontroMolienda){
            Molienda unaMolienda = (Molienda) moliendas.next();
            seEncontroMolienda = unaMolienda.estaRegular() && unaMolienda.esAnteriorOIgualA(unaFecha);
        }
        if (seEncontroMolienda)
            seEncontroTransformacionPosterior = true;
        return !seEncontroTransformacionPosterior;
    }
    
    void estacionar() {
        this.tipo_Lote = TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA;
    }
    
    void moler() {
        this.tipo_Lote = TIPO_LOTE_YERBA_CANCHADA_MOLIDA;
    }

    void anularEstacionamiento() {
        this.tipo_Lote = TIPO_LOTE_YERBA_CANCHADA_VERDE;
    }
    
    void anularMolienda() {
        this.tipo_Lote = TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA;
    }

    public void agregarEgreso(Egreso unEgreso) {
        this.salidasAsociadas.add(unEgreso);
        agregarEvento(unEgreso);
    }
    
    public void agregarMerma(Merma unaMerma) {
        this.salidasAsociadas.add(unaMerma);
        //agregarEvento(unEgreso); NO SE AGREGA A EVENTO DE LOTE PORQUE NECESITO IGNORAR ESTE HECHO PARA HACER UNA ANULACION DE UN MOVIMIENTO.
    }
    
    public void agregarPerdida(Perdida unaPerdida) {
        this.salidasAsociadas.add(unaPerdida);
        //agregarEvento(unEgreso); NO SE AGREGA A EVENTO DE LOTE PORQUE NECESITO IGNORAR ESTE HECHO PARA HACER UNA ANULACION DE UN MOVIMIENTO.
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
        while (analisis.hasNext() && !seEncontro){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) analisis.next();
            seEncontro = (unAnalisis.estaRegular() && unAnalisis.estaAprobado() && unAnalisis.esDeYerbaCanchadaVerde());
        }
        return seEncontro;
    }

    public boolean esDeYerbaCancadaEstacionada() {
        return this.tipo_Lote.equals(TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA);
    }

    public boolean poseeAnalisisDeYCERegularYAprobado() {
        boolean seEncontro = false;
        Iterator analisis = this.analisisAsociados.iterator();
        while (analisis.hasNext() && !seEncontro){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) analisis.next();
            seEncontro = (unAnalisis.estaRegular() && unAnalisis.estaAprobado() && unAnalisis.esDeYerbaCanchadaEstacionada());
        }
        return seEncontro;
    }

    public Float getCantidadDisponibleParaTransformarKg() throws ExcepcionCargaParametros {
        Float cantidadDisponible = Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.cantidadTotalPesoIngresado, Lote.UNIDAD_MEDIDA_KILOGRAMO);
        
        Iterator transformaciones = this.transformacionesAsociadas.iterator();
        while (transformaciones.hasNext()){
            Transformacion unaTransformacion = (Transformacion) transformaciones.next();
            if (unaTransformacion.estaRegular() && unaTransformacion.getTipoLoteEntrada().equals(this.tipo_Lote)){
                DetalleTransformacion unDetalle = unaTransformacion.getDetalleAsociadoALote(this);
                cantidadDisponible = cantidadDisponible - Organizacion.convertirUnidadPeso(unDetalle.getUnidadMedidaPeso(), unDetalle.getPesoUtilizdo(), "Kilogramo");
            }
        }
        Iterator salidas = this.salidasAsociadas.iterator();
        while (salidas.hasNext()){
            Salida unaSalida = (Salida) salidas.next();
            if (unaSalida.estaRegular()){
                cantidadDisponible = cantidadDisponible - Organizacion.convertirUnidadPeso(unaSalida.getUnidadMedidaPeso(), unaSalida.getPesoUtilizdo(), "Kilogramo");
            }
        }
        return cantidadDisponible;
    }
    public int getCantidadUnidadesDisponibleParaTransformar() {
        int cantidadDisponible = this.cantidadTotalUnidadesDeTransporteIngresadas;
        Iterator transformaciones = this.transformacionesAsociadas.iterator();
        while (transformaciones.hasNext()){
            Transformacion unaTransformacion = (Transformacion) transformaciones.next();
            if (unaTransformacion.estaRegular() && unaTransformacion.getTipoLoteEntrada().equals(this.tipo_Lote)){
                DetalleTransformacion unDetalle = unaTransformacion.getDetalleAsociadoALote(this);
                cantidadDisponible = cantidadDisponible - unDetalle.getCantidadUtilizada();
            }
        }
        Iterator salidas = this.salidasAsociadas.iterator();
        while (salidas.hasNext()){
            Salida unaSalida = (Salida) salidas.next();
            if (unaSalida.estaRegular()){
                cantidadDisponible = cantidadDisponible - unaSalida.getCantidadBolsasUtilizada();
            }
        }
        return cantidadDisponible;
    }
    
    public Float getCantidadDisponibleParaMolerKg() {
        Float cantidadDisponible = Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.cantidadTotalPesoIngresado, "Kilogramo");
        Iterator moliendas = this.moliendasAsociadas.iterator();
        while (moliendas.hasNext()){
            Molienda unaMolienda = (Molienda) moliendas.next();
            if (unaMolienda.estaRegular()){
                DetalleTransformacion unDetalle = unaMolienda.getDetalleAsociadoALote(this);
                cantidadDisponible = cantidadDisponible - Organizacion.convertirUnidadPeso(unDetalle.getUnidadMedidaPeso(), unDetalle.getPesoUtilizdo(), "Kilogramo");
            }
                
        }
        Iterator salidas = this.salidasAsociadas.iterator();
        while (salidas.hasNext()){
            Salida unaSalida = (Salida) salidas.next();
            if (unaSalida.estaRegular()){
                cantidadDisponible = cantidadDisponible - Organizacion.convertirUnidadPeso(unaSalida.getUnidadMedidaPeso(), unaSalida.getPesoUtilizdo(), "Kilogramo");
            }
        }
        return cantidadDisponible;
    }
    
    public Float getCantidadDisponibleParaMolerKg(Calendar unaFecha) {
        Float cantidadDisponible = Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.cantidadTotalPesoIngresado, "Kilogramo");
        Iterator moliendas = this.moliendasAsociadas.iterator();
        while (moliendas.hasNext()){
            Molienda unaMolienda = (Molienda) moliendas.next();
            if (unaMolienda.estaRegular() && unaMolienda.esAnteriorOIgualA(unaFecha)){
                DetalleTransformacion unDetalle = unaMolienda.getDetalleAsociadoALote(this);
                cantidadDisponible = cantidadDisponible - Organizacion.convertirUnidadPeso(unDetalle.getUnidadMedidaPeso(), unDetalle.getPesoUtilizdo(), "Kilogramo");
            }
                
        }
        Iterator salidas = this.salidasAsociadas.iterator();
        while (salidas.hasNext()){
            Salida unaSalida = (Salida) salidas.next();
            if (unaSalida.estaRegular() && unaSalida.esAnteriorOIgualA(unaFecha)){
                cantidadDisponible = cantidadDisponible - Organizacion.convertirUnidadPeso(unaSalida.getUnidadMedidaPeso(), unaSalida.getPesoUtilizdo(), "Kilogramo");
            }
        }
        return cantidadDisponible;
    }

    public int getCantidadUnidadesDisponibleParaMoler() {
        int cantidadDisponible = this.cantidadTotalUnidadesDeTransporteIngresadas;
        Iterator moliendas = this.moliendasAsociadas.iterator();
        while (moliendas.hasNext()){
            Molienda unaMolienda = (Molienda) moliendas.next();
            if (unaMolienda.estaRegular()){
                DetalleTransformacion unDetalle = unaMolienda.getDetalleAsociadoALote(this);
                cantidadDisponible = cantidadDisponible - unDetalle.getCantidadUtilizada();
            }
        }
        Iterator salidas = this.salidasAsociadas.iterator();
        while (salidas.hasNext()){
            Salida unaSalida = (Salida) salidas.next();
            if (unaSalida.estaRegular()){
                cantidadDisponible = cantidadDisponible - unaSalida.getCantidadBolsasUtilizada();
            }
        }
        return cantidadDisponible;
    }

    public Float obtenerRazonKgBolsa() {
        Float retorno = 0f;
        if (this.obtenerUnidadesTransporteIngresadoConPerdidasIncluidas() != 0)
            retorno = Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.obtenerPesoIngresadoConPerdidasIncluidasKg(), Lote.UNIDAD_MEDIDA_KILOGRAMO)/obtenerUnidadesTransporteIngresadoConPerdidasIncluidas();
        return retorno;
    }

    public boolean esDeTipo(String unTipoLote) {
        return this.tipo_Lote.equals(unTipoLote);
    }

    public boolean poseeUnUltimoEstacionamientoSinMerma() {
        boolean seEncontroUnUltimoEstacionamientoSinMerma = false;
        Estacionamiento unEstacionamiento = this.obtenerUltimoEstacionamientoRegular();
        if (unEstacionamiento != null && !unEstacionamiento.poseeMermaAsociadaRegular(this))
            seEncontroUnUltimoEstacionamientoSinMerma = true;
        return seEncontroUnUltimoEstacionamientoSinMerma;
            
    }

    public Estacionamiento obtenerUltimoEstacionamientoRegular() {
        Estacionamiento ultimoEstacionamiento = null;
        Iterator recorredorEstacionamientos = this.estacionamientosAsociados.iterator();
        while (recorredorEstacionamientos.hasNext()){
            Estacionamiento unEstacionamiento = (Estacionamiento) recorredorEstacionamientos.next();
            if (unEstacionamiento.esPosteriorA(ultimoEstacionamiento) && unEstacionamiento.estaRegular()){
                ultimoEstacionamiento = unEstacionamiento;
            }
        }
        return ultimoEstacionamiento;
    }

    public float obtenerPesoIngresadoConPerdidasIncluidasKg() {
        Float retorno = Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.cantidadTotalPesoIngresado, UNIDAD_MEDIDA_KILOGRAMO);
        Iterator recorredorSalidas = this.salidasAsociadas.iterator();
        while (recorredorSalidas.hasNext()){
            Salida unaSalida = (Salida) recorredorSalidas.next();
            if (unaSalida.estaRegular() && !(unaSalida instanceof Egreso))
                retorno = retorno - Organizacion.convertirUnidadPeso(unaSalida.getUnidadMedidaPeso(), unaSalida.getPesoUtilizdo(), UNIDAD_MEDIDA_KILOGRAMO);
            if (unaSalida.estaRegular() && unaSalida instanceof Egreso)
                retorno = retorno - Organizacion.convertirUnidadPeso(unaSalida.getUnidadMedidaPeso(), ((Egreso)unaSalida).getPesoUtilizdo(this), UNIDAD_MEDIDA_KILOGRAMO);
        }
        return Math.max(retorno, 0);
    }
    
    
    public float obtenerPesoIngresadoConPerdidasIncluidasKg(Calendar unaFecha) {
        Float retorno = Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.cantidadTotalPesoIngresado, UNIDAD_MEDIDA_KILOGRAMO);
        Iterator recorredorSalidas = this.salidasAsociadas.iterator();
        while (recorredorSalidas.hasNext()){
            Salida unaSalida = (Salida) recorredorSalidas.next();
            if (unaSalida.estaRegular() && unaSalida.esAnteriorOIgualA(unaFecha) && !(unaSalida instanceof Egreso))
                retorno = retorno - Organizacion.convertirUnidadPeso(unaSalida.getUnidadMedidaPeso(), unaSalida.getPesoUtilizdo(), UNIDAD_MEDIDA_KILOGRAMO);
            if (unaSalida.estaRegular() && unaSalida.esAnteriorOIgualA(unaFecha) && unaSalida instanceof Egreso)
                retorno = retorno - Organizacion.convertirUnidadPeso(unaSalida.getUnidadMedidaPeso(), ((Egreso)unaSalida).getPesoUtilizdo(this), UNIDAD_MEDIDA_KILOGRAMO);
        }
        return Math.max(retorno, 0);
    }
    
    public int obtenerUnidadesTransporteIngresadoConPerdidasIncluidas() {
        int retorno = this.cantidadTotalUnidadesDeTransporteIngresadas;
        Iterator recorredorSalidas = this.salidasAsociadas.iterator();
        while (recorredorSalidas.hasNext()){
            Salida unaSalida = (Salida) recorredorSalidas.next();
            if (unaSalida.estaRegular())
                retorno = retorno - unaSalida.getCantidadBolsasUtilizada();
        }
        return retorno;
    }
    
    public int obtenerUnidadesTransporteIngresadoConPerdidasIncluidas(Calendar unaFecha) {
        int retorno = this.cantidadTotalUnidadesDeTransporteIngresadas;
        Iterator recorredorSalidas = this.salidasAsociadas.iterator();
        while (recorredorSalidas.hasNext()){
            Salida unaSalida = (Salida) recorredorSalidas.next();
            if (unaSalida.estaRegular() && unaSalida.esAnteriorOIgualA(unaFecha))
                retorno = retorno - unaSalida.getCantidadBolsasUtilizada();
        }
        return retorno;
    }
    
    public String obtenerSituacion(){
        String retorno = "Debe estacionarse.";
        if (this.tipo_Lote.equals(Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE)){
            if (this.poseeAnalisisDeYCVRegularYAprobado())
                retorno = "Debe estacionarse.";
            else
                retorno = "Debe realizarse un análisis de laboratorio.";
        }
        if (this.tipo_Lote.equals(Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA)){
            if (this.poseeAnalisisDeYCERegularYAprobado())
                retorno = "Debe molerse.";
            else
                retorno = "Debe realizarse un análisis de laboratorio.";
        }
        if (this.tipo_Lote.equals(Lote.TIPO_LOTE_YERBA_CANCHADA_MOLIDA)){
            retorno = "Lote egresado.";
        }
        
        return retorno;
    }
    
    public String getSituacion(){
        return obtenerSituacion();
    }

    public String getEquipamientoS(){
        return getEquipamientoDondeReside().getNombre();
    }

    public boolean esDeYerbaCancadaEstacionada(Calendar unaFecha) {
        boolean seEncontroEstacionamiento = false;
        Iterator estacionamientos = this.estacionamientosAsociados.iterator();
        while (estacionamientos.hasNext() && !seEncontroEstacionamiento){
            Estacionamiento unEstacionamiento = (Estacionamiento) estacionamientos.next();
            seEncontroEstacionamiento = unEstacionamiento.estaRegular() && unEstacionamiento.esAnteriorOIgualA(unaFecha);
        }
        boolean seEncontroMolienda = false;
        Iterator moliendas = this.moliendasAsociadas.iterator();
        while (moliendas.hasNext() && !seEncontroMolienda){
            Molienda unaMolienda = (Molienda) moliendas.next();
            seEncontroMolienda = unaMolienda.estaRegular() && unaMolienda.esAnteriorOIgualA(unaFecha);
        }
        return (seEncontroEstacionamiento && !seEncontroMolienda);
    }
    
    @Override
    public String toString(){
        return this.etiqueta;
    }

    public Object[] devolverVectorModuloInteligente() throws ExcepcionCargaParametros {
        Object[] vec ={this, UtilidadesInterfazGrafica.formatearFlotante(getCantidadDisponibleParaTransformarKg())+" Kg(s).", this.obtenerSituacion(), this.getEquipamientoDondeReside().getNombre()};
        return vec;
    }   
    
}
