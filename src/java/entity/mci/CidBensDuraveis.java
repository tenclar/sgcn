/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;


@Entity
@Table(name="mci_cidbensduraveis")
public class CidBensDuraveis implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataadd;
    
    @OneToMany(mappedBy = "cidBensDuraveis", fetch = FetchType.LAZY, targetEntity = BensDuraveis.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})    
    private List<BensDuraveis> bensduraveis = new ArrayList<BensDuraveis>();
    
    @ManyToOne
    private Cidadao cidadao;

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }
    

    public List<BensDuraveis> getBensduraveis() {
        return bensduraveis;
    }

    public void setBensduraveis(List<BensDuraveis> bensduraveis) {
        this.bensduraveis = bensduraveis;
    }

    public Date getDataadd() {
        return dataadd;
    }

    public void setDataadd(Date dataadd) {
        this.dataadd = dataadd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
    
}
