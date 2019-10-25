/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

/**
 *
 * @author usuario
 */
import LogicaDeNegocio.Proveedor;
import LogicaDeNegocio.Bascula;
import LogicaDeNegocio.*;
import LogicaDeNegocio.CamaraEstacionamiento;
import LogicaDeNegocio.Molino;
import LogicaDeNegocio.Deposito;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class Persistencia {

    private Connection conexion = null;
    
    public void iniciarSesion (String DB_URL, String usuario, String pass) throws ClassNotFoundException, SQLException{

            //STEP 2: Inicializar el driver de postgres
            Class.forName("org.postgresql.Driver");
            //STEP 3: Conectarse a la base de datos
            System.out.println("Conectandome a la BD");
            conexion = DriverManager.getConnection(DB_URL, usuario, pass);
            //Handle errors for JDBC
            //se.printStackTrace();
            //Handle errors for Class.forName
            //e.printStackTrace();
        
        
    }
    
    public void iniciarSesion (String usuario, String pass) throws ClassNotFoundException, SQLException{
        //STEP 2: Inicializar el driver de postgres
        Class.forName("org.postgresql.Driver");
        //STEP 3: Conectarse a la base de datos
        System.out.println("Conectandome a la BD");
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        conexion = DriverManager.getConnection(DB_URL, usuario, pass);
    }

    public void cerrarSesion (){
        try{
            if(this.conexion!=null)
                conexion.close();
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void recuperarOrganizacion(Organizacion unaOrganizacion) throws SQLException, ClassNotFoundException{
        this.iniciarSesion("recuperador", "recuperador");
        String sql;
        Statement stmt;
        ResultSet resultadoDeConsulta;
        
        sql = "SELECT * from public.USUARIOS order by ID;";
        stmt = this.conexion.createStatement();        
        try{
            resultadoDeConsulta = stmt.executeQuery(sql);
            while (resultadoDeConsulta.next()){
                Usuario unUsuario  = new Usuario(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Apellido") ,resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getString("DNI"),resultadoDeConsulta.getString("Rol"));
                unaOrganizacion.getUsuarios().put(unUsuario.getId(), unUsuario);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        
        sql = "SELECT * from public.PAISES order by ID;";
        stmt = this.conexion.createStatement();        
        try{
            resultadoDeConsulta = stmt.executeQuery(sql);
            while (resultadoDeConsulta.next()){
                Pais unPais = new Pais(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Estado"));
                unaOrganizacion.getPaises().put(unPais.getId(), unPais);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        
        sql = "SELECT * from public.PROVINCIAS order by ID;";
        try{
            resultadoDeConsulta = stmt.executeQuery(sql);
            while (resultadoDeConsulta.next()){
                Pais unPais = unaOrganizacion.getPaises().get(resultadoDeConsulta.getInt("IdPais"));
                Provincia unaProvincia = new Provincia(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Estado"), unPais);
                unaProvincia.setPaisAsociado(unPais);
                unPais.agregarProvincia(unaProvincia);
                unaOrganizacion.getProvincias().put(unaProvincia.getId(), unaProvincia);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        
        sql = "SELECT * from public.LOCALIDADES order by ID;";
        try{
            resultadoDeConsulta = stmt.executeQuery(sql);
            while (resultadoDeConsulta.next()){
                Provincia unaProvincia = unaOrganizacion.getProvincias().get(resultadoDeConsulta.getInt("IdProvincia"));
                Localidad unaLocalidad = new Localidad(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getString("Codigo_Postal"), unaProvincia);
                unaLocalidad.setProvinciaAsociada(unaProvincia);
                unaProvincia.agregarLocalidad(unaLocalidad);
                unaOrganizacion.getLocalidades().put(unaLocalidad.getId(), unaLocalidad);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        
        //PROVEEDORES
        
        sql = "SELECT * from public.PROVEEDORES order by ID;";
        try{
            resultadoDeConsulta = stmt.executeQuery(sql);
            while (resultadoDeConsulta.next()){
                Localidad unaLocalidad = unaOrganizacion.getLocalidades().get(resultadoDeConsulta.getInt("IdLocalidad"));
                Proveedor unProveedor = new Proveedor(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Razon_Social"), resultadoDeConsulta.getString("CUIT"), resultadoDeConsulta.getString("Estado"), unaLocalidad);
                unaLocalidad.agregarProveedor(unProveedor);
                unaOrganizacion.getProveedores().put(unProveedor.getId(), unProveedor);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        //Recuperar los Equipamientos
        
        //Basculas
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Bascula') order by ID;";
            try{
                resultadoDeConsulta = stmt.executeQuery(sql);
                while (resultadoDeConsulta.next()){
                    Bascula unaBascula = new Bascula(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getFloat("Capacidad_Maxima"),resultadoDeConsulta.getString("Unidad_De_Medida"),resultadoDeConsulta.getString("Estado"));
                    unaOrganizacion.getEquipamientos().put(unaBascula.getId(), unaBascula);
                }
            }catch (SQLException e){
                System.err.println(e.getMessage());
                //No hacer nada. Si devuelve un error es porque la tabla está vacía.
            }
        //Depositos
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Deposito') order by ID;";
            try{
                resultadoDeConsulta = stmt.executeQuery(sql);
                while (resultadoDeConsulta.next()){
                    Deposito unDeposito = new Deposito(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getFloat("Capacidad_Maxima"),resultadoDeConsulta.getString("Unidad_De_Medida"),resultadoDeConsulta.getString("Estado"));
                    Bascula unaBascula = (Bascula) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdBascula"));
                    unDeposito.setBasculaAsociada(unaBascula);
                    unaBascula.getEquipamientosAsociados().add(unDeposito);
                    unaOrganizacion.getEquipamientos().put(unDeposito.getId(), unDeposito);
                }
            }catch (SQLException e){
                System.err.println(e.getMessage());
                //No hacer nada. Si devuelve un error es porque la tabla está vacía.
            }
        //Camaras de estacionamiento
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Camara de Estacionamiento') order by ID;";
        //stmt = this.conexion.createStatement();
            try{
                resultadoDeConsulta = stmt.executeQuery(sql);
                while (resultadoDeConsulta.next()){
                    CamaraEstacionamiento unaCamaraEstacionamiento = new CamaraEstacionamiento(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getFloat("Capacidad_Maxima"),resultadoDeConsulta.getString("Unidad_De_Medida"),resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getFloat("Duracion_Maxima_Estacionamiento"));
                    Bascula unaBascula = (Bascula) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdBascula"));
                    unaCamaraEstacionamiento.setBasculaAsociada(unaBascula);
                    unaBascula.getEquipamientosAsociados().add(unaCamaraEstacionamiento);
                    unaOrganizacion.getEquipamientos().put(unaCamaraEstacionamiento.getId(), unaCamaraEstacionamiento);
                }
            }catch (SQLException e){
                System.err.println(e.getMessage());
                //No hacer nada. Si devuelve un error es porque la tabla está vacía.
            }
            
        //Molinos
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Molino') order by ID;";
            try{
                resultadoDeConsulta = stmt.executeQuery(sql);
                while (resultadoDeConsulta.next()){
                    Molino unMolino = new Molino(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getFloat("Capacidad_Maxima"),resultadoDeConsulta.getString("Unidad_De_Medida"),resultadoDeConsulta.getString("Estado"));
                    
                    Bascula unaBascula = (Bascula) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdBascula"));
                    unMolino.setBasculaAsociada(unaBascula);
                    //AQUI DEBIERA PONER TRATAMIENTO SI EL MOLINO CONTIENE ALGUN DETALLE. SEA SECTORES POR EJEMPLO. ANALIZAR RELEVAMIENTO.
                    unaBascula.getEquipamientosAsociados().add(unMolino);
                    unaOrganizacion.getEquipamientos().put(unMolino.getId(), unMolino);
                }
            }catch (SQLException e){
                System.err.println(e.getMessage());
                //No hacer nada. Si devuelve un error es porque la tabla está vacía.
            }
        //Ordenes de produccion    
        
        //Cuando agrego los lotes, los asocio a su respectiva orden de produccion y de compra.
        sql = "SELECT * from public.ORDENES_PRODUCCION order by ID;";
            try{
                resultadoDeConsulta = stmt.executeQuery(sql);
                while (resultadoDeConsulta.next()){
                    OrdenDeProduccion unaOrden = new OrdenDeProduccion(resultadoDeConsulta.getInt("ID"),resultadoDeConsulta.getDate("Fecha_Origen"),resultadoDeConsulta.getFloat("Cantidad_A_Producir"),resultadoDeConsulta.getString("Unidad_De_Medida"),resultadoDeConsulta.getDate("Fecha_Entrega"),resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getString("Descripcion"));
                    unaOrganizacion.getOrdenesProduccion().put(unaOrden.getId(), unaOrden);
                }
            }catch (SQLException e){
                System.err.println(e.getMessage());
                //No hacer nada. Si devuelve un error es porque la tabla está vacía.
            }
            
        sql = "SELECT * from public.ORDENES_COMPRA order by ID;";
            try{
                resultadoDeConsulta = stmt.executeQuery(sql);
                while (resultadoDeConsulta.next()){
                    Proveedor unProveedor = unaOrganizacion.getProveedores().get(resultadoDeConsulta.getInt("IdProveedor"));
                    OrdenDeProduccion unaOrdenProduccion = unaOrganizacion.getOrdenesProduccion().get(resultadoDeConsulta.getInt("IdOrdenProduccion"));
                    OrdenDeCompra unaOrdenCompra = new OrdenDeCompra(resultadoDeConsulta.getInt("ID"),resultadoDeConsulta.getDate("Fecha_Origen"),resultadoDeConsulta.getFloat("Cantidad"),resultadoDeConsulta.getString("Unidad_De_Medida"),resultadoDeConsulta.getFloat("Costo_De_Compra_PorUnidad"), resultadoDeConsulta.getString("Estado"), unProveedor, unaOrdenProduccion);
                    if (unProveedor != null)
                        unProveedor.agregarOrdenDeCompra(unaOrdenCompra);
                    unaOrdenProduccion.agregarOrdenDeCompra(unaOrdenCompra);
                    unaOrganizacion.getOrdenesCompra().put(unaOrdenCompra.getId(), unaOrdenCompra);
                }
            }catch (SQLException e){
                System.err.println(e.getMessage());
                //No hacer nada. Si devuelve un error es porque la tabla está vacía.
            }
            
        //lotes
        sql = "SELECT * FROM public.LOTES order by ID;";
        try{
            resultadoDeConsulta = stmt.executeQuery(sql);
            while (resultadoDeConsulta.next()){
                OrdenDeCompra unaOrdenCompra = unaOrganizacion.getOrdenesCompra().get(resultadoDeConsulta.getInt("IdOrdenCompra"));                
                Lote unLote = new Lote(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Etiqueta"),resultadoDeConsulta.getFloat("Cantidad"),  resultadoDeConsulta.getString("Tipo_Lote"), resultadoDeConsulta.getString("Unidad_De_Medida"), resultadoDeConsulta.getString("Estado"), unaOrdenCompra);
                if (unaOrdenCompra != null)
                    unaOrdenCompra.agregarLote(unLote);
                unaOrganizacion.getLotes().put(unLote.getId(), unLote);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        
        //CUANDO SE RECUPERAN MOVIMIENTOS, SE PISARA SUCESIVAMENTE EL EQUIPAMIENTO DONDE RESIDE UN LOTE CON LOS DESTINOS DE LOS MOVIMIENTOS NO ANULADOS.
        this.cerrarSesion();
    }
        public void persistirObjeto(Object unObjeto) throws SQLException{
        String sql;
        Statement stmt = this.conexion.createStatement();
        PreparedStatement ps;   //Prueba para parametrizar los campos
        ResultSet resultadoDeConsulta;
        int id;
        switch (unObjeto.getClass().getSimpleName()){
            case "Pais":
                Pais unPais = (Pais) unObjeto;
                ps = this.conexion.prepareStatement("insert into PAISES (Nombre, Estado) values (?, ?);");
                ps.setObject(1, unPais.getNombre());
                ps.setObject(2, unPais.getEstado());
                ps.execute();
                sql = "SELECT max(Id) from public.PAISES;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unPais.setId(id);
                break;
            case "Provincia":
                Provincia unaProvincia = (Provincia) unObjeto;
                ps = this.conexion.prepareStatement("insert into PROVINCIAS (Nombre, Estado, IdPais) values (?, ?, ?);");
                ps.setObject(1, unaProvincia.getNombre());
                ps.setObject(2, unaProvincia.getEstado());
                ps.setObject(3, unaProvincia.getPaisAsociado().getId());
                ps.execute();
                sql = "SELECT max(Id) from public.PROVINCIAS;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unaProvincia.setId(id);
                break;
            case "Localidad":
                Localidad unaLocalidad = (Localidad) unObjeto;
                ps = this.conexion.prepareStatement("insert into LOCALIDADES (Nombre, Estado, Codigo_Postal, IdProvincia) values (?, ?, ?, ?);");
                ps.setObject(1, unaLocalidad.getNombre());
                ps.setObject(2, unaLocalidad.getEstado());
                ps.setObject(3, unaLocalidad.getCodigoPostal());
                ps.setObject(4, unaLocalidad.getProvinciaAsociada().getId());
                ps.execute();
                sql = "SELECT max(Id) from public.LOCALIDADES;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unaLocalidad.setId(id);
                break;
            case "Proveedor":
                Proveedor unProveedor = (Proveedor) unObjeto;
                ps = this.conexion.prepareStatement("insert into public.PROVEEDORES (Razon_Social, CUIT, Estado, IdLocalidad) values (?, ?, ?, ?);");
                ps.setObject(1, unProveedor.getRazonSocial());
                ps.setObject(2, unProveedor.getCuit());
                ps.setObject(3, unProveedor.getEstado());
                ps.setObject(4, unProveedor.getLocalidad().getId());
                ps.execute();
                sql = "SELECT max(Id) from public.PROVEEDORES;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unProveedor.setId(id);
                break;
            case "Bascula":
                Bascula unaBascula = (Bascula) unObjeto;
                ps = this.conexion.prepareStatement("insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado) values (?, 'Bascula', ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unaBascula.getNombre());
                ps.setObject(2, unaBascula.getDireccion());
                ps.setObject(3, unaBascula.getFechaAdquisicion());
                ps.setObject(4, unaBascula.getFechaUltimoMantenimiento());
                ps.setObject(5, unaBascula.getCapacidadMaxima());
                ps.setObject(6, unaBascula.getUnidadDeMedida());
                ps.setObject(7, unaBascula.getEstado());
                ps.execute();
                //stmt.execute(sql);
                sql = "SELECT max(Id) from public.EQUIPAMIENTOS;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unaBascula.setId(id);
                break;
            case "Deposito":
                Deposito unDeposito = (Deposito) unObjeto;
                //sql = "insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado) values ('"+unDeposito.getNombre()+"', 'Deposito', '"+unDeposito.getDireccion()+"', '"+unDeposito.getFechaAdquisicion()+"', '"+unDeposito.getFechaUltimoMantenimiento()+"', "+unDeposito.getCapacidadMaxima()+", '"+unDeposito.getUnidadDeMedida()+"', '"+unDeposito.getEstado()+"');";
                ps = this.conexion.prepareStatement("insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, idBascula) values (?, 'Deposito', ?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unDeposito.getNombre());
                ps.setObject(2, unDeposito.getDireccion());
                ps.setObject(3, unDeposito.getFechaAdquisicion());
                ps.setObject(4, unDeposito.getFechaUltimoMantenimiento());
                ps.setObject(5, unDeposito.getCapacidadMaxima());
                ps.setObject(6, unDeposito.getUnidadDeMedida());
                ps.setObject(7, unDeposito.getEstado());
                ps.setObject(8, unDeposito.getBasculaAsociada().getId());
                ps.execute();
                //stmt.execute(sql);
                sql = "SELECT max(Id) from public.EQUIPAMIENTOS;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unDeposito.setId(id);
                break;
            case "Molino":
                Molino unMolino = (Molino) unObjeto;
                //sql = "insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado) values ('"+unMolino.getNombre()+"', 'Molino', '"+unMolino.getDireccion()+"', '"+unMolino.getFechaAdquisicion()+"', '"+unMolino.getFechaUltimoMantenimiento()+"', "+unMolino.getCapacidadMaxima()+", '"+unMolino.getUnidadDeMedida()+"', '"+unMolino.getEstado()+"');";
                ps = this.conexion.prepareStatement("insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, idBascula) values (?, 'Molino', ?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unMolino.getNombre());
                ps.setObject(2, unMolino.getDireccion());
                ps.setObject(3, unMolino.getFechaAdquisicion());
                ps.setObject(4, unMolino.getFechaUltimoMantenimiento());
                ps.setObject(5, unMolino.getCapacidadMaxima());
                ps.setObject(6, unMolino.getUnidadDeMedida());
                ps.setObject(7, unMolino.getEstado());
                ps.setObject(8, unMolino.getBasculaAsociada().getId());
                ps.execute();
                //stmt.execute(sql);
                sql = "SELECT max(Id) from public.EQUIPAMIENTOS;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unMolino.setId(id);
                break;
            case "CamaraEstacionamiento":
                CamaraEstacionamiento unaCamara = (CamaraEstacionamiento) unObjeto;
                //sql = "insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, Duracion_Maxima_Estacionamiento) values ('"+unaCamara.getNombre()+"', 'Camara de Estacionamiento', '"+unaCamara.getDireccion()+"', '"+unaCamara.getFechaAdquisicion()+"', '"+unaCamara.getFechaUltimoMantenimiento()+"', "+unaCamara.getCapacidadMaxima()+", '"+unaCamara.getUnidadDeMedida()+"', '"+unaCamara.getEstado()+"', "+unaCamara.getDuracionMaximaEstacionamiento()+");";
                ps = this.conexion.prepareStatement("insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, Duracion_Maxima_Estacionamiento, idBascula) values (?, 'Camara de Estacionamiento', ?, ?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unaCamara.getNombre());
                ps.setObject(2, unaCamara.getDireccion());
                ps.setObject(3, unaCamara.getFechaAdquisicion());
                ps.setObject(4, unaCamara.getFechaUltimoMantenimiento());
                ps.setObject(5, unaCamara.getCapacidadMaxima());
                ps.setObject(6, unaCamara.getUnidadDeMedida());
                ps.setObject(7, unaCamara.getEstado());
                ps.setObject(8, unaCamara.getDuracionMaximaEstacionamiento());                
                ps.setObject(9, unaCamara.getBasculaAsociada().getId());                
                ps.execute();
                //stmt.execute(sql);
                sql = "SELECT max(Id) from public.EQUIPAMIENTOS;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unaCamara.setId(id);
                break;   
                
            case "OrdenDeProduccion":
                OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
                ps = this.conexion.prepareStatement("insert into ORDENES_PRODUCCION (Cantidad_A_Producir, Unidad_De_Medida, Fecha_Origen, Fecha_Entrega, Descripcion,Estado) values (?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unaOrdenProduccion.getCantidadAProducir());
                ps.setObject(2, unaOrdenProduccion.getUnidadDeMedida());
                ps.setObject(3, unaOrdenProduccion.getFechaOrigen());
                ps.setObject(4, unaOrdenProduccion.getFechaEntregaProductoTerminado());
                ps.setObject(5, unaOrdenProduccion.getDescripcion());
                ps.setObject(6, unaOrdenProduccion.getEstado());
                ps.execute();
                sql = "SELECT max(Id) from public.ORDENES_PRODUCCION;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unaOrdenProduccion.setId(id);
                break;
            case "OrdenDeCompra":
                OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) unObjeto;
                
                //Puede o no tener un proveedor.
                if (unaOrdenDeCompra.poseeProveedorAsociado()){
                    ps = this.conexion.prepareStatement("insert into ORDENES_COMPRA (Fecha_Origen, Cantidad, Unidad_De_Medida, Costo_De_Compra_PorUnidad, Estado, IdOrdenProduccion, IdProveedor) values (?, ?, ?, ?, ?, ?, ?);");
                    ps.setObject(1, unaOrdenDeCompra.getFechaOrigen());
                    ps.setObject(2, unaOrdenDeCompra.getCantidadAComprar());
                    ps.setObject(3, unaOrdenDeCompra.getUnidadDeMedida());
                    ps.setObject(4, unaOrdenDeCompra.getCostoPorUnidad());
                    ps.setObject(5, unaOrdenDeCompra.getEstado());   
                    ps.setObject(6, unaOrdenDeCompra.getOrdenDeProduccionAsociada().getId());   
                    ps.setObject(7, unaOrdenDeCompra.getProveedorAsociado().getId());   
                }else{
                    ps = this.conexion.prepareStatement("insert into ORDENES_COMPRA (Fecha_Origen, Cantidad, Unidad_De_Medida, Costo_De_Compra_PorUnidad, Estado, IdOrdenProduccion) values (?, ?, ?, ?, ?, ?);");
                    ps.setObject(1, unaOrdenDeCompra.getFechaOrigen());
                    ps.setObject(2, unaOrdenDeCompra.getCantidadAComprar());
                    ps.setObject(3, unaOrdenDeCompra.getUnidadDeMedida());
                    ps.setObject(4, unaOrdenDeCompra.getCostoPorUnidad());
                    ps.setObject(5, unaOrdenDeCompra.getEstado());   
                    ps.setObject(6, unaOrdenDeCompra.getOrdenDeProduccionAsociada().getId());   
                }
                ps.execute();
                sql = "SELECT max(Id) from public.ORDENES_COMPRA;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unaOrdenDeCompra.setId(id);                
                break;                
            case "Lote":
                Lote unLote = (Lote) unObjeto;
                //Un lote siempre que se inserta sera en un equipamiento.
                ps = this.conexion.prepareStatement("insert into LOTES (Tipo_Lote, Etiqueta, Estado, Cantidad, Unidad_De_Medida, Fecha_Adquisicion, IdOrdenCompra) values (?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unLote.getTipo_Lote());
                ps.setObject(2, unLote.getEtiqueta());
                ps.setObject(3, unLote.getEstado());
                ps.setObject(4, unLote.getCantidad());
                ps.setObject(5, unLote.getUnidadDeMedida());
                ps.setObject(6, unLote.getFechaAdquisicion());//DEBERIA HACER PRIMERO ORDENES DE PRODUCCION Y DE COMPRA           
                ps.setObject(7, unLote.getOrdenDeCompraAsociada().getId());
                //ps.setObject(7, unLote.getOrdenDeProduccionAsociada().getId());
                
                //ps.setObject(9, unLote.getEquipamientoDondeReside().getId());
                ps.execute();
                sql = "SELECT max(Id) from public.LOTES;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unLote.setId(id);
                break;
            case "Usuario":
                Usuario unUsuario = (Usuario) unObjeto;
                //Un lote siempre que se inserta sera en un equipamiento.
                ps = this.conexion.prepareStatement("insert into USUARIOS (Nombre, Apellido, DNI, Rol, Estado) values (?, ?, ?, ?, ?);");
                ps.setObject(1, unUsuario.getNombre());
                ps.setObject(2, unUsuario.getApellido());
                ps.setObject(3, unUsuario.getDni());
                ps.setObject(4, unUsuario.getRol());
                ps.setObject(5, unUsuario.getEstado());
                ps.execute();
                sql = "SELECT max(Id) from public.USUARIOS;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unUsuario.setId(id);
                break;
            }         
        
        }
   
        public void modificarObjeto(Object unObjeto) throws SQLException, ExcepcionPersistencia{
            String sql;
            Statement stmt = this.conexion.createStatement();
            PreparedStatement ps;
            switch (unObjeto.getClass().getSimpleName()){
                case "Pais":
                    Pais unPais = (Pais) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.PAISES SET Nombre = ?, Estado = ? WHERE ID = ?;");
                    ps.setObject(1, unPais.getNombre());
                    ps.setObject(2, unPais.getEstado());
                    ps.setObject(3, unPais.getId());
                    ps.execute();
                    break;
                    
                case "Provincia":
                    Provincia unaProvincia = (Provincia) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.PROVINCIAS SET Nombre = ?, Estado = ?, IdPais = ? WHERE ID = ?;");
                    ps.setObject(1, unaProvincia.getNombre());
                    ps.setObject(2, unaProvincia.getEstado());//ESTADO
                    ps.setObject(3, unaProvincia.getPaisAsociado().getId());
                    ps.setObject(4, unaProvincia.getId());
                    ps.execute();
                    break;
                
                case "Localidad":
                    Localidad unaLocalidad = (Localidad) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.LOCALIDADES SET Nombre = ?, Codigo_Postal = ?, Estado = ?, IdProvincia = ? WHERE ID = ?;");
                    ps.setObject(1, unaLocalidad.getNombre());
                    ps.setObject(2, unaLocalidad.getCodigoPostal());
                    ps.setObject(3, unaLocalidad.getEstado());
                    ps.setObject(4, unaLocalidad.getProvinciaAsociada().getId());
                    ps.setObject(5, unaLocalidad.getId());
                    ps.execute();
                    break;
                
                case "Proveedor":
                    Proveedor unProveedor = (Proveedor) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.PROVEEDORES SET Razon_Social = ?, CUIT = ?, Estado = ?, IdLocalidad = ? WHERE ID = ?;");
                    ps.setObject(1, unProveedor.getRazonSocial());
                    ps.setObject(2, unProveedor.getCuit());
                    ps.setObject(3, unProveedor.getEstado());
                    ps.setObject(4, unProveedor.getLocalidad().getId());
                    ps.setObject(5, unProveedor.getId());
                    ps.execute();
                    break;
                case "Bascula":
                    Bascula unaBascula = (Bascula) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.EQUIPAMIENTOS SET Nombre = ?, Direccion = ?, Fecha_Adquisicion = ?, Fecha_Ultimo_Mantenimiento = ?, Capacidad_Maxima = ?, Unidad_De_Medida = ?, Estado = ? WHERE ID = ?;");
                    ps.setObject(1, unaBascula.getNombre());
                    ps.setObject(2, unaBascula.getDireccion());
                    ps.setObject(3, unaBascula.getFechaAdquisicion());
                    ps.setObject(4, unaBascula.getFechaUltimoMantenimiento());
                    ps.setObject(5, unaBascula.getCapacidadMaxima());
                    ps.setObject(6, unaBascula.getUnidadDeMedida());
                    ps.setObject(7, unaBascula.getEstado());
                    ps.setObject(8, unaBascula.getId());
                    ps.execute();
//                  sql = "UPDATE public.EQUIPAMIENTOS SET Nombre = '"+unaBascula.getNombre()+"', Direccion = '"+unaBascula.getDireccion()+"', Fecha_Adquisicion = '"+unaBascula.getFechaAdquisicion()+"', Fecha_Ultimo_Mantenimiento = '"+unaBascula.getFechaUltimoMantenimiento()+"', Capacidad_Maxima = "+unaBascula.getCapacidadMaxima()+", Unidad_De_Medida = '"+unaBascula.getUnidadDeMedida()+"', Estado = '"+unaBascula.getEstado()+"' WHERE ID = "+unaBascula.getId()+";";
                    
                    break;
                case "Deposito":
                    Deposito unDeposito = (Deposito) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.EQUIPAMIENTOS SET Nombre = ?, Direccion = ?, Fecha_Adquisicion = ?, Fecha_Ultimo_Mantenimiento = ?, Capacidad_Maxima = ?, Unidad_De_Medida = ?, Estado = ?, idBascula = ? WHERE ID = ?;");
                    ps.setObject(1, unDeposito.getNombre());
                    ps.setObject(2, unDeposito.getDireccion());
                    ps.setObject(3, unDeposito.getFechaAdquisicion());
                    ps.setObject(4, unDeposito.getFechaUltimoMantenimiento());
                    ps.setObject(5, unDeposito.getCapacidadMaxima());
                    ps.setObject(6, unDeposito.getUnidadDeMedida());
                    ps.setObject(7, unDeposito.getEstado());
                    ps.setObject(8, unDeposito.getBasculaAsociada().getId());
                    ps.setObject(9, unDeposito.getId());
                    ps.execute();
                    //sql = "UPDATE public.EQUIPAMIENTOS SET Nombre = '"+unDeposito.getNombre()+"', Direccion = '"+unDeposito.getDireccion()+"', Fecha_Adquisicion = '"+unDeposito.getFechaAdquisicion()+"', Fecha_Ultimo_Mantenimiento = '"+unDeposito.getFechaUltimoMantenimiento()+"', Capacidad_Maxima = "+unDeposito.getCapacidadMaxima()+", Unidad_De_Medida = '"+unDeposito.getUnidadDeMedida()+"', Estado = '"+unDeposito.getEstado()+"' WHERE ID = "+unDeposito.getId()+";";
                    
                    break;
                case "Molino":
                    ps = this.conexion.prepareStatement("UPDATE public.EQUIPAMIENTOS SET Nombre = ?, Direccion = ?, Fecha_Adquisicion = ?, Fecha_Ultimo_Mantenimiento = ?, Capacidad_Maxima = ?, Unidad_De_Medida = ?, Estado = ?, idBascula = ? WHERE ID = ?;");
                    Molino unMolino = (Molino) unObjeto;
                    ps.setObject(1, unMolino.getNombre());
                    ps.setObject(2, unMolino.getDireccion());
                    ps.setObject(3, unMolino.getFechaAdquisicion());
                    ps.setObject(4, unMolino.getFechaUltimoMantenimiento());
                    ps.setObject(5, unMolino.getCapacidadMaxima());
                    ps.setObject(6, unMolino.getUnidadDeMedida());
                    ps.setObject(7, unMolino.getEstado());
                    ps.setObject(8, unMolino.getBasculaAsociada().getId());
                    ps.setObject(9, unMolino.getId());
                    ps.execute();

                    //sql = "UPDATE public.EQUIPAMIENTOS SET Nombre = '"+unMolino.getNombre()+"', Direccion = '"+unMolino.getDireccion()+"', Fecha_Adquisicion = '"+unMolino.getFechaAdquisicion()+"', Fecha_Ultimo_Mantenimiento = '"+unMolino.getFechaUltimoMantenimiento()+"', Capacidad_Maxima = "+unMolino.getCapacidadMaxima()+", Unidad_De_Medida = '"+unMolino.getUnidadDeMedida()+"', Estado = '"+unMolino.getEstado()+"' WHERE ID = "+unMolino.getId()+";";

                    break;
                case "CamaraEstacionamiento":
                    CamaraEstacionamiento unaCamara = (CamaraEstacionamiento) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.EQUIPAMIENTOS SET Nombre = ?, Direccion = ?, Fecha_Adquisicion = ?, Fecha_Ultimo_Mantenimiento = ?, Capacidad_Maxima = ?, Unidad_De_Medida = ?, Estado = ?, Duracion_Maxima_Estacionamiento = ?,idBascula = ? WHERE ID = ?;");
                    ps.setObject(1, unaCamara.getNombre());
                    ps.setObject(2, unaCamara.getDireccion());
                    ps.setObject(3, unaCamara.getFechaAdquisicion());
                    ps.setObject(4, unaCamara.getFechaUltimoMantenimiento());
                    ps.setObject(5, unaCamara.getCapacidadMaxima());
                    ps.setObject(6, unaCamara.getUnidadDeMedida());
                    ps.setObject(7, unaCamara.getEstado());
                    ps.setObject(8, unaCamara.getDuracionMaximaEstacionamiento());
                    ps.setObject(9, unaCamara.getBasculaAsociada().getId());
                    ps.setObject(10, unaCamara.getId());
                    ps.execute();
                    //sql = "UPDATE public.EQUIPAMIENTOS SET Nombre = '"+unaCamara.getNombre()+"', Direccion = '"+unaCamara.getDireccion()+"', Fecha_Adquisicion = '"+unaCamara.getFechaAdquisicion()+"', Fecha_Ultimo_Mantenimiento = '"+unaCamara.getFechaUltimoMantenimiento()+"', Capacidad_Maxima = "+unaCamara.getCapacidadMaxima()+", Unidad_De_Medida = '"+unaCamara.getUnidadDeMedida()+"', Estado = '"+unaCamara.getEstado()+"', Duracion_Maxima_Estacionamiento = "+unaCamara.getDuracionMaximaEstacionamiento()+" WHERE ID = "+unaCamara.getId()+";";
                    
                    break;                 
                case "OrdenDeProduccion":
                    OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.ORDENES_PRODUCCION SET Cantidad_A_Producir = ?, Unidad_De_Medida = ?, Fecha_Origen = ?, Fecha_Entrega = ?, Descripcion = ?, Estado = ? WHERE ID = ?;");
                    ps.setObject(1, unaOrdenProduccion.getCantidadAProducir());
                    ps.setObject(2, unaOrdenProduccion.getUnidadDeMedida());
                    ps.setObject(3, unaOrdenProduccion.getFechaOrigen());
                    ps.setObject(4, unaOrdenProduccion.getFechaEntregaProductoTerminado());
                    ps.setObject(5, unaOrdenProduccion.getDescripcion());
                    ps.setObject(6, unaOrdenProduccion.getEstado());
                    ps.setObject(7, unaOrdenProduccion.getId());
                    ps.execute();
                    break;
                    
                case "OrdenDeCompra":
                    OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) unObjeto;
                    if (unaOrdenDeCompra.poseeProveedorAsociado()){
                        ps = this.conexion.prepareStatement("UPDATE public.ORDENES_COMPRA SET Fecha_Origen = ?, Cantidad = ?, Unidad_De_Medida = ?, Costo_De_Compra_PorUnidad = ?, Estado = ?, IdOrdenProduccion = ?, IdProveedor = ? WHERE ID = ?;");
                        ps.setObject(1, unaOrdenDeCompra.getFechaOrigen());
                        ps.setObject(2, unaOrdenDeCompra.getCantidadAComprar());
                        ps.setObject(3, unaOrdenDeCompra.getUnidadDeMedida());
                        ps.setObject(4, unaOrdenDeCompra.getCostoPorUnidad());
                        ps.setObject(5, unaOrdenDeCompra.getEstado());   
                        ps.setObject(6, unaOrdenDeCompra.getOrdenDeProduccionAsociada().getId());   
                        ps.setObject(7, unaOrdenDeCompra.getProveedorAsociado().getId()); 
                        ps.setObject(8, unaOrdenDeCompra.getId()); 
                    }else{
                        ps = this.conexion.prepareStatement("UPDATE public.ORDENES_COMPRA SET Fecha_Origen = ?, Cantidad = ?, Unidad_De_Medida = ?, Costo_De_Compra_PorUnidad = ?, Estado = ?, IdOrdenProduccion = ?, IdProveedor = NULL WHERE ID = ?;");
                        ps.setObject(1, unaOrdenDeCompra.getFechaOrigen());
                        ps.setObject(2, unaOrdenDeCompra.getCantidadAComprar());
                        ps.setObject(3, unaOrdenDeCompra.getUnidadDeMedida());
                        ps.setObject(4, unaOrdenDeCompra.getCostoPorUnidad());
                        ps.setObject(5, unaOrdenDeCompra.getEstado());   
                        ps.setObject(6, unaOrdenDeCompra.getOrdenDeProduccionAsociada().getId());   
                        ps.setObject(7, unaOrdenDeCompra.getId()); 
                    }            
                    ps.execute();
                    break;
                    
                case "Lote":
                    Lote unLote = (Lote) unObjeto;
                    
                    //Puede o no poseer un equipamiento
                    if (unLote.poseeEquipamientoAsociado()){
                        ps = this.conexion.prepareStatement("UPDATE public.LOTES SET Tipo_Lote = ?, Etiqueta = ?, Estado = ?, Cantidad = ?, Unidad_De_Medida = ?, Fecha_Adquisicion = ?, IdOrdenProduccion = ?, IdOrdenCompra = ?, IdEquipamiento = ? WHERE ID = ?;");

                        ps.setObject(1, unLote.getTipo_Lote());
                        ps.setObject(2, unLote.getEtiqueta());
                        ps.setObject(3, unLote.getEstado());
                        ps.setObject(4, unLote.getCantidad());
                        ps.setObject(5, unLote.getUnidadDeMedida());
                        ps.setObject(6, unLote.getFechaAdquisicion());
                        ps.setObject(7, unLote.getOrdenDeProduccionAsociada().getId());
                        ps.setObject(8, unLote.getOrdenDeCompraAsociada().getId());
                        ps.setObject(9, unLote.getEquipamientoDondeReside().getId());
                        ps.setObject(10, unLote.getId());                        
                    }else{
                        ps = this.conexion.prepareStatement("UPDATE public.LOTES SET Tipo_Lote = ?, Etiqueta = ?, Estado = ?, Cantidad = ?, Unidad_De_Medida = ?, Fecha_Adquisicion = ?, IdOrdenProduccion = ?, IdOrdenCompra = ? WHERE ID = ?;");

                        ps.setObject(1, unLote.getTipo_Lote());
                        ps.setObject(2, unLote.getEtiqueta());
                        ps.setObject(3, unLote.getEstado());
                        ps.setObject(4, unLote.getCantidad());
                        ps.setObject(5, unLote.getUnidadDeMedida());
                        ps.setObject(6, unLote.getFechaAdquisicion());
                        ps.setObject(7, unLote.getOrdenDeProduccionAsociada().getId());
                        ps.setObject(8, unLote.getOrdenDeCompraAsociada().getId());
                        ps.setObject(9, unLote.getId());                        
                    }
                    ps.execute();
                    break;
                case "Usuario":
                    Usuario unUsuario = (Usuario) unObjeto;
                    ps = this.conexion.prepareStatement("UPDATE public.USUARIOS SET Nombre = ?, Apellido = ?, DNI = ?, Rol = ?, Estado = ? WHERE ID = ?;");
                    ps.setObject(1, unUsuario.getNombre());
                    ps.setObject(2, unUsuario.getApellido());
                    ps.setObject(3, unUsuario.getDni());
                    ps.setObject(4, unUsuario.getRol());
                    ps.setObject(5, unUsuario.getEstado());
                    ps.setObject(6, unUsuario.getId());

                        
                       
                    ps.execute();
                    break;                    
                    
                default:
                    throw new ExcepcionPersistencia("La clase "+unObjeto.getClass().getSimpleName()+" no es valida para realizar una modificación.");
            }
        }
}
