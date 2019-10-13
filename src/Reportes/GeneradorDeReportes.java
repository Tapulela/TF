/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.ExcepcionCargaParametros;
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

/**
 *
 * @author usuario
 */
public class GeneradorDeReportes {
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
}
