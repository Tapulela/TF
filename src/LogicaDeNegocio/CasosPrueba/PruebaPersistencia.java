/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio.CasosPrueba;

import LogicaDeNegocio.Proveedor;
import LogicaDeNegocio.Bascula;
import LogicaDeNegocio.CamaraEstacionamiento;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.Molino;
import LogicaDeNegocio.Deposito;
import LogicaDeNegocio.Organizacion;
import Persistencia.Persistencia;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class PruebaPersistencia {


    public void probarRecuperacion(Organizacion unaOrganizacion) throws SQLException{
        unaOrganizacion.getPersistencia().recuperarOrganizacion(unaOrganizacion);;    
    }
    
    
    

    
    public static void probarActualizarProveedores(Organizacion unaOrganizacion){
        try {
            System.out.println("Iniciando Caso de Prueba de actualizar un proveedor.");
            Set<Integer> llaves = unaOrganizacion.getProveedores().keySet();
            Iterator recorredorLlaves = llaves.iterator();
            int unId = (int) recorredorLlaves.next();
            Proveedor unProveedor = unaOrganizacion.getProveedores().get(unId);
            unProveedor.setCuit("11-11111111-111");
            unProveedor.setRazonSocial("Proveedor modificado");
            unaOrganizacion.getPersistencia().modificarObjeto(unProveedor);
            
        } catch (Exception ex) {
            System.err.println("Ocurrio un error en la prueba de Actualizacion de tuplas de proveedores: "+ex.getMessage());
        }   
        System.out.println("Caso de prueba de Actualizacion de proveedor completado exitosamente.");
    }
    
    public static void probarInsercionEquipamientos(Organizacion unaOrganizacion) {
        try {
            System.out.println("Iniciando Caso de Prueba de Insercion de Equipamientos.");
            Calendar unaFechaAdquisicion = Calendar.getInstance();
            Calendar unaFechaUltimoMantenimiento = Calendar.getInstance();

            //Una bascula
            unaFechaAdquisicion.set(2012, 8, 3);
            unaFechaUltimoMantenimiento.set(2019, 7, 4);
            Bascula unaBascula = new Bascula("Basscula de Apostoles", "Esta en Apostoles", unaFechaAdquisicion, unaFechaUltimoMantenimiento, 300, "Tonelada");
            
            unaFechaAdquisicion.set(2013, 8, 3);
            unaFechaUltimoMantenimiento.set(2015, 7, 4);
            Deposito unDeposito = new Deposito("Deposito D2 Posadas", "Calle Falsa 123", unaFechaUltimoMantenimiento, unaFechaUltimoMantenimiento, 300, "Tonelada", unaBascula);        
            unaOrganizacion.getPersistencia().persistirObjeto(unDeposito);

            //Una Camara
            unaFechaAdquisicion.set(2014, 9, 4);
            unaFechaUltimoMantenimiento.set(2016, 5, 2);
            CamaraEstacionamiento unaCamara = new CamaraEstacionamiento("Camara C1 Apostoles", "Esta en Apostoles", unaFechaAdquisicion, unaFechaUltimoMantenimiento, 30, "Tonelada", 20, unaBascula);
            unaOrganizacion.getPersistencia().persistirObjeto(unaCamara);

            unaFechaAdquisicion.set(2013, 4, 21);
            unaFechaUltimoMantenimiento.set(2014, 5, 29);
            Molino unMolino = new Molino("Molino M1 Liebig", "Esta en Liebig", unaFechaAdquisicion, unaFechaUltimoMantenimiento, 40000, "Kilogramo", unaBascula);
            unaOrganizacion.getPersistencia().persistirObjeto(unMolino);
        } catch (Exception ex) {
            System.err.println("Ocurrio un error en la prueba de Insercion de tuplas de Equipamiento: "+ex.getMessage());
        }
        System.out.println("Caso de Prueba de Insercion de Equipamientos completado exitosamente.");
    }
    
    public static void probarActualizarEquipamientos(Organizacion unaOrganizacion) {
        System.out.println("Iniciando Caso de Prueba de actualizar Equipamientos.");
        try {
            
            Set<Integer> llaves = unaOrganizacion.getEquipamientos().keySet();
            Iterator recorredorLlaves = llaves.iterator();
            int unId = (int) recorredorLlaves.next();
            Equipamiento unEquipamiento = unaOrganizacion.getEquipamientos().get(unId);
            unEquipamiento.setCapacidadMaxima(1234);
            unEquipamiento.setNombre("Nombre modificado");
            
            Calendar unaFechaAdquisicion = Calendar.getInstance();
            Calendar unaFechaUltimoMantenimiento = Calendar.getInstance();
            unaFechaAdquisicion.set(1900, 7, 7);
            unaFechaUltimoMantenimiento.set(1905, 8, 8);
            
            unEquipamiento.setFechaAdquisicion(unaFechaAdquisicion);
            unEquipamiento.setFechaUltimoMantenimiento(unaFechaUltimoMantenimiento);
            
            System.out.println(unEquipamiento.getNombre());
            unaOrganizacion.getPersistencia().modificarObjeto(unEquipamiento);
        } catch (Exception ex) {
            System.err.println("Ocurrio un error en la prueba de Actualizacion de tuplas de Equipamiento: "+ex.getMessage());
        }
        System.out.println("Caso de Prueba de Actualizacion de equipamiento completado exitosamente.");
    
    
    
    }
}
