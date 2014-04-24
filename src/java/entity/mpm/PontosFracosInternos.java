/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mpm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tenclar
 */

@Entity
@Table(name="mpm_pontosfracosinternos")
public class PontosFracosInternos implements Serializable {
    
    
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Plano plano;
    @ManyToOne
    private PontoFracoInterno pontofracointerno;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PontoFracoInterno getPontofracointerno() {
        return pontofracointerno;
    }

    public void setPontofracointerno(PontoFracoInterno pontofracointerno) {
        this.pontofracointerno = pontofracointerno;
    }


    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PontosFracosInternos other = (PontosFracosInternos) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
    
    
    
}
