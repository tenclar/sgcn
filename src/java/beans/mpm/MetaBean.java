/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.MetaDAO;
import entity.mpm.Meta;
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
 * @author Tenclar
 */
@ManagedBean
@ViewScoped
public class MetaBean {
private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;   
    private DataModel listaMetas;
    private Meta meta;
    private List<Meta> listaMeta = null;
   
    
    
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaMetas(){
         MetaDAO metaDAO = new MetaDAO();
        if(listaMeta == null) {
            listaMeta = metaDAO.getMetas();
          }
        listaMetas = new ListDataModel(listaMeta); 
        return listaMetas;
    }
    
    public List<SelectItem> getSelectItems() {
         MetaDAO metaDAO = new MetaDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Meta p : metaDAO.getMetas()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    
    
    
    
    public void newMeta(){
        
        this.meta = new Meta();
        
       
    }
    
    public void saveMeta(ActionEvent actionEvent){
         MetaDAO metaDAO = new MetaDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            listaMeta = metaDAO.getMetas();
            if (this.listaMeta.contains(this.meta)){
                throw new Exception("Já Existe");
            }
            else{               
                metaDAO.saveMeta(this.meta);
               this.listaMeta = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! "+e.getMessage());
            
        }
        
       
    }
    
    public void editMeta(ActionEvent actionEvent){
         MetaDAO metaDAO = new MetaDAO();
        this.meta = (Meta) (this.listaMetas.getRowData());
        this.meta = metaDAO.getMeta(this.meta.getId());
       
    }
    
    public void updateMeta(){
         MetaDAO metaDAO = new MetaDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            metaDAO.updateMeta(meta);
            this.listaMeta = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelMeta(){
       this.meta = null;
    }
}
