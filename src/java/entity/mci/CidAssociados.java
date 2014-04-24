package entity.mci;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="mci_cidassociados")
public class CidAssociados implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datainc;
    @ManyToOne
    private Cidadao cidadao;
    @ManyToOne
    private Cidadao associado;

    public CidAssociados() {
        this.datainc = new Date();
    }

    
    
    public Cidadao getAssociado() {
        return associado;
    }

    public void setAssociado(Cidadao associado) {
        this.associado = associado;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatainc() {
        return datainc;
    }

    public void setDatainc(Date datainc) {
        this.datainc = datainc;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CidAssociados other = (CidAssociados) obj;
        if (this.associado != other.associado && (this.associado == null || !this.associado.equals(other.associado))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.associado != null ? this.associado.hashCode() : 0);
        return hash;
    }
    
    
    
}
