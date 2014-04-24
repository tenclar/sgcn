/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.EmpreendimentoDAO;
import entity.mpm.Empreendimento;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import util.FacesUtils;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class EmpreendimentoBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
     private FacesContext context;   
    private DataModel listaEmpreendimentos;
    private Empreendimento empreendimento;
    private List<Empreendimento> listaEmpreendimento = null;
    
  
    
    @SuppressWarnings("unchecked")
    public DataModel getListaEmpreendimentos(){
         EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
        if(listaEmpreendimento == null) {
            listaEmpreendimento = empreendimentoDAO.getEmpreendimentos();
          }
        listaEmpreendimentos = new ListDataModel(listaEmpreendimento); 
        return listaEmpreendimentos;
    }
    
    
    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }
    
    public void newEmpreendimento(ActionEvent actionEvent){
        
        this.empreendimento = new Empreendimento();
        
       
    }
    
    public void saveEmpreendimento(){
         EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            if (this.listaEmpreendimento.contains(this.empreendimento)){
                throw new Exception("Já Existe");
            }
            else{               
                empreendimentoDAO.saveEmpreendimento(this.empreendimento);
               this.listaEmpreendimento = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editEmpreendimento(ActionEvent actionEvent){
        this.empreendimento = (Empreendimento) (this.listaEmpreendimentos.getRowData());
       
    }
    
    public void updateEmpreendimento(){
         EmpreendimentoDAO empreendimentoDAO = new EmpreendimentoDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            empreendimentoDAO.updateEmpreendimento(empreendimento);
            this.listaEmpreendimento = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelEmpreendimento(){
       this.empreendimento = null;
    }
    
}
