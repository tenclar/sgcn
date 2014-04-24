/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="cidbensociais")
public class CidBenSociais implements Serializable{
    
    
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="beneficioSocial_id")
    private BeneficioSocial beneficioSocial;
    @Column(nullable=true)
    private double renda;
    @Column(nullable=true)
    private Long nis;
    @ManyToOne
    @JoinColumn(name="cidadao_id")
    private Cidadao cidadao;
    

    public BeneficioSocial getBeneficioSocial() {
        return beneficioSocial;
    }

    public void setBeneficioSocial(BeneficioSocial beneficioSocial) {
        this.beneficioSocial = beneficioSocial;
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

    public Long getNis() {
        return nis;
    }

    public void setNis(Long nis) {
        this.nis = nis;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CidBenSociais other = (CidBenSociais) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
   
}
