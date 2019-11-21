/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;



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
public class Estacionamiento extends Transformacion {
    private Calendar fechaExtraccion;
    
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";

    public Estacionamiento(int id, Usuario unUsuario, java.sql.Date fechaOrigen, String unEstado, int idEvento, CamaraEstacionamiento unaCamara, java.sql.Date fechaExtraccion) {
        super(id, unUsuario, fechaOrigen, unEstado, idEvento, unaCamara);
        this.fechaExtraccion = Calendar.getInstance();
        this.fechaExtraccion.setTime(fechaExtraccion);
    }

    public Estacionamiento(Calendar fechaExtraccion, ArrayList lotesImplicados, Equipamiento equipamientoAsociado, Usuario unUsuario) {
        super(ESTADO_REGULAR, lotesImplicados, equipamientoAsociado, unUsuario);
        this.fechaExtraccion = fechaExtraccion;
    }

    
    
    public java.sql.Date getFechaExtraccion(){
        return new Date(this.fechaExtraccion.getTimeInMillis());
    }


    boolean esEnCamara(Equipamiento unaCamaraARevisar) {
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
    
    
    public Object[] devolverVector() {
        
        Object[] vec ={this.getId(),this.getEquipamientoAsociado().getNombre(), this.getEstado(), Organizacion.expresarCalendario(this.getFechaOrigenC()), ( new SimpleDateFormat( "dd-MM-yyyy" ) ).format( this.getFechaExtraccion().getTime() )};
        return vec;
    }

    

    

    public Calendar getFechaExtraccionC() {
        return this.fechaExtraccion;
    }




    
    
}
