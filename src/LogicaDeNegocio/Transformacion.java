/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import LogicaDeNegocio.Lote;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class Transformacion extends Evento {
    private int id;
    private Calendar fechaOrigen;
    private String estado;
    private ArrayList lotesImplicados;
    

    public Transformacion(int id, Usuario unUsuario, Calendar fechaOrigen, String unEstado, int idEvento, String estado) {
        super(idEvento, unEstado, unUsuario);
        this.id = id;
        this.fechaOrigen = fechaOrigen;
        this.estado = unEstado;
        lotesImplicados = new ArrayList();
    }
    
    

    public boolean estaRegular() {
        return this.estado.equals("Regular");
    }
    

}
