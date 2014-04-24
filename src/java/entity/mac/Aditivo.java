/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mac;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name="mac_aditivo")
public class Aditivo implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    private int seq;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateaditivo;
    @ManyToOne
    private Convenio convenio;
    @Column(columnDefinition = "text")
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    
    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Date getDateaditivo() {
        return dateaditivo;
    }

    public void setDateaditivo(Date dateaditivo) {
        this.dateaditivo = dateaditivo;
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
        final Aditivo other = (Aditivo) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
   
    
}
