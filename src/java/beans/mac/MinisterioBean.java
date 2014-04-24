/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mac;

import dao.mac.MinisterioDAO;
import entity.mac.Ministerio;
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

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class MinisterioBean implements Serializable{
    
    private FacesContext context;   
    private DataModel listaMinisterios;
    private Ministerio ministerio;
    private List<Ministerio> listaMinisterio = null;
    
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaMinisterios(){
      MinisterioDAO  ministerioDAO = new MinisterioDAO();
        if(listaMinisterio == null) {
            listaMinisterio = ministerioDAO.getMinisterios();
          }
        listaMinisterios = new ListDataModel(listaMinisterio); 
        return listaMinisterios;
    }
    
      
    public Ministerio getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(Ministerio ministerio) {
        this.ministerio = ministerio;
        
        
    }
    
    public void newMinisterio(ActionEvent actionEvent){
        
        this.ministerio = new Ministerio();
        
       
    }
    
    public void saveMinisterio(ActionEvent actionEvent){
        context = FacesContext.getCurrentInstance(); 
         MinisterioDAO ministerioDAO =  new MinisterioDAO();
        try {
            if(listaMinisterio == null) {
            listaMinisterio = ministerioDAO.getMinisterios();
          }
            if (this.listaMinisterio.contains(this.ministerio)){
                throw new Exception("Já Existe");
            }
            else{               
                ministerioDAO.saveMinisterio(this.ministerio);
               this.listaMinisterio = null;
                context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
            }
          
          
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
        
       
    }
    
    public void editMinisterio(ActionEvent actionEvent){
        this.ministerio = (Ministerio) (this.listaMinisterios.getRowData());
       
    }
    
    public void updateMinisterio(){
        context = FacesContext.getCurrentInstance();
         MinisterioDAO ministerioDAO = new MinisterioDAO();
        try {
          
            ministerioDAO.updateMinisterio(ministerio);
            this.listaMinisterio = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelMinisterio(){
       this.ministerio = null;
    }
    public List<SelectItem> getSelectItems() {
           MinisterioDAO ministerioDAO = new MinisterioDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Ministerio m : ministerioDAO.getMinisterios()) {
         toReturn.add(new SelectItem(m,m.getNome()));
        }
        return toReturn;
    }
    
}
