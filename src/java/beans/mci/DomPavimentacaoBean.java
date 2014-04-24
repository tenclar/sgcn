/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DomPavimentacaoDAO;
import entity.mci.DomPavimentacao;
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
public class DomPavimentacaoBean {
    
    private FacesContext context;   
    private DataModel listadmPavimentacao;
    private DomPavimentacao domPavimentacao;
    private List<DomPavimentacao> listaDomPavimentacao = null;
    
    private boolean edit = false;
    
    @SuppressWarnings("unchecked")
    public DataModel getListaDomPavimentacao(){
      DomPavimentacaoDAO  domPavimentacaoDAO = new DomPavimentacaoDAO();   
        if(listaDomPavimentacao == null) {
            listaDomPavimentacao = domPavimentacaoDAO.getPavimentacaos();
          }
        listadmPavimentacao = new ListDataModel(listaDomPavimentacao); 
        return listadmPavimentacao;
    }
    
      
    public DomPavimentacao getDomPavimentacao() {
        return domPavimentacao;
    }

    public void setDomPavimentacao(DomPavimentacao domPavimentacao) {
        this.domPavimentacao = domPavimentacao;
    }
    
    public void newDomPavimentacao(ActionEvent actionEvent){
        
        this.domPavimentacao = new DomPavimentacao();
        
       
    }
    
    public void saveDomPavimentacao(ActionEvent actionEvent){
        context = FacesContext.getCurrentInstance(); 
      DomPavimentacaoDAO  domPavimentacaoDAO = new DomPavimentacaoDAO();   
        try {
            
            if (this.edit){
                domPavimentacaoDAO.savePavimentacao(this.domPavimentacao);
               this.listaDomPavimentacao = null;
                context.addMessage(null, new FacesMessage("Sucesso", "Alteração Efetuada !"));   
            }else{
                
                if(listaDomPavimentacao == null) {
                listaDomPavimentacao = domPavimentacaoDAO.getPavimentacaos();
                }
                if (this.listaDomPavimentacao.contains(this.domPavimentacao)){
                throw new Exception("Item "+this.domPavimentacao.getNome()+" Já Existe");
                }
                 else{               
                       domPavimentacaoDAO.savePavimentacao(this.domPavimentacao);
                        this.listaDomPavimentacao = null;
                     context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
                }
                this.domPavimentacao = null;
            }
          
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
        
       
    }
    
    public void editDomPavimentacao(ActionEvent actionEvent){
        this.edit = true;
        this.domPavimentacao = (DomPavimentacao) (this.listadmPavimentacao.getRowData());
       
    }
    
    public void updateDomPavimentacao(){
        context = FacesContext.getCurrentInstance();
      DomPavimentacaoDAO  domPavimentacaoDAO = new DomPavimentacaoDAO();   
        try {
          
            domPavimentacaoDAO.updatePavimentacao(domPavimentacao);
            this.listaDomPavimentacao = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelDomPavimentacao(){
       this.domPavimentacao = null;
    }
    
}
