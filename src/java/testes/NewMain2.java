/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.mci.CidadaoDAO;
import entity.mci.Cidadao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NOTE-04
 */
public class NewMain2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        List<Cidadao> listacid = new ArrayList<Cidadao>();
        
        listacid.addAll(cidadaoDAO.getList("VA"));
       
    }
}
