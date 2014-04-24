/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.ResponsavelDAO;
import entity.mpm.Responsavel;
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
public class ResponsavelBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;   
    private DataModel listaResponsavels;
    private Responsavel responsavel;
    private List<Responsavel> listaResponsavel = null;
    
    private String busca;
    
   public void ResponsavelBean(){
       
       
   }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
     public void localiza(){         
         ResponsavelDAO responsavelDAO = new ResponsavelDAO();
                listaResponsavel = responsavelDAO.getList(this.busca);
       }
    
     
     
     @SuppressWarnings("unchecked")
        public DataModel getDataModelListaResp() {
         ListDataModel dmLista = null;
         if(listaResponsavel != null){                                
            dmLista = new ListDataModel(listaResponsavel);         
         }
        return dmLista;
    } 
    @SuppressWarnings("unchecked")
    public DataModel getListaResponsavel(){
        ResponsavelDAO responsavelDAO = new ResponsavelDAO();
        if(listaResponsavel == null) {
            listaResponsavel = responsavelDAO.getResponsavels();
          }
        listaResponsavels = new ListDataModel(listaResponsavel); 
        return listaResponsavels;
    }
    
    public List<SelectItem> getSelectItems() {
        ResponsavelDAO responsavelDAO = new ResponsavelDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Responsavel p : responsavelDAO.getResponsavels()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    
    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
    
    public void newResponsavel(ActionEvent actionEvent){
        
        this.responsavel = new Responsavel();
        
       
    }
    
    public void saveResponsavel(){
        ResponsavelDAO responsavelDAO = new ResponsavelDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            this.listaResponsavel = responsavelDAO.getResponsavels();
            if (this.listaResponsavel.contains(this.responsavel)){
                throw new Exception("Já Existe");
            }
            else{               
                responsavelDAO.saveResponsavel(this.responsavel);
               this.listaResponsavel = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editResponsavel(){
        ResponsavelDAO responsavelDAO = new ResponsavelDAO();
        this.responsavel = (Responsavel) (this.listaResponsavels.getRowData());
       this.responsavel = responsavelDAO.getResponsavel(this.responsavel.getId());
    }
    
    public void updateResponsavel(){
        ResponsavelDAO responsavelDAO = new ResponsavelDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            responsavelDAO.updateResponsavel(responsavel);
            this.listaResponsavel = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelResponsavel(){
       this.responsavel = null;
       
    }
}
