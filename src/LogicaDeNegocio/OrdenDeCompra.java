/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.Consultable;
import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class OrdenDeCompra extends Evento implements Reporte, Filtrable, Consultable {
    public static final String ESTADO_REGULAR = "Regular";
    public static final String ESTADO_ANULADO = "Anulado";
    private float cantidadAComprar;
    private String unidadDeMedida;
    private float costoPorUnidad;
    private String estado;
    private String tipoLote;
    
    private ArrayList lotesAsociados;
    private Proveedor proveedorAsociado;
    private OrdenDeProduccion ordenDeProduccionAsociada;
    private ArrayList analisisRealizados;

    public OrdenDeCompra(int id, Usuario unUsuario, java.sql.Date fechaOrigen, float cantidadAComprar, String unidadDeMedida, float costoPorUnidad, String estado, Proveedor proveedorAsociado, OrdenDeProduccion unaOrdenDeProduccion, int idEvento, String unTipoLote) {
        super(idEvento, estado, unUsuario, fechaOrigen, id);
        this.cantidadAComprar = cantidadAComprar;
        this.unidadDeMedida = unidadDeMedida;
        this.costoPorUnidad = costoPorUnidad;
        this.estado = estado;
        this.proveedorAsociado = proveedorAsociado;
        this.ordenDeProduccionAsociada = unaOrdenDeProduccion;
        this.lotesAsociados = new ArrayList();
        this.analisisRealizados = new ArrayList();
        this.tipoLote = unTipoLote;
    }
    
    public OrdenDeCompra(Usuario unUsuario, float cantidadComprada, String unidadDeMedida, float costoPorUnidad, Proveedor proveedorAsociado, OrdenDeProduccion ordenDeProduccionAsociada, String unTipoLote) {
        super(Evento.ESTADO_REGULAR, unUsuario);
        this.cantidadAComprar = cantidadComprada;
        this.unidadDeMedida = unidadDeMedida;
        this.costoPorUnidad = costoPorUnidad;
        this.proveedorAsociado = proveedorAsociado;
        this.estado = ESTADO_REGULAR;
        this.ordenDeProduccionAsociada = ordenDeProduccionAsociada;
        this.lotesAsociados = new ArrayList();
        this.analisisRealizados = new ArrayList();
        this.tipoLote = unTipoLote;

    }

    
    


    public float getCantidadAComprar() {
        return cantidadAComprar;
    }

    public void setCantidadAComprar(float cantidadAComprar) {
        this.cantidadAComprar = cantidadAComprar;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public float getCostoPorUnidad() {
        return costoPorUnidad;
    }

    public void setCostoPorUnidad(float costoPorUnidad) {
        this.costoPorUnidad = costoPorUnidad;
    }

    public String getEstadoEvento() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList getLotesAsociados() {
        return lotesAsociados;
    }

    public void setLotesAsociados(ArrayList lotesAsociados) {
        this.lotesAsociados = lotesAsociados;
    }

    public Proveedor getProveedorAsociado() {
        return proveedorAsociado;
    }

    public void setProveedorAsociado(Proveedor proveedorAsociado) {
        this.proveedorAsociado = proveedorAsociado;
    }

    public OrdenDeProduccion getOrdenDeProduccionAsociada() {
        return ordenDeProduccionAsociada;
    }

    public void setOrdenDeProduccionAsociada(OrdenDeProduccion ordenDeProduccionAsociada) {
        this.ordenDeProduccionAsociada = ordenDeProduccionAsociada;
    }

    public void agregarLote(Lote unLote) {
        this.lotesAsociados.add(unLote);
    }

    public boolean poseeProveedorAsociado() {
        return (this.proveedorAsociado!=null);
    }
    public boolean poseeLotesAsociados(){
        return (!this.lotesAsociados.isEmpty());
    }
    public boolean poseeLotesRegularesAsociados(){
        boolean retorno = false;
        Iterator recorredorLotes = this.lotesAsociados.iterator();
        while (!retorno && recorredorLotes.hasNext()){
            Lote unLote = (Lote) recorredorLotes.next();
            retorno = !unLote.estaAnulado();
        }
        return retorno;
    }
    
    
    public boolean seEncuentraRegular(){
        return this.estado.equals(ESTADO_REGULAR);
    }
    
    public boolean seEncuentraAnulada() {
        return this.estado.equals(ESTADO_ANULADO);
    }
    
    public void anular(){
        super.anularEsteEvento();
        this.estado = ESTADO_ANULADO;
    }


    public Boolean poseeOrdenDeProduccionAsociada(OrdenDeProduccion unaOrdenDeProduccion) {
        return this.ordenDeProduccionAsociada==unaOrdenDeProduccion;
    }

    
    @Override
    public Object[] devolverVector() {
        String unProveedor = "No posee";
        String fechaOrigen = Organizacion.expresarCalendario(getFechaOrigenC());
        if (this.proveedorAsociado != null){
            unProveedor = this.proveedorAsociado.getRazonSocial();
        }
        Object[] vec ={this.getId(), fechaOrigen, UtilidadesInterfazGrafica.formatearFlotante(this.getCantidadAComprar()), this.getUnidadDeMedida(), UtilidadesInterfazGrafica.formatearFlotante(getCostoPorUnidad()),this.getEstadoEvento(), this.getOrdenDeProduccionAsociada().getId(), unProveedor};
        return vec;
    }
    
    private ArrayList getLotesAsociadosNoAnulados() {
        ArrayList retorno = new ArrayList();
        Iterator lotes = this.lotesAsociados.iterator();
        while (lotes.hasNext()){
            Lote unLote = (Lote) lotes.next();
            if (!unLote.estaAnulado())
                retorno.add(unLote);
        }
        return retorno;
    }

    float getCantidadComprada(String unaUnidadMedida)  {
        float retorno = 0;
        Iterator lotesNoAnulados = getLotesAsociadosNoAnulados().iterator();
        while (lotesNoAnulados.hasNext()){
            Lote unLote = (Lote) lotesNoAnulados.next();
            retorno = retorno + Organizacion.convertirUnidadPeso(unLote.getUnidadDeMedida(), unLote.getCantidadTotalPesoIngresado(), unaUnidadMedida);
        }
        return retorno;
    }

    public float getCantidadRestanteARecibir() {
        float retorno = Math.max(this.cantidadAComprar - this.getCantidadComprada(this.unidadDeMedida),0);
        
        return retorno;
    }
    
    public void agregarAnalisisDeLaboratorio(AnalisisLaboratorio unAnalisis){
        this.analisisRealizados.add(unAnalisis);
    }

    public void removerAnalisisDeLaboratorio(AnalisisLaboratorio unAnalisis) {
        this.analisisRealizados.remove(unAnalisis);
    }

    public boolean poseeProveedor(Proveedor unProveedorSeleccionado) {
        return this.proveedorAsociado == unProveedorSeleccionado;
    }

    @Override
    public String getReporte() {
        String retorno = "";
            retorno = retorno + "ID: "+this.getId() +"\t\t Fecha: "+Organizacion.expresarCalendario(this.getFechaOrigenC())+"\t\tCantidad: "+this.cantidadAComprar+" "+this.unidadDeMedida;
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "Proveedor asociado: "+obtenerProveedorAsociado()+ "\t\tCantidad Adquirida: "+UtilidadesInterfazGrafica.formatearFlotante(getCantidadComprada(Lote.UNIDAD_MEDIDA_KILOGRAMO))+" Kgs de "+UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(unidadDeMedida, cantidadAComprar, Lote.UNIDAD_MEDIDA_KILOGRAMO))+" Kgs\n\n"
                    +"Cantidad restante a recibir: "+ UtilidadesInterfazGrafica.formatearFlotante(Organizacion.convertirUnidadPeso(unidadDeMedida, getCantidadRestanteARecibir(), Lote.UNIDAD_MEDIDA_KILOGRAMO))+" Kgs"+"\t\tOrden de producción asociada: "+this.getOrdenDeProduccionAsociada().getId();
            retorno = retorno + "\n";
            retorno = retorno + "\n";
            retorno = retorno + "ID orden de producción: "+ordenDeProduccionAsociada.getId()+""
                    + "\t\t\t\tEstado: "+ estado +" \n\n";
            return retorno;
    }

    private String obtenerProveedorAsociado() {
        String retorno;
        if (poseeProveedorAsociado())
            retorno = this.proveedorAsociado.getRazonSocial();
        else
            retorno = "No posee";
        return retorno;
        
    }

    @Override
    public boolean cumpleCriterio(String unCriterio, Object unObjeto) {
        boolean cumpleCriterio = false;
        if (unObjeto == null)
            return cumpleCriterio;
        if (unObjeto instanceof ArrayList && !((ArrayList)unObjeto).isEmpty() && (((ArrayList)unObjeto).get(0)instanceof Calendar)){
            ArrayList unaLista = (ArrayList) unObjeto;
            Calendar fechaInferior = (Calendar) unaLista.get(0);
            Calendar fechaSuperior = (Calendar) unaLista.get(1);
            return this.fechaOrigenEstaEntre(fechaInferior, fechaSuperior);
        }
        if (unObjeto instanceof OrdenDeProduccion){
            OrdenDeProduccion unaOrdenProduccion = (OrdenDeProduccion) unObjeto;
            return unaOrdenProduccion.getId() == this.ordenDeProduccionAsociada.getId();
        }
        if (unObjeto instanceof Proveedor){
            Proveedor unProveedor = (Proveedor) unObjeto;
            return this.poseeProveedor(unProveedor);
        }
            
        return cumpleCriterio;
    }

    public Boolean poseeEstado(String unEstado) {
        return this.estado.equals(unEstado);
    }

    public String getTipoLote() {
        return tipoLote;
    }

    public boolean poseeTipoLote(String tipoLote) {
        return this.tipoLote.equals(tipoLote);
    }
    
    
}
