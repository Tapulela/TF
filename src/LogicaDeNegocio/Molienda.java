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
public class Molienda extends Transformacion {
    private int id;
    private String sector;
    private String turno;

    public Molienda(int id, Usuario unUsuario, java.sql.Date fechaOrigen, String estado, int idEvento, Molino unMolino) {
        super(id, unUsuario, fechaOrigen, estado, idEvento, unMolino);
    }
}
