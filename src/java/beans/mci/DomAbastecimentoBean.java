/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DomAbastecimentoDAO;
import entity.mci.DomAbastecimento;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tecnologia01
 */

@ManagedBean
@ViewScoped
public class DomAbastecimentoBean  implements Serializable{
    
    private FacesContext context;   
    private DataModel listadmAbastecimento;
    private DomAbastecimento domAbastecimento;
    private List<DomAbastecimento> listaDomAbastecimento = null;
    
    private boolean edit = false;
    
    @SuppressWarnings("unchecked")
    public DataModel getListaDomAbastecimento(){
       DomAbastecimentoDAO domAbastecimentoDAO = new DomAbastecimentoDAO();
        if(listaDomAbastecimento == null) {
            listaDomAbastecimento = domAbastecimentoDAO.getListas();
          }
        listadmAbastecimento = new ListDataModel(listaDomAbastecimento); 
        return listadmAbastecimento;
    }
    
      
    public DomAbastecimento getDomAbastecimento() {
        return domAbastecimento;
    }

    public void setDomAbastecimento(DomAbastecimento domAbastecimento) {
        this.domAbastecimento = domAbastecimento;
    }
    
    public void newDomAbastecimento(ActionEvent actionEvent){
        
        this.domAbastecimento = new DomAbastecimento();
        
       
    }
    
    public void saveDomAbastecimento(ActionEvent actionEvent){
        context = FacesContext.getCurrentInstance(); 
       DomAbastecimentoDAO domAbastecimentoDAO = new DomAbastecimentoDAO();
        try {
            
            if (this.edit){
                
                 domAbastecimentoDAO.save(this.domAbastecimento);
                    this.listaDomAbastecimento = null;                     
                     context.addMessage(null, new FacesMessage("Sucesso", " Alteração Efetuada!"));
                     edit = false;
               
            }else{                        
                
                if(listaDomAbastecimento == null) {
                    listaDomAbastecimento = domAbastecimentoDAO.getListas();
                } 
                if (this.listaDomAbastecimento.contains(this.domAbastecimento)){
                    throw new Exception("Item "+this.domAbastecimento.getNome()+" Já Existe");
                }else{
                    domAbastecimentoDAO.save(this.domAbastecimento);
                    this.listaDomAbastecimento = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));  
                   
                }
              }                 
               
          
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
        
       
    }
    
    public void editDomAbastecimento(ActionEvent actionEvent){
        this.edit = true;
        this.domAbastecimento = (DomAbastecimento) (this.listadmAbastecimento.getRowData());
       
    }
    
    public void updateDomAbastecimento(){
        context = FacesContext.getCurrentInstance();
      DomAbastecimentoDAO  domAbastecimentoDAO = new DomAbastecimentoDAO();
        try {
          
            domAbastecimentoDAO.update(domAbastecimento);
            this.listaDomAbastecimento = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelDomAbastecimento(){
       this.domAbastecimento = null;
    }
    
}
