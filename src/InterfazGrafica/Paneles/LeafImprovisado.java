/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica.Paneles;

/**
 *
 * @author usuario
 */
public class LeafImprovisado {
    private String nombreAExhibir;
    private Object objetoAsociado;

    public LeafImprovisado(String nombreAExhibir, Object objetoAsociado) {
        this.nombreAExhibir = nombreAExhibir;
        this.objetoAsociado = objetoAsociado;
    }

    public String getNombreAExhibir() {
        return nombreAExhibir;
    }

    public Object getObjetoAsociado() {
        return objetoAsociado;
    }

    @Override
    public String toString() {
        return nombreAExhibir;
    }
    
    
}
