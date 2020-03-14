/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import LogicaDeNegocio.GestionUsuariosYRoles.ConfiguracionDeLogicaDeNegocios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author usuario
 */
public class Proveedor {
    private int id;
    private String razonSocial;
    private String cuit;
    private String estado;
    private Localidad localidad;
    
    private ArrayList ordenesDeCompraAsociadas;
    private ArrayList analisisLaboratorio;
    private ArrayList analisisLaboratorioRechazados;
    
    public Proveedor(int id, String razonSocial, String cuit, String estado, Localidad localidad) { //Constructor para la base de datos
        this.id = id;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.estado = estado;
        this.localidad = localidad;
        
        this.ordenesDeCompraAsociadas = new ArrayList();
        this.analisisLaboratorio = new ArrayList();
        this.analisisLaboratorioRechazados = new ArrayList();
    }

    public Proveedor(String razonSocial, String cuit, Localidad localidad) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.estado = "Activo";
        this.localidad = localidad;
        
        this.ordenesDeCompraAsociadas = new ArrayList();
        this.analisisLaboratorio = new ArrayList();
        this.analisisLaboratorioRechazados = new ArrayList();
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public ArrayList getAnalisisLaboratorio() {
        return analisisLaboratorio;
    }

    public ArrayList getAnalisisLaboratorioRechazados() {
        return analisisLaboratorioRechazados;
    }

    public ArrayList getOrdenesDeCompraAsociadas() {
        return ordenesDeCompraAsociadas;
    }

    
    

    public int getId() {
        return id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void agregarOrdenDeCompra(OrdenDeCompra unaOrdenDeCompra){
        this.ordenesDeCompraAsociadas.add(unaOrdenDeCompra);
    }
    
    public boolean seEncuentraActivo(){
        return this.estado.equals("Activo");
    }

    public boolean poseeCuit(String unCuit) {
        return this.cuit.equals(unCuit);
    }

    public boolean poseeRazonSocial(String unaRazonSocial) {
        return this.razonSocial.toUpperCase().equals(unaRazonSocial.toUpperCase());
    }
    
    public void agregarAnalisis(AnalisisLaboratorio unAnalisis){
        this.analisisLaboratorio.add(unAnalisis);
        if (unAnalisis.estaRechazado())
            this.analisisLaboratorioRechazados.add(unAnalisis);
    }
    
    

    public Object[] devolverVector() {
        Object[] vec ={this.getId(),this.getRazonSocial(), this.getCuit(), this.getEstado(), this.localidad.getNombre(), this.obtenerCalidad()};
        return vec;
    }
    
    public String obtenerCalidad(){
        String retorno = "Desconocida";
        int cantidadTotalAnalisis = 0;
        int cantidadTotalAprobados = 0;
        Iterator recorredorDeAnalis = this.analisisLaboratorio.iterator();
        while (recorredorDeAnalis.hasNext()){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) recorredorDeAnalis.next();
            if (unAnalisis.poseeOrdenDeCompra() && unAnalisis.getOrdenDeCompraImplicada().poseeProveedor(this) && unAnalisis.estaRegular()){
                cantidadTotalAnalisis++;
                if (unAnalisis.estaAprobado())
                    cantidadTotalAprobados++;
            }
        }
        if (cantidadTotalAnalisis < ConfiguracionDeLogicaDeNegocios.cantidadMinimaDeAnalisis)
            return retorno;
        
        Float tasaAprobados = (float) cantidadTotalAprobados / (float) cantidadTotalAnalisis;
        retorno = "Muy baja";
        if (tasaAprobados >= ConfiguracionDeLogicaDeNegocios.limiteSuperiorCalidadBaja)
            retorno = "Baja";
        if (tasaAprobados >= ConfiguracionDeLogicaDeNegocios.limiteSuperiorCalidadMedia)
            retorno = "Media";
        if (tasaAprobados >= ConfiguracionDeLogicaDeNegocios.limiteSuperiorCalidadRegular)
            retorno = "Regular";
        if (tasaAprobados >= ConfiguracionDeLogicaDeNegocios.limiteSuperiorCalidadBuena)
            retorno = "Buena";
        if (tasaAprobados >= ConfiguracionDeLogicaDeNegocios.limiteSuperiorCalidadExcelente)
            retorno = "Excelente";
        
        return retorno;
    }
    
    public HashMap<String, Integer> calcularAnalisisAprobadosYRechazados(){
        HashMap<String, Integer> retorno = new HashMap<String, Integer>();
        Iterator analisis = this.analisisLaboratorio.iterator();
        int cantidadAnalisisAprobados = 0;
        int cantidadAnalisisRechazados = 0;
        while (analisis.hasNext()){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) analisis.next();
            if (unAnalisis.estaRegular()){
                if (unAnalisis.estaAprobado())
                    cantidadAnalisisAprobados++;
                else
                    cantidadAnalisisRechazados++;
            }
        }
        retorno.put("Aprobados", cantidadAnalisisAprobados);
        retorno.put("Rechazados", cantidadAnalisisRechazados);
        return retorno;
    }
}
