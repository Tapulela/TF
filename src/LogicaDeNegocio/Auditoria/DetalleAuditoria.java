/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio.Auditoria;


/**
 *
 * @author usuario
 */
public class DetalleAuditoria {
    private String campo;
    private String valorAnterior;
    private String valorPosterior;

    public DetalleAuditoria(String campo, String valorAnterior, String valorPosterior) {
        this.campo = campo;
        this.valorAnterior = valorAnterior;
        this.valorPosterior = valorPosterior;
    }
    public Object[] devolverVector() {
        Object[] vec ={this.campo, this.valorAnterior, this.valorPosterior};
    return vec;
    }
    
}
