/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.relatorio;

import entity.mci.Cidadao;

/**
 *
 * @author admin
 */
public class RelBeneficiario {
    private Cidadao cidadao;
    private int totalben;
    

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public int getTotalben() {
        return totalben;
    }

    public void setTotalben(int totalben) {
        this.totalben = totalben;
    }
    
   
    
}
