/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import entity.mci.enumerator.EnumTipoEquipamento;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name="mci_equipamentossecretaria")
public class EquipamentosSecretaria implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataentrega;
    @ManyToOne
    @JoinColumn(name="equipamento_id")
    private Equipamento equipamento;    
    private String patrimonio;   
    private int quantidade;
    private String estado;
    private String situacao;
    private double valor;
    private String codgrp;
    
    @Enumerated(EnumType.STRING)
    private EnumTipoEquipamento tipoequipamento;
    
    
    
    @ManyToOne
    @JoinColumn(name="cidadao_id")
    private Cidadao cidadao;

    public EnumTipoEquipamento getTipoequipamento() {
        return tipoequipamento;
    }

    public void setTipoequipamento(EnumTipoEquipamento tipoequipamento) {
        this.tipoequipamento = tipoequipamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    
    
    public String getCodgrp() {
        return codgrp;
    }

    public void setCodgrp(String codgrp) {
        this.codgrp = codgrp;
    }
    
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }
    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }


    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

   public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EquipamentosSecretaria other = (EquipamentosSecretaria) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
   
}
