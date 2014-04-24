/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mpm;

import java.util.Date;
import javax.persistence.Column;
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
@Table(name="mpm_historicovisita")
public class HistoricoVisita {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;    
    @Column(name="descricao", columnDefinition="TEXT")
    private String descricao;   
    @ManyToOne
    private Plano plano;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    
    
}
