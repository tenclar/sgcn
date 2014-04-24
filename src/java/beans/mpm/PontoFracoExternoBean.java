/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.PontoFracoExternoDAO;
import entity.mpm.PontoFracoExterno;

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
public class PontoFracoExternoBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
     private FacesContext context;   
    private DataModel listaPontoFracoExternos;
    private PontoFracoExterno pontoFracoExterno;
    private List<PontoFracoExterno> listaPontoFracoExterno = null;
    
    
   public void PontoFracoExternoBean(){
       
       
   }
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaPontoFracoExternos(){
        PontoFracoExternoDAO pontoFracoExternoDAO = new PontoFracoExternoDAO();
        if(listaPontoFracoExterno == null) {
            listaPontoFracoExterno = pontoFracoExternoDAO.getPontoFracoExternos();
          }
        listaPontoFracoExternos = new ListDataModel(listaPontoFracoExterno); 
        return listaPontoFracoExternos;
    }
    public List<SelectItem> getSelectItemsPontoFracoExterno() {
        PontoFracoExternoDAO pontoFracoExternoDAO = new PontoFracoExternoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (PontoFracoExterno p : pontoFracoExternoDAO.getPontoFracoExternos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    
    public PontoFracoExterno getPontoFracoExterno() {
        return pontoFracoExterno;
    }

    public void setPontoFracoExterno(PontoFracoExterno pontoFracoExterno) {
        this.pontoFracoExterno = pontoFracoExterno;
    }
    
    public void newPontoFracoExterno(ActionEvent actionEvent){
        
        this.pontoFracoExterno = new PontoFracoExterno();
        
       
    }
    
    public void savePontoFracoExterno(ActionEvent actionEvent){
        PontoFracoExternoDAO pontoFracoExternoDAO = new PontoFracoExternoDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            listaPontoFracoExterno = pontoFracoExternoDAO.getPontoFracoExternos();
            if (this.listaPontoFracoExterno.contains(this.pontoFracoExterno)){
                throw new Exception("Já Existe");
            }
            else{               
                pontoFracoExternoDAO.savePontoFracoExterno(this.pontoFracoExterno);
               this.listaPontoFracoExterno = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editPontoFracoExterno(ActionEvent actionEvent){
        PontoFracoExternoDAO pontoFracoExternoDAO = new PontoFracoExternoDAO();
        this.pontoFracoExterno = (PontoFracoExterno) (this.listaPontoFracoExternos.getRowData());
        this.pontoFracoExterno =  pontoFracoExternoDAO.getPontoFracoExterno(this.pontoFracoExterno.getId());
    }
    
    public void updatePontoFracoExterno(){
        PontoFracoExternoDAO pontoFracoExternoDAO = new PontoFracoExternoDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            pontoFracoExternoDAO.updatePontoFracoExterno(pontoFracoExterno);
            this.listaPontoFracoExterno = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelPontoFracoExterno(){
       this.pontoFracoExterno = null;
    }
    
}
