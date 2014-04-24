/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mpm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name="mpm_metasempresariais")

public class MetasEmpresariais implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datamon;
    @ManyToOne
    private Meta meta;
    private String prazo;
    private String realizado;
    private String tipo;
    
    @ManyToOne
    private Plano plano;

    public MetasEmpresariais() {
        this.datamon = new Date();
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatamon() {
        return datamon;
    }

    public void setDatamon(Date datamon) {
        this.datamon = datamon;
    }
    
    
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getRealizado() {
        return realizado;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    
    
    
    
    
}
