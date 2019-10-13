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
    
    
    public static boolean unCuitEsValido(String unCuit){
        return Validaciones.CUIT.matcher(unCuit).matches();
    }
    
    public static boolean unDniEsValido(String unDni){
        return Validaciones.DNI.matcher(unDni).matches();
    }
}
