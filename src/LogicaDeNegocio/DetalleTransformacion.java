/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.Lote;
import LogicaDeNegocio.OrdenDeProduccion;
import LogicaDeNegocio.Transformacion;
import java.util.ArrayList;
import java.util.Iterator;
    
/**
 *
 * @author usuario
 */
public class DetalleTransformacion {
    private String unidadMedidaTransporte;
    private int cantidadUtilizada;
    private String unidadMedidaPeso;
    private Float pesoUtilizdo;
    private Lote loteImplicado;
    private Transformacion transformacionAsociada;

    public DetalleTransformacion(String unidadMedidaTransporte, int cantidadUtilizada, String unidadMedidaPeso, Float pesoUtilizdo, Lote loteImplicado, Transformacion transformacionAsociada) {
        this.unidadMedidaTransporte = unidadMedidaTransporte;
        this.cantidadUtilizada = cantidadUtilizada;
        this.unidadMedidaPeso = unidadMedidaPeso;
        this.pesoUtilizdo = pesoUtilizdo;
        this.loteImplicado = loteImplicado;
        this.transformacionAsociada = transformacionAsociada;
    }
    
    public DetalleTransformacion(String unidadMedidaTransporte, int cantidadUtilizada, String unidadMedidaPeso, Float pesoUtilizdo, Lote loteImplicado) {
        this.unidadMedidaTransporte = unidadMedidaTransporte;
        this.cantidadUtilizada = cantidadUtilizada;
        this.unidadMedidaPeso = unidadMedidaPeso;
        this.pesoUtilizdo = pesoUtilizdo;
        this.loteImplicado = loteImplicado;
    }
    

    public boolean poseeOrdenDeProduccionAsociada(OrdenDeProduccion unaOrdenDeProduccion) {
        return this.loteImplicado.poseeOrdenDeProduccionAsociada(unaOrdenDeProduccion);
    }

    public boolean poseeLote(Lote unLoteAVerificar) {
        return this.loteImplicado.equals(unLoteAVerificar);
    }

    public Lote getLoteImplicado() {
        return loteImplicado;
    }

    public String getUnidadMedidaTransporte() {
        return unidadMedidaTransporte;
    }

    public int getCantidadUtilizada() {
        return cantidadUtilizada;
    }

    public String getUnidadMedidaPeso() {
        return unidadMedidaPeso;
    }

    public Float getPesoUtilizdo() {
        return pesoUtilizdo;
    }

    public Object[] devolverVector() {
        
        Object[] vec ={this, this.loteImplicado.getOrdenDeProduccionAsociada().getId(), this.loteImplicado.getOrdenDeCompraAsociada().getId(), this.cantidadUtilizada, this.getLoteImplicado().getCantidadUnidadesDisponibleParaMoler(), UtilidadesInterfazGrafica.formatearFlotante(this.pesoUtilizdo), UtilidadesInterfazGrafica.formatearFlotante(this.loteImplicado.getCantidadDisponibleParaMolerKg())};
        return vec;
    }

    @Override
    public String toString() {
        return this.loteImplicado.getEtiqueta();
    }
    
    public static ArrayList obtenerLotesImplicados(ArrayList detallesARevisar){
        ArrayList retorno = new ArrayList();
        Iterator detalles = detallesARevisar.iterator();
        while (detalles.hasNext()){
            DetalleTransformacion unDetalle = (DetalleTransformacion) detalles.next();
            Lote unLote = unDetalle.getLoteImplicado();
            retorno.add(unLote);
        }
        return retorno;
    }
    
    
}
