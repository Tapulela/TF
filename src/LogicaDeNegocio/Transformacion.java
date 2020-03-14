/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import LogicaDeNegocio.Lote;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Transformacion extends Evento {
    private String estado;
    
    private ArrayList<DetalleTransformacion> detallesAsociados;
    private Equipamiento equipamientoAsociado;
    private String tipoLoteEntrada; //Solo utilizada en la aplicación.
    
    private ArrayList salidasAsociadas;
    

    public Transformacion(int id, Usuario unUsuario, java.sql.Date fechaOrigen, String estado, int idEvento, Equipamiento unEquipamiento, String unTipoLoteEntrada) {
        super(idEvento, estado, unUsuario, fechaOrigen, id);
        this.estado = estado;
        this.equipamientoAsociado = unEquipamiento;
        this.tipoLoteEntrada = unTipoLoteEntrada;
        detallesAsociados = new ArrayList();
        salidasAsociadas = new ArrayList();
    }

    public Transformacion(String estado, Equipamiento equipamientoAsociado, Usuario unUsuario, String unTipoLoteEntrada) {
        super(estado, unUsuario);
        this.estado = estado;
        detallesAsociados = new ArrayList();
        this.equipamientoAsociado = equipamientoAsociado;
        this.tipoLoteEntrada = unTipoLoteEntrada;
        salidasAsociadas = new ArrayList();
    }
    
    public Transformacion(String estado, Equipamiento equipamientoAsociado, Usuario unUsuario, ArrayList detallesImplicados, String unTipoLoteEntrada) {
        super(estado, unUsuario);
        this.estado = estado;
        detallesAsociados = detallesImplicados;
        this.equipamientoAsociado = equipamientoAsociado;
        this.tipoLoteEntrada = unTipoLoteEntrada;
        salidasAsociadas = new ArrayList();
    }

    public ArrayList getSalidasAsociadas() {
        return salidasAsociadas;
    }
    
    public boolean estaRegular() {
        return this.estado.equals("Regular");
    }

    public String getEstado() {
        return estado;
    }

    public ArrayList<DetalleTransformacion> getDetallesAsociados() {
        return detallesAsociados;
    }

    

    public Equipamiento getEquipamientoAsociado() {
        return equipamientoAsociado;
    }
    
    
    public void agregarDetalle(DetalleTransformacion unDetalle){
        this.detallesAsociados.add(unDetalle);
    }
    
    public boolean poseeEquipamiento(Equipamiento unEquipamiento){
        return this.equipamientoAsociado.equals(unEquipamiento);
    }
    
    public boolean poseeOrdenDeProduccionImplicada(OrdenDeProduccion unaOrdenDeProduccion){
        boolean seEncontro = false;
        Iterator detallesARecorrer = this.detallesAsociados.iterator();
        while (detallesARecorrer.hasNext() && !seEncontro){
            DetalleTransformacion unDetalle = (DetalleTransformacion) detallesARecorrer.next();
            seEncontro = unDetalle.poseeOrdenDeProduccionAsociada(unaOrdenDeProduccion);
        }
        return seEncontro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    void anular() {
        this.setEstado(Evento.ESTADO_ANULADO);
        super.anularEsteEvento();
    }
    
    public boolean poseeLoteImplicado(Lote unLoteAVerificar){
        boolean seEncontro = false;
        Iterator detallesARecorrer = this.detallesAsociados.iterator();
        while (detallesARecorrer.hasNext() && !seEncontro){
            DetalleTransformacion unDetalle = (DetalleTransformacion) detallesARecorrer.next();
            seEncontro = unDetalle.poseeLote(unLoteAVerificar);
        }
        return seEncontro;
    }
    
    public boolean poseeUnoOMasEventosRegularesPosteriores() {
        boolean seEncontroEventoPosteriorRegular = false;
        Iterator recorredorDeDetalles = this.getDetallesAsociados().iterator();
        while (recorredorDeDetalles.hasNext() && !seEncontroEventoPosteriorRegular){
            Lote unLote = (Lote) ((DetalleTransformacion)recorredorDeDetalles.next()).getLoteImplicado();
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
    
    public ArrayList obtenerLotesImplicados() {
        ArrayList retorno = new ArrayList();
        Iterator recorredorDeDetalles = detallesAsociados.iterator();
        while (recorredorDeDetalles.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) recorredorDeDetalles.next();
            Lote unLote = unDetalle.getLoteImplicado();
            retorno.add(unLote);
        }
        return retorno;
    }
    public DetalleTransformacion getDetalleAsociadoALote(Lote unLote) {
        
        Iterator detalles = this.detallesAsociados.iterator();
        while (detalles.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) detalles.next();
            if (unDetalle.poseeLote(unLote))
                return unDetalle;
        }
        
        return null;
        
    }

    public Float getPesoTotalEnKg(){
        Float pesoTotal = 0f;
        Iterator detalles = this.detallesAsociados.iterator();
        while (detalles.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) detalles.next();
            pesoTotal = pesoTotal + Organizacion.convertirUnidadPeso(unDetalle.getUnidadMedidaPeso(), unDetalle.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO);
        }
        return pesoTotal;
    }
    
    public String getTipoLoteEntrada(){
        return this.tipoLoteEntrada;
    }
    
    public void agregarSalida (Salida unaSalida){
        this.salidasAsociadas.add(unaSalida);
    }
    
    public Float obtenerTotalTransformadoKgs(OrdenDeProduccion unaOrdenDeProduccion){
        Float totalTransformado = 0f;
        Iterator recorredorDeDetalles = this.detallesAsociados.iterator();
        while (recorredorDeDetalles.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) recorredorDeDetalles.next();
            totalTransformado = totalTransformado + Organizacion.convertirUnidadPeso(unDetalle.getUnidadMedidaPeso(), unDetalle.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO);
        }
        return totalTransformado;
    }
    
    protected int obtenerCantidadDeLotesImplicados() {
        return this.getDetallesAsociados().size();
    }
    
}
