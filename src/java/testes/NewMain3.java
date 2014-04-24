/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import entity.mci.Cidadao;

import entity.mci.Cursos;
import entity.mgc.Curso;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author NOTE-04
 */
public class NewMain3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cidadao c = new Cidadao();
                Set<Cursos> cursoses = new HashSet<Cursos>();

        Cursos cursos = new Cursos();      
        Curso curso = new  Curso();
        Cursos cursos2 = new Cursos();      
        
        c.setNome("nome");
        curso.setNome("informatica");
        cursos.setCidadao(c);
        cursos.setCurso(curso);
        cursos.setHora(34);
        cursos2.setCidadao(c);
        cursos2.setCurso(curso);
        cursos2.setHora(6767);
        
        cursoses.add(cursos);
        cursoses.add(cursos2);
        
        System.out.println(" qtd "+ cursoses.size());
                
    }
}
