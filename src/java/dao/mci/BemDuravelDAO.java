/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mci;

import dao.GenericDAO;
import entity.mci.BemDuravel;
import java.util.List;

/**
 *
 * @author tecnologia01
 */
public class BemDuravelDAO  extends GenericDAO{

    public BemDuravelDAO() {
    }

    
    
    
    public long saveBemDuravel(BemDuravel bemDuravel) {
        saveOrUpdatePojo(bemDuravel);
        return bemDuravel.getId();
    }

   

    public void updateBemDuravel(BemDuravel bemDuravel) {
        saveOrUpdatePojo(bemDuravel);
    }

    public BemDuravel getBemDuravel(int bemDuravelId) {
        BemDuravel BemDuravel =  getPojo(BemDuravel.class, bemDuravelId);
        return BemDuravel;
    }

    public List<BemDuravel> getBemDuravels() {
        return getPureList(BemDuravel.class,"from BemDuravel bemDuravel");
    }
    
}
