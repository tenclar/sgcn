/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mpm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name="mpm_foto")
public class Foto implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String descricao;
    private String caminho;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datafoto;
    @ManyToOne
    private Plano plano;
    
    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Date getDatafoto() {
        return datafoto;
    }

    public void setDatafoto(Date datafoto) {
        this.datafoto = datafoto;
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

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    
    
    
    
    
}
