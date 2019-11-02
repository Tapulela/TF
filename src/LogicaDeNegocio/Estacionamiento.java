/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;



import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class Estacionamiento extends Transformacion {
    private int id;
    private Calendar fechaExtraccion;

    public Estacionamiento(int id, Usuario unUsuario, Calendar fechaOrigen, String unEstado, int idEvento, String estado) {
        super(id, unUsuario, fechaOrigen, unEstado, idEvento, estado);
    }
}
