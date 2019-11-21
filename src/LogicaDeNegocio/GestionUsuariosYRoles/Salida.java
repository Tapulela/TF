/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio.GestionUsuariosYRoles;

import LogicaDeNegocio.Evento;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Salida extends Evento{
    
    private int id;
    private String unEstado;
    
    private ArrayList lotesImplicados;
    
    
    public Salida(int idEvento, String estado, Usuario unUsuario, Date fechaOrigen) {
        super(idEvento, estado, unUsuario, fechaOrigen);
    }
    
}
