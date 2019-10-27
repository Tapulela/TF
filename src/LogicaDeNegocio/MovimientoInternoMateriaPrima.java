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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class MovimientoInternoMateriaPrima {
    private int id;
    private Usuario unOperador;
    private Calendar fechaOrigen;
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

    public MovimientoInternoMateriaPrima(int id, Usuario unOperador, java.sql.Date fechaOrigen, java.sql.Time horaEntrada, java.sql.Time horaSalida, String unidadTransporte, int cantidadUnidades, String unidadDeMedidaPeso, float pesoEntrada, float pesoSalida, String nHojaRuta, String nRemito, String precinto, String nombreConductor, String patenteChasis, String patenteAcoplado, String unEstado,Lote loteAMover, Equipamiento origen,Equipamiento destino, Bascula unaBasculaAsociada) {
        this.id = id;
        this.unOperador = unOperador;
        
        this.fechaOrigen = Calendar.getInstance();
        this.fechaOrigen.setTime(fechaOrigen);
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
    }
    
    public MovimientoInternoMateriaPrima(Usuario unOperador, LocalTime horaEntrada, LocalTime horaSalida, String unidadTransporte, int cantidadUnidades, String unidadDeMedidaPeso, float pesoEntrada, float pesoSalida, String nHojaRuta, String nRemito, String precinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Lote loteAMover, Equipamiento origen,Equipamiento destino) {
        this.unOperador = unOperador;
        this.fechaOrigen = Calendar.getInstance();
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
        this.estado = "Activo";
        
        this.loteAsociado = loteAMover;
        this.equipamientoOrigen = origen;
        this.equipamientoDestino = destino;
        this.basculaAsociada = this.equipamientoDestino.getBasculaAsociada();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUnOperador() {
        return unOperador;
    }

    public void setUnOperador(Usuario unOperador) {
        this.unOperador = unOperador;
    }

    public java.sql.Date getFechaOrigen() {
        return new Date(this.fechaOrigen.getTimeInMillis());
    }

    public void setFechaOrigen(Calendar fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    public java.sql.Timestamp getHoraEntrada() {
        LocalDate localDate = LocalDateTime.ofInstant(this.fechaOrigen.toInstant(), fechaOrigen.getTimeZone().toZoneId()).toLocalDate();
        return Timestamp.valueOf(horaEntrada.atDate(localDate));
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public java.sql.Timestamp getHoraSalida() {
        LocalDate localDate = LocalDateTime.ofInstant(this.fechaOrigen.toInstant(), fechaOrigen.getTimeZone().toZoneId()).toLocalDate();
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
        return estado;
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
    
    
    
    
    
    public boolean estaRegular(){
        return this.estado.equals("Regular");
    }


    public float getPesoNeto(String unidadDeMedida) throws Exception {
        return Organizacion.convertirUnidadPeso(this.unidadDeMedidaPeso, this.pesoEntrada-this.pesoSalida, unidadDeMedida);
    }

    public boolean poseeEquipamientoOrigen() {
        return this.equipamientoOrigen != null;
    }
    
    
}
