/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio.Auditoria;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class AuditoriaOrdenProduccion {
    private int id;
    private Calendar fechaOrigenAnterior;
    private float cantidadAProducir;
    private String unidadDeMedida;
    private Calendar fechaEntregaProductoTerminado;
    private String estado;
    private String descripcion;
    
    private ArrayList ordenesCompraImplicadas;
    
    private Usuario usuarioAsociado;
}
