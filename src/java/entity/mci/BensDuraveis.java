/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author NOTE-04
 */

@Entity
@Table(name="mci_bensduraveis")
public class BensDuraveis implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    @Column(precision=2)
    private double valor;
    @ManyToOne
    private BemDuravel bemDuravel;
    
    @ManyToOne
    private CidBensDuraveis cidBensDuraveis;

    public BemDuravel getBemDuravel() {
        return bemDuravel;
    }

    public void setBemDuravel(BemDuravel bemDuravel) {
        this.bemDuravel = bemDuravel;
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

    public CidBensDuraveis getCidBensDuraveis() {
        return cidBensDuraveis;
    }

    public void setCidBensDuraveis(CidBensDuraveis cidBensDuraveis) {
        this.cidBensDuraveis = cidBensDuraveis;
    }
    
    
    
}
