/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.Salida;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;

/**
 *
 * @author usuario
 */
public class Egreso extends Salida{
    
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    
    private Molienda moliendaImplicada;
    
    public Egreso(int id, String estado, String descripcion, String comentario, int idEvento, Usuario unUsuario, Date fechaOrigen, Molienda unaMolienda, 
            String unaUnidadMedidaTransporte, int unaCantidadUtilizada, String unaUnidadMedidaPeso, Float unPesoUtilizado) {
        super(id, estado, descripcion, comentario, idEvento, unUsuario, fechaOrigen, unaMolienda, unaUnidadMedidaTransporte, unaCantidadUtilizada, unaUnidadMedidaPeso, unPesoUtilizado);
        this.moliendaImplicada = unaMolienda;
    }

    public Egreso(String descripcion, String comentario, Usuario unUsuario, Molienda unaMolienda, 
            String unaUnidadMedidaTransporte, int unaCantidadUtilizada, String unaUnidadMedidaPeso, Float unPesoUtilizado) {
        super(descripcion, comentario, ESTADO_REGULAR, unUsuario, unaMolienda, unaUnidadMedidaTransporte, unaCantidadUtilizada, unaUnidadMedidaPeso, unPesoUtilizado);
        this.moliendaImplicada = unaMolienda;
        
    }

    public Molienda getMoliendaImplicada() {
        return moliendaImplicada;
    }

    public boolean esEnMolino(Equipamiento unMolinoSeleccionado) {
        return this.moliendaImplicada.esEnMolino(unMolinoSeleccionado);
    }
    
    public Object[] devolverVectorEgreso() {
        Object[] vec ={this.getId(),this.getMoliendaImplicada().getEquipamientoAsociado().getNombre(), this.getEstado(), Organizacion.expresarCalendario(this.getFechaOrigenC()), this.getMoliendaImplicada().getId(), UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(this.getUnidadMedidaPeso(), this.getPesoUtilizdo(), Lote.UNIDAD_MEDIDA_KILOGRAMO))};
        return vec;
    }

    
}
