/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.Consultable;
import InterfazGrafica.Paneles.PanelGestionAnalisisLaboratorio;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author usuario
 */
public class AnalisisLaboratorio extends Evento implements Reporte, Filtrable, Consultable {
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    
    public static final String ATRIBUTO_PORCENTAJE_SEMILLA = "Porcentaje de semilla";
    public static final String ATRIBUTO_PORCENTAJE_PALO = "Porcentaje de palo";
    public static final String ATRIBUTO_PORCENTAJE_POLVO = "Porcentaje de polvo";
    public static final String ATRIBUTO_PORCENTAJE_HOJA = "Porcentaje de hoja";
    public static final String ATRIBUTO_PORCENTAJE_HUMEDAD = "Porcentaje de humedad";
    public static final String[] ATRIBUTOS_CUANTITATIVOS = {ATRIBUTO_PORCENTAJE_SEMILLA, ATRIBUTO_PORCENTAJE_PALO, ATRIBUTO_PORCENTAJE_POLVO, ATRIBUTO_PORCENTAJE_HOJA, ATRIBUTO_PORCENTAJE_HUMEDAD};
    
    public static final String ATRIBUTO_PUNTOS_NEGROS = "Puntos negros";
    public static final String[] SUB_ATRIBUTO_PUNTOS_NEGROS = {"SI", "NO", "ALGUNOS"};
    public static final String ATRIBUTO_TORRADA = "Torrada";
    public static final String[] SUB_ATRIBUTO_TORRADA = {"SI", "NO", "ALGUNOS"};
    public static final String ATRIBUTO_COLOR = "Color";
    public static final String[] SUB_ATRIBUTO_COLOR = {"VERDE OSCURO", "VERDE CLARO", "AMARILLENTO"};
    public static final String ATRIBUTO_AROMA = "Aroma";
    public static final String[] SUB_ATRIBUTO_AROMA = {"TIPICO", "OLOR LEVE A HUMO", "OLOR FUERTE A HUMO"};
    public static final String ATRIBUTO_TACTO = "Tacto";
    public static final String[] SUB_ATRIBUTO_TACTO = {"CRUJIENTE", "SUAVE", "ESPONJOSO"};
    public static final String ATRIBUTO_DEGUSTACION = "Degustación";
    public static final String[] SUB_ATRIBUTO_DEGUSTACION = {"SI", "NO"};
    
    public static final String[] ATRIBUTOS = {ATRIBUTO_PUNTOS_NEGROS, ATRIBUTO_TORRADA, ATRIBUTO_COLOR, ATRIBUTO_AROMA, ATRIBUTO_TACTO, ATRIBUTO_DEGUSTACION};
    public static final String[][] SUB_ATRIBUTOS = {SUB_ATRIBUTO_PUNTOS_NEGROS, SUB_ATRIBUTO_TORRADA, SUB_ATRIBUTO_COLOR, SUB_ATRIBUTO_AROMA, SUB_ATRIBUTO_TACTO, SUB_ATRIBUTO_DEGUSTACION};
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
        super(idEvento, estado, unUsuario, fechaOrigen, id);
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
        if (this.loteImplicado != null)
            return this.loteImplicado.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
        else
            return this.ordenDeCompraImplicada.poseeOrdenDeProduccionAsociada(unaOrdenProduccionSeleccionada);
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
    
