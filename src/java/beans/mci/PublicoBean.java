/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.PublicoDAO;
import entity.mci.Publico;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import util.RelatorioUtil;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class PublicoBean implements Serializable {

    private FacesContext context;
    private DataModel listaPublicos;
    private Publico publico;
    private List<Publico> listaPublico = null;
    
    private boolean edit=false;

    @SuppressWarnings("unchecked")
    public DataModel getListaPublicos() {
      PublicoDAO publicoDAO = new PublicoDAO();
        if (listaPublico == null) {
            listaPublico = publicoDAO.getPublicos();
        }
        listaPublicos = new ListDataModel(listaPublico);
        return listaPublicos;
    }
     public List<SelectItem> getSelectItems() {
       PublicoDAO publicoDAO = new PublicoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Publico p : publicoDAO.getPublicos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;
    }
     
     public List<Publico> getSelects() {
       PublicoDAO publicoDAO = new PublicoDAO();       
        
        return publicoDAO.getPublicos();
    }


    public Publico getPublico() {
        return publico;
    }

    public void setPublico(Publico publico) {
        this.publico = publico;


    }

    public void newPublico(ActionEvent actionEvent) {

        this.publico = new Publico();


    }

    public void savePublico(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        PublicoDAO publicoDAO = new PublicoDAO();
        try {
            if (edit) {
                this.updatePublico();
                edit = false;
            } else {
                if (listaPublico == null) {
                    listaPublico = publicoDAO.getPublicos();
                }
                if (this.listaPublico.contains(this.publico)) {
                    throw new Exception("Já Existe");
                } else {
                     publicoDAO.savePublico(this.publico);
                    this.listaPublico = null;
                    this.publico = new Publico();
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editPublico(ActionEvent actionEvent) {
        this.publico = (Publico) (this.listaPublicos.getRowData());
        edit=true;

    }

    public void updatePublico() {
        context = FacesContext.getCurrentInstance();
        PublicoDAO publicoDAO = new PublicoDAO();
        try {

            publicoDAO.updatePublico(publico);
            this.listaPublico = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelPublico() {
        this.publico = null;
    }
    
     @SuppressWarnings("unchecked")
    public void imprimeRelatorioWebTodos() throws IOException, JRException {
        
        PublicoDAO publicoDAO = new PublicoDAO();
        List lista =  publicoDAO.getPublicos();
        

        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_lista_publico");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, "lista_publico");

    }
}
