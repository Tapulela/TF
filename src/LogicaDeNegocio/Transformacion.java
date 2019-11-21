/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import LogicaDeNegocio.Lote;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class Transformacion extends Evento {
    private int id;
    private String estado;
    private ArrayList lotesImplicados;
    private Equipamiento equipamientoAsociado;
    

    public Transformacion(int id, Usuario unUsuario, java.sql.Date fechaOrigen, String estado, int idEvento, Equipamiento unEquipamiento) {
        super(idEvento, estado, unUsuario, fechaOrigen);
        this.id = id;
        this.estado = estado;
        this.equipamientoAsociado = unEquipamiento;
        lotesImplicados = new ArrayList();
    }

    public Transformacion(String estado, ArrayList lotesImplicados, Equipamiento equipamientoAsociado, Usuario unUsuario) {
        super(estado, unUsuario);
        this.estado = estado;
        this.lotesImplicados = lotesImplicados;
        this.equipamientoAsociado = equipamientoAsociado;
    }
    
    

    public boolean estaRegular() {
        return this.estado.equals("Regular");
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public ArrayList getLotesImplicados() {
        return lotesImplicados;
    }

    public Equipamiento getEquipamientoAsociado() {
        return equipamientoAsociado;
    }
    
    
    public void agregarLote(Lote unLote){
        this.lotesImplicados.add(unLote);
    }
    
    public boolean poseeEquipamiento(Equipamiento unEquipamiento){
        return this.equipamientoAsociado.equals(unEquipamiento);
    }
    
    public boolean poseeOrdenDeProduccionImplicada(OrdenDeProduccion unaOrdenDeProduccion){
        boolean seEncontro = false;
        Iterator lotesARecorrer = this.lotesImplicados.iterator();
        while (lotesARecorrer.hasNext() && !seEncontro){
            Lote unLote = (Lote) lotesARecorrer.next();
            seEncontro = unLote.poseeOrdenDeProduccionAsociada(unaOrdenDeProduccion);
        }
        return seEncontro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    void anular() {
        this.setEstado(this.ESTADO_ANULADO);
        super.anularEsteEvento();
    }
    
    public boolean poseeLoteImplicado(Lote unLoteAVerificar){
        boolean seEncontro = false;
        Iterator lotesARecorrer = this.lotesImplicados.iterator();
        while (lotesARecorrer.hasNext() && !seEncontro){
            Lote unLote = (Lote) lotesARecorrer.next();
            seEncontro = unLote.equals(unLoteAVerificar);
        }
        return seEncontro;
    }
    
    boolean poseeUnoOMasEventosRegularesPosteriores() {
        boolean seEncontroEventoPosteriorRegular = false;
        Iterator recorredorDeLotes = this.getLotesImplicados().iterator();
        while (recorredorDeLotes.hasNext() && !seEncontroEventoPosteriorRegular){
            Lote unLote = (Lote) recorredorDeLotes.next();
            Iterator recorredorDeEventos = unLote.getEventosImplicadosPosteriores().iterator();
            while (recorredorDeEventos.hasNext() && !seEncontroEventoPosteriorRegular){
                Evento unEvento = (Evento) recorredorDeEventos.next();
                seEncontroEventoPosteriorRegular = (unEvento.esPosteriorA(this) && unEvento.eventoEstaRegular());
            }
        }
        return seEncontroEventoPosteriorRegular;
    }
    
    public boolean poseeEstado (String unEstado){
        return this.estado.equals(unEstado);
    }
    
    

}
