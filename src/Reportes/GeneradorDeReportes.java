/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import InterfazGrafica.ParametrosDeInterfaz;
import LogicaDeNegocio.CamaraEstacionamiento;
import LogicaDeNegocio.ConfiguracionLogicaNegocio;
import LogicaDeNegocio.Deposito;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.GestionUsuariosYRoles.Usuario;
import LogicaDeNegocio.Molino;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Reporte;
import Reportes.reporteEstadisticas.TuplaReporteEstadistica;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author usuario
 */

public class GeneradorDeReportes {
    
    public static String RUTA_REPORTES = "C:\\Users\\usuario\\Documents\\Yerbasoft\\Reportes";

    public static void generarReporteEquipamiento(Map<String, Boolean> criteriosUtilizados, Map<String, String> criterios, ArrayList equipamientos, String unEmisor) throws JRException, ExcepcionCargaParametros{
        Boolean criterioNombre = criteriosUtilizados.get("Nombre");
        Boolean criterioTipo = criteriosUtilizados.get("Tipo");
        Boolean criterioDireccion = criteriosUtilizados.get("Direccion");
        Boolean criteriofechaAdquisicion = criteriosUtilizados.get("FechaAdquisicion");
        Boolean criteriofechaUltimoMantenimiento = criteriosUtilizados.get("FechaUltimoMantenimiento");
        Boolean criterioEstado = criteriosUtilizados.get("Estado");
        Boolean criterioBasculaAsociada = criteriosUtilizados.get("BasculaAsociada");
        
        if (equipamientos.isEmpty()){
            throw new ExcepcionCargaParametros("No se posee Equipamientos con esas características para generar un reporte");
        }
        JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("src\\Reportes\\ModeloReporteEquipamiento.jasper"));
                    
