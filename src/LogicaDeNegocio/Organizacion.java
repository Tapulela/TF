/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import Persistencia.ExcepcionPersistencia;
import Persistencia.Persistencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

/**
 *
 * @author usuario
 */
public class Organizacion {
    private Persistencia persistencia;
    private Map<Integer, Empleado> empleados;
    private Map<Integer, Proveedor> proveedores;
    private Map<Integer, Equipamiento> equipamientos;
    private Map<Integer, MovimientoInternoMateriaPrima> movimientos;
    private Map<Integer, Lote> lotes;
    private Map<Integer, OrdenDeProduccion> ordenesProduccion;
    private Map<Integer, OrdenDeCompra> ordenesCompra;
    private Map<Integer, Pais> paises;
    private Map<Integer, Provincia> provincias;
    private Map<Integer, Localidad> localidades;

    public Organizacion(Persistencia persistencia) throws SQLException {
        this.persistencia = persistencia;
        this.proveedores = new HashMap<Integer, Proveedor>();
        this.equipamientos = new HashMap<Integer, Equipamiento>();
        this.movimientos = new HashMap<Integer, MovimientoInternoMateriaPrima>();
        this.ordenesProduccion = new HashMap <Integer, OrdenDeProduccion>();
        this.lotes = new HashMap <Integer, Lote>();
        this.ordenesCompra = new HashMap <Integer, OrdenDeCompra>();
        this.paises = new HashMap <Integer, Pais>();        
        this.provincias = new HashMap <Integer, Provincia>();
        this.localidades = new HashMap <Integer, Localidad>();
        this.persistencia.recuperarOrganizacion(this);
    }

    public Map<Integer, OrdenDeCompra> getOrdenesCompra() {
        return ordenesCompra;
    }

    
    public Map<Integer, OrdenDeProduccion> getOrdenesProduccion() {
        return ordenesProduccion;
    }
    
    public Map<Integer, Empleado> getEmpleados() {
        return empleados;
    }

    public Map<Integer, Proveedor> getProveedores() {
        return proveedores;
    }

    public Map<Integer, Equipamiento> getEquipamientos() {
        return equipamientos;
    }

    public Persistencia getPersistencia() {
        return persistencia;
    }

    public Map<Integer, MovimientoInternoMateriaPrima> getMovimientos() {
        return movimientos;
    }

    public Map<Integer, Lote> getLotes() {
        return lotes;
    }

    public Map<Integer, Pais> getPaises() {
        return paises;
    }

    public Map<Integer, Provincia> getProvincias() {
        return provincias;
    }

    public Map<Integer, Localidad> getLocalidades() {
        return localidades;
    }
    

