/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name="domicilio")
public class Domicilio implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataadd;
    private String localbanheiro;
    private int comodo;
    private String iluminacaopublica;
    @ManyToOne
    private DomMoradia moradia;   
    @ManyToOne
    private DomSituacao situacao;
    @ManyToOne
    private DomEscoamento escoamento;
    @ManyToOne
    private DomAbastecimento abastecimento;
    @ManyToOne
    private DomIluminacao iluminacao;
    @ManyToOne
    private DomPavimentacao pavimentacao;
    @ManyToOne
    private DomColetaLixo coletalixo;
    @ManyToOne
    private DomConstrucao construcao;

    public Domicilio() {
        
        moradia = new DomMoradia();
        situacao = new DomSituacao();
        escoamento = new DomEscoamento();
        abastecimento = new DomAbastecimento();
        iluminacao = new DomIluminacao();
        pavimentacao = new DomPavimentacao();
        coletalixo = new DomColetaLixo();
        construcao = new DomConstrucao();
    }

    public Date getDataadd() {
        return dataadd;
    }

    public void setDataadd(Date dataadd) {
        this.dataadd = dataadd;
    }
    
    
    
    
    
    public DomAbastecimento getAbastecimento() {
        return abastecimento;
    }

    public void setAbastecimento(DomAbastecimento abastecimento) {
        this.abastecimento = abastecimento;
    }

    public DomColetaLixo getColetalixo() {
        return coletalixo;
    }

    public void setColetalixo(DomColetaLixo coletalixo) {
        this.coletalixo = coletalixo;
    }

    public int getComodo() {
        return comodo;
    }

    public void setComodo(int comodo) {
        this.comodo = comodo;
    }

    public DomEscoamento getEscoamento() {
        return escoamento;
    }

    public void setEscoamento(DomEscoamento escoamento) {
        this.escoamento = escoamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIluminacaopublica() {
        return iluminacaopublica;
    }

    public void setIluminacaopublica(String iluminacaopublica) {
        this.iluminacaopublica = iluminacaopublica;
    }

    public DomIluminacao getIluminacao() {
        return iluminacao;
    }

    public void setIluminacao(DomIluminacao iluminacao) {
        this.iluminacao = iluminacao;
    }

    public String getLocalbanheiro() {
        return localbanheiro;
    }

    public void setLocalbanheiro(String localbanheiro) {
        this.localbanheiro = localbanheiro;
    }

    

    public DomMoradia getMoradia() {
        return moradia;
    }

    public void setMoradia(DomMoradia moradia) {
        this.moradia = moradia;
    }

    public DomPavimentacao getPavimentacao() {
        return pavimentacao;
    }

    public void setPavimentacao(DomPavimentacao pavimentacao) {
        this.pavimentacao = pavimentacao;
    }

    public DomSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(DomSituacao situacao) {
        this.situacao = situacao;
    }

    public DomConstrucao getConstrucao() {
        return construcao;
    }

    public void setConstrucao(DomConstrucao construcao) {
        this.construcao = construcao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domicilio other = (Domicilio) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
    
    
    
}
