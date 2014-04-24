/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.mpm.PlanoDAO;
import entity.mpm.Plano;
import entity.mpm.Rendimento;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author NOTE-04
 */
public class NewMain6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaRend();
        
        

    }

    public static void listaRend() {
        // TODO code application logic here
        
        int anoaux = 0;
        int mesaux = 0;
       // PlanoDAO pdao = new PlanoDAO(HibernateUtil.getSession());
      //  Plano p = pdao.getPlano(1);

//        for (Rendimento r : p.getRendimentos()) {
//            if (r.getDaterend().get(Calendar.YEAR) != anoaux) {
//                System.out.println(" Ano: " + r.getDaterend().get(Calendar.YEAR));                
//
//                for (Rendimento rr : p.getRendimentos()) {
//                    if (r.getDaterend().get(Calendar.YEAR) == rr.getDaterend().get(Calendar.YEAR)) {
//                        if (rr.getDaterend().get(Calendar.MONTH) != mesaux) {
//                            System.out.println("  Mes: " + rr.getDaterend().get(Calendar.MONTH));
//                            
//
//                            for (Rendimento rrr : p.getRendimentos()) {
//                                if (rr.getDaterend().get(Calendar.MONTH) == rrr.getDaterend().get(Calendar.MONTH)) {
//                                    System.out.println("   Dia: " + rrr.getDaterend().get(Calendar.DAY_OF_MONTH) + " Venda: " + rrr.getVendas());
//
//                                    
//                                }
//                            }
//                            
//                            
//                        }
//                        
//                    }
//
//                    mesaux = rr.getDaterend().get(Calendar.MONTH);
//
//
//                }
//
//             
//            }
//
//            anoaux = r.getDaterend().get(Calendar.YEAR);
//
//
//
//        }
//        
    }
}
