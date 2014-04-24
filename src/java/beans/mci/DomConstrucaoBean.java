/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DomConstrucaoDAO;
import entity.mci.DomConstrucao;
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
public class DomConstrucaoBean implements Serializable {

    private FacesContext context;
    private DataModel listaDomConstrucaos;
    private DomConstrucao domConstrucao;
    private List<DomConstrucao> listaDomConstrucao = null;
    
    private boolean edit=false;

    @SuppressWarnings("unchecked")
    public DataModel getListaDomConstrucaos() {
      DomConstrucaoDAO  domConstrucaoDAO = new DomConstrucaoDAO();
        if (listaDomConstrucao == null) {
            listaDomConstrucao = domConstrucaoDAO.getListas();
        }
        listaDomConstrucaos = new ListDataModel(listaDomConstrucao);
        return listaDomConstrucaos;
    }

    public DomConstrucao getDomConstrucao() {
        return domConstrucao;
    }

    public void setDomConstrucao(DomConstrucao domDomConstrucao) {
        this.domConstrucao = domDomConstrucao;
    }

    public void newDomConstrucao(ActionEvent actionEvent) {

        this.domConstrucao = new DomConstrucao();


    }

    public void saveDomConstrucao(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
       DomConstrucaoDAO domConstrucaoDAO = new DomConstrucaoDAO();
        try {
            if (edit) {
                this.updateDomConstrucao();
                edit = false;
            } else {
                if (listaDomConstrucao == null) {
                    listaDomConstrucao = domConstrucaoDAO.getListas();
                }
                if (this.listaDomConstrucao.contains(this.domConstrucao)) {
                    throw new Exception(this.domConstrucao.getNome() + " Já Existe");
                } else {
                    domConstrucaoDAO.save(domConstrucao);
                    this.listaDomConstrucao = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }

            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editDomConstrucao(ActionEvent actionEvent) {
        this.domConstrucao = (DomConstrucao) (this.listaDomConstrucaos.getRowData());
        edit = true;
    }

    public void updateDomConstrucao() {
        context = FacesContext.getCurrentInstance();
        DomConstrucaoDAO domConstrucaoDAO = new DomConstrucaoDAO();
        try {

            domConstrucaoDAO.update(domConstrucao);
            this.listaDomConstrucao = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelDomConstrucao() {
        this.domConstrucao = null;
    }
}
