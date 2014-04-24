/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mac;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name = "mac_investimento")
public class Investimento implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private int codigo;
    @Column(name="descricao", columnDefinition="TEXT")
    private String descricao;
    private double valorproponente;
    private double valorconcedente;
    private String complemento;
    @ManyToOne
    private NatDespesa natDespesa;
    @ManyToOne
    private Convenio convenio;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public NatDespesa getNatDespesa() {
        return natDespesa;
    }

    public void setNatDespesa(NatDespesa natDespesa) {
        this.natDespesa = natDespesa;
    }
    

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValorconcedente() {
        return valorconcedente;
    }

    public void setValorconcedente(double valorconcedente) {
        this.valorconcedente = valorconcedente;
    }

    public double getValorproponente() {
        return valorproponente;
    }

    public void setValorproponente(double valorproponente) {
        this.valorproponente = valorproponente;
    }

    public double getValorTotal() {
        return this.valorconcedente + this.valorproponente;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Investimento other = (Investimento) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
