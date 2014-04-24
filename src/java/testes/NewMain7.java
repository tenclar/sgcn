/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.mci.CidadaoDAO;
import entity.mci.CidAssociados;
import entity.mci.Cidadao;

/**
 *
 * @author NOTE-04
 */
public class NewMain7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CidadaoDAO cdao = new CidadaoDAO();

        for (Cidadao c : cdao.getList()) {
            for (CidAssociados assoc : c.getAssociados()) {
               
                
            }
            c.getCidAtividades();
                c.getCidBenHabits();
                c.getCidbBenSociais();
                c.getCidbensduraveis();
                c.getCursosSecretarias();
                c.getEquipamentosproprios();
                c.getCursos();
                c.getTelefones();
                c.getDependentes();
                c.getDespesas();
                c.getHistdomicilio();







        }






    }
}
