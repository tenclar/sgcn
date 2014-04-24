/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DomColetalixoDAO;
import entity.mci.DomColetaLixo;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class DomColetaLixoBean {
  
    
    private FacesContext context;   
    private DataModel listadmColetaLixo;
    private DomColetaLixo domColetaLixo;
    private List<DomColetaLixo> listaDomColetaLixo = null;
    
    private boolean edit = false;
    
    @SuppressWarnings("unchecked")
    public DataModel getListaDomColetaLixo(){
     DomColetalixoDAO   domColetaLixoDAO = new DomColetalixoDAO();
        if(listaDomColetaLixo == null) {
            listaDomColetaLixo = domColetaLixoDAO.getColetaLixos();
          }
        listadmColetaLixo = new ListDataModel(listaDomColetaLixo); 
        return listadmColetaLixo;
    }
    
      
    public DomColetaLixo getDomColetaLixo() {
        return domColetaLixo;
    }

    public void setDomColetaLixo(DomColetaLixo domColetaLixo) {
        this.domColetaLixo = domColetaLixo;
    }
    
    public void newDomColetaLixo(ActionEvent actionEvent){
        
        this.domColetaLixo = new DomColetaLixo();
        
       
    }
    
    public void saveDomColetaLixo(ActionEvent actionEvent){
        context = FacesContext.getCurrentInstance(); 
       DomColetalixoDAO domColetaLixoDAO = new DomColetalixoDAO();
        try {
            
            if (this.edit){
                domColetaLixoDAO.saveColetaLixo(this.domColetaLixo);
               this.listaDomColetaLixo = null;
                context.addMessage(null, new FacesMessage("Sucesso", "Alteração Efetuada !"));   
            }else{
                
                if(listaDomColetaLixo == null) {
                listaDomColetaLixo = domColetaLixoDAO.getColetaLixos();
                }
                if (this.listaDomColetaLixo.contains(this.domColetaLixo)){
                throw new Exception("Item "+this.domColetaLixo.getNome()+" Já Existe");
                }
                 else{               
                       domColetaLixoDAO.saveColetaLixo(this.domColetaLixo);
                        this.listaDomColetaLixo = null;
                     context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
                }
                this.domColetaLixo = null;
            }
          
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
        
       
    }
    
    public void editDomColetaLixo(ActionEvent actionEvent){
        this.edit = true;
        this.domColetaLixo = (DomColetaLixo) (this.listadmColetaLixo.getRowData());
       
    }
    
    public void updateDomColetaLixo(){
        context = FacesContext.getCurrentInstance();
       DomColetalixoDAO domColetaLixoDAO = new DomColetalixoDAO();
        try {
          
            domColetaLixoDAO.updateColetaLixo(domColetaLixo);
            this.listaDomColetaLixo = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelDomColetaLixo(){
       this.domColetaLixo = null;
    }
    
}
