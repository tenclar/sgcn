/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.PontoForteExternoDAO;
import entity.mpm.PontoForteExterno;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
public class PontoForteExternoBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
     private FacesContext context;   
    private DataModel listaPontoForteExternos;
    private PontoForteExterno pontoForteExterno;
    private List<PontoForteExterno> listaPontoForteExterno = null;
    
    
   public void PontoForteExternoBean(){
       
       
   }
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaPontoForteExternos(){
         PontoForteExternoDAO pontoForteExternoDAO = new PontoForteExternoDAO();
        if(listaPontoForteExterno == null) {
            listaPontoForteExterno = pontoForteExternoDAO.getPontoForteExternos();
          }
        listaPontoForteExternos = new ListDataModel(listaPontoForteExterno); 
        return listaPontoForteExternos;
    }
    
    public List<SelectItem> getSelectItemsPontoForteExterno() {
         PontoForteExternoDAO pontoForteExternoDAO = new PontoForteExternoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (PontoForteExterno p : pontoForteExternoDAO.getPontoForteExternos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    
    public PontoForteExterno getPontoForteExterno() {
        return pontoForteExterno;
    }

    public void setPontoForteExterno(PontoForteExterno pontoForteExterno) {
        this.pontoForteExterno = pontoForteExterno;
    }
    
    public void newPontoForteExterno(){
        
        this.pontoForteExterno = new PontoForteExterno();
        
       
    }
    
    public void savePontoForteExterno(){
         PontoForteExternoDAO pontoForteExternoDAO = new PontoForteExternoDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            listaPontoForteExterno = pontoForteExternoDAO.getPontoForteExternos();
            
            if (this.listaPontoForteExterno.contains(this.pontoForteExterno)){
                throw new Exception("Já Existe");
            }
            else{               
                pontoForteExternoDAO.savePontoForteExterno(this.pontoForteExterno);
               this.listaPontoForteExterno = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editPontoForteExterno(){
         PontoForteExternoDAO pontoForteExternoDAO = new PontoForteExternoDAO();
        this.pontoForteExterno = (PontoForteExterno) (this.listaPontoForteExternos.getRowData());
        this.pontoForteExterno = pontoForteExternoDAO.getPontoForteExterno(this.pontoForteExterno.getId());
       
    }
    
    public void updatePontoForteExterno(){
         PontoForteExternoDAO pontoForteExternoDAO = new PontoForteExternoDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            pontoForteExternoDAO.updatePontoForteExterno(pontoForteExterno);
            this.listaPontoForteExterno = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelPontoForteExterno(){
       this.pontoForteExterno = null;
    }
    
}
