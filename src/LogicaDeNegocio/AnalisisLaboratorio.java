/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class AnalisisLaboratorio extends Evento {
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    
    
    private int id;
    private String estado;
    
    private String conclusion;
    private String comentario;
    
    private String puntosNegros;
    private String torrada;
    private String color;
    private String aroma;
    private String tacto;
    private String degustacion;
    
    private float porcentajePalo;
    private float porcentajePolvo;
    private float porcentajeSemilla;
    private float porcentajeHoja;
    private float porcentajeHumedad;
    
    private Lote loteImplicado;
    private Laboratorio laboratorioAsociado;
    private CriterioAnalisisLaboratorio criterioAsociado;
    private OrdenDeCompra ordenDeCompraImplicada;

    public AnalisisLaboratorio(int id, String estado, String puntosNegros, String torrada, String color, String aroma, String tacto, String degustacion, float porcentajePalo, float porcentajePolvo, float porcentajeSemilla, float porcentajeHoja, float porcentajeHumedad, Lote loteImplicado, OrdenDeCompra unaOrdenDeCompra, Laboratorio laboratorioAsociado, CriterioAnalisisLaboratorio criterioAsociado, int idEvento, Usuario unUsuario, Date fechaOrigen, String unComentario, String unaConclusion) {
        super(idEvento, estado, unUsuario, fechaOrigen);
        this.id = id;
        this.estado = estado;
        this.puntosNegros = puntosNegros;
        this.torrada = torrada;
        this.color = color;
        this.aroma = aroma;
        this.tacto = tacto;
        this.degustacion = degustacion;
        this.porcentajePalo = porcentajePalo;
        this.porcentajePolvo = porcentajePolvo;
        this.porcentajeSemilla = porcentajeSemilla;
        this.porcentajeHoja = porcentajeHoja;
        this.porcentajeHumedad = porcentajeHumedad;
        this.loteImplicado = loteImplicado;
        this.laboratorioAsociado = laboratorioAsociado;
        this.criterioAsociado = criterioAsociado;
        this.comentario = unComentario;
        this.conclusion = unaConclusion;
        this.ordenDeCompraImplicada = unaOrdenDeCompra;
    }

    public AnalisisLaboratorio(String puntosNegros, String torrada, String color, String aroma, String tacto, String degustacion, float porcentajePalo, float porcentajePolvo, float porcentajeSemilla, float porcentajeHoja, float porcentajeHumedad, Lote loteImplicado, Laboratorio laboratorioAsociado, CriterioAnalisisLaboratorio criterioAsociado, Usuario unUsuario, String unComentario) {
        //Constructor para los analisis de lotes ya ingresados
        super(AnalisisLaboratorio.ESTADO_REGULAR, unUsuario);
        this.estado = AnalisisLaboratorio.ESTADO_REGULAR;
        this.puntosNegros = puntosNegros;
        this.torrada = torrada;
        this.color = color;
        this.aroma = aroma;
        this.tacto = tacto;
        this.degustacion = degustacion;
        this.porcentajePalo = porcentajePalo;
        this.porcentajePolvo = porcentajePolvo;
        this.porcentajeSemilla = porcentajeSemilla;
        this.porcentajeHoja = porcentajeHoja;
        this.porcentajeHumedad = porcentajeHumedad;
        this.loteImplicado = loteImplicado;
        this.laboratorioAsociado = laboratorioAsociado;
        this.criterioAsociado = criterioAsociado;
        this.comentario = unComentario;
        criterioAsociado.evaluarAnalisis(this);
    }
    
    public AnalisisLaboratorio(String puntosNegros, String torrada, String color, String aroma, String tacto, String degustacion, float porcentajePalo, float porcentajePolvo, float porcentajeSemilla, float porcentajeHoja, float porcentajeHumedad, Laboratorio laboratorioAsociado, CriterioAnalisisLaboratorio criterioAsociado, Usuario unUsuario, String unComentario, OrdenDeCompra unaOrdenDeCompra) {
        //Constructor para los analisis de lotes que van a ingresar
        super(AnalisisLaboratorio.ESTADO_REGULAR, unUsuario);
        this.estado = AnalisisLaboratorio.ESTADO_REGULAR;
        this.puntosNegros = puntosNegros;
        this.torrada = torrada;
        this.color = color;
        this.aroma = aroma;
        this.tacto = tacto;
        this.degustacion = degustacion;
        this.porcentajePalo = porcentajePalo;
        this.porcentajePolvo = porcentajePolvo;
        this.porcentajeSemilla = porcentajeSemilla;
        this.porcentajeHoja = porcentajeHoja;
        this.porcentajeHumedad = porcentajeHumedad;
        this.loteImplicado = loteImplicado;
        this.laboratorioAsociado = laboratorioAsociado;
        this.criterioAsociado = criterioAsociado;
        this.comentario = unComentario;
        criterioAsociado.evaluarAnalisis(this);
        this.ordenDeCompraImplicada = unaOrdenDeCompra;
    }
    
    
    
    
    

    public String getEstado() {
        return estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Laboratorio getLaboratorioAsociado() {
        return laboratorioAsociado;
    }

    public float getPorcentajePalo() {
        return porcentajePalo;
    }

    public float getPorcentajePolvo() {
        return porcentajePolvo;
    }

    public float getPorcentajeSemilla() {
        return porcentajeSemilla;
    }

    public float getPorcentajeHoja() {
        return porcentajeHoja;
    }

    public float getPorcentajeHumedad() {
        return porcentajeHumedad;
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

    
    public Lote getLoteImplicado() {
        return loteImplicado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CriterioAnalisisLaboratorio getCriterioAsociado() {
        return criterioAsociado;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getConclusion() {
        return conclusion;
    }

    public String getComentario() {
        return comentario;
    }

    
    
    
    public void anular() {
        this.setEstado(this.ESTADO_ANULADO);
        super.anularEsteEvento();
    }

    public boolean poseeComentario(String unComentario) {
        return this.comentario.toUpperCase().contains(unComentario);
    }

    public boolean poseeLote(Lote unLote) {
        if (this.loteImplicado != null)
            return this.loteImplicado.equals(unLote);
        else
            return false;
    }

    public boolean poseeTipoDeLote(String tipoLoteSeleccionado) {
        return this.criterioAsociado.getTipoLote().equals(tipoLoteSeleccionado);
    }

    public boolean poseeEstado(String estadoSeleccionado) {
        return this.estado.equals(estadoSeleccionado);
    }

    public boolean poseeOrdenDeProduccionImplicada(OrdenDeProduccion unaOrdenProduccionSeleccionada) {
        return this.loteImplicado.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
    }

    public boolean poseeCriterio(CriterioAnalisisLaboratorio unCriterioSeleccionado) {
        return this.criterioAsociado.equals(unCriterioSeleccionado);
    }

    public void setLoteImplicado(Lote loteImplicado) {
        this.loteImplicado = loteImplicado;
    }

    public void setOrdenDeCompraImplicada(OrdenDeCompra ordenDeCompraImplicada) {
        this.ordenDeCompraImplicada = ordenDeCompraImplicada;
    }
    
    
    public Object[] devolverVector() {
        String columnaLote = "-----";
        if (this.loteImplicado != null)
            columnaLote = this.loteImplicado.getEtiqueta();
        String columnaOrdenDeCompra = "-----";
        if (this.ordenDeCompraImplicada != null)
            columnaOrdenDeCompra = ""+this.ordenDeCompraImplicada.getId();
        Object[] vec ={this.getId(),this.getConclusion(), this.getEstado(), columnaLote, columnaOrdenDeCompra, this.getCriterioAsociado().getNombre()};
        return vec;
    }

    public boolean poseeLaboratorio(Laboratorio unLaboratorio) {
        return this.laboratorioAsociado.equals(unLaboratorio);
    }

    public OrdenDeCompra getOrdenDeCompraImplicada() {
        return ordenDeCompraImplicada;
    }

    public void desvincularseDeLoteYDeOrdenDeCompra() {
        this.loteImplicado = null;
        this.ordenDeCompraImplicada = null;
    }

    public boolean poseeOrdenDeCompra(OrdenDeCompra unaOrdenDeCompra) {
        if (this.ordenDeCompraImplicada == null)
            return false;
        else
            return this.ordenDeCompraImplicada.equals(unaOrdenDeCompra);
    }

    public boolean estaAprobado() {
        return this.conclusion.equals(CriterioAnalisisLaboratorio.ESTADO_APROBADO);
    }

    public boolean estaRegular() {
        return this.estado.equals(AnalisisLaboratorio.ESTADO_REGULAR);
    }

    public void desvincularseDeLote() {
        this.ordenDeCompraImplicada = this.loteImplicado.getOrdenDeCompraAsociada();
        this.loteImplicado = null;
    }

    public boolean poseeLote() {
        return this.loteImplicado !=null;
    }

    public boolean esDeYerbaCanchadaVerde() {
        return this.criterioAsociado.getTipoLote().equals(CriterioAnalisisLaboratorio.TIPO_YCV);
    }

    
    
    
    

    
    


    

    

    
    
    
}
