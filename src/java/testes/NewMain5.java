/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.mci.CidadaoDAO;
import entity.mci.Cidadao;
import entity.mci.enumerator.EnumTipoPessoa;
import java.util.Date;

/**
 *
 * @author NOTE-04
 */
public class NewMain5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Cidadao c = new Cidadao();
        c.setTipopessoa(EnumTipoPessoa.CID);
        CidadaoDAO cd = new CidadaoDAO();
        cd.save(c);               
    }
}
