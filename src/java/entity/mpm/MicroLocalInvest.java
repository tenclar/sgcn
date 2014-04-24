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
 * @author NOTE-04
 */
@Entity
@Table(name="mpm_microlocalinvest")
public class MicroLocalInvest implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private LocalInvestimento localinvestimento;
    @ManyToOne
    private Microcredito microcredito;

    public Microcredito getMicrocredito() {
        return microcredito;
    }

    public void setMicrocredito(Microcredito microcredito) {
        this.microcredito = microcredito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalInvestimento getLocalinvestimento() {
        return localinvestimento;
    }

    public void setLocalinvestimento(LocalInvestimento localinvestimento) {
        this.localinvestimento = localinvestimento;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MicroLocalInvest other = (MicroLocalInvest) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
    
    
}
