/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.relatorio;

import java.io.Serializable;

/**
 *
 * @author tenclar
 */
public class QuadroQuantitativo implements Serializable {
    private String tipopessoa;
    private String statuscid;
    private String benstatus;
    private int  total;

    public QuadroQuantitativo() {
    }

    
    public QuadroQuantitativo(String tipopessoa, String statuscid, String benstatus, int total) {
        this.tipopessoa = tipopessoa;
        this.statuscid = statuscid;
        this.benstatus = benstatus;
        this.total = total;
    }

    public String getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(String tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    public String getStatuscid() {
        return statuscid;
    }

    public void setStatuscid(String statuscid) {
        this.statuscid = statuscid;
    }

    public String getBenstatus() {
        return benstatus;
    }

    public void setBenstatus(String benstatus) {
        this.benstatus = benstatus;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
