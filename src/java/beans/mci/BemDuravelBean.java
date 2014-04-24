/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.BemDuravelDAO;
import entity.mci.BemDuravel;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class BemDuravelBean implements Serializable {

    private FacesContext context;
    private DataModel listaBemDuravels;
    private BemDuravel bemDuravel;
    private List<BemDuravel> listaBemDuravel = null;
    
    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaBemDuravels() {
      BemDuravelDAO  bemDuravelDAO = new BemDuravelDAO();
        if (listaBemDuravel == null) {
            listaBemDuravel = bemDuravelDAO.getBemDuravels();
        }
        listaBemDuravels = new ListDataModel(listaBemDuravel);
        return listaBemDuravels;
    }

    public BemDuravel getBemDuravel() {
        return bemDuravel;
    }

    public void setBemDuravel(BemDuravel bemDuravel) {
        this.bemDuravel = bemDuravel;
    }

    public void newBemDuravel(ActionEvent actionEvent) {

        this.bemDuravel = new BemDuravel();


    }

    public void saveBemDuravel(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
      BemDuravelDAO  bemDuravelDAO = new BemDuravelDAO();
        try {
            if (edit) {
                this.updateBemDuravel();
                edit = false;
            } else {
                if (listaBemDuravel == null) {
                    listaBemDuravel = bemDuravelDAO.getBemDuravels();
                }
                if (this.listaBemDuravel.contains(this.bemDuravel)) {
                    throw new Exception("Já Existe");
                } else {
                    bemDuravelDAO.saveBemDuravel(this.bemDuravel);
                    this.listaBemDuravel = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }

            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editBemDuravel(ActionEvent actionEvent) {
        this.bemDuravel = (BemDuravel) (this.listaBemDuravels.getRowData());
        this.edit=true;

    }

    public void updateBemDuravel() {
        context = FacesContext.getCurrentInstance();
       BemDuravelDAO bemDuravelDAO = new BemDuravelDAO();
        try {

            bemDuravelDAO.updateBemDuravel(bemDuravel);
            this.listaBemDuravel = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelBemDuravel() {
        this.bemDuravel = null;
    }
}
