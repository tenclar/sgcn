/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.ParentescoDAO;
import entity.mci.Parentesco;
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

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class ParentescoBean implements Serializable {

    private FacesContext context;
    private DataModel listaParentescos;
    private Parentesco parentesco;
    private List<Parentesco> listaParentesco = null;
    
    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaParentescos() {
    ParentescoDAO    parentescoDAO = new ParentescoDAO();
        if (listaParentesco == null) {
            listaParentesco = parentescoDAO.getParentescos();
        }
        listaParentescos = new ListDataModel(listaParentesco);
        return listaParentescos;
    }

    public List<SelectItem> getSelectItems() {
      ParentescoDAO  parentescoDAO = new ParentescoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Parentesco p : parentescoDAO.getParentescos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;


    }

    public void newParentesco(ActionEvent actionEvent) {

        this.parentesco = new Parentesco();


    }

    public void saveParentesco(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
       ParentescoDAO parentescoDAO = new ParentescoDAO();
        try {
            if (edit) {
                this.updateParentesco();
                edit = false;
            } else {
                if (listaParentesco == null) {
                    listaParentesco = parentescoDAO.getParentescos();
                }
                if (this.listaParentesco.contains(this.parentesco)) {
                    throw new Exception(this.parentesco.getNome()+" Já Existe");
                } else {
                    parentescoDAO.saveParentesco(this.parentesco);
                    this.listaParentesco = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }

            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editParentesco(ActionEvent actionEvent) {
        this.parentesco = (Parentesco) (this.listaParentescos.getRowData());
        this.edit = true;

    }

    public void updateParentesco() {
        context = FacesContext.getCurrentInstance();
       ParentescoDAO parentescoDAO = new ParentescoDAO();
        try {

            parentescoDAO.updateParentesco(parentesco);
            this.listaParentesco = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelParentesco() {
        this.parentesco = null;
    }
}
