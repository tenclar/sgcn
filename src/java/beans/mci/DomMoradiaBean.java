/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DomMoradiaDAO;
import entity.mci.DomMoradia;
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
public class DomMoradiaBean implements Serializable {

    private FacesContext context;
    private DataModel listaDomMoradias;
    private DomMoradia domMoradia;
    private List<DomMoradia> listaDomMoradia = null;
    
    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaDomMoradias() {
       DomMoradiaDAO domMoradiaDAO = new DomMoradiaDAO();
        if (listaDomMoradia == null) {
            listaDomMoradia = domMoradiaDAO.getMoradias();
        }
        listaDomMoradias = new ListDataModel(listaDomMoradia);
        return listaDomMoradias;
    }

    public DomMoradia getDomMoradia() {
        return domMoradia;
    }

    public void setDomMoradia(DomMoradia domMoradia) {
        this.domMoradia = domMoradia;
    }

    public void newDomMoradia(ActionEvent actionEvent) {

        this.domMoradia = new DomMoradia();


    }

    public void saveDomMoradia(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
      DomMoradiaDAO  domMoradiaDAO = new DomMoradiaDAO();
        try {
            if (edit) {
                this.updateDomMoradia();;
                edit = false;
            } else {
                if (listaDomMoradia == null) {
                    listaDomMoradia = domMoradiaDAO.getMoradias();
                }
                if (this.listaDomMoradia.contains(this.domMoradia)) {
                    throw new Exception(this.domMoradia.getDescricao() + " Já Existe");
                } else {
                    domMoradiaDAO.saveMoradia(domMoradia);
                    this.listaDomMoradia = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editDomMoradia(ActionEvent actionEvent) {
        this.domMoradia = (DomMoradia) (this.listaDomMoradias.getRowData());
        edit = true;
    }

    public void updateDomMoradia() {
        context = FacesContext.getCurrentInstance();
      DomMoradiaDAO domMoradiaDAO = new DomMoradiaDAO();
        try {

            domMoradiaDAO.updateMoradia(domMoradia);
            this.listaDomMoradia = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelDomMoradia() {
        this.domMoradia = null;
    }
}
