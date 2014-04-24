/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.ServicoDAO;
import entity.mpm.Servico;
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
 * @author silvia
 */

@ManagedBean
@ViewScoped
public class ServicoBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;   
    private DataModel listaServicos;
    private Servico servico;
    private List<Servico> listaServico = null;
    
    
   public void ServicoBean(){
       
       
   }
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaServicos(){
        ServicoDAO servicoDAO = new ServicoDAO();
        if(listaServico == null) {
            listaServico = servicoDAO.getServicos();
          }
        listaServicos = new ListDataModel(listaServico); 
        return listaServicos;
    }
    
    public List<SelectItem> getSelectItemsServico() {
        ServicoDAO servicoDAO = new ServicoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Servico s : servicoDAO.getServicos()) {
            toReturn.add(new SelectItem(s, s.getNome()));
        }
        return toReturn;

    }
    
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public void newServico( ){
        
        this.servico = new Servico();
        
       
    }
    
    public void saveServico( ){
        context = FacesContext.getCurrentInstance(); 
        ServicoDAO servicoDAO = new ServicoDAO();
        try {
            if(this.listaServico == null) 
                this.listaServico = servicoDAO.getServicos();
            
            if (this.listaServico.contains(this.servico)){
                throw new Exception("Já Existe");
            }
            else{               
                servicoDAO.saveServico(this.servico);
               this.listaServico = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editServico( ){
        this.servico = (Servico) (this.listaServicos.getRowData());
       
    }
    
    public void updateServico(){
        context = FacesContext.getCurrentInstance();
        ServicoDAO servicoDAO = new ServicoDAO();
        try {
          
            servicoDAO.updateServico(servico);
            this.listaServico = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelServico(){
       this.servico = null;
    }
    
    
}
