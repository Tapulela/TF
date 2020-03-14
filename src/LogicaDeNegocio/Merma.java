/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Salida;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;

/**
 *
 * @author usuario
 */
public class Merma extends Salida{
    
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    
    private Lote loteImplicado;
    private Estacionamiento estacionamientoImplicado;
    private MovimientoInternoMateriaPrima movimientoImplicado;
    
    public Merma(int id, String estado, String descripcion, String comentario, int idEvento, Usuario unUsuario, Date fechaOrigen, Estacionamiento unEstacionamiento, 
            String unaUnidadMedidaTransporte, int unaCantidadUtilizada, String unaUnidadMedidaPeso, Float unPesoUtilizado, Lote unLoteImplicado, MovimientoInternoMateriaPrima unMovimientoImplicado) {
        super(id, estado, descripcion, comentario, idEvento, unUsuario, fechaOrigen, unEstacionamiento, unaUnidadMedidaTransporte, unaCantidadUtilizada, unaUnidadMedidaPeso, unPesoUtilizado);
        this.estacionamientoImplicado = unEstacionamiento;
        this.loteImplicado = unLoteImplicado;
        this.movimientoImplicado = unMovimientoImplicado;
    }

    public Merma(String descripcion, String comentario, Usuario unUsuario, Estacionamiento unEstacionamiento, 
            String unaUnidadMedidaTransporte, int unaCantidadUtilizada, String unaUnidadMedidaPeso, Float unPesoUtilizado, Lote unLoteImplicado, MovimientoInternoMateriaPrima unMovimientoImplicado) {
        super(descripcion, comentario, ESTADO_REGULAR, unUsuario, unEstacionamiento, unaUnidadMedidaTransporte, unaCantidadUtilizada, unaUnidadMedidaPeso, unPesoUtilizado);
        this.estacionamientoImplicado = unEstacionamiento;
        this.loteImplicado = unLoteImplicado;
        this.movimientoImplicado = unMovimientoImplicado;
    }

    public Estacionamiento getEstacionamientoImplicado() {
        return estacionamientoImplicado;
    }

    public Lote getLoteImplicado() {
        return loteImplicado;
    }

    public MovimientoInternoMateriaPrima getMovimientoImplicado() {
        return movimientoImplicado;
    }
    
    
    
    
    
    
}
