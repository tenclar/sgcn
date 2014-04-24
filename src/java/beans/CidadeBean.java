/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CidadeDAO;
import dao.EstadoDAO;
import entity.Cidade;
import entity.Estado;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author tecnologia01
 */
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
    
    private FacesContext context;   
    private DataModel listacidades;
    private Cidade cidade;
    private List<Cidade> listacidade = null;
    
    private List<Estado> listaestado = null;
    private EstadoDAO estadoDAO = null;
    
    
    public void CidadeBean(){
            
    }
   @SuppressWarnings("unchecked") 
    public DataModel getListaCidades(){
       CidadeDAO cidadeDAO = new CidadeDAO();
        if (listacidade == null){
           
            listacidade =  cidadeDAO.getCidades();
        }        
        listacidades = new ListDataModel(listacidade); 
        return listacidades;
    }
    
      public List<SelectItem> getSelectItemsEstado() {
          estadoDAO = new EstadoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Estado uf : estadoDAO.getEstados()) {
         toReturn.add(new SelectItem(uf,uf.getNome()));
        }
        return toReturn;
    }
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public void newCidade(ActionEvent actionEvent){        
        this.cidade = new Cidade();
        
       
    }
    
    public void saveCidade(ActionEvent actionEvent){
        
        CidadeDAO cidadeDAO = new CidadeDAO();
        context = FacesContext.getCurrentInstance(); 
        try {
            if (listacidade.contains(this.cidade)){
                throw new Exception("Já Existe");
            }
            else{               
                  
                cidadeDAO.saveCidade(this.cidade);
                listacidade = null;
                context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
            }
          
          
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
        
       
    }
    
    public void editCidade(ActionEvent actionEvent){
        this.cidade = (Cidade) (this.listacidades.getRowData());
        System.out.println("Edita Cidade:"+cidade.getNome());
    }
    
    public void updateCidade(ActionEvent actionEvent){
        context = FacesContext.getCurrentInstance();
        CidadeDAO cidadeDAO = new CidadeDAO();
        try {            
            cidadeDAO.updateCidade(cidade);
            listacidade = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelCidade(){
       this.cidade = null;
    }
    
}
