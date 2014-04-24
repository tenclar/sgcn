/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import entity.mci.enumerator.EnumStatusCurso;
import entity.mgc.Curso;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author NOTE-04
 */
@Entity
@Table(name="mci_cursossecretaria")
public class CursosSecretaria implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Curso curso;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datainicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datafim;
    private String frequencia;
    private double valor;
    @Enumerated(EnumType.STRING)
    private EnumStatusCurso status;
    @ManyToOne
    @JoinColumn(name="cidadao_id")
    private Cidadao cidadao;

    public CursosSecretaria() {
        this.status = EnumStatusCurso.RESERVA;
    }
    
    
    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }
    

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
      public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }
    
    
    
    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

  

    public EnumStatusCurso getStatus() {
        return status;
    }

    public void setStatus(EnumStatusCurso status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CursosSecretaria other = (CursosSecretaria) obj;
        if (this.curso != other.curso && (this.curso == null || !this.curso.equals(other.curso))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (this.curso != null ? this.curso.hashCode() : 0);
        return hash;
    }

   

   
    
    
    

}
