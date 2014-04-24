/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mpm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name="mpm_pontofracointerno")
public class PontoFracoInterno  implements Serializable{
    
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PontoFracoInterno other = (PontoFracoInterno) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }
    
  
    
    
    
}
