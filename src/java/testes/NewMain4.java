/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.mci.CidadaoDAO;
import entity.mci.Cidadao;

/**
 *
 * @author NOTE-04
 */
public class NewMain4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cidadao c ;
        
        CidadaoDAO ciddao = new  CidadaoDAO();
        c = ciddao.getEntityByCnp("63905256215");
        //c = ciddao.getEntity("63905256215");
        
        System.out.println("Nome: "+c.getNome());
    }
}
