/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.Bascula;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Equipamiento implements Reporte{
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
    
    public static Calendar FECHA_REPORTE_INYM = Calendar.getInstance();
    
    public static final String TIPO_LABORATORIO = "Laboratorio";
    public static final String TIPO_BASCULA = "Bascula";
    public static final String TIPO_DEPOSITO = "Deposito";
    public static final String TIPO_CAMARA_ESTACIONAMIENTO = "Camara de estacionamiento acelerado";
    public static final String TIPO_MOLINO = "Molino";
    
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
    
    public float obtenerCapacidadDisponible(String unidadDeMedida) throws ExcepcionCargaParametros{
        //Capacidad máxima menos todos los movimientos de entrada mas todos los movimientos de salida
        float retorno = this.capacidadMaxima;
        Iterator recorredorLotes = this.lotesEnExistenciaActuales.iterator();
        while (recorredorLotes.hasNext()){
            Lote unLote = (Lote) recorredorLotes.next();
            retorno = retorno - Organizacion.convertirUnidadPeso(unLote.getUnidadDeMedida(), unLote.getCantidadTotalPesoIngresado(), this.unidadDeMedida);
        }
        
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


    public ArrayList getMovimientosAsociadosDeSalidaActuales() {
        return movimientosAsociadosDeSalidaActuales;
    }

    public Bascula getBasculaAsociada() {
        return basculaAsociada;
    }

    public void setBasculaAsociada(Bascula basculaAsociada) {
        this.basculaAsociada = basculaAsociada;
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
        if (this instanceof Laboratorio)
            retorno = "Laboratorio";
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

    public boolean poseeUnoOMasLotes() {
        return !this.lotesEnExistenciaActuales.isEmpty();
    }

    public boolean seLlama(String unNombre) {
        return this.nombre.toUpperCase().equals(unNombre.toUpperCase());
    }

    public void cambiarDeBascula(Bascula unaBascula) throws ExcepcionCargaParametros {
        
        if (!unaBascula.estaActivo())
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
        if (this instanceof Laboratorio)
            retorno = "Laboratorio";
        return retorno;
    }
    public String getBasculaAsociadaS(){        //METODO PARA REPORTES
        if (this.basculaAsociada != null)
            return this.getBasculaAsociada().getNombre();
        else
            return "No posee";
    }

    public boolean puedeAlbergar(float unPesoAVerificar, String unidadMedidaPeso) throws ExcepcionCargaParametros {
        float capacidadDisponible = Organizacion.convertirUnidadPeso(this.unidadDeMedida, this.capacidadMaxima, unidadMedidaPeso) - this.getCantidadAlmacenada(unidadMedidaPeso);
        return (capacidadDisponible >= unPesoAVerificar);
    }
    
    public ArrayList getLotesAsociadosNoAnulados(){
        ArrayList retorno = new ArrayList();
        Iterator lotes = this.lotesEnExistenciaActuales.iterator();
        while (lotes.hasNext()){
            Lote unLote = (Lote) lotes.next();
            if (!unLote.estaAnulado())
                retorno.add(unLote);
        }        
        return retorno;
    }
    
    private float getCantidadAlmacenada(String unaUnidadMedida) throws ExcepcionCargaParametros {
        float retorno = 0;
        Iterator lotesNoAnulados = getLotesAsociadosNoAnulados().iterator();
        while (lotesNoAnulados.hasNext()){
            Lote unLote = (Lote) lotesNoAnulados.next();
            retorno = retorno + Organizacion.convertirUnidadPeso(unLote.getUnidadDeMedida(), unLote.getCantidadTotalPesoIngresado(), unaUnidadMedida);
        }
        return retorno;
    }

    public void removerLote(Lote unLote) {
        this.lotesEnExistenciaActuales.remove(unLote);
    }

    boolean poseeLotes(ArrayList<Lote> lotes) {
        boolean estanTodosDentro = true;
        Iterator recorredorLotes = lotes.iterator();
        while (recorredorLotes.hasNext() && estanTodosDentro){
            Lote unLote = (Lote) recorredorLotes.next();
            estanTodosDentro = this.lotesEnExistenciaActuales.contains(unLote);
        }
        return estanTodosDentro;
    }

    boolean fechaAdquisicionEstaEntre(Calendar fechaAdquisicionLimiteInferior, Calendar fechaAdquisicionLimiteSuperior) {
        fechaAdquisicionLimiteInferior.set(Calendar.HOUR, 0);
        fechaAdquisicionLimiteInferior.set(Calendar.MINUTE, 0);
        fechaAdquisicionLimiteInferior.set(Calendar.SECOND, 0);
        fechaAdquisicionLimiteInferior.set(Calendar.MILLISECOND, 0);

        fechaAdquisicionLimiteSuperior.set(Calendar.HOUR_OF_DAY, 24);
        fechaAdquisicionLimiteSuperior.set(Calendar.MINUTE, 59);
        fechaAdquisicionLimiteSuperior.set(Calendar.SECOND, 59);        
        return (this.fechaAdquisicion.after(fechaAdquisicionLimiteInferior) && this.fechaAdquisicion.before(fechaAdquisicionLimiteSuperior));
    }
    @Override
    public String getReporte(){
        String retorno = "";
        retorno = retorno + "Nombre: "+this.nombre +""
                + "\t\tTipo: "+ this.getTipoEquipamiento()+" \n\n";
        retorno = retorno + "Fecha de adquisicion: "+Organizacion.expresarCalendario(fechaAdquisicion)+""
                + "\t\t\tBascula asociada: "+ this.getBasculaAsociadaS()+" \n\n";
        retorno = retorno + "Fecha de ultimo mantenimiento: "+Organizacion.expresarCalendario(fechaUltimoMantenimiento)+""
                + "\t\tEstado: "+ this.getEstado()+" \n\n";
        retorno = retorno + "Direccion: "+ direccion;
        return retorno;
    }

    public Float getPesoTotalDeYCVKgV4(Calendar unaFecha) {
        Float pesoTotal = 0f;
        
        Iterator recorredorMovimientosEntrada = this.movimientosAsociadosDeEntradaActuales.iterator();
        while (recorredorMovimientosEntrada.hasNext()){
            boolean seMovioElLote;
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) recorredorMovimientosEntrada.next();
            MovimientoInternoMateriaPrima unMovimientoPosterior = unMovimiento.getMovimientoInmediatamentePosterior();
            seMovioElLote = (unMovimientoPosterior != null && unMovimiento.estaRegular() && unMovimientoPosterior.estaRegular() && unMovimientoPosterior.esAnteriorOIgualA(unaFecha)); //hubo movimientos posteriores
            if (!seMovioElLote){
                //System.out.println("no se movio el lote");
                Lote unLote = unMovimiento.getLoteAsociado();
                /*if (unLote.getMovimientoDeIngreso().esAnteriorOIgualA(Equipamiento.FECHA_REPORTE_INYM))
                    System.out.println("el movimiento de ingreso de este lote es anterior a la fecha " +Organizacion.expresarCalendario(FECHA_REPORTE_INYM)+" solicitada");
                else
                    System.out.println("el movimiento de ingreso "+unLote.getMovimientoDeIngreso().getId()+" de este lote "+unLote.getId()+" (Fecha :"+Organizacion.expresarCalendario(unLote.getMovimientoDeIngreso().getFechaOrigenC())+") NO es anterior a la fecha " +Organizacion.expresarCalendario(FECHA_REPORTE_INYM)+" solicitada");*/
                if (unLote.estaRegular() && unLote.esDeYerbaCancadaVerde(unaFecha) && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha)){
                    Float unPeso = unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(unaFecha);
                    pesoTotal = pesoTotal + unPeso;
                }
            }
        }
        return pesoTotal;
    }
    
    public int getUnidadesTotalYCVV4(Calendar unaFecha) {
        int cantidadTotal = 0;
        
        Iterator recorredorMovimientosEntrada = this.movimientosAsociadosDeEntradaActuales.iterator();
        while (recorredorMovimientosEntrada.hasNext()){
            boolean seMovioElLote;
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) recorredorMovimientosEntrada.next();
            MovimientoInternoMateriaPrima unMovimientoPosterior = unMovimiento.getMovimientoInmediatamentePosterior();
            seMovioElLote = (unMovimientoPosterior != null && unMovimiento.estaRegular() && unMovimientoPosterior.estaRegular() && unMovimientoPosterior.esAnteriorOIgualA(unaFecha)); //hubo movimientos posteriores
            if (!seMovioElLote){
                Lote unLote = unMovimiento.getLoteAsociado();
                if (unLote.estaRegular() && unLote.esDeYerbaCancadaVerde(unaFecha) && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha)){
                    int unaCantidad = unLote.obtenerUnidadesTransporteIngresadoConPerdidasIncluidas(unaFecha);
                    cantidadTotal = cantidadTotal + unaCantidad;
                }
            }
        }
        
        return cantidadTotal;
    }

    
    public Float getPesoTotalDeYCEKgV4(Calendar unaFecha) {
        Float pesoTotal = 0f;
        
        Iterator recorredorMovimientosEntrada = this.movimientosAsociadosDeEntradaActuales.iterator();
        while (recorredorMovimientosEntrada.hasNext()){
            boolean seMovioElLote;
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) recorredorMovimientosEntrada.next();
            MovimientoInternoMateriaPrima unMovimientoPosterior = unMovimiento.getMovimientoInmediatamentePosterior();
            seMovioElLote = (unMovimientoPosterior != null && unMovimiento.estaRegular() && unMovimientoPosterior.estaRegular() && unMovimientoPosterior.esAnteriorOIgualA(unaFecha)); //hubo movimientos posteriores
            if (!seMovioElLote){
                Lote unLote = unMovimiento.getLoteAsociado();
                if (unLote.estaRegular() && unLote.esDeYerbaCancadaEstacionada(unaFecha) && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha)){
                    Float unPeso = unLote.obtenerPesoIngresadoConPerdidasIncluidasKg(unaFecha);
                    pesoTotal = pesoTotal + unPeso;
                }
            }
        }
        return pesoTotal;
    }
    
    
    public int getUnidadesTotalYCEV4(Calendar unaFecha) {
        int cantidadTotal = 0;
        Iterator recorredorMovimientosEntrada = this.movimientosAsociadosDeEntradaActuales.iterator();
        while (recorredorMovimientosEntrada.hasNext()){
            boolean seMovioElLote;
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) recorredorMovimientosEntrada.next();
            MovimientoInternoMateriaPrima unMovimientoPosterior = unMovimiento.getMovimientoInmediatamentePosterior();
            seMovioElLote = (unMovimientoPosterior != null && unMovimiento.estaRegular() && unMovimientoPosterior.estaRegular() && unMovimientoPosterior.esAnteriorOIgualA(unaFecha)); //hubo movimientos posteriores
            if (!seMovioElLote){
                Lote unLote = unMovimiento.getLoteAsociado();
                if (unLote.estaRegular() && unLote.esDeYerbaCancadaEstacionada(unaFecha) && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha)){
                    int unaCantidad = unLote.obtenerUnidadesTransporteIngresadoConPerdidasIncluidas(unaFecha);
                    cantidadTotal = cantidadTotal + unaCantidad;
                }
            }
        }
        return cantidadTotal;
    }    

    public Float getPesoTotalDeYCMKgV2(Calendar unaFecha) {
        Float pesoTotal = 0f;
        Iterator recorredorMovimientosEntrada = this.movimientosAsociadosDeEntradaActuales.iterator();
        while (recorredorMovimientosEntrada.hasNext()){
            boolean seMovioElLote;
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) recorredorMovimientosEntrada.next();
            MovimientoInternoMateriaPrima unMovimientoPosterior = unMovimiento.getMovimientoInmediatamentePosterior();
            seMovioElLote = (unMovimientoPosterior != null && unMovimiento.estaRegular() && unMovimientoPosterior.estaRegular() && unMovimientoPosterior.esAnteriorOIgualA(unaFecha)); //hubo movimientos posteriores
            if (!seMovioElLote){
                Lote unLote = unMovimiento.getLoteAsociado();
                if (unLote.estaRegular() && unLote.esDeTipo(Lote.TIPO_LOTE_YERBA_CANCHADA_MOLIDA) && unLote.getCantidadDisponibleParaMolerKg()>0 && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha)){
                    Float unPeso = unLote.getCantidadDisponibleParaMolerKg(unaFecha);
                    pesoTotal = pesoTotal + unPeso;
                }
            }
        }
        
        return pesoTotal;
    }

    
    public int getUnidadesTotalYCMV2(Calendar unaFecha) {
        int cantidadTotal = 0;
        
        Iterator recorredorMovimientosEntrada = this.movimientosAsociadosDeEntradaActuales.iterator();
        while (recorredorMovimientosEntrada.hasNext()){
            boolean seMovioElLote;
            MovimientoInternoMateriaPrima unMovimiento = (MovimientoInternoMateriaPrima) recorredorMovimientosEntrada.next();
            MovimientoInternoMateriaPrima unMovimientoPosterior = unMovimiento.getMovimientoInmediatamentePosterior();
            seMovioElLote = (unMovimientoPosterior != null && unMovimiento.estaRegular() && unMovimientoPosterior.estaRegular() && unMovimientoPosterior.esAnteriorOIgualA(unaFecha)); //hubo movimientos posteriores
            if (!seMovioElLote){
                Lote unLote = unMovimiento.getLoteAsociado();
                if (unLote.estaRegular() && unLote.esDeTipo(Lote.TIPO_LOTE_YERBA_CANCHADA_MOLIDA) && unLote.getMovimientoDeIngreso().esAnteriorOIgualA(unaFecha)){
                    int unaCantidad = unLote.obtenerUnidadesTransporteIngresadoConPerdidasIncluidas(unaFecha);
                    cantidadTotal = cantidadTotal + unaCantidad;
                }
            }
        }
        
        return cantidadTotal;
    }
    
    public String getReporteINYM(){
        String retorno = "";
        retorno = retorno + this.getTipoEquipamiento()+": "+this.getNombre()+", Direccion: "+this.getDireccion();
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Yerba canchada verde: se tienen "+UtilidadesInterfazGrafica.formatearFlotante(this.getPesoTotalDeYCVKgV4(Equipamiento.FECHA_REPORTE_INYM))+" kgs distribuidos en "+this.getUnidadesTotalYCVV4(Equipamiento.FECHA_REPORTE_INYM)+ " bolsas.";
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Yerba canchada estacionada: se tienen "+UtilidadesInterfazGrafica.formatearFlotante(this.getPesoTotalDeYCEKgV4(Equipamiento.FECHA_REPORTE_INYM))+" kgs distribuidos en "+this.getUnidadesTotalYCEV4(Equipamiento.FECHA_REPORTE_INYM)+ " bolsas";
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Yerba canchada molida: se tienen "+UtilidadesInterfazGrafica.formatearFlotante(this.getPesoTotalDeYCMKgV2(Equipamiento.FECHA_REPORTE_INYM))+" kgs";// distribuidos en "+unEquipamiento.getUnidadesTotalYCM()+ " bolsas";
       return retorno;
    }
    /*public String getReporteINYM(){
        String retorno = "";
        retorno = retorno + this.getTipoEquipamiento()+": "+this.getNombre()+", Direccion: "+this.getDireccion();
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Yerba canchada verde: se tienen "+UtilidadesInterfazGrafica.formatearFlotante(this.getPesoTotalDeYCVKgV4())+" kgs distribuidos en "+this.getUnidadesTotalYCVV4()+ " bolsas.";
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Yerba canchada estacionada: se tienen "+UtilidadesInterfazGrafica.formatearFlotante(this.getPesoTotalDeYCEKgV4())+" kgs distribuidos en "+this.getUnidadesTotalYCEV4()+ " bolsas";
        retorno = retorno + "\n";
        retorno = retorno + "\n";
        retorno = retorno + "Yerba canchada molida: se tienen "+UtilidadesInterfazGrafica.formatearFlotante(this.getPesoTotalDeYCMKgV2())+" kgs";// distribuidos en "+unEquipamiento.getUnidadesTotalYCM()+ " bolsas";
        return retorno;
    }*/
    
    /*public Float getYCV(){
        return this.getPesoTotalDeYCVKg();
    }
    
    public Float getYCE(){
        return this.getPesoTotalDeYCEKg();
    }
    
    public Float getYM(){
        return this.getPesoTotalDeYCMKg();
    }*/
    
    public Float getYCV(){
        return this.getPesoTotalDeYCVKgV4(Equipamiento.FECHA_REPORTE_INYM);
    }
    
    public Float getYCE(){
        return this.getPesoTotalDeYCEKgV4(Equipamiento.FECHA_REPORTE_INYM);
    }
    
    public Float getYM(){
        return this.getPesoTotalDeYCMKgV2(Equipamiento.FECHA_REPORTE_INYM);
    }
    
    public void agregarMovimientoEntrada(MovimientoInternoMateriaPrima unMovimiento){
        this.movimientosAsociadosDeEntradaActuales.add(unMovimiento);
    }
    
    public void agregarMovimientoSalida(MovimientoInternoMateriaPrima unMovimiento){
        this.movimientosAsociadosDeSalidaActuales.add(unMovimiento);
    }

    public Float[][] getCantidadAvistamientos(Calendar fechaLimiteInferior, Calendar FechaLimiteSuperior){
        int cantidadDias = (int) Organizacion.calcularCantidadDiasDiferencia(fechaLimiteInferior, FechaLimiteSuperior);
        Float[][] retorno = new Float[cantidadDias][Lote.TIPO_LOTES.length];
        System.out.println("cantidad de dias "+cantidadDias);
        for (int i = 0; i<cantidadDias; i++){
            Calendar unaFecha = (Calendar) fechaLimiteInferior.clone();
            unaFecha.add(Calendar.DATE, i);
            retorno[i][0] = getPesoTotalDeYCVKgV4(unaFecha);
            //System.out.println("peso total YCV de dia "+Organizacion.expresarCalendario(unaFecha)+": "+retorno[i][0]);
            retorno[i][1] = getPesoTotalDeYCEKgV4(unaFecha);
            //System.out.println("peso total YCE de dia "+Organizacion.expresarCalendario(unaFecha)+": "+retorno[i][1]);
            retorno[i][2] = getPesoTotalDeYCMKgV2(unaFecha);
            //System.out.println("peso total YCM de dia "+Organizacion.expresarCalendario(unaFecha)+": "+retorno[i][2]);
        }
        return retorno;
    }

}




