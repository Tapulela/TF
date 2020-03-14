/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio.GestionUsuariosYRoles;

import InterfazGrafica.Consultable;
import InterfazGrafica.Paneles.PanelGestionUsuarios;
import LogicaDeNegocio.Filtrable;
import LogicaDeNegocio.Reporte;

/**
 *
 * @author usuario
 */
public class Usuario implements Filtrable, Consultable, Reporte {
    private int id;
    private String nombre;
    private String nombreUsuario;
    private String Apellido;
    private String Estado;
    private String dni;
    private String rol;
    public static final String ESTADO_ACTIVO = "Activo";
    public static final String ESTADO_BAJA = "Baja";
    public static final String ROL_GERENTE = "gerenteareaproduccion";
    public static final String ROL_TECNICO = "tecnicolaboratorio";
    public static final String ROL_operadorBalanza = "operadorbalanza";

    public Usuario(int id, String nombre, String Apellido, String Estado, String dni, String rol, String nombreUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.Apellido = Apellido;
        this.Estado = Estado;
        this.dni = dni;
        this.rol = rol;
    }

    public Usuario(String nombre, String nombreUsuario, String Apellido, String dni, String rol) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.Apellido = Apellido;
        this.dni = dni;
        this.rol = rol;
        this.Estado = ESTADO_ACTIVO;
    }
    
    

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean seIdentifica(String unNombre) {
        return this.nombreUsuario.equals(unNombre);
    }

    @Override
    public boolean cumpleCriterio(String unCriterio, Object unObjeto) {
        boolean retorno = false;
        if (unObjeto == null)
            return false;
        if (unCriterio.equals(PanelGestionUsuarios.criterios[0]))   //Usuario
            return this.nombreUsuario.toUpperCase().contains(((String)unObjeto).toUpperCase());
        if (unCriterio.equals(PanelGestionUsuarios.criterios[1]))   //Nombre
            return this.nombre.toUpperCase().contains(((String)unObjeto).toUpperCase());
        if (unCriterio.equals(PanelGestionUsuarios.criterios[2]))   //Apellido
            return this.Apellido.toUpperCase().contains(((String)unObjeto).toUpperCase());
        if (unCriterio.equals(PanelGestionUsuarios.criterios[3]))   //DNI
            return this.dni.toUpperCase().contains(((String)unObjeto).toUpperCase());
        if (unCriterio.equals(PanelGestionUsuarios.criterios[4]))   //Apellido
            return this.Estado.toUpperCase().contains(((String)unObjeto).toUpperCase());
        return retorno;
    }

    @Override
    public Object[] devolverVector() {
        Object[] vec ={this.getId(), this.nombreUsuario, this.rol, this.nombre, this.Apellido, this.dni, this.Estado};
        return vec;
    }

    @Override
    public String getReporte() {
        String retorno = "";
            retorno = retorno + "ID: "+this.getId() +"\t\t Nombre de usuario: "+this.nombreUsuario;
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Apellido: "+this.Apellido+ "\t\tNombre: "+this.nombre+" \t\tDNI: "+this.dni;
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Estado: "+this.Estado+"";
            return retorno;
    }

    public void darDeBaja() {
        this.Estado = Usuario.ESTADO_BAJA;
    }

    public boolean estaDadoDeBaja() {
        return this.Estado.equals(Usuario.ESTADO_BAJA);
    }

    public boolean esGerenteProduccion() {
        return this.rol.equals(Usuario.ROL_GERENTE);
    }
    
    public boolean esTecnicoLaboratorio() {
        return this.rol.equals(Usuario.ROL_TECNICO);
    }
    
    public boolean esOperadorBalanza() {
        return this.rol.equals(Usuario.ROL_operadorBalanza);
    }

    public boolean poseeNombreDeUsuario(String unNombreDeUsuario) {
        if (unNombreDeUsuario == null)
            return false;
        return unNombreDeUsuario.toUpperCase().equals(this.nombreUsuario.toUpperCase());
    }

    
    
}
