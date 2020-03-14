/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.Consultable;
import InterfazGrafica.Paneles.PanelGestionOrdenesProduccion;
import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class OrdenDeProduccion extends Evento implements Reporte, Comparable, Filtrable, Consultable{
    private static final String ESTADO_REGULAR = "Regular";
    private static final String ESTADO_ANULADO = "Anulado";
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
    private ArrayList egresosAsociados;
    private ArrayList mermasAsociadas;
    private ArrayList perdidasAsociadas;
    
    public OrdenDeProduccion(int id, java.sql.Date fechaOrigen, float cantidadAProducir, String unidadDeMedida, java.sql.Date fechaEntregaProductoTerminado, String estado, String unaDescripcion, int idEvento, Usuario unUsuario) {
        super(idEvento, estado, unUsuario, fechaOrigen, id);
        this.cantidadAProducir = cantidadAProducir;
        this.unidadDeMedida = unidadDeMedida;
        this.fechaEntregaProductoTerminado = Calendar.getInstance();
        this.fechaEntregaProductoTerminado.setTime(fechaEntregaProductoTerminado);
        
        this.estado = estado;
        this.descripcion = unaDescripcion;
        
        this.ordenesCompraImplicadas = new ArrayList();
        this.analisisLaboratorio = new ArrayList();
        this.estacionamientos = new ArrayList();
        this.moliendas = new ArrayList();
        this.eventosImplicados = new ArrayList();
        this.criteriosDeAnalisisAsociados = new ArrayList();
        this.egresosAsociados = new ArrayList();
        this.mermasAsociadas = new ArrayList();
        this.perdidasAsociadas = new ArrayList();
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
        this.egresosAsociados = new ArrayList();
        this.mermasAsociadas = new ArrayList();
        this.perdidasAsociadas = new ArrayList();
    }

    public ArrayList getEgresosAsociados() {
        return egresosAsociados;
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
    private void agregarAnalisisLaboratio (AnalisisLaboratorio unAnalisis){
        this.analisisLaboratorio.add(unAnalisis);
    }
    
    private void agregarEgreso(Egreso unEgreso) {
        this.egresosAsociados.add(unEgreso);
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
            agregarAnalisisLaboratio((AnalisisLaboratorio) unEvento);
        if (unEvento instanceof Egreso)
            agregarEgreso((Egreso) unEvento);
        if (unEvento instanceof Merma)
            agregarMerma((Merma) unEvento);
        if (unEvento instanceof Perdida)
            agregarPerdida((Perdida) unEvento);
        
    }
    
    public boolean seEncuentraRegular(){
        return this.estado.equals(ESTADO_REGULAR);
    }
    public Object[] devolverVector() {
        String fechaOrigen = Organizacion.expresarCalendario(getFechaOrigenC());
        String fechaEntrega = Organizacion.expresarCalendario(fechaEntregaProductoTerminado);
        Object[] vec ={this.getId(),UtilidadesInterfazGrafica.formatearFlotante(this.getCantidadAProducir()), this.getUnidadDeMedida(), fechaOrigen, fechaEntrega,this.getEstadoEvento()};
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
    
    public ArrayList obtenerLotesRestantesAEstacionar(){
        ArrayList retorno = new ArrayList();
        //ArrayList retorno = getLotesAsociados();
        Iterator lotes = this.getLotesAsociados().iterator();
        while (lotes.hasNext()){
            Lote unLote = (Lote) lotes.next();
            if (unLote.getTipo_Lote().equals(Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE) && unLote.estaRegular()){
                retorno.add(unLote);
            }
        }
        return retorno;
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

    private void agregarMerma(Merma unaMerma) {
        this.mermasAsociadas.add(unaMerma);
    }

    private void agregarPerdida(Perdida unaPerdida) {
        this.perdidasAsociadas.add(unaPerdida);
    }

    public Float calcularProgresoGeneral(){
        Float progreso = 0f;
        Float pesoEgresado = calcularPesoEgresadoKgs();
        Float cantidadAProducirKg = Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.cantidadAProducir, Lote.UNIDAD_MEDIDA_KILOGRAMO);
        progreso = pesoEgresado/cantidadAProducirKg*100;
        return progreso;
    }

    public Float calcularPesoEgresadoKgs() {
        Float egresosTotales = 0f;
        Iterator recorredorDeEgresos = this.egresosAsociados.iterator();
        while (recorredorDeEgresos.hasNext()){
            Egreso unEgreso = (Egreso) recorredorDeEgresos.next();
            if (unEgreso.estaRegular())
                egresosTotales = egresosTotales + Organizacion.convertirUnidadPeso(unEgreso.getUnidadMedidaPeso(), unEgreso.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO);
        }
        return egresosTotales;
    }
    
    public Float calcularPesoPerdidoKgs(){
        Float perdidasTotales = 0f;
        Iterator recorredorDePerdidas = this.perdidasAsociadas.iterator();
        while (recorredorDePerdidas.hasNext()){
            Perdida unaPerdida = (Perdida) recorredorDePerdidas.next();
            if (unaPerdida.estaRegular())
                perdidasTotales = perdidasTotales + Organizacion.convertirUnidadPeso(unaPerdida.getUnidadMedidaPeso(), unaPerdida.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO);
        }
        return perdidasTotales;
    }
    
    public Float calcularMermaKgs(){
        Float perdidasTotales = 0f;
        Iterator recorredorDeMermas = this.mermasAsociadas.iterator();
        while (recorredorDeMermas.hasNext()){
            Merma unaMerma = (Merma) recorredorDeMermas.next();
            if (unaMerma.estaRegular())
                perdidasTotales = perdidasTotales + Organizacion.convertirUnidadPeso(unaMerma.getUnidadMedidaPeso(), unaMerma.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO);
        }
        return perdidasTotales;
    }
    
    public Float calcularTotalAEstacionarKgs(){
        Float pesoAEstacionarTotal = 0f;
        Iterator recorredorDeLotes = this.getLotesAsociados().iterator();
        while (recorredorDeLotes.hasNext()){
            Lote unLote = (Lote) recorredorDeLotes.next();
            if (unLote.estaRegular() && unLote.esDeYerbaCancadaVerde())
                pesoAEstacionarTotal = pesoAEstacionarTotal + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg();
        }
        return pesoAEstacionarTotal;
    }
    
    public Float calcularTotalEstacionadoKgs(){
        Float pesoEstacionadoTotal = 0f;
        Iterator recorredorEstacionamientos = this.estacionamientos.iterator();
        while (recorredorEstacionamientos.hasNext()){
            Estacionamiento unEstacionamiento = (Estacionamiento) recorredorEstacionamientos.next();
            if (unEstacionamiento.estaRegular())
                pesoEstacionadoTotal = pesoEstacionadoTotal + unEstacionamiento.obtenerTotalTransformadoKgs(this);
        }
        return pesoEstacionadoTotal;
    }
    
    public Float calcularTotalAMolerKgs(){
        Float pesoAMolerTotal = 0f;
        Iterator recorredorDeLotes = this.getLotesAsociados().iterator();
        while (recorredorDeLotes.hasNext()){
            Lote unLote = (Lote) recorredorDeLotes.next();
            if (unLote.estaRegular() && unLote.esDeYerbaCancadaEstacionada())
                pesoAMolerTotal = pesoAMolerTotal + unLote.getCantidadDisponibleParaMolerKg();
        }
        return pesoAMolerTotal;
    }
    
    public Float calcularTotalMolidoKgs(){
        Float pesoMolidoTotal = 0f;
        Iterator recorredorDeMoliendas = this.moliendas.iterator();
        while (recorredorDeMoliendas.hasNext()){
            Molienda unaMolienda = (Molienda) recorredorDeMoliendas.next();
            if (unaMolienda.estaRegular())
                pesoMolidoTotal = pesoMolidoTotal + unaMolienda.obtenerTotalTransformadoKgs(this);
        }
        return pesoMolidoTotal;
    }
    
    public int calcularRechazos(){
        int rechazosTotales = 0;
        Iterator recorredorDeAnalisis = this.analisisLaboratorio.iterator();
        while (recorredorDeAnalisis.hasNext()){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) recorredorDeAnalisis.next();
            if (unAnalisis.estaRegular() && unAnalisis.estaRechazado())
                rechazosTotales += 1;
        }
        return rechazosTotales;
    }

    @Override
    public String getReporte() {
        String retorno = "";
        retorno = retorno + "ID: "+this.getId() +"\t\t Fecha de origen: "+Organizacion.expresarCalendario(this.getFechaOrigenC())+"\t\t fecha de entrega: "+Organizacion.expresarCalendario(this.fechaEntregaProductoTerminado);
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Cantidad a producir: "+UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(this.unidadDeMedida, cantidadAProducir, Lote.UNIDAD_MEDIDA_KILOGRAMO))+" Kgs\tCantidad producida: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularPesoEgresadoKgs())+" kgs\t" +"\tProgreso: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularProgresoGeneral())+" %";
            retorno = retorno + "\n";
            retorno = retorno + "Cantidad total estacionada: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalEstacionadoKgs())+" Kgs \t\tCantidad total Molida: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalMolidoKgs())+" Kgs";
            retorno = retorno + "\n";
            retorno = retorno + "Total de Perdidas: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularPesoPerdidoKgs())+" Kgs \t\t Total de Mermas: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularMermaKgs())+" Kgs \t\t cantidad de rechazos: "+this.calcularRechazos()+" \n\n"
                    + "Estado: "+ estado;
            
            return retorno;
    }

    public int compareTo(OrdenDeProduccion t) {
        int retorno = 0;
        if (t == null)
            return retorno;
        if (t instanceof OrdenDeProduccion){
            OrdenDeProduccion unaOrden = (OrdenDeProduccion)t;
            if (this.fechaEntregaProductoTerminado.before(unaOrden.getFechaEntregaProductoTerminadoC()))
                retorno = -1;
            if (this.fechaEntregaProductoTerminado.after(unaOrden.getFechaEntregaProductoTerminadoC()))
                retorno = 1;
        }
        return retorno;
    }

    public ArrayList obtenerOrdenesDeCompraPendientes(){
        ArrayList retorno = new ArrayList();
        Iterator recorredorOrdenesDeCompra = this.getOrdenesCompraImplicadasActivas().iterator();
        while (recorredorOrdenesDeCompra.hasNext()){
            OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) recorredorOrdenesDeCompra.next();
            if (unaOrdenDeCompra.getCantidadRestanteARecibir() > 0.5f){
                retorno.add(unaOrdenDeCompra);
            }
        }
        return retorno;
    }
    
    public Float obtenerCantidadTotalARecibirDeOrdenesDeCompraPendientesEnKgs(){
        Float retorno = 0f;
        Iterator ordenesDeCompra = obtenerOrdenesDeCompraPendientes().iterator();
        while (ordenesDeCompra.hasNext()){
            OrdenDeCompra unaOrden = (OrdenDeCompra) ordenesDeCompra.next();
            retorno = retorno + Organizacion.convertirUnidadPeso(unaOrden.getUnidadDeMedida(), unaOrden.getCantidadRestanteARecibir(), Lote.UNIDAD_MEDIDA_KILOGRAMO);
        }
        return retorno;
    }
    public String exhibirCompromisosPendientes() throws ExcepcionCargaParametros{
        String retorno = "";
        ArrayList listaOrdenesDeCompra = obtenerOrdenesDeCompraPendientes();
        Iterator recorredorOrdenesDeCompra = listaOrdenesDeCompra.iterator();
        retorno = retorno + "Orden de producción "+this.getId()+"<br>";
        retorno = retorno + "&emsp&emsp Progreso total de la producción: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularProgresoGeneral())+" %. <br> <br>";
        retorno = retorno + "&emsp&emsp Total de materia prima restante a comprar: "+UtilidadesInterfazGrafica.formatearFlotante(  Math.max(Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.getPesoRestanteAComprar(), Lote.UNIDAD_MEDIDA_KILOGRAMO), 0f))+" Kilogramos.<br>";
        retorno = retorno + "&emsp&emsp Total de pérdidas "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularPesoPerdidoKgs())+" Kilogramos. <br>";
        retorno = retorno + "&emsp&emsp Total de materia prima pendiente de recibir de las ordenes de compra: "+UtilidadesInterfazGrafica.formatearFlotante(this.obtenerCantidadTotalARecibirDeOrdenesDeCompraPendientesEnKgs())+ " Kilogramos. <br>";
        if (this.obtenerCantidadTotalARecibirDeOrdenesDeCompraPendientesEnKgs()>0)
            retorno = retorno + "&emsp&emsp&emsp&emsp   Detalle<br>";
        while (recorredorOrdenesDeCompra.hasNext()){
            OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) recorredorOrdenesDeCompra.next();
            retorno = retorno + "&emsp&emsp&emsp&emsp&emsp&emsp       Orden de compra: "+unaOrdenDeCompra.getId()+" <br>";
            retorno = retorno + "&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp       Cantidad restante a recibir: "+UtilidadesInterfazGrafica.formatearFlotante(unaOrdenDeCompra.getCantidadRestanteARecibir()) + " "+unaOrdenDeCompra.getUnidadDeMedida()+"(s) <br>";
        }
        retorno = retorno + "<br>";
        retorno = retorno + "&emsp&emsp       Total de yerba canchada verde a estacionar: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalAEstacionarKgs())+" Kilogramos"
                + "<br> &emsp&emsp&emsp&emsp Detalle: <br>";
        Iterator recorredorLotesAEstacionar = this.obtenerLotesRestantesAEstacionar().iterator();
        while (recorredorLotesAEstacionar.hasNext()){
            Lote unLote = (Lote)recorredorLotesAEstacionar.next();
            retorno = retorno + "&emsp&emsp&emsp&emsp&emsp&emsp           Lote "+unLote.getEtiqueta()+", lugar de residencia: "+unLote.getEquipamientoDondeReside().getNombre()+". Situación: "+unLote.obtenerSituacion()+"<br>";
        }
        retorno = retorno + "<br>";
        retorno = retorno + "&emsp&emsp Total de yerba canchada estacionada a moler: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalAMolerKgs())+" Kilogramos<br>"
                + "&emsp&emsp&emsp&emsp Detalle: <br>";
        
        Iterator recorredorLotesAMoler = this.obtenerLotesRestantesAMoler().iterator();
        while (recorredorLotesAMoler.hasNext()){
            Lote unLote = (Lote)recorredorLotesAMoler.next();
            retorno = retorno + "&emsp&emsp&emsp&emsp&emsp&emsp Lote "+unLote.getEtiqueta()+", Cantidad a moler: "+UtilidadesInterfazGrafica.formatearFlotante(unLote.getCantidadDisponibleParaMolerKg())+" Kilogramos, lugar de residencia: "+unLote.getEquipamientoDondeReside().getNombre()+". Situación: "+unLote.obtenerSituacion()+"<br>";
        }
        retorno = retorno + "<br><br><br>";
        
        
        return retorno;
        
        
    }

    public ArrayList obtenerLotesRestantesAMoler() {
        ArrayList retorno = new ArrayList();
        //ArrayList retorno = getLotesAsociados();
        Iterator lotes = this.getLotesAsociados().iterator();
        while (lotes.hasNext()){
            Lote unLote = (Lote) lotes.next();
            if (unLote.getTipo_Lote().equals(Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA)){
                retorno.add(unLote);
            }
        }
        return retorno;
    }

    public boolean fechaEntregaEstaEntre(Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) {
        fechaOrigenInferior.set(Calendar.HOUR_OF_DAY, 0);
        fechaOrigenInferior.set(Calendar.MINUTE, 0);
        fechaOrigenInferior.set(Calendar.SECOND, 0);
        fechaOrigenInferior.set(Calendar.MILLISECOND, 0);
        
        fechaOrigenSuperior.set(Calendar.HOUR_OF_DAY, 23);
        fechaOrigenSuperior.set(Calendar.MINUTE, 59);
        fechaOrigenSuperior.set(Calendar.SECOND, 59);        
        return ((this.getFechaEntregaProductoTerminadoC().compareTo(fechaOrigenInferior)>=0 )&& this.getFechaEntregaProductoTerminadoC().compareTo(fechaOrigenSuperior)<=0);
    }
    
    @Override
    public boolean cumpleCriterio(String unCriterio, Object unObjeto) {
        boolean cumpleCriterio = false;
        if (unObjeto == null)
            return cumpleCriterio;
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionOrdenesProduccion.criterios[0])){   //Descripcion
            if (this.descripcion == null)
                return cumpleCriterio;
            return this.descripcion.toUpperCase().contains(((String)unObjeto).toUpperCase());
        }
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionOrdenesProduccion.criterios[1])){   //Estado
            return this.estado.toUpperCase().equals(((String)unObjeto).toUpperCase());
        }
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar) && unCriterio.equals(PanelGestionOrdenesProduccion.criterios[2])){ //Fecha de origen
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaOrigenEstaEntre(fechaInferior, fechaSuperior);
        }
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar) && unCriterio.equals(PanelGestionOrdenesProduccion.criterios[3])){ //Fecha de entrega
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaEntregaEstaEntre(fechaInferior, fechaSuperior);
        }
        return cumpleCriterio;
    }
    
}
