/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio.GestionUsuariosYRoles;

/**
 *
 * @author usuario
 */
public class Permiso {
    public static final String PERMISO_REGISTRO_INGRESO = "Permiso Registro Ingreso MP";
    public static final String PERMISO_ANULACION_INGRESO = "Permiso Anulacion MP";
    
    public static final String PERMISO_ALTA_EQUIPAMIENTO = "Permiso Alta Equipamiento";
    public static final String PERMISO_BAJA_EQUIPAMIENTO = "Permiso Baja Equipamiento";
    public static final String PERMISO_MODIFICACION_EQUIPAMIENTO = "Permiso Modificacion Equipamiento";
    
    public static final String PERMISO_REGISTRO_ORDENES_PRODUCCION = "Permiso Registro Orden Produccion";
    public static final String PERMISO_ANULACION_ORDENES_PRODUCCION = "Permiso Anulacion Orden Produccion";
    public static final String PERMISO_MODIFICACION_ORDENES_PRODUCCION = "Permiso Modificacion Orden Produccion";
    
    public static final String PERMISO_REGISTRO_ORDENES_COMPRA = "Permiso Registro Orden Compra";
    public static final String PERMISO_ANULACION_ORDENES_COMPRA = "Permiso Anulacion Orden Compra";
    public static final String PERMISO_MODIFICACION_ORDENES_COMPRA = "Permiso Modificacion Orden Compra";
    
    private int id;
    private final String permiso;

    public Permiso(int id, String permiso) {
        this.id = id;
        this.permiso = permiso;
    }
    
    public Permiso(String permiso) {
        this.permiso = permiso;
    }
    
    
    
}
