/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Lote;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import LogicaDeNegocio.Organizacion;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class MovimientoInternoMateriaPrima extends Evento{
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    
    
    private int id;
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
    

    public MovimientoInternoMateriaPrima(int id, Usuario unOperador, java.sql.Date fechaOrigen, java.sql.Time horaEntrada, java.sql.Time horaSalida, String unidadTransporte, int cantidadUnidades, String unidadDeMedidaPeso, float pesoEntrada, float pesoSalida, String nHojaRuta, String nRemito, String precinto, String nombreConductor, String patenteChasis, String patenteAcoplado, String unEstado,Lote loteAMover, Equipamiento origen,Equipamiento destino, Bascula unaBasculaAsociada, Proveedor unProveedor, int idEvento) {
        super(idEvento, unEstado, unOperador, fechaOrigen);
        this.id = id;
        
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
    }
    
    public MovimientoInternoMateriaPrima(Usuario unOperador, LocalTime horaEntrada, LocalTime horaSalida, String unidadTransporte, int cantidadUnidades, String unidadDeMedidaPeso, float pesoEntrada, float pesoSalida, String nHojaRuta, String nRemito, String precinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Lote loteAMover, Equipamiento origen, Equipamiento destino, Proveedor unProveedorDeTransporte) {
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
        
        this.loteAsociado = loteAMover;
        this.equipamientoOrigen = origen;
        this.equipamientoDestino = destino;
        this.basculaAsociada = this.equipamientoDestino.getBasculaAsociada();
        this.proveedorTransporte = unProveedorDeTransporte;
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

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public float getPesoNeto(String unidadDeMedida) throws ExcepcionCargaParametros {
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
        Object[] vec ={this.getId(), this.getEstadoEvento(), this.getLoteAsociado().getOrdenDeProduccionAsociada().getId(), this.getLoteAsociado().getOrdenDeCompraAsociada().getId(),this.getEquipamientoDestino().getNombre(), this.proveedorTransporte.getRazonSocial(), fecha, this.loteAsociado.getEtiqueta()};
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
    
    
}
