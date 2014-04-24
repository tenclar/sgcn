/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.PontoComercialDAO;
import entity.mpm.PontoComercial;
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
 * @author silvia
 */
@ManagedBean
@ViewScoped
public class PontoComercialBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;   
    private DataModel listaPontoComercials;
    private PontoComercial pontoComercial;
    private List<PontoComercial> listaPontoComercial = null;
    
    /** Creates a new instance of PontoComercial */
    public PontoComercialBean() {
    }
    
    
     public List<SelectItem> getSelectItemsPontoComercial() {
          PontoComercialDAO pontoComercialDAO = new PontoComercialDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (PontoComercial p : pontoComercialDAO.getPontoComercials()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
 
    
    @SuppressWarnings("unchecked")
    public DataModel getListaPontoComercials(){
         PontoComercialDAO pontoComercialDAO = new PontoComercialDAO();
        if(listaPontoComercial == null) {
            listaPontoComercial = pontoComercialDAO.getPontoComercials();
          }
        listaPontoComercials = new ListDataModel(listaPontoComercial); 
        return listaPontoComercials;
    }
    
    
    public PontoComercial getPontoComercial() {
        return pontoComercial;
    }

    public void setPontoComercial(PontoComercial pontoComercial) {
        this.pontoComercial = pontoComercial;
    }
    
    public void newPontoComercial(){
        
        this.pontoComercial = new PontoComercial();
        
       
    }
    
    public void savePontoComercial(){
         PontoComercialDAO pontoComercialDAO = new PontoComercialDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            getListaPontoComercials();
            if (this.listaPontoComercial.contains(this.pontoComercial)){
                throw new Exception("Já Existe");
            }
            else{               
                pontoComercialDAO.savePontoComercial(this.pontoComercial);
               this.listaPontoComercial = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editPontoComercial(){
         PontoComercialDAO pontoComercialDAO = new PontoComercialDAO();
        this.pontoComercial = (PontoComercial) (this.listaPontoComercials.getRowData());
        this.pontoComercial = pontoComercialDAO.getPontoComercial(this.pontoComercial.getId());
        
       
    }
    
    public void updatePontoComercial(){
         PontoComercialDAO pontoComercialDAO = new PontoComercialDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            pontoComercialDAO.updatePontoComercial(pontoComercial);
            this.listaPontoComercial = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelPontoComercial(){
       this.pontoComercial = null;
    }
    
}
