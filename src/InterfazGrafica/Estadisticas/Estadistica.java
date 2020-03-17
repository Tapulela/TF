/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Estadisticas;

import InterfazGrafica.ParametrosDeInterfaz;
import InterfazGrafica.UtilidadesInterfazGrafica;
import LogicaDeNegocio.AnalisisLaboratorio;
import LogicaDeNegocio.Equipamiento;
import LogicaDeNegocio.ExcepcionCargaParametros;
import LogicaDeNegocio.Lote;
import LogicaDeNegocio.OrdenDeCompra;
import LogicaDeNegocio.Organizacion;
import LogicaDeNegocio.Proveedor;
import Reportes.reporteEstadisticas.TuplaReporteEstadistica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.GradientBarPainter;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author usuario
 */
public class Estadistica {
    private static Organizacion unaOrganizacion;

    

    

    public Estadistica() {
    }
    
    public static HashMap<String, Integer> calcularProveedoresMasUtilizadosGenerales(){
        HashMap<String, Integer> retorno = new HashMap<String, Integer>();
        
        Set claves = unaOrganizacion.getProveedores().keySet();
        Iterator recorredorDeClaves = claves.iterator();
        while (recorredorDeClaves.hasNext()){
            int unaClave = (int) recorredorDeClaves.next();
            Proveedor unProveedor = (Proveedor) unaOrganizacion.getProveedores().get(unaClave);
            if (unProveedor.seEncuentraActivo()){
                int cantidadOrdenesCompraAsignadas = 0;
                Iterator recorredorOrdenesDeCompra = unProveedor.getOrdenesDeCompraAsociadas().iterator();
                while (recorredorOrdenesDeCompra.hasNext()){
                    OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) recorredorOrdenesDeCompra.next();
                    if (unaOrdenDeCompra.seEncuentraRegular()){
                        cantidadOrdenesCompraAsignadas++;
                    }
                }
                if (cantidadOrdenesCompraAsignadas > 0){
                    retorno.put(unProveedor.getRazonSocial(), cantidadOrdenesCompraAsignadas);
                }
            }
        }
        return retorno;
    }
    
    public static HashMap<String, Integer> calcularProveedoresFiltrados(ArrayList ordenesDeCompra){
        
        HashMap<String, Integer> retorno = new HashMap<String, Integer>();
        
        Set claves = unaOrganizacion.getProveedores().keySet();
        Iterator recorredorDeClaves = claves.iterator();
        while (recorredorDeClaves.hasNext()){
            int unaClave = (int) recorredorDeClaves.next();
            Proveedor unProveedor = (Proveedor) unaOrganizacion.getProveedores().get(unaClave);
            System.out.println("Proveedor "+unProveedor.getRazonSocial());
            if (unProveedor.seEncuentraActivo()){
                int cantidadOrdenesCompraAsignadas = 0;
                Iterator recorredorOrdenesDeCompra = unProveedor.getOrdenesDeCompraAsociadas().iterator();
                while (recorredorOrdenesDeCompra.hasNext()){
                    OrdenDeCompra unaOrdenDeCompra = (OrdenDeCompra) recorredorOrdenesDeCompra.next();
                    if (unaOrdenDeCompra.seEncuentraRegular() && ordenesDeCompra.contains(unaOrdenDeCompra)){
                        cantidadOrdenesCompraAsignadas++;
                    }
                }
                if (cantidadOrdenesCompraAsignadas > 0){
                    retorno.put(unProveedor.getRazonSocial(), cantidadOrdenesCompraAsignadas);
                }
            }
        }
        return retorno;
        
    }
    
    public static void setOrganizacion(Organizacion unaOrganizacion){
        Estadistica.unaOrganizacion = unaOrganizacion;
    }
    
    public static JFreeChart obtenerGraficoTorta(Map<String, Integer> partes, String titulo){
        JFreeChart retorno;
        
        DefaultPieDataset datos = new DefaultPieDataset();
        Set<String> llaves = (Set<String>) partes.keySet();
        Iterator recorredorDeLlaves = llaves.iterator();
        while (recorredorDeLlaves.hasNext()){
            String unaClave = (String) recorredorDeLlaves.next();
            int unaCantidad = partes.get(unaClave);
            datos.setValue(unaClave+": "+unaCantidad, unaCantidad);
        }
        retorno = ChartFactory.createPieChart(titulo, datos, true, true, true);
        
        return retorno;
        
    }
    
    public static JFreeChart obtenerGraficoTortaFlotante(Map<String, Float> partes, String titulo, String unaUnidadMedida){
        JFreeChart retorno;
        
        DefaultPieDataset datos = new DefaultPieDataset();
        Set<String> llaves = (Set<String>) partes.keySet();
        Iterator recorredorDeLlaves = llaves.iterator();
        while (recorredorDeLlaves.hasNext()){
            String unaClave = (String) recorredorDeLlaves.next();
            Float unaCantidad = partes.get(unaClave);
            datos.setValue(unaClave+": "+UtilidadesInterfazGrafica.formatearFlotante(unaCantidad)+" "+ unaUnidadMedida, unaCantidad);
        }
        retorno = ChartFactory.createPieChart(titulo, datos, true, true, true);
        
        retorno.getTitle().setFont(ParametrosDeInterfaz.fuentePorDefecto);
        LegendTitle legend = retorno.getLegend();
        legend.setItemFont(ParametrosDeInterfaz.fuentePorDefecto);
        
        ((PiePlot) retorno.getPlot()).setLabelFont(ParametrosDeInterfaz.fuentePorDefecto);
        return retorno;
        
    }
    
    public static ChartPanel generarPanelGraficoTorta(Map<String, Integer> partes, String unTitulo, float unEscalar){
        JFreeChart unGraficoTorta = Estadistica.obtenerGraficoTorta(partes, unTitulo);
        
        unGraficoTorta.getTitle().setFont(ParametrosDeInterfaz.fuentePorDefecto);
        LegendTitle legend = unGraficoTorta.getLegend();
        legend.setItemFont(ParametrosDeInterfaz.fuentePorDefecto);
        
        ((PiePlot) unGraficoTorta.getPlot()).setLabelFont(ParametrosDeInterfaz.fuentePorDefecto);
        ChartPanel unPanelGrafico = new ChartPanel(unGraficoTorta);
        Dimension dmnsn = new Dimension((int) (unPanelGrafico.getPreferredSize().width*unEscalar), (int) (unPanelGrafico.getPreferredSize().height*unEscalar));
        unPanelGrafico.setPreferredSize(dmnsn);
        
        return unPanelGrafico;
    }
    
    public static ChartPanel generarPanelGraficoTortaFlotante(Map<String, Float> partes, String unTitulo, float unEscalar, String unaUnidadMedida){
        JFreeChart unGraficoTorta = Estadistica.obtenerGraficoTortaFlotante(partes, unTitulo, unaUnidadMedida);
        
        
        ChartPanel unPanelGrafico = new ChartPanel(unGraficoTorta);
        Dimension dmnsn = new Dimension((int) (unPanelGrafico.getPreferredSize().width*unEscalar), (int) (unPanelGrafico.getPreferredSize().height*unEscalar));
        unPanelGrafico.setPreferredSize(dmnsn);
        
        return unPanelGrafico;
    }
    
    public static ChartPanel generarHistogramaPorcentajes(ArrayList unaListaObservada, String unTitulo, float unEscalar) throws ExcepcionCargaParametros{
        JFreeChart unGraficoHistograma = Estadistica.obtenerGraficoHistograma(unaListaObservada, unTitulo);
        
                
//((XYBarRenderer)cplot.getRenderer()).setBarPainter(new GradientBarPainter());
        /*BarRenderer r = (BarRenderer)unGraficoHistograma.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, ParametrosDeInterfaz.COLOR_BARRA_LATERAL);
        */
        //((XYPlot) unGraficoHistograma.getPlot()).setLabelFont(ParametrosDeInterfaz.fuentePorDefecto);
        ChartPanel unPanelGrafico = new ChartPanel(unGraficoHistograma);
        Dimension dmnsn = new Dimension((int) (unPanelGrafico.getPreferredSize().width*unEscalar), (int) (unPanelGrafico.getPreferredSize().height*unEscalar));
        unPanelGrafico.setPreferredSize(dmnsn);
        
        return unPanelGrafico;
    }
    
    
    /*public static ChartPanel generarGraficoBarras(String unTitulo, String unTituloEjeX, String unTituloEjeY, ArrayList datosObservados, ArrayList categorias, ArrayList atributos, float unEscalar) throws ExcepcionCargaParametros{
        JFreeChart unGraficoBarras = Estadistica.obtenerGraficoBarras(unTitulo, unTituloEjeX, unTituloEjeY, datosObservados, categorias, atributos);
        unGraficoBarras.getTitle().setFont(ParametrosDeInterfaz.fuentePorDefecto);
        LegendTitle legend = unGraficoBarras.getLegend();
        legend.setItemFont(ParametrosDeInterfaz.fuentePorDefecto);
        //((XYPlot) unGraficoHistograma.getPlot()).setLabelFont(ParametrosDeInterfaz.fuentePorDefecto);
        ChartPanel unPanelGrafico = new ChartPanel(unGraficoBarras);
        Dimension dmnsn = new Dimension((int) (unPanelGrafico.getPreferredSize().width*unEscalar), (int) (unPanelGrafico.getPreferredSize().height*unEscalar));
        unPanelGrafico.setPreferredSize(dmnsn);
        
        return unPanelGrafico;
    }*/
    
    public static ChartPanel generarGraficoBarras(String unTitulo, String unTituloEjeX, String unTituloEjeY, Float[] datosObservados, String[] categorias, float unEscalar) throws ExcepcionCargaParametros{
        JFreeChart unGraficoBarras = Estadistica.obtenerGraficoBarras(unTitulo, unTituloEjeX, unTituloEjeY, datosObservados, categorias);
        
        ChartPanel unPanelGrafico = new ChartPanel(unGraficoBarras);
        Dimension dmnsn = new Dimension((int) (unPanelGrafico.getPreferredSize().width*unEscalar), (int) (unPanelGrafico.getPreferredSize().height*unEscalar));
        
        
        unPanelGrafico.setPreferredSize(dmnsn);
        
        return unPanelGrafico;
    }
    
    public static ChartPanel generarGraficoLineas(String unTitulo, String unTituloEjeX, String unTituloEjeY, Float[] datosObservados, String[] categorias, float unEscalar) throws ExcepcionCargaParametros{
        JFreeChart unGraficoBarras = Estadistica.obtenerGraficoLineas(unTitulo, unTituloEjeX, unTituloEjeY, datosObservados, categorias);
        unGraficoBarras.getTitle().setFont(ParametrosDeInterfaz.fuentePorDefecto);
        
        CategoryPlot cplot = (CategoryPlot)unGraficoBarras.getPlot();
        cplot.setBackgroundPaint(SystemColor.inactiveCaption);//change background color
        //set  bar chart color
        /*((LineAndShapeRenderer)cplot.getRenderer()).set(new StandardBarPainter());
        BarRenderer r = (BarRenderer)unGraficoBarras.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, ParametrosDeInterfaz.COLOR_BARRA_LATERAL);
        */
        LegendTitle legend = unGraficoBarras.getLegend();
        //legend.setItemFont(ParametrosDeInterfaz.fuentePorDefecto);
        //((XYPlot) unGraficoHistograma.getPlot()).setLabelFont(ParametrosDeInterfaz.fuentePorDefecto);
        ChartPanel unPanelGrafico = new ChartPanel(unGraficoBarras);
        Dimension dmnsn = new Dimension((int) (unPanelGrafico.getPreferredSize().width*unEscalar), (int) (unPanelGrafico.getPreferredSize().height*unEscalar));
        
        
        unPanelGrafico.setPreferredSize(dmnsn);
        
        return unPanelGrafico;
    }
    
    public static ChartPanel generarGraficoLineasFechas(String unTitulo, String unTituloEjeX, String unTituloEjeY,  Calendar[] fechas, Float[][] datosObservados,String[] atributos, float unEscalar) throws ExcepcionCargaParametros{
        JFreeChart unGraficoLineas = Estadistica.obtenerGraficoLineasFechas(unTitulo, unTituloEjeX, unTituloEjeY, fechas, datosObservados, atributos);
        //legend.setItemFont(ParametrosDeInterfaz.fuentePorDefecto);
        //((XYPlot) unGraficoHistograma.getPlot()).setLabelFont(ParametrosDeInterfaz.fuentePorDefecto);
        ChartPanel unPanelGrafico = new ChartPanel(unGraficoLineas);
        Dimension dmnsn = new Dimension((int) (unPanelGrafico.getPreferredSize().width*unEscalar), (int) (unPanelGrafico.getPreferredSize().height*unEscalar));
        unPanelGrafico.setPreferredSize(dmnsn);
        return unPanelGrafico;
    }
    
    
    
    public static void agregarChartPanel(JPanel unPanel, ChartPanel unChartPanel){
        unPanel.setLayout(new BorderLayout());
        unPanel.removeAll();
        unPanel.add(unChartPanel, BorderLayout.CENTER);
        unPanel.setVisible(true);

    }
    
    /*
    private static JFreeChart obtenerGraficoBarras (String unTitulo, String ejeXTitulo, String ejeYTitulo, ArrayList datosObservadosEnSubAtributos, ArrayList atributos, ArrayList subAtributos){
        JFreeChart retorno;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0 ; i<atributos.size();i++){
            String unAtributo = (String) atributos.get(i);    
            Float[] datosDeSubAtributos = (Float[]) datosObservadosEnSubAtributos.get(i);
            String[] subAtributosDeAtributo = (String[]) subAtributos.get(i);
            for (int j = 0; j<subAtributosDeAtributo.length; j++){
                Float unaCantidad = datosDeSubAtributos[j];
                String unSubAtributo = subAtributosDeAtributo[j];
                dataset.addValue(unaCantidad, unAtributo, unSubAtributo);
            }
        }
        retorno = ChartFactory.createBarChart(
         unTitulo, 
         ejeXTitulo, ejeYTitulo, 
         dataset,PlotOrientation.VERTICAL, 
         true, true, false);
        NumberAxis range = (NumberAxis) retorno.getCategoryPlot().getRangeAxis();
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return retorno;
    }*/
    
    public static JFreeChart obtenerGraficoBarras (String unTitulo, String ejeXTitulo, String ejeYTitulo, Float[] datosObservadosEnSubAtributos, String[] subAtributos){
        JFreeChart retorno;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0 ; i<subAtributos.length;i++){
            String unAtributo = subAtributos[i];    
                Float unaCantidad = datosObservadosEnSubAtributos[i];
                dataset.addValue(unaCantidad, "", unAtributo);
        }
        retorno = ChartFactory.createBarChart(
         unTitulo, 
         ejeXTitulo, ejeYTitulo, 
         dataset,PlotOrientation.VERTICAL, 
         false, true, false);
        NumberAxis range = (NumberAxis) retorno.getCategoryPlot().getRangeAxis();
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        retorno.getTitle().setFont(ParametrosDeInterfaz.fuentePorDefecto);
        
        CategoryPlot cplot = (CategoryPlot)retorno.getPlot();
        cplot.setBackgroundPaint(SystemColor.inactiveCaption);//change background color
        //set  bar chart color
        ((BarRenderer)cplot.getRenderer()).setBarPainter(new StandardBarPainter());
        BarRenderer r = (BarRenderer)retorno.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, ParametrosDeInterfaz.COLOR_BARRA_LATERAL);
        
        //LegendTitle legend = retorno.getLegend();
        //legend.setItemFont(ParametrosDeInterfaz.fuentePorDefecto);
        //((XYPlot) unGraficoHistograma.getPlot()).setLabelFont(ParametrosDeInterfaz.fuentePorDefecto);
        return retorno;
    }
    
    private static JFreeChart obtenerGraficoLineas (String unTitulo, String ejeXTitulo, String ejeYTitulo, Float[] datosObservadosEnSubAtributos, String[] subAtributos){
        JFreeChart retorno;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i<subAtributos.length; i++){
            String unAtributo = subAtributos[i];    
                Float unaCantidad = datosObservadosEnSubAtributos[i];
                dataset.addValue(unaCantidad, "", unAtributo);
        }
        retorno = ChartFactory.createLineChart(
         unTitulo, 
         ejeXTitulo, ejeYTitulo, 
         dataset,PlotOrientation.VERTICAL, 
         false, true, false);
        NumberAxis range = (NumberAxis) retorno.getCategoryPlot().getRangeAxis();
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return retorno;
    }
    
    private static XYDataset crearSetDatos(String[] atributos, Float[][] datosObservadosEnAtributos, Calendar[] fechas) {
        TimeSeriesCollection dataset = new TimeSeriesCollection(); 
        for (int i = 0; i<atributos.length; i++){
            String unAtributo = atributos[i];    
            TimeSeries unaSerieDeTiempo = new TimeSeries(unAtributo);  
            //for (int j = 0; j < datosObservadosEnAtributos[i].length; j++){
            //System.out.println("Datos observados en atributos [i].length: "+datosObservadosEnAtributos[i].length);
            //System.out.println("Cantidad de fechas en metodo crearSetDatos: "+fechas.length);
            for (int j = 0; j < fechas.length; j++){
                Calendar unaFecha = fechas[j];
                Float unaCantidad = datosObservadosEnAtributos[j][i];
                //System.out.println(Organizacion.expresarCalendario(unaFecha));
                //System.out.println("dia: "+unaFecha.get(Calendar.DAY_OF_MONTH)+", Mes: "+ unaFecha.get(Calendar.MONTH)+", año: "+unaFecha.get(Calendar.YEAR));
                Day unDia = new Day(unaFecha.get(Calendar.DAY_OF_MONTH), (unaFecha.get(Calendar.MONTH))+1, unaFecha.get(Calendar.YEAR));
                unaSerieDeTiempo.addOrUpdate(unDia, unaCantidad);
            }
            dataset.addSeries(unaSerieDeTiempo);
        }
        return dataset;
    }
    
    private static JFreeChart obtenerGraficoLineasFechas (String unTitulo, String ejeXTitulo, String ejeYTitulo, Calendar fechas[], Float[][] datosObservadosEnAtributos, String[] atributos){
        JFreeChart retorno;
        XYDataset dataset = crearSetDatos(atributos, datosObservadosEnAtributos, fechas);
        //TimeSeriesCollection dataset = new TimeSeriesCollection(); 
        retorno = ChartFactory.createTimeSeriesChart(
         unTitulo, 
         ejeXTitulo, ejeYTitulo, 
         dataset);
        //Cambiar el color
        XYPlot plot = (XYPlot) retorno.getPlot();
        plot.setBackgroundPaint(new Color (222,222,222));
        retorno.getTitle().setFont(ParametrosDeInterfaz.fuentePorDefecto);

        /*NumberAxis range = (NumberAxis) retorno.getCategoryPlot().getRangeAxis();
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());*/
        return retorno;
    }
    
    public static JFreeChart obtenerGraficoHistograma(ArrayList unaListaObservada, String unTitulo) throws ExcepcionCargaParametros {
        JFreeChart retorno;
        
        HistogramDataset datos = new HistogramDataset();
        datos.setType(HistogramType.FREQUENCY);
        /*for (int i = 0; i<50; i++){
            datos.addBin(new SimpleHistogramBin(i*2, (i+1)*2.0, true, false));
        }*/
        
        double dobles[] = new double[unaListaObservada.size()];
        for (int i = 0; i<unaListaObservada.size();i++){
            dobles[i] = Double.parseDouble(Float.toString(((Float)unaListaObservada.get(i))));
            //dobles[i] = (double) unaListaObservada.get(i);
        }
        if (dobles.length==0)
            throw new ExcepcionCargaParametros("No existen "+unTitulo+" que cumplan los criterios especificados");
        
        datos.addSeries(unTitulo, dobles, 50);  //El 50 representa la cantidad de barritas

        /*Iterator recorredorDeValores = unaListaObservada.iterator();
        while (recorredorDeValores.hasNext()){
            float unaObservacion = (float) recorredorDeValores.next();
            datos.addObservation(unaObservacion);
        }*/
        retorno = ChartFactory.createHistogram(unTitulo, "Porcentajes", "Frec. observada", datos, PlotOrientation.VERTICAL, true, true, false);
        
        NumberAxis range = (NumberAxis) retorno.getXYPlot().getRangeAxis();
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        retorno.getTitle().setFont(ParametrosDeInterfaz.fuentePorDefecto);
        LegendTitle legend = retorno.getLegend();
        legend.setItemFont(ParametrosDeInterfaz.fuentePorDefecto);
        
        XYPlot cplot = (XYPlot)retorno.getPlot();
        cplot.setBackgroundPaint(SystemColor.inactiveCaption);//change background color
        //set  bar chart color
        XYBarRenderer renderer = (XYBarRenderer) retorno.getXYPlot().getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());
        renderer.setSeriesPaint(0, ParametrosDeInterfaz.COLOR_BARRA_LATERAL);
        
        return retorno;
        
    }
    
    public static ArrayList generarTuplasEstadisticaAnalisisLaboratorio(ArrayList unaListaAnalisisLaboratorio) throws ExcepcionCargaParametros{
        String tamanoLetraAnterior = ""+ParametrosDeInterfaz.fuentePorDefecto.getSize();
        ParametrosDeInterfaz.cambiarTamanoFuente("11");
        ArrayList unaListaTuplas = new ArrayList();
        
        Map <String, Float> partes = new HashMap<String, Float>();
        Float cantidadDeAprobados = 0f;
        Float cantidadDeRechazados = 0f;
        Iterator analisis = unaListaAnalisisLaboratorio.iterator();
        while (analisis.hasNext()){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) analisis.next();
            if (unAnalisis.estaAprobado())
                cantidadDeAprobados++;
            else
                cantidadDeRechazados++;
        }
        partes.put("Aprobados", cantidadDeAprobados);
        partes.put("Rechazados", cantidadDeRechazados);
        JFreeChart unGraficoTorta = Estadistica.obtenerGraficoTortaFlotante(partes, "Conclusiones totales de la lista filtrada", "Analisis");
        unaListaTuplas.add(new TuplaReporteEstadistica(unGraficoTorta, ""));
        
        for (int i = 0; i<AnalisisLaboratorio.ATRIBUTOS_CUANTITATIVOS.length; i++){
            String unCriterioCuantitativo = AnalisisLaboratorio.ATRIBUTOS_CUANTITATIVOS[i];
            ArrayList listaObservada = Organizacion.desmenuzarAnalisis(unaListaAnalisisLaboratorio, unCriterioCuantitativo);
            JFreeChart unGrafico = Estadistica.obtenerGraficoHistograma(listaObservada, "Distribución de frecuencia de "+unCriterioCuantitativo);
            TuplaReporteEstadistica unaTupla = new TuplaReporteEstadistica(unGrafico, "");
            unaListaTuplas.add(unaTupla);
        }
        analisis = unaListaAnalisisLaboratorio.iterator();
        Object[][] cantidadVecesSubAtributosAvistados = new Object[AnalisisLaboratorio.ATRIBUTOS.length][];
        while (analisis.hasNext()){
            AnalisisLaboratorio unAnalisis = (AnalisisLaboratorio) analisis.next();
            for (int i = 0; i < AnalisisLaboratorio.ATRIBUTOS.length; i++){
                String unAtributo = AnalisisLaboratorio.ATRIBUTOS[i];
                for (int j = 0; j < AnalisisLaboratorio.SUB_ATRIBUTOS[i].length; j++){
                    String unSubAtributo = AnalisisLaboratorio.SUB_ATRIBUTOS[i][j];
                    if (cantidadVecesSubAtributosAvistados[i] == null)
                        cantidadVecesSubAtributosAvistados[i] = new Float[AnalisisLaboratorio.SUB_ATRIBUTOS[i].length];
                    if (cantidadVecesSubAtributosAvistados[i][j] == null)
                        cantidadVecesSubAtributosAvistados[i][j] = 0f;
                    cantidadVecesSubAtributosAvistados[i][j] = (Float)cantidadVecesSubAtributosAvistados[i][j] + unAnalisis.getValorUnAtributo(unAtributo, unSubAtributo);
                }
            }
        }
        for (int i = 0; i < AnalisisLaboratorio.ATRIBUTOS.length; i++){
            String unAtributo = AnalisisLaboratorio.ATRIBUTOS[i];
            JFreeChart unGrafico = Estadistica.obtenerGraficoBarras(unAtributo, "Categoría", "Frecuencia observada", (Float[]) cantidadVecesSubAtributosAvistados[i], AnalisisLaboratorio.SUB_ATRIBUTOS[i]);
            TuplaReporteEstadistica unaTupla = new TuplaReporteEstadistica(unGrafico, "");
            unaListaTuplas.add(unaTupla);
        }
        ParametrosDeInterfaz.cambiarTamanoFuente(tamanoLetraAnterior);
        return unaListaTuplas;
    }
    
    public static ArrayList generarTuplasEstadisticaEquipamiento(Equipamiento unEquipamientoSeleccionado) throws ExcepcionCargaParametros{
        String tamanoLetraAnterior = ""+ParametrosDeInterfaz.fuentePorDefecto.getSize();
        ParametrosDeInterfaz.cambiarTamanoFuente("11");
        ArrayList unaListaTuplas = new ArrayList();
        
        Float espacioTotal = unEquipamientoSeleccionado.getCapacidadMaxima();
        Float espacioTotalSinUtilizar;
        Float espacioTotalOcupadoYCV = unEquipamientoSeleccionado.getPesoTotalDeYCVKgV4(Calendar.getInstance());
        Float espacioTotalOcupadoYCE = unEquipamientoSeleccionado.getPesoTotalDeYCEKgV4(Calendar.getInstance());
        Float espacioTotalOcupadoYCM = unEquipamientoSeleccionado.getPesoTotalDeYCMKgV2(Calendar.getInstance());
        espacioTotalSinUtilizar = Math.max(espacioTotal - espacioTotalOcupadoYCV - espacioTotalOcupadoYCE - espacioTotalOcupadoYCM,0);
        Map <String, Float> partes = new HashMap<String, Float>();
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE, espacioTotalOcupadoYCV);
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA, espacioTotalOcupadoYCE);        
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_MOLIDA, espacioTotalOcupadoYCM);
        partes.put("Espacio sin utilizar", espacioTotalSinUtilizar);
        JFreeChart unGrafico = Estadistica.obtenerGraficoTortaFlotante(partes, "Existencia actual en el equipamiento "+unEquipamientoSeleccionado.getNombre(), "Kg(s)");
        TuplaReporteEstadistica unaTupla = new TuplaReporteEstadistica(unGrafico, "");
        unaListaTuplas.add(unaTupla);

        ParametrosDeInterfaz.cambiarTamanoFuente(tamanoLetraAnterior);
        return unaListaTuplas;
    }
    
    public ArrayList generarTuplaEquipamiento(Equipamiento unEquipamiento) throws ExcepcionCargaParametros{
        String tamanoLetraAnterior = ""+ParametrosDeInterfaz.fuentePorDefecto.getSize();
        ParametrosDeInterfaz.cambiarTamanoFuente("11");
        
        ArrayList retorno = new ArrayList();
        Float espacioTotal = unEquipamiento.getCapacidadMaxima();
        Float espacioTotalSinUtilizar;
        Float espacioTotalOcupadoYCV = unEquipamiento.getPesoTotalDeYCVKgV4(Calendar.getInstance());
        Float espacioTotalOcupadoYCE = unEquipamiento.getPesoTotalDeYCEKgV4(Calendar.getInstance());
        Float espacioTotalOcupadoYCM = unEquipamiento.getPesoTotalDeYCMKgV2(Calendar.getInstance());
        espacioTotalSinUtilizar = Math.max(espacioTotal - espacioTotalOcupadoYCV - espacioTotalOcupadoYCE - espacioTotalOcupadoYCM,0);
        Map <String, Float> partes = new HashMap<String, Float>();
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE, espacioTotalOcupadoYCV);
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA, espacioTotalOcupadoYCE);
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_MOLIDA, espacioTotalOcupadoYCM);
        partes.put("Espacio sin utilizar", espacioTotalSinUtilizar);
        String unTitulo = "Existencia actual en el equipamiento";
        String unaUnidadMedida = "Kg(s)";
        JFreeChart unGrafico = Estadistica.obtenerGraficoTortaFlotante(partes, unTitulo, unaUnidadMedida);
        retorno.add(unGrafico);
        
        ParametrosDeInterfaz.cambiarTamanoFuente(tamanoLetraAnterior);
        return retorno;
    }
    
    static public ArrayList generarTuplaEquipamientoConLineas(Equipamiento unEquipamiento, Calendar fechaLimiteInferior, Calendar fechaLimiteSuperior) throws ExcepcionCargaParametros{
        
        if (unEquipamiento == null || fechaLimiteInferior == null || fechaLimiteSuperior == null)
            throw new ExcepcionCargaParametros("Revise los datos ingresados.");
        
        String tamanoLetraAnterior = ""+ParametrosDeInterfaz.fuentePorDefecto.getSize();
        ParametrosDeInterfaz.cambiarTamanoFuente("11");
        
        ArrayList retorno = new ArrayList();
        Float espacioTotal = unEquipamiento.getCapacidadMaxima();
        Float espacioTotalSinUtilizar;
        Float espacioTotalOcupadoYCV = unEquipamiento.getPesoTotalDeYCVKgV4(Calendar.getInstance());
        Float espacioTotalOcupadoYCE = unEquipamiento.getPesoTotalDeYCEKgV4(Calendar.getInstance());
        Float espacioTotalOcupadoYCM = unEquipamiento.getPesoTotalDeYCMKgV2(Calendar.getInstance());
        espacioTotalSinUtilizar = Math.max(espacioTotal - espacioTotalOcupadoYCV - espacioTotalOcupadoYCE - espacioTotalOcupadoYCM,0);
        Map <String, Float> partes = new HashMap<String, Float>();
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_VERDE, espacioTotalOcupadoYCV);
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_ESTACIONADA, espacioTotalOcupadoYCE);
        partes.put(Lote.TIPO_LOTE_YERBA_CANCHADA_MOLIDA, espacioTotalOcupadoYCM);
        partes.put("Espacio sin utilizar", espacioTotalSinUtilizar);
        String unTitulo = "Existencia actual en el equipamiento";
        String unaUnidadMedida = "Kg(s)";
        JFreeChart unGrafico = Estadistica.obtenerGraficoTortaFlotante(partes, unTitulo, unaUnidadMedida);
        TuplaReporteEstadistica unaTupla = new TuplaReporteEstadistica(unGrafico, "");
        retorno.add(unaTupla);
        
        long diasDeDiferencia = Organizacion.calcularCantidadDiasDiferencia(fechaLimiteInferior, fechaLimiteSuperior);
        if (diasDeDiferencia <= 0){
            throw new ExcepcionCargaParametros("La cantidad de días a censar tiene que ser mayor a 0 (Cero)");
        }
        Calendar[] calendarios = new Calendar[(int)diasDeDiferencia];
        for (int i = 0; i<diasDeDiferencia; i++){
            calendarios[i]= (Calendar) fechaLimiteInferior.clone();
            calendarios[i].add(Calendar.DATE, i);
        }
        Float[][] datosPorDia = unEquipamiento.getCantidadAvistamientos(fechaLimiteInferior, fechaLimiteSuperior);
        unTitulo = "Registro histórico de existencia en equipamiento";
        unGrafico = Estadistica.obtenerGraficoLineasFechas(unTitulo, "Tiempo", "Cantidad existente", calendarios, datosPorDia, Lote.TIPO_LOTES);
        unaTupla = new TuplaReporteEstadistica(unGrafico, "");
        retorno.add(unaTupla);
        
        ParametrosDeInterfaz.cambiarTamanoFuente(tamanoLetraAnterior);
        
        return retorno;
    }
    
    public static ArrayList generarTuplasProveedor(Proveedor unProveedor) throws ExcepcionCargaParametros{
        ArrayList retorno = new ArrayList();
        String tamanoLetraAnterior = ""+ParametrosDeInterfaz.fuentePorDefecto.getSize();
        ParametrosDeInterfaz.cambiarTamanoFuente("11");
        
        JFreeChart unGrafico;
        unGrafico = obtenerGraficoTorta(unProveedor.calcularAnalisisAprobadosYRechazados(), "Cantidad de análisis aprobados y rechazados");
        TuplaReporteEstadistica unaTupla = new TuplaReporteEstadistica(unGrafico, "");
        retorno.add(unaTupla);
        
        ParametrosDeInterfaz.cambiarTamanoFuente(tamanoLetraAnterior);
        return retorno;
    }
    
}
