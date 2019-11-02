/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.util.ArrayList;
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

    private String estadoEvento;

    public Evento(int idEvento, String estado, Usuario unUsuario) { //Para recuperar en la bd
        this.idEvento = idEvento;
        this.estadoEvento = estado;
        this.usuarioAsociado = unUsuario;
    }

    public Evento(String estado, Usuario unUsuario) {
        this.estadoEvento = estado;
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
    
    
}
