/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.Consultable;
import InterfazGrafica.Paneles.PanelGestionSalidas;
import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.Evento;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class Salida extends Evento implements Reporte, Filtrable, Consultable {
    public static final String TIPO_EGRESO = "Egreso";
    public static final String TIPO_PERDIDA = "Perdida";
    public static final String TIPO_Merma = "Merma";
    private String estado;
    private String descripcion;
    private String comentario;
    
    private String unidadMedidaTransporte;
    private int cantidadUtilizada;
    private String unidadMedidaPeso;
    private Float pesoUtilizdo;
    private Evento eventoAsociado; //Puede ser una molienda, un estacionamiento o un movimiento

    public Salida(int id, String estado, String descripcion, String comentario, int idEvento, Usuario unUsuario, Date fechaOrigen, Evento eventoAsociado, 
            String unaUnidadMedidaTransporte, int unaCantidadUtilizada, String unaUnidadMedidaPeso, Float unPesoUtilizado) {
        super(idEvento, estado, unUsuario, fechaOrigen, id);
        this.estado = estado;
        this.descripcion = descripcion;
        this.comentario = comentario;
        this.eventoAsociado = eventoAsociado;
        
        this.unidadMedidaTransporte = unaUnidadMedidaTransporte;
        this.cantidadUtilizada = unaCantidadUtilizada;
        this.unidadMedidaPeso = unaUnidadMedidaPeso;
        this.pesoUtilizdo = unPesoUtilizado;
    }

    public Salida(String descripcion, String comentario, String estado, Usuario unUsuario, Evento eventoAsociado, 
            String unaUnidadMedidaTransporte, int unaCantidadUtilizada, String unaUnidadMedidaPeso, Float unPesoUtilizado) {
        super(estado, unUsuario);
        this.descripcion = descripcion;
        this.comentario = comentario;
        this.eventoAsociado = eventoAsociado;
        this.estado = estado;
        this.unidadMedidaTransporte = unaUnidadMedidaTransporte;
        this.cantidadUtilizada = unaCantidadUtilizada;
        this.unidadMedidaPeso = unaUnidadMedidaPeso;
        this.pesoUtilizdo = unPesoUtilizado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getComentario() {
        return comentario;
    }
    
    public boolean estaRegular() {
        return this.estado.equals("Regular");
    }


    public String getEstado() {
        return estado;
    }

    public String getUnidadMedidaTransporte() {
        return unidadMedidaTransporte;
    }

    public int getCantidadBolsasUtilizada() {
        return cantidadUtilizada;
    }

    public String getUnidadMedidaPeso() {
        return unidadMedidaPeso;
    }

    public Float getPesoUtilizdo() {
        return pesoUtilizdo;
    }
    
    
    
    public boolean poseeOrdenDeProduccionImplicada(OrdenDeProduccion unaOrdenDeProduccion){
        if (eventoAsociado instanceof Transformacion){
            Transformacion unaTransformacion = (Transformacion) eventoAsociado;
            return unaTransformacion.poseeOrdenDeProduccionImplicada(unaOrdenDeProduccion);
        }
        return false;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    void anular() {
        this.setEstado(Evento.ESTADO_ANULADO);
        super.anularEsteEvento();
    }
    
    public boolean poseeEventoImplicado(Evento unEvento){
        return unEvento.equals(this.eventoAsociado);
    }
    
    public boolean poseeLoteImplicado(Lote unLoteAVerificar){
        if (eventoAsociado instanceof Transformacion){
            //Transformacion unaTransformacion = (Transformacion) eventoAsociado;
            //return unaTransformacion.poseeLoteImplicado(unLoteAVerificar);
            return unLoteAVerificar.equals(((Merma)this).getLoteImplicado());
        }
        return false;
    }
    
    public boolean poseeUnoOMasEventosRegularesPosteriores() {
        //EN REALIDAD NO HAY EVENTOS POSTERIORES DESPUES DE UN EGRESO.
        return false;
    }
    
    public boolean poseeEstado (String unEstado){
        return this.estado.equals(unEstado);
    }

    public boolean esEnEquipamiento(Equipamiento unEquipamientoSeleccionado) {
        boolean retorno = false;
        if (this instanceof Perdida){
            Perdida unaPerdida = (Perdida) this;
            return unaPerdida.poseeEquipamiento(unEquipamientoSeleccionado);
        }
        if (eventoAsociado == null)
            return retorno;
        if (this.eventoAsociado instanceof Molienda){
            Molienda unaMolienda = (Molienda) this.eventoAsociado;
            return unaMolienda.poseeEquipamiento(unEquipamientoSeleccionado);
        }
        if (this.eventoAsociado instanceof Estacionamiento){
            Estacionamiento unEstacionamiento = (Estacionamiento) this.eventoAsociado;
            return unEstacionamiento.poseeEquipamiento(unEquipamientoSeleccionado);
        }
        return retorno;
    }

    public boolean esDeTipo(String unTipo) {
        boolean retorno = false;
        switch (unTipo){
            case (Salida.TIPO_EGRESO):
                return (this instanceof Egreso);
            case (Salida.TIPO_Merma):
                return (this instanceof Merma);
            case (Salida.TIPO_PERDIDA):
                return (this instanceof Perdida);
        }
        return retorno;
    }
    
    public Object[] devolverVectorSalida() {
        String equipamiento = this.obtenerEquipamientoS();
        String lote = this.obtenerLoteS();
        String evento = this.obtenerEventoS();
        Object[] vec ={this.getId(),this.obtenerTipoS(), equipamiento, this.getEstado(), Organizacion.expresarCalendario(this.getFechaOrigenC()), lote, evento, UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(this.getUnidadMedidaPeso(), this.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO)), this.cantidadUtilizada};
        return vec;
    }

    private String obtenerTipoS() {
        String retorno = "----";
        if (this instanceof Egreso)
            return Salida.TIPO_EGRESO;
        if (this instanceof Merma)
            return Salida.TIPO_Merma;
        if (this instanceof Perdida)
            return Salida.TIPO_PERDIDA;
        return retorno;
    }

    private String obtenerEventoS() {
        String retorno = "----";
        if (this.eventoAsociado == null)
            return retorno;
        if (this.eventoAsociado instanceof Estacionamiento)
            return "Estacionamiento";
        if (this.eventoAsociado instanceof Molienda)
            return "Molienda";
        if (this.eventoAsociado instanceof MovimientoInternoMateriaPrima)
            return "Movimiento";
        return retorno;
    }

    private String obtenerLoteS() {
        String retorno = "----";
        if (this instanceof Perdida)
            return ((Perdida) this).getLoteImplicado().getEtiqueta();
        if (this instanceof Merma)
            return ((Merma) this).getLoteImplicado().getEtiqueta();
        return retorno;        
    }

    private String obtenerEquipamientoS() {
        String retorno = "----";
        if (this.eventoAsociado == null)
            return retorno;
        if (this.eventoAsociado instanceof Transformacion)
            return ((Transformacion) this.eventoAsociado).getEquipamientoAsociado().getNombre();
        if (this.eventoAsociado instanceof MovimientoInternoMateriaPrima)
            return retorno;
        return retorno;
    }
    

    @Override
    public String getReporte() {
        String retorno = "";
            retorno = retorno + "ID: "+this.getId() +"\t\t Fecha: "+Organizacion.expresarCalendario(this.getFechaOrigenC())+"\tTipo de salida: "+this.obtenerTipoS()+"\tCantidad registrada: "+UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(this.getUnidadMedidaPeso(), this.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO))+" kg(s)";
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Lote implicado: "+obtenerLoteS()+ "\t\tEvento asociado: "+this.obtenerEventoS()+", ID: "+this.getEventoAsociado().getId();
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Equipamiento asociado: "+this.obtenerEquipamientoS()+"\tCantidad de unidades implicadas: "+this.getCantidadBolsasUtilizada()+" bolsa(s)\n\n"
                    + "Estado: "+ estado +" \n\n";
            return retorno;
    }

    public Evento getEventoAsociado() {
        return eventoAsociado;
    }

    @Override
    public boolean cumpleCriterio(String unCriterio, Object unObjeto) {
        boolean cumpleCriterio = false;
        if (unObjeto == null)
            return cumpleCriterio;
        if (unObjeto instanceof Equipamiento)
            return esEnEquipamiento((Equipamiento)unObjeto);
        if (unObjeto instanceof OrdenDeProduccion)
            return poseeOrdenDeProduccionImplicada((OrdenDeProduccion)unObjeto);
        if (unObjeto instanceof Lote)
            return poseeLoteImplicado((Lote) unObjeto);
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionSalidas.criterios[3]))
            return poseeEstado((String) unObjeto);
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar)){
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaOrigenEstaEntre(fechaInferior, fechaSuperior);
        }
        if (unObjeto instanceof Evento)
            return poseeEventoImplicado((Evento) unObjeto);
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionSalidas.criterios[6]))
            return esDeTipo((String) unObjeto);
        
        return cumpleCriterio;
    }

    @Override
    public Object[] devolverVector() {
        return devolverVectorSalida();
    }

    

    
    

    
    
    
    
}
