/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class Configuracion {
    
    public static final Float PORCENTAJE_CONCLUSION_MOLIENDA = 0.005f;
    public static final int BOLSAS_CONCLUSION_MOLIENDA = 0;
    public static final Float PORCENTAJE_MAXIMO_MERMA = 0.03f;
    public static boolean verificarExistenciaDeArchivo(){
        boolean retorno;
        String ruta = "./Configuracion.ini";
        File archivo = new File(ruta);
        retorno = archivo.exists();
        return retorno;
    }
    public static void crearArchivo() throws IOException{
            String ruta = "./Configuracion.ini";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
    }
    
    
    public static void borrarArchivoConfiguracion(){
        String ruta = "./Configuracion.ini";
        File archivo = new File(ruta);
        archivo.delete();
    }
    
    public static Map<String, String> leerArchivoConfiguracion() throws FileNotFoundException, IOException{
        Map<String, String> retorno = new HashMap<String, String>();
        String ruta = "./Configuracion.ini";
        File archivo = new File(ruta);
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        String lineaActual;
        while((lineaActual=br.readLine())!=null){
            String[] partes = lineaActual.split(" ");
            retorno.put(partes[partes.length-2], partes[partes.length-1]); //Guardo los dos ultimos datos
        }
        fr.close();
        br.close();
        return retorno;
    }
    
    public static void escribirArchivoConfiguracion(ArrayList lineas) throws IOException{
        String ruta = "./Configuracion.ini";
        PrintWriter writer = new PrintWriter(ruta, "UTF-8");
        Iterator recorredorLineas = lineas.iterator();
        while (recorredorLineas.hasNext()){
            String unaLinea = (String) recorredorLineas.next();
            writer.println(unaLinea);    
        }
        writer.close(); 
    }
    
        
    
}
