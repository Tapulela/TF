/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;



import InterfazGrafica.Consultable;
import InterfazGrafica.Paneles.PanelGestionEstacionamientos;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Estacionamiento extends Transformacion implements Reporte, Filtrable, Consultable {

    
    private Calendar fechaExtraccion;
    
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    
    private ArrayList mermasAsociadas;

    public Estacionamiento(int id, Usuario unUsuario, java.sql.Date fechaOrigen, String unEstado, int idEvento, CamaraEstacionamiento unaCamara, java.sql.Date fechaExtraccion) {
        super(id, unUsuario, fechaOrigen, unEstado, idEvento, unaCamara, Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE);
        this.fechaExtraccion = Calendar.getInstance();
        this.fechaExtraccion.setTime(fechaExtraccion);
        this.mermasAsociadas = new ArrayList();
    }

    public Estacionamiento(Calendar fechaExtraccion, Equipamiento equipamientoAsociado, Usuario unUsuario, Calendar unaFechaOrigen) {
        super(ESTADO_REGULAR, equipamientoAsociado, unUsuario, Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE);
        this.fechaExtraccion = fechaExtraccion;
        this.setFechaOrigen(unaFechaOrigen);
        this.mermasAsociadas = new ArrayList();
    }

    
    
    public java.sql.Date getFechaExtraccion(){
        return new Date(this.fechaExtraccion.getTimeInMillis()); 
    }


    public boolean esEnCamara(Equipamiento unaCamaraARevisar) {
        return super.poseeEquipamiento(unaCamaraARevisar);
    }

    boolean fechaExtraccionEstaEntre(Calendar fechaExtraccionInferior, Calendar fechaExtraccionSuperior) {
        fechaExtraccionInferior.set(Calendar.HOUR, 0);
        fechaExtraccionInferior.set(Calendar.MINUTE, 0);
        fechaExtraccionInferior.set(Calendar.SECOND, 0);
        fechaExtraccionInferior.set(Calendar.MILLISECOND, 0);

        fechaExtraccionSuperior.set(Calendar.HOUR_OF_DAY, 24);
        fechaExtraccionSuperior.set(Calendar.MINUTE, 59);
        fechaExtraccionSuperior.set(Calendar.SECOND, 59);        
        return (this.getFechaOrigenC().compareTo(fechaExtraccionInferior)>=0 && this.getFechaOrigenC().compareTo(fechaExtraccionSuperior)<=0);
    }
    
    @Override
    public Object[] devolverVector() {
        Object[] vec ={this.getId(),this.getEquipamientoAsociado().getNombre(), this.getEstado(), Organizacion.expresarCalendario(this.getFechaOrigenC()), ( new SimpleDateFormat( "dd/MM/yyyy" ) ).format( this.getFechaExtraccion().getTime() )};
        return vec;
    }

    

    

    public Calendar getFechaExtraccionC() {
        return this.fechaExtraccion;
    }

    public static void prepararDetalle(ArrayList lotesAsociados, Estacionamiento unEstacionamiento) {
        Iterator recorredorLotes = lotesAsociados.iterator();
        while (recorredorLotes.hasNext()){
            Lote unLote = (Lote) recorredorLotes.next();
            DetalleTransformacion unDetalle = new DetalleTransformacion(unLote.getUnidadDeMedidaTransporte(), unLote.getCantidadTotalUnidadesDeTransporteIngresadas(), unLote.getUnidadDeMedida(), unLote.getCantidadTotalPesoIngresado(), unLote, unEstacionamiento);
            unEstacionamiento.agregarDetalle(unDetalle);
        }
    }    

    public boolean estaEnCurso() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaExtraccionLimiteInferior = (Calendar) fechaExtraccion.clone();
        
        fechaExtraccionLimiteInferior.set(Calendar.HOUR_OF_DAY, 0);
        fechaExtraccionLimiteInferior.set(Calendar.MINUTE, 0);
        fechaExtraccionLimiteInferior.set(Calendar.SECOND, 0);
        fechaExtraccionLimiteInferior.set(Calendar.MILLISECOND, 0);
        
        return (fechaActual.compareTo(fechaExtraccionLimiteInferior)< 0);
    }

    
    public void agregarMerma (Merma unaMerma){
        this.mermasAsociadas.add(unaMerma);
    }

    public boolean poseeMermaAsociadaRegular(Lote unLote) {
        Iterator recorredorMermas = this.mermasAsociadas.iterator();
        while (recorredorMermas.hasNext()){
            Merma unaMerma = (Merma) recorredorMermas.next();
            if (unaMerma.poseeLoteImplicado(unLote) && unaMerma.estaRegular())
                return true;
        }
        return false;
    }

    @Override
    public String getReporte() {
        String retorno = "";
        retorno = retorno + "ID: "+this.getId() +"\t\tFecha de origen: "+Organizacion.expresarCalendario(this.getFechaOrigenC())+ "\t\tFecha de extracción: "+Organizacion.expresarCalendario(this.getFechaExtraccionC());
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Cantidad de lotes estacionados: "+this.obtenerCantidadDeLotesImplicados();
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Equipamiento asociado: "+this.getEquipamientoAsociado().getNombre();
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Estado: "+this.getEstado();
        return retorno;
    }

    @Override
    public boolean cumpleCriterio(String unCriterio, Object unObjeto) {
        boolean cumpleCriterio = false;
        if (unObjeto == null)
            return cumpleCriterio;
        if (unObjeto instanceof CamaraEstacionamiento)
            return esEnCamara((CamaraEstacionamiento)unObjeto);
        if (unObjeto instanceof OrdenDeProduccion)
            return poseeOrdenDeProduccionImplicada((OrdenDeProduccion)unObjeto);
        if (unObjeto instanceof Lote)
            return poseeLoteImplicado((Lote)unObjeto);
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar) && unCriterio.equals(PanelGestionEstacionamientos.criterios[4])){
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaOrigenEstaEntre(fechaInferior, fechaSuperior);
        }
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar) && unCriterio.equals(PanelGestionEstacionamientos.criterios[5])){
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaExtraccionEstaEntre(fechaInferior, fechaSuperior);
        }
        if (unObjeto instanceof String)
            return this.poseeEstado((String) unObjeto);
        return cumpleCriterio;
    }

    




    
    
}
