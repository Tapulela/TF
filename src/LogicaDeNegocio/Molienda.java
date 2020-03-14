
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.Consultable;
import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Molienda extends Transformacion implements Reporte, Filtrable, Consultable {
    private String sector;
    private String turno;

    public Molienda(int id, Usuario unUsuario, Date fechaOrigen, String estado, int idEvento, Equipamiento unEquipamiento, String sector, String turno) {
        super(id, unUsuario, fechaOrigen, estado, idEvento, unEquipamiento, Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA);
        this.sector = sector;
        this.turno = turno;
    }
    
    

    public Molienda(String sector, String turno, Equipamiento equipamientoAsociado, Usuario unUsuario, ArrayList detallesImplicados) {
        super(Transformacion.ESTADO_REGULAR, equipamientoAsociado, unUsuario, detallesImplicados, Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA);
        this.sector = sector;
        this.turno = turno;
    }
    
    
    

    
    public boolean esEnMolino(Equipamiento unMolinoARevisar) {
        return super.poseeEquipamiento(unMolinoARevisar);
    }

    public String getSector() {
        return sector;
    }

    public String getTurno() {
        return turno;
    }

    @Override
    public Object[] devolverVector() {
        Object[] vec ={this.getId(),this.getEquipamientoAsociado().getNombre(), this.getEstado(), Organizacion.expresarCalendario(this.getFechaOrigenC())};
        return vec;
    }

    public Float getPesoDisponibleAEgresarKg() {
        Float pesoDisponible = this.getPesoTotalEnKg();
        Iterator salidas = this.getSalidasAsociadas().iterator();
        while (salidas.hasNext()){
            Salida unaSalida = (Salida) salidas.next();
            if (unaSalida.estaRegular())
                pesoDisponible = pesoDisponible - Organizacion.convertirUnidadPeso(unaSalida.getUnidadMedidaPeso(), unaSalida.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO);
        }
        return pesoDisponible;
    }

    public boolean poseeUnoOMasEgresos() {
        boolean seEncontro = false;
        Iterator recorredorDeSalidas = this.getSalidasAsociadas().iterator();
        while (!seEncontro && recorredorDeSalidas.hasNext()){
            Salida unaSalida = (Salida) recorredorDeSalidas.next();
            seEncontro = unaSalida.estaRegular();
        }
        return seEncontro;
    }
    public OrdenDeProduccion getOrdenDeProduccionAsociada(){
        Iterator recorredorDeDetalles = this.getDetallesAsociados().iterator();
        if (recorredorDeDetalles.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) recorredorDeDetalles.next();
            return unDetalle.getLoteImplicado().getOrdenDeProduccionAsociada();
        }
        return null;
    }

    @Override
    public String getReporte() {
        String retorno = "";
        retorno = retorno + "ID: "+this.getId() +"\t\t\t\t\tFecha de origen: "+Organizacion.expresarCalendario(this.getFechaOrigenC());
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Cantidad de lotes molidos: "+obtenerCantidadDeLotesImplicados()+"\t\tpeso total molido: "+UtilidadesInterfazGrafica.formatearFlotante(getPesoTotalEnKg())+" kgs.";
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
        if (unObjeto instanceof Molino)
            return poseeEquipamiento((Molino)unObjeto);
        if (unObjeto instanceof OrdenDeProduccion)
            return poseeOrdenDeProduccionImplicada((OrdenDeProduccion)unObjeto);
        if (unObjeto instanceof Lote)
            return poseeLoteImplicado((Lote)unObjeto);
        if (unObjeto instanceof Lote)
            return poseeLoteImplicado((Lote)unObjeto);
        if (unObjeto instanceof String)
            return this.poseeEstado((String) unObjeto);
        return cumpleCriterio;
    }
}
