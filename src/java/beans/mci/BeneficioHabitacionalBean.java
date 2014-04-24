/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.BeneficioHabitacionalDAO;
import entity.mci.BeneficioHabitacional;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author tecnologia01
 */

@ManagedBean
@ViewScoped
public class BeneficioHabitacionalBean implements Serializable{
    
    private boolean editar=false;
    private FacesContext context;   
    private DataModel listaBeneficioHabitacionals;
    private BeneficioHabitacional beneficioHabitacional;
    private List<BeneficioHabitacional> listaBeneficioHabitacional = null;
    
    
   public void BeneficioHabitacionalBean(){
       
       
   }
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaBeneficioHabitacionals(){
      BeneficioHabitacionalDAO   beneficioHabitacionalDAO = new BeneficioHabitacionalDAO();
        if(listaBeneficioHabitacional == null) {
            listaBeneficioHabitacional = beneficioHabitacionalDAO.getBeneficioHabitacionais();
          }
        listaBeneficioHabitacionals = new ListDataModel(listaBeneficioHabitacional); 
        return listaBeneficioHabitacionals;
    }
    
      public List<SelectItem> getSelectItemsBeneficioHabitacional() { 
      BeneficioHabitacionalDAO     beneficioHabitacionalDAO = new BeneficioHabitacionalDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
         if(listaBeneficioHabitacional == null); {
            listaBeneficioHabitacional = beneficioHabitacionalDAO.getBeneficioHabitacionais();
          }
        for (BeneficioHabitacional uf : listaBeneficioHabitacional) {
            toReturn.add(new SelectItem(uf,uf.getNome()));
        }
        return toReturn;
    }
    public BeneficioHabitacional getBeneficioHabitacional() {
        return beneficioHabitacional;
    }

    public void setBeneficioHabitacional(BeneficioHabitacional beneficioHabitacional) {
        this.beneficioHabitacional = beneficioHabitacional;
    }
    
    public void newBeneficioHabitacional(){
        
        this.beneficioHabitacional = new BeneficioHabitacional();
        
        
       
    }
    
    public void saveBeneficioHabitacional(ActionEvent actionEvent){
           
           
        context = FacesContext.getCurrentInstance(); 
      BeneficioHabitacionalDAO   beneficioHabitacionalDAO = new BeneficioHabitacionalDAO();
        try {
            
             if(listaBeneficioHabitacional == null) {
            listaBeneficioHabitacional = beneficioHabitacionalDAO.getBeneficioHabitacionais();
          }
            if (editar == false){ 
              if (this.listaBeneficioHabitacional.contains(this.beneficioHabitacional)){
                throw new Exception("Já Existe");
                
              }
            }
             beneficioHabitacionalDAO.saveBeneficioHabitacional(this.beneficioHabitacional);
             this.listaBeneficioHabitacional = null;
            if (editar == false) {
                context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
            }else{
                context.addMessage(null, new FacesMessage("Sucesso", "Alteração Efetuada!")); 
                editar =false;
            }
          
          
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Não Efetuado! "+e.getMessage()));  
        }
        
       
    }
    
    public void editBeneficioHabitacional(ActionEvent actionEvent){
        this.beneficioHabitacional = (BeneficioHabitacional) (this.listaBeneficioHabitacionals.getRowData());
        this.editar =true;
       
    }
    
    public void updateBeneficioHabitacional(){
        context = FacesContext.getCurrentInstance();
        BeneficioHabitacionalDAO beneficioHabitacionalDAO = new BeneficioHabitacionalDAO();
        try {
          
            beneficioHabitacionalDAO.updateBeneficioHabitacional(beneficioHabitacional);
            this.listaBeneficioHabitacional = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelBeneficioHabitacional(){
       this.beneficioHabitacional = null;
       
    }
    
}
