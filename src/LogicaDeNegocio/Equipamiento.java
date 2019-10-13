/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.Bascula;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Equipamiento {
    private int id;
    private String nombre;
    private String direccion;
    private Calendar fechaAdquisicion;
    private Calendar fechaUltimoMantenimiento;
    
    private float capacidadMaxima;
    private String unidadDeMedida;
    private String estado;
    
    private ArrayList movimientosAsociadosDeEntradaActuales;
    private ArrayList movimientosAsociadosDeSalidaActuales;
    private ArrayList lotesEnExistenciaActuales;
    
    private Bascula basculaAsociada;

    public Equipamiento(int id, String nombre, String direccion, java.sql.Date fechaAdquisicion, java.sql.Date fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaAdquisicion = Calendar.getInstance();

        this.fechaAdquisicion.setTime(fechaAdquisicion);
        this.fechaUltimoMantenimiento = Calendar.getInstance();
        this.fechaUltimoMantenimiento.setTime(fechaUltimoMantenimiento);
        this.capacidadMaxima = capacidadMaxima;
        this.unidadDeMedida = unidadDeMedida;
        this.estado = estado;
        
        this.lotesEnExistenciaActuales = new ArrayList();
        this.movimientosAsociadosDeEntradaActuales = new ArrayList();
        this.movimientosAsociadosDeSalidaActuales = new ArrayList();
    }

    public Equipamiento(String nombre, String direccion, Calendar fechaAdquisicion, Calendar fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida) {//Constructor para basculas
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaAdquisicion = fechaAdquisicion;
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
        this.capacidadMaxima = capacidadMaxima;
        this.unidadDeMedida = unidadDeMedida;
        this.estado = "Activo";
        
        this.lotesEnExistenciaActuales = new ArrayList();
        this.movimientosAsociadosDeEntradaActuales = new ArrayList();
        this.movimientosAsociadosDeSalidaActuales = new ArrayList();
    }    
    
    public Equipamiento(String nombre, String direccion, Calendar fechaAdquisicion, Calendar fechaUltimoMantenimiento, float capacidadMaxima, String unidadDeMedida, Bascula unaBascula) throws ExcepcionCargaParametros {
        
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaAdquisicion = fechaAdquisicion;
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
        this.capacidadMaxima = capacidadMaxima;
        this.unidadDeMedida = unidadDeMedida;
        this.estado = "Activo";
        this.basculaAsociada = unaBascula;
        
        this.lotesEnExistenciaActuales = new ArrayList();
        this.movimientosAsociadosDeEntradaActuales = new ArrayList();
        this.movimientosAsociadosDeSalidaActuales = new ArrayList();
    }
    
    public float obtenerCapacidadDisponible(String unidadDeMedida) throws Exception{
        //Capacidad máxima menos todos los movimientos de entrada mas todos los movimientos de salida
        float retorno = this.capacidadMaxima;
        float total_Entrada = 0;
        float total_Salida = 0;
        Iterator entradas = this.movimientosAsociadosDeEntradaActuales.iterator();
        Iterator salidas = this.movimientosAsociadosDeSalidaActuales.iterator();
        while (entradas.hasNext()){
            MovimientoInternoMateriaPrima unMovimientoDeEntrada = (MovimientoInternoMateriaPrima) entradas.next();
            if (unMovimientoDeEntrada.estaRegular())
                total_Entrada = total_Entrada + unMovimientoDeEntrada.getPesoNeto(unidadDeMedida);
        }
        while (salidas.hasNext()){
            MovimientoInternoMateriaPrima unMovimientoDeSalida = (MovimientoInternoMateriaPrima) salidas.next();
            if (unMovimientoDeSalida.estaRegular())
                total_Salida = total_Salida + unMovimientoDeSalida.getPesoNeto((unidadDeMedida));
        }
        retorno = total_Entrada - total_Salida;
        return retorno;
    }
    
    

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaAdquisicion() { 
        return new Date(this.fechaAdquisicion.getTimeInMillis());
    }
    
    public Calendar getFechaAdquisicionC() { 
        return this.fechaAdquisicion;
    }
    
    public String obtenerFechaAdquisicion() {//Tratamiento especial para obtener en formato dd/mm/aaaa
        
        return Integer.toString(fechaAdquisicion.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(fechaAdquisicion.get(Calendar.MONTH)+1)+"/"+Integer.toString(fechaAdquisicion.get(Calendar.YEAR));
    }
    
    public String obtenerFechaUltimoMantenimiento(){//Tratamiento especial para obtener en formato dd/mm/aaaa
        return Integer.toString(fechaUltimoMantenimiento.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(fechaUltimoMantenimiento.get(Calendar.MONTH)+1)+"/"+Integer.toString(fechaUltimoMantenimiento.get(Calendar.YEAR));
    }
    
    public Date getFechaUltimoMantenimiento() { 
        return new Date(this.fechaUltimoMantenimiento.getTimeInMillis());
    }
    public Calendar getFechaUltimoMantenimientoC() { 
        return this.fechaUltimoMantenimiento;
    }

    public float getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaAdquisicion(Calendar fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public void setFechaUltimoMantenimiento(Calendar fechaUltimoMantenimiento) {
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
    }

    public void setCapacidadMaxima(float capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList getMovimientosAsociadosDeEntradaActuales() {
        return movimientosAsociadosDeEntradaActuales;
    }

    public void setMovimientosAsociadosDeEntradaActuales(ArrayList movimientosAsociadosDeEntradaActuales) {
        this.movimientosAsociadosDeEntradaActuales = movimientosAsociadosDeEntradaActuales;
    }

    public ArrayList getMovimientosAsociadosDeSalidaActuales() {
        return movimientosAsociadosDeSalidaActuales;
    }

    public void setMovimientosAsociadosDeSalidaActuales(ArrayList movimientosAsociadosDeSalidaActuales) {
        this.movimientosAsociadosDeSalidaActuales = movimientosAsociadosDeSalidaActuales;
    }

    public Bascula getBasculaAsociada() {
        return basculaAsociada;
    }

    public void setBasculaAsociada(Bascula basculaAsociada) {
        this.basculaAsociada = basculaAsociada;
    }
    
    public String Nombre(){
        return this.nombre;
    }
    
    

    @Override
    public String toString() {
        return this.nombre;
    }

    boolean estaActivo() {
        return this.estado.equals("Activo");
    }
    
    public Object[] devolverVector() {
        String unaBascula = "----";
        if (this.basculaAsociada != null){
            unaBascula = this.basculaAsociada.getNombre();
        }
        Object[] vec ={this.getId(),this.getNombre(), this.getDireccion(), this.getEstado(), unaBascula};
        return vec;
    }
    
    public String getTipo(){    //METODO PARA LA INTERFAZ GRAFICA
        String retorno = null;
        if (this instanceof Molino)
            retorno = "Molino";
        if (this instanceof Deposito)
            retorno = "Deposito";
        if (this instanceof CamaraEstacionamiento)
            retorno = "Camara Est. Acel.";
        if (this instanceof Bascula)
            retorno = "Bascula";
        return retorno;
    }
    
    public void darDeBaja() {
        this.estado = "Baja";
    }
    

    public void modificarEquipamiento(String unNombre, String unaDireccion, Calendar unaFechaAdquisicion, Calendar unaFechaUltimoMantenimiento, float unaCapacidadMaxima, String unaUnidadDeMedida, String unEstado, Bascula unaBascula){
        this.nombre = unNombre;
        this.direccion = unaDireccion;
        this.fechaAdquisicion = unaFechaAdquisicion;
        this.fechaUltimoMantenimiento = unaFechaUltimoMantenimiento;
        this.capacidadMaxima = unaCapacidadMaxima;
        this.unidadDeMedida = unaUnidadDeMedida;
        this.estado = unEstado;
        this.basculaAsociada = unaBascula;
    }

    public boolean poseeLotes() {
        return !this.lotesEnExistenciaActuales.isEmpty();
    }

    public boolean seLlama(String unNombre) {
        return this.nombre.toUpperCase().equals(unNombre.toUpperCase());
    }

    public void cambiarDeBascula(Bascula unaBascula) throws ExcepcionCargaParametros {
        
        if (!estaActivo())
            throw new ExcepcionCargaParametros("La bascula no se encuentra activa para ser asociada.");
        if (this.basculaAsociada == null)
            throw new ExcepcionCargaParametros("El equipamiento no posee una Bascula asociada");
        if (unaBascula.equals(this.basculaAsociada))
            return;
        this.basculaAsociada.removerEquipamiento(this);
        this.basculaAsociada = unaBascula;
    }

    public void agregarLote(Lote unLote){
        this.lotesEnExistenciaActuales.add(unLote);
    }
    public String getFechaAdquisicionS(){       //METODO PARA REPORTES
        return this.obtenerFechaAdquisicion();
    }
    public String getFechaUltimoMantenimientoS(){   //METODO PARA REPORTES
        return this.obtenerFechaUltimoMantenimiento();
    }
    
    public String getTipoEquipamiento(){    //METODO PARA REPORTES
        String retorno = null;
        if (this instanceof Molino)
            retorno = "Molino";
        if (this instanceof Deposito)
            retorno = "Deposito";
        if (this instanceof CamaraEstacionamiento)
            retorno = "Camara Est. Acel.";
        if (this instanceof Bascula)
            retorno = "Bascula";
        return retorno;
    }
    public String getBasculaAsociadaS(){        //METODO PARA REPORTES
        if (this.basculaAsociada != null)
            return this.getBasculaAsociada().getNombre();
        else
            return "No posee";
    }
    
}




