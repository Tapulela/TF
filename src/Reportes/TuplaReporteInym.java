/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.Reporte;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author usuario
 */
public class TuplaReporteInym implements Reporte{
    private net.sf.jasperreports.engine.data.JRBeanCollectionDataSource subDatos;
    private net.sf.jasperreports.engine.JasperReport subReporte;
    private String subCabecera;
    
    

    public TuplaReporteInym(String unaSubCabecera, ArrayList unaSubLista) throws JRException {
        this.subCabecera = unaSubCabecera;
        this.subReporte = GeneradorDeReportes.generarSubReporteINYM();
        Iterator recorredorDeTuplas = unaSubLista.iterator();
        List<Equipamiento> lista = new ArrayList<Equipamiento>();
        while (recorredorDeTuplas.hasNext()){
            Equipamiento unaTupla = (Equipamiento) recorredorDeTuplas.next();
            lista.add(unaTupla);
        }
        this.subDatos = new JRBeanCollectionDataSource(unaSubLista);
    }
    
    public JRBeanCollectionDataSource getSubDatos() {
        return subDatos;
    }

    public String getSubCabecera() {
        return subCabecera;
    }

    @Override
    public String getReporte() {
        return "INYM";
    }

    public JasperReport getSubReporte() {
        return subReporte;
    }
    
    
    
}
