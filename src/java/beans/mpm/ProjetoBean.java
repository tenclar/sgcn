/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.ProjetoDAO;
import entity.mpm.Projeto;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
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
public class ProjetoBean implements Serializable {
    private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;   
    private DataModel listaProjetos;
    private Projeto projeto;
    private List<Projeto> listaProjeto = null;
    
    
    
    
    public List<SelectItem> getSelectItemsProjeto() {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Projeto p : projetoDAO.getProjetos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    @SuppressWarnings("unchecked")
    public DataModel getListaProjetos(){
        ProjetoDAO projetoDAO = new ProjetoDAO();
        if(listaProjeto == null) {
            listaProjeto = projetoDAO.getProjetos();
          }
        listaProjetos = new ListDataModel(listaProjeto); 
        return listaProjetos;
    }
    
   
    
    
    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    public void newProjeto(){
        
        this.projeto = new Projeto();
        
       
    }
    
    public void saveProjeto(){
        ProjetoDAO projetoDAO = new ProjetoDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
             
             if(listaProjeto == null) {
            listaProjeto = projetoDAO.getProjetos();
          }
            if (this.listaProjeto.contains(this.projeto)){
                throw new Exception("Já Existe");
            }
            else{               
                projetoDAO.saveProjeto(this.projeto);
               this.listaProjeto = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editProjeto(){
        ProjetoDAO projetoDAO = new ProjetoDAO();
        this.projeto = (Projeto) (this.listaProjetos.getRowData());
        this.projeto = projetoDAO.getProjeto(this.projeto.getId());
    }
    
    public void updateProjeto(){
        ProjetoDAO projetoDAO = new ProjetoDAO();
        context = FacesContext.getCurrentInstance();
        try {
          
            projetoDAO.updateProjeto(projeto);
            this.listaProjeto = null;
            facesutils.info("Cadastro efetuado"); 
        } catch (Exception e) {
             facesutils.erro("Cadastro Não Efetuado! "+e.getMessage());  
        }
            
    }
    public void cancelProjeto(){
       this.projeto = null;
    }
    
}
