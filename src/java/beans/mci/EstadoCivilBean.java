/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.EstadoCivilDAO;
import entity.mci.EstadoCivil;
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
public class EstadoCivilBean implements Serializable {
    
    
    private FacesContext context;
    private DataModel listaEstadoCivils;
    private EstadoCivil estadocivil;
    private List<EstadoCivil> listaEstadoCivil = null;
    
    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaEstadoCivils() {
      EstadoCivilDAO  necEspecialsDAO = new EstadoCivilDAO();
        if (listaEstadoCivil == null) {
            listaEstadoCivil = necEspecialsDAO.getEstadosCivis();
        }
        listaEstadoCivils = new ListDataModel(listaEstadoCivil);
        return listaEstadoCivils;
    }
 public List<SelectItem> getSelectItems() {
       EstadoCivilDAO  estadocivilDAO = new EstadoCivilDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (EstadoCivil p : estadocivilDAO.getEstadosCivis()) {
            toReturn.add(new SelectItem(p, p.getDescricao()));
        }
        return toReturn;
    }
    public EstadoCivil getEstadoCivil() {
        return estadocivil;
    }

    public void setEstadoCivil(EstadoCivil estadocivil) {
        this.estadocivil = estadocivil;
    }

    public void newEstadoCivil(ActionEvent actionEvent) {

        this.estadocivil = new EstadoCivil();


    }

    public void saveEstadoCivil(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
       EstadoCivilDAO necEspecialsDAO = new EstadoCivilDAO();
        try {
            if (edit) {
                this.updateEstadoCivil();
                edit = false;
            } else {
                if (listaEstadoCivil == null) {
                    listaEstadoCivil = necEspecialsDAO.getEstadosCivis();
                }
                if (this.listaEstadoCivil.contains(this.estadocivil)) {
                    throw new Exception(this.estadocivil.getDescricao() + " Já Existe");
                } else {
                    necEspecialsDAO.saveEstadoCivil(estadocivil);
                    this.listaEstadoCivil = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editEstadoCivil(ActionEvent actionEvent) {
        this.estadocivil = (EstadoCivil) (this.listaEstadoCivils.getRowData());
        this.edit = true;

    }

    public void updateEstadoCivil() {
        context = FacesContext.getCurrentInstance();
      EstadoCivilDAO  necEspecialsDAO = new EstadoCivilDAO();
        try {

            necEspecialsDAO.updateEstadoCivil(estadocivil);
            this.listaEstadoCivil = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Atualização Efetuada !"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelEstadoCivil() {
        this.estadocivil = null;
    }
    
}