    public void registrarOrdenDeProduccion(Calendar unaFechaOrigen, float cantidadAProducir, String unidadDeMedida, Calendar fechaEntregaProductoTerminado, String unaDescripcion) throws ExcepcionCargaParametros, SQLException {
        if (unaFechaOrigen == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de origen.");
        if (fechaEntregaProductoTerminado == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de entrega de producto terminado.");
        if (cantidadAProducir <= 0)
            throw new ExcepcionCargaParametros("Por favor, ingrese una cantidad a Producir positiva.");
        OrdenDeProduccion unaOrdenDeProduccion = new OrdenDeProduccion(unaFechaOrigen, cantidadAProducir, unidadDeMedida, fechaEntregaProductoTerminado, unaDescripcion);
        this.persistencia.persistirObjeto(unaOrdenDeProduccion);
        this.ordenesProduccion.put(unaOrdenDeProduccion.getId(), unaOrdenDeProduccion);
    }
    
    /**
     *Registra un Equipamiento segun los parametros ingresados
     * Notese que si se ingresa una camara de estacionamiento, la capacidad maxima de estacionamiento debe ingresarse como ultimo parametro y debe ser no nulo.
     * Valores aceptados para unTipoEquipamiento:
     * Molino
     * Bascula
     * Camara de estacionamiento acelerado
     * Deposito
     */
    public void registrarEquipamiento(String unTipoEquipamiento, String unNombre, String unaDireccion, Calendar unaFechaAdquisicion, Calendar unaFechaUltimoMantenimiento, float unaCapacidadMaxima, String unaUnidadDeMedida, Bascula unaBasculaAsociada, float unaDuracionMaximaEstacionamiento) throws SQLException, ExcepcionCargaParametros{
        if (!unTipoEquipamiento.equals("Bascula") && unaBasculaAsociada == null)
            throw new ExcepcionCargaParametros("No se ingresó una bascula para asociar");
        if (this.existeEquipamiento(unNombre)){
            throw new ExcepcionCargaParametros("Ya existe un equipamiento con ese nombre.");
        }
        if (unaBasculaAsociada != null && !unaBasculaAsociada.estaActivo())
            throw new ExcepcionCargaParametros("La bascula que desea asociar no se encuentra activa.");
        if (unNombre.isEmpty())
            throw new ExcepcionCargaParametros("Se requiere especificar un nombre");
        if (unaDireccion.isEmpty())
            throw new ExcepcionCargaParametros("Se requiere especificar una direccion");
        if (unaFechaAdquisicion == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de adquisicion.");
        if (unaFechaUltimoMantenimiento == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de Ultimo mantenimiento.");
        if (unaFechaAdquisicion.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("No se puede cargar una fecha de adquisicion posterior a la fecha actual.");
        if (unaFechaUltimoMantenimiento.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("No se puede cargar una fecha de ultimo mantenimiento posterior a la fecha actual.");
        if (unaCapacidadMaxima <= 0)
            throw new ExcepcionCargaParametros("Por favor ingrese una capacidad máxima positiva.");
        switch (unTipoEquipamiento){
            case "Molino":    
                Molino unMolino = new Molino(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, unaCapacidadMaxima, unaUnidadDeMedida, unaBasculaAsociada);
                unaBasculaAsociada.agregarEquipamiento(unMolino);
                this.persistencia.persistirObjeto(unMolino);
                this.equipamientos.put(unMolino.getId(), unMolino);
                break;
            case "Bascula":
                Bascula unaBascula = new Bascula(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, unaCapacidadMaxima, unaUnidadDeMedida);
                this.persistencia.persistirObjeto(unaBascula);
                this.equipamientos.put(unaBascula.getId(), unaBascula);
                break;
            case "Camara de estacionamiento acelerado":
                if (unaDuracionMaximaEstacionamiento <= 0)
                    throw new ExcepcionCargaParametros("Por favor ingrese una duracion maxima de estacionamiento valida.");
                CamaraEstacionamiento unaCamara = new CamaraEstacionamiento(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, unaCapacidadMaxima, unaUnidadDeMedida, unaDuracionMaximaEstacionamiento, unaBasculaAsociada);
                unaBasculaAsociada.agregarEquipamiento(unaCamara);
                this.persistencia.persistirObjeto(unaCamara);
                this.equipamientos.put(unaCamara.getId(), unaCamara);
                break;
            case "Deposito":
                Deposito unDeposito = new Deposito(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, unaCapacidadMaxima, unaUnidadDeMedida, unaBasculaAsociada);
                unaBasculaAsociada.agregarEquipamiento(unDeposito);
                this.persistencia.persistirObjeto(unDeposito);
                this.equipamientos.put(unDeposito.getId(), unDeposito);
                break;
        }
        
                
    }
    
    public void registrarMovimientoInternoMateriaPrima(Calendar fechaEntrada, Calendar fechaSalida, String unidadTransporte, int cantidadUnidades, String unidadMedidaPeso, float pesoEntrada, float pesoSalida, String nHojaRuta, String nRemito, String nPrecinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Lote unLoteAMover, Bascula unaBascula) throws ExcepcionCargaParametros{
        float pesoNeto = pesoEntrada - pesoSalida;
        if (fechaEntrada.after(fechaSalida))
            throw new ExcepcionCargaParametros("La hora de entrada no puede exceder la hora de salida.");
        if (cantidadUnidades <= 0)
            throw new ExcepcionCargaParametros("La cantidad de unidades ingresadas de "+unidadTransporte+" no puede ser negativa.");
        if (pesoEntrada <=0 || pesoSalida <= 0)
            throw new ExcepcionCargaParametros("El peso de entrada o de salida no pueden ser 0 o negativos.");
        
        
    }
    
    public static float convertirUnidadPeso (String unidadMedidaEntrada, float peso, String unidadMedidaSalida) throws ExcepcionCargaParametros{
        switch (unidadMedidaEntrada){
            case "Tonelada":
                switch (unidadMedidaSalida){
                    case "Tonelada":
                        return peso;
                    case "Kilogramo":
                        return peso*1000;
                    default:
                        throw new ExcepcionCargaParametros ("Error en la conversion de peso: Se ingreso una unidad de medida no valida de Salida. (Utilice 'Tonelada' o 'Kilogramo'.");
                }
            case "Kilogramo":
                switch (unidadMedidaSalida){
                    case "Tonelada":
                        return peso/1000;
                    case "Kilogramo":
                        return peso;
                    default:
                        throw new ExcepcionCargaParametros ("Error en la conversion de peso: Se ingreso una unidad de medida no valida de Salida. (Utilice 'Tonelada' o 'Kilogramo'.");
                }                
            default:
                throw new ExcepcionCargaParametros ("Error en la conversion de peso: Se ingreso una unidad de medida no valida de entrada. (Utilice 'Tonelada' o 'Kilogramo'.");
        }
    }
    
    public ArrayList getBasculasActivas(){
        ArrayList retorno = new ArrayList();
        Iterator recorredorClaves = this.equipamientos.keySet().iterator();
        while (recorredorClaves.hasNext()){
            
            Equipamiento unEquipamiento = (Equipamiento) this.equipamientos.get(recorredorClaves.next());
            if (unEquipamiento instanceof Bascula && unEquipamiento.estaActivo()){
                retorno.add(unEquipamiento);
            }
        }
        return retorno;
    }
    
    public Bascula getUnaBascula(String unNombre){ //METODO PARA INTERFAZ GRAFICA.
        Bascula retorno = null;
        ArrayList basculas = this.getBasculasActivas();
        Iterator recorredorBasculas = basculas.iterator();
        while (recorredorBasculas.hasNext() && retorno == null){
            Bascula unaBascula = (Bascula) recorredorBasculas.next();
            if (unaBascula.getNombre().equals(unNombre))
                retorno = unaBascula;
        }
        return retorno;
    }
    
    public ArrayList filtrarEquipamientos(Map<String, Boolean> criterios, String unNombre, String unTipoEquipamiento, String unaDireccion, Calendar fechaAdquisicionLimiteInferior, Calendar fechaAdquisicionLimiteSuperior, Calendar fechaUltimoMantenimientoLimiteInferior, Calendar fechaUltimoMantenimientoLimiteSuperior, String unEstado, Bascula unaBasculaAsociada) throws ExcepcionCargaParametros{
        Boolean criterioNombre = criterios.get("Nombre");
        Boolean criterioTipo = criterios.get("Tipo");
        Boolean criterioDireccion = criterios.get("Direccion");
        Boolean criteriofechaAdquisicion = criterios.get("FechaAdquisicion");
        Boolean criteriofechaUltimoMantenimiento = criterios.get("FechaUltimoMantenimiento");
        Boolean criterioEstado = criterios.get("Estado");
        Boolean criterioBasculaAsociada = criterios.get("BasculaAsociada");
        ArrayList retorno = new ArrayList();
        Iterator equipamientos = this.equipamientos.keySet().iterator();
        
        if (criterioBasculaAsociada && unaBasculaAsociada==null){
            throw new ExcepcionCargaParametros("Debe seleccionar una bascula para realizar la busqueda");
        }
        if (criteriofechaAdquisicion && (fechaAdquisicionLimiteInferior==null || fechaAdquisicionLimiteSuperior == null)){
            throw new ExcepcionCargaParametros("Verifique las fechas de Adquisicion ingresadas");
        }
        if (criteriofechaUltimoMantenimiento && (fechaUltimoMantenimientoLimiteInferior==null || fechaUltimoMantenimientoLimiteSuperior == null)){
            throw new ExcepcionCargaParametros("Verifique las fechas de Ultimo mantenimiento ingresadas");
        }
        while (equipamientos.hasNext()){
            int unId = (int) equipamientos.next();
            Equipamiento unEquipamiento = this.equipamientos.get(unId);
            boolean sePuedeAgregar = true;
            if (criterioNombre){
                sePuedeAgregar = unEquipamiento.getNombre().contains(unNombre);
            }
            if (criterioTipo && sePuedeAgregar){
                switch (unTipoEquipamiento){
                    case "Molino":
                        sePuedeAgregar = unEquipamiento instanceof Molino;
                        break;
                    case "Camara de estacionamiento acelerado":
                        sePuedeAgregar = unEquipamiento instanceof CamaraEstacionamiento;
                        break;
                    case "Deposito":
                        sePuedeAgregar = unEquipamiento instanceof Deposito;
                        break;
                    case "Bascula":
                        sePuedeAgregar = unEquipamiento instanceof Bascula;
                        break;
                }
            }
            if (criterioDireccion && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getDireccion().contains(unaDireccion);
            }
            if (criteriofechaAdquisicion && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getFechaAdquisicionC().after(fechaAdquisicionLimiteInferior);
                if (sePuedeAgregar)
                    sePuedeAgregar = unEquipamiento.getFechaAdquisicionC().before(fechaAdquisicionLimiteSuperior);
            }
            if (criteriofechaUltimoMantenimiento && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getFechaUltimoMantenimientoC().after(fechaUltimoMantenimientoLimiteInferior);
                if (sePuedeAgregar)
                    sePuedeAgregar = unEquipamiento.getFechaUltimoMantenimientoC().before(fechaUltimoMantenimientoLimiteSuperior);
            }
            if (criterioEstado && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getEstado().equals(unEstado);
            }
            if (criterioBasculaAsociada && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getBasculaAsociada() == unaBasculaAsociada;
            }
            if (sePuedeAgregar)
                retorno.add(unEquipamiento);
        }
        return retorno;
    }
    
    public ArrayList filtrarEquipamientosSinBasculas(Map<String, Boolean> criterios, String unNombre, String unTipoEquipamiento, String unaDireccion, Calendar fechaAdquisicionLimiteInferior, Calendar fechaAdquisicionLimiteSuperior, Calendar fechaUltimoMantenimientoLimiteInferior, Calendar fechaUltimoMantenimientoLimiteSuperior, String unEstado, Bascula unaBasculaAsociada) throws ExcepcionCargaParametros{
        Boolean criterioNombre = criterios.get("Nombre");
        Boolean criterioTipo = criterios.get("Tipo");
        Boolean criterioDireccion = criterios.get("Direccion");
        Boolean criteriofechaAdquisicion = criterios.get("FechaAdquisicion");
        Boolean criteriofechaUltimoMantenimiento = criterios.get("FechaUltimoMantenimiento");
        Boolean criterioEstado = criterios.get("Estado");
        Boolean criterioBasculaAsociada = criterios.get("BasculaAsociada");
        ArrayList retorno = new ArrayList();
        Iterator equipamientos = this.equipamientos.keySet().iterator();
        
        if (criterioBasculaAsociada && unaBasculaAsociada==null){
            throw new ExcepcionCargaParametros("Debe seleccionar una bascula para realizar la busqueda");
        }
        if (criteriofechaAdquisicion && (fechaAdquisicionLimiteInferior==null || fechaAdquisicionLimiteSuperior == null)){
            throw new ExcepcionCargaParametros("Verifique las fechas de Adquisicion ingresadas");
        }
        if (criteriofechaUltimoMantenimiento && (fechaUltimoMantenimientoLimiteInferior==null || fechaUltimoMantenimientoLimiteSuperior == null)){
            throw new ExcepcionCargaParametros("Verifique las fechas de Ultimo mantenimiento ingresadas");
        }
        while (equipamientos.hasNext()){
            int unId = (int) equipamientos.next();
            Equipamiento unEquipamiento = this.equipamientos.get(unId);
            boolean sePuedeAgregar = true;
            if (unEquipamiento instanceof Bascula){
                sePuedeAgregar = false;
            }
            if (criterioNombre && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getNombre().contains(unNombre);
            }
            if (criterioTipo && sePuedeAgregar){
                switch (unTipoEquipamiento){
                    case "Molino":
                        sePuedeAgregar = unEquipamiento instanceof Molino;
                        break;
                    case "Camara de estacionamiento acelerado":
                        sePuedeAgregar = unEquipamiento instanceof CamaraEstacionamiento;
                        break;
                    case "Deposito":
                        sePuedeAgregar = unEquipamiento instanceof Deposito;
                        break;
                }
            }
            if (criterioDireccion && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getDireccion().contains(unaDireccion);
            }
            if (criteriofechaAdquisicion && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getFechaAdquisicionC().after(fechaAdquisicionLimiteInferior);
                if (sePuedeAgregar)
                    sePuedeAgregar = unEquipamiento.getFechaAdquisicionC().before(fechaAdquisicionLimiteSuperior);
            }
            if (criteriofechaUltimoMantenimiento && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getFechaUltimoMantenimientoC().after(fechaUltimoMantenimientoLimiteInferior);
                if (sePuedeAgregar)
                    sePuedeAgregar = unEquipamiento.getFechaUltimoMantenimientoC().before(fechaUltimoMantenimientoLimiteSuperior);
            }
            if (criterioEstado && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getEstado().equals(unEstado);
            }
            if (criterioBasculaAsociada && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getBasculaAsociada() == unaBasculaAsociada;
            }
            if (sePuedeAgregar)
                retorno.add(unEquipamiento);
        }
        return retorno;
    }    
    
    
    public void darDeBajaUnPais(Pais unPais) throws ExcepcionCargaParametros, ExcepcionPersistencia, SQLException{
        if (unPais.poseeProvinciasActivas())
            throw new ExcepcionCargaParametros("No se puede dar de baja un país con provincias activas.");
        unPais.darDeBaja();
        persistencia.modificarObjeto(unPais);
    }
    
    public void darDeBajaUnaProvincia(Provincia unaProvincia) throws ExcepcionCargaParametros, ExcepcionPersistencia, SQLException{
        if (unaProvincia.poseeLocalidadesActivas())
            throw new ExcepcionCargaParametros("No se puede dar de baja una provincia con localidades activas.");
        unaProvincia.darDeBaja();
        persistencia.modificarObjeto(unaProvincia);
    }
    
    public void darDeBajaUnaLocalidad(Localidad unaLocalidad) throws ExcepcionCargaParametros, ExcepcionPersistencia, SQLException{
        if (unaLocalidad.poseeProveedoresActivos())
            throw new ExcepcionCargaParametros("No se puede dar de baja una localidad con proveedores activos.");
        unaLocalidad.darDeBaja();
        persistencia.modificarObjeto(unaLocalidad);
    }
    
    public void darDeBajaUnEquipamiento(Equipamiento unEquipamiento) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        //Si se trata de una bascula, solo se podrá dar de baja si no posee ningun Equipamiento asociado
        //Solo se puede dar de baja un equipamiento si no posee ningun lote de entrada que no haya salido.
        if (unEquipamiento == null) 
            throw new ExcepcionCargaParametros("No se ha seleccionado ningun Equipamiento para dar de baja.");
        if (unEquipamiento.poseeLotes())
            throw new ExcepcionCargaParametros("No se puede dar de baja un equipamiento que posea lotes en su interior.");
        switch (unEquipamiento.getClass().getSimpleName()){
            case "Bascula":
                Bascula unaBascula = (Bascula) unEquipamiento;
                if (unaBascula.poseeEquipamientosActivosAsociados()){
                    throw new ExcepcionCargaParametros("No puede darse de baja la bascula porque posee equipamientos activos asociados.");
                }else{
                    unaBascula.darDeBaja();
                    this.persistencia.modificarObjeto(unaBascula);
                }
                break;
            case "Molino":
                Molino unMolino = (Molino) unEquipamiento;
                unMolino.darDeBaja();
                this.persistencia.modificarObjeto(unMolino);
                break;
            case "CamaraEstacionamiento":
                CamaraEstacionamiento unaCamara = (CamaraEstacionamiento) unEquipamiento;
                unaCamara.darDeBaja();
                this.persistencia.modificarObjeto(unaCamara);
                break;
            case "Deposito":
                Deposito unaDeposito = (Deposito) unEquipamiento;
                unaDeposito.darDeBaja();
                this.persistencia.modificarObjeto(unaDeposito);
                break;
        }
    }
    public void modificarUnEquipamiento(Equipamiento unEquipamiento, String unNombre, String unaDireccion, Calendar unaFechaAdquisicion,Calendar unaFechaUltimoMantenimiento, float unaCapacidadMaxima, String unaUnidadDeMedida, Bascula unaBasculaAsociada, float unaDuracionMaximaEstacionamiento, String unEstado) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unEquipamiento == null) 
            throw new ExcepcionCargaParametros("No se ha seleccionado ningun Equipamiento para dar de baja.");
        if (this.existeEquipamiento(unEquipamiento, unNombre))
            throw new ExcepcionCargaParametros("Ya existe un equipamiento con ese nombre.");
        
        if (!(unEquipamiento instanceof Bascula) && unaBasculaAsociada == null)
            throw new ExcepcionCargaParametros("No se ingresó una bascula para asociar");
        if (unaBasculaAsociada != null && !unaBasculaAsociada.estaActivo())
            throw new ExcepcionCargaParametros("La bascula que desea asociar no se encuentra activa.");
        if (unNombre.isEmpty())
            throw new ExcepcionCargaParametros("Se requiere especificar un nombre");
        if (unaDireccion.isEmpty())
            throw new ExcepcionCargaParametros("Se requiere especificar una direccion");
        if (unaFechaAdquisicion == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de adquisicion.");
        if (unaFechaUltimoMantenimiento == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de Ultimo mantenimiento.");
        if (unaFechaAdquisicion.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("No se puede cargar una fecha de adquisicion posterior a la fecha actual.");
        if (unaFechaUltimoMantenimiento.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("No se puede cargar una fecha de ultimo mantenimiento posterior a la fecha actual.");
        if (unaCapacidadMaxima <= 0)
            throw new ExcepcionCargaParametros("Por favor ingrese una capacidad máxima positiva.");
        if (unEstado.equals("Baja") && unEquipamiento instanceof Bascula && unEquipamiento.estaActivo()){
            Bascula unaBascula = (Bascula) unEquipamiento;
            if (unaBascula.poseeEquipamientosActivosAsociados())
                throw new ExcepcionCargaParametros("No se puede dar de baja una Bascula que posee Equipamientos asociados.");
        }
            
        if (unEstado.equals("Baja") && unEquipamiento.estaActivo() && unEquipamiento.poseeLotes())
            throw new ExcepcionCargaParametros("No se puede dar de baja un equipamiento que posee lotes en su interior.");
        
        
        
        unEquipamiento.setNombre(unNombre.toUpperCase());
        unEquipamiento.setDireccion(unaDireccion.toUpperCase());
        unEquipamiento.setFechaAdquisicion(unaFechaAdquisicion);
        unEquipamiento.setFechaUltimoMantenimiento(unaFechaUltimoMantenimiento);
        unEquipamiento.setCapacidadMaxima(unaCapacidadMaxima);
        unEquipamiento.setCapacidadMaxima(unaCapacidadMaxima);
        unEquipamiento.setUnidadDeMedida(unaUnidadDeMedida);        
        unEquipamiento.setEstado(unEstado);
        
        
        String tipoDeEquipamiento = unEquipamiento.getClass().getSimpleName();
        switch (tipoDeEquipamiento){
            case "Molino":    
                Molino unMolino = (Molino) unEquipamiento;
                unEquipamiento.cambiarDeBascula(unaBasculaAsociada);
                this.persistencia.modificarObjeto(unMolino);
                break;
            case "Bascula":
                Bascula unaBascula = (Bascula) unEquipamiento;
                this.persistencia.modificarObjeto(unaBascula);
                break;
            case "CamaraEstacionamiento":
                if (unaDuracionMaximaEstacionamiento <= 0)
                    throw new ExcepcionCargaParametros("Por favor ingrese una duracion maxima de estacionamiento valida.");
                unEquipamiento.cambiarDeBascula(unaBasculaAsociada);
                CamaraEstacionamiento unaCamara = (CamaraEstacionamiento) unEquipamiento;
                unaCamara.setDuracionMaximaEstacionamiento(unaDuracionMaximaEstacionamiento);
                this.persistencia.modificarObjeto(unaCamara);
                break;
            case "Deposito":
                unEquipamiento.cambiarDeBascula(unaBasculaAsociada);
                Deposito unDeposito = (Deposito) unEquipamiento;
                this.persistencia.modificarObjeto(unDeposito);
                break;
        }        
    }

    private boolean existeEquipamiento(String unNombre) {
        boolean retorno = false;
        Iterator recorredorEquipamientos = this.equipamientos.keySet().iterator();
        while (recorredorEquipamientos.hasNext() && !retorno){
            Equipamiento unEquipamiento = this.equipamientos.get(recorredorEquipamientos.next());
            retorno = unEquipamiento.seLlama(unNombre);
        }
        return retorno;
    }

    private boolean existeEquipamiento(Equipamiento unEquipamientoAVerificar, String unNombre) {
        boolean retorno = false;
        Iterator recorredorEquipamientos = this.equipamientos.keySet().iterator();
        while (recorredorEquipamientos.hasNext() && !retorno){
            Equipamiento unEquipamiento = this.equipamientos.get(recorredorEquipamientos.next());
            retorno = (unEquipamiento.seLlama(unNombre)&& !unEquipamiento.equals(unEquipamientoAVerificar));
        }        
        return retorno;
    }
    
    public void registrarPais(String unNombre) throws ExcepcionCargaParametros, SQLException{
        if (unNombre == null)
            throw new ExcepcionCargaParametros("No puede registrarse un nombre vacío.");
        if (existePais(unNombre))
            throw new ExcepcionCargaParametros("Ya existe un pais con ese nombre.");
        Pais unPais = new Pais(unNombre.toUpperCase());
        persistencia.persistirObjeto(unPais);
        this.paises.put(unPais.getId(), unPais);
    }
    
    public void registrarProvincia(Pais unPais, String unNombre) throws ExcepcionCargaParametros, SQLException{
        if (unNombre == null)
            throw new ExcepcionCargaParametros("No puede registrarse un nombre vacío.");
        if (existeProvincia(unPais, unNombre))
            throw new ExcepcionCargaParametros("Ya existe una provincia con ese nombre en ese país.");
        Provincia unaProvincia = new Provincia(unNombre.toUpperCase(), unPais);
        persistencia.persistirObjeto(unaProvincia);
        this.provincias.put(unaProvincia.getId(), unaProvincia);
        unPais.agregarProvincia(unaProvincia);
    }
    public void registrarLocalidad(Provincia unaProvincia, String unNombre, String unCodigoPostal) throws ExcepcionCargaParametros, SQLException{
        if (unNombre == null)
            throw new ExcepcionCargaParametros("No puede registrarse un nombre vacío.");
        if (unCodigoPostal == null)
            throw new ExcepcionCargaParametros("No puede registrarse un codigo postal vacío.");
        if(!Validaciones.esUnCodigoPostalValido(unCodigoPostal))
            throw new ExcepcionCargaParametros("El código postal no es valido (Debe ser el formato viejo (4 digitos) o el formato nuevo (Normas IRAM))");
        if (existeLocalidad(unaProvincia, unNombre))
            throw new ExcepcionCargaParametros("Ya existe una localidad con ese nombre en esa provincia.");
        Localidad unaLocalidad = new Localidad(unNombre.toUpperCase(), unCodigoPostal, unaProvincia);
        persistencia.persistirObjeto(unaLocalidad);
        this.localidades.put(unaLocalidad.getId(), unaLocalidad);
        unaProvincia.agregarLocalidad(unaLocalidad);
    }    
    public void registrarProveedor(String unaRazonSocial, String unCuit, Localidad unaLocalidad) throws ExcepcionCargaParametros, SQLException{
        if (!Validaciones.unCuitEsValido(unCuit))
            throw new ExcepcionCargaParametros("El cuit debe estar en formato XX-XXXXXXXX-X.");
        if (unaRazonSocial.isEmpty())
            throw new ExcepcionCargaParametros("Debe ingresar una razon social para realizar el alta.");
        if (existeProveedor(unaRazonSocial, unCuit))
            throw new ExcepcionCargaParametros("Ya existe un proveedor con esa razon social o ese cuit.");
        if (unaLocalidad == null)
            throw new ExcepcionCargaParametros("No se especifico una localidad.");
        if (!unaLocalidad.seEncuentraActiva())
            throw new ExcepcionCargaParametros("La localidad especificada no se encuentra activa.");
        Proveedor unProveedor = new Proveedor(unaRazonSocial.toUpperCase(), unCuit, unaLocalidad);
        persistencia.persistirObjeto(unProveedor);
        this.proveedores.put(unProveedor.getId(), unProveedor);
        unaLocalidad.agregarProveedor(unProveedor);
    }
    
    public void modificarPais (Pais unPais, String unNombre, String unEstado) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unNombre == null || unNombre.equals(""))
            throw new ExcepcionCargaParametros("El nuevo nombre no puede ser vacío.");
        if (existePais(unPais, unNombre))
            throw new ExcepcionCargaParametros("Ya existe un pais con ese nombre.");
        if (unEstado.equals(unPais.ESTADO_BAJA) && unPais.poseeProvinciasActivas())
            throw new ExcepcionCargaParametros("No se puede dar de baja un país con provincias activas.");
        unPais.setNombre(unNombre.toUpperCase());
        unPais.setEstado(unEstado);
        persistencia.modificarObjeto(unPais);
    }
    
    public void modificarProvincia (Provincia unaProvincia, String unNombre, String unEstado) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unNombre == null || unNombre.equals(""))
            throw new ExcepcionCargaParametros("El nuevo nombre no puede ser vacío.");
        if (existeProvincia(unaProvincia.getPaisAsociado(), unaProvincia, unNombre))
            throw new ExcepcionCargaParametros("Ya existe una provincia con ese nombre en ese pais.");
        if (unEstado.equals(unaProvincia.ESTADO_BAJA) && unaProvincia.poseeLocalidadesActivas())
            throw new ExcepcionCargaParametros("No se puede dar de baja una provincia con localidades activas.");
        unaProvincia.setNombre(unNombre.toUpperCase());
        unaProvincia.setEstado(unEstado);
        persistencia.modificarObjeto(unaProvincia);
    }
    
    public void modificarLocalidad (Provincia unaProvincia, Localidad unaLocalidad, String unNombre, String unCodigoPostal, String unEstado) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unNombre == null || unNombre.equals(""))
            throw new ExcepcionCargaParametros("El nuevo nombre no puede ser vacío.");
        if (!Validaciones.esUnCodigoPostalValido(unCodigoPostal))
            throw new ExcepcionCargaParametros("El código postal no es valido (Debe ser el formato viejo (4 digitos) o el formato nuevo (Normas IRAM))");
        if (existeLocalidad(unaLocalidad.getProvinciaAsociada(), unaLocalidad, unNombre))
            throw new ExcepcionCargaParametros("Ya existe una localidad con ese nombre en esa provincia.");
        if (unEstado.equals(unaLocalidad.ESTADO_BAJA) && unaLocalidad.poseeProveedoresActivos())
            throw new ExcepcionCargaParametros("No se puede dar de baja una localidad con proveedores activos.");
        unaLocalidad.setNombre(unNombre.toUpperCase());
        unaLocalidad.setEstado(unEstado);
        unaLocalidad.setCodigoPostal(unCodigoPostal);
        persistencia.modificarObjeto(unaLocalidad);
    }
    
    public void modificarOrdenDeCompra(OrdenDeCompra unaOrdenDeCompra, String unaUnidadMedida, String unCostoDeCompra, String unEstado, String unaCantidadAComprar, Proveedor proveedorSeleccionado, OrdenDeProduccion ordenProduccionSeleccionada) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        unaCantidadAComprar = unaCantidadAComprar.replace(",", ".");
        unCostoDeCompra = unCostoDeCompra.replace(",", ".");
        
        
        if (unaOrdenDeCompra == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de Compra");
        if (ordenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción");
        if (unEstado.equals(OrdenDeCompra.ESTADO_ANULADO) && unaOrdenDeCompra.poseeLotesRegularesAsociados())
            throw new ExcepcionCargaParametros("No se puede anular una orden de Compra que posee lotes en estado regular.");
        if (unaUnidadMedida.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado una unidad de medida");
        if (!Validaciones.esUnNumeroFraccionarioValido(unaCantidadAComprar))
            throw new ExcepcionCargaParametros("La cantidad a comprar no posee un formato valido (solo numeros y un punto)");
        if (!Validaciones.esUnNumeroFraccionarioValido(unCostoDeCompra))
            throw new ExcepcionCargaParametros("El costo de compra no posee un formato valido (solo numeros y un punto)");
        if (proveedorSeleccionado != null && !proveedorSeleccionado.seEncuentraActivo())
            throw new ExcepcionCargaParametros("El proveedor asociado no se encuentra activo");
        if (!ordenProduccionSeleccionada.seEncuentraRegular())
            throw new ExcepcionCargaParametros("La orden de producción no se encuentra en estado regular.");
        float cantidadAComprar = Float.parseFloat(unaCantidadAComprar);
        float costoDeCompra = Float.parseFloat(unCostoDeCompra);       
        if (cantidadAComprar <=0)
            throw new ExcepcionCargaParametros("La orden de compra no puede poseer una cantidad negativa.");
        if (costoDeCompra <=0)
            throw new ExcepcionCargaParametros("La orden de compra no puede poseer un costo de compra negativo.");
        if (unaOrdenDeCompra.getCantidadComprada(unaUnidadMedida) > cantidadAComprar)
            throw new ExcepcionCargaParametros("La orden de compra no puede tener una cantidad a comprar menor a la cantidad ya comprada.");
        
        unaOrdenDeCompra.setUnidadDeMedida(unaUnidadMedida);
        unaOrdenDeCompra.setCostoPorUnidad(costoDeCompra);
        unaOrdenDeCompra.setCantidadAComprar(cantidadAComprar);
        unaOrdenDeCompra.setProveedorAsociado(proveedorSeleccionado);
        unaOrdenDeCompra.setOrdenDeProduccionAsociada(ordenProduccionSeleccionada);
        unaOrdenDeCompra.setEstado(unEstado);
        persistencia.modificarObjeto(unaOrdenDeCompra);
        
    }
    
    public void modificarProveedor(Proveedor unProveedorAModificar, String unaRazonSocial, String unCuit, String unEstado) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (!Validaciones.unCuitEsValido(unCuit))
            throw new ExcepcionCargaParametros("El cuit debe estar en formato XX-XXXXXXXX-X.");
        if (unaRazonSocial.isEmpty())
            throw new ExcepcionCargaParametros("Debe ingresar una razon social para realizar el alta.");
        if (existeProveedor(unProveedorAModificar, unaRazonSocial, unCuit))
            throw new ExcepcionCargaParametros("Ya existe un proveedor con esa razon social o ese cuit.");
        unProveedorAModificar.setRazonSocial(unaRazonSocial.toUpperCase());
        unProveedorAModificar.setCuit(unCuit);
        unProveedorAModificar.setEstado(unEstado);
        persistencia.modificarObjeto(unProveedorAModificar);
        
    }

    private boolean existeProveedor(String unaRazonSocial, String unCuit) {
        Iterator recorredorProveedores = this.proveedores.keySet().iterator();
        boolean existe = false;
        while (recorredorProveedores.hasNext() && !existe){
            Proveedor unProveedor = this.proveedores.get(recorredorProveedores.next());
            if (unProveedor.poseeCuit(unCuit) || unProveedor.poseeRazonSocial(unaRazonSocial))
                existe = true;
        }
        return existe;
    }
    
    private boolean existeProveedor(Proveedor unProveedorAVerificar, String unaRazonSocial, String unCuit) {
        Iterator recorredorProveedores = this.proveedores.keySet().iterator();
        boolean existe = false;
        while (recorredorProveedores.hasNext() && !existe){
            Proveedor unProveedor = (Proveedor) this.proveedores.get(recorredorProveedores.next());
            if ((unProveedor.poseeCuit(unCuit) || unProveedor.poseeRazonSocial(unaRazonSocial)) && !unProveedor.equals(unProveedorAVerificar))
                existe = true;
        }
        return existe;
    }
    
    public ArrayList filtrarProveedores(Map<String, Boolean> criterios, String unaRazonSocial, String unCuit, String unEstado){
        Boolean criterioRazonSocial = criterios.get("razonSocial");
        Boolean criterioCuit = criterios.get("cuit");
        Boolean criterioEstado = criterios.get("estado");
        ArrayList retorno = new ArrayList();
        Iterator proveedores = this.proveedores.keySet().iterator();
        while (proveedores.hasNext()){
            int unId = (int) proveedores.next();
            Proveedor unProveedor = this.proveedores.get(unId);
            boolean sePuedeAgregar = true;
            if (criterioRazonSocial){
                sePuedeAgregar = unProveedor.getRazonSocial().contains(unaRazonSocial);
            }
            if (criterioCuit && sePuedeAgregar){
                sePuedeAgregar = unProveedor.getCuit().contains(unCuit);
            }
            if (criterioEstado && sePuedeAgregar){
                sePuedeAgregar = unProveedor.getEstado().equals(unEstado);
            }
            if (sePuedeAgregar){
                retorno.add(unProveedor);
            }
        }
        return retorno;
    }
    
    public void darDeBajaUnProveedor(Proveedor unProveedor) throws SQLException, ExcepcionPersistencia{
        unProveedor.setEstado("Baja");
        persistencia.modificarObjeto(unProveedor);
    }

    public ArrayList filtrarOrdenesProduccion(Map<String, Boolean> criterios, String unaDescripcion, String unEstado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior, Calendar fechaEntregaInferior, Calendar fechaEntregaSuperior) throws ExcepcionCargaParametros {
        Boolean criterioDescripcion = criterios.get("descripcion");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioFechaEntrega = criterios.get("fechaEntrega");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        if (criterioFechaEntrega && (fechaEntregaInferior == null || fechaEntregaSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Entrega Inferior y Superior ingresadas");
        ArrayList retorno = new ArrayList();
        Iterator ordenesProduccion = this.ordenesProduccion.keySet().iterator();
        while (ordenesProduccion.hasNext()){
            int unId = (int) ordenesProduccion.next();
            OrdenDeProduccion unaOrdenProduccion = this.ordenesProduccion.get(unId);
            boolean sePuedeAgregar = true;
            if (criterioDescripcion)
                sePuedeAgregar = (unaOrdenProduccion.getDescripcion() != null && unaOrdenProduccion.getDescripcion().contains(unaDescripcion));
            if (sePuedeAgregar && criterioEstado)
                sePuedeAgregar = unaOrdenProduccion.getEstado().equals(unEstado);
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = (unaOrdenProduccion.getFechaOrigenC().after(fechaOrigenInferior) && unaOrdenProduccion.getFechaOrigenC().before(fechaOrigenSuperior));
            if (sePuedeAgregar && criterioFechaEntrega)
                sePuedeAgregar = (unaOrdenProduccion.getFechaEntregaProductoTerminadoC().after(fechaEntregaInferior) && unaOrdenProduccion.getFechaEntregaProductoTerminadoC().before(fechaEntregaSuperior));
            if (sePuedeAgregar){
                retorno.add(unaOrdenProduccion);
            }
        }
        return retorno;
    }

    public void anularOrdenDeProduccion(OrdenDeProduccion unaOrdenDeProduccion) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia {
        
        //CUESTION A RESOLVER: ¿Esta esto bien?
        if (unaOrdenDeProduccion == null)
            throw new ExcepcionCargaParametros("No se selecciono una orden de produccion para anular.");
        if (unaOrdenDeProduccion.poseeOrdenesCompraImplicadasActivas())
            throw new ExcepcionCargaParametros("No se puede anular la orden de producción porque posee ordenes de compra implicadas en estado regular.");
        unaOrdenDeProduccion.anular();
        this.persistencia.modificarObjeto(unaOrdenDeProduccion);
    }

    public ArrayList filtrarOrdenesDeCompra(Map<String, Boolean> criterios, OrdenDeProduccion unaOrdenProduccionSeleccionada, Proveedor unProveedorSeleccionado, String unEstado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioProveedor = criterios.get("proveedor");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioProveedor && unProveedorSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un proveedor.");
        if (criterioEstado && unEstado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        Iterator ordenesDeCompra = this.ordenesCompra.keySet().iterator();
        while (ordenesDeCompra.hasNext()){
            Boolean sePuedeAgregar = true;
            int unId = (int) ordenesDeCompra.next();
            OrdenDeCompra unaOrdenDeCompra = this.ordenesCompra.get(unId);
            if (sePuedeAgregar && criterioOrdenDeProduccionAsociada)
                sePuedeAgregar = unaOrdenDeCompra.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
            if (sePuedeAgregar && criterioProveedor)
                sePuedeAgregar = unaOrdenDeCompra.poseeProveedorAsociado();
            if (sePuedeAgregar && criterioFechaOrigen){
                sePuedeAgregar = unaOrdenDeCompra.origenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            }
            if (sePuedeAgregar)
                retorno.add(unaOrdenDeCompra);
        }
        return retorno;
    }

    public void registrarOrdenDeCompra(OrdenDeProduccion ordenProduccionSeleccionada, Proveedor proveedorSeleccionado, String unaUnidadMedida,String unaCantidadAComprar, String unCostoDeCompra) throws ExcepcionCargaParametros, SQLException {
        unaCantidadAComprar = unaCantidadAComprar.replace(",", ".");
        unCostoDeCompra = unCostoDeCompra.replace(",", ".");
        
        if (ordenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción");
        if (unaUnidadMedida.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado una unidad de medida");
        if (!Validaciones.esUnNumeroFraccionarioValido(unaCantidadAComprar))
            throw new ExcepcionCargaParametros("La cantidad a comprar no posee un formato valido (solo numeros y un punto)");
        if (!Validaciones.esUnNumeroFraccionarioValido(unCostoDeCompra))
            throw new ExcepcionCargaParametros("El costo de compra no posee un formato valido (solo numeros y un punto)");
        if (proveedorSeleccionado != null && !proveedorSeleccionado.seEncuentraActivo())
            throw new ExcepcionCargaParametros("El proveedor asociado no se encuentra activo");
        if (!ordenProduccionSeleccionada.seEncuentraRegular())
            throw new ExcepcionCargaParametros("La orden de producción no se encuentra en estado regular.");
        
        float cantidadAComprar = Float.parseFloat(unaCantidadAComprar);
        float costoDeCompra = Float.parseFloat(unCostoDeCompra);
        
        
        if (cantidadAComprar <=0)
            throw new ExcepcionCargaParametros("La orden de compra no puede poseer una cantidad negativa.");
        if (cantidadAComprar <=0)
            throw new ExcepcionCargaParametros("La orden de compra no puede poseer un costo de compra negativo.");        
        if (Organizacion.convertirUnidadPeso(ordenProduccionSeleccionada.getUnidadDeMedida(), ordenProduccionSeleccionada.getCantidadAProducir(), unaUnidadMedida)< cantidadAComprar)
            throw new ExcepcionCargaParametros("No se puede registrar una compra por una cantidad de peso mayor a la indicada en la orden de producción.");
        
        OrdenDeCompra unaOrdenDeCompra = new OrdenDeCompra(cantidadAComprar, unaUnidadMedida, costoDeCompra, proveedorSeleccionado, ordenProduccionSeleccionada);
        persistencia.persistirObjeto(unaOrdenDeCompra);
        this.ordenesCompra.put(unaOrdenDeCompra.getId(), unaOrdenDeCompra);
        ordenProduccionSeleccionada.agregarOrdenDeCompra(unaOrdenDeCompra);
        
    }

    public void anularOrdenDeCompra(OrdenDeCompra unaOrdenDeCompra) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia {
        if (unaOrdenDeCompra == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de compra para anular.");
        if (unaOrdenDeCompra.poseeLotesRegularesAsociados())
            throw new ExcepcionCargaParametros("No se puede anular una orden de Compra que posee lotes en estado regular.");
        unaOrdenDeCompra.anular();
        persistencia.modificarObjeto(unaOrdenDeCompra);
        
    }

    public ArrayList getLocalidadesActivas() {
        ArrayList retorno = new ArrayList();
        Iterator recorredorLocalidades = this.localidades.keySet().iterator();
        while (recorredorLocalidades.hasNext()){
            int unId = (int)recorredorLocalidades.next();
            Localidad unaLocalidad = this.localidades.get(unId);
            if (unaLocalidad.seEncuentraActiva())
                retorno.add(unaLocalidad);
        }
        return retorno;
    }

    private boolean existePais(String unNombre) {
        boolean retorno = false;
        Iterator paises = this.paises.keySet().iterator();
        while (paises.hasNext() && !retorno){
            int unId = (int) paises.next();
            Pais unPais = this.paises.get(unId);
            retorno = unPais.seLlama(unNombre);
        }
        return retorno;
    }

    private boolean existeProvincia(Pais unPais, String unNombre) {
        boolean retorno = false;
        Iterator provincias = unPais.getProvincias().iterator();
        while (provincias.hasNext() && !retorno){
            Provincia unaProvincia = (Provincia) provincias.next();
            retorno = unaProvincia.seLlama(unNombre);
        }
        return retorno;
    }

    private boolean existeLocalidad(Provincia unaProvincia, String unNombre) {
        //POR LO QUE LEI, PUEDE REPETIRSE EL CODIGO POSTAL. POR ESO NO LO VALIDO.
        boolean retorno = false;
        Iterator localidades = unaProvincia.getLocalidades().iterator();
        while (localidades.hasNext() && !retorno){
            Localidad unaLocalidad = (Localidad) localidades.next();
            retorno = unaLocalidad.seLlama(unNombre);
        }
        return retorno;
    }

    private boolean existePais(Pais unPaisAVerificar, String unNombre) {
        Iterator recorredorPaises = this.paises.keySet().iterator();
        boolean existe = false;
        while (recorredorPaises.hasNext() && !existe){
            Pais unPais = (Pais) this.paises.get(recorredorPaises.next());
            if (unPais.seLlama(unNombre) && !unPais.equals(unPaisAVerificar))
                existe = true;
        }
        return existe;
    }
    
    private boolean existeProvincia(Pais unPaisAVerificar, Provincia unaProvinciaAVerificar, String unNombre) {
        Iterator recorredorProvincias = unPaisAVerificar.getProvincias().iterator();
        boolean existe = false;
        while (recorredorProvincias.hasNext() && !existe){
            Provincia unaProvincia = (Provincia) recorredorProvincias.next();
            if (unaProvincia.seLlama(unNombre) && !unaProvincia.equals(unaProvinciaAVerificar))
                existe = true;
        }
        return existe;
    }    
    
    private boolean existeLocalidad(Provincia unaProvinciaAVerificar, Localidad unaLocalidadAVerificar,String unNombre) {
        Iterator recorredorLocalidades = unaProvinciaAVerificar.getLocalidades().iterator();
        boolean existe = false;
        while (recorredorLocalidades.hasNext() && !existe){
            Localidad unaLocalidad = (Localidad) recorredorLocalidades.next();
            if (unaLocalidad.seLlama(unNombre) && !unaLocalidad.equals(unaLocalidadAVerificar))
                existe = true;
        }
        return existe;
    }        




}


