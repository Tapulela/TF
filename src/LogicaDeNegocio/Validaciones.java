/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author usuario
 */
public class Validaciones {
    public static final Pattern CUIT = Pattern.compile("\\d{2}[-]\\d{8}[-]\\d");
    public static final Pattern DNI = Pattern.compile("\\d{8,9}");
//    public static final Pattern NUMERO_FRACCIONARIO = Pattern.compile("\\d+.?\\d*");
    public static final Pattern NUMERO_FRACCIONARIO = Pattern.compile("\\d+.+,?\\d*");
    public static final Pattern CODIGO_POSTAL_NUEVO = Pattern.compile("[A-Z]\\d{4}[A-Z]{3}");
    public static final Pattern CODIGO_POSTAL_VIEJO = Pattern.compile("\\d{4}");
    public static final Pattern PATENTE_VIEJA = Pattern.compile("[A-Z]{3}-\\d{3}");
    public static final Pattern PATENTE_NUEVA = Pattern.compile("[A-Z]{2}-\\d{3}-[A-Z]{2}");
    public static final Pattern NUMERO_ENTERO = Pattern.compile("\\d+");
    
    public static boolean unCuitEsValido(String unCuit){
        return Validaciones.CUIT.matcher(unCuit).matches();
    }
    
    public static void validarCuit(String unCuit) throws ExcepcionCargaParametros{
    unCuit = unCuit.replaceAll("[^\\d]", "");
    if (unCuit.length() != 11){
        throw new ExcepcionCargaParametros("El cuit no se valido.");
    }
    char[] cuitArray = unCuit.toCharArray();
    Integer[] serie = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
    Integer aux = 0;
    for (int i=0; i<10; i++){
        aux += Character.getNumericValue(cuitArray[i]) * serie[i];
    }
    aux = 11 - (aux % 11);
    if (aux == 11){
        aux = 0;
    }
    if (!Objects.equals(Character.getNumericValue(cuitArray[10]), aux))
        throw new ExcepcionCargaParametros("El cuit no se valido.");
    }
    
    public static boolean unDniEsValido(String unDni){
        return Validaciones.DNI.matcher(unDni).matches();
    }
    public static boolean esUnNumeroFraccionarioValido(String unNumero){
        return Validaciones.NUMERO_FRACCIONARIO.matcher(unNumero).matches();
    }
    
    public static boolean esUnCodigoPostalValido(String unCodigoPostal){ //VALIDA POR SI CUMPLE CUALQUIERA DE LOS DOS FORMATOS.
        return (Validaciones.CODIGO_POSTAL_NUEVO.matcher(unCodigoPostal).matches() || Validaciones.CODIGO_POSTAL_VIEJO.matcher(unCodigoPostal).matches());
    }
    
    public static boolean esUnaPatenteValida(String unaPatente){
        return (Validaciones.PATENTE_VIEJA.matcher(unaPatente).matches() || Validaciones.PATENTE_NUEVA.matcher(unaPatente).matches());
    }
    
    public static boolean esUnNumeroEnteroValido(String unEntero){
        return (Validaciones.NUMERO_ENTERO.matcher(unEntero).matches());
    }

    public static boolean sonLotesDeYerbaCanchadaVerde(ArrayList<Lote> lotes) {
        boolean sonLotesYCV = true;
        Iterator recorredorLotes = lotes.iterator();
        while (recorredorLotes.hasNext() && sonLotesYCV){
            Lote unLote = (Lote) recorredorLotes.next();
            sonLotesYCV = unLote.esDeYerbaCancadaVerde();
        }
        return sonLotesYCV;
    }

    public static boolean sonLotesRegulares(ArrayList<Lote> lotes) {
        boolean sonRegulares = true;
        Iterator recorredorLotes = lotes.iterator();
        while (recorredorLotes.hasNext() && sonRegulares){
            Lote unLote = (Lote) recorredorLotes.next();
            sonRegulares = unLote.estaRegular();
        }
        return sonRegulares;
    }
}
