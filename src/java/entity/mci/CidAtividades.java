/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tecnologia01
 */
@Entity
@Table(name="cidatividades")
public class CidAtividades implements Serializable {
   
    
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="atividade_id")
    private Atividade atividade;
    private double renda;
     @ManyToOne
     @JoinColumn(name="cidadao_id")
    private Cidadao cidadao;
    
     

    

    
    
    
    

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }
    
   
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CidAtividades other = (CidAtividades) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    
     
    
}
