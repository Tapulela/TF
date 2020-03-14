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
public class Perdida extends Salida{
    
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    
    
    //En realidad, un movimiento puede ya incluir la informacion del lote asociado, pero pueden haber perdidas que no sean registradas sobre movimientos, sino registradas manualmente.
    
    //Podría hacer un metodo que calcule el equipamiento donde ocurrió la perdida del lote si fuera necesario, analizando el ultimo movimiento anterior.
    private Lote loteImplicado;
    private MovimientoInternoMateriaPrima unMovimientoImplicado;
    private Equipamiento equipamientoImplicado;
    
    public Perdida(int id, String estado, String descripcion, String comentario, int idEvento, Usuario unUsuario, Date fechaOrigen, Lote unLoteImplicado, MovimientoInternoMateriaPrima unMovimientoImplicado, Equipamiento unEquipamiento, 
            String unaUnidadMedidaTransporte, int unaCantidadUtilizada, String unaUnidadMedidaPeso, Float unPesoUtilizado) {
        super(id, estado, descripcion, comentario, idEvento, unUsuario, fechaOrigen, unMovimientoImplicado, unaUnidadMedidaTransporte, unaCantidadUtilizada, unaUnidadMedidaPeso, unPesoUtilizado);
        this.loteImplicado = unLoteImplicado;
        this.unMovimientoImplicado = unMovimientoImplicado;
        this.equipamientoImplicado = unEquipamiento;
    }

    public Perdida(String descripcion, String comentario, Usuario unUsuario, Lote unLoteImplicado, MovimientoInternoMateriaPrima unMovimientoImplicado, Equipamiento unEquipamiento, 
            String unaUnidadMedidaTransporte, int unaCantidadUtilizada, String unaUnidadMedidaPeso, Float unPesoUtilizado) {
        super(descripcion, comentario, ESTADO_REGULAR, unUsuario, unMovimientoImplicado, unaUnidadMedidaTransporte, unaCantidadUtilizada, unaUnidadMedidaPeso, unPesoUtilizado);
        this.loteImplicado = unLoteImplicado;
        this.unMovimientoImplicado = unMovimientoImplicado;
        this.equipamientoImplicado = unEquipamiento;
    }

    public boolean poseeEquipamiento(Equipamiento unEquipamientoSeleccionado) {
        return unEquipamientoSeleccionado.equals(this.equipamientoImplicado);
    }

    public Lote getLoteImplicado() {
        return loteImplicado;
    }
    
    
}