    @Override
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
        return this.loteImplicado != null;
    }

    public boolean esDeYerbaCanchadaVerde() {
        return this.criterioAsociado.getTipoLote().equals(CriterioAnalisisLaboratorio.TIPO_YCV);
    }

    public boolean esDeYerbaCanchadaEstacionada() {
        return this.criterioAsociado.getTipoLote().equals(CriterioAnalisisLaboratorio.TIPO_YCE);
    }

    @Override
    public String getReporte() {
        String retorno = "";
            retorno = retorno + "ID: "+this.getId() +"\t\t Fecha: "+Organizacion.expresarCalendario(this.getFechaOrigenC())+"\tResultado: "+this.conclusion;
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Lote implicado: "+obtenerLoteImplicado()+ "\t\tCriterio utilizado: "+this.criterioAsociado.getNombre()+"";
            //        +"Cantidad restante a recibir: "+ UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(unidadDeMedida, getCantidadRestanteARecibir(), Lote.UNIDAD_MEDIDA_KILOGRAMO))+" Kgs"+"\t\tOrden de producción asociada: "+this.getOrdenDeProduccionAsociada().getId();
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "ID orden de producción: "+this.obtenerOrdenDeProduccion()+"\t\tID orden de Compra: "+this.obtenerOrdenDeCompra()
                    + "\t\tEstado: "+ estado;
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Laboratorio implicado: "+this.laboratorioAsociado.getNombre();
            return retorno;
    }

    private String obtenerLoteImplicado() {
        if (poseeLote())
            return this.loteImplicado.getEtiqueta();
        else
            return "No posee";
    }
    
    private String obtenerOrdenDeProduccion(){
        if (poseeLote())
            return ""+loteImplicado.getOrdenDeProduccionAsociada().getId();
        if (poseeOrdenDeCompra())
            return ""+ordenDeCompraImplicada.getOrdenDeProduccionAsociada().getId();
        return "No disponible";
    }
    
    private String obtenerOrdenDeCompra(){
        if (poseeLote())
            return ""+loteImplicado.getOrdenDeCompraAsociada().getId();
        if (poseeOrdenDeCompra())
            return ""+ordenDeCompraImplicada.getId();
        return "No disponible";
    }    

    public boolean poseeOrdenDeCompra() {
        return this.ordenDeCompraImplicada != null;
    }

    public boolean estaRechazado() {
        return this.conclusion.equals(CriterioAnalisisLaboratorio.ESTADO_RECHAZADO);
    }

    @Override
    public boolean cumpleCriterio(String unCriterio, Object unObjeto) {
        boolean cumpleCriterio = false;
        if (unObjeto == null)
            return cumpleCriterio;
        if (unObjeto instanceof Lote)
            return this.poseeLote((Lote) unObjeto);
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionAnalisisLaboratorio.criterios[1]))//Comentario
            return this.poseeComentario((String) unObjeto);
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionAnalisisLaboratorio.criterios[2]))//Tipo de lote
            return this.poseeTipoDeLote((String) unObjeto);
        if (unObjeto instanceof CriterioAnalisisLaboratorio)
            return this.poseeCriterio((CriterioAnalisisLaboratorio) unObjeto);
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionAnalisisLaboratorio.criterios[4]))//Estado
            return this.poseeEstado((String) unObjeto);
        if (unObjeto instanceof String && unCriterio.equals(PanelGestionAnalisisLaboratorio.criterios[10]))//posee conclusion
            return this.poseeConclusion((String) unObjeto);
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar)){
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaOrigenEstaEntre(fechaInferior, fechaSuperior);
        }
        if (unObjeto instanceof OrdenDeProduccion)
            return this.poseeOrdenDeProduccionImplicada((OrdenDeProduccion) unObjeto);
        if (unObjeto instanceof Laboratorio)
            return this.poseeLaboratorio((Laboratorio) unObjeto);
        if (unObjeto instanceof OrdenDeCompra)
            return this.poseeOrdenDeCompra((OrdenDeCompra) unObjeto);
        if (unObjeto instanceof Boolean && ((Boolean)unObjeto))
            return this.poseeLote();
        if (unObjeto instanceof Boolean && (!(Boolean)unObjeto))
            return !this.poseeLote();
            
        
        return cumpleCriterio;
    }

    public Float getValorUnAtributo(String unAtributo, String unSubAtributo) {
        Float retorno = 0f;
        switch (unAtributo){
            case ATRIBUTO_AROMA:
                if (aroma.toUpperCase().equals(unSubAtributo))
                    retorno = 1f;
                break;
            case ATRIBUTO_COLOR:
                if (color.toUpperCase().equals(unSubAtributo))
                    retorno = 1f;
                break;
            case ATRIBUTO_PUNTOS_NEGROS:
                if (puntosNegros.toUpperCase().equals(unSubAtributo))
                    retorno = 1f;
                break;
            case ATRIBUTO_TACTO:
                if (tacto.toUpperCase().equals(unSubAtributo))
                    retorno = 1f;
                break;
            case ATRIBUTO_TORRADA:
                if (torrada.toUpperCase().equals(unSubAtributo))
                    retorno = 1f;
                break;
            case ATRIBUTO_DEGUSTACION:
                if (degustacion.toUpperCase().equals(unSubAtributo))
                    retorno = 1f;
                break;
        }
        return retorno;
    }

    private boolean poseeConclusion(String unaConclusion) {
        return this.conclusion.equals(unaConclusion);
    }

    
    
}
