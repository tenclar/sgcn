/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.LocalInvestimentoDAO;
import entity.mpm.LocalInvestimento;
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
 * @author Tenclar
 */

@ManagedBean
@ViewScoped
public class LocalInvestimentoBean {
    
private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;   
    private DataModel listaLocalInvestimentos;
    private LocalInvestimento localInvestimento;
    private List<LocalInvestimento> listaLocalInvestimento = null;
    
    
    
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaLocalInvestimentos(){
        LocalInvestimentoDAO localInvestimentoDAO = new LocalInvestimentoDAO();
        if(listaLocalInvestimento == null) {
            listaLocalInvestimento = localInvestimentoDAO.getLocalInvestimentos();
          }
        listaLocalInvestimentos = new ListDataModel(listaLocalInvestimento); 
        return listaLocalInvestimentos;
    }
    
    public List<SelectItem> getSelectItems() {
        LocalInvestimentoDAO localInvestimentoDAO = new LocalInvestimentoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (LocalInvestimento p : localInvestimentoDAO.getLocalInvestimentos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    public LocalInvestimento getLocalInvestimento() {
        return localInvestimento;
    }

    public void setLocalInvestimento(LocalInvestimento localInvestimento) {
        this.localInvestimento = localInvestimento;
    }
    
    
    
    
    public void newLocalInvestimento(){
        
        this.localInvestimento = new LocalInvestimento();
        
       
    }
    
    public void saveLocalInvestimento(){
        LocalInvestimentoDAO localInvestimentoDAO = new LocalInvestimentoDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            this.getListaLocalInvestimentos();
            if (this.listaLocalInvestimento.contains(this.localInvestimento)){
                throw new Exception("Já Existe");
            }
            else{               
                localInvestimentoDAO.saveLocalInvestimento(this.localInvestimento);
               this.listaLocalInvestimento = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! "+e.getMessage());
            
        }
        
       
    }
    
    public void editLocalInvestimento(){
        LocalInvestimentoDAO localInvestimentoDAO = new LocalInvestimentoDAO();
        this.localInvestimento = (LocalInvestimento) (this.listaLocalInvestimentos.getRowData());
        this.localInvestimento = localInvestimentoDAO.getLocalInvestimento(this.localInvestimento.getId());
       
    }
    
    public void updateLocalInvestimento(){
        LocalInvestimentoDAO localInvestimentoDAO = new LocalInvestimentoDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            localInvestimentoDAO.updateLocalInvestimento(localInvestimento);
            this.listaLocalInvestimento = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelLocalInvestimento(){
       this.localInvestimento = null;
    }
}
