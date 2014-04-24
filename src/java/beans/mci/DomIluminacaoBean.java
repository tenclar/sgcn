/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DomIluminacaoDAO;
import entity.mci.DomIluminacao;

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
public class DomIluminacaoBean implements Serializable {

    private FacesContext context;
    private DataModel listadmIluminacao;
    private DomIluminacao domIluminacao;
    private List<DomIluminacao> listaDomIluminacao = null;
    
    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaDomIluminacao() {
      DomIluminacaoDAO  domIluminacaoDAO = new DomIluminacaoDAO();
        if (listaDomIluminacao == null) {
            listaDomIluminacao = domIluminacaoDAO.getIluminacaos();
        }
        listadmIluminacao = new ListDataModel(listaDomIluminacao);
        return listadmIluminacao;
    }

    public DomIluminacao getDomIluminacao() {
        return domIluminacao;
    }

    public void setDomIluminacao(DomIluminacao domIluminacao) {
        this.domIluminacao = domIluminacao;
    }

    public void newDomIluminacao(ActionEvent actionEvent) {

        this.domIluminacao = new DomIluminacao();


    }

    public void saveDomIluminacao(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        DomIluminacaoDAO domIluminacaoDAO = new DomIluminacaoDAO();  
        try {

            if (this.edit) {
                domIluminacaoDAO.saveIluminacao(this.domIluminacao);
                this.listaDomIluminacao = null;
                context.addMessage(null, new FacesMessage("Sucesso", "Alteração Efetuada !"));
            } else {

                if (listaDomIluminacao == null) {
                    listaDomIluminacao = domIluminacaoDAO.getIluminacaos();
                }
                if (this.listaDomIluminacao.contains(this.domIluminacao)) {
                    throw new Exception("Item " + this.domIluminacao.getNome() + " Já Existe");
                } else {
                    domIluminacaoDAO.saveIluminacao(this.domIluminacao);
                    this.listaDomIluminacao = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
                this.domIluminacao = null;
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editDomIluminacao(ActionEvent actionEvent) {
        this.edit = true;
        this.domIluminacao = (DomIluminacao) (this.listadmIluminacao.getRowData());

    }

    public void updateDomIluminacao() {
        context = FacesContext.getCurrentInstance();
       DomIluminacaoDAO domIluminacaoDAO = new DomIluminacaoDAO();  
        try {

            domIluminacaoDAO.updateIluminacao(domIluminacao);
            this.listaDomIluminacao = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelDomIluminacao() {
        this.domIluminacao = null;
    }
}
