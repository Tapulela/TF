/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.AnalisisLaboratorio;
import LogicaDeNegocio.OrdenDeProduccion;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class CriterioAnalisisLaboratorio {
    
    public static final String ESTADO_ALTA = "Activo";
    public static final String ESTADO_BAJA = "Baja";
    
    public static final String ESTADO_APROBADO = "APROBADO";
    public static final String ESTADO_RECHAZADO = "RECHAZADO";
    
    public static final String TIPO_YCV = "YCV";
    public static final String TIPO_YCE = "YCE";
    public static final String TIPO_YM = "YM";
    
    private int id;
    private String nombre;
    private String descripcion;
    private String estado;
    private Calendar fechaOrigen;
    
    private String puntosNegros;
    private String torrada;
    private String color;
    private String aroma;
    private String tacto;
    private String degustacion;
    
    private Float porcentajeSemillaLimiteInferior;
    private Float porcentajeSemillaLimiteSuperior;
    
    private Float porcentajePaloLimiteInferior;
    private Float porcentajePaloLimiteSuperior;
    
    private Float porcentajePolvoLimiteInferior;
    private Float porcentajePolvoLimiteSuperior;
    
    private Float porcentajeHojaLimiteInferior;
    private Float porcentajeHojaLimiteSuperior;
    
    private Float porcentajeHumedadLimiteInferior;
    private Float porcentajeHumedadLimiteSuperior;
    
    private String tipoLote;
    
    private ArrayList ordenesDeProduccionAsociadas;
    private ArrayList analisisLaboratorio;

    public CriterioAnalisisLaboratorio(int id, String nombre, String descripcion, String estado, 
            java.sql.Date fechaOrigen, 
            Float porcentajeSemillaLimiteInferior, Float porcentajeSemillaLimiteSuperior, 
            Float porcentajePaloLimiteInferior, Float porcentajePaloLimiteSuperior, 
            Float porcentajePolvoLimiteInferior, Float porcentajePolvoLimiteSuperior, 
            Float porcentajeHojaLimiteInferior, Float porcentajeHojaLimiteSuperior, 
            Float porcentajeHumedadLimiteInferior, Float porcentajeHumedadLimiteSuperior, 
            String tipoLote, String puntosNegros, String torrada, String color, String aroma, String tacto, String degustacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.puntosNegros = puntosNegros;
        this.torrada = torrada;
        this.color = color;
        this.aroma = aroma;
        this.tacto = tacto;
        this.degustacion = degustacion;
        this.fechaOrigen = Calendar.getInstance();
        this.fechaOrigen.setTime(fechaOrigen);
        this.porcentajeSemillaLimiteInferior = porcentajeSemillaLimiteInferior;
        this.porcentajeSemillaLimiteSuperior = porcentajeSemillaLimiteSuperior;
        this.porcentajePaloLimiteInferior = porcentajePaloLimiteInferior;
        this.porcentajePaloLimiteSuperior = porcentajePaloLimiteSuperior;
        this.porcentajePolvoLimiteInferior = porcentajePolvoLimiteInferior;
        this.porcentajePolvoLimiteSuperior = porcentajePolvoLimiteSuperior;
        this.porcentajeHojaLimiteInferior = porcentajeHojaLimiteInferior;
        this.porcentajeHojaLimiteSuperior = porcentajeHojaLimiteSuperior;
        this.porcentajeHumedadLimiteInferior = porcentajeHumedadLimiteInferior;
        this.porcentajeHumedadLimiteSuperior = porcentajeHumedadLimiteSuperior;
        this.tipoLote = tipoLote;
        
        this.ordenesDeProduccionAsociadas = new ArrayList();
        this.analisisLaboratorio = new ArrayList();
    }
    
    

    public CriterioAnalisisLaboratorio(String nombre, String descripcion, Calendar fechaOrigen, 
            Float porcentajeSemillaLimiteInferior, Float porcentajeSemillaLimiteSuperior, 
            Float porcentajePaloLimiteInferior, Float porcentajePaloLimiteSuperior, 
            Float porcentajePolvoLimiteInferior, Float porcentajePolvoLimiteSuperior, 
            Float porcentajeHojaLimiteInferior, Float porcentajeHojaLimiteSuperior, 
            Float porcentajeHumedadLimiteInferior, Float porcentajeHumedadLimiteSuperior,
            String tipoLote, String puntosNegros, String torrada, String color, String aroma, String tacto, String degustacion) {
        this.nombre = nombre;
        this.estado = ESTADO_ALTA;
        this.descripcion = descripcion;
        this.fechaOrigen = fechaOrigen;
        this.puntosNegros = puntosNegros;
        this.torrada = torrada;
        this.color = color;
        this.aroma = aroma;
        this.tacto = tacto;
        this.degustacion = degustacion;
        this.porcentajeSemillaLimiteInferior = porcentajeSemillaLimiteInferior;
        this.porcentajeSemillaLimiteSuperior = porcentajeSemillaLimiteSuperior;
        this.porcentajePaloLimiteInferior = porcentajePaloLimiteInferior;
        this.porcentajePaloLimiteSuperior = porcentajePaloLimiteSuperior;
        this.porcentajePolvoLimiteInferior = porcentajePolvoLimiteInferior;
        this.porcentajePolvoLimiteSuperior = porcentajePolvoLimiteSuperior;
        this.porcentajeHojaLimiteInferior = porcentajeHojaLimiteInferior;
        this.porcentajeHojaLimiteSuperior = porcentajeHojaLimiteSuperior;
        this.porcentajeHumedadLimiteInferior = porcentajeHumedadLimiteInferior;
        this.porcentajeHumedadLimiteSuperior = porcentajeHumedadLimiteSuperior;
        this.tipoLote = tipoLote;
        this.ordenesDeProduccionAsociadas = new ArrayList();
        this.analisisLaboratorio = new ArrayList();
    }

    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public String getEstado() {
        return estado;
    }

    public java.sql.Date getFechaOrigen() {
        return new Date(this.fechaOrigen.getTimeInMillis());
    }
    
    public Calendar getFechaOrigenC() {
        return this.fechaOrigen;
    }

    public String getPuntosNegros() {
        return puntosNegros;
    }

    public String getTorrada() {
        return torrada;
    }

    public String getColor() {
        return color;
    }

    public String getAroma() {
        return aroma;
    }

    public String getTacto() {
        return tacto;
    }

    public String getDegustacion() {
        return degustacion;
    }

    public Float getPorcentajeSemillaLimiteInferior() {
        return porcentajeSemillaLimiteInferior;
    }

    public Float getPorcentajeSemillaLimiteSuperior() {
        return porcentajeSemillaLimiteSuperior;
    }

    public Float getPorcentajePaloLimiteInferior() {
        return porcentajePaloLimiteInferior;
    }

    public Float getPorcentajePaloLimiteSuperior() {
        return porcentajePaloLimiteSuperior;
    }

    public Float getPorcentajePolvoLimiteInferior() {
        return porcentajePolvoLimiteInferior;
    }

    public Float getPorcentajePolvoLimiteSuperior() {
        return porcentajePolvoLimiteSuperior;
    }

    public Float getPorcentajeHojaLimiteInferior() {
        return porcentajeHojaLimiteInferior;
    }

    public Float getPorcentajeHojaLimiteSuperior() {
        return porcentajeHojaLimiteSuperior;
    }

    public Float getPorcentajeHumedadLimiteInferior() {
        return porcentajeHumedadLimiteInferior;
    }

    public Float getPorcentajeHumedadLimiteSuperior() {
        return porcentajeHumedadLimiteSuperior;
    }

    public String getTipoLote() {
        return tipoLote;
    }

    public void agregarOrdenDeProduccion(OrdenDeProduccion unaOrdenDeProduccion) {
        if (!this.ordenesDeProduccionAsociadas.contains(unaOrdenDeProduccion))
            this.ordenesDeProduccionAsociadas.add(unaOrdenDeProduccion);
    }

    public ArrayList getAnalisisLaboratorio() {
        return analisisLaboratorio;
    }

    public void agregarAnalisis(AnalisisLaboratorio unAnalisis) {
        this.analisisLaboratorio.add(unAnalisis);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String generarDetalleDeResultadoDeAnalisis(AnalisisLaboratorio unAnalisis) {
        String resultado = "";
        if (!unAnalisis.getPuntosNegros().equals(this.getPuntosNegros()))
            resultado = resultado+"No cumple con el criterio de  puntos negros: "+this.getPuntosNegros()+" (Valor registrado: "+unAnalisis.getPuntosNegros()+")\n";
        if (!unAnalisis.getTorrada().equals(this.getTorrada()))
            resultado = resultado+"No cumple con el criterio de torrada: "+this.getTorrada()+" (Valor registrado: "+unAnalisis.getTorrada()+")\n";
        if (!unAnalisis.getColor().equals(this.getColor()))
            resultado = resultado+"No cumple con el criterio de torrada: "+this.getColor()+" (Valor registrado: "+unAnalisis.getColor()+")\n";
        if (!unAnalisis.getAroma().equals(this.getAroma()))
            resultado = resultado+"No cumple con el criterio de aroma: "+this.getAroma()+" (Valor registrado: "+unAnalisis.getAroma()+")\n";
        if (!unAnalisis.getTacto().equals(this.getTacto()))
            resultado = resultado+"No cumple con el criterio de tacto: "+this.getTacto()+" (Valor registrado: "+unAnalisis.getTacto()+")\n";
        if (!unAnalisis.getDegustacion().equals(this.getDegustacion()))
            resultado = resultado+"No cumple con el criterio de degustacion: "+this.getDegustacion()+" (Valor registrado: "+unAnalisis.getDegustacion()+")\n";
        if (!(this.getPorcentajePolvoLimiteInferior() <= unAnalisis.getPorcentajePolvo() && unAnalisis.getPorcentajePolvo() <= this.getPorcentajePolvoLimiteSuperior()))
            resultado = resultado+"No cumple con el criterio de porcentaje de polvo: ["+this.getPorcentajePolvoLimiteInferior()+" - " +this.getPorcentajePolvoLimiteSuperior() +"] (Valor registrado: "+unAnalisis.getPorcentajePolvo()+")\n";
        if (!(this.getPorcentajePaloLimiteInferior()<= unAnalisis.getPorcentajePalo()&& unAnalisis.getPorcentajePalo()<= this.getPorcentajePaloLimiteSuperior()))
            resultado = resultado+"No cumple con el criterio de porcentaje de palo: ["+this.getPorcentajePaloLimiteInferior()+" - " +this.getPorcentajePaloLimiteSuperior() +"] (Valor registrado: "+unAnalisis.getPorcentajePalo()+")\n";
        if (!(this.getPorcentajeHojaLimiteInferior()<= unAnalisis.getPorcentajeHoja()&& unAnalisis.getPorcentajeHoja()<= this.getPorcentajeHojaLimiteSuperior()))
            resultado = resultado+"No cumple con el criterio de porcentaje de hoja: ["+this.getPorcentajeHojaLimiteInferior()+" - " +this.getPorcentajeHojaLimiteSuperior() +"] (Valor registrado: "+unAnalisis.getPorcentajeHoja()+")\n";
        if (!(this.getPorcentajeHumedadLimiteInferior()<= unAnalisis.getPorcentajeHumedad()&& unAnalisis.getPorcentajeHumedad()<= this.getPorcentajeHumedadLimiteSuperior()))
            resultado = resultado+"No cumple con el criterio de porcentaje de humedad: ["+this.getPorcentajeHumedadLimiteInferior()+" - " +this.getPorcentajeHumedadLimiteSuperior() +"] (Valor registrado: "+unAnalisis.getPorcentajeHumedad()+")\n";
        if (!(this.getPorcentajeSemillaLimiteInferior()<= unAnalisis.getPorcentajeSemilla()&& unAnalisis.getPorcentajeSemilla()<= this.getPorcentajeSemillaLimiteSuperior()))
            resultado = resultado+"No cumple con el criterio de porcentaje de semilla: ["+this.getPorcentajeSemillaLimiteInferior()+" - " +this.getPorcentajeSemillaLimiteSuperior() +"] (Valor registrado: "+unAnalisis.getPorcentajeSemilla()+")\n";
        
        return resultado;
    }

    public void evaluarAnalisis(AnalisisLaboratorio unAnalisis) {
        boolean seEncuentraAprobado = true;
        if (seEncuentraAprobado)
            seEncuentraAprobado = (unAnalisis.getPuntosNegros().equals(this.getPuntosNegros()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = (unAnalisis.getTorrada().equals(this.getTorrada()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = (unAnalisis.getColor().equals(this.getColor()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = (unAnalisis.getAroma().equals(this.getAroma()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = (unAnalisis.getTacto().equals(this.getTacto()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = (unAnalisis.getDegustacion().equals(this.getDegustacion()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = ((this.getPorcentajePolvoLimiteInferior() <= unAnalisis.getPorcentajePolvo() && unAnalisis.getPorcentajePolvo() <= this.getPorcentajePolvoLimiteSuperior()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = ((this.getPorcentajePaloLimiteInferior()<= unAnalisis.getPorcentajePalo() && unAnalisis.getPorcentajePalo()<= this.getPorcentajePaloLimiteSuperior()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = ((this.getPorcentajeHojaLimiteInferior()<= unAnalisis.getPorcentajeHoja() && unAnalisis.getPorcentajeHoja()<= this.getPorcentajeHojaLimiteSuperior()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = ((this.getPorcentajeHumedadLimiteInferior()<= unAnalisis.getPorcentajeHumedad() && unAnalisis.getPorcentajeHumedad()<= this.getPorcentajeHumedadLimiteSuperior()));
        if (seEncuentraAprobado)
            seEncuentraAprobado = ((this.getPorcentajeSemillaLimiteInferior()<= unAnalisis.getPorcentajeSemilla() && unAnalisis.getPorcentajeSemilla()<= this.getPorcentajeSemillaLimiteSuperior()));
        
        if (seEncuentraAprobado)
            unAnalisis.setConclusion(CriterioAnalisisLaboratorio.ESTADO_APROBADO);
        else
            unAnalisis.setConclusion(CriterioAnalisisLaboratorio.ESTADO_RECHAZADO);
    }

    public boolean poseeOrdenDeProduccionImplicada(OrdenDeProduccion unaOrdenProduccionSeleccionada) {
        return this.ordenesDeProduccionAsociadas.contains(unaOrdenProduccionSeleccionada);
        
    }

    public boolean poseeEstado(String estadoSeleccionado) {
        return this.estado.toUpperCase().equals(estadoSeleccionado.toUpperCase());
    }

    public boolean fechaOrigenEstaEntre(Calendar fechaOrigenInferior, Calendar fechaOrigenSuperior) {
        fechaOrigenInferior.set(Calendar.HOUR_OF_DAY, 0);
        fechaOrigenInferior.set(Calendar.MINUTE, 0);
        fechaOrigenInferior.set(Calendar.SECOND, 0);
        fechaOrigenInferior.set(Calendar.MILLISECOND, 0);
        
        fechaOrigenSuperior.set(Calendar.HOUR_OF_DAY, 23);
        fechaOrigenSuperior.set(Calendar.MINUTE, 59);
        fechaOrigenSuperior.set(Calendar.SECOND, 59);        
        return ((this.getFechaOrigenC().compareTo(fechaOrigenInferior)>=0 )&& this.getFechaOrigenC().compareTo(fechaOrigenSuperior)<=0);
    }

    public boolean poseeNombre(String unNombre) {
        return this.nombre.toUpperCase().equals(unNombre.toUpperCase());
    }

    public boolean poseeDescripcion(String unaDescripcion) {
        return this.descripcion.toUpperCase().contains(unaDescripcion.toUpperCase());
    }

    public boolean poseeAnalisisDeLaboratorioAsociado(AnalisisLaboratorio unAnalisisDeLaboratorioAsociado) {
        return this.analisisLaboratorio.contains(unAnalisisDeLaboratorioAsociado);
    }

    public Object[] devolverVector() {
        Object[] vec ={this.getId(),this.getNombre(), this.getDescripcion(), Organizacion.expresarCalendario(this.getFechaOrigenC()), this.getTipoLote(), this.getEstado()};
        return vec;
    }

    public Object[] devolverVectorPantallaOrdenProduccion() {
        Object[] vec ={this.getId(),this.getNombre(), this.getDescripcion()};
        return vec;
    }

    public boolean seEncuentraActivo() {
        return this.estado.equals("Activo");
    }

    public void darDeBaja() {
        this.estado = CriterioAnalisisLaboratorio.ESTADO_BAJA;
    }

    void removerOrdenDeProduccion(OrdenDeProduccion unaOrdenDeProduccion) {
        this.ordenesDeProduccionAsociadas.remove(unaOrdenDeProduccion);
    }

    @Override
    public boolean equals(Object o) {
        boolean sonIguales = true;
        if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            
        CriterioAnalisisLaboratorio unCriterio = (CriterioAnalisisLaboratorio)o;
        sonIguales = unCriterio.getId()== this.id;
        if (sonIguales)
            sonIguales = unCriterio.getAroma().equals(unCriterio.getAroma());
        if (sonIguales)
            sonIguales = unCriterio.getColor().equals(unCriterio.getColor());
        if (sonIguales)
            sonIguales = unCriterio.getDegustacion().equals(unCriterio.getDegustacion());
        if (sonIguales)
            sonIguales = unCriterio.getTorrada().equals(unCriterio.getTorrada());
        if (sonIguales)
            sonIguales = unCriterio.getDescripcion().equals(unCriterio.getDescripcion());
        return sonIguales;
    }
    
    //Metodos para la carga de datos / pruebas
    public String generarPorcentajePalo(){
        String retorno = "";
        Float porcentajeInferior = porcentajePaloLimiteInferior;
        Float porcentajeSuperior = porcentajePaloLimiteSuperior;
        Float holgura = porcentajeSuperior - porcentajeInferior;
        //Double porcentaje = porcentajeInferior + Math.random()*holgura/32;
        Double porcentaje = porcentajeInferior + 0d;
        retorno = UtilidadesInterfazGrafica.formatearFlotante(Float.parseFloat(""+porcentaje));
        return retorno;
    }
    public String generarPorcentajeSemilla(){
        String retorno = "";
        Float porcentajeInferior = porcentajeSemillaLimiteInferior;
        Float porcentajeSuperior = porcentajeSemillaLimiteSuperior;
        Float holgura = porcentajeSuperior - porcentajeInferior;
        //Double porcentaje = porcentajeInferior + Math.random()*holgura/32;
        Double porcentaje = porcentajeInferior + 0d;
        retorno = UtilidadesInterfazGrafica.formatearFlotante(Float.parseFloat(""+porcentaje));
        return retorno;
    }
    
    public String generarPorcentajePolvo(){
        String retorno = "";
        Float porcentajeInferior = porcentajePolvoLimiteInferior;
        Float porcentajeSuperior = porcentajePolvoLimiteSuperior;
        Float holgura = porcentajeSuperior - porcentajeInferior;
        //Double porcentaje = porcentajeInferior + Math.random()*holgura/32;
        Double porcentaje = porcentajeInferior + 0d;
        retorno = UtilidadesInterfazGrafica.formatearFlotante(Float.parseFloat(""+porcentaje));
        return retorno;
    }
    
    public String generarPorcentajeHoja(){
        String retorno = "";
        Float porcentajeInferior = porcentajeHojaLimiteInferior;
        Float porcentajeSuperior = porcentajeHojaLimiteSuperior;
        Float holgura = porcentajeSuperior - porcentajeInferior;
        //Double porcentaje = porcentajeInferior + Math.random()*holgura/32;
        Double porcentaje = porcentajeInferior + 0d;
        retorno = UtilidadesInterfazGrafica.formatearFlotante(Float.parseFloat(""+porcentaje));
        return retorno;
    }
    public String generarPorcentajeHumedad(){
        String retorno = "";
        Float porcentajeInferior = porcentajeHumedadLimiteInferior;
        Float porcentajeSuperior = porcentajeHumedadLimiteSuperior;
        Float holgura = porcentajeSuperior - porcentajeInferior;
        //Double porcentaje = porcentajeInferior + Math.random()*holgura/32;
        Double porcentaje = porcentajeInferior + 0d;
        retorno = UtilidadesInterfazGrafica.formatearFlotante(Float.parseFloat(""+porcentaje));
        return retorno;
    }
    
    public String generarUnAtributoCuantitativo(int i){
        String retorno = "10,0";
        if (i == 0) //Semilla
            retorno = generarPorcentajeSemilla();
        if (i == 1) //Palo
            retorno = generarPorcentajePalo();
        if (i == 2) //Polvo
            retorno = generarPorcentajePolvo();
        if (i == 3) //Hoja
            retorno = generarPorcentajeHoja();
        if (i == 4) //Humedad
            retorno = generarPorcentajeHumedad();
        return retorno;
    }
    
    public String generarUnAtributoCualitativo(int i){
        String retorno = "";
        if (i==0)   //puntos negros
            retorno = puntosNegros;
        if (i==1)   //torrada
            retorno = torrada;
        if (i==2)   //color
            retorno = color;
        if (i==3)   //aroma
            retorno = aroma;
        if (i==4)   //tacto
            retorno = tacto;
        if (i==5)   //degustacion
            retorno = degustacion;
        return retorno;
    }
    
    
    
}