        Iterator recorredorEquipamientos = equipamientos.iterator();
        List<Equipamiento> lista = new ArrayList<Equipamiento>();
        while (recorredorEquipamientos.hasNext()){
            Equipamiento unEquipamiento = (Equipamiento) recorredorEquipamientos.next();
            lista.add(unEquipamiento);
        }
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("emisor", unEmisor);
        parametros.put("fechaEmision", Calendar.getInstance().getTime().toString());
        if (criterioNombre){
            parametros.put("criterioNombre", "Nombre: "+criterios.get("Nombre"));
        }else{
            parametros.put("criterioNombre", "");
        }
        if (criterioTipo){
            parametros.put("criterioTipoEquipamiento", "Tipo de Equipamiento: "+criterios.get("Tipo"));
        }else{
            parametros.put("criterioTipoEquipamiento", "");
        }
        if (criterioDireccion){
            parametros.put("criterioDireccion", "Direccion: "+criterios.get("Direccion"));
        }else{
            parametros.put("criterioDireccion", "");
        }
        if (criteriofechaAdquisicion){
            parametros.put("criterioFechaAdquisicion", "Fecha de adquisicion: "+criterios.get("FechaAdquisicion"));
        }else{
            parametros.put("criterioFechaAdquisicion", "");
        }        
        if (criteriofechaUltimoMantenimiento){
            parametros.put("criterioFechaUltimoMantenimiento", "Fecha de ultimo mantenimiento: "+criterios.get("FechaUltimoMantenimiento"));
        }else{
            parametros.put("criterioFechaUltimoMantenimiento", "");
        }        
        if (criterioEstado){
            parametros.put("criterioEstado", "Estado: "+criterios.get("Estado"));
        }else{
            parametros.put("criterioEstado", "");
        }
        if (criterioBasculaAsociada){
            parametros.put("criterioBasculaAsociada", "Bascula asociada: "+criterios.get("BasculaAsociada"));
        }else{
            parametros.put("criterioBasculaAsociada", "");
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
        Exporter exporter  = new JRPdfExporter();
        
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("Equipamientos.pdf"));
        exporter.exportReport();
        
        
    }
    
    public static void generarReporteEquipamientov2(Map<String, Boolean> criteriosUtilizados, Map<String, String> criterios, ArrayList equipamientos, String unEmisor) throws JRException, ExcepcionCargaParametros{
        Boolean criterioNombre = criteriosUtilizados.get("Nombre");
        Boolean criterioTipo = criteriosUtilizados.get("Tipo");
        Boolean criterioDireccion = criteriosUtilizados.get("Direccion");
        Boolean criteriofechaAdquisicion = criteriosUtilizados.get("FechaAdquisicion");
        Boolean criteriofechaUltimoMantenimiento = criteriosUtilizados.get("FechaUltimoMantenimiento");
        Boolean criterioEstado = criteriosUtilizados.get("Estado");
        Boolean criterioBasculaAsociada = criteriosUtilizados.get("BasculaAsociada");
        String criteriosDeFiltracion = "";
        if (equipamientos.isEmpty()){
            throw new ExcepcionCargaParametros("No se posee Equipamientos con esas características para generar un reporte");
        }
        JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("src\\Reportes\\ModeloReporteGenerico.jasper"));
        String tituloReporte = "Equipamientos";
        Iterator recorredorEquipamientos = equipamientos.iterator();
        List<Equipamiento> lista = new ArrayList<Equipamiento>();
        while (recorredorEquipamientos.hasNext()){
            Equipamiento unEquipamiento = (Equipamiento) recorredorEquipamientos.next();
            lista.add(unEquipamiento);
        }
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("ReporteDe", tituloReporte);
        String cabecera = GeneradorDeReportes.armarCabecera(unEmisor);
        parametros.put("Cabecera", cabecera);
        if (criterioNombre)
            criteriosDeFiltracion = criteriosDeFiltracion + "Nombre: "+ criterios.get("Nombre") +"\n";
        else
            criteriosDeFiltracion = criteriosDeFiltracion + "Nombre: Todos\n";
        if (criterioTipo)
            criteriosDeFiltracion = criteriosDeFiltracion + "Tipo de equipamiento: "+ criterios.get("Tipo") +"\n";
        else
            criteriosDeFiltracion = criteriosDeFiltracion + "Tipo: Todos\n";
        if (criterioDireccion)
            criteriosDeFiltracion = criteriosDeFiltracion + "Direccion: "+ criterios.get("Direccion") +"\n";
        else
            criteriosDeFiltracion = criteriosDeFiltracion + "Dirección: Todas\n";
        if (criteriofechaAdquisicion)
            criteriosDeFiltracion = criteriosDeFiltracion + "Fecha de adquisición: "+ criterios.get("FechaAdquisicion") +"\n";
        else
            criteriosDeFiltracion = criteriosDeFiltracion + "Fecha de adquisición: Todas\n";
        if (criteriofechaUltimoMantenimiento)
            criteriosDeFiltracion = criteriosDeFiltracion + "Fecha de ultimo mantenimiento: "+ criterios.get("FechaUltimoMantenimiento") +"\n";
        else
            criteriosDeFiltracion = criteriosDeFiltracion + "Fecha de ultimo mantenimiento: Todas\n";
        if (criterioEstado)
            criteriosDeFiltracion = criteriosDeFiltracion + "Estado: "+ criterios.get("Estado") +"\n";
        else
            criteriosDeFiltracion = criteriosDeFiltracion + "Estado: Todos\n";
        if (criterioBasculaAsociada)
            criteriosDeFiltracion = criteriosDeFiltracion + "Bascula asociada: "+ criterios.get("BasculaAsociada");
        else
            criteriosDeFiltracion = criteriosDeFiltracion + "Bascula asociada: Indiferente\n";
        
        parametros.put("criterios", criteriosDeFiltracion);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
        Exporter exporter  = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteEquipamiento-"+Organizacion.expresarFechaArchivo()+".pdf"));
        exporter.exportReport();
        
    }

    public static String armarCabecera(String unEmisor) {
        String retorno = "";
        retorno = retorno + "\t\t\tFecha de emision: "+Organizacion.expresarCalendario(Calendar.getInstance())+"\n";
        retorno = retorno + "\t\t\tEmisor: "+unEmisor;
        return retorno;
    }
    
    
    public static void generarReporteGenerico(String unTituloReporte,Map<String, Boolean> criteriosUtilizados, Map<String, String> criterios, ArrayList tuplas, String unEmisor) throws JRException, ExcepcionCargaParametros{
        
        String criteriosDeFiltracion = "";
        Iterator recorredorDeCriteriosUtilizados = criteriosUtilizados.keySet().iterator();
        while (recorredorDeCriteriosUtilizados.hasNext()){
            String unaClave = (String)recorredorDeCriteriosUtilizados.next();
            Boolean unCriterio = criteriosUtilizados.get(unaClave);
            if (unCriterio)
                criteriosDeFiltracion = criteriosDeFiltracion + unaClave+": "+ criterios.get(unaClave) +"\n";
            else
                criteriosDeFiltracion = criteriosDeFiltracion + unaClave+": indistinto\n";
        }
        JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("src\\Reportes\\ModeloReporteGenerico.jasper"));
        String tituloReporte = unTituloReporte;
        Iterator recorredorDeTuplas = tuplas.iterator();
        List<Reporte> lista = new ArrayList<Reporte>();
        while (recorredorDeTuplas.hasNext()){
            Reporte unaTupla = (Reporte) recorredorDeTuplas.next();
            lista.add(unaTupla);
        }
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("ReporteDe", tituloReporte);
        String cabecera = GeneradorDeReportes.armarCabecera(unEmisor);
        parametros.put("Cabecera", cabecera);
        
        String rutaLogo = ParametrosDeInterfaz.rutaLogo;
        parametros.put("reportLogo", rutaLogo);
        parametros.put("NombreEmpresa", ConfiguracionLogicaNegocio.NOMBRE_EMPRESA);
        parametros.put("NombreSoftware", ConfiguracionLogicaNegocio.NOMBRE_SOFTWARE+" - Sistema de gestión de producción de yerba");
        parametros.put("logoEmpresa", ConfiguracionLogicaNegocio.RUTA_LOGO_EMPRESA);
        parametros.put("Cabecera", cabecera);
        
        parametros.put("criterios", criteriosDeFiltracion);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
        Exporter exporter  = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File archivoSalida = new File(RUTA_REPORTES, "Reporte "+unTituloReporte+"-"+Organizacion.expresarFechaArchivo()+".pdf");
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(archivoSalida));
        //exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("Reporte "+unTituloReporte+"-"+Organizacion.expresarFechaArchivo()+".pdf"));
        exporter.exportReport();
    }
    
    public static void generarReporteGenericoEstadistica(String unTituloReporte,Map<String, Boolean> criteriosUtilizados, Map<String, String> criterios, ArrayList tuplas, String unEmisor) throws JRException, ExcepcionCargaParametros{
        String criteriosDeFiltracion = "";
        Iterator recorredorDeCriteriosUtilizados = criteriosUtilizados.keySet().iterator();
        while (recorredorDeCriteriosUtilizados.hasNext()){
            String unaClave = (String)recorredorDeCriteriosUtilizados.next();
            Boolean unCriterio = criteriosUtilizados.get(unaClave);
            if (unCriterio)
                criteriosDeFiltracion = criteriosDeFiltracion + unaClave+": "+ criterios.get(unaClave) +"\n";
            else
                criteriosDeFiltracion = criteriosDeFiltracion + unaClave+": indistinto\n";
        }
        JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("src\\Reportes\\reporteEstadisticas\\ModeloReporteGenericoEstadistica.jasper"));
        String tituloReporte = unTituloReporte;
        Iterator recorredorDeTuplas = tuplas.iterator();
        List<TuplaReporteEstadistica> lista = new ArrayList<TuplaReporteEstadistica>();
        while (recorredorDeTuplas.hasNext()){
            TuplaReporteEstadistica unaTupla = (TuplaReporteEstadistica) recorredorDeTuplas.next();
            lista.add(unaTupla);
        }
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("ReporteDe", tituloReporte);
        String cabecera = GeneradorDeReportes.armarCabecera(unEmisor);
        
        
        String rutaLogo = ParametrosDeInterfaz.rutaLogo;
        parametros.put("reportLogo", rutaLogo);
        parametros.put("Cabecera", cabecera);
        
        parametros.put("NombreEmpresa", ConfiguracionLogicaNegocio.NOMBRE_EMPRESA);
        parametros.put("logoEmpresa", ConfiguracionLogicaNegocio.RUTA_LOGO_EMPRESA);
        parametros.put("NombreSoftware", ConfiguracionLogicaNegocio.NOMBRE_SOFTWARE+" - Sistema de gestión de producción de yerba");
        parametros.put("criterios", criteriosDeFiltracion);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
        Exporter exporter  = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File archivoSalida = new File(RUTA_REPORTES, "Reporte estadístico de "+unTituloReporte+"-"+Organizacion.expresarFechaArchivo()+".pdf");
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(archivoSalida));
        //exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("Reporte "+unTituloReporte+"-"+Organizacion.expresarFechaArchivo()+".pdf"));
        exporter.exportReport();
    }
    
    public static void generarReporteINYMv3(Calendar unaFecha, Map<Integer, Equipamiento> equipamientos, String unResumen, Usuario unUsuario) throws JRException, ExcepcionCargaParametros{
        if (unaFecha == null){
            throw new ExcepcionCargaParametros("Debe seleccionar una fecha para generar el reporte de INYM");
        }
        Equipamiento.FECHA_REPORTE_INYM = unaFecha;
        ArrayList datos = new ArrayList();
        ArrayList listaFiltradaCamaras = new ArrayList();
        ArrayList listaFiltradaDepositos = new ArrayList();
        ArrayList listaFiltradaMolinos = new ArrayList();
        Iterator recorredorDeEquipamientos = equipamientos.keySet().iterator();
        while (recorredorDeEquipamientos.hasNext()){
            int unId = (int) recorredorDeEquipamientos.next();
            Equipamiento unEquipamiento = equipamientos.get(unId);
            if (unEquipamiento instanceof CamaraEstacionamiento)
                listaFiltradaCamaras.add(unEquipamiento);
            if (unEquipamiento instanceof Deposito)
                listaFiltradaDepositos.add(unEquipamiento);
            if (unEquipamiento instanceof Molino)
                listaFiltradaMolinos.add(unEquipamiento);
        }
        //Crear tupla (sub reporte) de Depositos
        
        datos.add(new TuplaReporteInym("Cámaras de estacionamiento", listaFiltradaCamaras));
        datos.add(new TuplaReporteInym("Depósitos", listaFiltradaDepositos));
        datos.add(new TuplaReporteInym("Molinos", listaFiltradaMolinos));
        
        Map<String, Object> parametros = new HashMap<String, Object>();
        //TOTALES
        //String unResumen = this.resumirExistenciaV3();
        System.out.println(unResumen);
        parametros.put("ReporteDe", "existencia para INYM "+Organizacion.expresarCalendario(unaFecha));
        parametros.put("unResumen", unResumen);
        String rutaLogo = ParametrosDeInterfaz.rutaLogo;
        parametros.put("reportLogo", rutaLogo);
        parametros.put("Cabecera", GeneradorDeReportes.armarCabecera(unUsuario.getApellido()+", "+unUsuario.getNombre()));
        parametros.put("NombreEmpresa", ConfiguracionLogicaNegocio.NOMBRE_EMPRESA);
        parametros.put("logoEmpresa", ConfiguracionLogicaNegocio.RUTA_LOGO_EMPRESA);
        parametros.put("NombreSoftware", ConfiguracionLogicaNegocio.NOMBRE_SOFTWARE+" - Sistema de gestión de producción de yerba");
        
        JasperReport reporte = GeneradorDeReportes.generarReporteINYM();
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(datos));
        Exporter exporter  = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File archivoSalida = new File(RUTA_REPORTES, "ReporteINYMv3-"+Organizacion.expresarFechaArchivo(unaFecha)+".pdf");
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(archivoSalida));
        //exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteINYMv3-"+Organizacion.expresarFechaArchivo(unaFecha)+".pdf"));
        exporter.exportReport();
    }
    
    public static JasperReport generarSubReporteINYM() throws JRException{
        JasperReport subreport = (JasperReport)JRLoader.loadObjectFromFile("src\\Reportes\\ModeloSubReporteINYM.jasper");
        return subreport;
    }
    
    public static JasperReport generarReporteINYM() throws JRException{
        JasperReport subreport = (JasperReport)JRLoader.loadObjectFromFile("src\\Reportes\\ModeloReporteINYM.jasper");
        return subreport;
    }
    
    
}
