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
@Table(name="mac_contrapartida")
public class ContraPartida implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    private int ordem;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datacontra;
    @ManyToOne
    private Convenio convenio;
    @Column(columnDefinition = "text")
    private String descricao;
    private String status;

    public ContraPartida() {
        
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

    public Date getDatacontra() {
        return datacontra;
    }

    public void setDatacontra(Date datacontra) {
        this.datacontra = datacontra;
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
