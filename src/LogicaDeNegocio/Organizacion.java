/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.Estadisticas.Estadistica;
import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.Auditoria.Auditoria;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import Persistencia.Configuracion;
import Persistencia.ExcepcionPersistencia;
import Persistencia.Persistencia;
import Reportes.GeneradorDeReportes;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author usuario
 */
public class Organizacion {

    

    
    private Persistencia persistencia;
    
    private Usuario usuarioActivo;
    
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
    private Map<Integer, Molienda> moliendas;
    
    private Map<Integer, AnalisisLaboratorio> analisisLaboratorio;
    private Map<Integer, CriterioAnalisisLaboratorio> criteriosAnalisisLaboratorio;
    private Map<Integer, Salida> salidas;
    private Map<Integer, Egreso> egresos;
    private Map<Integer, Merma> mermas;
    private Map<Integer, Perdida> perdidas;
    
    private Map<Integer, Evento> eventos;
    
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
        this.moliendas = new HashMap <Integer, Molienda>();
        
        this.analisisLaboratorio = new HashMap <Integer, AnalisisLaboratorio>();
        this.criteriosAnalisisLaboratorio = new HashMap <Integer, CriterioAnalisisLaboratorio>();
        
        this.salidas = new HashMap <Integer, Salida>();
        this.egresos = new HashMap <Integer, Egreso>();
        this.mermas = new HashMap <Integer, Merma>();
        this.perdidas = new HashMap <Integer, Perdida>();
        
        this.eventos = new HashMap <Integer, Evento>();
        
        this.persistencia.recuperarOrganizacion(this);
        
