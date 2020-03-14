/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio.Auditoria;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import LogicaDeNegocio.Organizacion;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class Auditoria {
    private int id;
    private Calendar fechaOrigen;
    private String tabla;
    private int idAsociado;
    private String operacion;
    private ArrayList detalles;
    
    private Usuario usuario;

    public Auditoria(int id, java.sql.Date fechaOrigen, String tabla, int idAsociado, String operacion, Usuario usuario) {
        this.id = id;
        this.fechaOrigen = Calendar.getInstance();
        this.fechaOrigen.setTime(fechaOrigen);
        this.tabla = tabla;
        this.idAsociado = idAsociado;
        this.operacion = operacion;
        this.usuario = usuario;
    }

    public void setDetalles(ArrayList detalles) {
        this.detalles = detalles;
    }
    
    public Object[] devolverVector() {
        Object[] vec ={this.id, Organizacion.expresarCalendario(this.fechaOrigen), this.operacion, this.tabla, this.idAsociado, this.usuario.getApellido()+" "+this.usuario.getNombre()};
    return vec;
    }

    public boolean esDeTabla(String unaTablaAsociada) {
        return unaTablaAsociada.toLowerCase().equals(this.tabla);
    }

    public boolean fechaOrigenEstaEntre(Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) {
        fechaOrigenInferior.set(Calendar.HOUR_OF_DAY, 0);
        fechaOrigenInferior.set(Calendar.MINUTE, 0);
        fechaOrigenInferior.set(Calendar.SECOND, 0);
        fechaOrigenInferior.set(Calendar.MILLISECOND, 0);
        
        fechaOrigenSuperior.set(Calendar.HOUR_OF_DAY, 23);
        fechaOrigenSuperior.set(Calendar.MINUTE, 59);
        fechaOrigenSuperior.set(Calendar.SECOND, 59);        
        return ((this.fechaOrigen.compareTo(fechaOrigenInferior)>=0 )&& this.fechaOrigen.compareTo(fechaOrigenSuperior)<=0);
    }
    
    public boolean poseeOperacion(String unaOperacion){
        return unaOperacion.equals(this.operacion);
    }
    
    
    
}
