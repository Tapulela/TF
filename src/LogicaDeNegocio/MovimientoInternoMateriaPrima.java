/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Lote;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.GestionUsuariosYRoles.Operador;
import LogicaDeNegocio.Organizacion;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class MovimientoInternoMateriaPrima {
    private int id;
    private Operador unOperador;
    private Calendar fechaOrigen;
    private Calendar fechaEntrada;
    private Calendar fechaSalida;
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
    
    private Equipamiento equipamientoOrigen;
    private Equipamiento equipamientoDestino;

    public MovimientoInternoMateriaPrima(Operador unOperador, Calendar fechaEntrada, Calendar fechaSalida, String unidadTransporte, int cantidadUnidades, String unidadDeMedidaPeso, float pesoEntrada, float pesoSalida, String nHojaRuta, String nRemito, String precinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Lote loteAMover, Equipamiento origen,Equipamiento destino) {
        this.unOperador = unOperador;
        this.fechaOrigen = Calendar.getInstance();
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
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
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Operador getUnOperador() {
        return unOperador;
    }

    public void setUnOperador(Operador unOperador) {
        this.unOperador = unOperador;
    }

    public Calendar getFechaOrigen() {
        return fechaOrigen;
    }

    public void setFechaOrigen(Calendar fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    public Calendar getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Calendar fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Calendar getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Calendar fechaSalida) {
        this.fechaSalida = fechaSalida;
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
    
    
}
