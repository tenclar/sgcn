/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.mci.CidadaoDAO;
import entity.mci.CidAtividades;
import entity.mci.Cidadao;
import entity.mci.Cursos;
import entity.mci.Dependente;
import entity.mci.enumerator.EnumTipoPessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NOTE-04
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        List<Cursos> lista = new ArrayList<Cursos>();
        
        
        for(Cidadao c: cidadaoDAO.getList()){
            System.out.println("***Dados Cadastrais***");
            System.out.println("Codigo: "+c.getId());
            System.out.println("Cidadao: "+c.getNome());
            System.out.println("Endereço: "+c.getEndereco().getLogradouro());
            System.out.println("Cidade: "+c.getEndereco().getBairro().getCidade().getNome());
            //System.out.println("Dependentes " +c.getDependentes().size());
            
//            for (CidAtividades a : c.getCidAtividades()) {
//                    System.out.println("*** Atividades ***");
//                    System.out.println("Atividade: " + a.getAtividade().getDescricao());
//                    System.out.println("Renda: " + a.getRenda());
//                }
            
                
//                for (Cursos cr : c.getCursos()) {
//                    System.out.println("*** Cursos ***");
//                    System.out.println("Curso: " + cr.getCurso().getNome());
//                    System.out.println("Carga: " + cr.getHora());
//                }
//            
//                      System.out.println("*** Dependentes ***");
//                for (Dependente cr : c.getDependentes()) {
//                    
//                    System.out.println("nome: " + cr.getNome());
//                    //System.out.println("Local Trabalho: " + cr.getLocalTrabalho());
//                    System.out.println("Nec Especial: " + cr.getNecEspecial().getDescricao());
//                }
//            
        }
    }
}
