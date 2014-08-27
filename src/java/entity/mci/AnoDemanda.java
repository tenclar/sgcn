/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tecnologia01
 */
@Entity
@Table(name="mci_anodemanda")
public class AnoDemanda implements Serializable {
    
    
    @Id
    @GeneratedValue
    private Integer id;
    private Integer ano;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.ano != null ? this.ano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnoDemanda other = (AnoDemanda) obj;
        if (this.ano != other.ano && (this.ano == null || !this.ano.equals(other.ano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ano.toString() ;
    }

   
   
    
    
    

    

  
    
    
    
}
