/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio.GestionUsuariosYRoles;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Rol {
    public static final String ROL_GerenteAreaCompras = "GerenteAreaProduccion";
    public static final String ROL_OperadorBalanza = "OperadorBalanza";
    private int id;
    private String nombre;
    ArrayList permisos;
}
