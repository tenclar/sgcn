/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DomEscoamentoDAO;
import entity.mci.DomEscoamento;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tecnologia01
 */
@ManagedBean
@ViewScoped
public class DomEscoamentoBean implements Serializable {

    private FacesContext context;
    private DataModel listadmEscoamento;
    private DomEscoamento domEscoamento;
    private List<DomEscoamento> listadomEscoamento = null;
    
    private boolean edit = true;

    @SuppressWarnings("unchecked")
    public DataModel getListaDomEscoamento() {
       DomEscoamentoDAO domEscoamentoDAO = new DomEscoamentoDAO();
        if (listadomEscoamento == null) {
            listadomEscoamento = domEscoamentoDAO.getEscoamentos();
        }
        listadmEscoamento = new ListDataModel(listadomEscoamento);
        return listadmEscoamento;
    }

    public DomEscoamento getDomEscoamento() {
        return domEscoamento;
    }

    public void setDomEscoamento(DomEscoamento domEscoamento) {
        this.domEscoamento = domEscoamento;
    }

    public void newDomEscoamento(ActionEvent actionEvent) {

        this.domEscoamento = new DomEscoamento();


    }

    public void saveDomEscoamento(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        DomEscoamentoDAO domEscoamentoDAO = new DomEscoamentoDAO();
        try {
            if (edit) {
                this.updateDomEscoamento();
                edit = false;
            } else {
                if (listadomEscoamento == null) {
                    listadomEscoamento = domEscoamentoDAO.getEscoamentos();
                }
                if (this.listadomEscoamento.contains(this.domEscoamento)) {
                    throw new Exception(this.domEscoamento.getNome() + " Já Existe");
                } else {
                    domEscoamentoDAO.saveEscoamento(this.domEscoamento);
                    this.listadomEscoamento = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }

            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editDomEscoamento(ActionEvent actionEvent) {
        this.domEscoamento = (DomEscoamento) (this.listadmEscoamento.getRowData());
        edit = true;
    }

    public void updateDomEscoamento() {
        context = FacesContext.getCurrentInstance();
        DomEscoamentoDAO domEscoamentoDAO = new DomEscoamentoDAO();
        try {

            domEscoamentoDAO.updateEscoamento(domEscoamento);
            this.listadomEscoamento = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelDomEscoamento() {
        this.domEscoamento = null;
    }
}
