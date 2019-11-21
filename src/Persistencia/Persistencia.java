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
import LogicaDeNegocio.CriterioAnalisisLaboratorio;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class Persistencia {

    private Connection conexion = null;
    private Organizacion organizacionAsociada;

    public void iniciarSesion(String DB_URL, String usuario, String pass) throws ClassNotFoundException, SQLException {

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

    public void iniciarSesion(String usuario, String pass) throws ClassNotFoundException, SQLException {
        //STEP 2: Inicializar el driver de postgres
        Class.forName("org.postgresql.Driver");
        //STEP 3: Conectarse a la base de datos
        System.out.println("Conectandome a la BD");
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        conexion = DriverManager.getConnection(DB_URL, usuario, pass);
    }

    public void iniciarSesion(String usuario, String pass, Organizacion unaOrganizacion) throws ClassNotFoundException, SQLException, ExcepcionCargaParametros {
        //STEP 2: Inicializar el driver de postgres
        Class.forName("org.postgresql.Driver");
        //STEP 3: Conectarse a la base de datos
        System.out.println("Conectandome a la BD");
        String DB_URL = "jdbc:postgresql://localhost/postgres";
        conexion = DriverManager.getConnection(DB_URL, usuario, pass);
        Usuario unUsuario = unaOrganizacion.recuperarUsuario(usuario);
        if (usuario.equals("postgres")) {
            unUsuario = new Usuario(0, "Admin", "", "Activo", "----", "GerenteAreaProduccion", "Admin");
        }
        if (unUsuario == null) {
            throw new ExcepcionCargaParametros("El usuario no se encuentra registrado.");
        }
        unaOrganizacion.setUsuarioActivo(unUsuario);
    }

    public void cerrarSesion() {
        try {
            if (this.conexion != null) {
                conexion.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }//end finally try
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void recuperarOrganizacion(Organizacion unaOrganizacion) throws SQLException, ClassNotFoundException {
        this.iniciarSesion("recuperador", "recuperador");
        String sql;
        Statement consulta;
        Statement subConsulta;
        ResultSet resultadoDeConsulta;
        ResultSet resultadoDeSubConsulta;

        
        //USUARIOS
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.USUARIOS order by ID;";
        consulta = this.conexion.createStatement();
        subConsulta = this.conexion.createStatement();//Utilizada para las sub consultas de detalles de transformacion

        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Usuario unUsuario = new Usuario(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Apellido"), resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getString("DNI"), resultadoDeConsulta.getString("Rol"), resultadoDeConsulta.getString("Nombre_Usuario"));
                unaOrganizacion.getUsuarios().put(unUsuario.getId(), unUsuario);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //Pais, provincia, localidad
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.PAISES order by ID;";
        consulta = this.conexion.createStatement();
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Pais unPais = new Pais(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Estado"));
                unaOrganizacion.getPaises().put(unPais.getId(), unPais);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }

        sql = "SELECT * from public.PROVINCIAS order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Pais unPais = unaOrganizacion.getPaises().get(resultadoDeConsulta.getInt("IdPais"));
                Provincia unaProvincia = new Provincia(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Estado"), unPais);
                unaProvincia.setPaisAsociado(unPais);
                unPais.agregarProvincia(unaProvincia);
                unaOrganizacion.getProvincias().put(unaProvincia.getId(), unaProvincia);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }

        sql = "SELECT * from public.LOCALIDADES order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Provincia unaProvincia = unaOrganizacion.getProvincias().get(resultadoDeConsulta.getInt("IdProvincia"));
                Localidad unaLocalidad = new Localidad(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getString("Codigo_Postal"), unaProvincia);
                unaLocalidad.setProvinciaAsociada(unaProvincia);
                unaProvincia.agregarLocalidad(unaLocalidad);
                unaOrganizacion.getLocalidades().put(unaLocalidad.getId(), unaLocalidad);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>     
        //PROVEEDORES
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.PROVEEDORES order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Localidad unaLocalidad = unaOrganizacion.getLocalidades().get(resultadoDeConsulta.getInt("IdLocalidad"));
                Proveedor unProveedor = new Proveedor(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Razon_Social"), resultadoDeConsulta.getString("CUIT"), resultadoDeConsulta.getString("Estado"), unaLocalidad);
                unaLocalidad.agregarProveedor(unProveedor);
                unaOrganizacion.getProveedores().put(unProveedor.getId(), unProveedor);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //Recuperar los Equipamientos
        // <editor-fold defaultstate="collapsed">
        //Basculas
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Bascula') order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Bascula unaBascula = new Bascula(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getFloat("Capacidad_Maxima"), resultadoDeConsulta.getString("Unidad_De_Medida"), resultadoDeConsulta.getString("Estado"));
                unaOrganizacion.getEquipamientos().put(unaBascula.getId(), unaBascula);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //Depositos
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Deposito') order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Deposito unDeposito = new Deposito(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getFloat("Capacidad_Maxima"), resultadoDeConsulta.getString("Unidad_De_Medida"), resultadoDeConsulta.getString("Estado"));
                Bascula unaBascula = (Bascula) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdBascula"));
                unDeposito.setBasculaAsociada(unaBascula);
                unaBascula.getEquipamientosAsociados().add(unDeposito);
                unaOrganizacion.getEquipamientos().put(unDeposito.getId(), unDeposito);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //Camaras de estacionamiento
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Camara de Estacionamiento') order by ID;";
        //stmt = this.conexion.createStatement();
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                CamaraEstacionamiento unaCamaraEstacionamiento = new CamaraEstacionamiento(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getFloat("Capacidad_Maxima"), resultadoDeConsulta.getString("Unidad_De_Medida"), resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getFloat("Duracion_Maxima_Estacionamiento"));
                Bascula unaBascula = (Bascula) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdBascula"));
                unaCamaraEstacionamiento.setBasculaAsociada(unaBascula);
                unaBascula.getEquipamientosAsociados().add(unaCamaraEstacionamiento);
                unaOrganizacion.getEquipamientos().put(unaCamaraEstacionamiento.getId(), unaCamaraEstacionamiento);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //Molinos
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Molino') order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Molino unMolino = new Molino(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getFloat("Capacidad_Maxima"), resultadoDeConsulta.getString("Unidad_De_Medida"), resultadoDeConsulta.getString("Estado"));

                Bascula unaBascula = (Bascula) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdBascula"));
                unMolino.setBasculaAsociada(unaBascula);
                //AQUI DEBIERA PONER TRATAMIENTO SI EL MOLINO CONTIENE ALGUN DETALLE. SEA SECTORES POR EJEMPLO. ANALIZAR RELEVAMIENTO.
                unaBascula.getEquipamientosAsociados().add(unMolino);
                unaOrganizacion.getEquipamientos().put(unMolino.getId(), unMolino);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //Laboratorios
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.EQUIPAMIENTOS where (EQUIPAMIENTOS.Tipo_Equipamiento = 'Laboratorio') order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Laboratorio unLaboratorio = new Laboratorio(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Direccion"), resultadoDeConsulta.getDate("Fecha_Adquisicion"), resultadoDeConsulta.getDate("Fecha_Ultimo_Mantenimiento"), resultadoDeConsulta.getString("Estado"));
                unaOrganizacion.getEquipamientos().put(unLaboratorio.getId(), unLaboratorio);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
// </editor-fold>        
        //Ordenes de produccion    
        // <editor-fold defaultstate="collapsed">
        //Cuando agrego los lotes, los asocio a su respectiva orden de produccion y de compra.
        sql = "SELECT * from public.ORDENES_PRODUCCION order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {

                Usuario unUsuario = unaOrganizacion.getUsuarios().get(resultadoDeConsulta.getInt("IdUsuario"));
                OrdenDeProduccion unaOrden = new OrdenDeProduccion(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getDate("Fecha_Origen"), resultadoDeConsulta.getFloat("Cantidad_A_Producir"), resultadoDeConsulta.getString("Unidad_De_Medida"), resultadoDeConsulta.getDate("Fecha_Entrega"), resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getString("Descripcion"), resultadoDeConsulta.getInt("ID_Evento"), unUsuario);
                unaOrganizacion.getOrdenesProduccion().put(unaOrden.getId(), unaOrden);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //ORDENES DE COMPRA
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * from public.ORDENES_COMPRA order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Proveedor unProveedor = unaOrganizacion.getProveedores().get(resultadoDeConsulta.getInt("IdProveedor"));
                OrdenDeProduccion unaOrdenProduccion = unaOrganizacion.getOrdenesProduccion().get(resultadoDeConsulta.getInt("IdOrdenProduccion"));
                Usuario unUsuario = unaOrganizacion.getUsuarios().get(resultadoDeConsulta.getInt("IdUsuario"));
                OrdenDeCompra unaOrdenCompra = new OrdenDeCompra(resultadoDeConsulta.getInt("ID"), unUsuario, resultadoDeConsulta.getDate("Fecha_Origen"), resultadoDeConsulta.getFloat("Cantidad"), resultadoDeConsulta.getString("Unidad_De_Medida"), resultadoDeConsulta.getFloat("Costo_De_Compra_PorUnidad"), resultadoDeConsulta.getString("Estado"), unProveedor, unaOrdenProduccion, resultadoDeConsulta.getInt("ID_Evento"));
                if (unProveedor != null) {
                    unProveedor.agregarOrdenDeCompra(unaOrdenCompra);
                }
                unaOrdenProduccion.agregarEvento(unaOrdenCompra);
                unaOrganizacion.getOrdenesCompra().put(unaOrdenCompra.getId(), unaOrdenCompra);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //lotes
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * FROM public.LOTES order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                OrdenDeCompra unaOrdenCompra = unaOrganizacion.getOrdenesCompra().get(resultadoDeConsulta.getInt("IdOrdenCompra"));
                Lote unLote = new Lote(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Etiqueta"), resultadoDeConsulta.getFloat("Cantidad"), resultadoDeConsulta.getString("Tipo_Lote"), resultadoDeConsulta.getString("Unidad_De_Medida"), resultadoDeConsulta.getString("Estado"), unaOrdenCompra, resultadoDeConsulta.getDate("Fecha_Adquisicion"));
                Equipamiento unEquipamientoDondeReside = unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdEquipamiento"));
                if (unaOrdenCompra != null) {
                    unaOrdenCompra.agregarLote(unLote);
                }
                unaOrganizacion.getLotes().put(unLote.getId(), unLote);
                if (unEquipamientoDondeReside != null) {
                    unEquipamientoDondeReside.agregarLote(unLote);
                    unLote.setEquipamientoDondeReside(unEquipamientoDondeReside);
                }

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //MOVIMIENTOS
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * FROM public.MOVIMIENTOS_INTERNOS_MP order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Lote loteImplicado = unaOrganizacion.getLotes().get(resultadoDeConsulta.getInt("IdLote"));
                Bascula basculaAsociada = (Bascula) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdBascula"));
                Equipamiento equipamientoOrigen = unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdEquipamientoOrigen"));
                Equipamiento equipamientoDestino = unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdEquipamientoDestino"));
                Usuario unUsuario = unaOrganizacion.getUsuarios().get(resultadoDeConsulta.getInt("IdUsuario"));
                Proveedor unProveedor = unaOrganizacion.getProveedores().get(resultadoDeConsulta.getInt("IdProveedorTransporte"));
                //OrdenDeCompra unaOrdenDeCompra = unaOrganizacion.getOrdenesCompra().get(resultadoDeConsulta.getInt("IdProveedorTransporte"));
                MovimientoInternoMateriaPrima unMovimiento = new MovimientoInternoMateriaPrima(resultadoDeConsulta.getInt("ID"), unUsuario, resultadoDeConsulta.getDate("Fecha_Origen"), resultadoDeConsulta.getTime("Hora_Entrada"), resultadoDeConsulta.getTime("Hora_Salida"), resultadoDeConsulta.getString("Tipo_Unidad_Transporte"), resultadoDeConsulta.getInt("cantidad_Unidades"), resultadoDeConsulta.getString("Unidad_De_Medida_Peso"), resultadoDeConsulta.getFloat("Peso_Entrada"), resultadoDeConsulta.getFloat("Peso_Salida"), resultadoDeConsulta.getString("N_Hoja_Ruta"), resultadoDeConsulta.getString("N_Remito"), resultadoDeConsulta.getString("N_Precinto"), resultadoDeConsulta.getString("Nombre_Conductor"), resultadoDeConsulta.getString("Patente_Chasis"), resultadoDeConsulta.getString("Patente_Acoplado"), resultadoDeConsulta.getString("Estado"), loteImplicado, equipamientoOrigen, equipamientoDestino, basculaAsociada, unProveedor, resultadoDeConsulta.getInt("ID_Evento"));
                unaOrganizacion.getMovimientos().put(unMovimiento.getId(), unMovimiento);

                loteImplicado.agregarMovimiento(unMovimiento);
                loteImplicado.getOrdenDeProduccionAsociada().agregarEvento(unMovimiento);
                if (!unMovimiento.poseeEquipamientoOrigen()) {
                    unaOrganizacion.getMovimientosDeIngreso().put(unMovimiento.getId(), unMovimiento);
                }

                if (unMovimiento.estaRegular()) {
                    loteImplicado.asignarUltimoMovimiento(unMovimiento);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //ESTACIONAMIENTOS
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * FROM public.TRANSFORMACIONES where (Tipo_Transformacion = 'Estacionamiento') order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {

                Usuario unUsuario = unaOrganizacion.getUsuarios().get(resultadoDeConsulta.getInt("IdUsuario"));
                CamaraEstacionamiento unaCamaraDeEstacionamiento = (CamaraEstacionamiento) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdEquipamiento"));
                Estacionamiento unEstacionamiento = new Estacionamiento(resultadoDeConsulta.getInt("ID"), unUsuario, resultadoDeConsulta.getDate("Fecha_Origen"), resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getInt("ID_Evento"), unaCamaraDeEstacionamiento, resultadoDeConsulta.getDate("Fecha_extraccion"));
                //HASTA ACA LLEGUE

                int idTransformacion = resultadoDeConsulta.getInt("ID");

                sql = "SELECT * FROM public.DETALLES_TRANSFORMACION where (IdTransformacion = " + idTransformacion + ") order by ID;";
                resultadoDeSubConsulta = subConsulta.executeQuery(sql);

                while (resultadoDeSubConsulta.next()) {
                    int unIdDeLote = resultadoDeSubConsulta.getInt("IdLote");
                    Lote unLote = unaOrganizacion.getLotes().get(unIdDeLote);
                    unEstacionamiento.agregarLote(unLote);
                    unLote.agregarEstacionamiento(unEstacionamiento);
                    unLote.getOrdenDeProduccionAsociada().agregarEvento(unEstacionamiento);
                }
                unaOrganizacion.getTransformaciones().put(unEstacionamiento.getId(), unEstacionamiento);
                unaOrganizacion.getEstacionamientos().put(unEstacionamiento.getId(), unEstacionamiento);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //Criterios de analisis de laboratorio
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * FROM public.CRITERIOS_ANALISIS_LABORATORIO order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                String puntosNegros = resultadoDeConsulta.getString("Puntos_Negros");
                String torrada = resultadoDeConsulta.getString("Torrada");
                String color = resultadoDeConsulta.getString("Color");
                String aroma = resultadoDeConsulta.getString("Aroma");
                String tacto = resultadoDeConsulta.getString("Tacto");
                String degustacion = resultadoDeConsulta.getString("Degustacion");
                CriterioAnalisisLaboratorio unCriterio = new CriterioAnalisisLaboratorio(resultadoDeConsulta.getInt("ID"), resultadoDeConsulta.getString("Nombre"), resultadoDeConsulta.getString("Descripcion"), resultadoDeConsulta.getString("Estado"), resultadoDeConsulta.getDate("Fecha_Origen"),
                        resultadoDeConsulta.getFloat("Porcentaje_Semilla_Limite_Inferior"), resultadoDeConsulta.getFloat("Porcentaje_Semilla_Limite_Superior"),
                        resultadoDeConsulta.getFloat("Porcentaje_Palo_Limite_Inferior"), resultadoDeConsulta.getFloat("Porcentaje_Palo_Limite_Superior"),
                        resultadoDeConsulta.getFloat("Porcentaje_Polvo_Limite_Inferior"), resultadoDeConsulta.getFloat("Porcentaje_Polvo_Limite_Superior"),
                        resultadoDeConsulta.getFloat("Porcentaje_Hoja_Limite_Inferior"), resultadoDeConsulta.getFloat("Porcentaje_Hoja_Limite_Superior"),
                        resultadoDeConsulta.getFloat("Porcentaje_Humedad_Limite_Inferior"), resultadoDeConsulta.getFloat("Porcentaje_Humedad_Limite_Superior"),
                        resultadoDeConsulta.getString("Tipo_Lote"),
                        puntosNegros, torrada, color, aroma, tacto, degustacion);

                sql = "SELECT * FROM public.ORDEN_PRODUCCION_DETALLES_CRITERIOS_ANALISIS where (IdCriterioAnalisisLaboratorio = " + unCriterio.getId() + ") order by ID;";
                resultadoDeSubConsulta = subConsulta.executeQuery(sql);
                while (resultadoDeSubConsulta.next()) {
                    int unIdOrdenDeProduccion = resultadoDeSubConsulta.getInt("IdOrdenProduccion");
                    OrdenDeProduccion unaOrdenDeProduccion = unaOrganizacion.getOrdenesProduccion().get(unIdOrdenDeProduccion);
                    unaOrdenDeProduccion.agregarCriterioLaboratorio(unCriterio);
                    unCriterio.agregarOrdenDeProduccion(unaOrdenDeProduccion);
                }
                unaOrganizacion.getCriteriosAnalisisLaboratorio().put(unCriterio.getId(), unCriterio);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>
        //Analisis de laboratorio
        // <editor-fold defaultstate="collapsed">
        sql = "SELECT * FROM public.ANALISIS_LABORATORIO order by ID;";
        try {
            resultadoDeConsulta = consulta.executeQuery(sql);
            while (resultadoDeConsulta.next()) {
                Usuario unUsuario = unaOrganizacion.getUsuarios().get(resultadoDeConsulta.getInt("IdUsuario"));
                Laboratorio unLaboratorio = (Laboratorio) unaOrganizacion.getEquipamientos().get(resultadoDeConsulta.getInt("IdLaboratorio"));
                Lote unLote = null;
                OrdenDeProduccion unaOrdenDeProduccion = null;
                OrdenDeCompra unaOrdenDeCompra = null;
                if (resultadoDeConsulta.getInt("IdLote") != 0) {
                    unLote = (Lote) unaOrganizacion.getLotes().get(resultadoDeConsulta.getInt("IdLote"));
                    unaOrdenDeProduccion = unLote.getOrdenDeProduccionAsociada();
                } else {
                    if (resultadoDeConsulta.getInt("IdOrdenDeCompra") != 0){
                        unaOrdenDeCompra = (OrdenDeCompra) unaOrganizacion.getOrdenesCompra().get(resultadoDeConsulta.getInt("IdOrdenDeCompra"));
                        unaOrdenDeProduccion = unaOrdenDeCompra.getOrdenDeProduccionAsociada();
                    }
                }
                CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) unaOrganizacion.getCriteriosAnalisisLaboratorio().get(resultadoDeConsulta.getInt("IdCriterio"));

                String puntosNegros = resultadoDeConsulta.getString("Puntos_Negros");
                String torrada = resultadoDeConsulta.getString("Torrada");
                String color = resultadoDeConsulta.getString("Color");
                String aroma = resultadoDeConsulta.getString("Aroma");
                String tacto = resultadoDeConsulta.getString("Tacto");
                String degustacion = resultadoDeConsulta.getString("Degustacion");
                String estado = resultadoDeConsulta.getString("Estado");
                AnalisisLaboratorio unAnalisis = new AnalisisLaboratorio(resultadoDeConsulta.getInt("ID"), estado,
                        puntosNegros, torrada, color, aroma, tacto, degustacion,
                        resultadoDeConsulta.getFloat("Porcentaje_Palo"),
                        resultadoDeConsulta.getFloat("Porcentaje_Polvo"),
                        resultadoDeConsulta.getFloat("Porcentaje_Semilla"),
                        resultadoDeConsulta.getFloat("Porcentaje_Hoja"),
                        resultadoDeConsulta.getFloat("Porcentaje_Humedad"),
                        unLote, unaOrdenDeCompra, unLaboratorio, unCriterio,
                        resultadoDeConsulta.getInt("ID_Evento"), unUsuario,
                        resultadoDeConsulta.getDate("Fecha_Origen"),
                        resultadoDeConsulta.getString("Comentario"), resultadoDeConsulta.getString("Conclusion"));
                if (unLote != null) {
                    unLote.agregarAnalisis(unAnalisis);
                }
                if (unaOrdenDeCompra != null) {
                    unaOrdenDeCompra.agregarAnalisisDeLaboratorio(unAnalisis);
                }
                unaOrdenDeProduccion.agregarEvento(unAnalisis);
                unLaboratorio.agregarAnalisis(unAnalisis);
                unCriterio.agregarAnalisis(unAnalisis);
                unCriterio.agregarOrdenDeProduccion(unaOrdenDeProduccion);
                unaOrganizacion.getAnalisisLaboratorio().put(unAnalisis.getId(), unAnalisis);

                //BORRAME
                System.out.println(unAnalisis.getCriterioAsociado().generarDetalleDeResultadoDeAnalisis(unAnalisis));
            }
        
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        // </editor-fold>

        this.cerrarSesion();
    }

    public void persistirObjeto(Object unObjeto) throws SQLException {
        String sql;
        Statement stmt = this.conexion.createStatement();
        PreparedStatement ps = null;   //Prueba para parametrizar los campos
        ResultSet resultadoDeConsulta;
        int id, idEvento;
        switch (unObjeto.getClass().getSimpleName()) {
            //Pais, provincia y localidad
            // <editor-fold defaultstate="collapsed">
            case "Pais":
                Pais unPais = (Pais) unObjeto;
                ps = this.conexion.prepareStatement("insert into public.PAISES (Nombre, Estado) values (?, ?);");
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
                ps = this.conexion.prepareStatement("insert into public.PROVINCIAS (Nombre, Estado, IdPais) values (?, ?, ?);");
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
                ps = this.conexion.prepareStatement("insert into public.LOCALIDADES (Nombre, Estado, Codigo_Postal, IdProvincia) values (?, ?, ?, ?);");
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
            // </editor-fold>
            //Proveedor
            // <editor-fold defaultstate="collapsed">
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
// </editor-fold>
            //Equipamiento
            // <editor-fold defaultstate="collapsed">
            //Bascula
            // <editor-fold defaultstate="collapsed">
            case "Bascula":
                Bascula unaBascula = (Bascula) unObjeto;
                ps = this.conexion.prepareStatement("insert into public.EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado) values (?, 'Bascula', ?, ?, ?, ?, ?, ?);");
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
// </editor-fold>
            //Deposito
            // <editor-fold defaultstate="collapsed">
            case "Deposito":
                Deposito unDeposito = (Deposito) unObjeto;
                ps = this.conexion.prepareStatement("insert into public.EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, idBascula) values (?, 'Deposito', ?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unDeposito.getNombre());
                ps.setObject(2, unDeposito.getDireccion());
                ps.setObject(3, unDeposito.getFechaAdquisicion());
                ps.setObject(4, unDeposito.getFechaUltimoMantenimiento());
                ps.setObject(5, unDeposito.getCapacidadMaxima());
                ps.setObject(6, unDeposito.getUnidadDeMedida());
                ps.setObject(7, unDeposito.getEstado());
                ps.setObject(8, unDeposito.getBasculaAsociada().getId());
                ps.execute();
                sql = "SELECT max(Id) from public.EQUIPAMIENTOS;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unDeposito.setId(id);
                break;                
// </editor-fold>                
            //Molino
            // <editor-fold defaultstate="collapsed">
            case "Molino":
                Molino unMolino = (Molino) unObjeto;
                //sql = "insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado) values ('"+unMolino.getNombre()+"', 'Molino', '"+unMolino.getDireccion()+"', '"+unMolino.getFechaAdquisicion()+"', '"+unMolino.getFechaUltimoMantenimiento()+"', "+unMolino.getCapacidadMaxima()+", '"+unMolino.getUnidadDeMedida()+"', '"+unMolino.getEstado()+"');";
                ps = this.conexion.prepareStatement("insert into public.EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, idBascula) values (?, 'Molino', ?, ?, ?, ?, ?, ?, ?);");
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
// </editor-fold>
            //Camara de estacionamiento
            // <editor-fold defaultstate="collapsed">
            case "CamaraEstacionamiento":
                CamaraEstacionamiento unaCamara = (CamaraEstacionamiento) unObjeto;
                //sql = "insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, Duracion_Maxima_Estacionamiento) values ('"+unaCamara.getNombre()+"', 'Camara de Estacionamiento', '"+unaCamara.getDireccion()+"', '"+unaCamara.getFechaAdquisicion()+"', '"+unaCamara.getFechaUltimoMantenimiento()+"', "+unaCamara.getCapacidadMaxima()+", '"+unaCamara.getUnidadDeMedida()+"', '"+unaCamara.getEstado()+"', "+unaCamara.getDuracionMaximaEstacionamiento()+");";
                ps = this.conexion.prepareStatement("insert into public.EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, Duracion_Maxima_Estacionamiento, idBascula) values (?, 'Camara de Estacionamiento', ?, ?, ?, ?, ?, ?, ?, ?);");
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
// </editor-fold>                
            //Laboratorio
            // <editor-fold defaultstate="collapsed">
            case "Laboratorio":
                Laboratorio unLaboratorio = (Laboratorio) unObjeto;
                //sql = "insert into EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Capacidad_Maxima, Unidad_De_Medida, Estado, Duracion_Maxima_Estacionamiento) values ('"+unaCamara.getNombre()+"', 'Camara de Estacionamiento', '"+unaCamara.getDireccion()+"', '"+unaCamara.getFechaAdquisicion()+"', '"+unaCamara.getFechaUltimoMantenimiento()+"', "+unaCamara.getCapacidadMaxima()+", '"+unaCamara.getUnidadDeMedida()+"', '"+unaCamara.getEstado()+"', "+unaCamara.getDuracionMaximaEstacionamiento()+");";
                ps = this.conexion.prepareStatement("insert into public.EQUIPAMIENTOS (Nombre, Tipo_Equipamiento, Direccion, Fecha_Adquisicion, Fecha_Ultimo_Mantenimiento, Estado) values (?, 'Laboratorio', ?, ?, ?, ?);");
                ps.setObject(1, unLaboratorio.getNombre());
                ps.setObject(2, unLaboratorio.getDireccion());
                ps.setObject(3, unLaboratorio.getFechaAdquisicion());
                ps.setObject(4, unLaboratorio.getFechaUltimoMantenimiento());
                ps.setObject(5, unLaboratorio.getEstado());
                ps.execute();
                //stmt.execute(sql);
                sql = "SELECT max(Id) from public.EQUIPAMIENTOS;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unLaboratorio.setId(id);
                break;                
// </editor-fold>                
// </editor-fold>            
            //Orden de produccion
            // <editor-fold defaultstate="collapsed">
            case "OrdenDeProduccion":
                OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
                ps = this.conexion.prepareStatement("insert into public.ORDENES_PRODUCCION (Cantidad_A_Producir, Unidad_De_Medida, Fecha_Origen, Fecha_Entrega, Descripcion,Estado, IdUsuario) values (?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unaOrdenProduccion.getCantidadAProducir());
                ps.setObject(2, unaOrdenProduccion.getUnidadDeMedida());
                ps.setObject(3, unaOrdenProduccion.getFechaOrigen());
                ps.setObject(4, unaOrdenProduccion.getFechaEntregaProductoTerminado());
                ps.setObject(5, unaOrdenProduccion.getDescripcion());
                ps.setObject(6, unaOrdenProduccion.getEstadoEvento());
                ps.setObject(7, unaOrdenProduccion.getUsuarioAsociado().getId());
                ps.execute();
                sql = "SELECT max(Id) from public.ORDENES_PRODUCCION;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unaOrdenProduccion.setId(id);

                //Establecer numero de evento.
                sql = "SELECT max(ID_Evento) from public.ORDENES_PRODUCCION;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                idEvento = resultadoDeConsulta.getInt(1);
                unaOrdenProduccion.setIdEvento(idEvento);

                Iterator criteriosAsociados = unaOrdenProduccion.getCriteriosDeAnalisisAsociados().iterator();
                while (criteriosAsociados.hasNext()) {
                    CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) criteriosAsociados.next();
                    ps = this.conexion.prepareStatement("insert into public.ORDEN_PRODUCCION_DETALLES_CRITERIOS_ANALISIS (IdOrdenProduccion, IdCriterioAnalisisLaboratorio) values (? , ?); ");
                    ps.setObject(1, id);
                    ps.setObject(2, unCriterio.getId());
                    ps.execute();
                }
                break;
// </editor-fold>
            //orden de compra
            // <editor-fold defaultstate="collapsed">
            case "OrdenDeCompra":
                OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) unObjeto;

                //Puede o no tener un proveedor.
                if (unaOrdenDeCompra.poseeProveedorAsociado()) {
                    ps = this.conexion.prepareStatement("insert into public.ORDENES_COMPRA (Fecha_Origen, Cantidad, Unidad_De_Medida, Costo_De_Compra_PorUnidad, Estado, IdOrdenProduccion, IdProveedor, IdUsuario) values (?, ?, ?, ?, ?, ?, ?, ?);");
                    ps.setObject(1, unaOrdenDeCompra.getFechaOrigen());
                    ps.setObject(2, unaOrdenDeCompra.getCantidadAComprar());
                    ps.setObject(3, unaOrdenDeCompra.getUnidadDeMedida());
                    ps.setObject(4, unaOrdenDeCompra.getCostoPorUnidad());
                    ps.setObject(5, unaOrdenDeCompra.getEstadoEvento());
                    ps.setObject(6, unaOrdenDeCompra.getOrdenDeProduccionAsociada().getId());
                    ps.setObject(7, unaOrdenDeCompra.getProveedorAsociado().getId());
                    ps.setObject(8, unaOrdenDeCompra.getUsuarioAsociado().getId());
                } else {
                    ps = this.conexion.prepareStatement("insert into public.ORDENES_COMPRA (Fecha_Origen, Cantidad, Unidad_De_Medida, Costo_De_Compra_PorUnidad, Estado, IdOrdenProduccion, IdUsuario) values (?, ?, ?, ?, ?, ?, ?);");
                    ps.setObject(1, unaOrdenDeCompra.getFechaOrigen());
                    ps.setObject(2, unaOrdenDeCompra.getCantidadAComprar());
                    ps.setObject(3, unaOrdenDeCompra.getUnidadDeMedida());
                    ps.setObject(4, unaOrdenDeCompra.getCostoPorUnidad());
                    ps.setObject(5, unaOrdenDeCompra.getEstadoEvento());
                    ps.setObject(6, unaOrdenDeCompra.getOrdenDeProduccionAsociada().getId());
                    ps.setObject(7, unaOrdenDeCompra.getUsuarioAsociado().getId());
                }
                ps.execute();
                sql = "SELECT max(Id) from public.ORDENES_COMPRA;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unaOrdenDeCompra.setId(id);

                //Establecer numero de evento.
                sql = "SELECT max(ID_Evento) from public.ORDENES_PRODUCCION;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                idEvento = resultadoDeConsulta.getInt(1);
                unaOrdenDeCompra.setIdEvento(idEvento);
                break;                
// </editor-fold>                
            //lote
            // <editor-fold defaultstate="collapsed">
            case "Lote":
                Lote unLote = (Lote) unObjeto;
                //Un lote siempre que se inserta sera en un equipamiento.
                //LA ETIQUETA SE CALCULA A PARTIR DEL ID UNA VEZ PERSISTIDO

                ps = this.conexion.prepareStatement("insert into public.LOTES (Tipo_Lote, Estado, Cantidad, Unidad_De_Medida, Fecha_Adquisicion, IdOrdenCompra, IdEquipamiento) values (?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unLote.getTipo_Lote());
                ps.setObject(2, unLote.getEstado());
                ps.setObject(3, unLote.getCantidad());
                ps.setObject(4, unLote.getUnidadDeMedida());
                ps.setObject(5, unLote.getFechaAdquisicion());//DEBERIA HACER PRIMERO ORDENES DE PRODUCCION Y DE COMPRA           
                ps.setObject(6, unLote.getOrdenDeCompraAsociada().getId());
                ps.setObject(7, unLote.getEquipamientoDondeReside().getId());

                //
                ps.execute();
                sql = "SELECT max(Id) from public.LOTES;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unLote.setId(id);
                break;
// </editor-fold>
            //USUARIO
            // <editor-fold defaultstate="collapsed">
            case "Usuario":
                
                Usuario unUsuario = (Usuario) unObjeto;
                ps = this.conexion.prepareStatement("insert into public.USUARIOS (Nombre, Apellido, DNI, Rol, Estado) values (?, ?, ?, ?, ?);");
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
// </editor-fold>
            //Movimiento interno
            // <editor-fold defaultstate="collapsed">
            case "MovimientoInternoMateriaPrima":
                MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) unObjeto;
                //Un lote siempre que se inserta sera en un equipamiento.
                ps = this.conexion.prepareStatement("insert into public.MOVIMIENTOS_INTERNOS_MP (Fecha_Origen, Hora_Entrada, Hora_Salida, Tipo_Unidad_Transporte, cantidad_Unidades, Unidad_De_Medida_Peso, Peso_Entrada, Peso_Salida, N_Hoja_Ruta, N_Remito, N_Precinto, Nombre_Conductor, Patente_Chasis, Patente_Acoplado, Estado, IdLote, IdBascula, IdEquipamientoOrigen, IdEquipamientoDestino, IdUsuario, IdProveedorTransporte) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unMovimiento.getFechaOrigen());
                ps.setObject(2, unMovimiento.getHoraEntrada());
                ps.setObject(3, unMovimiento.getHoraSalida());
                ps.setObject(4, unMovimiento.getUnidadTransporte());
                ps.setObject(5, unMovimiento.getCantidadUnidades());
                ps.setObject(6, unMovimiento.getUnidadDeMedidaPeso());
                ps.setObject(7, unMovimiento.getPesoEntrada());
                ps.setObject(8, unMovimiento.getPesoSalida());
                ps.setObject(9, unMovimiento.getnHojaRuta());
                ps.setObject(10, unMovimiento.getnRemito());
                ps.setObject(11, unMovimiento.getPrecinto());
                ps.setObject(12, unMovimiento.getNombreConductor());
                ps.setObject(13, unMovimiento.getPatenteChasis());
                ps.setObject(14, unMovimiento.getPatenteAcoplado());
                ps.setObject(15, unMovimiento.getEstadoEvento());
                ps.setObject(16, unMovimiento.getLoteAsociado().getId());
                ps.setObject(17, unMovimiento.getEquipamientoDestino().getBasculaAsociada().getId());
                if (unMovimiento.poseeEquipamientoOrigen()) {
                    ps.setObject(18, unMovimiento.getEquipamientoOrigen().getId());
                } else {
                    ps.setObject(18, null);
                }
                ps.setObject(19, unMovimiento.getEquipamientoDestino().getId());
                ps.setObject(20, unMovimiento.getUsuarioAsociado().getId());
                ps.setObject(21, unMovimiento.getProveedorTransporte().getId());
                ps.execute();
                sql = "SELECT max(Id) from public.MOVIMIENTOS_INTERNOS_MP;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unMovimiento.setId(id);

                //Establecer numero de evento.
                sql = "SELECT max(ID_Evento) from public.MOVIMIENTOS_INTERNOS_MP;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                idEvento = resultadoDeConsulta.getInt(1);
                unMovimiento.setIdEvento(idEvento);
                break;
// </editor-fold>                
            //Estacionamiento
            // <editor-fold defaultstate="collapsed">
            case "Estacionamiento":
                //Para dar de alta un estacionamiento, se deben hacer tres cosas:
                //Dar de alta una transformacion
                //Dar de alta un Estacionamiento
                //ASOCIAR  cada lote como un detalle de transformacion.
                Estacionamiento unEstacionamiento = (Estacionamiento) unObjeto;
                ps = this.conexion.prepareStatement("insert into public.TRANSFORMACIONES (Tipo_Transformacion, Estado, Fecha_Origen, Fecha_extraccion, IdEquipamiento, IdUsuario) values (?, ?, ?, ?, ?, ?);");
                ps.setObject(1, "Estacionamiento");
                ps.setObject(2, unEstacionamiento.getEstado());
                ps.setObject(3, unEstacionamiento.getFechaOrigen());
                ps.setObject(4, unEstacionamiento.getFechaExtraccion());
                ps.setObject(5, unEstacionamiento.getEquipamientoAsociado().getId());
                ps.setObject(6, unEstacionamiento.getUsuarioAsociado().getId());

                ps.execute();
                sql = "SELECT max(Id) from public.TRANSFORMACIONES;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unEstacionamiento.setId(id);

                Iterator lotesEstacionados = unEstacionamiento.getLotesImplicados().iterator();
                while (lotesEstacionados.hasNext()) {
                    Lote unLoteEstacionado = (Lote) lotesEstacionados.next();
                    ps = this.conexion.prepareStatement("insert into public.DETALLES_TRANSFORMACION (IdTransformacion, IdLote) values (? , ?); ");
                    ps.setObject(1, id);
                    ps.setObject(2, unLoteEstacionado.getId());
                    ps.execute();
                }

                //Establecer numero de evento.
                sql = "SELECT max(ID_Evento) from public.TRANSFORMACIONES;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                idEvento = resultadoDeConsulta.getInt(1);
                unEstacionamiento.setIdEvento(idEvento);
                break;
// </editor-fold>  
            //Analisis de laboratorio
            // <editor-fold defaultstate="collapsed">
            case "AnalisisLaboratorio":
                AnalisisLaboratorio unAnalisisDeLaboratorio = (AnalisisLaboratorio) unObjeto;

                if (unAnalisisDeLaboratorio.getLoteImplicado() != null) {
                    ps = this.conexion.prepareStatement("insert into public.ANALISIS_LABORATORIO (Estado, Fecha_Origen, Puntos_Negros, Torrada, Color, Aroma, Tacto, Degustacion, IdLote, Porcentaje_Palo, Porcentaje_Polvo, Porcentaje_Semilla, Porcentaje_Hoja, Porcentaje_Humedad, Conclusion, Comentario, IdLaboratorio, IdCriterio, IdUsuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                    ps.setObject(1, unAnalisisDeLaboratorio.getEstado());
                    ps.setObject(2, unAnalisisDeLaboratorio.getFechaOrigen());
                    ps.setObject(3, unAnalisisDeLaboratorio.getPuntosNegros());
                    ps.setObject(4, unAnalisisDeLaboratorio.getTorrada());
                    ps.setObject(5, unAnalisisDeLaboratorio.getColor());
                    ps.setObject(6, unAnalisisDeLaboratorio.getAroma());
                    ps.setObject(7, unAnalisisDeLaboratorio.getTacto());
                    ps.setObject(8, unAnalisisDeLaboratorio.getDegustacion());
                    ps.setObject(9, unAnalisisDeLaboratorio.getLoteImplicado().getId());
                    ps.setObject(10, unAnalisisDeLaboratorio.getPorcentajePalo());
                    ps.setObject(11, unAnalisisDeLaboratorio.getPorcentajePolvo());
                    ps.setObject(12, unAnalisisDeLaboratorio.getPorcentajeSemilla());
                    ps.setObject(13, unAnalisisDeLaboratorio.getPorcentajeHoja());
                    ps.setObject(14, unAnalisisDeLaboratorio.getPorcentajeHumedad());
                    ps.setObject(15, unAnalisisDeLaboratorio.getConclusion());
                    ps.setObject(16, unAnalisisDeLaboratorio.getComentario());
                    ps.setObject(17, unAnalisisDeLaboratorio.getLaboratorioAsociado().getId());
                    ps.setObject(18, unAnalisisDeLaboratorio.getCriterioAsociado().getId());
                    ps.setObject(19, unAnalisisDeLaboratorio.getUsuarioAsociado().getId());
                } else {
                    ps = this.conexion.prepareStatement("insert into public.ANALISIS_LABORATORIO (Estado, Fecha_Origen, Puntos_Negros, Torrada, Color, Aroma, Tacto, Degustacion, IdOrdenDeCompra, Porcentaje_Palo, Porcentaje_Polvo, Porcentaje_Semilla, Porcentaje_Hoja, Porcentaje_Humedad, Conclusion, Comentario, IdLaboratorio, IdCriterio, IdUsuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                    ps.setObject(1, unAnalisisDeLaboratorio.getEstado());
                    ps.setObject(2, unAnalisisDeLaboratorio.getFechaOrigen());
                    ps.setObject(3, unAnalisisDeLaboratorio.getPuntosNegros());
                    ps.setObject(4, unAnalisisDeLaboratorio.getTorrada());
                    ps.setObject(5, unAnalisisDeLaboratorio.getColor());
                    ps.setObject(6, unAnalisisDeLaboratorio.getAroma());
                    ps.setObject(7, unAnalisisDeLaboratorio.getTacto());
                    ps.setObject(8, unAnalisisDeLaboratorio.getDegustacion());
                    ps.setObject(9, unAnalisisDeLaboratorio.getOrdenDeCompraImplicada().getId());
                    //ps.setObject(9, unAnalisisDeLaboratorio.getLoteImplicado().getId());
                    ps.setObject(10, unAnalisisDeLaboratorio.getPorcentajePalo());
                    ps.setObject(11, unAnalisisDeLaboratorio.getPorcentajePolvo());
                    ps.setObject(12, unAnalisisDeLaboratorio.getPorcentajeSemilla());
                    ps.setObject(13, unAnalisisDeLaboratorio.getPorcentajeHoja());
                    ps.setObject(14, unAnalisisDeLaboratorio.getPorcentajeHumedad());
                    ps.setObject(15, unAnalisisDeLaboratorio.getConclusion());
                    ps.setObject(16, unAnalisisDeLaboratorio.getComentario());
                    ps.setObject(17, unAnalisisDeLaboratorio.getLaboratorioAsociado().getId());
                    ps.setObject(18, unAnalisisDeLaboratorio.getCriterioAsociado().getId());
                    ps.setObject(19, unAnalisisDeLaboratorio.getUsuarioAsociado().getId());

                }

                ps.execute();
                sql = "SELECT max(Id) from public.ANALISIS_LABORATORIO;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unAnalisisDeLaboratorio.setId(id);

                //Establecer numero de evento.
                sql = "SELECT max(ID_Evento) from public.ANALISIS_LABORATORIO;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                idEvento = resultadoDeConsulta.getInt(1);
                unAnalisisDeLaboratorio.setIdEvento(idEvento);
                break;                
// </editor-fold>                  
            //Criterios de analisis de laboratorio
            // <editor-fold defaultstate="collapsed">
            case "CriterioAnalisisLaboratorio":
                CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) unObjeto;

                ps = this.conexion.prepareStatement("insert into public.CRITERIOS_ANALISIS_LABORATORIO (Nombre, Descripcion, Fecha_Origen, "
                        + "Puntos_Negros, Torrada, Color, Aroma, Tacto, Degustacion, "
                        + "Porcentaje_Semilla_Limite_Inferior, Porcentaje_Semilla_Limite_Superior, "
                        + "Porcentaje_Palo_Limite_Inferior, Porcentaje_Palo_Limite_Superior, "
                        + "Porcentaje_Polvo_Limite_Inferior, Porcentaje_Polvo_Limite_Superior,"
                        + "Porcentaje_Hoja_Limite_Inferior, Porcentaje_Hoja_Limite_Superior, "
                        + "Porcentaje_Humedad_Limite_Inferior, Porcentaje_Humedad_Limite_Superior, "
                        + "Tipo_Lote, Estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                ps.setObject(1, unCriterio.getNombre());
                ps.setObject(2, unCriterio.getDescripcion());
                ps.setObject(3, unCriterio.getFechaOrigen());
                ps.setObject(4, unCriterio.getPuntosNegros());
                ps.setObject(5, unCriterio.getTorrada());
                ps.setObject(6, unCriterio.getColor());
                ps.setObject(7, unCriterio.getAroma());
                ps.setObject(8, unCriterio.getTacto());
                ps.setObject(9, unCriterio.getDegustacion());
                ps.setObject(10, unCriterio.getPorcentajeSemillaLimiteInferior());
                ps.setObject(11, unCriterio.getPorcentajeSemillaLimiteSuperior());
                ps.setObject(12, unCriterio.getPorcentajePaloLimiteInferior());
                ps.setObject(13, unCriterio.getPorcentajePaloLimiteSuperior());
                ps.setObject(14, unCriterio.getPorcentajePolvoLimiteInferior());
                ps.setObject(15, unCriterio.getPorcentajePolvoLimiteSuperior());
                ps.setObject(16, unCriterio.getPorcentajeHojaLimiteInferior());
                ps.setObject(17, unCriterio.getPorcentajeHojaLimiteSuperior());
                ps.setObject(18, unCriterio.getPorcentajeHumedadLimiteInferior());
                ps.setObject(19, unCriterio.getPorcentajeHumedadLimiteSuperior());
                ps.setObject(20, unCriterio.getTipoLote());
                ps.setObject(21, unCriterio.getEstado());

                ps.execute();
                sql = "SELECT max(Id) from public.CRITERIOS_ANALISIS_LABORATORIO;";
                resultadoDeConsulta = stmt.executeQuery(sql);
                resultadoDeConsulta.next();
                id = resultadoDeConsulta.getInt(1);
                unCriterio.setId(id);
                break;                
// </editor-fold>  
        }
    }

    public void modificarObjeto(Object unObjeto) throws SQLException, ExcepcionPersistencia {
        String sql;
        Statement stmt = this.conexion.createStatement();
        PreparedStatement ps;
        switch (unObjeto.getClass().getSimpleName()) {
            //Pais provincia y localidad
            //<editor-fold defaultstate="collapsed">
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
            // </editor-fold>
            //Proveedor
            // <editor-fold defaultstate="collapsed">
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
            // </editor-fold>
            //Equipamiento
            // <editor-fold defaultstate="collapsed">
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
            case "Laboratorio":
                Laboratorio unLaboratorio = (Laboratorio) unObjeto;
                ps = this.conexion.prepareStatement("UPDATE public.EQUIPAMIENTOS SET Nombre = ?, Direccion = ?, Fecha_Adquisicion = ?, Fecha_Ultimo_Mantenimiento = ?, Estado = ? WHERE ID = ?;");
                ps.setObject(1, unLaboratorio.getNombre());
                ps.setObject(2, unLaboratorio.getDireccion());
                ps.setObject(3, unLaboratorio.getFechaAdquisicion());
                ps.setObject(4, unLaboratorio.getFechaUltimoMantenimiento());
                ps.setObject(5, unLaboratorio.getEstado());
                ps.setObject(6, unLaboratorio.getId());
                ps.execute();
                break;
            // </editor-fold>
            //Orden de produccion
            // <editor-fold defaultstate="collapsed">            
            case "OrdenDeProduccion":
                OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
                ps = this.conexion.prepareStatement("UPDATE public.ORDENES_PRODUCCION SET Cantidad_A_Producir = ?, Unidad_De_Medida = ?, Fecha_Origen = ?, Fecha_Entrega = ?, Descripcion = ?, Estado = ? WHERE ID = ?;");
                ps.setObject(1, unaOrdenProduccion.getCantidadAProducir());
                ps.setObject(2, unaOrdenProduccion.getUnidadDeMedida());
                ps.setObject(3, unaOrdenProduccion.getFechaOrigen());
                ps.setObject(4, unaOrdenProduccion.getFechaEntregaProductoTerminado());
                ps.setObject(5, unaOrdenProduccion.getDescripcion());
                ps.setObject(6, unaOrdenProduccion.getEstadoEvento());
                ps.setObject(7, unaOrdenProduccion.getId());
                ps.execute();

                //remocion de criterios no utilizados
                ps = this.conexion.prepareStatement("DELETE FROM public.ORDEN_PRODUCCION_DETALLES_CRITERIOS_ANALISIS WHERE (IdOrdenProduccion = ?);");
                ps.setObject(1, unaOrdenProduccion.getId());
                ps.execute();

                Iterator criteriosAsociados = unaOrdenProduccion.getCriteriosDeAnalisisAsociados().iterator();
                while (criteriosAsociados.hasNext()) {
                    CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) criteriosAsociados.next();
                    ps = this.conexion.prepareStatement("insert into public.ORDEN_PRODUCCION_DETALLES_CRITERIOS_ANALISIS (IdOrdenProduccion, IdCriterioAnalisisLaboratorio) values (? , ?); ");
                    ps.setObject(1, unaOrdenProduccion.getId());
                    ps.setObject(2, unCriterio.getId());
                    ps.execute();
                }
                break;
            // </editor-fold>
            //orden de compra
            // <editor-fold defaultstate="collapsed">            
            case "OrdenDeCompra":
                OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) unObjeto;
                if (unaOrdenDeCompra.poseeProveedorAsociado()) {
                    ps = this.conexion.prepareStatement("UPDATE public.ORDENES_COMPRA SET Fecha_Origen = ?, Cantidad = ?, Unidad_De_Medida = ?, Costo_De_Compra_PorUnidad = ?, Estado = ?, IdOrdenProduccion = ?, IdProveedor = ? WHERE ID = ?;");
                    ps.setObject(1, unaOrdenDeCompra.getFechaOrigen());
                    ps.setObject(2, unaOrdenDeCompra.getCantidadAComprar());
                    ps.setObject(3, unaOrdenDeCompra.getUnidadDeMedida());
                    ps.setObject(4, unaOrdenDeCompra.getCostoPorUnidad());
                    ps.setObject(5, unaOrdenDeCompra.getEstadoEvento());
                    ps.setObject(6, unaOrdenDeCompra.getOrdenDeProduccionAsociada().getId());
                    ps.setObject(7, unaOrdenDeCompra.getProveedorAsociado().getId());
                    ps.setObject(8, unaOrdenDeCompra.getId());
                } else {
                    ps = this.conexion.prepareStatement("UPDATE public.ORDENES_COMPRA SET Fecha_Origen = ?, Cantidad = ?, Unidad_De_Medida = ?, Costo_De_Compra_PorUnidad = ?, Estado = ?, IdOrdenProduccion = ?, IdProveedor = NULL WHERE ID = ?;");
                    ps.setObject(1, unaOrdenDeCompra.getFechaOrigen());
                    ps.setObject(2, unaOrdenDeCompra.getCantidadAComprar());
                    ps.setObject(3, unaOrdenDeCompra.getUnidadDeMedida());
                    ps.setObject(4, unaOrdenDeCompra.getCostoPorUnidad());
                    ps.setObject(5, unaOrdenDeCompra.getEstadoEvento());
                    ps.setObject(6, unaOrdenDeCompra.getOrdenDeProduccionAsociada().getId());
                    ps.setObject(7, unaOrdenDeCompra.getId());
                }
                ps.execute();
                break;
            // </editor-fold>                
            //lote
            // <editor-fold defaultstate="collapsed">            
            case "Lote":
                Lote unLote = (Lote) unObjeto;

                //Puede o no poseer un equipamiento
                ps = this.conexion.prepareStatement("UPDATE public.LOTES SET Tipo_Lote = ?, Etiqueta = ?, Estado = ?, Cantidad = ?, Unidad_De_Medida = ?, Fecha_Adquisicion = ?, IdOrdenCompra = ?, IdEquipamiento = ? WHERE ID = ?;");

                ps.setObject(1, unLote.getTipo_Lote());
                ps.setObject(2, unLote.getEtiqueta());
                ps.setObject(3, unLote.getEstado());
                ps.setObject(4, unLote.getCantidad());
                ps.setObject(5, unLote.getUnidadDeMedida());
                ps.setObject(6, unLote.getFechaAdquisicion());
                ps.setObject(7, unLote.getOrdenDeCompraAsociada().getId());
                ps.setObject(8, unLote.getEquipamientoDondeReside().getId());
                ps.setObject(9, unLote.getId());

                ps.execute();
                break;            
            // </editor-fold>                
            //usuario                
            // <editor-fold defaultstate="collapsed">            
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
            // </editor-fold>                
            //movimiento
            // <editor-fold defaultstate="collapsed">            
            case "MovimientoInternoMateriaPrima":
                MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) unObjeto;
                //Un lote siempre que se inserta sera en un equipamiento.
                ps = this.conexion.prepareStatement("UPDATE public.MOVIMIENTOS_INTERNOS_MP SET "
                        + "Fecha_Origen = ?, "
                        + "Hora_Entrada = ?, "
                        + "Hora_Salida = ?, "
                        + "Tipo_Unidad_Transporte = ?, "
                        + "cantidad_Unidades = ?, "
                        + "Unidad_De_Medida_Peso = ?, "
                        + "Peso_Entrada = ?, "
                        + "Peso_Salida = ?, "
                        + "N_Hoja_Ruta = ?, "
                        + "N_Remito = ?, "
                        + "N_Precinto = ?, "
                        + "Nombre_Conductor = ?, "
                        + "Patente_Chasis = ?, "
                        + "Patente_Acoplado = ?, "
                        + "Estado = ?, "
                        + "IdLote = ?, "
                        + "IdBascula = ?, "
                        + "IdEquipamientoOrigen = ?, "
                        + "IdEquipamientoDestino = ?, "
                        + "IdUsuario = ? "
                        + "WHERE ID = ?;");
                ps.setObject(1, unMovimiento.getFechaOrigen());
                ps.setObject(2, unMovimiento.getHoraEntrada());
                ps.setObject(3, unMovimiento.getHoraSalida());
                ps.setObject(4, unMovimiento.getUnidadTransporte());
                ps.setObject(5, unMovimiento.getCantidadUnidades());
                ps.setObject(6, unMovimiento.getUnidadDeMedidaPeso());
                ps.setObject(7, unMovimiento.getPesoEntrada());
                ps.setObject(8, unMovimiento.getPesoSalida());
                ps.setObject(9, unMovimiento.getnHojaRuta());
                ps.setObject(10, unMovimiento.getnRemito());
                ps.setObject(11, unMovimiento.getPrecinto());
                ps.setObject(12, unMovimiento.getNombreConductor());
                ps.setObject(13, unMovimiento.getPatenteChasis());
                ps.setObject(14, unMovimiento.getPatenteAcoplado());
                ps.setObject(15, unMovimiento.getEstadoEvento());
                ps.setObject(16, unMovimiento.getLoteAsociado().getId());
                ps.setObject(17, unMovimiento.getEquipamientoDestino().getBasculaAsociada().getId());
                if (unMovimiento.poseeEquipamientoOrigen()) {
                    ps.setObject(18, unMovimiento.getEquipamientoOrigen().getId());
                } else {
                    ps.setObject(18, null);
                }
                ps.setObject(19, unMovimiento.getEquipamientoDestino().getId());
                ps.setObject(20, unMovimiento.getUsuarioAsociado().getId());
                ps.setObject(21, unMovimiento.getId());
                ps.execute();
                break;            
            // </editor-fold>                
            //estacionamiento
            // <editor-fold defaultstate="collapsed">            
            case "Estacionamiento":
                Estacionamiento unEstacionamiento = (Estacionamiento) unObjeto;
                ps = this.conexion.prepareStatement("UPDATE public.TRANSFORMACIONES SET "
                        + "Estado = ?, "
                        + "Fecha_extraccion = ? "
                        + "WHERE ID = ?;");
                ps.setObject(1, unEstacionamiento.getEstado());
                ps.setObject(2, unEstacionamiento.getFechaExtraccion());
                ps.setObject(3, unEstacionamiento.getId());
                ps.execute();
                break;            
            // </editor-fold>                
            //analisis de laboratorio
            // <editor-fold defaultstate="collapsed">            
            case "AnalisisLaboratorio":
                AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) unObjeto;
                ps = this.conexion.prepareStatement("UPDATE public.ANALISIS_LABORATORIO SET "
                        + "Estado = ?, "
                        + "Comentario = ?, "
                        + "IdLote = ? ,"
                        + "IdOrdenDeCompra = ? "
                        + "WHERE ID = ?;");
                ps.setObject(1, unAnalisis.getEstado());
                ps.setObject(2, unAnalisis.getComentario());
                if (unAnalisis.getLoteImplicado() != null) {
                    ps.setObject(3, unAnalisis.getLoteImplicado().getId());
                } else {
                    ps.setObject(3, null);
                }
                if (unAnalisis.getOrdenDeCompraImplicada() != null){
                    ps.setObject(4, unAnalisis.getOrdenDeCompraImplicada().getId());
                } else {
                    ps.setObject(4, null);
                }
                ps.setObject(5, unAnalisis.getId());
                ps.execute();
                break;            
            // </editor-fold>                
            //Criterios de analisis de laboratorio
            // <editor-fold defaultstate="collapsed">            
            case "CriterioAnalisisLaboratorio":
                CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) unObjeto;
                ps = this.conexion.prepareStatement("UPDATE public.CRITERIOS_ANALISIS_LABORATORIO SET Nombre = ?, Descripcion = ?, Fecha_Origen = ?, "
                        + "Puntos_Negros = ?, Torrada = ?, Color = ?, Aroma = ?, Tacto = ?, Degustacion = ?, "
                        + "Porcentaje_Semilla_Limite_Inferior = ?, Porcentaje_Semilla_Limite_Superior = ?, "
                        + "Porcentaje_Palo_Limite_Inferior = ?, Porcentaje_Palo_Limite_Superior = ?, "
                        + "Porcentaje_Polvo_Limite_Inferior = ?, Porcentaje_Polvo_Limite_Superior = ?,"
                        + "Porcentaje_Hoja_Limite_Inferior = ?, Porcentaje_Hoja_Limite_Superior = ?, "
                        + "Porcentaje_Humedad_Limite_Inferior = ?, Porcentaje_Humedad_Limite_Superior = ?, "
                        + "Tipo_Lote = ?, Estado = ? WHERE ID = ?;");
                ps.setObject(1, unCriterio.getNombre());
                ps.setObject(2, unCriterio.getDescripcion());
                ps.setObject(3, unCriterio.getFechaOrigen());
                ps.setObject(4, unCriterio.getPuntosNegros());
                ps.setObject(5, unCriterio.getTorrada());
                ps.setObject(6, unCriterio.getColor());
                ps.setObject(7, unCriterio.getAroma());
                ps.setObject(8, unCriterio.getTacto());
                ps.setObject(9, unCriterio.getDegustacion());
                ps.setObject(10, unCriterio.getPorcentajeSemillaLimiteInferior());
                ps.setObject(11, unCriterio.getPorcentajeSemillaLimiteSuperior());
                ps.setObject(12, unCriterio.getPorcentajePaloLimiteInferior());
                ps.setObject(13, unCriterio.getPorcentajePaloLimiteSuperior());
                ps.setObject(14, unCriterio.getPorcentajePolvoLimiteInferior());
                ps.setObject(15, unCriterio.getPorcentajePolvoLimiteSuperior());
                ps.setObject(16, unCriterio.getPorcentajeHojaLimiteInferior());
                ps.setObject(17, unCriterio.getPorcentajeHojaLimiteSuperior());
                ps.setObject(18, unCriterio.getPorcentajeHumedadLimiteInferior());
                ps.setObject(19, unCriterio.getPorcentajeHumedadLimiteSuperior());
                ps.setObject(20, unCriterio.getTipoLote());
                ps.setObject(21, unCriterio.getEstado());
                ps.setObject(22, unCriterio.getId());
                ps.execute();
                break;            
            // </editor-fold>                
            default:
                throw new ExcepcionPersistencia("La clase " + unObjeto.getClass().getSimpleName() + " no es valida para realizar una modificación.");
        }
    }

    public ArrayList obtenerAuditoriaOrdenesProduccion(String unaCantidadDeRegistros) throws SQLException, ExcepcionCargaParametros {
        if (!Validaciones.esUnNumeroEnteroValido(unaCantidadDeRegistros)) {
            throw new ExcepcionCargaParametros("No se ingreso un numero valido de registros a recuperar.");
        }
        int cantidadDeRegistros = Integer.parseInt(unaCantidadDeRegistros);
        ArrayList retorno = new ArrayList();
        String sql;
        Statement stmt;
        ResultSet rc;

        sql = "select * from AUDITORIA_ORDENES_PRODUCCION LIMIT " + cantidadDeRegistros + ";";
        stmt = this.conexion.createStatement();
        try {
            rc = stmt.executeQuery(sql);
            while (rc.next()) {
                java.sql.Date unaFechaOcurrencia = rc.getDate("Fecha");
                String unaFechaOcurrenciaString = (new SimpleDateFormat("dd/MM/yyyy")).format(unaFechaOcurrencia.getTime());

                java.sql.Date unaFechaOrigenAnterior = rc.getDate("Fecha_Origen_Antiguo");
                String unaFechaOrigenAnteriorString = null;
                if (unaFechaOrigenAnterior != null) {
                    unaFechaOrigenAnteriorString = (new SimpleDateFormat("dd/MM/yyyy")).format(unaFechaOrigenAnterior.getTime());
                }

                java.sql.Date unaFechaOrigenPosterior = rc.getDate("Fecha_Origen_Nuevo");
                String unaFechaOrigenPosteriorString = null;
                if (unaFechaOrigenPosterior != null) {
                    unaFechaOrigenPosteriorString = (new SimpleDateFormat("dd/MM/yyyy")).format(unaFechaOrigenPosterior.getTime());
                }

                java.sql.Date unaFechaEntregaAnterior = rc.getDate("Fecha_Entrega_Antiguo");
                String unaFechaEntregaAnteriorString = null;
                if (unaFechaEntregaAnterior != null) {
                    unaFechaEntregaAnteriorString = (new SimpleDateFormat("dd/MM/yyyy")).format(unaFechaEntregaAnterior.getTime());
                }

                java.sql.Date unaFechaEntregaPosterior = rc.getDate("Fecha_Entrega_Nuevo");
                String unaFechaEntregaPosteriorString = null;
                if (unaFechaEntregaPosterior != null) {
                    unaFechaEntregaPosteriorString = (new SimpleDateFormat("dd/MM/yyyy")).format(unaFechaEntregaPosterior.getTime());
                }
                Usuario unUsuario = this.organizacionAsociada.getUsuarios().get(rc.getInt("IdUsuario"));
                Object[] vec = {
                    rc.getInt("ID"),
                    "" + unUsuario.getApellido() + ", " + unUsuario.getNombre(),
                    unaFechaOcurrenciaString,
                    rc.getFloat("Cantidad_A_Producir_Antiguo"),
                    rc.getFloat("Cantidad_A_Producir_Nuevo"),
                    rc.getString("Unidad_De_Medida_Antiguo"),
                    rc.getString("Unidad_De_Medida_Nuevo"),
                    unaFechaOrigenAnteriorString,
                    unaFechaOrigenPosteriorString,
                    unaFechaEntregaAnteriorString,
                    unaFechaEntregaPosteriorString,
                    rc.getString("Estado_Antiguo"),
                    rc.getString("Estado_Nuevo"),};
                retorno.add(vec);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //No hacer nada. Si devuelve un error es porque la tabla está vacía.
        }
        return retorno;
    }

    public Organizacion getOrganizacionAsociada() {
        return organizacionAsociada;
    }

    public void setOrganizacionAsociada(Organizacion organizacionAsociada) {
        this.organizacionAsociada = organizacionAsociada;
    }

}
