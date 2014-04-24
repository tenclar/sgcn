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
@Table(name="mpm_pontosfracosexternos")
public class PontosFracosExternos implements Serializable {
   
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private PontoFracoExterno pontofracoexterno;
    @ManyToOne
    private Plano plano;
            
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PontoFracoExterno getPontofracoexterno() {
        return pontofracoexterno;
    }

    public void setPontofracoexterno(PontoFracoExterno pontofracoexterno) {
        this.pontofracoexterno = pontofracoexterno;
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
        final PontosFracosExternos other = (PontosFracosExternos) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
}
