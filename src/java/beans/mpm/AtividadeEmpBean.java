/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.AtividadeEmpDAO;
import entity.mpm.AtividadeEmp;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import util.FacesUtils;

/**
 *
 * @author silvia
 */
@ManagedBean
@ViewScoped
public class AtividadeEmpBean implements Serializable {
    
    private FacesUtils facesutils = new FacesUtils();
    private DataModel listaAtividadeEmps;
    private AtividadeEmp atividadeEmp;
    private List<AtividadeEmp> listaAtividadeEmp = null;
    
    
    
    
    public List<SelectItem> getSelectItems() {
        AtividadeEmpDAO atividadeEmpDAO = new AtividadeEmpDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (AtividadeEmp p : atividadeEmpDAO.getAtividadeEmps()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    @SuppressWarnings("unchecked")
    public DataModel getListaAtividadeEmps(){
        AtividadeEmpDAO atividadeEmpDAO = new AtividadeEmpDAO();
        if(listaAtividadeEmp == null) {
            listaAtividadeEmp = atividadeEmpDAO.getAtividadeEmps();
          }
        listaAtividadeEmps = new ListDataModel(listaAtividadeEmp); 
        return listaAtividadeEmps;
    }
    
   
    
    
    public AtividadeEmp getAtividadeEmp() {
        return atividadeEmp;
    }

    public void setAtividadeEmp(AtividadeEmp atividadeEmp) {
        this.atividadeEmp = atividadeEmp;
    }
    
    public void newAtividade(){
        System.out.println(" atividade");
        this.atividadeEmp = new AtividadeEmp();
        
       
    }
    
    public void saveAtividadeEmp(){
        AtividadeEmpDAO atividadeEmpDAO = new AtividadeEmpDAO();
        
        
        try {
            
             if(listaAtividadeEmp == null) {
            listaAtividadeEmp = atividadeEmpDAO.getAtividadeEmps();
             }
            if (this.listaAtividadeEmp.contains(this.atividadeEmp)){
                throw new Exception("Já Existe");
            }
            else{               
                atividadeEmpDAO.saveAtividadeEmp(this.atividadeEmp);
               this.listaAtividadeEmp = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! "+e.getMessage());
            
        }
        
       
    }
    
    public void editAtividadeEmp(){
        AtividadeEmpDAO atividadeEmpDAO = new AtividadeEmpDAO();
        this.atividadeEmp = (AtividadeEmp) (this.listaAtividadeEmps.getRowData());
        this.atividadeEmp = atividadeEmpDAO.getAtividadeEmp(this.atividadeEmp.getId());
       
    }
    
    public void updateAtividadeEmp(){
        AtividadeEmpDAO atividadeEmpDAO = new AtividadeEmpDAO();
       
        try {
          
            atividadeEmpDAO.updateAtividadeEmp(atividadeEmp);
            this.listaAtividadeEmp = null;
            facesutils.info("Cadastro efetuado"); 
        } catch (Exception e) {
             facesutils.erro("Cadastro Não Efetuado! "+e.getMessage());  
        }
            
    }
    public void cancelAtividadeEmp(){
       this.atividadeEmp = null;
    }
    
}
