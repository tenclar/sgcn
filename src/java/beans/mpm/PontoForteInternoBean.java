/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.PontoForteInternoDAO;
import entity.mpm.PontoForteInterno;

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
public class PontoForteInternoBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
     private FacesContext context;   
    private DataModel listaPontoForteInternos;
    private PontoForteInterno pontoForteInterno;
    private List<PontoForteInterno> listaPontoForteInterno = null;
    
    
   public void PontoForteInternoBean(){
       
       
   }
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaPontoForteInternos(){
        PontoForteInternoDAO pontoForteInternoDAO = new PontoForteInternoDAO();
        if(listaPontoForteInterno == null) {
            listaPontoForteInterno = pontoForteInternoDAO.getPontoForteInternos();
          }
        listaPontoForteInternos = new ListDataModel(listaPontoForteInterno); 
        return listaPontoForteInternos;
    }
    
    public List<SelectItem> getSelectItemsPontoForteInterno() {
        PontoForteInternoDAO pontoForteInternoDAO = new PontoForteInternoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (PontoForteInterno p : pontoForteInternoDAO.getPontoForteInternos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    
    public PontoForteInterno getPontoForteInterno() {
        return pontoForteInterno;
    }

    public void setPontoForteInterno(PontoForteInterno pontoForteInterno) {
        this.pontoForteInterno = pontoForteInterno;
    }
    
    public void newPontoForteInterno(ActionEvent actionEvent){
        
        this.pontoForteInterno = new PontoForteInterno();
        
       
    }
    
    public void savePontoForteInterno(ActionEvent actionEvent){
        PontoForteInternoDAO pontoForteInternoDAO = new PontoForteInternoDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            listaPontoForteInterno = pontoForteInternoDAO.getPontoForteInternos();
            if (this.listaPontoForteInterno.contains(this.pontoForteInterno)){
                throw new Exception("Já Existe");
            }
            else{               
                pontoForteInternoDAO.savePontoForteInterno(this.pontoForteInterno);
               this.listaPontoForteInterno = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
           facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editPontoForteInterno(ActionEvent actionEvent){
        PontoForteInternoDAO pontoForteInternoDAO = new PontoForteInternoDAO();
        this.pontoForteInterno = (PontoForteInterno) (this.listaPontoForteInternos.getRowData());
       this.pontoForteInterno = pontoForteInternoDAO.getPontoForteInterno(this.pontoForteInterno.getId());
    }
    
    public void updatePontoForteInterno(){
        PontoForteInternoDAO pontoForteInternoDAO = new PontoForteInternoDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            pontoForteInternoDAO.updatePontoForteInterno(pontoForteInterno);
            this.listaPontoForteInterno = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelPontoForteInterno(){
       this.pontoForteInterno = null;
    }
    
}
