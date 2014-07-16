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
@Table(name="mac_concedente")
public class Concedente implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    private int ordem;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datacon;
    @ManyToOne
    private Convenio convenio;
    @Column(columnDefinition = "text")
    private String descricao;
    private String status;

    public Concedente() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public Date getDatacon() {
        return datacon;
    }

    public void setDatacontra(Date datacontra) {
        this.datacon = datacontra;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
