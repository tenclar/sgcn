/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import entity.Cidade;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author tecnologia01
 */
@Entity
@Table(name="dependente")
public class Dependente implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Parentesco grau;
    private String nome;    
    private String sexo;
    @ManyToOne
    @JoinColumn(name="naturalidade_id")
    private Cidade naturalidade;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    @ManyToOne
    @JoinColumn(name="necespecial_id")
    private NecEspecial necEspecial;
    @ManyToOne
    @JoinColumn(name="escolaridade_id")
    private Escolaridade escolaridade;
    private int ano;
    private String instituicao;
    private String frequenta;
    private String localTrabalho;
    private String cargo;
    private double renda;

    public Dependente() {
        this.naturalidade = new Cidade();
        this.necEspecial = new NecEspecial();
        this.escolaridade = new Escolaridade();
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    
    
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getFrequenta() {
        return frequenta;
    }

    public void setFrequenta(String frequenta) {
        this.frequenta = frequenta;
    }

    public Parentesco getGrau() {
        return grau;
    }

    public void setGrau(Parentesco grau) {
        this.grau = grau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public Cidade getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(Cidade naturalidade) {
        this.naturalidade = naturalidade;
    }

    public NecEspecial getNecEspecial() {
        return necEspecial;
    }

    public void setNecEspecial(NecEspecial necEspecial) {
        this.necEspecial = necEspecial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }
    public int getIdade() {
        int idade = 0;
        Calendar cData = Calendar.getInstance();
        Calendar cHoje = Calendar.getInstance();
        if (this.getDataNascimento() != null) {
            cData.set(Calendar.YEAR, cHoje.get(Calendar.YEAR));
            cData.setTime(this.getDataNascimento());
            idade = cData.after(cHoje) ? -1 : 0;
            idade += cHoje.get(Calendar.YEAR) - cData.get(Calendar.YEAR);
        }

        return idade;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dependente other = (Dependente) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    
    
     @Override
    public String toString() {
        return id.toString();
    }
    
}
