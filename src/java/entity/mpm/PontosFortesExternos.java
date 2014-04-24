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
@Table(name="mpm_pontosfortesexternos")
public class PontosFortesExternos implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private PontoForteExterno pontoforteexterno;
    @ManyToOne
    private Plano plano;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PontoForteExterno getPontoforteexterno() {
        return pontoforteexterno;
    }

    public void setPontoforteexterno(PontoForteExterno pontoforteexterno) {
        this.pontoforteexterno = pontoforteexterno;
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
        final PontosFortesExternos other = (PontosFortesExternos) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
    
}
