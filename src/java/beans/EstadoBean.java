/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.EstadoDAO;
import entity.Estado;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;


/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
    
    private FacesContext context;   
    private DataModel listaEstados;
    private Estado estado;
    private List<Estado> listaEstado = null;
    private EstadoDAO estadoDAO = null;
    
   public void EstadoBean(){
       
       
   }
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaEstados(){
        if(listaEstado == null) {
            estadoDAO = new EstadoDAO();
            listaEstado = estadoDAO.getEstados();
          }
        listaEstados = new ListDataModel(listaEstado); 
        return listaEstados;
    }
    
      public List<SelectItem> getSelectItemsEstado() { 
           estadoDAO = new EstadoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
         if(listaEstado == null); {
            listaEstado = estadoDAO.getEstados();
          }
        for (Estado uf : listaEstado) {
            toReturn.add(new SelectItem(uf,uf.getNome()));
        }
        return toReturn;
    }
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public void newEstado(ActionEvent actionEvent){
        
        this.estado = new Estado();
        
       
    }
    
    public void saveEstado(ActionEvent actionEvent){
        context = FacesContext.getCurrentInstance(); 
         estadoDAO = new EstadoDAO();
        try {
            if (this.listaEstado.contains(this.estado)){
                throw new Exception("Já Existe");
            }
            else{               
                estadoDAO.saveEstado(this.estado);
               this.listaEstado = null;
                context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
            }
          
          
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
        
       
    }
    
    public void editEstado(ActionEvent actionEvent){
        this.estado = (Estado) (this.listaEstados.getRowData());
       
    }
    
    public void updateEstado(){
        context = FacesContext.getCurrentInstance();
         estadoDAO = new EstadoDAO();
        try {
          
            estadoDAO.updateEstado(estado);
            this.listaEstado = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelEstado(){
       this.estado = null;
    }
    
}
