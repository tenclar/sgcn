/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mac;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author tenclar
 */

@Entity
@Table(name="mac_historicointerno")
public class HistoricoInterno implements Serializable{
    
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datainc;
    @Column(name="descricao", columnDefinition="TEXT")
    private String descricao;
    @ManyToOne
    private Convenio convenio;
    
    @OneToMany(mappedBy="historico",fetch= FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Arquivo> arquivos = new ArrayList<Arquivo>();

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    
   
    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Date getDatainc() {
        return datainc;
    }

    public void setDatainc(Date datainc) {
        this.datainc = datainc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistoricoInterno other = (HistoricoInterno) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
    
}
