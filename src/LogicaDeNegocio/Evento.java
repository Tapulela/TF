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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Evento implements Comparable{
    public static String ESTADO_REGULAR = "Regular";
    public static String ESTADO_ANULADO = "Anulado";
    
    private int idEvento;
    
    private Usuario usuarioAsociado;
    private Calendar fechaOrigen;

    private String estadoEvento;

    public Evento(int idEvento, String estado, Usuario unUsuario, java.sql.Date fechaOrigen) { //Para recuperar en la bd
        this.idEvento = idEvento;
        this.fechaOrigen = Calendar.getInstance();
        this.fechaOrigen.setTime(fechaOrigen);
        if (estado.contains(ESTADO_ANULADO)){
            this.estadoEvento = ESTADO_ANULADO;
        }else{
            this.estadoEvento = ESTADO_REGULAR;
        }
        this.usuarioAsociado = unUsuario;
    }

    public Evento(String estado, Usuario unUsuario) {
        if (estado.contains(ESTADO_ANULADO)){
            this.estadoEvento = ESTADO_ANULADO;
        }else{
            this.estadoEvento = ESTADO_REGULAR;
        }
        this.fechaOrigen = Calendar.getInstance();
        this.usuarioAsociado = unUsuario;
    }
    
    
    
    
    
    public boolean poseeEventosPosterioresRegulares(ArrayList<Evento> eventosPosteriores){
        boolean seEncontroEventoPosteriorRegular = false;
        Iterator eventos = eventosPosteriores.iterator();
        while (eventos.hasNext() && !seEncontroEventoPosteriorRegular){
            Evento unEvento = (Evento) eventos.next();
            seEncontroEventoPosteriorRegular = unEvento.getEstadoEvento().equals(ESTADO_REGULAR);
        }
        return seEncontroEventoPosteriorRegular;
    }
    
    public ArrayList getEventosPosterioresRegulares(ArrayList<Evento> eventosPosteriores){
        ArrayList retorno = new ArrayList();
        Iterator eventos = eventosPosteriores.iterator();
        while (eventos.hasNext()){
            Evento unEvento = (Evento) eventos.next();
            if (unEvento.eventoEstaRegular())
                retorno.add(unEvento);
        }
        return retorno;
    }
    
    

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int id) {
        this.idEvento = id;
    }

    public java.sql.Date getFechaOrigen() {
        return new Date(this.fechaOrigen.getTimeInMillis());
    }
    
    public Calendar getFechaOrigenC() {
        return this.fechaOrigen;
    }


    public String getEstadoEvento() {
        return estadoEvento;
    }

    
    public void anularEsteEvento(){
        this.estadoEvento = ESTADO_ANULADO;
    }
    
    public void regularizar(){
        this.estadoEvento = ESTADO_REGULAR;
    }

    public boolean eventoEstaRegular() {
        return this.estadoEvento.equals(ESTADO_REGULAR);
    }
    public boolean eventoEstaAnulado() {
        return this.estadoEvento.equals(ESTADO_ANULADO);
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }
    
    
    

    @Override
    public int compareTo(Object t) {
        int compararId=((Evento)t).getIdEvento();
        /* For Ascending order*/
        return this.idEvento-compararId;
    }

    boolean esPosteriorA(Evento unEventoAComparar) {
        return (this.idEvento > unEventoAComparar.getIdEvento());
    }
    
    
    public Object[] devolverVectorEvento() {
        String unTipoDeEvento = "------";
        int unId = 0;
        String unaFechaDeOrigen = ( new SimpleDateFormat( "dd/MM/yyyy" ) ).format( this.fechaOrigen.getTime() );
        if (this instanceof MovimientoInternoMateriaPrima){
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) this;
            unId = unMovimiento.getId();
            if (!unMovimiento.poseeEquipamientoOrigen())
                unTipoDeEvento = "Ingreso de M.P.";
            else
                unTipoDeEvento = "Movimiento de Lote";
        }
        if (this instanceof Estacionamiento){
            Estacionamiento unEstacionamiento = (Estacionamiento) this;
            unId = unEstacionamiento.getId();
            unTipoDeEvento = "Estacionamiento";
        }
        if (this instanceof OrdenDeCompra){
            OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) this;
            unId = unaOrdenDeCompra.getId();
            unTipoDeEvento = "Compra";
        }
        if (this instanceof AnalisisLaboratorio){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) this;
            unId = unAnalisis.getId();
            unTipoDeEvento = "Analisis de Laboratorio";
        }
        //Object[] vec ={unId, unTipoDeEvento, unaFechaDeOrigen, this.estadoEvento};
        Object[] vec ={this.idEvento, unTipoDeEvento, unaFechaDeOrigen, this.estadoEvento};
        return vec;
    }

    public boolean poseeEstadoEvento(String estadoSeleccionado) {
        return this.estadoEvento.equals(estadoSeleccionado);
    }

    public boolean fechaOrigenEstaEntre(Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) {
        fechaOrigenInferior.set(Calendar.HOUR_OF_DAY, 0);
        fechaOrigenInferior.set(Calendar.MINUTE, 0);
        fechaOrigenInferior.set(Calendar.SECOND, 0);
        fechaOrigenInferior.set(Calendar.MILLISECOND, 0);
        

        
        fechaOrigenSuperior.set(Calendar.HOUR_OF_DAY, 23);
        fechaOrigenSuperior.set(Calendar.MINUTE, 59);
        fechaOrigenSuperior.set(Calendar.SECOND, 59);        
        return ((this.getFechaOrigenC().compareTo(fechaOrigenInferior)>=0 )&& this.getFechaOrigenC().compareTo(fechaOrigenSuperior)<=0);
    }
}