        Estadistica.setOrganizacion(this);
    }

    public Map<Integer, Perdida> getPerdidas() {
        return perdidas;
    }

    
    
    public Map<Integer, Merma> getMermas() {
        return mermas;
    }

    public Map<Integer, AnalisisLaboratorio> getAnalisisLaboratorio() {
        return analisisLaboratorio;
    }

    public Map<Integer, Salida> getSalidas() {
        return salidas;
    }

    public Map<Integer, Egreso> getEgresos() {
        return egresos;
    }

    public Map<Integer, Evento> getEventos() {
        return eventos;
    }

    public Map<Integer, MovimientoInternoMateriaPrima> getMovimientosDeIngreso() {
        return movimientosDeIngreso;
    }

    public Map<Integer, Molienda> getMoliendas() {
        return moliendas;
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
    
    public Map<Integer, MovimientoInternoMateriaPrima> getMovimientosInternos() {
        Map<Integer, MovimientoInternoMateriaPrima> retorno = new HashMap();
        for (Map.Entry<Integer,MovimientoInternoMateriaPrima> entry : this.movimientos.entrySet()){
            MovimientoInternoMateriaPrima unMovimiento = entry.getValue();
            if (unMovimiento.poseeEquipamientoOrigen())
                retorno.put(entry.getKey(),entry.getValue());
        }
        return retorno;
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
            unCriterio.agregarOrdenDeProduccion(unaOrdenDeProduccion);
        }
        this.persistencia.modificarObjeto(unaOrdenDeProduccion);
    }
    
    public void registrarOrdenDeProduccion(Calendar unaFechaOrigen, String cantidadAProducir, String unidadDeMedida, Calendar fechaEntregaProductoTerminado, String unaDescripcion, ArrayList criterios) throws ExcepcionCargaParametros, SQLException {
        if (unaFechaOrigen == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de origen.");
        if (fechaEntregaProductoTerminado == null)
            throw new ExcepcionCargaParametros("Se requiere especificar una fecha de entrega de producto terminado.");
        if (fechaEntregaProductoTerminado.before(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha de entrega del producto terminado no puede anteceder la fecha actual.");
        float unaCantidad = Organizacion.convertirAFlotante(cantidadAProducir, "cantidad a producir");
        if (unaCantidad <= 0)
            throw new ExcepcionCargaParametros("Por favor, ingrese una cantidad a Producir positiva.");
        OrdenDeProduccion unaOrdenDeProduccion = new OrdenDeProduccion(unaFechaOrigen, unaCantidad, unidadDeMedida, fechaEntregaProductoTerminado, unaDescripcion, this.usuarioActivo);
        Iterator criteriosImplicados = criterios.iterator();
        while (criteriosImplicados.hasNext()){
            CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) criteriosImplicados.next();
            unaOrdenDeProduccion.agregarCriterioLaboratorio(unCriterio);
            unCriterio.agregarOrdenDeProduccion(unaOrdenDeProduccion);
        }
        this.persistencia.persistirObjeto(unaOrdenDeProduccion);
        this.getEventos().put(unaOrdenDeProduccion.getIdEvento(), unaOrdenDeProduccion);
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
        
        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
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
        if (unCriterio == null)
            throw new ExcepcionCargaParametros("Debe seleccionar un criterio de analisis.");
        if (!unCriterio.seEncuentraActivo())
            throw new ExcepcionCargaParametros("Debe seleccionar un criterio de analisis activo para realizar un analisis de laboratorio.");
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
            
        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
    
        if (unLote == null && unaOrdenDeCompra == null)
            throw new ExcepcionCargaParametros("Debe ingresar al menos un lote o una orden de compra asociada.");
        
        if (unLote != null && unaOrdenDeCompra != null)
            throw new ExcepcionCargaParametros("No puede ingresarse un lote y una orden de compra al mismo tiempo.");
        
        if (unaOrdenDeCompra != null && !unaOrdenDeCompra.seEncuentraRegular())
            throw new ExcepcionCargaParametros("La orden de compra seleccionada no se encuentra en estado regular.");
        if (unaOrdenDeCompra != null && !unaOrdenDeCompra.poseeProveedorAsociado())
            throw new ExcepcionCargaParametros("La orden de compra seleccionada no posee un proveedor asociado.");
        if (unaOrdenDeCompra != null && !unaOrdenDeCompra.poseeTipoLote(unCriterio.getTipoLote()))
            throw new ExcepcionCargaParametros("La orden de compra seleccionada es de "+unaOrdenDeCompra.getTipoLote()+" pero el criterio de análisis de laboratorio es para "+unCriterio.getTipoLote());
        
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
        if ((unPorcentajePalo + unPorcentajeSemilla + unPorcentajePolvo + unPorcentajeHoja) <99 && (unPorcentajePalo + unPorcentajeSemilla + unPorcentajePolvo + unPorcentajeHoja) >101)
            throw new ExcepcionCargaParametros("El porcentaje total de palo, semilla, polvo y hoja debe igualar a 100 % (Actualmente es de "+(unPorcentajePalo + unPorcentajeSemilla + unPorcentajePolvo + unPorcentajeHoja));
        
        AnalisisLaboratorio unAnalisis = null;
        if (unLote != null){
            unAnalisis = new AnalisisLaboratorio(puntosNegros, torrada, color, aroma, tacto, degustacion, unPorcentajePalo, unPorcentajePolvo, unPorcentajeSemilla, unPorcentajeHoja, unPorcentajeHumedad, unLote, (Laboratorio) unLaboratorio, unCriterio, usuarioActivo, unComentario.toUpperCase());
            unLote.agregarAnalisis(unAnalisis);
            unLote.getOrdenDeProduccionAsociada().agregarEvento(unAnalisis);
        }else if (unaOrdenDeCompra != null){
            unAnalisis = new AnalisisLaboratorio(puntosNegros, torrada, color, aroma, tacto, degustacion, unPorcentajePalo, unPorcentajePolvo, 
                    unPorcentajeSemilla, unPorcentajeHoja, unPorcentajeHumedad, (Laboratorio) unLaboratorio, unCriterio, usuarioActivo, 
                    unComentario.toUpperCase(), unaOrdenDeCompra);
            unaOrdenDeCompra.getProveedorAsociado().agregarAnalisis(unAnalisis);
            unaOrdenDeCompra.agregarAnalisisDeLaboratorio(unAnalisis);
            unaOrdenDeCompra.getOrdenDeProduccionAsociada().agregarEvento(unAnalisis);
        }
        
        persistencia.persistirObjeto(unAnalisis);
        ((Laboratorio)unLaboratorio).agregarAnalisis(unAnalisis);
        unCriterio.agregarAnalisis(unAnalisis);
        this.getAnalisisLaboratorio().put(unAnalisis.getId(), unAnalisis);
        this.getEventos().put(unAnalisis.getIdEvento(), unAnalisis);
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
        Iterator detallesAAnular = unEstacionamiento.getDetallesAsociados().iterator();
        while (detallesAAnular.hasNext()){
            Lote unLote = ((DetalleTransformacion) detallesAAnular.next()).getLoteImplicado();
            unLote.anularEstacionamiento();
            persistencia.modificarObjeto(unLote);
        }
    }
    
    public void anularMolienda(Molienda unaMolienda) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unaMolienda == null)
            throw new ExcepcionCargaParametros("Debe seleccionar una molienda para anular.");
        if (unaMolienda.poseeUnoOMasEventosRegularesPosteriores())
            throw new ExcepcionCargaParametros("No se puede anular una molienda que tenga un lote que registre uno o mas eventos (Movimientos, moliendas, analisis, etc) posteriores regulares.");
        if (unaMolienda.poseeUnoOMasEgresos())
            throw new ExcepcionCargaParametros("No se puede anular una molienda sobre la que se registre una o más salidas posteriores.");
        unaMolienda.anular();
        persistencia.modificarObjeto(unaMolienda);
        Iterator detallesAAnular = unaMolienda.getDetallesAsociados().iterator();
        while (detallesAAnular.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) detallesAAnular.next();
            if (unDetalle.getCantidadUtilizada() > Configuracion.BOLSAS_CONCLUSION_MOLIENDA){
                Lote unLote = unDetalle.getLoteImplicado();
                unLote.anularMolienda();
                persistencia.modificarObjeto(unLote);
            }
        }
    }
    
    public void anularEgreso(Egreso unEgreso) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unEgreso == null)
            throw new ExcepcionCargaParametros("Debe seleccionar un egreso para anular.");
        if (unEgreso.poseeUnoOMasEventosRegularesPosteriores())
            throw new ExcepcionCargaParametros("No se puede dar de baja un egreso que registre uno o mas eventos (Movimientos, moliendas, analisis, etc) posteriores regulares.");
        unEgreso.anular();
        persistencia.modificarObjeto(unEgreso);
    }
    
    public void registrarIngresoMateriaPrima(Calendar fechaOrigen, LocalTime horaEntrada, LocalTime horaSalida, String unidadTransporte, String cantidadUnidades, String unidadMedidaPeso, String pesoEntrada, String pesoSalida, String nHojaRuta, String nRemito, String nPrecinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Equipamiento destino, OrdenDeCompra unaOrdenDeCompraAsociada,Proveedor unProveedorDeServicioDeTransporte, AnalisisLaboratorio unAnalisisLaboratorio, String unTipoDeLote) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        
        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
        
        if (unTipoDeLote.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe seleccionar un tipo de lote para el ingreso de materia prima.");
        
        if (fechaOrigen == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una fecha de origen valida para el ingreso de materia prima.");
        if (fechaOrigen.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha origen de un ingreso de materia prima no puede exceder la fecha actual.");
        
        if (!Validaciones.esUnNumeroEnteroValido(cantidadUnidades))
            throw new ExcepcionCargaParametros("Verifique la cantidad de unidades ingresadas en el ingreso de materia prima.");
        
        if (unaOrdenDeCompraAsociada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de compra asociada para el ingreso de materia prima.");
        if (!unaOrdenDeCompraAsociada.seEncuentraRegular())
            throw new ExcepcionCargaParametros("La orden de compra seleccionada no se encuentra en estado regular para el ingreso de materia prima.");
        if (!unaOrdenDeCompraAsociada.poseeProveedorAsociado())
            throw new ExcepcionCargaParametros("La orden de compra seleccionada no posee un proveedor asociado para el ingreso de materia prima.");
        
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
            throw new ExcepcionCargaParametros("No se puede registrar un ingreso de materia prima sobre un analisis de laboratorio que no sea de la orden de compra especificada. (Orden de compra ingresada: "+unaOrdenDeCompraAsociada.getId()+", orden esperada: "+unAnalisisLaboratorio.getOrdenDeCompraImplicada().getId()+")");
        if (unAnalisisLaboratorio.poseeLote() && unAnalisisLaboratorio.getLoteImplicado().estaRegular())
            throw new ExcepcionCargaParametros("No se puede registrar un ingreso de materia prima sobre un analisis de laboratorio que ya posea un lote asociado en estado regular.");
        
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
        
        if (Organizacion.convertirUnidadPeso(unidadMedidaPeso, pesoNeto, Lote.UNIDAD_MEDIDA_KILOGRAMO) > Organizacion.convertirUnidadPeso(unaOrdenDeCompraAsociada.getUnidadDeMedida(), unaOrdenDeCompraAsociada.getCantidadRestanteARecibir(), Lote.UNIDAD_MEDIDA_KILOGRAMO))
            throw new ExcepcionCargaParametros("No se puede registrar un ingreso que supere lo pactado en la orden de compra "+unaOrdenDeCompraAsociada.getId()+". (Ingreso: "+UtilidadesInterfazGrafica.formatearFlotante(pesoNeto)+" "+unidadMedidaPeso+"s, Cantidad restante a recibir de la orden de compra: "+UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(unaOrdenDeCompraAsociada.getUnidadDeMedida(), unaOrdenDeCompraAsociada.getCantidadRestanteARecibir(), unidadMedidaPeso))+" "+unaOrdenDeCompraAsociada.getUnidadDeMedida()+"s)");
        
        if (!destino.puedeAlbergar(pesoNeto, unidadMedidaPeso))
            throw new ExcepcionCargaParametros("El destino donde residirá el lote no posee espacio suficiente.");
        
        if (!unAnalisisLaboratorio.poseeTipoDeLote(unTipoDeLote))
            throw new ExcepcionCargaParametros("El analisis de laboratorio ingresado es para lotes de tipo "+unAnalisisLaboratorio.getCriterioAsociado().getTipoLote()+" pero se esta registrando el tipo de lote "+unTipoDeLote);
        
        Lote unLote = new Lote(pesoNeto, unTipoDeLote, unidadMedidaPeso, fechaOrigen, unaOrdenDeCompraAsociada, destino, unidadTransporte, unaCantidadDeUnidades);
        persistencia.persistirObjeto(unLote);
        unLote.setEtiqueta(( new SimpleDateFormat( "yyyyMMdd" ) ).format( fechaOrigen.getTime() )+"-"+unLote.getId());
        MovimientoInternoMateriaPrima unMovimiento = new MovimientoInternoMateriaPrima(fechaOrigen, this.getUsuarioActivo(), horaEntrada, horaSalida, unidadTransporte, unaCantidadDeUnidades, unidadMedidaPeso, unPesoEntrada, unPesoSalida, nHojaRuta, nRemito, nPrecinto, nombreConductor, patenteChasis, patenteAcoplado, unLote, null, destino, unProveedorDeServicioDeTransporte);
        destino.agregarLote(unLote);
        destino.agregarMovimientoEntrada(unMovimiento);
        
        persistencia.persistirObjeto(unMovimiento);
        unLote.agregarMovimiento(unMovimiento);
        unLote.asignarUltimoMovimiento(unMovimiento);
        unLote.getOrdenDeProduccionAsociada().agregarEvento(unMovimiento);
        persistencia.modificarObjeto(unLote);//ACTUALIZAR EN LA BASE DE DATOS EL MOVIMENTO INTERNO ASOCIADO AL INGRESO Y SU ETIQUETA.
        unaOrdenDeCompraAsociada.agregarLote(unLote);
        this.lotes.put(unLote.getId(), unLote);
        this.movimientos.put(unMovimiento.getId(), unMovimiento);
        this.movimientosDeIngreso.put(unMovimiento.getId(), unMovimiento);
        this.getEventos().put(unMovimiento.getIdEvento(), unMovimiento);
        
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
        
        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
        
        Lote unLoteImplicado = unMovimiento.getLoteAsociado();
        if (!unMovimiento.poseeEquipamientoOrigen())
            throw new ExcepcionCargaParametros("No se puede anular un movimiento de ingreso. Dirijase a gestión de Ingresos para hacerlo.");
        if (unLoteImplicado.poseeUnoOMasEventosRegularesPosterioresA(unMovimiento))
            throw new ExcepcionCargaParametros("No se puede dar de baja un movimiento cuyo lote tenga eventos posteriores (Movimientos o transformaciones) en estados regulares.");
        unMovimiento.anular();
        unMovimiento.getEquipamientoDestino().removerLote(unLoteImplicado);
        if (unMovimiento.poseeMermaRegular()){
            Merma unaMerma = unMovimiento.obtenerMermaRegular();
            if (unaMerma != null){
                unaMerma.anular();
                persistencia.modificarObjeto(unaMerma);
            }
        }
        MovimientoInternoMateriaPrima nuevoUltimoMovimiento = unLoteImplicado.calcularUltimoMovimientoRegular();
        unLoteImplicado.setEquipamientoDondeReside(nuevoUltimoMovimiento.getEquipamientoDestino());
        nuevoUltimoMovimiento.getEquipamientoDestino().agregarLote(unLoteImplicado);
        nuevoUltimoMovimiento.setMovimientoInmediatamentePosterior(null);
        unLoteImplicado.setUltimoMovimientoRegular(nuevoUltimoMovimiento);
        persistencia.modificarObjeto(unMovimiento);
        persistencia.modificarObjeto(unLoteImplicado);
    }    
    
public void registrarMovimimiento(Calendar fechaOrigen, LocalTime horaEntrada, LocalTime horaSalida, String unidadTransporte, String cantidadUnidades, String unidadMedidaPeso, String pesoEntrada, String pesoSalida, String nHojaRuta, String nRemito, String nPrecinto, String nombreConductor, String patenteChasis, String patenteAcoplado, Lote unLote, Equipamiento destino, Proveedor unProveedorDeServicioDeTransporte) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        
        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
        
        if (fechaOrigen == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una fecha de origen valida.");
        if (fechaOrigen.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha de origen no puede exceder la fecha actual.");
        
        if (unLote == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un lote.");
        
        if (Validaciones.loteSeEncuentraSiendoEstacionadoActualmente(unLote))
            throw new ExcepcionCargaParametros("No se puede mover un lote que esté registre un estacionamiento en curso actualmente.");
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
        
        
        
        MovimientoInternoMateriaPrima unMovimiento = new MovimientoInternoMateriaPrima(fechaOrigen, this.getUsuarioActivo(), horaEntrada, horaSalida, unidadTransporte, unaCantidadDeUnidades, unidadMedidaPeso, unPesoEntrada, unPesoSalida, nHojaRuta, nRemito, nPrecinto, nombreConductor, patenteChasis, patenteAcoplado, unLote, unLote.getEquipamientoDondeReside(), destino, unProveedorDeServicioDeTransporte);
        persistencia.persistirObjeto(unMovimiento);
        unLote.getEquipamientoDondeReside().agregarMovimientoSalida(unMovimiento);
        destino.agregarMovimientoEntrada(unMovimiento);

        unLote.getEquipamientoDondeReside().removerLote(unLote);
        destino.agregarLote(unLote);
        
        unLote.setEquipamientoDondeReside(destino);
        unLote.agregarMovimiento(unMovimiento);
        unLote.asignarUltimoMovimiento(unMovimiento);
        
        unLote.getOrdenDeProduccionAsociada().agregarEvento(unMovimiento);
        
        persistencia.modificarObjeto(unLote);//Ahora el lote tiene como clave foranea al equipamiento destino.
        
        this.movimientos.put(unMovimiento.getId(), unMovimiento);
        this.getEventos().put(unMovimiento.getIdEvento(), unMovimiento);
        
        System.out.println("Cantidad de peso ingresado menos las perdidas: "+unLote.obtenerPesoIngresadoConPerdidasIncluidasKg());
        Float cantidadPerdida = Organizacion.convertirUnidadPeso(unLote.getUnidadDeMedida(), unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(), Lote.UNIDAD_MEDIDA_KILOGRAMO) - Organizacion.convertirUnidadPeso(unidadMedidaPeso, pesoNeto, Lote.UNIDAD_MEDIDA_KILOGRAMO);
        
        cantidadPerdida = Math.max(cantidadPerdida, 0);
        int diferenciaBolsas = unLote.obtenerUnidadesTransporteIngresadoConPerdidasIncluidas()-unaCantidadDeUnidades;
        diferenciaBolsas = Math.max(diferenciaBolsas, 0); //SI ME INGRESAN MAS BOLSAS NO VOY A REGISTRAR UNA PERDIDA NEGATIVA.
        //System.out.println("CANTIDAD PERDIDA: "+cantidadPerdida);
        if (cantidadPerdida == 0 && diferenciaBolsas == 0)
            return;
        
        if (unLote.poseeUnUltimoEstacionamientoSinMerma()){
            Estacionamiento unEstacionamiento = unLote.obtenerUltimoEstacionamientoRegular();
            //Calculo del maximo de merma posible:
            Float maximoDeMermaPosibleKg = Configuracion.PORCENTAJE_MAXIMO_MERMA*unLote.obtenerPesoIngresadoConPerdidasIncluidasKg();
            //Calculo la diferencia de peso
            
            //AQUI VIENE EL FABULOSO MODULO INTELIGENTE
            Float pesoDeMermaKg = Math.min(cantidadPerdida, maximoDeMermaPosibleKg);
            if (pesoDeMermaKg > 0){
                Merma unaMerma = new Merma("Merma asociada al estacionamiento de id "+unEstacionamiento.getId()+" registrada a traves del movimiento de ID "+unMovimiento.getId(), "", usuarioActivo, unEstacionamiento, Lote.UNIDAD_MEDIDA_Tranporte, 0, Lote.UNIDAD_MEDIDA_KILOGRAMO, pesoDeMermaKg, unLote, unMovimiento);
                unMovimiento.agregarMerma(unaMerma);
                unEstacionamiento.agregarSalida(unaMerma);
                unEstacionamiento.agregarMerma(unaMerma);
                unLote.getOrdenDeProduccionAsociada().agregarEvento(unaMerma);
                unLote.agregarMerma(unaMerma);
                persistencia.persistirObjeto(unaMerma);
                this.salidas.put(unaMerma.getId(), unaMerma);
                this.mermas.put(unaMerma.getId(), unaMerma);
                this.getEventos().put(unaMerma.getIdEvento(), unaMerma);
                //EL MOVIMIENTO DEBERÍA CONOCER SU MERMA SI POSEYERA?
            }
            if (cantidadPerdida > maximoDeMermaPosibleKg){
                System.err.println("Detectada una anomalía: Perdida excesiva de peso en el lote. Generando Perdida asociada...");
                cantidadPerdida = cantidadPerdida - maximoDeMermaPosibleKg;
                
                Perdida unaPerdida = new Perdida("Perdida asociada al movimiento de id "+unMovimiento.getId(), "", usuarioActivo, unLote, unMovimiento, null, Lote.UNIDAD_MEDIDA_Tranporte, diferenciaBolsas, Lote.UNIDAD_MEDIDA_KILOGRAMO, cantidadPerdida);
                persistencia.persistirObjeto(unaPerdida);
                unLote.getOrdenDeProduccionAsociada().agregarEvento(unaPerdida);
                unLote.agregarPerdida(unaPerdida);
                this.salidas.put(unaPerdida.getId(), unaPerdida);
                this.perdidas.put(unaPerdida.getId(), unaPerdida);
                this.getEventos().put(unaPerdida.getIdEvento(), unaPerdida);
            }
        }else{
            
            //SOLO SE CALCULA LA DIFERENCIA DE PESO Y SI HAY DISCREPANCIA SE REGISTRA PERDIDA.
            System.err.println("Detectada una anomalía: Perdida excesiva de peso en el lote. Generando Perdida asociada...");

            Perdida unaPerdida = new Perdida("Perdida asociada al movimiento de id "+unMovimiento.getId(), "", usuarioActivo, unLote, unMovimiento, null, Lote.UNIDAD_MEDIDA_Tranporte, diferenciaBolsas, Lote.UNIDAD_MEDIDA_KILOGRAMO, cantidadPerdida);
            persistencia.persistirObjeto(unaPerdida);
            unLote.getOrdenDeProduccionAsociada().agregarEvento(unaPerdida);
            unLote.agregarPerdida(unaPerdida);
            this.salidas.put(unaPerdida.getId(), unaPerdida);
            this.perdidas.put(unaPerdida.getId(), unaPerdida);
            this.getEventos().put(unaPerdida.getIdEvento(), unaPerdida);
        }
    }

    public void registrarEgreso (String unaDescripcion, String unComentario, String unaUnidadMedidaTransporte, String unaCantidadUtilizada, String unaUnidadMedidaPeso, String unPesoUtilizado, Molienda unaMolienda) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        
        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");
        
        if (unaUnidadMedidaTransporte.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se selecciono una unidad de medida de transporte.");
        
        if (!Validaciones.esUnNumeroEnteroValido(unaCantidadUtilizada))
            throw new ExcepcionCargaParametros("No se ingreso una cantidad de unidades a utilizar valida");
        int cantidadUtilizada = Integer.parseInt(unaCantidadUtilizada);
        if (cantidadUtilizada<0)
            throw new ExcepcionCargaParametros("La cantidad de unidades de transporte no puede ser negativa.");
        
        if (unaUnidadMedidaPeso.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se selecciono una unidad de medida de peso.");
        Float pesoUtilizado = Organizacion.convertirAFlotante(unPesoUtilizado, "peso egresado");
        if (unaMolienda == null)
            throw new ExcepcionCargaParametros("No se seleccionó una molienda");
        if (!unaMolienda.estaRegular())
            throw new ExcepcionCargaParametros("Debe seleccionar una molienda en estado regular.");
        
        if (pesoUtilizado<=0)
            throw new ExcepcionCargaParametros("El peso utilizado no puede ser menor o igual a 0 (Cero).");
        if (pesoUtilizado>unaMolienda.getPesoDisponibleAEgresarKg())
            throw new ExcepcionCargaParametros("El peso del egreso a registrar excede el peso disponible de molienda. (Peso disponible: "+unaMolienda.getPesoDisponibleAEgresarKg()+" Kgs.)");
        
        Egreso unEgreso = new Egreso(unaDescripcion, unComentario, getUsuarioActivo(), unaMolienda, unaUnidadMedidaTransporte, cantidadUtilizada, unaUnidadMedidaPeso, pesoUtilizado);        
        unaMolienda.agregarSalida(unEgreso);
        
        persistencia.persistirObjeto(unEgreso);
        this.getEventos().put(unEgreso.getIdEvento(), unEgreso);
        unaMolienda.getOrdenDeProduccionAsociada().agregarEvento(unEgreso);
        this.salidas.put(unEgreso.getId(), unEgreso);
        this.egresos.put(unEgreso.getId(), unEgreso);
  
        //agregar egreso a los lotes
        Iterator detalles = unaMolienda.getDetallesAsociados().iterator();
        while (detalles.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) detalles.next();
            Lote unLote = unDetalle.getLoteImplicado();
            unLote.agregarEgreso(unEgreso);
        }
    }

    public void registrarEstacionamiento (ArrayList lotesAsociados, Equipamiento unEquipamiento, Calendar unaFechaOrigen, String duracion) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{

        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");

        if (lotesAsociados.isEmpty())
            throw new ExcepcionCargaParametros("No se selecciono ningún lote.");
        if (!unEquipamiento.poseeLotes(lotesAsociados))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados estan presentes en ese equipamiento.");
        if (!Validaciones.sonLotesDeYerbaCanchadaVerde(lotesAsociados))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados son de yerba canchada verde.");
        if (!Validaciones.sonLotesRegulares(lotesAsociados))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados estan en estado regular.");
        if (!Validaciones.sonLotesAnalizadosYCV(lotesAsociados))
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
        
        Estacionamiento unEstacionamiento = new Estacionamiento(fechaExtraccion, unEquipamiento, this.usuarioActivo, unaFechaOrigen);
        Estacionamiento.prepararDetalle((ArrayList) lotesAsociados, unEstacionamiento);
        
        persistencia.persistirObjeto(unEstacionamiento);
        this.getEventos().put(unEstacionamiento.getIdEvento(), unEstacionamiento);
        this.transformaciones.put(unEstacionamiento.getId(), unEstacionamiento);
        this.estacionamientos.put(unEstacionamiento.getId(), unEstacionamiento);
        
        Iterator lotesAEstacionar = lotesAsociados.iterator();
        while (lotesAEstacionar.hasNext()){
            Lote unLote = (Lote) lotesAEstacionar.next();
            unLote.estacionar();
            unLote.agregarEstacionamiento(unEstacionamiento);
            unLote.getOrdenDeProduccionAsociada().agregarEvento(unEstacionamiento);
            persistencia.modificarObjeto(unLote);
        }
    }
    
    public void registrarMolienda (ArrayList detallesAsociados, Equipamiento unEquipamiento, Calendar unaFechaOrigen, String unSector, String unTurno, OrdenDeProduccion unaOrdenProduccion) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{

        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
            throw new ExcepcionCargaParametros("Debe ingresar al sistema con un usuario valido.");

        if (detallesAsociados == null)
            throw new ExcepcionCargaParametros("No se ingreso ningun detalle.");
        if (detallesAsociados.isEmpty())
            throw new ExcepcionCargaParametros("No se selecciono ningún lote.");
        
        ArrayList lotesImplicados = DetalleTransformacion.obtenerLotesImplicados(detallesAsociados);
        
        if (!Validaciones.sonLotesDeYerbaCanchadaEstacionada(lotesImplicados))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados son de yerba canchada estacionada.");
        if (!Validaciones.sonLotesRegulares(lotesImplicados))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados estan en estado regular.");
        if (!Validaciones.sonLotesAnalizadosYCE(lotesImplicados))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados poseen un analisis de laboratorio de YCE aprobado.");
        if (!Validaciones.sonLotesDeUnaMismaOrdenDeProduccion(lotesImplicados, unaOrdenProduccion))
            throw new ExcepcionCargaParametros("No todos los lotes seleccionados pertenecen a la misma orden de producción.");
        if (unSector.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe seleccionar un sector.");
        if (unTurno.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe seleccionar un turno.");
        
        if (unEquipamiento == null)
            throw new ExcepcionCargaParametros("No se selecciono un molino.");
        if (!(unEquipamiento instanceof Molino)) 
            throw new ExcepcionCargaParametros("Debe seleccionar un molino para realizar una molienda.");

        if (unaFechaOrigen == null)
            throw new ExcepcionCargaParametros("No se especificó la fecha de origen.");
        if (unaFechaOrigen.after(Calendar.getInstance()))
            throw new ExcepcionCargaParametros("La fecha de origen no puede ser posterior a la fecha actual.");
        
        Molienda unaMolienda = new Molienda(unSector, unTurno, unEquipamiento, this.usuarioActivo, detallesAsociados);
        
        
        persistencia.persistirObjeto(unaMolienda);
        this.getEventos().put(unaMolienda.getIdEvento(), unaMolienda);
        this.transformaciones.put(unaMolienda.getId(), unaMolienda);
        this.moliendas.put(unaMolienda.getId(), unaMolienda);
        
        Iterator lotesAMoler = lotesImplicados.iterator();
        while (lotesAMoler.hasNext()){
            Lote unLote = (Lote) lotesAMoler.next();
            unLote.agregarMolienda(unaMolienda);
            unLote.getOrdenDeProduccionAsociada().agregarEvento(unaMolienda);
            
            /*Float pesoTotalKg = Organizacion.convertirUnidadPeso(unLote.getUnidadDeMedida(), unLote.getCantidadTotalPesoIngresado(), Lote.UNIDAD_MEDIDA_KILOGRAMO);
            if (unLote.getCantidadDisponibleParaMolerKg()< pesoTotalKg*Configuracion.PORCENTAJE_CONCLUSION_MOLIENDA){
                unLote.estacionar();
                persistencia.modificarObjeto(unLote);
            }*/
            
            if (unLote.getCantidadUnidadesDisponibleParaMoler()<=Configuracion.BOLSAS_CONCLUSION_MOLIENDA){
                unLote.moler();
                persistencia.modificarObjeto(unLote);
            }
        }
    }

    
    public static float convertirUnidadPeso (String unidadMedidaEntrada, float peso, String unidadMedidaSalida){
        switch (unidadMedidaEntrada){
            case "Tonelada":
                switch (unidadMedidaSalida){
                    case "Tonelada":
                        return peso;
                    case "Kilogramo":
                        return peso*1000;
                    default:
                        return peso;
                }
            case "Kilogramo":
                switch (unidadMedidaSalida){
                    case "Tonelada":
                        return peso/1000;
                    case "Kilogramo":
                        return peso;
                    default:
                        return peso;
                }                
            default:
                return peso;
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
    
        public ArrayList filtrarAuditoria(Map<String, Boolean> criterios, String unaTablaAsociada, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior, String unaCantidadDeRegistros, String unaOperacion) throws ExcepcionCargaParametros, SQLException {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioTabla = criterios.get("tabla");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioOperacion = criterios.get("operacion");
        
        if (!Validaciones.esUnNumeroEnteroValido(unaCantidadDeRegistros))
            throw new ExcepcionCargaParametros("Ingrese una cantidad de registros valida para recuperar.");
        if (criterioTabla && unaTablaAsociada.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado una tabla.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        if (criterioOperacion && unaOperacion.contains("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe seleccionar una operación.");
        
        if (!criterioTabla)
            unaTablaAsociada = "cualquiera";
        if (!criterioOperacion)
            unaOperacion = "cualquiera";
        Iterator auditoriaARevisar = this.persistencia.obtenerAuditoria(unaTablaAsociada, unaCantidadDeRegistros, unaOperacion).iterator();
        while (auditoriaARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            Auditoria unaAuditoria = (Auditoria) auditoriaARevisar.next();
            
            if (criterioTabla && sePuedeAgregar)
                sePuedeAgregar = unaAuditoria.esDeTabla(unaTablaAsociada);
            
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unaAuditoria.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            
            if (sePuedeAgregar && criterioOperacion)
                sePuedeAgregar = unaAuditoria.poseeOperacion(unaOperacion);
            
            if (sePuedeAgregar)
                retorno.add(unaAuditoria);
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
            
        if (this.usuarioActivo != null && this.usuarios.get(usuarioActivo.getId())== null)
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
    
    public ArrayList filtrarEgresos(Map<String, Boolean> criterios, Equipamiento unMolinoSeleccionado, OrdenDeProduccion unaOrdenProduccionSeleccionada, Lote unLoteSeleccionado, String estadoSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior, Molienda unaMoliendaImplicada) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioMolinoAsociado = criterios.get("molino");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioLoteAsociado = criterios.get("lote");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioMolienda = criterios.get("molienda");
        
        if (criterioMolinoAsociado && unMolinoSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un molino.");
        if (criterioMolinoAsociado && !(unMolinoSeleccionado instanceof Molino))
            throw new ExcepcionCargaParametros("Debe seleccionarse un molino.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioLoteAsociado && unLoteSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un lote.");
        if (criterioEstado && estadoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        if (criterioMolienda && unaMoliendaImplicada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una molienda.");

        Iterator egresosARevisar = this.getEgresos().keySet().iterator();
        while (egresosARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            int unId = (int) egresosARevisar.next();
            Egreso unEgreso = this.getEgresos().get(unId);
            
            if (criterioMolinoAsociado && sePuedeAgregar)
                sePuedeAgregar = unEgreso.esEnMolino(unMolinoSeleccionado);
                
            if (criterioOrdenDeProduccionAsociada && sePuedeAgregar)
                sePuedeAgregar = unEgreso.poseeOrdenDeProduccionImplicada(unaOrdenProduccionSeleccionada);
            if (criterioLoteAsociado && sePuedeAgregar)
                sePuedeAgregar = unEgreso.poseeLoteImplicado(unLoteSeleccionado);
            if (criterioEstado && sePuedeAgregar)
                sePuedeAgregar = unEgreso.poseeEstado(estadoSeleccionado);
            
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unEgreso.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            
            if (criterioMolienda && sePuedeAgregar)
                sePuedeAgregar = unEgreso.poseeEventoImplicado(unaMoliendaImplicada);
            
            if (sePuedeAgregar)
                retorno.add(unEgreso);
        }
        return retorno;
    }
    
    public ArrayList filtrarSalidas(Map<String, Boolean> criterios, Equipamiento unEquipamientoSeleccionado, OrdenDeProduccion unaOrdenProduccionSeleccionada, Lote unLoteSeleccionado, String estadoSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior, Evento unEvento, String unTipo) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioEquipamientoAsociado = criterios.get("equipamiento");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioLoteAsociado = criterios.get("lote");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioEvento = criterios.get("evento");
        Boolean criterioTipo = criterios.get("tipo");
        
        if (criterioEquipamientoAsociado && unEquipamientoSeleccionado == null)
            throw new ExcepcionCargaParametros("Debe seleccionarse un equipamiento.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioLoteAsociado && unLoteSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un lote.");
        if (criterioEstado && estadoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");
        if (criterioEvento && unEvento == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un evento.");
        if (criterioTipo && unTipo.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un tipo de salida.");

        Iterator salidasARevisar = this.getSalidas().keySet().iterator();
        while (salidasARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            int unId = (int) salidasARevisar.next();
            Salida unaSalida = this.getSalidas().get(unId);
            
            if (criterioEquipamientoAsociado && sePuedeAgregar)
                sePuedeAgregar = unaSalida.esEnEquipamiento(unEquipamientoSeleccionado);
                
            if (criterioOrdenDeProduccionAsociada && sePuedeAgregar)
                sePuedeAgregar = unaSalida.poseeOrdenDeProduccionImplicada(unaOrdenProduccionSeleccionada);
            
            if (criterioLoteAsociado && sePuedeAgregar)
                sePuedeAgregar = unaSalida.poseeLoteImplicado(unLoteSeleccionado);
            
            if (criterioEstado && sePuedeAgregar)
                sePuedeAgregar = unaSalida.poseeEstado(estadoSeleccionado);
            
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unaSalida.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            
            if (criterioEvento && sePuedeAgregar)
                sePuedeAgregar = unaSalida.poseeEventoImplicado(unEvento);
            
            if (criterioTipo && sePuedeAgregar)
                sePuedeAgregar = unaSalida.esDeTipo(unTipo);
            
            if (sePuedeAgregar)
                retorno.add(unaSalida);
        }
        return retorno;
    }
    
    public ArrayList filtrarMoliendas(Map<String, Boolean> criterios, Equipamiento unMolinoSeleccionado, OrdenDeProduccion unaOrdenProduccionSeleccionada, Lote unLoteSeleccionado, String estadoSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioMolinoAsociado = criterios.get("molino");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioLoteAsociado = criterios.get("lote");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        
        if (criterioMolinoAsociado && unMolinoSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un molino.");
        if (criterioMolinoAsociado && !(unMolinoSeleccionado instanceof Molino))
            throw new ExcepcionCargaParametros("Debe seleccionarse un molino.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioLoteAsociado && unLoteSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un lote.");
        if (criterioEstado && estadoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");

        Iterator moliendasARevisar = this.getMoliendas().keySet().iterator();
        while (moliendasARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            int unId = (int) moliendasARevisar.next();
            Molienda unaMolienda = this.getMoliendas().get(unId);
            
            if (criterioMolinoAsociado && sePuedeAgregar)
                sePuedeAgregar = unaMolienda.esEnMolino(unMolinoSeleccionado);
                
            if (criterioOrdenDeProduccionAsociada && sePuedeAgregar)
                sePuedeAgregar = unaMolienda.poseeOrdenDeProduccionImplicada(unaOrdenProduccionSeleccionada);
            if (criterioLoteAsociado && sePuedeAgregar)
                sePuedeAgregar = unaMolienda.poseeLoteImplicado(unLoteSeleccionado);
            if (criterioEstado && sePuedeAgregar)
                sePuedeAgregar = unaMolienda.poseeEstado(estadoSeleccionado);
            
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unaMolienda.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            
            
            if (sePuedeAgregar)
                retorno.add(unaMolienda);
        }
        return retorno;
    }
    
    public ArrayList filtrarMoliendasNoEgresadas(Map<String, Boolean> criterios, Equipamiento unMolinoSeleccionado, OrdenDeProduccion unaOrdenProduccionSeleccionada, Lote unLoteSeleccionado, String estadoSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioMolinoAsociado = criterios.get("molino");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioLoteAsociado = criterios.get("lote");
        Boolean criterioEstado = criterios.get("estado");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        
        if (criterioMolinoAsociado && unMolinoSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un molino.");
        if (criterioMolinoAsociado && !(unMolinoSeleccionado instanceof Molino))
            throw new ExcepcionCargaParametros("Debe seleccionarse un molino.");
        if (criterioOrdenDeProduccionAsociada && unaOrdenProduccionSeleccionada == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado una orden de producción.");
        if (criterioLoteAsociado && unLoteSeleccionado == null)
            throw new ExcepcionCargaParametros("No se ha seleccionado un lote.");
        if (criterioEstado && estadoSeleccionado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("No se ha seleccionado un estado.");
        
        if (criterioFechaOrigen && (fechaOrigenInferior == null || fechaOrigenSuperior == null))
            throw new ExcepcionCargaParametros("Verifique las fechas de Origen Inferior y Superior ingresadas");

        Iterator moliendasARevisar = this.getMoliendas().keySet().iterator();
        while (moliendasARevisar.hasNext()){
            boolean sePuedeAgregar = true;
            int unId = (int) moliendasARevisar.next();
            Molienda unaMolienda = this.getMoliendas().get(unId);
            
            if (criterioMolinoAsociado && sePuedeAgregar)
                sePuedeAgregar = unaMolienda.esEnMolino(unMolinoSeleccionado);
                
            if (criterioOrdenDeProduccionAsociada && sePuedeAgregar)
                sePuedeAgregar = unaMolienda.poseeOrdenDeProduccionImplicada(unaOrdenProduccionSeleccionada);
            if (criterioLoteAsociado && sePuedeAgregar)
                sePuedeAgregar = unaMolienda.poseeLoteImplicado(unLoteSeleccionado);
            if (criterioEstado && sePuedeAgregar)
                sePuedeAgregar = unaMolienda.poseeEstado(estadoSeleccionado);
            
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unaMolienda.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            
            if (sePuedeAgregar)
                sePuedeAgregar = unaMolienda.getPesoDisponibleAEgresarKg()>0;
            if (sePuedeAgregar) 
                retorno.add(unaMolienda);
        }
        return retorno;
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
    
public ArrayList filtrarEventosDeOrdenDeProduccion(Map<String, Boolean> criterios, OrdenDeProduccion unaOrdenProduccionSeleccionada, String estadoSeleccionado, String unTipoDeEventoSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) throws ExcepcionCargaParametros {
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
                    case "Movimientos":
                        sePuedeAgregar = unEvento instanceof MovimientoInternoMateriaPrima;
                        break;
                    case "Analisis de laboratorios":
                        sePuedeAgregar = unEvento instanceof AnalisisLaboratorio;
                        break;
                    case "Transformaciones":
                        sePuedeAgregar = unEvento instanceof Transformacion;
                        break;
                    case "Estacionamientos":
                        sePuedeAgregar = unEvento instanceof Estacionamiento;
                        break;
                    case "Moliendas":
                        sePuedeAgregar = unEvento instanceof Molienda;
                        break;
                    case "Salidas":
                        sePuedeAgregar = unEvento instanceof Salida;
                        break;
                    case "Egresos":
                        sePuedeAgregar = unEvento instanceof Egreso;
                        break;
                    case "Mermas":
                        sePuedeAgregar = unEvento instanceof Merma;
                        break;
                    case "Perdidas":
                        sePuedeAgregar = unEvento instanceof Perdida;
                        break;
                    case "Compras":
                        sePuedeAgregar = unEvento instanceof OrdenDeCompra;
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

public ArrayList filtrarLotes(Map<String, Boolean> criterios, Equipamiento unEquipamientoAFiltrar, String unaEtiqueta, OrdenDeProduccion unaOrdenProduccionSeleccionada, OrdenDeCompra unaOrdenCompraSeleccionada, Proveedor unProveedorSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior, String unTipoLote) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioEtiqueta = criterios.get("etiqueta");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioOrdenDeCompra = criterios.get("ordenCompra");
        Boolean criterioProveedorOrdenCompra = criterios.get("proveedorOrdenCompra");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioEquipamiento = criterios.get("equipamiento");
        Boolean criterioTipoLote = criterios.get("tipoLote");
        
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
        if (criterioTipoLote && unTipoLote.equals("Seleccionar")){
            throw new ExcepcionCargaParametros("Debe seleccionar un tipo de lote.");
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
                if (sePuedeAgregar && criterioTipoLote){
                    sePuedeAgregar = unLote.esDeTipo(unTipoLote);
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
                if (sePuedeAgregar && criterioTipoLote){
                    sePuedeAgregar = unLote.esDeTipo(unTipoLote);
                }
                if (sePuedeAgregar)
                    retorno.add(unLote);
            }
            return retorno;
        }
            
        
    }

public ArrayList filtrarLotesOmitiendoLotes(Map<String, Boolean> criterios, Equipamiento unEquipamientoAFiltrar, String unaEtiqueta, OrdenDeProduccion unaOrdenProduccionSeleccionada, OrdenDeCompra unaOrdenCompraSeleccionada, Proveedor unProveedorSeleccionado, Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior, ArrayList lotesAOmitir, String unTipoLote) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        
        Boolean criterioEtiqueta = criterios.get("etiqueta");
        Boolean criterioOrdenDeProduccionAsociada = criterios.get("ordenProduccion");
        Boolean criterioOrdenDeCompra = criterios.get("ordenCompra");
        Boolean criterioProveedorOrdenCompra = criterios.get("proveedorOrdenCompra");
        Boolean criterioFechaOrigen = criterios.get("fechaOrigen");
        Boolean criterioEquipamiento = criterios.get("equipamiento");
        Boolean criterioTipoLote = criterios.get("tipoLote");
        
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
        if (criterioTipoLote && unTipoLote.equals("Seleccionar")){
            throw new ExcepcionCargaParametros("Debe seleccionar un tipo de lote.");
        }
        Iterator lotesARecorrer;
        
        if (criterioEquipamiento){
            lotesARecorrer = unEquipamientoAFiltrar.getLotesAsociadosNoAnulados().iterator();
            while (lotesARecorrer.hasNext()){
                boolean sePuedeAgregar = true;
                Lote unLote = (Lote) lotesARecorrer.next();
                sePuedeAgregar = !lotesAOmitir.contains(unLote);
                if (sePuedeAgregar && criterioOrdenDeProduccionAsociada)
                    sePuedeAgregar = unLote.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
                if (sePuedeAgregar && criterioOrdenDeCompra)
                    sePuedeAgregar = unLote.poseeOrdenDeCompraAsociada(unaOrdenCompraSeleccionada);
                if (sePuedeAgregar && criterioProveedorOrdenCompra)
                    sePuedeAgregar = unLote.poseeProveedorAsociado(unProveedorSeleccionado);
                if (sePuedeAgregar && criterioFechaOrigen)
                    sePuedeAgregar = unLote.ultimoMovimientoEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
                if (sePuedeAgregar && criterioTipoLote)
                    sePuedeAgregar = unLote.esDeTipo(unTipoLote);
                
                if (sePuedeAgregar)
                    retorno.add(unLote);
            }
            return retorno;
        }else{
            lotesARecorrer = this.lotes.keySet().iterator();
            while (lotesARecorrer.hasNext()){
                Lote unLote = this.lotes.get((int) lotesARecorrer.next());
                boolean sePuedeAgregar = true;
                sePuedeAgregar = !lotesAOmitir.contains(unLote);
                if (sePuedeAgregar && criterioOrdenDeProduccionAsociada)
                    sePuedeAgregar = unLote.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
                if (sePuedeAgregar && criterioOrdenDeCompra)
                    sePuedeAgregar = unLote.poseeOrdenDeCompraAsociada(unaOrdenCompraSeleccionada);
                if (sePuedeAgregar && criterioProveedorOrdenCompra)
                    sePuedeAgregar = unLote.poseeProveedorAsociado(unProveedorSeleccionado);
                if (sePuedeAgregar && criterioFechaOrigen)
                    sePuedeAgregar = unLote.ultimoMovimientoEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
                if (sePuedeAgregar && criterioTipoLote)
                    sePuedeAgregar = unLote.esDeTipo(unTipoLote);
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
                sePuedeAgregar = unaOrdenDeCompra.poseeProveedor(unProveedorSeleccionado);
            if (sePuedeAgregar && criterioFechaOrigen)
                sePuedeAgregar = unaOrdenDeCompra.fechaOrigenEstaEntre(fechaOrigenInferior, fechaOrigenSuperior);
            if (sePuedeAgregar && criterioEstado)
                sePuedeAgregar = unaOrdenDeCompra.poseeEstado(unEstado);
            if (sePuedeAgregar)
                retorno.add(unaOrdenDeCompra);
        }
        return retorno;
    }
    
    public ArrayList filtrarGenerico(String [] criterios, Map<String, Boolean> criteriosSeleccionados, Map<String, Object> objetosAsociadosACriterios, Map<Integer, Object> objetosAFiltrar) throws ExcepcionCargaParametros {
        ArrayList retorno = new ArrayList();
        //Validaciones
        for (int i=0; i<criterios.length; i++){
            Boolean unCriterio = criteriosSeleccionados.get(criterios[i]);
            Object unObjeto = objetosAsociadosACriterios.get(criterios[i]);
            if (unCriterio && (unObjeto == null || (unObjeto instanceof String && (unObjeto.equals("") || unObjeto.equals("Seleccionar"))) || (unObjeto instanceof ArrayList && ((ArrayList)unObjeto).isEmpty())))
                throw new ExcepcionCargaParametros("No se ha seleccionado un valor para el criterio: "+criterios[i]);
            if (unCriterio && unObjeto instanceof ArrayList && ((ArrayList)unObjeto).size()>=2 && (((ArrayList)unObjeto).get(0) == null || ((ArrayList)unObjeto).get(1) == null))
                throw new ExcepcionCargaParametros("No se ha seleccionado un valor para el criterio: "+criterios[i]);
        }
        //Evaluaciones
        for (int i=0;i<criterios.length;i++){}
        Iterator objetos = objetosAFiltrar.keySet().iterator();
        while (objetos.hasNext()){
            Boolean sePuedeAgregar = true;
            int unId = (int) objetos.next();
            Filtrable unObjetoFiltrable = (Filtrable) objetosAFiltrar.get(unId);
            for (int i=0;i<criterios.length && sePuedeAgregar;i++){
                boolean seDebeEvaluarCriterio = criteriosSeleccionados.get(criterios[i]);
                if (seDebeEvaluarCriterio){
                    String unCriterio = criterios[i];
                    Object unObjeto = objetosAsociadosACriterios.get(criterios[i]);
                    sePuedeAgregar = unObjetoFiltrable.cumpleCriterio(unCriterio, unObjeto);
                }
            }
            if (sePuedeAgregar)
                retorno.add(unObjetoFiltrable);
        }
        return retorno;
    }
    
    public static ArrayList desmenuzarAnalisis (ArrayList unaListaAnalisis, String unDatoACensar){
        ArrayList retorno = new ArrayList();
        Iterator recorredorAnalisis = unaListaAnalisis.iterator();
        while (recorredorAnalisis.hasNext()){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) recorredorAnalisis.next();
            switch (unDatoACensar){
                case AnalisisLaboratorio.ATRIBUTO_PORCENTAJE_HOJA:
                    retorno.add(unAnalisis.getPorcentajeHoja());
                    break;
                case AnalisisLaboratorio.ATRIBUTO_PORCENTAJE_HUMEDAD:
                    retorno.add(unAnalisis.getPorcentajeHumedad());
                    break;
                case AnalisisLaboratorio.ATRIBUTO_PORCENTAJE_PALO:
                    retorno.add(unAnalisis.getPorcentajePalo());
                    break;
                case AnalisisLaboratorio.ATRIBUTO_PORCENTAJE_POLVO:
                    retorno.add(unAnalisis.getPorcentajePolvo());
                    break;
                case AnalisisLaboratorio.ATRIBUTO_PORCENTAJE_SEMILLA:
                    retorno.add(unAnalisis.getPorcentajeSemilla());
                    break;
                default:
                    retorno.add(0f);
            }
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
    public void registrarOrdenDeCompra(String unTipoLote, OrdenDeProduccion ordenProduccionSeleccionada, Proveedor proveedorSeleccionado, String unaUnidadMedida,String unaCantidadAComprar, String unCostoDeCompra) throws ExcepcionCargaParametros, SQLException {
        
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
        if (unTipoLote.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("Debe seleccionar un tipo de lote.");
        
        float cantidadAComprar = Organizacion.convertirAFlotante(unaCantidadAComprar, "cantidad a comprar");
        float costoDeCompra = Organizacion.convertirAFlotante(unCostoDeCompra, "costo de compra");
        
        if (cantidadAComprar <=0)
            throw new ExcepcionCargaParametros("La orden de compra no puede poseer una cantidad negativa.");
        if (cantidadAComprar <=0)
            throw new ExcepcionCargaParametros("La orden de compra no puede poseer un costo de compra negativo.");        
        if (Organizacion.convertirUnidadPeso(ordenProduccionSeleccionada.getUnidadDeMedida(), ordenProduccionSeleccionada.getCantidadAProducir(), unaUnidadMedida)< cantidadAComprar)
            throw new ExcepcionCargaParametros("No se puede registrar una compra por una cantidad de peso mayor a la indicada en la orden de producción.");
        if (Organizacion.convertirUnidadPeso(ordenProduccionSeleccionada.getUnidadDeMedida(), ordenProduccionSeleccionada.getPesoRestanteAComprar(), unaUnidadMedida)< cantidadAComprar)
            throw new ExcepcionCargaParametros("La orden de producción tiene un remanente a comprar menor al ingresado.");
        
        OrdenDeCompra unaOrdenDeCompra = new OrdenDeCompra(this.getUsuarioActivo(), cantidadAComprar, unaUnidadMedida, costoDeCompra, proveedorSeleccionado, ordenProduccionSeleccionada, unTipoLote);
        persistencia.persistirObjeto(unaOrdenDeCompra);
        this.ordenesCompra.put(unaOrdenDeCompra.getId(), unaOrdenDeCompra);
        ordenProduccionSeleccionada.agregarEvento(unaOrdenDeCompra);
        this.getEventos().put(unaOrdenDeCompra.getIdEvento(), unaOrdenDeCompra);
        
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

    public static float convertirAFlotante(String unaCadena, String unCampo) throws ExcepcionCargaParametros {
        if (!Validaciones.esUnNumeroFraccionarioValido(unaCadena))
            throw new ExcepcionCargaParametros("El valor ingresado para "+unCampo+" no posee un formato valido (Utilice solo numeros y una coma)");
        unaCadena = unaCadena.replace(".", "");
        unaCadena = unaCadena.replace(",", ".");
        return Float.parseFloat(unaCadena);
    }



    public static String expresarCalendario (Calendar unaFecha){
        return ( new SimpleDateFormat( "dd/MM/yyyy" ) ).format( unaFecha.getTime() );
    }
    
    public static String expresarFechaArchivo() {
        return ( new SimpleDateFormat( "yyyy-MM-dd" ) ).format( Calendar.getInstance().getTime() );
    }
    
    public static String expresarFechaArchivo(Calendar unCalendario) {
        return ( new SimpleDateFormat( "yyyy-MM-dd" ) ).format( unCalendario.getTime() );
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

    public void darDeBajaUnCriterio(CriterioAnalisisLaboratorio unCriterio) throws SQLException, ExcepcionPersistencia {
        unCriterio.darDeBaja();
        persistencia.modificarObjeto(unCriterio);
    }

    public void modificarCriterioDeAnalisis(CriterioAnalisisLaboratorio unCriterio, String unEstado) throws SQLException, ExcepcionPersistencia, ExcepcionCargaParametros {
        if (unEstado.equals("Selecionar"))
            throw new ExcepcionCargaParametros("Debe seleccionar un estado para realizar una modificación");
        unCriterio.setEstado(unEstado);
        persistencia.modificarObjeto(unCriterio);
    }

    public DetalleTransformacion generarDetalleMoliendaEnKg(String unaCantidadUnidadesUtilizadasEnKg, Lote unLoteImplicado) throws ExcepcionCargaParametros{
        DetalleTransformacion retorno = null;
        
        //Validaciones de no nulidad
        //<editor-fold defaultstate="collapsed">
        if (unaCantidadUnidadesUtilizadasEnKg.equals(""))
            throw new ExcepcionCargaParametros("Debe ingresar una unidad de unidades de transporte utilizadas.");
        if (unLoteImplicado == null)
            throw new ExcepcionCargaParametros("Debe ingresar un lote para registrar un detalle.");
        //</editor-fold>
        //Validaciones logicas
        //<editor-fold defaultstate="collapsed">
        if (!Validaciones.esUnNumeroEnteroValido(unaCantidadUnidadesUtilizadasEnKg))
            throw new ExcepcionCargaParametros("La cantidad de unidades a utilizar no esta en un formato valido (Solo numeros).");
        int unaCantidad = Integer.parseInt(unaCantidadUnidadesUtilizadasEnKg);
        if (unaCantidad <= 0)
            throw new ExcepcionCargaParametros("La cantidad de unidades ingresadas debe ser mayor que 0(Cero).");
        if (unaCantidad > unLoteImplicado.getCantidadUnidadesDisponibleParaMoler())
            throw new ExcepcionCargaParametros("El peso a utilizar excede el remanente disponible para moler. (Peso disponible: "+unLoteImplicado.getCantidadDisponibleParaMolerKg()+" Kg.)");
        
        Float unPeso = unaCantidad * unLoteImplicado.obtenerRazonKgBolsa();
        
        
        if (unPeso <= 0)
            throw new ExcepcionCargaParametros("El peso a utilizar debe ser mayor que 0 (Cero).");
        if (unPeso > unLoteImplicado.getCantidadDisponibleParaMolerKg())
            throw new ExcepcionCargaParametros("El peso a utilizar excede el remanente disponible para moler. (Peso disponible: "+unLoteImplicado.getCantidadDisponibleParaMolerKg()+" Kg.)");
        
        //</editor-fold>
        retorno = new DetalleTransformacion(unLoteImplicado.getUnidadDeMedidaTransporte(), unaCantidad, Lote.UNIDAD_MEDIDA_KILOGRAMO, unPeso, unLoteImplicado);
        return retorno;
    }
    
    public void cargaInicial(){
        boolean comentariosHabilitados = true;
        if (!this.persistencia.seCargoInicialmenteLosCampos()){
            try {
                
                System.out.println("Detectada ausencia de tuplas iniciales, realizando carga...");
                Calendar unaFechaOrigen; 
                AnalisisLaboratorio unAnalisis;
                Lote unLoteAMover;
                String unTipoLote;
                String pesoSalida;
                String pesoEntrada;
                Float unPorcentajePaloInferior;
                Float unPorcentajePaloSuperior;
                String unPorcentajePalo;

                Float unPorcentajeSemillaInferior;
                Float unPorcentajeSemillaSuperior;
                String unPorcentajeSemilla;
                
                Float unPorcentajePolvoInferior;
                Float unPorcentajePolvoSuperior;
                String unPorcentajePolvo;
                
                Float unPorcentajeHumedadInferior;
                Float unPorcentajeHumedadSuperior;
                String unPorcentajeHumedad;
                
                Float unPorcentajeHojaInferior;
                Float unPorcentajeHojaSuperior;
                String unPorcentajeHoja;
            //Carga analisis
            //<editor-fold defaultstate="collapsed">
                //Analisis N° 1: Debe estar aprobado.
                //<editor-fold defaultstate="collapsed">
                if (comentariosHabilitados){
                    System.out.println("carga de analisis de laboratorio");
                    System.out.println("Analisis N° 1");
                }
                Laboratorio unLaboratorio = (Laboratorio) this.equipamientos.get(1);
                CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(4);
                OrdenDeCompra unaOrdenDeCompra = this.ordenesCompra.get(1);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -6);
                
                unPorcentajePaloInferior = unCriterio.getPorcentajePaloLimiteInferior();
                unPorcentajePaloSuperior = unCriterio.getPorcentajePaloLimiteSuperior();
                unPorcentajePalo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePaloInferior+(Float)(unPorcentajePaloSuperior-unPorcentajePaloInferior)/2);
                
                unPorcentajeSemillaInferior = unCriterio.getPorcentajeSemillaLimiteInferior();
                unPorcentajeSemillaSuperior = unCriterio.getPorcentajeSemillaLimiteSuperior();
                unPorcentajeSemilla = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeSemillaInferior+(Float)(unPorcentajeSemillaSuperior-unPorcentajeSemillaInferior)/2);
                
                unPorcentajePolvoInferior = unCriterio.getPorcentajePolvoLimiteInferior();
                unPorcentajePolvoSuperior = unCriterio.getPorcentajePolvoLimiteSuperior();
                unPorcentajePolvo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePolvoInferior+(Float)(unPorcentajePolvoSuperior-unPorcentajePolvoInferior)/2);
                
                unPorcentajeHumedadInferior = unCriterio.getPorcentajeHumedadLimiteInferior();
                unPorcentajeHumedadSuperior = unCriterio.getPorcentajeHumedadLimiteSuperior();
                unPorcentajeHumedad = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHumedadInferior+(Float)(unPorcentajeHumedadSuperior-unPorcentajeHumedadInferior)/2);
                
                unPorcentajeHojaInferior = unCriterio.getPorcentajeHojaLimiteInferior();
                unPorcentajeHojaSuperior = unCriterio.getPorcentajeHojaLimiteSuperior();
                unPorcentajeHoja = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHojaInferior+(Float)(unPorcentajeHojaSuperior-unPorcentajeHojaInferior)/2);
                
                registrarAnalisisDeLaboratorio(unCriterio.getPuntosNegros(), unCriterio.getTorrada(), unCriterio.getColor(), unCriterio.getAroma(), unCriterio.getTacto(), unCriterio.getDegustacion(), unaFechaOrigen, null, unLaboratorio, unPorcentajePalo, unPorcentajeSemilla, unPorcentajePolvo, unPorcentajeHumedad, unPorcentajeHoja, unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                unAnalisis = analisisLaboratorio.get(1);
                
                /*System.out.println("Analisis n° "+unAnalisis.getId()+", conclusion: "+unAnalisis.getConclusion());
                if (unAnalisis.estaRechazado())
                    System.out.println(unAnalisis.getCriterioAsociado().generarDetalleDeResultadoDeAnalisis(unAnalisis));
                */
                
                //</editor-fold>
                //Analisis N° 2: Debe estar aprobado.
                //<editor-fold defaultstate="collapsed">
                if (comentariosHabilitados)
                    System.out.println("Carga de análisis N°2");
                unLaboratorio = (Laboratorio) this.equipamientos.get(1);
                unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(5);
                unaOrdenDeCompra = this.ordenesCompra.get(2);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -6);
                
                unPorcentajePaloInferior = unCriterio.getPorcentajePaloLimiteInferior();
                unPorcentajePaloSuperior = unCriterio.getPorcentajePaloLimiteSuperior();
                unPorcentajePalo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePaloInferior+(Float)(unPorcentajePaloSuperior-unPorcentajePaloInferior)/2);
                
                unPorcentajeSemillaInferior = unCriterio.getPorcentajeSemillaLimiteInferior();
                unPorcentajeSemillaSuperior = unCriterio.getPorcentajeSemillaLimiteSuperior();
                unPorcentajeSemilla = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeSemillaInferior+(Float)(unPorcentajeSemillaSuperior-unPorcentajeSemillaInferior)/2);
                
                unPorcentajePolvoInferior = unCriterio.getPorcentajePolvoLimiteInferior();
                unPorcentajePolvoSuperior = unCriterio.getPorcentajePolvoLimiteSuperior();
                unPorcentajePolvo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePolvoInferior+(Float)(unPorcentajePolvoSuperior-unPorcentajePolvoInferior)/2);
                
                unPorcentajeHumedadInferior = unCriterio.getPorcentajeHumedadLimiteInferior();
                unPorcentajeHumedadSuperior = unCriterio.getPorcentajeHumedadLimiteSuperior();
                unPorcentajeHumedad = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHumedadInferior+(Float)(unPorcentajeHumedadSuperior-unPorcentajeHumedadInferior)/2);
                
                unPorcentajeHojaInferior = unCriterio.getPorcentajeHojaLimiteInferior();
                unPorcentajeHojaSuperior = unCriterio.getPorcentajeHojaLimiteSuperior();
                unPorcentajeHoja = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHojaInferior+(Float)(unPorcentajeHojaSuperior-unPorcentajeHojaInferior)/2);
                
                registrarAnalisisDeLaboratorio(unCriterio.getPuntosNegros(), unCriterio.getTorrada(), unCriterio.getColor(), unCriterio.getAroma(), unCriterio.getTacto(), unCriterio.getDegustacion(), unaFechaOrigen, null, unLaboratorio, unPorcentajePalo, unPorcentajeSemilla, unPorcentajePolvo, unPorcentajeHumedad, unPorcentajeHoja, unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                
                //registrarAnalisisDeLaboratorio("SI", "SI", "VERDE OSCURO", "TIPICO", "CRUJIENTE", "SI", unaFechaOrigen, null, unLaboratorio, "5,0", "5,0", "5,0", "15,0", "85,0", unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                unAnalisis = analisisLaboratorio.get(2);
                //</editor-fold>                
                //Analisis N° 3: Debe estar aprobado.
                //<editor-fold defaultstate="collapsed">
                unLaboratorio = (Laboratorio) this.equipamientos.get(1);
                unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(5);
                unaOrdenDeCompra = this.ordenesCompra.get(2);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -6);
                
                unPorcentajePaloInferior = unCriterio.getPorcentajePaloLimiteInferior();
                unPorcentajePaloSuperior = unCriterio.getPorcentajePaloLimiteSuperior();
                unPorcentajePalo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePaloInferior+(Float)(unPorcentajePaloSuperior-unPorcentajePaloInferior)/2);
                
                unPorcentajeSemillaInferior = unCriterio.getPorcentajeSemillaLimiteInferior();
                unPorcentajeSemillaSuperior = unCriterio.getPorcentajeSemillaLimiteSuperior();
                unPorcentajeSemilla = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeSemillaInferior+(Float)(unPorcentajeSemillaSuperior-unPorcentajeSemillaInferior)/2);
                
                unPorcentajePolvoInferior = unCriterio.getPorcentajePolvoLimiteInferior();
                unPorcentajePolvoSuperior = unCriterio.getPorcentajePolvoLimiteSuperior();
                unPorcentajePolvo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePolvoInferior+(Float)(unPorcentajePolvoSuperior-unPorcentajePolvoInferior)/2);
                
                unPorcentajeHumedadInferior = unCriterio.getPorcentajeHumedadLimiteInferior();
                unPorcentajeHumedadSuperior = unCriterio.getPorcentajeHumedadLimiteSuperior();
                unPorcentajeHumedad = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHumedadInferior+(Float)(unPorcentajeHumedadSuperior-unPorcentajeHumedadInferior)/2);
                
                unPorcentajeHojaInferior = unCriterio.getPorcentajeHojaLimiteInferior();
                unPorcentajeHojaSuperior = unCriterio.getPorcentajeHojaLimiteSuperior();
                unPorcentajeHoja = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHojaInferior+(Float)(unPorcentajeHojaSuperior-unPorcentajeHojaInferior)/2);
                
                registrarAnalisisDeLaboratorio(unCriterio.getPuntosNegros(), unCriterio.getTorrada(), unCriterio.getColor(), unCriterio.getAroma(), unCriterio.getTacto(), unCriterio.getDegustacion(), unaFechaOrigen, null, unLaboratorio, unPorcentajePalo, unPorcentajeSemilla, unPorcentajePolvo, unPorcentajeHumedad, unPorcentajeHoja, unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                //registrarAnalisisDeLaboratorio("SI", "SI", "VERDE OSCURO", "TIPICO", "CRUJIENTE", "SI", unaFechaOrigen, null, unLaboratorio, "5,0", "5,0", "5,0", "15,0", "85,0", unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                unAnalisis = analisisLaboratorio.get(3);
                //</editor-fold>
                //Analisis N° 4: Debe estar rechazado.
                //<editor-fold defaultstate="collapsed">
                unLaboratorio = (Laboratorio) this.equipamientos.get(1);
                unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(4);
                unaOrdenDeCompra = this.ordenesCompra.get(3);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -6);
                registrarAnalisisDeLaboratorio("SI", "SI", "VERDE OSCURO", "TIPICO", "CRUJIENTE", "SI", unaFechaOrigen, null, unLaboratorio, "10,0", "5,7", "4,3", "6,7", "80,0", unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                unAnalisis = analisisLaboratorio.get(4);
                //</editor-fold>
                //Analisis N° 5: Debe estar aprobado.
                //<editor-fold defaultstate="collapsed">
                unLaboratorio = (Laboratorio) this.equipamientos.get(1);
                unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(4);
                unaOrdenDeCompra = this.ordenesCompra.get(3);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -6);
                
                unPorcentajePaloInferior = unCriterio.getPorcentajePaloLimiteInferior();
                unPorcentajePaloSuperior = unCriterio.getPorcentajePaloLimiteSuperior();
                unPorcentajePalo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePaloInferior+(Float)(unPorcentajePaloSuperior-unPorcentajePaloInferior)/2);
                
                unPorcentajeSemillaInferior = unCriterio.getPorcentajeSemillaLimiteInferior();
                unPorcentajeSemillaSuperior = unCriterio.getPorcentajeSemillaLimiteSuperior();
                unPorcentajeSemilla = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeSemillaInferior+(Float)(unPorcentajeSemillaSuperior-unPorcentajeSemillaInferior)/2);
                
                unPorcentajePolvoInferior = unCriterio.getPorcentajePolvoLimiteInferior();
                unPorcentajePolvoSuperior = unCriterio.getPorcentajePolvoLimiteSuperior();
                unPorcentajePolvo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePolvoInferior+(Float)(unPorcentajePolvoSuperior-unPorcentajePolvoInferior)/2);
                
                unPorcentajeHumedadInferior = unCriterio.getPorcentajeHumedadLimiteInferior();
                unPorcentajeHumedadSuperior = unCriterio.getPorcentajeHumedadLimiteSuperior();
                unPorcentajeHumedad = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHumedadInferior+(Float)(unPorcentajeHumedadSuperior-unPorcentajeHumedadInferior)/2);
                
                unPorcentajeHojaInferior = unCriterio.getPorcentajeHojaLimiteInferior();
                unPorcentajeHojaSuperior = unCriterio.getPorcentajeHojaLimiteSuperior();
                unPorcentajeHoja = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHojaInferior+(Float)(unPorcentajeHojaSuperior-unPorcentajeHojaInferior)/2);
                
                registrarAnalisisDeLaboratorio(unCriterio.getPuntosNegros(), unCriterio.getTorrada(), unCriterio.getColor(), unCriterio.getAroma(), unCriterio.getTacto(), unCriterio.getDegustacion(), unaFechaOrigen, null, unLaboratorio, unPorcentajePalo, unPorcentajeSemilla, unPorcentajePolvo, unPorcentajeHumedad, unPorcentajeHoja, unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                //registrarAnalisisDeLaboratorio("SI", "SI", "VERDE OSCURO", "TIPICO", "CRUJIENTE", "SI", unaFechaOrigen, null, unLaboratorio, "5,0", "5,0", "5,0", "15,0", "85,0", unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                unAnalisis = analisisLaboratorio.get(5);
                //</editor-fold>
                //Analisis N° 6: Debe estar aprobado.
                //<editor-fold defaultstate="collapsed">
                unLaboratorio = (Laboratorio) this.equipamientos.get(1);
                unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(5);
                unaOrdenDeCompra = this.ordenesCompra.get(4);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -6);
                
                unPorcentajePaloInferior = unCriterio.getPorcentajePaloLimiteInferior();
                unPorcentajePaloSuperior = unCriterio.getPorcentajePaloLimiteSuperior();
                unPorcentajePalo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePaloInferior+(Float)(unPorcentajePaloSuperior-unPorcentajePaloInferior)/2);
                
                unPorcentajeSemillaInferior = unCriterio.getPorcentajeSemillaLimiteInferior();
                unPorcentajeSemillaSuperior = unCriterio.getPorcentajeSemillaLimiteSuperior();
                unPorcentajeSemilla = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeSemillaInferior+(Float)(unPorcentajeSemillaSuperior-unPorcentajeSemillaInferior)/2);
                
                unPorcentajePolvoInferior = unCriterio.getPorcentajePolvoLimiteInferior();
                unPorcentajePolvoSuperior = unCriterio.getPorcentajePolvoLimiteSuperior();
                unPorcentajePolvo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePolvoInferior+(Float)(unPorcentajePolvoSuperior-unPorcentajePolvoInferior)/2);
                
                unPorcentajeHumedadInferior = unCriterio.getPorcentajeHumedadLimiteInferior();
                unPorcentajeHumedadSuperior = unCriterio.getPorcentajeHumedadLimiteSuperior();
                unPorcentajeHumedad = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHumedadInferior+(Float)(unPorcentajeHumedadSuperior-unPorcentajeHumedadInferior)/2);
                
                unPorcentajeHojaInferior = unCriterio.getPorcentajeHojaLimiteInferior();
                unPorcentajeHojaSuperior = unCriterio.getPorcentajeHojaLimiteSuperior();
                unPorcentajeHoja = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHojaInferior+(Float)(unPorcentajeHojaSuperior-unPorcentajeHojaInferior)/2);
                
                registrarAnalisisDeLaboratorio(unCriterio.getPuntosNegros(), unCriterio.getTorrada(), unCriterio.getColor(), unCriterio.getAroma(), unCriterio.getTacto(), unCriterio.getDegustacion(), unaFechaOrigen, null, unLaboratorio, unPorcentajePalo, unPorcentajeSemilla, unPorcentajePolvo, unPorcentajeHumedad, unPorcentajeHoja, unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                //registrarAnalisisDeLaboratorio("SI", "SI", "VERDE OSCURO", "TIPICO", "CRUJIENTE", "SI", unaFechaOrigen, null, unLaboratorio, "5,0", "5,0", "5,0", "15,0", "85,0", unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                unAnalisis = analisisLaboratorio.get(6);
                //</editor-fold>
                //Analisis N° 7: Debe estar aprobado.
                //<editor-fold defaultstate="collapsed">
                unLaboratorio = (Laboratorio) this.equipamientos.get(1);
                unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(4);
                unaOrdenDeCompra = this.ordenesCompra.get(5);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -6);
                
                unPorcentajePaloInferior = unCriterio.getPorcentajePaloLimiteInferior();
                unPorcentajePaloSuperior = unCriterio.getPorcentajePaloLimiteSuperior();
                unPorcentajePalo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePaloInferior+(Float)(unPorcentajePaloSuperior-unPorcentajePaloInferior)/2);
                
                unPorcentajeSemillaInferior = unCriterio.getPorcentajeSemillaLimiteInferior();
                unPorcentajeSemillaSuperior = unCriterio.getPorcentajeSemillaLimiteSuperior();
                unPorcentajeSemilla = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeSemillaInferior+(Float)(unPorcentajeSemillaSuperior-unPorcentajeSemillaInferior)/2);
                
                unPorcentajePolvoInferior = unCriterio.getPorcentajePolvoLimiteInferior();
                unPorcentajePolvoSuperior = unCriterio.getPorcentajePolvoLimiteSuperior();
                unPorcentajePolvo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePolvoInferior+(Float)(unPorcentajePolvoSuperior-unPorcentajePolvoInferior)/2);
                
                unPorcentajeHumedadInferior = unCriterio.getPorcentajeHumedadLimiteInferior();
                unPorcentajeHumedadSuperior = unCriterio.getPorcentajeHumedadLimiteSuperior();
                unPorcentajeHumedad = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHumedadInferior+(Float)(unPorcentajeHumedadSuperior-unPorcentajeHumedadInferior)/2);
                
                unPorcentajeHojaInferior = unCriterio.getPorcentajeHojaLimiteInferior();
                unPorcentajeHojaSuperior = unCriterio.getPorcentajeHojaLimiteSuperior();
                unPorcentajeHoja = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHojaInferior+(Float)(unPorcentajeHojaSuperior-unPorcentajeHojaInferior)/2);
                
                registrarAnalisisDeLaboratorio(unCriterio.getPuntosNegros(), unCriterio.getTorrada(), unCriterio.getColor(), unCriterio.getAroma(), unCriterio.getTacto(), unCriterio.getDegustacion(), unaFechaOrigen, null, unLaboratorio, unPorcentajePalo, unPorcentajeSemilla, unPorcentajePolvo, unPorcentajeHumedad, unPorcentajeHoja, unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                //registrarAnalisisDeLaboratorio("SI", "SI", "VERDE OSCURO", "TIPICO", "CRUJIENTE", "SI", unaFechaOrigen, null, unLaboratorio, "5,0", "5,0", "5,0", "15,0", "85,0", unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
                unAnalisis = analisisLaboratorio.get(7);
                if (comentariosHabilitados)
                    System.out.println("carga aleatoria");
                //</editor-fold>
                
                //Carga aleatoria
                //<editor-fold defaultstate="collapsed">
                Random generadorAleatorio = new Random();
                int cantidadAGenerarAnalisis = 1000;
                for (int i = 0; i<cantidadAGenerarAnalisis; i++){
                    unLaboratorio = (Laboratorio) this.equipamientos.get(1);
                    
                    int unNumeroCriterio = Math.max(Math.abs(generadorAleatorio.nextInt() % (this.criteriosAnalisisLaboratorio.keySet().size())),1);
                    unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(unNumeroCriterio);
                    
                    int unNumeroOrdenCompra = Math.max((Math.abs(generadorAleatorio.nextInt() % (this.ordenesCompra.keySet().size()))), 1);
                    unaOrdenDeCompra = (OrdenDeCompra) this.ordenesCompra.get(unNumeroOrdenCompra);
                    
                    if (!(unCriterio.getTipoLote().equals(unaOrdenDeCompra.getTipoLote()))){
                        i= i-1;
                        continue;
                    }
                    
                    if (Math.random()>0.5){
                        int unAnio = (Math.abs(generadorAleatorio.nextInt() % 10));
                        int unMes = (Math.abs(generadorAleatorio.nextInt() % 12));
                        int unDia = (Math.abs(generadorAleatorio.nextInt() % 28));

                        unaFechaOrigen = Calendar.getInstance();
                        unaFechaOrigen.add(Calendar.YEAR, -unAnio);
                        unaFechaOrigen.add(Calendar.MONTH, -unMes);
                        unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -unDia);

                        String[] atrCual = new String[AnalisisLaboratorio.ATRIBUTOS.length];
                        String[] atrCuan = new String[4];
                        for (int j = 0; j<AnalisisLaboratorio.ATRIBUTOS.length; j++){
                            int unNumeroSubAtributo = (Math.abs(generadorAleatorio.nextInt() % (AnalisisLaboratorio.SUB_ATRIBUTOS[j].length)));
                            String unSubAtributo = AnalisisLaboratorio.SUB_ATRIBUTOS[j][unNumeroSubAtributo];
                            atrCual[j] = unSubAtributo;
                        }
                        Float porcentajeTotal = 100f;
                        unPorcentajeHumedad = UtilidadesInterfazGrafica.formatearFlotante(generadorAleatorio.nextFloat()*100);
                        for (int j = 0; j<4; j++){
                            if (j<3)
                                atrCuan[j] = UtilidadesInterfazGrafica.formatearFlotante((generadorAleatorio.nextFloat()*10000) % porcentajeTotal);
                            else
                                atrCuan[j] = UtilidadesInterfazGrafica.formatearFlotante(porcentajeTotal);
                            porcentajeTotal = porcentajeTotal - Organizacion.convertirAFlotante(atrCuan[j], "insertor de tuplas de anlisis de laboratorio");
                        }
                        
                        registrarAnalisisDeLaboratorio(atrCual[0], atrCual[1], atrCual[2], atrCual[3], atrCual[4], "SI", unaFechaOrigen, null, unLaboratorio, ""+atrCuan[0], ""+atrCuan[1], ""+atrCuan[2], ""+unPorcentajeHumedad, ""+atrCuan[3], unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
//                    unAnalisis = analisisLaboratorio.get(7);    
                    }else{
                        int unAnio = (Math.abs(generadorAleatorio.nextInt() % 10));
                        int unMes = (Math.abs(generadorAleatorio.nextInt() % 12));
                        int unDia = (Math.abs(generadorAleatorio.nextInt() % 28));

                        unaFechaOrigen = Calendar.getInstance();
                        unaFechaOrigen.add(Calendar.YEAR, -unAnio);
                        unaFechaOrigen.add(Calendar.MONTH, -unMes);
                        unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -unDia);

                        String[] atrCual = new String[AnalisisLaboratorio.ATRIBUTOS.length];
                        String[] atrCuan = new String[4];
                        for (int j = 0; j<AnalisisLaboratorio.ATRIBUTOS.length; j++){
                            String unSubAtributo = unCriterio.generarUnAtributoCualitativo(j);
                            atrCual[j] = unSubAtributo;
                        }
                        Float porcentajeTotal = 100f;
                        unPorcentajePalo = unCriterio.generarPorcentajePalo();
                        porcentajeTotal = porcentajeTotal - Organizacion.convertirAFlotante(unPorcentajePalo, "Insertor tuplas porcentaje palo");
                        //System.out.println("porcentaje palo "+unPorcentajePalo);
                        
                        unPorcentajePolvo = unCriterio.generarPorcentajePolvo();
                        porcentajeTotal = porcentajeTotal - Organizacion.convertirAFlotante(unPorcentajePolvo, "Insertor tuplas porcentaje polvo");
                        //System.out.println("porcentaje Polvo "+unPorcentajePolvo);
                        
                        unPorcentajeSemilla = unCriterio.generarPorcentajeSemilla();
                        porcentajeTotal = porcentajeTotal - Organizacion.convertirAFlotante(unPorcentajeSemilla, "Insertor tuplas porcentaje semilla");
                        //System.out.println("porcentaje Semilla "+unPorcentajeSemilla);
                        
                        unPorcentajeHoja = UtilidadesInterfazGrafica.formatearFlotante(porcentajeTotal);
                        //System.out.println("porcentaje Hoja "+unPorcentajeHoja);
                        
                        unPorcentajeHumedad = unCriterio.generarPorcentajeHumedad();
                        //System.out.println("porcentaje humedad "+unPorcentajeHumedad);
                        registrarAnalisisDeLaboratorio(atrCual[0], atrCual[1], atrCual[2], atrCual[3], atrCual[4], "SI", unaFechaOrigen, null, unLaboratorio, ""+unPorcentajePalo, ""+unPorcentajeSemilla, ""+unPorcentajePolvo, ""+unPorcentajeHumedad, ""+unPorcentajeHoja, unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);                        
                    }
                }
                
                //</editor-fold>
                
            //</editor-fold>
            //Carga de Ingresos
            if (comentariosHabilitados)            
                System.out.println("Carga de ingresos");
            //<editor-fold defaultstate="collapsed">
                //Ingreso N°1
                //<editor-fold defaultstate="collapsed">
                Equipamiento unEquipamientoDestino = equipamientos.get(3);     //El Depósito N°1.
                unaOrdenDeCompra = ordenesCompra.get(1);
                unTipoLote = Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE;
                unAnalisis = analisisLaboratorio.get(1);
                Proveedor unProveedorServicioTransporte = proveedores.get(3);
                LocalTime unHorarioEntrada = LocalTime.of(7,00,12);
                LocalTime unHorarioSalida = LocalTime.of(11, 30, 15);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -2);
                unaFechaOrigen.add(Calendar.YEAR, -5);
                pesoEntrada = "40.000";
                pesoSalida = "20.000";
                registrarIngresoMateriaPrima(unaFechaOrigen, unHorarioEntrada, unHorarioSalida, Lote.UNIDAD_MEDIDA_Tranporte, "150", Lote.UNIDAD_MEDIDA_KILOGRAMO, pesoEntrada, pesoSalida, "3545", "3321", "5135", "Pedro Gabriel Gómez", "OMD-418", "IUT-166", unEquipamientoDestino, unaOrdenDeCompra, unProveedorServicioTransporte, unAnalisis, unTipoLote);
                //</editor-fold>
                //Ingreso N°2
                //<editor-fold defaultstate="collapsed">
                unEquipamientoDestino = equipamientos.get(4);     //La C.E.A. N°1
                unaOrdenDeCompra = ordenesCompra.get(2);
                unTipoLote = Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA;
                unAnalisis = analisisLaboratorio.get(2);
                unProveedorServicioTransporte = proveedores.get(4);
                unHorarioEntrada = LocalTime.of(7,00,12);
                unHorarioSalida = LocalTime.of(11, 30, 15);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -2);
                unaFechaOrigen.add(Calendar.YEAR, -5);
                pesoEntrada = "33.500";
                pesoSalida = "23.500";
                
                registrarIngresoMateriaPrima(unaFechaOrigen, unHorarioEntrada, unHorarioSalida, Lote.UNIDAD_MEDIDA_Tranporte, "150", Lote.UNIDAD_MEDIDA_KILOGRAMO, pesoEntrada, pesoSalida, "3545", "3321", "5135", "Pedro Gabriel Gómez", "OMD-418", "IUT-166", unEquipamientoDestino, unaOrdenDeCompra, unProveedorServicioTransporte, unAnalisis, unTipoLote);
                //</editor-fold>
                //Ingreso N°3
                //<editor-fold defaultstate="collapsed">
                unEquipamientoDestino = equipamientos.get(4);     //La C.E.A. N°1
                unaOrdenDeCompra = ordenesCompra.get(2);
                unTipoLote = Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA;
                unAnalisis = analisisLaboratorio.get(3);
                unProveedorServicioTransporte = proveedores.get(4);
                unHorarioEntrada = LocalTime.of(7,00,12);
                unHorarioSalida = LocalTime.of(11, 30, 15);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -1);
                unaFechaOrigen.add(Calendar.YEAR, -5);
                pesoEntrada = "39.500";
                pesoSalida = "34.500";
                registrarIngresoMateriaPrima(unaFechaOrigen, unHorarioEntrada, unHorarioSalida, Lote.UNIDAD_MEDIDA_Tranporte, "150", Lote.UNIDAD_MEDIDA_KILOGRAMO, pesoEntrada, pesoSalida, "3545", "3321", "5135", "Pedro Gabriel Gómez", "OMD-418", "IUT-166", unEquipamientoDestino, unaOrdenDeCompra, unProveedorServicioTransporte, unAnalisis, unTipoLote);
                //</editor-fold>                
                //Ingreso N°4
                //<editor-fold defaultstate="collapsed">
                unEquipamientoDestino = equipamientos.get(3);     //DEPOSITO N°1
                unTipoLote = Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE;
                unaOrdenDeCompra = ordenesCompra.get(3);
                unAnalisis = analisisLaboratorio.get(5);
                unProveedorServicioTransporte = proveedores.get(3);
                unHorarioEntrada = LocalTime.of(7,00,12);
                unHorarioSalida = LocalTime.of(11, 30, 15);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -1);
                unaFechaOrigen.add(Calendar.YEAR, -4);
                pesoEntrada = "35.000";
                pesoSalida = "25.000";
                registrarIngresoMateriaPrima(unaFechaOrigen, unHorarioEntrada, unHorarioSalida, Lote.UNIDAD_MEDIDA_Tranporte, "150", Lote.UNIDAD_MEDIDA_KILOGRAMO, pesoEntrada, pesoSalida, "3545", "3321", "5135", "Pedro Gabriel Gómez", "OMD-418", "INE-487", unEquipamientoDestino, unaOrdenDeCompra, unProveedorServicioTransporte, unAnalisis, unTipoLote);
                //</editor-fold>
                //Ingreso N°5
                //<editor-fold defaultstate="collapsed">
                unEquipamientoDestino = equipamientos.get(3);     //DEPOSITO N°1
                unaOrdenDeCompra = ordenesCompra.get(4);
                unTipoLote = Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA;
                unAnalisis = analisisLaboratorio.get(6);
                unProveedorServicioTransporte = proveedores.get(4);
                unHorarioEntrada = LocalTime.of(7,00,12);
                unHorarioSalida = LocalTime.of(11, 30, 15);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, 0);
                unaFechaOrigen.add(Calendar.YEAR, -4);
                pesoEntrada = "35.627";
                pesoSalida = "25.627";
                registrarIngresoMateriaPrima(unaFechaOrigen, unHorarioEntrada, unHorarioSalida, Lote.UNIDAD_MEDIDA_Tranporte, "150", Lote.UNIDAD_MEDIDA_KILOGRAMO, pesoEntrada, pesoSalida, "3545", "3321", "5135", "Pedro Gabriel Gómez", "YKE-289", "HIR-873", unEquipamientoDestino, unaOrdenDeCompra, unProveedorServicioTransporte, unAnalisis, unTipoLote);
                //</editor-fold>
            //</editor-fold>
            if (comentariosHabilitados)
                System.out.println("Carga de ingresos completada");
            //Carga Movimientos:
            //<editor-fold defaultstate="collapsed">
                //MOVIMIENTO N°1
                //<editor-fold defaultstate="collapsed">
                unEquipamientoDestino = equipamientos.get(4);     //La C.E.A. N°1
                unProveedorServicioTransporte = proveedores.get(3);
                unHorarioEntrada = LocalTime.of(7,00,12);
                unHorarioSalida = LocalTime.of(11, 30, 15);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -4);
                unLoteAMover = lotes.get(1);
                registrarMovimimiento(unaFechaOrigen, unHorarioEntrada, unHorarioSalida, Lote.UNIDAD_MEDIDA_Tranporte, "150", Lote.UNIDAD_MEDIDA_KILOGRAMO, "38.453", "15.124", "4235", "6015", "6785", "Juan Carlos Burnick", "UUF-516", "MEC-443", unLoteAMover, unEquipamientoDestino, unProveedorServicioTransporte);
                //</editor-fold>
                
                //MOVIMIENTO N°2
                //<editor-fold defaultstate="collapsed">
                unEquipamientoDestino = equipamientos.get(4);     //La C.E.A. N°1
                unProveedorServicioTransporte = proveedores.get(3);
                unHorarioEntrada = LocalTime.of(7,00,12);
                unHorarioSalida = LocalTime.of(11, 30, 15);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -4);
                unLoteAMover = lotes.get(4);
                registrarMovimimiento(unaFechaOrigen, unHorarioEntrada, unHorarioSalida, Lote.UNIDAD_MEDIDA_Tranporte, "150", Lote.UNIDAD_MEDIDA_KILOGRAMO, "38.124", "15.132", "4236", "6115", "6795", "Juan Carlos Burnick", "UUF-516", "MEC-443", unLoteAMover, unEquipamientoDestino, unProveedorServicioTransporte);
                //</editor-fold>
            
            //</editor-fold>
            //Estacionamientos
            //<editor-fold defaultstate="collapsed">
                //ESTACIONAMIENTO N°1
                //<editor-fold defaultstate="collapsed">
                CamaraEstacionamiento unaCamara = (CamaraEstacionamiento) equipamientos.get(4);
                ArrayList lotesAEstacionar = new ArrayList();
                Lote unLoteAEstacionar = this.lotes.get(1);
                lotesAEstacionar.add(unLoteAEstacionar);
                unaFechaOrigen = Calendar.getInstance();
                unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -3);
                registrarEstacionamiento(lotesAEstacionar, unaCamara, unaFechaOrigen, "0");
                
                //</editor-fold>
            //</editor-fold>
            
            //ANALISIS DEL LOTE N°1 DE YCE
            //<editor-fold defaultstate="collapsed">
            if (comentariosHabilitados)    
                System.out.println("Carga de análisis posterior al estacionamiento");
            unLaboratorio = (Laboratorio) this.equipamientos.get(1);
            unCriterio = (CriterioAnalisisLaboratorio) this.criteriosAnalisisLaboratorio.get(5);
            unaOrdenDeCompra = null;
            unaFechaOrigen = Calendar.getInstance();
            unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -2);
            Lote unLote = (Lote) lotes.get(1);
            registrarAnalisisDeLaboratorio("SI", "SI", "VERDE OSCURO", "TIPICO", "CRUJIENTE", "SI", unaFechaOrigen, unLote, unLaboratorio, "5,0", "5,0", "5,0", "15,0", "85,0", unCriterio, "SIN COMENTARIO", null);
            //</editor-fold>
            
            //MOVIMIENTO A MOLINO DEL LOTE N°1
            //<editor-fold defaultstate="collapsed">
            unEquipamientoDestino = equipamientos.get(5);     //El MOLINO
            unProveedorServicioTransporte = proveedores.get(3);
            unHorarioEntrada = LocalTime.of(7,00,12);
            unHorarioSalida = LocalTime.of(11, 30, 15);
            unaFechaOrigen = Calendar.getInstance();
            unaFechaOrigen.add(Calendar.DAY_OF_MONTH, -2);
            unLoteAMover = lotes.get(1);
            registrarMovimimiento(unaFechaOrigen, unHorarioEntrada, unHorarioSalida, Lote.UNIDAD_MEDIDA_Tranporte, "150", Lote.UNIDAD_MEDIDA_KILOGRAMO, "35.000", "15.600", "4235", "6015", "6785", "Abel Gomez", "FIU-196", "MAQ-453", unLoteAMover, unEquipamientoDestino, unProveedorServicioTransporte);
            //</editor-fold>
                
            //cargarDatosAleatorios(5);

            
            
            } catch (ExcepcionCargaParametros ex) {
                System.err.println("Error en la carga de parametros en la pre-carga de tuplas: "+ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("Error en la base de datos en la pre-carga de tuplas: "+ex.getMessage());
            } catch (ExcepcionPersistencia ex) {
                System.err.println("Error en la persistencia en la pre-carga de tuplas: "+ex.getMessage());
            }
        }
    }
    
    public void generarReporteINYMv3(Calendar unaFecha) throws JRException, ExcepcionCargaParametros{
        if (unaFecha == null)
            throw new ExcepcionCargaParametros("Debe seleccionar una fecha para generar el reporte de INYM");
        String unResumen = this.resumirExistenciaV3();
        GeneradorDeReportes.generarReporteINYMv3(unaFecha, equipamientos, unResumen, usuarioActivo);
    }
    
    public void generarReporteINYMv4(Calendar unaFecha) throws JRException, ExcepcionCargaParametros{
        if (unaFecha == null)
            throw new ExcepcionCargaParametros("Debe seleccionar una fecha para generar el reporte de INYM");
        String unResumen = this.resumirExistenciaV4(unaFecha);
        GeneradorDeReportes.generarReporteINYMv3(unaFecha, equipamientos, unResumen, usuarioActivo);
    }
    
    public ArrayList filtrarDetalleAuditoria(String unId) throws ExcepcionCargaParametros, SQLException {
        if (!Validaciones.esUnNumeroEnteroValido(unId))
            throw new ExcepcionCargaParametros("No se ingresó un numero correcto para realizar una consulta de detalle de auditoría.");
        int id = Integer.parseInt(unId);
        return this.persistencia.obtenerDetalleAuditoria(id);
    }

    private String resumirExistencia() {
        String retorno = "";
        retorno = retorno + "Total de YCV: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCVKg())+" Kilogramos\n";
        retorno = retorno + "Total de YCE: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCEKg())+" Kilogramos\n";
        retorno = retorno + "Total de YCM: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCMKg())+" Kilogramos\n";
        return retorno;
    }

    
    /*CUANDO VUELVAS
        TENES QUE
    
    REDISEÑAR RESUMIR EXISTENCIA V3
    HACER CALCULAR TOTAL YCVKG V3
    HACER CALCULAR TOTAL YCEKG V3
    HACER CALCULAR TOTAL YCMKG V3
    
    */
    private String resumirExistenciaV3() {
        String retorno = "";
        retorno = retorno + "Total de YCV: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCVKgV3())+" Kilogramos\n";
        retorno = retorno + "Total de YCE: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCEKgV3())+" Kilogramos\n";
        retorno = retorno + "Total de YCM: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCMKgV3())+" Kilogramos\n";
        return retorno;
    }
    
    private String resumirExistenciaV4(Calendar unaFecha) {
        String retorno = "";
        retorno = retorno + "Total de YCV: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCVKgV4(unaFecha))+" Kilogramos\n";
        retorno = retorno + "Total de YCE: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCEKgV4(unaFecha))+" Kilogramos\n";
        retorno = retorno + "Total de YCM: "+UtilidadesInterfazGrafica.formatearFlotante(this.calcularTotalYCMKgV4(unaFecha))+" Kilogramos\n";
        return retorno;
    }
    
    private Float calcularTotalYCVKg() {
        Float totalYCV = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeYerbaCancadaVerde() && unLote.estaRegular())
                totalYCV = totalYCV + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg();
        }
        return totalYCV;
    }
    
    private Float calcularTotalYCVKgV3() {
        Float totalYCV = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeYerbaCancadaVerde() && unLote.estaRegular() && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(Equipamiento.FECHA_REPORTE_INYM))
                totalYCV = totalYCV + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(Equipamiento.FECHA_REPORTE_INYM);
        }
        return totalYCV;
    }
    
    private Float calcularTotalYCVKgV4(Calendar unaFecha) {
        Float totalYCV = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeYerbaCancadaVerde(unaFecha) && unLote.estaRegular() && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha))
                totalYCV = totalYCV + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(unaFecha);
        }
        return totalYCV;
    }
    
    private Float calcularTotalYCEKg() {
        Float totalYCE = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeYerbaCancadaEstacionada()&& unLote.estaRegular())
                totalYCE = totalYCE + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg();
        }
        return totalYCE;
    }
    
    private Float calcularTotalYCEKgV3() {
        Float totalYCE = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeYerbaCancadaEstacionada()&& unLote.estaRegular() && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(Equipamiento.FECHA_REPORTE_INYM))
                totalYCE = totalYCE + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(Equipamiento.FECHA_REPORTE_INYM);
        }
        return totalYCE;
    }
    

    private Float calcularTotalYCEKgV4(Calendar unaFecha) {
        Float totalYCE = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeYerbaCancadaEstacionada(unaFecha)&& unLote.estaRegular() && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha))
                totalYCE = totalYCE + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(unaFecha);
        }
        return totalYCE;
    }
    
    private Float calcularTotalYCMKg() {
        Float totalYCV = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeTipo("YM")&& unLote.estaRegular())
                totalYCV = totalYCV + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg();
        }
        return totalYCV;
    }
    
    private Float calcularTotalYCMKgV3() {
        Float totalYM = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeTipo("YM") && unLote.estaRegular() && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(Equipamiento.FECHA_REPORTE_INYM))
                totalYM = totalYM + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(Equipamiento.FECHA_REPORTE_INYM);
        }
        return totalYM;
    }
    
    private Float calcularTotalYCMKgV4(Calendar unaFecha) {
        Float totalYM = 0f;
        Iterator recorredorDeLotes = this.lotes.keySet().iterator();
        while (recorredorDeLotes.hasNext()){
            int unId = (int) recorredorDeLotes.next();
            Lote unLote = this.lotes.get(unId);
            if (unLote.esDeTipo("YM") && unLote.estaRegular() && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha))
                totalYM = totalYM + unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(unaFecha);
        }
        return totalYM;
    }
    
    public void darDeAltaUnUsuario (String unNombreUsuario, String unNombre, String unApellido,String unDNI, String unRol) throws ExcepcionCargaParametros, SQLException{
        if (unNombreUsuario.equals(""))
            throw new ExcepcionCargaParametros("El nombre de usuario ingresado no es valido.");
        if (unNombre.equals(""))
            throw new ExcepcionCargaParametros("El nombre ingresado no es valido.");
        if (unApellido.equals(""))
            throw new ExcepcionCargaParametros("El apellido ingresado no es valido.");
        if (unDNI.equals(""))
            throw new ExcepcionCargaParametros("El DNI ingresado no es valido.");
        if (!Validaciones.unDniEsValido(unDNI))
            throw new ExcepcionCargaParametros("El DNI ingresado no es valido.");
        if (unRol.equals("") || unRol.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("El nombre de usuario ingresado no es valido");
        
        ArrayList usuariosAValidar = Organizacion.convertMapUsuariosToArrayList(this.usuarios);
        if (Validaciones.existeUsuario(usuariosAValidar,unNombreUsuario))
            throw new ExcepcionCargaParametros("Ya existe un usuario con ese nombre de usuario");
        
        Usuario unUsuario = new Usuario(unNombre, unNombreUsuario, unApellido, unDNI, unRol);
        persistencia.persistirObjeto(unUsuario);
        this.usuarios.put(unUsuario.getId(), unUsuario);
    }
    
    
    
    public void darDeBajaUnUsuario (Usuario unUsuario) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unUsuario == null)
            throw new ExcepcionCargaParametros("Debe seleccionar un usuario.");
        if (this.usuarioActivo.equals(unUsuario))
            throw new ExcepcionCargaParametros("No puede darse de baja el usuario de la sesión actualmente activa.");
        unUsuario.darDeBaja();
        persistencia.modificarObjeto(unUsuario);
    }
    
    public void modificarUnUsuario (Usuario unUsuario, String unEstado, String unRol) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        if (unUsuario == null)
            throw new ExcepcionCargaParametros("Debe seleccionar un usuario.");
        if (this.usuarioActivo.equals(unUsuario))
            throw new ExcepcionCargaParametros("No se puede modificar usuario de la sesión actualmente activa.");
        if (unEstado.equals("") || unEstado.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("El estado ingresado no es valido.");
        if (unRol.equals("") || unRol.equals("Seleccionar"))
            throw new ExcepcionCargaParametros("El rol ingresado no es valido.");
        unUsuario.setRol(unRol);
        unUsuario.setEstado(unEstado);
        persistencia.modificarObjeto(unUsuario);
    }
    
    public ArrayList obtenerOrdenesDeProduccionOrdenadas(){
        ArrayList retorno = new ArrayList();
        
        Iterator claves = this.ordenesProduccion.keySet().iterator();
        while (claves.hasNext()){
            int unaClave = (int) claves.next();
            OrdenDeProduccion unaOrden = this.ordenesProduccion.get(unaClave);
            if (unaOrden.seEncuentraRegular() && unaOrden.calcularProgresoGeneral()<95f){
                retorno.add(unaOrden);
            }
        }
        Collections.sort(retorno);
        return retorno;
    }
    
    private static ArrayList convertMapUsuariosToArrayList(Map<Integer, Usuario> mapa) {
        ArrayList retorno = new ArrayList();
        Iterator recorredor = mapa.keySet().iterator();
        while (recorredor.hasNext()){
            int unId = (int) recorredor.next();
            Usuario unUsuario = mapa.get(unId);
            retorno.add(unUsuario);
        }
        return retorno;
    }
    
    public static long calcularCantidadDiasDiferencia (Calendar fechaLimiteInferior, Calendar fechaLimiteSuperior){
        long retorno = 0;
        long milisegundosLimiteSuperior = fechaLimiteSuperior.getTimeInMillis();
        long milisegundosLimiteInferior = fechaLimiteInferior.getTimeInMillis();
        long diferencia = milisegundosLimiteSuperior - milisegundosLimiteInferior;
        //Pasar a días
        diferencia = diferencia / (24 * 60 * 60 * 1000);
        retorno = diferencia;
        return retorno;
    }
    
    private void cargarDatosAleatorios(int unaCantidadOrdenesProduccion) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        for (int i = 0; i<unaCantidadOrdenesProduccion; i++){
            
            Calendar unaFechaOrigenOrdenDeProduccion;
            float unaCantidadAProducir;
            String unaUnidadDeMedida;
            Calendar unaFechaEntregaProductoTerminado;
            String unaDescripcion;
            ArrayList criteriosAnalisisLaboratorio;
            
            //determinar cantidad a producir
            {
                float max=14;
                float min=8;
                unaCantidadAProducir = (500)*(int) (((Math.random())*(max-min)+1)+min);
            }
            //determinar fecha de origen
            {
                int max = 24;
                int min = 1;
                int unaDiferenciaMeses =   (int)((Math.random())*(max-min)+1)+min;
                unaFechaOrigenOrdenDeProduccion = Calendar.getInstance();
                unaFechaOrigenOrdenDeProduccion.add(Calendar.MONTH, -unaDiferenciaMeses);
            }
            unaUnidadDeMedida = Lote.UNIDAD_MEDIDA_KILOGRAMO;
            unaDescripcion = "Orden de producción";
            
            unaFechaEntregaProductoTerminado = (Calendar) unaFechaOrigenOrdenDeProduccion.clone();
            unaFechaEntregaProductoTerminado.add(Calendar.YEAR, 3);
            criteriosAnalisisLaboratorio = new ArrayList();
            {
                for (int j = 1; j<=3; j++){
                    CriterioAnalisisLaboratorio unCriterio = this.getCriteriosAnalisisLaboratorio().get(j);
                    criteriosAnalisisLaboratorio.add(unCriterio);
                }
            }
            registrarOrdenDeProduccion(unaFechaOrigenOrdenDeProduccion, UtilidadesInterfazGrafica.formatearFlotante(unaCantidadAProducir), unaUnidadDeMedida, unaFechaEntregaProductoTerminado, unaDescripcion, criteriosAnalisisLaboratorio);
            
            OrdenDeProduccion unaOrdenDeProduccion;
            Proveedor unProveedor = getCualquierProveedorActivo();
            
            int unIdOrdenProduccion = (int) getOrdenesProduccion().keySet().toArray()[getOrdenesProduccion().size()-1];
            unaOrdenDeProduccion = getOrdenesProduccion().get(unIdOrdenProduccion);
            System.out.println("registrada orden de produccion");

            ArrayList ordenesDeCompraIngresadas = new ArrayList();
            
            while (unaOrdenDeProduccion.getPesoRestanteAComprar() >0){
                float unaCantidadAComprar;
                {
                    float max=unaOrdenDeProduccion.getPesoRestanteAComprar()/500;
                    float min=1;
                    unaCantidadAComprar = Math.min((500)*(int) (((Math.random())*(max-min)+1)+min), unaOrdenDeProduccion.getPesoRestanteAComprar());
                }
                float unCostoDeCompra;
                {
                    float max=5;
                    float min=1;
                    unCostoDeCompra = (150)*(int) (((Math.random())*(max-min)+1)+min);
                }
                
                registrarOrdenDeCompra(Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE, unaOrdenDeProduccion, unProveedor, unaUnidadDeMedida, 
                        UtilidadesInterfazGrafica.formatearFlotante(unaCantidadAComprar), UtilidadesInterfazGrafica.formatearFlotante(unCostoDeCompra));
                System.out.println("registrada orden de compra");

                int unIdOrdenCompra = (int) getOrdenesCompra().keySet().toArray()[getOrdenesCompra().size()-1];
                OrdenDeCompra unaOrdenDeCompra = getOrdenesCompra().get(unIdOrdenCompra);
                ordenesDeCompraIngresadas.add(unaOrdenDeCompra);
            }
            
            //Por cada orden de compra, genero hasta 4 ingresos para completarlos
            System.out.println("registro de ingreso de materia prima");
            // <editor-fold defaultstate="collapsed">
            Iterator recorredorOrdenesDeCompra = ordenesDeCompraIngresadas.iterator();
            while (recorredorOrdenesDeCompra.hasNext()){
                OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) recorredorOrdenesDeCompra.next();
                AnalisisLaboratorio unAnalisis = registrarUnAnalisisLaboratorioAprobadoCargaAleatoria(unaOrdenDeCompra);
                //Generar la cantidad a ingresar
                Float cantidadAIngresar = unaOrdenDeCompra.getCantidadAComprar();
                String pesoEntrada = ""+ UtilidadesInterfazGrafica.formatearFlotante(10000+cantidadAIngresar);
                String pesoSalida = ""+ UtilidadesInterfazGrafica.formatearFlotante(10000f);

                Calendar unaFechaOrigenIngreso = (Calendar) unaOrdenDeProduccion.getFechaOrigenC().clone();
                unaFechaOrigenIngreso.add(Calendar.DAY_OF_YEAR, -2);

                String unaUnidadMedidaTransporte = Lote.UNIDAD_MEDIDA_Tranporte;
                String cantidadBolsas = "100";

                //Generar hoja de ruta y n de remito y nº precinto
                String nHojaRuta;
                String nRemito;
                String unNumeroDePrecinto;
                {
                    float max=100000;
                    float min=1;
                    nHojaRuta = ""+(int) (((Math.random())*(max-min)+1)+min);
                    nRemito = ""+(int) (((Math.random())*(max-min)+1)+min);
                    unNumeroDePrecinto = ""+(int) (((Math.random())*(max-min)+1)+min);
                }

                Equipamiento destino = getUnEquipamientoDestinoCargaAleatoria();
                registrarIngresoMateriaPrima(unaFechaOrigenIngreso, LocalTime.MIN, LocalTime.NOON, unaUnidadMedidaTransporte, cantidadBolsas, 
                        unaUnidadDeMedida, pesoEntrada, pesoSalida, nHojaRuta, nRemito, unNumeroDePrecinto, "Sergio Ifran", 
                        "ASK-658", "TER-703", destino, unaOrdenDeCompra, unProveedor, unAnalisis, Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE);
            //carga de un analisis de laboratorio aprobado
            // <editor-fold defaultstate="collapsed">



        // </editor-fold>
            }
            // </editor-fold>
            
            
            
            
            // <editor-fold defaultstate="collapsed">
            // </editor-fold>
            
            // <editor-fold defaultstate="collapsed">
            // </editor-fold>
        }
    }

    private Proveedor getCualquierProveedorActivo() {
        Proveedor retorno = null;
        while (retorno == null){
            int max = this.getProveedores().keySet().size()-1;
            int min = 0;
            int unIdAProbar = (int)((Math.random())*(max-min)+1)+min;
            Proveedor unProveedor = this.getProveedores().get(unIdAProbar);
            if (unProveedor.seEncuentraActivo())
                retorno = unProveedor;
        }
        return retorno;
    }
    
    private AnalisisLaboratorio registrarUnAnalisisLaboratorioAprobadoCargaAleatoria(OrdenDeCompra unaOrdenDeCompra) throws ExcepcionCargaParametros, SQLException, ExcepcionPersistencia{
        AnalisisLaboratorio retorno = null;
        
        Calendar unaFechaOrigen;
        
        Float unPorcentajePaloInferior;
        Float unPorcentajePaloSuperior;
        String unPorcentajePalo;        
        
        Float unPorcentajeSemillaInferior;
        Float unPorcentajeSemillaSuperior;
        String unPorcentajeSemilla;

        Float unPorcentajePolvoInferior;
        Float unPorcentajePolvoSuperior;
        String unPorcentajePolvo;

        Float unPorcentajeHumedadInferior;
        Float unPorcentajeHumedadSuperior;
        String unPorcentajeHumedad;

        Float unPorcentajeHojaInferior;
        Float unPorcentajeHojaSuperior;
        String unPorcentajeHoja;
        
        Laboratorio unLaboratorio = (Laboratorio) this.equipamientos.get(1);
        OrdenDeProduccion unaOrdenDeProduccion = unaOrdenDeCompra.getOrdenDeProduccionAsociada();
        CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio) unaOrdenDeProduccion.getCriteriosDeAnalisisAsociados().get(0);
        unaFechaOrigen = (Calendar) unaOrdenDeCompra.getFechaOrigenC().clone();
        unaFechaOrigen.add(Calendar.DAY_OF_YEAR, -1);
        
        unPorcentajePaloInferior = unCriterio.getPorcentajePaloLimiteInferior();
        unPorcentajePaloSuperior = unCriterio.getPorcentajePaloLimiteSuperior();
        unPorcentajePalo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePaloInferior+(Float)(unPorcentajePaloSuperior-unPorcentajePaloInferior)/2);

        unPorcentajeSemillaInferior = unCriterio.getPorcentajeSemillaLimiteInferior();
        unPorcentajeSemillaSuperior = unCriterio.getPorcentajeSemillaLimiteSuperior();
        unPorcentajeSemilla = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeSemillaInferior+(Float)(unPorcentajeSemillaSuperior-unPorcentajeSemillaInferior)/2);

        unPorcentajePolvoInferior = unCriterio.getPorcentajePolvoLimiteInferior();
        unPorcentajePolvoSuperior = unCriterio.getPorcentajePolvoLimiteSuperior();
        unPorcentajePolvo = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajePolvoInferior+(Float)(unPorcentajePolvoSuperior-unPorcentajePolvoInferior)/2);

        unPorcentajeHumedadInferior = unCriterio.getPorcentajeHumedadLimiteInferior();
        unPorcentajeHumedadSuperior = unCriterio.getPorcentajeHumedadLimiteSuperior();
        unPorcentajeHumedad = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHumedadInferior+(Float)(unPorcentajeHumedadSuperior-unPorcentajeHumedadInferior)/2);

        unPorcentajeHojaInferior = unCriterio.getPorcentajeHojaLimiteInferior();
        unPorcentajeHojaSuperior = unCriterio.getPorcentajeHojaLimiteSuperior();
        unPorcentajeHoja = UtilidadesInterfazGrafica.formatearFlotante(unPorcentajeHojaInferior+(Float)(unPorcentajeHojaSuperior-unPorcentajeHojaInferior)/2);

        registrarAnalisisDeLaboratorio(unCriterio.getPuntosNegros(), unCriterio.getTorrada(), unCriterio.getColor(), unCriterio.getAroma(), unCriterio.getTacto(), unCriterio.getDegustacion(), unaFechaOrigen, null, unLaboratorio, unPorcentajePalo, unPorcentajeSemilla, unPorcentajePolvo, unPorcentajeHumedad, unPorcentajeHoja, unCriterio, "SIN COMENTARIO", unaOrdenDeCompra);
        
        int unIdAnalisis = (int) this.getAnalisisLaboratorio().keySet().toArray()[this.getAnalisisLaboratorio().keySet().size()-1];
        AnalisisLaboratorio unAnalisis = analisisLaboratorio.get(unIdAnalisis);
        retorno = unAnalisis;
        
        return retorno;
    }

    private Equipamiento getUnEquipamientoDestinoCargaAleatoria() {
        Equipamiento retorno = null;
        while (retorno == null){
            int max = this.getEquipamientos().keySet().size()-1;
            int min = 0;
            int unIdAProbar = (int)((Math.random())*(max-min)+1)+min;
            Equipamiento unEquipamiento = getEquipamientos().get(unIdAProbar);
            if (unEquipamiento.estaActivo() && (unEquipamiento.getTipo().equals(Equipamiento.TIPO_DEPOSITO)|| unEquipamiento.getTipo().equals(Equipamiento.TIPO_CAMARA_ESTACIONAMIENTO)))
                retorno = unEquipamiento;    
        }
        return retorno;
    }

}

