/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.Consultable;
import InterfazGrafica.Paneles.PanelGestionIngresos;
import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class MovimientoInternoMateriaPrima extends Evento implements Reporte, Filtrable,Consultable{
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    
    
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private String unidadTransporte;
    private int cantidadUnidades;
    private String unidadDeMedidaPeso;
    private float pesoEntrada;
    private float pesoSalida;
    private String nHojaRuta;
    private String nRemito;
    private String precinto;
    private String nombreConductor;
    private String patenteChasis;
    private String patenteAcoplado;
    private String estado;
    
    private Lote loteAsociado;
    private Bascula basculaAsociada;
    
    private Equipamiento equipamientoOrigen;
    private Equipamiento equipamientoDestino;
    private Proveedor proveedorTransporte;
    
    private ArrayList mermas;
    
    private MovimientoInternoMateriaPrima movimientoInmediatamentePosterior;
    

    public MovimientoInternoMateriaPrima(int id, Usuario unOperador, java.sql.Date fechaOrigen, java.sql.Time horaEntrada, java.sql.Time horaSalida, String unidadTransporte, int cantidadUnidades, String unidadDeMedidaPeso, float pesoEntrada, float pesoSalida, String nHojaRuta, String nRemito, String precinto, String nombreConductor, String patenteChasis, String patenteAcoplado, String unEstado,Lote loteAMover, Equipamiento origen,Equipamiento destino, Bascula unaBasculaAsociada, Proveedor unProveedor, int idEvento) {
        super(idEvento, unEstado, unOperador, fechaOrigen, id);
        
        this.horaEntrada = horaEntrada.toLocalTime();
        this.horaSalida = horaSalida.toLocalTime();

        this.unidadTransporte = unidadTransporte;
        this.cantidadUnidades = cantidadUnidades;
        this.unidadDeMedidaPeso = unidadDeMedidaPeso;
        this.pesoEntrada = pesoEntrada;
        this.pesoSalida = pesoSalida;
        this.nHojaRuta = nHojaRuta;
        this.nRemito = nRemito;
        this.precinto = precinto;
        this.nombreConductor = nombreConductor;
        this.patenteChasis = patenteChasis;
        this.patenteAcoplado = patenteAcoplado;
        this.estado = unEstado;
        
        this.loteAsociado = loteAMover;
        this.equipamientoOrigen = origen;
        this.equipamientoDestino = destino;
        this.basculaAsociada = unaBasculaAsociada;
        this.proveedorTransporte = unProveedor;
        this.mermas = new ArrayList();
    }
    
    public MovimientoInternoMateriaPrima(Calendar unaFechaOrigen, Usuario unOperador, LocalTime horaEntrada, LocalTime horaSalida, String unidadTransporte, int cantidadUnidades, String unidadDeMedidaPeso, float pesoEntrada, float pesoSalida, String nHojaRuta, String nRemito, String precinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Lote loteAMover, Equipamiento origen, Equipamiento destino, Proveedor unProveedorDeTransporte) {
        super(Evento.ESTADO_REGULAR, unOperador);
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.unidadTransporte = unidadTransporte;
        this.cantidadUnidades = cantidadUnidades;
        this.unidadDeMedidaPeso = unidadDeMedidaPeso;
        this.pesoEntrada = pesoEntrada;
        this.pesoSalida = pesoSalida;
        this.nHojaRuta = nHojaRuta;
        this.nRemito = nRemito;
        this.precinto = precinto;
        this.nombreConductor = nombreConductor;
        this.patenteChasis = patenteChasis;
        this.patenteAcoplado = patenteAcoplado;
        this.estado = ESTADO_REGULAR;
        
        setFechaOrigen(unaFechaOrigen);
        
        this.loteAsociado = loteAMover;
        this.equipamientoOrigen = origen;
        this.equipamientoDestino = destino;
        this.basculaAsociada = this.equipamientoDestino.getBasculaAsociada();
        this.proveedorTransporte = unProveedorDeTransporte;
        
        this.mermas = new ArrayList();
    }

    public Bascula getBasculaAsociada() {
        return basculaAsociada;
    }

    public void setBasculaAsociada(Bascula basculaAsociada) {
        this.basculaAsociada = basculaAsociada;
    }

    public Proveedor getProveedorTransporte() {
        return proveedorTransporte;
    }

    public void setProveedorTransporte(Proveedor proveedorTransporte) {
        this.proveedorTransporte = proveedorTransporte;
    }

    



    public java.sql.Timestamp getHoraEntrada() {
        LocalDate localDate = LocalDateTime.ofInstant(this.getFechaOrigenC().toInstant(), this.getFechaOrigenC().getTimeZone().toZoneId()).toLocalDate();
        return Timestamp.valueOf(horaEntrada.atDate(localDate));
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public java.sql.Timestamp getHoraSalida() {
        LocalDate localDate = LocalDateTime.ofInstant(this.getFechaOrigenC().toInstant(), this.getFechaOrigenC().getTimeZone().toZoneId()).toLocalDate();
        return Timestamp.valueOf(horaSalida.atDate(localDate));
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getUnidadTransporte() {
        return unidadTransporte;
    }

    public void setUnidadTransporte(String unidadTransporte) {
        this.unidadTransporte = unidadTransporte;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    public String getUnidadDeMedidaPeso() {
        return unidadDeMedidaPeso;
    }

    public void setUnidadDeMedidaPeso(String unidadDeMedidaPeso) {
        this.unidadDeMedidaPeso = unidadDeMedidaPeso;
    }

    public float getPesoEntrada() {
        return pesoEntrada;
    }

    public void setPesoEntrada(float pesoEntrada) {
        this.pesoEntrada = pesoEntrada;
    }

    public float getPesoSalida() {
        return pesoSalida;
    }

    public void setPesoSalida(float pesoSalida) {
        this.pesoSalida = pesoSalida;
    }

    public String getnHojaRuta() {
        return nHojaRuta;
    }

    public void setnHojaRuta(String nHojaRuta) {
        this.nHojaRuta = nHojaRuta;
    }

    public String getnRemito() {
        return nRemito;
    }

    public void setnRemito(String nRemito) {
        this.nRemito = nRemito;
    }

    public String getPrecinto() {
        return precinto;
    }

    public void setPrecinto(String precinto) {
        this.precinto = precinto;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getPatenteChasis() {
        return patenteChasis;
    }

    public void setPatenteChasis(String patenteChasis) {
        this.patenteChasis = patenteChasis;
    }

    public String getPatenteAcoplado() {
        return patenteAcoplado;
    }

    public void setPatenteAcoplado(String patenteAcoplado) {
        this.patenteAcoplado = patenteAcoplado;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Lote getLoteAsociado() {
        return loteAsociado;
    }

    public void setLoteAsociado(Lote loteAsociado) {
        this.loteAsociado = loteAsociado;
    }

    public Equipamiento getEquipamientoDestino() {
        return equipamientoDestino;
    }

    public void setEquipamientoDestino(Equipamiento equipamientoDestino) {
        this.equipamientoDestino = equipamientoDestino;
    }

    public Equipamiento getEquipamientoOrigen() {
        return equipamientoOrigen;
    }

    public void setEquipamientoOrigen(Equipamiento equipamientoOrigen) {
        this.equipamientoOrigen = equipamientoOrigen;
    }

    public void anular() {
        super.anularEsteEvento();
        this.estado = ESTADO_ANULADO;
    }
    
    public boolean estaRegular(){
        return this.estado.equals(ESTADO_REGULAR);
    }


    public float getPesoNeto(String unidadDeMedida){
        return Organizacion.convertirUnidadPeso(this.unidadDeMedidaPeso, this.pesoEntrada-this.pesoSalida, unidadDeMedida);
    }

    public boolean poseeEquipamientoOrigen() {
        return this.equipamientoOrigen != null;
    }

    public boolean poseeOrdenDeProduccionAsociada(OrdenDeProduccion unaOrdenProduccionSeleccionada) {
        return this.loteAsociado.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
    }

    public boolean poseeEstado(String unEstado) {
        return this.estado.equals(unEstado);
    }

    public boolean poseeEtiqueta(String unaEtiqueta) {
        return this.loteAsociado.poseeEtiqueta(unaEtiqueta);
    }

    public boolean poseeOrdenDeCompraAsociada(OrdenDeCompra unaOrdenCompraSeleccionada) {
        return this.loteAsociado.poseeOrdenDeCompraAsociada(unaOrdenCompraSeleccionada);
    }

    public boolean poseeProveedorTransporteAsociado(Proveedor unProveedorTransporte) {
        return this.proveedorTransporte.equals(unProveedorTransporte);
    }

    public boolean poseeEquipamientoDestino(Equipamiento unEquipamiento) {
        return this.equipamientoDestino.equals(unEquipamiento);
    }

    public Object[] devolverVectorIngreso() {
        String fecha = ( new SimpleDateFormat( "dd/MM/yyyy" ) ).format( this.getFechaOrigenC().getTime() );
        Object[] vec ={this.getId(), this.getEstadoEvento(), UtilidadesInterfazGrafica.formatearFlotante(this.getPesoNeto(Lote.UNIDAD_MEDIDA_KILOGRAMO))+" Kg(s)",this.getLoteAsociado().getOrdenDeProduccionAsociada().getId(), this.getLoteAsociado().getOrdenDeCompraAsociada().getId(),this.getEquipamientoDestino().getNombre(), this.proveedorTransporte.getRazonSocial(), fecha, this.loteAsociado.getEtiqueta()};
        return vec;
    }
    
    public Object[] devolverVectorMovimiento() {
        String fecha = ( new SimpleDateFormat( "dd/MM/yyyy" ) ).format( this.getFechaOrigenC().getTime() );
        Object[] vec ={this.getId(), this.getEstadoEvento(), this.getLoteAsociado().getOrdenDeProduccionAsociada().getId(), this.getLoteAsociado().getOrdenDeCompraAsociada().getId(),this.getEquipamientoOrigen().getNombre(), this.getEquipamientoDestino().getNombre(), this.proveedorTransporte.getRazonSocial(), fecha, this.loteAsociado.getEtiqueta()};
        return vec;
    }

    public Boolean poseeProveedorAsociado(Proveedor unProveedor) {
        return this.loteAsociado.getOrdenDeCompraAsociada().getProveedorAsociado().equals(unProveedor);
    }

    @Override
    public String getReporte(){
        if (!poseeEquipamientoOrigen()){
            String retorno = "";
            retorno = retorno + "ID: "+this.getId() +"\t\t Fecha: "+Organizacion.expresarCalendario(this.getFechaOrigenC()) +"\tHora de Entrada: "+horaEntrada+ "\t\tHora de Salida: "+horaSalida+"";
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Etiqueta de lote: "+this.getLoteAsociado().getEtiqueta()+ "\tCantidad de unidades: "+ this.getCantidadUnidades()+" "+this.getUnidadTransporte()+"(s) " +"\tPeso Neto: "+UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(this.getUnidadDeMedidaPeso(), this.getPesoNeto(unidadDeMedidaPeso), Lote.UNIDAD_MEDIDA_KILOGRAMO))+" Kgs\n\n"
                    + "Destino: "+this.equipamientoDestino.getNombre() +"\tBascula asociada: "+ this.getBasculaAsociada().getNombre()+" ";
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "N° de hoja de ruta: "+this.getnHojaRuta()+""
                    + "\t\tEstado: "+ this.getEstado();
            return retorno;
        }else{
            String retorno = "";
            retorno = retorno + "ID: "+this.getId() +"\t\t Fecha: "+Organizacion.expresarCalendario(this.getFechaOrigenC()) +"\tHora de Entrada: "+horaEntrada+ "\t\tHora de Salida: "+horaSalida+"";
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Etiqueta de lote: "+this.getLoteAsociado().getEtiqueta()+ "\tCantidad de unidades: "+ this.getCantidadUnidades()+" "+this.getUnidadTransporte()+"(s) " +"\tPeso Neto: "+UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(this.getUnidadDeMedidaPeso(), this.getPesoNeto(unidadDeMedidaPeso), Lote.UNIDAD_MEDIDA_KILOGRAMO))+" Kgs\n\n"
                    + "Origen "+this.equipamientoOrigen.getNombre()+ "\tDestino: "+this.equipamientoDestino.getNombre() +"\tBascula asociada: "+ this.getBasculaAsociada().getNombre()+" ";
            retorno = retorno + "\n";
            retorno = retorno + "N° de hoja de ruta: "+this.getnHojaRuta()+""
                    + "\t\tEstado: "+ this.getEstado();
            return retorno;
        }
    }

    @Override
    public Object[] devolverVector() {
        if (!this.poseeEquipamientoOrigen())
            return devolverVectorIngreso();
        else
            return devolverVectorMovimiento();
        
    }

    @Override
    public boolean cumpleCriterio(String unCriterio, Object unObjeto) {
        boolean cumpleCriterio = false;
        if (unObjeto == null)
            return cumpleCriterio;
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar)){
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaOrigenEstaEntre(fechaInferior, fechaSuperior);
        }
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionIngresos.criterios[0]))
            return ((String)unObjeto).equals(this.estado);
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionIngresos.criterios[1]))
            return this.getLoteAsociado().getEtiqueta().contains(((String)unObjeto));
        if (unObjeto instanceof OrdenDeProduccion){
            OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
            return this.poseeOrdenDeProduccionAsociada(unaOrdenProduccion);
        }
        if (unObjeto instanceof OrdenDeCompra){
            OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) unObjeto;
            return this.poseeOrdenDeCompraAsociada(unaOrdenDeCompra);
        }
        if (unObjeto instanceof Equipamiento){
            Equipamiento unEquipamiento = (Equipamiento) unObjeto;
            return this.poseeEquipamientoDestino(unEquipamiento);
        }
        if (unObjeto instanceof Proveedor && unCriterio.equals(PanelGestionIngresos.criterios[5])){
            Proveedor unProveedor = (Proveedor) unObjeto;
            return this.poseeProveedorTransporteAsociado(unProveedor);
        }
        if (unObjeto instanceof Proveedor && unCriterio.equals(PanelGestionIngresos.criterios[6])){
            Proveedor unProveedor = (Proveedor) unObjeto;
            return this.poseeProveedorAsociado(unProveedor);
        }
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar)){
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaOrigenEstaEntre(fechaInferior, fechaSuperior);
        }
        return cumpleCriterio;
    }
    
    public void agregarMerma(Merma unaMerma){
        this.mermas.add(unaMerma);
    }

    public boolean poseeMermaRegular() {
        boolean seEncontroMermaRegular = false;
        Iterator recMermas = this.mermas.iterator();
        while (recMermas.hasNext() && !seEncontroMermaRegular){
            Merma unaMerma = (Merma) recMermas.next();
            seEncontroMermaRegular = unaMerma.estaRegular();
        }
        return seEncontroMermaRegular;
    }
    
    public Merma obtenerMermaRegular() {
        Merma retorno = null;
        Iterator recMermas = this.mermas.iterator();
        while (recMermas.hasNext() && retorno == null){
            Merma unaMerma = (Merma) recMermas.next();
            if (unaMerma.estaRegular())
                retorno = unaMerma;
        }
        return retorno;
    }

    public MovimientoInternoMateriaPrima getMovimientoInmediatamentePosterior() {
        return movimientoInmediatamentePosterior;
    }

    public void setMovimientoInmediatamentePosterior(MovimientoInternoMateriaPrima movimientoInmediatamentePosterior) {
        this.movimientoInmediatamentePosterior = movimientoInmediatamentePosterior;
    }
    
}
