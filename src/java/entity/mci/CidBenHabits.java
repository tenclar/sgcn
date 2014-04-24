/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import java.io.Serializable;
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
@Table(name="cidbenhabits")
public class CidBenHabits implements Serializable {
    
   
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="beneficioHabitacional_id")
    private BeneficioHabitacional beneficioHabitacional;
    private double valor;
    @ManyToOne
    @JoinColumn(name="cidadao_id")
    private Cidadao cidadao;
    

    public BeneficioHabitacional getBeneficioHabitacional() {
        return beneficioHabitacional;
    }

    public void setBeneficioHabitacional(BeneficioHabitacional beneficioHabitacional) {
        this.beneficioHabitacional = beneficioHabitacional;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
        final CidBenHabits other = (CidBenHabits) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    
   
    
    
}
