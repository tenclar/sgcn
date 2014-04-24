/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DomSituacaoDAO;
import entity.mci.DomSituacao;
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
public class DomSituacaoBean  implements Serializable{
     
     private FacesContext context;   
    private DataModel listaDomSituacaos;
    private DomSituacao domSituacao;
    private List<DomSituacao> listaDomSituacao = null;
    
    private boolean edit = false;
    
    @SuppressWarnings("unchecked")
    public DataModel getListaDomSituacaos(){
     DomSituacaoDAO   domSituacaoDAO = new DomSituacaoDAO();
        if(listaDomSituacao == null) {
            listaDomSituacao = domSituacaoDAO.getListas();
          }
        listaDomSituacaos = new ListDataModel(listaDomSituacao); 
        return listaDomSituacaos;
    }
    
      
    public DomSituacao getDomSituacao() {
        return domSituacao;
    }

    public void setDomSituacao(DomSituacao domSituacao) {
        this.domSituacao = domSituacao;
    }
    
    public void newDomSituacao(ActionEvent actionEvent){
        
        this.domSituacao = new DomSituacao();
        
       
    }
    
    public void saveDomSituacao(ActionEvent actionEvent){
        context = FacesContext.getCurrentInstance(); 
       DomSituacaoDAO domSituacaoDAO = new DomSituacaoDAO();
        try {
             if (edit) {
                this.updateDomSituacao();
                edit = false;
            } else {
            if(listaDomSituacao == null) {
            listaDomSituacao = domSituacaoDAO.getListas();
          }
            if (this.listaDomSituacao.contains(this.domSituacao)){
                throw new Exception(this.domSituacao.getNome()+ " Já Existe");
            }
            else{               
                domSituacaoDAO.save(domSituacao);
               this.listaDomSituacao = null;
                context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
            }
          
             }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
        
       
    }
    
    public void editDomSituacao(ActionEvent actionEvent){
        this.domSituacao = (DomSituacao) (this.listaDomSituacaos.getRowData());
        edit = true;
       
    }
    
    public void updateDomSituacao(){
        context = FacesContext.getCurrentInstance();
       DomSituacaoDAO domSituacaoDAO = new DomSituacaoDAO();
        try {
          
            domSituacaoDAO.update(domSituacao);
            this.listaDomSituacao = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelDomSituacao(){
       this.domSituacao = null;
    }
}
