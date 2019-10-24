/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.util.regex.Pattern;

/**
 *
 * @author usuario
 */
public class Validaciones {
    public static final Pattern CUIT = Pattern.compile("\\d{2}[-]\\d{8}[-]\\d");
    public static final Pattern DNI = Pattern.compile("\\d{8,9}");
    public static final Pattern NUMERO_FRACCIONARIO = Pattern.compile("\\d+.?\\d*");
    public static final Pattern CODIGO_POSTAL_NUEVO = Pattern.compile("[A-Z]\\d{4}[A-Z]{3}");
    public static final Pattern CODIGO_POSTAL_VIEJO = Pattern.compile("\\d{4}");
    
    
    public static boolean unCuitEsValido(String unCuit){
        return Validaciones.CUIT.matcher(unCuit).matches();
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
}
