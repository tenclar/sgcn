/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name="mpm_microcredito")


public class Microcredito implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String recebeu;
    private String objetivo;
    private String condfin;
    private String intobterfin;
    @OneToMany(mappedBy = "microcredito")
     @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<MicroLocalInvest> microlocaisinvestidos = new ArrayList<MicroLocalInvest>();
    @OneToMany(mappedBy = "microcredito")
     @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<MicroLocalInvestFin> microlocaisinvestfin = new ArrayList<MicroLocalInvestFin>();
    
    

    public Microcredito() {
   
        this.objetivo = "NAO";
        this.recebeu = "NAO";
        this.condfin= "NONE";                
        this.intobterfin = "NONE";
    }

    public String getCondfin() {
        return condfin;
    }

    public void setCondfin(String condfin) {
        this.condfin = condfin;
    }

    public String getIntobterfin() {
        return intobterfin;
    }

    public void setIntobterfin(String intobterfin) {
        this.intobterfin = intobterfin;
    }

   
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getRecebeu() {
        return recebeu;
    }

    public void setRecebeu(String recebeu) {
        this.recebeu = recebeu;
    }

    public List<MicroLocalInvestFin> getMicrolocaisinvestfin() {
        return microlocaisinvestfin;
    }

    public void setMicrolocaisinvestfin(List<MicroLocalInvestFin> microlocaisinvestfin) {
        this.microlocaisinvestfin = microlocaisinvestfin;
    }

    public List<MicroLocalInvest> getMicrolocaisinvestidos() {
        return microlocaisinvestidos;
    }

    public void setMicrolocaisinvestidos(List<MicroLocalInvest> microlocaisinvestidos) {
        this.microlocaisinvestidos = microlocaisinvestidos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Microcredito other = (Microcredito) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

   
    
    
    
    
}
