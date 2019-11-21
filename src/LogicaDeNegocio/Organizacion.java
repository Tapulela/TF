/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import Persistencia.ExcepcionPersistencia;
import Persistencia.Persistencia;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class Organizacion {
    private Persistencia persistencia;
    
    private Usuario usuarioActivo;
    
    private Map<Integer, Empleado> empleados;
    private Map<Integer, Proveedor> proveedores;
    private Map<Integer, Equipamiento> equipamientos;
    private Map<Integer, MovimientoInternoMateriaPrima> movimientos; //INCLUYE INGRESOS
    private Map<Integer, MovimientoInternoMateriaPrima> movimientosDeIngreso;
    private Map<Integer, Lote> lotes;
    private Map<Integer, OrdenDeProduccion> ordenesProduccion;
    private Map<Integer, OrdenDeCompra> ordenesCompra;
    private Map<Integer, Pais> paises;
    private Map<Integer, Provincia> provincias;
    private Map<Integer, Localidad> localidades;
    
    private Map<Integer, Transformacion> transformaciones;
    private Map<Integer, Estacionamiento> estacionamientos;
    
    private Map<Integer, AnalisisLaboratorio> analisisLaboratorio;
    private Map<Integer, CriterioAnalisisLaboratorio> criteriosAnalisisLaboratorio;
    
    private Map<Integer, Usuario> usuarios;

    public Organizacion(Persistencia persistencia) throws SQLException, ClassNotFoundException {
        this.persistencia = persistencia;
        this.proveedores = new HashMap<Integer, Proveedor>();
        this.equipamientos = new HashMap<Integer, Equipamiento>();
        this.movimientos = new HashMap<Integer, MovimientoInternoMateriaPrima>();
        this.movimientosDeIngreso = new HashMap<Integer, MovimientoInternoMateriaPrima>();
        this.ordenesProduccion = new HashMap <Integer, OrdenDeProduccion>();
        this.lotes = new HashMap <Integer, Lote>();
        this.ordenesCompra = new HashMap <Integer, OrdenDeCompra>();
        this.paises = new HashMap <Integer, Pais>();        
        this.provincias = new HashMap <Integer, Provincia>();
        this.localidades = new HashMap <Integer, Localidad>();
        this.usuarios = new HashMap <Integer, Usuario>();
        
        this.transformaciones = new HashMap <Integer, Transformacion>();
        this.estacionamientos = new HashMap <Integer, Estacionamiento>();
        
        this.analisisLaboratorio = new HashMap <Integer, AnalisisLaboratorio>();
        this.criteriosAnalisisLaboratorio = new HashMap <Integer, CriterioAnalisisLaboratorio>();
        
        this.persistencia.recuperarOrganizacion(this);
    }

    public Map<Integer, AnalisisLaboratorio> getAnalisisLaboratorio() {
        return analisisLaboratorio;
    }

    
    
    public Map<Integer, MovimientoInternoMateriaPrima> getMovimientosDeIngreso() {
        return movimientosDeIngreso;
    }

    public void setMovimientosDeIngreso(Map<Integer, MovimientoInternoMateriaPrima> movimientosDeIngreso) {
        this.movimientosDeIngreso = movimientosDeIngreso;
    }

    public Map<Integer, Transformacion> getTransformaciones() {
        return transformaciones;
    }

    public void setTransformaciones(Map<Integer, Transformacion> transformaciones) {
        this.transformaciones = transformaciones;
    }

    public Map<Integer, Estacionamiento> getEstacionamientos() {
        return estacionamientos;
    }

    public void setEstacionamientos(Map<Integer, Estacionamiento> estacionamientos) {
        this.estacionamientos = estacionamientos;
    }
    
    public Map<Integer, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<Integer, Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public Map<Integer, OrdenDeCompra> getOrdenesCompra() {
        return ordenesCompra;
    }

    public Map<Integer, CriterioAnalisisLaboratorio> getCriteriosAnalisisLaboratorio() {
        return criteriosAnalisisLaboratorio;
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

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }
    
    public void modificarOrdenDeProduccion(OrdenDeProduccion unaOrdenDeProduccion, ArrayList criterios) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia {    
        Iterator criteriosADesvincular = unaOrdenDeProduccion.getCriteriosDeAnalisisAsociados().iterator();
        while (criteriosADesvincular.hasNext()){
            CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) criteriosADesvincular.next();
            unCriterio.removerOrdenDeProduccion(unaOrdenDeProduccion);
        }
        unaOrdenDeProduccion.removerTodosLosCriterios();
        Iterator criteriosImplicados = criterios.iterator();
        while (criteriosImplicados.hasNext()){
            CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) criteriosImplicados.next();
            unaOrdenDeProduccion.agregarCriterioLaboratorio(unCriterio);
        }
        this.persistencia.modificarObjeto(unaOrdenDeProduccion);
        
    }
    
    public void registrarOrdenDeProduccion(Calendar unaFechaOrigen, float cantidadAProducir, String unidadDeMedida, Calendar fechaEntregaProductoTerminado, String unaDescripcion, ArrayList criterios) throws ExcepcionCargaParametros, SQLException {
        if (unaFechaOrigen == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de origen.");
        if (fechaEntregaProductoTerminado == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de entrega de producto terminado.");
        if (fechaEntregaProductoTerminado.before(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha de entrega del producto terminado no puede anteceder la fecha actual.");
        if (cantidadAProducir <= 0)
            throw new ExcepcionCargaParametros("Por favor, ingrese una cantidad a Producir positiva.");
        OrdenDeProduccion unaOrdenDeProduccion = new OrdenDeProduccion(unaFechaOrigen, cantidadAProducir, unidadDeMedida, fechaEntregaProductoTerminado, unaDescripcion, this.usuarioActivo);
        Iterator criteriosImplicados = criterios.iterator();
        while (criteriosImplicados.hasNext()){
            CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) criteriosImplicados.next();
            unaOrdenDeProduccion.agregarCriterioLaboratorio(unCriterio);
            unCriterio.agregarOrdenDeProduccion(unaOrdenDeProduccion);
        }
        this.persistencia.persistirObjeto(unaOrdenDeProduccion);
        this.ordenesProduccion.put(unaOrdenDeProduccion.getId(), unaOrdenDeProduccion);
        
    }
    
    
    public void registrarCriterioDeAnalisisDeLaboratorio(
            String unNombre, String unaDescripcion, Calendar fechaOrigen,
            String unTipoDeLote, String puntosNegros, String torrada, String color, String aroma, String tacto, String degustacion,  
            String porcentajeSemillaLimiteInferior, String porcentajeSemillaLimiteSuperior, 
            String porcentajePaloLimiteInferior, String porcentajePaloLimiteSuperior,
            String porcentajePolvoLimiteInferior, String porcentajePolvoLimiteSuperior, 
            String porcentajeHumedadLimiteInferior, String porcentajeHumedadLimiteSuperior, 
            String porcentajeHojaLimiteInferior, String porcentajeHojaLimiteSuperior) throws ExcepcionCargaParametros, SQLException{
        
        if (puntosNegros.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar si la muestra posee puntos negros.");
        if (torrada.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar si la muestra es torrada.");
        if (color.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar el color de la muestra.");
        if (aroma.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar el color de la muestra.");
        if (tacto.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar el tacto registrado en la muestra.");
        if (degustacion.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar si hubo degustacion de la muestra.");
        if (unTipoDeLote.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar el tipo de lote que acepta este criterio.");
        
        if (this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
        
        if (fechaOrigen == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una fecha de origen valida.");
        if (fechaOrigen.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha origen no puede exceder la fecha actual.");
        
        float pSemillaLimiteInferior = convertirAFlotante(porcentajeSemillaLimiteInferior,"límite inferior de porcentaje de semilla");
        float pSemillaLimiteSuperior = convertirAFlotante(porcentajeSemillaLimiteSuperior,"límite superior de porcentaje de semilla");
        float pPaloLimiteInferior = convertirAFlotante(porcentajePaloLimiteInferior, "límite inferior de porcentaje de palo");
        float pPaloLimiteSuperior = convertirAFlotante(porcentajePaloLimiteSuperior, "límite superior de porcentaje de palo");
        float pPolvoLimiteInferior = convertirAFlotante(porcentajePolvoLimiteInferior, "límite inferior de porcentaje de polvo");
        float pPolvoLimiteSuperior = convertirAFlotante(porcentajePolvoLimiteSuperior, "límite superior de porcentaje de polvo");
        float pHumedadLimiteInferior = convertirAFlotante(porcentajeHumedadLimiteInferior, "límite inferior de porcentaje de humedad");
        float pHumedadLimiteSuperior = convertirAFlotante(porcentajeHumedadLimiteSuperior, "límite superior de porcentaje de humedad");
        float pHojaLimiteInferior = convertirAFlotante(porcentajeHojaLimiteInferior, "límite inferior de porcentaje de hojas");
        float pHojaLimiteSuperior = convertirAFlotante(porcentajeHojaLimiteSuperior, "límite superior de porcentaje de hojas");
        
        Validaciones.validarIntervaloDePorcentajesEnCriterio(pSemillaLimiteInferior, pSemillaLimiteSuperior, "porcentaje de semilla");
        Validaciones.validarIntervaloDePorcentajesEnCriterio(pPaloLimiteInferior, pPaloLimiteSuperior, "porcentaje de palo");
        Validaciones.validarIntervaloDePorcentajesEnCriterio(pPolvoLimiteInferior, pPolvoLimiteSuperior, "porcentaje de polvo");
        Validaciones.validarIntervaloDePorcentajesEnCriterio(pHumedadLimiteInferior, pHumedadLimiteSuperior, "porcentaje de humedad");
        Validaciones.validarIntervaloDePorcentajesEnCriterio(pHojaLimiteInferior, pHojaLimiteSuperior, "porcentaje de hoja");
        
        CriterioAnalisisLaboratorio unCriterio = new CriterioAnalisisLaboratorio(unNombre.toUpperCase(), unaDescripcion.toUpperCase(), fechaOrigen, 
                pSemillaLimiteInferior, pSemillaLimiteSuperior, 
                pPaloLimiteInferior, pPaloLimiteSuperior, 
                pPolvoLimiteInferior, pPolvoLimiteSuperior, 
                pHojaLimiteInferior, pHojaLimiteSuperior, 
                pHumedadLimiteInferior, pHumedadLimiteSuperior, unTipoDeLote, puntosNegros, torrada, color, aroma, tacto, degustacion);
        persistencia.persistirObjeto(unCriterio);
        this.getCriteriosAnalisisLaboratorio().put(unCriterio.getId(), unCriterio);
    }
    
    public void registrarAnalisisDeLaboratorio(String puntosNegros, String torrada, String color, String aroma, String tacto, String degustacion, 
            Calendar fechaOrigen, Lote unLote, Equipamiento unLaboratorio, String porcentajePalo, String porcentajeSemilla, String porcentajePolvo, 
            String porcentajeHumedad, String porcentajeHoja, CriterioAnalisisLaboratorio unCriterio, String unComentario, OrdenDeCompra unaOrdenDeCompra) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (puntosNegros.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar si la muestra posee puntos negros.");
        if (torrada.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar si la muestra es torrada.");
        if (color.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar el color de la muestra.");
        if (aroma.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar el color de la muestra.");
        if (tacto.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar el tacto registrado en la muestra.");
        if (degustacion.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe indicar si hubo degustacion de la muestra.");
            
        if (this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
    
        if (unLote == null && unaOrdenDeCompra == null)
            throw new ExcepcionCargaParametros("Debe ingresar al menos un lote o una orden de compra asociada.");
        
        if (unLote != null && unaOrdenDeCompra != null)
            throw new ExcepcionCargaParametros("No puede ingresarse un lote y una orden de compra al mismo tiempo.");
        
        if (unaOrdenDeCompra != null && !unaOrdenDeCompra.seEncuentraRegular())
            throw new ExcepcionCargaParametros("La ordne de compra seleccionada no se encuentra en estado regular.");
        if (unLote != null && !unLote.estaRegular())
            throw new ExcepcionCargaParametros("El lote seleccionado no se encuentra en estado regular.");
        
        
        if (fechaOrigen == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una fecha de origen valida.");
        if (fechaOrigen.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha origen no puede exceder la fecha actual.");

        if (unLaboratorio == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un laboratorio.");
        if (!(unLaboratorio instanceof Laboratorio))
            throw new ExcepcionCargaParametros("Debe seleccionar un laboratorio para registrar un analisis.");
        
        
        if (unCriterio == null)
            throw new ExcepcionCargaParametros("Debe seleccionar un criterio de analisis.");

        if (unLote != null && !unLote.getTipo_Lote().equals(unCriterio.getTipoLote()))
            throw new ExcepcionCargaParametros("No se puede registrar un analisis para el lote de tipo: "+unLote.getTipo_Lote()+" ya que el criterio es para lotes de tipo "+unCriterio.getTipoLote()+".");
        
        if (!Validaciones.esUnNumeroFraccionarioValido(porcentajePalo))
            throw new ExcepcionCargaParametros("El porcentaje de palo no posee un formato valido (Utilice solo numeros y una coma)");
        if (!Validaciones.esUnNumeroFraccionarioValido(porcentajeSemilla))
            throw new ExcepcionCargaParametros("El porcentaje de semilla no posee un formato valido (Utilice solo numeros y una coma)");
        if (!Validaciones.esUnNumeroFraccionarioValido(porcentajePolvo))
            throw new ExcepcionCargaParametros("El porcentaje de polvo no posee un formato valido (Utilice solo numeros y una coma)");
        if (!Validaciones.esUnNumeroFraccionarioValido(porcentajeHumedad))
            throw new ExcepcionCargaParametros("El porcentaje de humedad no posee un formato valido (Utilice solo numeros y una coma)");
        if (!Validaciones.esUnNumeroFraccionarioValido(porcentajeHoja))
            throw new ExcepcionCargaParametros("El porcentaje de hoja no posee un formato valido (Utilice solo numeros y una coma)");
        
        
        
        porcentajePalo = porcentajePalo.replace(".", "");
        porcentajePalo = porcentajePalo.replace(",", ".");
        porcentajeSemilla = porcentajeSemilla.replace(".", "");
        porcentajeSemilla = porcentajeSemilla.replace(",", ".");
        porcentajePolvo = porcentajePolvo.replace(".", "");
        porcentajePolvo = porcentajePolvo.replace(",", ".");
        porcentajeHumedad = porcentajeHumedad.replace(".", "");
        porcentajeHumedad = porcentajeHumedad.replace(",", ".");
        porcentajeHoja = porcentajeHoja.replace(".", "");
        porcentajeHoja = porcentajeHoja.replace(",", ".");
        
        float unPorcentajePalo = Float.parseFloat(porcentajePalo);
        float unPorcentajeSemilla = Float.parseFloat(porcentajeSemilla);
        float unPorcentajePolvo = Float.parseFloat(porcentajePolvo);
        float unPorcentajeHumedad = Float.parseFloat(porcentajeHumedad);
        float unPorcentajeHoja = Float.parseFloat(porcentajeHoja);
        
        if (unPorcentajePalo < 0 || unPorcentajePalo >100)
            throw new ExcepcionCargaParametros("El porcentaje de palo debe estar en un rango de 0% a 100%.");
        if (unPorcentajeSemilla < 0 || unPorcentajeSemilla >100)
            throw new ExcepcionCargaParametros("El porcentaje de semilla debe estar en un rango de 0% a 100%.");
        if (unPorcentajePolvo < 0 || unPorcentajePolvo >100)
            throw new ExcepcionCargaParametros("El porcentaje de polvo debe estar en un rango de 0% a 100%.");
        if (unPorcentajeHumedad < 0 || unPorcentajeHumedad >100)
            throw new ExcepcionCargaParametros("El porcentaje de humedad debe estar en un rango de 0% a 100%.");
        if (unPorcentajeHoja < 0 || unPorcentajeHoja >100)
            throw new ExcepcionCargaParametros("El porcentaje de Palo debe estar en un rango de 0% a 100%.");
        if ((unPorcentajePalo + unPorcentajeSemilla + unPorcentajePolvo + unPorcentajeHoja) !=100)
            throw new ExcepcionCargaParametros("El porcentaje total de palo, semilla, polvo y hoja debe igualar a 100 % (Actualmente es de "+(unPorcentajePalo + unPorcentajeSemilla + unPorcentajePolvo + unPorcentajeHoja));
        
        AnalisisLaboratorio unAnalisis = null;
        if (unLote != null){
        unAnalisis = new AnalisisLaboratorio(puntosNegros, torrada, color, aroma, tacto, degustacion, unPorcentajePalo, unPorcentajePolvo, unPorcentajeSemilla, unPorcentajeHoja, unPorcentajeHumedad, unLote, (Laboratorio) unLaboratorio, unCriterio, usuarioActivo, unComentario.toUpperCase());
        unLote.agregarAnalisis(unAnalisis);
        unLote.getOrdenDeProduccionAsociada().agregarEvento(unAnalisis);
        }else if (unaOrdenDeCompra != null){
            unAnalisis = new AnalisisLaboratorio(puntosNegros, torrada, color, aroma, tacto, degustacion, unPorcentajePalo, unPorcentajePolvo, unPorcentajeSemilla, unPorcentajeHoja, unPorcentajeHumedad, (Laboratorio) unLaboratorio, unCriterio, usuarioActivo, unComentario.toUpperCase(), unaOrdenDeCompra);
            unaOrdenDeCompra.agregarAnalisisDeLaboratorio(unAnalisis);
            unaOrdenDeCompra.getOrdenDeProduccionAsociada().agregarEvento(unAnalisis);
        }
        
        persistencia.persistirObjeto(unAnalisis);
        ((Laboratorio)unLaboratorio).agregarAnalisis(unAnalisis);
        unCriterio.agregarAnalisis(unAnalisis);
        this.getAnalisisLaboratorio().put(unAnalisis.getId(), unAnalisis);
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
    public void registrarEquipamiento(String unTipoEquipamiento, String unNombre, String unaDireccion, Calendar unaFechaAdquisicion, Calendar unaFechaUltimoMantenimiento, String unaCapacidadMaxima, String unaUnidadDeMedida, Bascula unaBasculaAsociada, String unaDuracionMaximaEstacionamiento) throws SQLException, ExcepcionCargaParametros{
        
        float capacidadMaxima = 0;
        float duracionMaximaEstacionamiento = 0;
        if (!(unTipoEquipamiento.equals("Bascula") || unTipoEquipamiento.equals("Laboratorio")) && unaBasculaAsociada == null)
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
        
        
        if (!unTipoEquipamiento.equals("Laboratorio") && !Validaciones.esUnNumeroFraccionarioValido(unaCapacidadMaxima))
            throw new ExcepcionCargaParametros("Verifique la capacidad maxima ingresada (Utilice solo numeros y una coma).");
        if (!unTipoEquipamiento.equals("Laboratorio")){
            unaCapacidadMaxima = unaCapacidadMaxima.replace(".", "");
            unaCapacidadMaxima = unaCapacidadMaxima.replace(",", ".");    
            capacidadMaxima = Float.parseFloat(unaCapacidadMaxima);
            if (capacidadMaxima <= 0)
                throw new ExcepcionCargaParametros("Por favor ingrese una capacidad máxima positiva.");
        }
        if (unTipoEquipamiento.equals("Camara de estacionamiento acelerado") && !Validaciones.esUnNumeroFraccionarioValido(unaDuracionMaximaEstacionamiento))
            throw new ExcepcionCargaParametros("Verifique la duración máxima ingresada (Utilice solo numeros y una coma).");
        if (unTipoEquipamiento.equals("Camara de estacionamiento acelerado")){
            unaDuracionMaximaEstacionamiento = unaDuracionMaximaEstacionamiento.replace(".", "");
            unaDuracionMaximaEstacionamiento = unaDuracionMaximaEstacionamiento.replace(",", ".");
            duracionMaximaEstacionamiento = Float.parseFloat(unaDuracionMaximaEstacionamiento);    
            if (duracionMaximaEstacionamiento <= 0)
                throw new ExcepcionCargaParametros("Por favor ingrese una duracion maxima de estacionamiento valida.");
        }
        
        
        
        
        switch (unTipoEquipamiento){
            case "Molino":    
                Molino unMolino = new Molino(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, capacidadMaxima, unaUnidadDeMedida, unaBasculaAsociada);
                unaBasculaAsociada.agregarEquipamiento(unMolino);
                this.persistencia.persistirObjeto(unMolino);
                this.equipamientos.put(unMolino.getId(), unMolino);
                break;
            case "Bascula":
                Bascula unaBascula = new Bascula(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, capacidadMaxima, unaUnidadDeMedida);
                this.persistencia.persistirObjeto(unaBascula);
                this.equipamientos.put(unaBascula.getId(), unaBascula);
                break;
            case "Camara de estacionamiento acelerado":
                CamaraEstacionamiento unaCamara = new CamaraEstacionamiento(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, capacidadMaxima, unaUnidadDeMedida, duracionMaximaEstacionamiento, unaBasculaAsociada);
                unaBasculaAsociada.agregarEquipamiento(unaCamara);
                this.persistencia.persistirObjeto(unaCamara);
                this.equipamientos.put(unaCamara.getId(), unaCamara);
                break;
            case "Deposito":
                Deposito unDeposito = new Deposito(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, capacidadMaxima, unaUnidadDeMedida, unaBasculaAsociada);
                unaBasculaAsociada.agregarEquipamiento(unDeposito);
                this.persistencia.persistirObjeto(unDeposito);
                this.equipamientos.put(unDeposito.getId(), unDeposito);
                break;
            case "Laboratorio":
                Laboratorio unLaboratorio = new Laboratorio(unNombre.toUpperCase(), unaDireccion.toUpperCase(), unaFechaAdquisicion, unaFechaUltimoMantenimiento, capacidadMaxima, unaUnidadDeMedida);
                this.persistencia.persistirObjeto(unLaboratorio);
                this.equipamientos.put(unLaboratorio.getId(), unLaboratorio);
                break;
        }
        
                
    }
    
    public void anularAnalisisDeLaboratorio(AnalisisLaboratorio unAnalisis) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        
        if (unAnalisis.getLoteImplicado() != null && unAnalisis.getLoteImplicado().poseeUnoOMasEventosRegularesPosterioresA(unAnalisis))
            throw new ExcepcionCargaParametros("No se puede anular el analisis de un lote que registre uno o mas eventos (Movimientos, moliendas, analisis, etc) posteriores regulares.");
        unAnalisis.anular();
        persistencia.modificarObjeto(unAnalisis);
    }   
    public void anularEstacionamiento(Estacionamiento unEstacionamiento) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        
        if (unEstacionamiento.poseeUnoOMasEventosRegularesPosteriores())
            throw new ExcepcionCargaParametros("No se puede dar de baja un estacionamiento que tenga un lote que registre uno o mas eventos (Movimientos, moliendas, analisis, etc) posteriores regulares.");
        unEstacionamiento.anular();
        persistencia.modificarObjeto(unEstacionamiento);
        Iterator lotesAEstacionar = unEstacionamiento.getLotesImplicados().iterator();
        while (lotesAEstacionar.hasNext()){
            Lote unLote = (Lote) lotesAEstacionar.next();
            unLote.anularEstacionamiento();
            unLote.agregarEstacionamiento(unEstacionamiento);
            persistencia.modificarObjeto(unLote);
        }
    }       
    
    public void registrarIngresoMateriaPrima(Calendar fechaOrigen, LocalTime horaEntrada, LocalTime horaSalida, String unidadTransporte, String cantidadUnidades, String unidadMedidaPeso, String pesoEntrada, String pesoSalida, String nHojaRuta, String nRemito, String nPrecinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Equipamiento destino, OrdenDeCompra unaOrdenDeCompraAsociada,Proveedor unProveedorDeServicioDeTransporte, AnalisisLaboratorio unAnalisisLaboratorio, String unTipoDeLote) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        
        if (this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
        
        if (unTipoDeLote.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe seleccionar un tipo de lote.");
        
        if (fechaOrigen == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una fecha de origen valida.");
        if (fechaOrigen.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha origen no puede exceder la fecha actual.");
        
        if (!Validaciones.esUnNumeroEnteroValido(cantidadUnidades))
            throw new ExcepcionCargaParametros("Verifique la cantidad de unidades ingresadas.");
        
        if (unaOrdenDeCompraAsociada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de compra asociada.");
        if (!unaOrdenDeCompraAsociada.seEncuentraRegular())
            throw new ExcepcionCargaParametros("La orden de compra seleccionada no se encuentra en estado regular.");
        if (!unaOrdenDeCompraAsociada.poseeProveedorAsociado())
            throw new ExcepcionCargaParametros("La orden de compra seleccionada no posee un proveedor asociado.");
        
        if (unProveedorDeServicioDeTransporte == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un proveedor de servicio de transporte.");
        if (!unProveedorDeServicioDeTransporte.seEncuentraActivo())
            throw new ExcepcionCargaParametros("El proveedor seleccionado no se encuentra activo.");
        
        if (unAnalisisLaboratorio == null)
            throw new ExcepcionCargaParametros("Debe seleccionar un analisis de laboratorio.");
        if (!unAnalisisLaboratorio.estaAprobado())
            throw new ExcepcionCargaParametros("No se puede registrar un ingreso de materia prima sobre un analisis de laboratorio que no este aprobado.");
        if (!unAnalisisLaboratorio.estaRegular())
            throw new ExcepcionCargaParametros("No se puede registrar un ingreso de materia prima sobre un analisis de laboratorio que no este regular.");
        if (!unaOrdenDeCompraAsociada.equals(unAnalisisLaboratorio.getOrdenDeCompraImplicada()))
            throw new ExcepcionCargaParametros("No se puede registrar un ingreso de materia prima sobre un analisis de laboratorio que no sea de la orden de compra especificada.");
        
        if (destino == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un equipamiento de destino.");
        if (destino instanceof Bascula)
            throw new ExcepcionCargaParametros("El destino no puede ser una bascula");
        if (!destino.getBasculaAsociada().estaActivo())
            throw new ExcepcionCargaParametros("El destino no posee una bascula activa.");
        
        if (horaEntrada==null)
            throw new ExcepcionCargaParametros("No se ha ingresado una hora de entrada.");
        if (horaSalida==null)
            throw new ExcepcionCargaParametros("No se ha ingresado una hora de salida.");
        
        if (nHojaRuta.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado un número de hoja de ruta.");
        if (nRemito.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado un número de remito.");
        if (nPrecinto.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado un número de precinto.");
        if (nombreConductor.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado el nombre del conductor.");
        
        if (patenteChasis.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado la patente del chasis.");
        if (!Validaciones.esUnaPatenteValida(patenteChasis))
            throw new ExcepcionCargaParametros("No se ha ingresado una patente de chasis valida (XXX-XXX) o (XX-XXX-XX).");
                
        if (patenteAcoplado.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado la patente del acoplado.");
        if (!Validaciones.esUnaPatenteValida(patenteAcoplado))
            throw new ExcepcionCargaParametros("No se ha ingresado una patente de acoplado valida (XXX-XXX) o (XX-XXX-XX).");
        
        if (unidadTransporte.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado una unidad de transporte");
        if (unidadMedidaPeso.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado una unidad de medida");
        if (!Validaciones.esUnNumeroFraccionarioValido(pesoEntrada))
            throw new ExcepcionCargaParametros("El peso de entrada no posee un formato valido (Utilice solo numeros y una coma)");
        if (!Validaciones.esUnNumeroFraccionarioValido(pesoSalida))
            throw new ExcepcionCargaParametros("El peso de salida no posee un formato valido (Utilice solo numeros y una coma)");
        if (horaEntrada.isAfter(horaSalida))
            throw new ExcepcionCargaParametros("La hora de entrada no puede exceder la hora de salida.");
        
        pesoEntrada = pesoEntrada.replace(".", "");
        pesoEntrada = pesoEntrada.replace(",", ".");
        pesoSalida = pesoSalida.replace(".", "");
        pesoSalida = pesoSalida.replace(",", ".");
        float unPesoEntrada = Float.parseFloat(pesoEntrada);
        float unPesoSalida = Float.parseFloat(pesoSalida);
        float pesoNeto = unPesoEntrada - unPesoSalida;
        if (pesoNeto <= 0)
            throw new ExcepcionCargaParametros("El peso de salida no puede exceder el peso de entrada.");
        if (!Validaciones.esUnNumeroEnteroValido(cantidadUnidades))
            throw new ExcepcionCargaParametros("La cantidad de unidades ingresadas no es valida (Solo números).");
        int unaCantidadDeUnidades = Integer.parseInt(cantidadUnidades);
        if (unaCantidadDeUnidades <= 0)
            throw new ExcepcionCargaParametros("La cantidad de unidades ingresadas de "+unidadTransporte+" no puede ser negativa.");
        if (unPesoEntrada <=0 || unPesoSalida <= 0)
            throw new ExcepcionCargaParametros("El peso de entrada o de salida no pueden ser 0 (cero) o negativos.");
        
        if (Organizacion.convertirUnidadPeso(unidadMedidaPeso, pesoNeto, unaOrdenDeCompraAsociada.getUnidadDeMedida()) > unaOrdenDeCompraAsociada.getCantidadRestanteARecibir())
            throw new ExcepcionCargaParametros("No se puede registrar un ingreso que supere lo pactado en la orden de compra.");
        
        if (!destino.puedeAlbergar(pesoNeto, unidadMedidaPeso))
            throw new ExcepcionCargaParametros("El destino donde residirá el lote no posee espacio suficiente.");
        
        if (!unAnalisisLaboratorio.poseeTipoDeLote(unTipoDeLote))
            throw new ExcepcionCargaParametros("El analisis de laboratorio ingresado es para lotes de tipo "+unAnalisisLaboratorio.getCriterioAsociado().getTipoLote()+" pero se esta registrando el tipo de lote "+unTipoDeLote);
        
        Lote unLote = new Lote(pesoNeto, unTipoDeLote, unidadMedidaPeso, fechaOrigen, unaOrdenDeCompraAsociada, destino);
        persistencia.persistirObjeto(unLote);
        unLote.setEtiqueta(( new SimpleDateFormat( "yyyyMMdd" ) ).format( fechaOrigen.getTime() )+"-"+unLote.getId());
        MovimientoInternoMateriaPrima unMovimiento = new MovimientoInternoMateriaPrima(this.getUsuarioActivo(), horaEntrada, horaSalida, unidadTransporte, unaCantidadDeUnidades, unidadMedidaPeso, unPesoEntrada, unPesoSalida, nHojaRuta, nRemito, nPrecinto, nombreConductor, patenteChasis, patenteAcoplado, unLote, null, destino, unProveedorDeServicioDeTransporte);
        destino.agregarLote(unLote);
        persistencia.persistirObjeto(unMovimiento);
        unLote.agregarMovimiento(unMovimiento);
        unLote.asignarUltimoMovimiento(unMovimiento);
        unLote.getOrdenDeProduccionAsociada().agregarEvento(unMovimiento);
        persistencia.modificarObjeto(unLote);//ACTUALIZAR EN LA BASE DE DATOS EL MOVIMENTO INTERNO ASOCIADO AL INGRESO Y SU ETIQUETA.
        unaOrdenDeCompraAsociada.agregarLote(unLote);
        this.lotes.put(unLote.getId(), unLote);
        this.movimientos.put(unMovimiento.getId(), unMovimiento);
        this.movimientosDeIngreso.put(unMovimiento.getId(), unMovimiento);
        
        unLote.agregarAnalisis(unAnalisisLaboratorio);
        unAnalisisLaboratorio.setLoteImplicado(unLote);
        persistencia.modificarObjeto(unAnalisisLaboratorio);
    }
    
    public void anularIngresoDeMateriaPrima(MovimientoInternoMateriaPrima unMovimiento) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unMovimiento == null)
            throw new ExcepcionCargaParametros("No se selecciono ningun movimiento para anular");
        Lote unLoteImplicado = unMovimiento.getLoteAsociado();
        if (unLoteImplicado.poseeUnoOMasEventosRegularesPosterioresA(unMovimiento))
            throw new ExcepcionCargaParametros("No se puede dar de baja un ingreso cuyo lote tenga eventos posteriores (Movimientos o transformaciones) en estados regulares.");
        unMovimiento.anular();
        unLoteImplicado.anular();
        persistencia.modificarObjeto(unMovimiento);
        persistencia.modificarObjeto(unLoteImplicado);
        //Desvinculacion del analisis de laboratorio empleado en el ingreso.
        unLoteImplicado.getAnalisisDeIngreso().desvincularseDeLote();
        persistencia.modificarObjeto(unLoteImplicado.getAnalisisDeIngreso());
        
    }
    
    public void anularMovimientoDeMateriaPrima(MovimientoInternoMateriaPrima unMovimiento) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        Lote unLoteImplicado = unMovimiento.getLoteAsociado();
        if (!unMovimiento.poseeEquipamientoOrigen())
            throw new ExcepcionCargaParametros("No se puede anular un movimiento de ingreso. Dirijase a gestión de Ingresos para hacerlo.");
        if (unLoteImplicado.poseeUnoOMasEventosRegularesPosterioresA(unMovimiento))
            throw new ExcepcionCargaParametros("No se puede dar de baja un ingreso cuyo lote tenga eventos posteriores (Movimientos o transformaciones) en estados regulares.");
        unMovimiento.anular();
        unMovimiento.getEquipamientoDestino().removerLote(unLoteImplicado);
        MovimientoInternoMateriaPrima nuevoUltimoMovimiento = unLoteImplicado.calcularUltimoMovimientoRegular();
        unLoteImplicado.setEquipamientoDondeReside(nuevoUltimoMovimiento.getEquipamientoDestino());
        nuevoUltimoMovimiento.getEquipamientoDestino().agregarLote(unLoteImplicado);
        unLoteImplicado.setUltimoMovimientoRegular(nuevoUltimoMovimiento);
        persistencia.modificarObjeto(unMovimiento);
        persistencia.modificarObjeto(unLoteImplicado);
    }    
    
    
    
public void registrarMovmimiento(Calendar fechaOrigen, LocalTime horaEntrada, LocalTime horaSalida, String unidadTransporte, String cantidadUnidades, String unidadMedidaPeso, String pesoEntrada, String pesoSalida, String nHojaRuta, String nRemito, String nPrecinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Lote unLote, Equipamiento destino, Proveedor unProveedorDeServicioDeTransporte) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        
        if (this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
        
        if (fechaOrigen == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una fecha de origen valida.");
        if (fechaOrigen.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha origen no puede exceder la fecha actual.");
        
        if (!Validaciones.esUnNumeroEnteroValido(cantidadUnidades))
            throw new ExcepcionCargaParametros("Verifique la cantidad de unidades ingresadas.");
        
        if (unProveedorDeServicioDeTransporte == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un proveedor de servicio de transporte.");
        if (!unProveedorDeServicioDeTransporte.seEncuentraActivo())
            throw new ExcepcionCargaParametros("El proveedor seleccionado no se encuentra activo.");
        
        if (destino == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un equipamiento de destino.");
        if (destino instanceof Bascula)
            throw new ExcepcionCargaParametros("El destino no puede ser una bascula");
        if (!destino.getBasculaAsociada().estaActivo())
            throw new ExcepcionCargaParametros("El destino no posee una bascula activa.");
        
        if (unLote.getEquipamientoDondeReside() == null)
            throw new ExcepcionCargaParametros("El lote seleccionado no reside en ningun equipamiento.");
        
        if (unLote.getEquipamientoDondeReside().equals(destino))
            throw new ExcepcionCargaParametros("El destino y el origen no pueden ser el mismo lugar.");
        
        if (horaEntrada==null)
            throw new ExcepcionCargaParametros("No se ha ingresado una hora de entrada.");
        if (horaSalida==null)
            throw new ExcepcionCargaParametros("No se ha ingresado una hora de salida.");
        
        if (nHojaRuta.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado un número de hoja de ruta.");
        if (nRemito.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado un número de remito.");
        if (nPrecinto.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado un número de precinto.");
        if (nombreConductor.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado el nombre del conductor.");
        
        if (patenteChasis.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado la patente del chasis.");
        if (!Validaciones.esUnaPatenteValida(patenteChasis))
            throw new ExcepcionCargaParametros("No se ha ingresado una patente de chasis valida (XXX-XXX) o (XX-XXX-XX).");
                
        if (patenteAcoplado.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado la patente del acoplado.");
        if (!Validaciones.esUnaPatenteValida(patenteAcoplado))
            throw new ExcepcionCargaParametros("No se ha ingresado una patente de acoplado valida (XXX-XXX) o (XX-XXX-XX).");
        
        if (unidadTransporte.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado una unidad de transporte");
        if (unidadMedidaPeso.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado una unidad de medida");
        if (!Validaciones.esUnNumeroFraccionarioValido(pesoEntrada))
            throw new ExcepcionCargaParametros("El peso de entrada no posee un formato valido (Utilice solo numeros y una coma)");
        if (!Validaciones.esUnNumeroFraccionarioValido(pesoSalida))
            throw new ExcepcionCargaParametros("El peso de salida no posee un formato valido (Utilice solo numeros y una coma)");
        if (horaEntrada.isAfter(horaSalida))
            throw new ExcepcionCargaParametros("La hora de entrada no puede exceder la hora de salida.");
        
        pesoEntrada = pesoEntrada.replace(".", "");
        pesoEntrada = pesoEntrada.replace(",", ".");
        pesoSalida = pesoSalida.replace(".", "");
        pesoSalida = pesoSalida.replace(",", ".");
        float unPesoEntrada = Float.parseFloat(pesoEntrada);
        float unPesoSalida = Float.parseFloat(pesoSalida);
        float pesoNeto = unPesoEntrada - unPesoSalida;
        if (pesoNeto <= 0)
            throw new ExcepcionCargaParametros("El peso de salida no puede exceder el peso de entrada.");
        if (!Validaciones.esUnNumeroEnteroValido(cantidadUnidades))
            throw new ExcepcionCargaParametros("La cantidad de unidades ingresadas no es valida (Solo números).");
        int unaCantidadDeUnidades = Integer.parseInt(cantidadUnidades);
        if (unaCantidadDeUnidades <= 0)
            throw new ExcepcionCargaParametros("La cantidad de unidades ingresadas de "+unidadTransporte+" no puede ser negativa.");
        if (unPesoEntrada <=0 || unPesoSalida <= 0)
            throw new ExcepcionCargaParametros("El peso de entrada o de salida no pueden ser 0 (cero) o negativos.");
        
        if (!destino.puedeAlbergar(pesoNeto, unidadMedidaPeso))
            throw new ExcepcionCargaParametros("El destino donde residirá el lote no posee espacio suficiente.");
        
        
        
        MovimientoInternoMateriaPrima unMovimiento = new MovimientoInternoMateriaPrima(this.getUsuarioActivo(), horaEntrada, horaSalida, unidadTransporte, unaCantidadDeUnidades, unidadMedidaPeso, unPesoEntrada, unPesoSalida, nHojaRuta, nRemito, nPrecinto, nombreConductor, patenteChasis, patenteAcoplado, unLote, unLote.getEquipamientoDondeReside(), destino, unProveedorDeServicioDeTransporte);
        persistencia.persistirObjeto(unMovimiento);
        unLote.getEquipamientoDondeReside().removerLote(unLote);
        destino.agregarLote(unLote);
        
        unLote.setEquipamientoDondeReside(destino);
        unLote.agregarMovimiento(unMovimiento);
        unLote.asignarUltimoMovimiento(unMovimiento);
        
        unLote.getOrdenDeProduccionAsociada().agregarEvento(unMovimiento);
        
        persistencia.modificarObjeto(unLote);//Ahora el lote tiene como clave foranea al equipamiento destino.
        
        this.movimientos.put(unMovimiento.getId(), unMovimiento);
    }

    public void registrarEstacionamiento (ArrayList<Lote> lotes, Equipamiento unEquipamiento, Calendar unaFechaOrigen, String duracion) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        
        if (lotes.isEmpty())
            throw new ExcepcionCargaParametros("No se selecciono ningún lote.");
        if (!unEquipamiento.poseeLotes(lotes))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados estan presentes en ese equipamiento.");
        if (!Validaciones.sonLotesDeYerbaCanchadaVerde(lotes))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados son de yerba canchada verde.");
        if (!Validaciones.sonLotesRegulares(lotes))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados estan en estado regular.");
        if (!Validaciones.sonLotesAnalizados(lotes))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados poseen un analisis de laboratorio aprobado.");
        
        if (unEquipamiento == null)
            throw new ExcepcionCargaParametros("No se selecciono una camara de estacionamiento.");
        if (!(unEquipamiento instanceof CamaraEstacionamiento)) 
            throw new ExcepcionCargaParametros("Debe seleccionar una camara de estacionamiento para realizar un estacionamiento.");

        if (unaFechaOrigen == null)
            throw new ExcepcionCargaParametros("No se especificó la fecha de origen.");
        if (unaFechaOrigen.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha de origen no puede ser posterior a la fecha actual.");
        if (!Validaciones.esUnNumeroEnteroValido(duracion))
            throw new ExcepcionCargaParametros("Verifique la cantidad de días ingresados (Solo numeros enteros).");
        int unaDuracion = Integer.parseInt(duracion);
        if (unaDuracion<0)
            throw new ExcepcionCargaParametros("La duración de estacionamiento no puede ser negativa.");
        if (unaDuracion>((CamaraEstacionamiento)unEquipamiento).getDuracionMaximaEstacionamiento())
            throw new ExcepcionCargaParametros("La duración de estacionamiento no puede exceder la duracion maxima de estacionamiento de la camara.");
        
        Calendar fechaExtraccion = (Calendar) unaFechaOrigen.clone();
        fechaExtraccion.add(Calendar.DAY_OF_YEAR, unaDuracion);
        
        
        Estacionamiento unEstacionamiento = new Estacionamiento(fechaExtraccion, lotes, unEquipamiento, this.usuarioActivo);
        
        
        persistencia.persistirObjeto(unEstacionamiento);
        this.transformaciones.put(unEstacionamiento.getId(), unEstacionamiento);
        this.estacionamientos.put(unEstacionamiento.getId(), unEstacionamiento);
        Iterator lotesAEstacionar = lotes.iterator();
        while (lotesAEstacionar.hasNext()){
            Lote unLote = (Lote) lotesAEstacionar.next();
            unLote.estacionar();
            unLote.agregarEstacionamiento(unEstacionamiento);
            unLote.getOrdenDeProduccionAsociada().agregarEvento(unEstacionamiento);
            persistencia.modificarObjeto(unLote);
        }
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
    
    public ArrayList filtrarAnalisisDeLaboratorio(Map<String, Boolean> criterios,
            Lote unLoteSeleccionado, String unComentario, String tipoLoteSeleccionado,
            CriterioAnalisisLaboratorio unCriterioSeleccionado, String estadoSeleccionado, 
            Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior,
            OrdenDeProduccion unaOrdenProduccionSeleccionada, Equipamiento unLaboratorio, OrdenDeCompra unaOrdenDeCompra,
            boolean poseeLote) throws ExcepcionCargaParametros {
        
        ArrayList retorno = new ArrayList();
        
        Boolean criterioLote = criterios.get("lote");
        Boolean criterioComentario = criterios.get("comentario");
        Boolean criterioTipoLote = criterios.get("tipoLote");
        Boolean criterioCriterioUtilizado = criterios.get("criterioImplicado");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioLaboratorio = criterios.get("laboratorio");
        Boolean criterioOrdenDeCompra = criterios.get("ordenCompra");
        Boolean criterioPoseeLotesAsociado = criterios.get("poseeLote");
        
        if (criterioLote && unLoteSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un lote.");
        if (criterioComentario && unComentario.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado un comentario.");
        if (criterioTipoLote && tipoLoteSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha ingresado un tipo de lote.");
        if (criterioCriterioUtilizado && unCriterioSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un criterio.");
        if (criterioEstado && estadoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioLaboratorio && unLaboratorio == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un laboratorio.");
        if (criterioLaboratorio && !(unLaboratorio instanceof Laboratorio))
            throw new ExcepcionCargaParametros("No se ha seleccionado un laboratorio.");
        if (criterioOrdenDeCompra && unaOrdenDeCompra == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de compra.");
        

        Iterator analisisARevisar = this.getAnalisisLaboratorio().keySet().iterator();
        while (analisisARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            int unId = (int) analisisARevisar.next();
            AnalisisLaboratorio unAnalisis = this.getAnalisisLaboratorio().get(unId);
            
            if (criterioLote && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.poseeLote(unLoteSeleccionado);
            if (criterioComentario && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.poseeComentario(unComentario.toUpperCase());
            if (criterioTipoLote && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.poseeTipoDeLote(tipoLoteSeleccionado);
            if (criterioCriterioUtilizado && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.poseeCriterio(unCriterioSeleccionado);
            if (criterioEstado && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.poseeEstado(estadoSeleccionado);
            if (criterioFechaOrigen && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            if (criterioOrdenDeProduccionAsociada && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.poseeOrdenDeProduccionImplicada(unaOrdenProduccionSeleccionada);
            if (criterioLaboratorio && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.poseeLaboratorio((Laboratorio) unLaboratorio);
            if (criterioOrdenDeCompra && sePuedeAgregar)
                sePuedeAgregar = unAnalisis.poseeOrdenDeCompra(unaOrdenDeCompra);
            if (criterioPoseeLotesAsociado && sePuedeAgregar){
                if (poseeLote)
                    sePuedeAgregar = unAnalisis.poseeLote();
                else
                    sePuedeAgregar = !unAnalisis.poseeLote();
            }
            
            if (sePuedeAgregar)
                retorno.add(unAnalisis);
        }
        return retorno;
    }
    
    public ArrayList filtrarCriterios(Map<String, Boolean> criterios, String unNombre, String unaDescripcion, String estadoSeleccionado,
        OrdenDeProduccion unaOrdenProduccionSeleccionada, AnalisisLaboratorio unAnalisisDeLaboratorioAsociado,  
        Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioNombre = criterios.get("nombre");
        Boolean criterioDescripcion = criterios.get("descripcion");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioAnalisisLaboratorio = criterios.get("analisisLaboratorio");
        
        if (criterioNombre && unNombre.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado un nombre.");
        if (criterioDescripcion && unaDescripcion.equals(""))
            throw new ExcepcionCargaParametros("No se ha ingresado una descripcion.");
        if (criterioEstado && estadoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas.");
        if (criterioAnalisisLaboratorio && unAnalisisDeLaboratorioAsociado == null)
            throw new ExcepcionCargaParametros("No se ha ingresado un analisis de laboratorio.");

        Iterator criteriosARevisar = this.getCriteriosAnalisisLaboratorio().keySet().iterator();
        while (criteriosARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            int unId = (int) criteriosARevisar.next();
            CriterioAnalisisLaboratorio unCriterio = this.getCriteriosAnalisisLaboratorio().get(unId);
            
            if (criterioNombre && sePuedeAgregar)
                sePuedeAgregar = unCriterio.poseeNombre(unNombre);
            if (criterioDescripcion && sePuedeAgregar)
                sePuedeAgregar = unCriterio.poseeDescripcion(unaDescripcion);
            if (criterioEstado && sePuedeAgregar)
                sePuedeAgregar = unCriterio.poseeEstado(estadoSeleccionado);
            if (criterioOrdenDeProduccionAsociada && sePuedeAgregar)
                sePuedeAgregar = unCriterio.poseeOrdenDeProduccionImplicada(unaOrdenProduccionSeleccionada);
            if (criterioFechaOrigen && sePuedeAgregar)
                sePuedeAgregar = unCriterio.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            if (criterioAnalisisLaboratorio && sePuedeAgregar)
                sePuedeAgregar = unCriterio.poseeAnalisisDeLaboratorioAsociado(unAnalisisDeLaboratorioAsociado);
            
            if (sePuedeAgregar)
                retorno.add(unCriterio);
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
                    case "Laboratorio":
                        sePuedeAgregar = unEquipamiento instanceof Laboratorio;
                        break;
                }
            }
            if (criterioDireccion && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getDireccion().contains(unaDireccion);
            }
            if (criteriofechaAdquisicion && sePuedeAgregar)
                sePuedeAgregar = unEquipamiento.fechaAdquisicionEstaEntre(fechaAdquisicionLimiteInferior, fechaAdquisicionLimiteSuperior);
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
    
    public ArrayList filtrarEquipamientosSinBasculasNiLaboratorios(Map<String, Boolean> criterios, String unNombre, String unTipoEquipamiento, String unaDireccion, Calendar fechaAdquisicionLimiteInferior, Calendar fechaAdquisicionLimiteSuperior, Calendar fechaUltimoMantenimientoLimiteInferior, Calendar fechaUltimoMantenimientoLimiteSuperior, String unEstado, Bascula unaBasculaAsociada) throws ExcepcionCargaParametros{
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
            if (unEquipamiento instanceof Bascula || unEquipamiento instanceof Laboratorio){
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
                    case "Laboratorio":
                        sePuedeAgregar = unEquipamiento instanceof Laboratorio;
                        break;
                }
            }
            if (criterioDireccion && sePuedeAgregar){
                sePuedeAgregar = unEquipamiento.getDireccion().contains(unaDireccion);
            }
            if (criteriofechaAdquisicion && sePuedeAgregar)
                sePuedeAgregar = unEquipamiento.fechaAdquisicionEstaEntre(fechaAdquisicionLimiteInferior, fechaAdquisicionLimiteSuperior);
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
        if (unEquipamiento.poseeUnoOMasLotes())
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
            
        if (unEstado.equals("Baja") && unEquipamiento.estaActivo() && unEquipamiento.poseeUnoOMasLotes())
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
        Validaciones.validarCuit(unCuit);
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
    
    public void modificarAnalisisLaboratorio(AnalisisLaboratorio unAnalisis, Lote unLote, OrdenDeCompra unaOrdenDeCompra) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
            
        if (this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
    
        if (unAnalisis == null)
            throw new ExcepcionCargaParametros("Debe seleccionar un analisis de laboratorio.");
        
        if (unLote == null && unaOrdenDeCompra == null)
            throw new ExcepcionCargaParametros("Debe ingresar al menos un lote o una orden de compra asociada.");
        if (unLote != null && unaOrdenDeCompra != null)
            throw new ExcepcionCargaParametros("No puede ingresarse un lote y una orden de compra al mismo tiempo.");
        
        if (unLote != null && !unLote.estaRegular())
            throw new ExcepcionCargaParametros("El lote seleccionado no se encuentra en estado regular.");
        if (unaOrdenDeCompra != null && !unaOrdenDeCompra.seEncuentraRegular())
            throw new ExcepcionCargaParametros("La orden de compra seleccionada no se encuentra en estado regular.");
        
        if (unLote != null && unLote.equals(unAnalisis.getLoteImplicado()))
            return;
        if (unaOrdenDeCompra != null && unaOrdenDeCompra.equals(unAnalisis.getOrdenDeCompraImplicada()))
            return;
        
        if (unLote != null){
            Lote loteAnterior = unAnalisis.getLoteImplicado();
            if (loteAnterior != null){
                loteAnterior.removerAnalisis(unAnalisis);
                loteAnterior.getOrdenDeProduccionAsociada().removerEvento(unAnalisis);
            }
            unAnalisis.desvincularseDeLoteYDeOrdenDeCompra();
            unLote.agregarAnalisis(unAnalisis);
            unLote.getOrdenDeProduccionAsociada().agregarEvento(unAnalisis);
            unAnalisis.setLoteImplicado(unLote);
        }else if (unaOrdenDeCompra != null){
            OrdenDeCompra ordenDeCompraAnterior = unAnalisis.getOrdenDeCompraImplicada();
            if (ordenDeCompraAnterior != null){
                ordenDeCompraAnterior.getOrdenDeProduccionAsociada().removerEvento(unAnalisis);
                ordenDeCompraAnterior.removerAnalisisDeLaboratorio(unAnalisis);
                ordenDeCompraAnterior.getOrdenDeProduccionAsociada().removerEvento(unAnalisis);
            }
            unAnalisis.desvincularseDeLoteYDeOrdenDeCompra();
            unaOrdenDeCompra.agregarAnalisisDeLaboratorio(unAnalisis);
            unaOrdenDeCompra.getOrdenDeProduccionAsociada().agregarEvento(unAnalisis);
            unAnalisis.setOrdenDeCompraImplicada(unaOrdenDeCompra);
            
        }
        persistencia.modificarObjeto(unAnalisis);
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
        if (unaOrdenDeCompra.poseeLotesAsociados() && proveedorSeleccionado == null)
            throw new ExcepcionCargaParametros("No puede desvincularse un proveedor de una orden de compra si la orden de compra posee ingresos de lotes asociados.");
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
        Iterator recorredorOrdenesProduccion = this.ordenesProduccion.keySet().iterator();
        while (recorredorOrdenesProduccion.hasNext()){
            int unId = (int) recorredorOrdenesProduccion.next();
            OrdenDeProduccion unaOrdenProduccion = this.ordenesProduccion.get(unId);
            boolean sePuedeAgregar = true;
            if (criterioDescripcion)
                sePuedeAgregar = (unaOrdenProduccion.getDescripcion() != null && unaOrdenProduccion.getDescripcion().contains(unaDescripcion));
            if (sePuedeAgregar && criterioEstado)
                sePuedeAgregar = unaOrdenProduccion.getEstadoEvento().equals(unEstado);
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unaOrdenProduccion.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            if (sePuedeAgregar && criterioFechaEntrega)
                sePuedeAgregar = unaOrdenProduccion.entregaEstaEntre(fechaEntregaInferior, fechaEntregaSuperior);
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
    
    public ArrayList filtrarEstacionamientos(Map<String, Boolean> criterios, Equipamiento unaCamaraSeleccionada, OrdenDeProduccion unaOrdenProduccionSeleccionada, Lote unLoteSeleccionado, String estadoSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior, Calendar fechaExtraccionInferior, Calendar fechaExtraccionSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioCamaraAsociada = criterios.get("camara");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioLoteAsociado = criterios.get("lote");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioFechaExtraccion = criterios.get("fechaExtraccion");
        
        if (criterioCamaraAsociada && unaCamaraSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una camara de estacionamiento.");
        if (criterioCamaraAsociada && !(unaCamaraSeleccionada instanceof CamaraEstacionamiento))
            throw new ExcepcionCargaParametros("Debe seleccionarse una camara de estacionamiento.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioLoteAsociado && unLoteSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un lote.");
        if (criterioEstado && estadoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        if (criterioFechaExtraccion && (fechaExtraccionInferior == null || fechaExtraccionSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de extracción Inferior y Superior ingresadas");

        Iterator estacionamientosARevisar = this.getEstacionamientos().keySet().iterator();
        while (estacionamientosARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            int unId = (int) estacionamientosARevisar.next();
            Estacionamiento unEstacionamiento = this.getEstacionamientos().get(unId);
            
            if (criterioCamaraAsociada && sePuedeAgregar)
                sePuedeAgregar = unEstacionamiento.esEnCamara(unaCamaraSeleccionada);
                
            if (criterioOrdenDeProduccionAsociada && sePuedeAgregar)
                sePuedeAgregar = unEstacionamiento.poseeOrdenDeProduccionImplicada(unaOrdenProduccionSeleccionada);
            if (criterioLoteAsociado && sePuedeAgregar)
                sePuedeAgregar = unEstacionamiento.poseeLoteImplicado(unLoteSeleccionado);
            if (criterioEstado && sePuedeAgregar)
                sePuedeAgregar = unEstacionamiento.poseeEstado(estadoSeleccionado);
            
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unEstacionamiento.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            
            if (sePuedeAgregar && criterioFechaExtraccion)
                sePuedeAgregar = unEstacionamiento.fechaExtraccionEstaEntre(fechaExtraccionInferior, fechaExtraccionSuperior);
            
            if (sePuedeAgregar)
                retorno.add(unEstacionamiento);
        }
        return retorno;
    }
    
public ArrayList filtrarEventos(Map<String, Boolean> criterios, OrdenDeProduccion unaOrdenProduccionSeleccionada, String estadoSeleccionado, String unTipoDeEventoSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioTipoDeEvento = criterios.get("tipoEvento");
        
        if (unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioEstado && estadoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        if (criterioTipoDeEvento && unTipoDeEventoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un tipo de evento.");
        
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");

        Iterator eventosARevisar = unaOrdenProduccionSeleccionada.getEventosImplicados().iterator();
        while (eventosARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            
            Evento unEvento = (Evento) eventosARevisar.next();
            
            if (criterioEstado && sePuedeAgregar)
                sePuedeAgregar = unEvento.poseeEstadoEvento(estadoSeleccionado);
            
            if (criterioFechaOrigen && sePuedeAgregar)
                sePuedeAgregar = unEvento.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);

            if (criterioTipoDeEvento && sePuedeAgregar){
                switch (unTipoDeEventoSeleccionado){
                    case "Ingresos":
                        sePuedeAgregar = (unEvento instanceof MovimientoInternoMateriaPrima && !((MovimientoInternoMateriaPrima)unEvento).poseeEquipamientoOrigen());
                        break;
                    case "Estacionamientos":
                        sePuedeAgregar = unEvento instanceof Estacionamiento;
                    case "Movimientos":
                        sePuedeAgregar = unEvento instanceof MovimientoInternoMateriaPrima;
                        break;
                    case "Analisis de laboratorios":
                        sePuedeAgregar = unEvento instanceof AnalisisLaboratorio;
                        break;
                    case "Moliendas":
                        sePuedeAgregar = unEvento instanceof Molienda;
                        break;
                }
                
            }
            
            if (sePuedeAgregar)
                retorno.add(unEvento);
            

        }
        return retorno;
    }    
    
    public ArrayList filtrarIngresos(Map<String, Boolean> criterios, String unEstado, String unaEtiqueta, OrdenDeProduccion unaOrdenProduccionSeleccionada, OrdenDeCompra unaOrdenCompraSeleccionada, Equipamiento unEquipamiento, Proveedor unProveedorTransporteSeleccionado, Proveedor unProveedorSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioEtiqueta = criterios.get("etiqueta");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioOrdenDeCompra = criterios.get("ordenCompra");
        Boolean criterioEquipamiento = criterios.get("equipamiento");
        Boolean criterioProveedorTransporte = criterios.get("proveedorTransporte");
        Boolean criterioProveedorOrdenCompra = criterios.get("proveedorOrdenCompra");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        
        if (criterioEstado && unEstado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        if (criterioEtiqueta && (unaEtiqueta == null || unaEtiqueta.isEmpty()))
            throw new ExcepcionCargaParametros("No se ha ingresado una etiqueta para realizar el filtrado.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioOrdenDeCompra && unaOrdenCompraSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de compra.");
        if (criterioEquipamiento && unEquipamiento == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un equipamiento donde reside.");
        if (criterioProveedorTransporte && unProveedorTransporteSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un proveedor de servicio de transporte.");
        if (criterioProveedorOrdenCompra && unProveedorSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un proveedor asociado a un ingreso.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        
        Iterator ingresos = this.movimientosDeIngreso.keySet().iterator();
        while (ingresos.hasNext()){
            Boolean sePuedeAgregar = true;
            int unId = (int) ingresos.next();
            MovimientoInternoMateriaPrima unMovimiento = this.movimientosDeIngreso.get(unId);
            if (sePuedeAgregar && criterioEstado)
                sePuedeAgregar = unMovimiento.poseeEstado(unEstado);
            if (sePuedeAgregar && criterioEtiqueta)
                sePuedeAgregar = unMovimiento.poseeEtiqueta(unaEtiqueta);
            if (sePuedeAgregar && criterioOrdenDeProduccionAsociada)
                sePuedeAgregar = unMovimiento.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
            if (sePuedeAgregar && criterioOrdenDeCompra)
                sePuedeAgregar = unMovimiento.poseeOrdenDeCompraAsociada(unaOrdenCompraSeleccionada);
            if (sePuedeAgregar && criterioEquipamiento)
                sePuedeAgregar = unMovimiento.poseeEquipamientoDestino(unEquipamiento);
            if (sePuedeAgregar && criterioProveedorTransporte)
                sePuedeAgregar = unMovimiento.poseeProveedorTransporteAsociado(unProveedorTransporteSeleccionado);
            if (sePuedeAgregar && criterioProveedorOrdenCompra)
                sePuedeAgregar = unMovimiento.poseeProveedorAsociado(unProveedorSeleccionado);
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unMovimiento.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            if (sePuedeAgregar)
                retorno.add(unMovimiento);
        }
        return retorno;
    }
    
public ArrayList filtrarMovimientos(Map<String, Boolean> criterios, String unEstado, String unaEtiqueta, OrdenDeProduccion unaOrdenProduccionSeleccionada, OrdenDeCompra unaOrdenCompraSeleccionada, Equipamiento unEquipamiento, Proveedor unProveedorTransporteSeleccionado, Proveedor unProveedorSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioEtiqueta = criterios.get("etiqueta");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioOrdenDeCompra = criterios.get("ordenCompra");
        Boolean criterioEquipamiento = criterios.get("equipamiento");
        Boolean criterioProveedorTransporte = criterios.get("proveedorTransporte");
        Boolean criterioProveedorOrdenCompra = criterios.get("proveedorOrdenCompra");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        
        if (criterioEstado && unEstado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        if (criterioEtiqueta && (unaEtiqueta == null || unaEtiqueta.isEmpty()))
            throw new ExcepcionCargaParametros("No se ha ingresado una etiqueta para realizar el filtrado.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioOrdenDeCompra && unaOrdenCompraSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de compra.");
        if (criterioEquipamiento && unEquipamiento == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un equipamiento donde reside.");
        if (criterioProveedorTransporte && unProveedorTransporteSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un proveedor de servicio de transporte.");
        if (criterioProveedorOrdenCompra && unProveedorSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un proveedor asociado a un ingreso.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        
        Iterator recorredorMovimientos = this.movimientos.keySet().iterator();
        while (recorredorMovimientos.hasNext()){
            Boolean sePuedeAgregar = true;
            int unId = (int) recorredorMovimientos.next();
            MovimientoInternoMateriaPrima unMovimiento = this.movimientos.get(unId);
            sePuedeAgregar = unMovimiento.poseeEquipamientoOrigen(); //Me aseguro de que solamente sean movimientos que no sean de ingreso.
            if (sePuedeAgregar && criterioEstado)
                sePuedeAgregar = unMovimiento.poseeEstado(unEstado);
            if (sePuedeAgregar && criterioEtiqueta)
                sePuedeAgregar = unMovimiento.poseeEtiqueta(unaEtiqueta);
            if (sePuedeAgregar && criterioOrdenDeProduccionAsociada)
                sePuedeAgregar = unMovimiento.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
            if (sePuedeAgregar && criterioOrdenDeCompra)
                sePuedeAgregar = unMovimiento.poseeOrdenDeCompraAsociada(unaOrdenCompraSeleccionada);
            if (sePuedeAgregar && criterioEquipamiento)
                sePuedeAgregar = unMovimiento.poseeEquipamientoDestino(unEquipamiento);
            if (sePuedeAgregar && criterioProveedorTransporte)
                sePuedeAgregar = unMovimiento.poseeProveedorTransporteAsociado(unProveedorTransporteSeleccionado);
            if (sePuedeAgregar && criterioProveedorOrdenCompra)
                sePuedeAgregar = unMovimiento.poseeProveedorAsociado(unProveedorSeleccionado);
            if (sePuedeAgregar && criterioFechaOrigen){
                fechaOrigenInferior.set(Calendar.HOUR, 0);
                fechaOrigenInferior.set(Calendar.MINUTE, 0);
                fechaOrigenInferior.set(Calendar.SECOND, 0);
                
                fechaOrigenSuperior.set(Calendar.HOUR_OF_DAY, 24);
                fechaOrigenSuperior.set(Calendar.MINUTE, 59);
                fechaOrigenSuperior.set(Calendar.SECOND, 59);
                sePuedeAgregar = unMovimiento.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            }
            if (sePuedeAgregar)
                retorno.add(unMovimiento);
        }
        return retorno;
    }    

public ArrayList filtrarLotes(Map<String, Boolean> criterios, Equipamiento unEquipamientoAFiltrar, String unaEtiqueta, OrdenDeProduccion unaOrdenProduccionSeleccionada, OrdenDeCompra unaOrdenCompraSeleccionada, Proveedor unProveedorSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioEtiqueta = criterios.get("etiqueta");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioOrdenDeCompra = criterios.get("ordenCompra");
        Boolean criterioProveedorOrdenCompra = criterios.get("proveedorOrdenCompra");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioEquipamiento = criterios.get("equipamiento");
        
        if (criterioEtiqueta && (unaEtiqueta == null || unaEtiqueta.isEmpty()))
            throw new ExcepcionCargaParametros("No se ha ingresado una etiqueta para realizar el filtrado.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioOrdenDeCompra && unaOrdenCompraSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de compra.");
        if (criterioProveedorOrdenCompra && unProveedorSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un proveedor asociado a un ingreso.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        if (criterioEquipamiento && unEquipamientoAFiltrar == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un equipamiento a filtrar.");
        
        if (criterioEquipamiento && !unEquipamientoAFiltrar.poseeUnoOMasLotes()){
            throw new ExcepcionCargaParametros("El equipamiento que seleccionó no posee lotes");
        }
        Iterator lotesARecorrer;
        
        if (criterioEquipamiento){
            lotesARecorrer = unEquipamientoAFiltrar.getLotesAsociadosNoAnulados().iterator();
            while (lotesARecorrer.hasNext()){
                boolean sePuedeAgregar = true;
                Lote unLote = (Lote) lotesARecorrer.next();

                if (sePuedeAgregar && criterioOrdenDeProduccionAsociada)
                    sePuedeAgregar = unLote.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
                if (sePuedeAgregar && criterioOrdenDeCompra)
                    sePuedeAgregar = unLote.poseeOrdenDeCompraAsociada(unaOrdenCompraSeleccionada);
                if (sePuedeAgregar && criterioProveedorOrdenCompra)
                    sePuedeAgregar = unLote.poseeProveedorAsociado(unProveedorSeleccionado);
                if (sePuedeAgregar && criterioFechaOrigen){
                    sePuedeAgregar = unLote.ultimoMovimientoEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
                }
                if (sePuedeAgregar)
                    retorno.add(unLote);
            }
            return retorno;
        }else{
            lotesARecorrer = this.lotes.keySet().iterator();
            while (lotesARecorrer.hasNext()){
                Lote unLote = this.lotes.get((int) lotesARecorrer.next());
                boolean sePuedeAgregar = true;
                
                if (sePuedeAgregar && criterioOrdenDeProduccionAsociada)
                    sePuedeAgregar = unLote.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
                if (sePuedeAgregar && criterioOrdenDeCompra)
                    sePuedeAgregar = unLote.poseeOrdenDeCompraAsociada(unaOrdenCompraSeleccionada);
                if (sePuedeAgregar && criterioProveedorOrdenCompra)
                    sePuedeAgregar = unLote.poseeProveedorAsociado(unProveedorSeleccionado);
                if (sePuedeAgregar && criterioFechaOrigen){
                    sePuedeAgregar = unLote.ultimoMovimientoEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
                }
                if (sePuedeAgregar)
                    retorno.add(unLote);
            }
            return retorno;
        }
            
        
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
                sePuedeAgregar = unaOrdenDeCompra.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            }
            if (sePuedeAgregar)
                retorno.add(unaOrdenDeCompra);
        }
        return retorno;
    }

    public ArrayList filtrarOrdenesDeCompraConProveedorAsociado(Map<String, Boolean> criterios, OrdenDeProduccion unaOrdenProduccionSeleccionada, Proveedor unProveedorSeleccionado, String unEstado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
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
            sePuedeAgregar = unaOrdenDeCompra.poseeProveedorAsociado();
            if (sePuedeAgregar && criterioOrdenDeProduccionAsociada)
                sePuedeAgregar = unaOrdenDeCompra.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
            if (sePuedeAgregar && criterioProveedor)
                sePuedeAgregar = unaOrdenDeCompra.poseeProveedorAsociado();
            if (sePuedeAgregar && criterioFechaOrigen){
                sePuedeAgregar = unaOrdenDeCompra.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
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
        if (Organizacion.convertirUnidadPeso(ordenProduccionSeleccionada.getUnidadDeMedida(), ordenProduccionSeleccionada.getPesoRestanteAComprar(), unaUnidadMedida)< cantidadAComprar)
            throw new ExcepcionCargaParametros("La orden de producción tiene un remanente a comprar menor al ingresado.");
        
        OrdenDeCompra unaOrdenDeCompra = new OrdenDeCompra(this.getUsuarioActivo(), cantidadAComprar, unaUnidadMedida, costoDeCompra, proveedorSeleccionado, ordenProduccionSeleccionada);
        persistencia.persistirObjeto(unaOrdenDeCompra);
        this.ordenesCompra.put(unaOrdenDeCompra.getId(), unaOrdenDeCompra);
        ordenProduccionSeleccionada.agregarEvento(unaOrdenDeCompra);
        
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
    
    public Usuario recuperarUsuario(String unNombreDeUsuario){
        Usuario retorno = null;
        Iterator recorredorUsuarios = this.usuarios.keySet().iterator();
        boolean seEncontro = false;
        while (recorredorUsuarios.hasNext() && !seEncontro){
            int unId = (int) recorredorUsuarios.next();
            Usuario unUsuario = this.usuarios.get(unId);
            if (unUsuario.seIdentifica(unNombreDeUsuario)){
                retorno = unUsuario;
                seEncontro = true;
            }
        }
        return retorno;
    }

    public ArrayList getListaOrdenesCompra() {
        ArrayList retorno = new ArrayList();
        Iterator recorredorDeOrdenes = this.ordenesCompra.keySet().iterator();
        while (recorredorDeOrdenes.hasNext()){
            int unId = (int) recorredorDeOrdenes.next();
            OrdenDeCompra unaOrdenDeCompra = this.ordenesCompra.get(unId);
            retorno.add(unaOrdenDeCompra);
        }
        return retorno;
    }

    private float convertirAFlotante(String unaCadena, String unCampo) throws ExcepcionCargaParametros {
        if (!Validaciones.esUnNumeroFraccionarioValido(unaCadena))
            throw new ExcepcionCargaParametros("El valor ingresado para "+unCampo+" no posee un formato valido (Utilice solo numeros y una coma)");
        unaCadena = unaCadena.replace(".", "");
        unaCadena = unaCadena.replace(",", ".");
        return Float.parseFloat(unaCadena);
    }



    public static String expresarCalendario (Calendar unaFecha){
        return ( new SimpleDateFormat( "dd/MM/yyyy" ) ).format( unaFecha.getTime() );
    }

    public ArrayList getCriteriosActivos() {
        ArrayList retorno = new ArrayList();
        Iterator recorredorDeCriterios = this.criteriosAnalisisLaboratorio.keySet().iterator();
        while (recorredorDeCriterios.hasNext()){
            int unId = (int) recorredorDeCriterios.next();
            CriterioAnalisisLaboratorio unCriterio = this.criteriosAnalisisLaboratorio.get(unId);
            if (unCriterio.seEncuentraActivo())
                retorno.add(unCriterio);
        }
        return retorno;
    }

    public void darDeBajaUnCriterio(CriterioAnalisisLaboratorio unAnalisis) throws SQLException, ExcepcionPersistencia {
        unAnalisis.darDeBaja();
        persistencia.modificarObjeto(unAnalisis);
    }

    public void modificarCriterioDeAnalisis(CriterioAnalisisLaboratorio unCriterio, String unEstado) throws SQLException, ExcepcionPersistencia, ExcepcionCargaParametros {
        if (unEstado.equals("Seleecionar"))
            throw new ExcepcionCargaParametros("Debe seleccionar un estado para realizar una modificación");
        unCriterio.setEstado(unEstado);
        persistencia.modificarObjeto(unCriterio);
    }



}


