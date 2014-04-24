/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author tenclar
 */


@Entity
@Table(name="mpm_rendimento")
public class Rendimento implements Serializable{
    
   
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datamon = new Date();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date daterend = new Date();
    private double vendas;
    private double despesas;
    private double outras;
    @ManyToOne
    private Plano plano;
    @Transient
    List<Rendimento> rendimentos= new ArrayList<Rendimento>();

    public Date getDatamon() {
        return datamon;
    }

    public void setDatamon(Date datamon) {
        this.datamon = datamon;
    }
    
    
    

    public double getDespesas() {
        return despesas;
    }

    public void setDespesas(double despesas) {
        this.despesas = despesas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDaterend() {
        return daterend;
    }

    public void setDaterend(Date daterend) {
        this.daterend = daterend;
    }

    


    

    public double getOutras() {
        return outras;
    }

    public void setOutras(double outras) {
        this.outras = outras;
    }

    public double getVendas() {
        return vendas;
    }

    public void setVendas(double vendas) {
        this.vendas = vendas;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    
    
    public double getLucro(){
        return this.vendas-this.despesas;
    }
    
    public double getTotalRenda(){
        return this.getLucro()+this.getOutras();
    }

    public List<Rendimento> getRendimentos() {
        return rendimentos;
    }

    public void setRendimentos(List<Rendimento> rendimentos) {
        this.rendimentos = rendimentos;
    }
    
  
    
}
