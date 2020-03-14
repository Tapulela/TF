/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes.reporteEstadisticas;

import net.sf.jasperreports.renderers.JCommonDrawableRendererImpl;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author usuario
 */
public class TuplaReporteEstadistica {
    JCommonDrawableRendererImpl grafico;
    String reporte;

    public TuplaReporteEstadistica(JFreeChart grafico, String reporte) {
        this.grafico = new JCommonDrawableRendererImpl(grafico);
        this.reporte = reporte;
    }

    public JCommonDrawableRendererImpl getGrafico() {
        return grafico;
    }

    public String getReporte() {
        return reporte;
    }
    
    
    
}
