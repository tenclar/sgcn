/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.PontoFracoInternoDAO;
import entity.mpm.PontoFracoInterno;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import util.FacesUtils;

/**
 *
 * @author tenclar
 */

@ManagedBean
@ViewScoped
public class PontoFracoInternoBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
    private boolean edit = false;
     private FacesContext context;   
    private DataModel listaPontoFracoInternos;
    private PontoFracoInterno pontoFracoInterno;
    private List<PontoFracoInterno> listaPontoFracoInterno = null;
    
    
   public void PontoFracoInternoBean(){
       
       
   }
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaPontoFracoInternos(){
        PontoFracoInternoDAO pontoFracoInternoDAO = new PontoFracoInternoDAO();
        if(listaPontoFracoInterno == null) {
            
            listaPontoFracoInterno = pontoFracoInternoDAO.getPontoFracoInternos();
          }
        listaPontoFracoInternos = new ListDataModel(listaPontoFracoInterno); 
        return listaPontoFracoInternos;
    }
    
     public List<SelectItem> getSelectItemsPontoFracoInterno() {
         PontoFracoInternoDAO pontoFracoInternoDAO = new PontoFracoInternoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (PontoFracoInterno p : pontoFracoInternoDAO.getPontoFracoInternos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    
    public PontoFracoInterno getPontoFracoInterno() {
        return pontoFracoInterno;
    }

    public void setPontoFracoInterno(PontoFracoInterno pontoFracoInterno) {
        this.pontoFracoInterno = pontoFracoInterno;
    }
    
    public void newPontoFracoInterno(ActionEvent actionEvent){
        
        this.pontoFracoInterno = new PontoFracoInterno();
        
       
    }
    
    public void savePontoFracoInterno(ActionEvent actionEvent){
        context = FacesContext.getCurrentInstance(); 
        PontoFracoInternoDAO pontoFracoInternoDAO = new PontoFracoInternoDAO();
        try {
            if (this.edit){
               pontoFracoInternoDAO.savePontoFracoInterno(this.pontoFracoInterno);
               this.listaPontoFracoInterno = null;
               this.edit = false;
                context.addMessage(null, new FacesMessage("Sucesso", "Alteração Efetuada !"));  
                
            }else{
                if(listaPontoFracoInterno == null) {
                listaPontoFracoInterno = pontoFracoInternoDAO.getPontoFracoInternos();
                }
                if (this.listaPontoFracoInterno.contains(this.pontoFracoInterno)){
                throw new Exception("Item "+this.pontoFracoInterno.getNome()+" Já Existe");
                }
                 else{               
                         pontoFracoInternoDAO.savePontoFracoInterno(this.pontoFracoInterno);
                            this.listaPontoFracoInterno = null;
                            
                     context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
                }
                this.pontoFracoInterno = null;
            }
            
            
            
            
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! "+e.getMessage());
            
        }
        
       
    }
    
    public void editPontoFracoInterno(ActionEvent actionEvent){
        PontoFracoInternoDAO pontoFracoInternoDAO = new PontoFracoInternoDAO();
        this.pontoFracoInterno = (PontoFracoInterno) (this.listaPontoFracoInternos.getRowData());
        this.pontoFracoInterno = pontoFracoInternoDAO.getPontoFracoInterno(this.pontoFracoInterno.getId());
        this.edit= true;
       
    }
    
    public void updatePontoFracoInterno(){
        PontoFracoInternoDAO pontoFracoInternoDAO = new PontoFracoInternoDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            pontoFracoInternoDAO.updatePontoFracoInterno(pontoFracoInterno);
            this.listaPontoFracoInterno = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelPontoFracoInterno(){
       this.pontoFracoInterno = null;
    }
    
}
