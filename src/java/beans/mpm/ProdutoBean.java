/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mpm.ProdutoDAO;
import entity.mpm.Produto;
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
public class ProdutoBean implements Serializable{
    private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;   
    private DataModel listaProdutos;
    private Produto produto;
    private List<Produto> listaProduto = null;
    
    
   public void ProdutoBean(){
       
       
   }
    
    
    @SuppressWarnings("unchecked")
    public DataModel getListaProdutos(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if(listaProduto == null) {
            listaProduto = produtoDAO.getProdutos();
          }
        listaProdutos = new ListDataModel(listaProduto); 
        return listaProdutos;
    }
    
    public List<SelectItem> getSelectItemsProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Produto p : produtoDAO.getProdutos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;

    }
    
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void newProduto(){
        
        this.produto = new Produto();
        
       
    }
    
    public void saveProduto(){
        context = FacesContext.getCurrentInstance(); 
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            
             if(listaProduto == null) {
            listaProduto = produtoDAO.getProdutos();
          }
            if (this.listaProduto.contains(this.produto)){
                throw new Exception(this.produto.getNome()+" Já Existe");
            }
            else{               
                produtoDAO.saveProduto(this.produto);
               this.listaProduto = null;
                facesutils.info("Cadastro efetuado com sucesso");
            }
          
          
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            
        }
        
       
    }
    
    public void editProduto(){
        this.produto = (Produto) (this.listaProdutos.getRowData());
       
    }
    
    public void updateProduto(){
        context = FacesContext.getCurrentInstance();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
          
            produtoDAO.updateProduto(produto);
            this.listaProduto = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));   
        } catch (Exception e) {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }
            
    }
    public void cancelProduto(){
       this.produto = null;
    }
    
}
