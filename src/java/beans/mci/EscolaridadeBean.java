/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.EscolaridadeDAO;
import entity.mci.Escolaridade;

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
public class EscolaridadeBean implements Serializable{
 
       
    private FacesContext context;
    private DataModel listaEscolaridades;
    private Escolaridade escolaridade;
    private List<Escolaridade> listaEscolaridade = null;
    
    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaEscolaridades() {
     EscolaridadeDAO   escolaridadesDAO = new EscolaridadeDAO();
        if (listaEscolaridade == null) {
            listaEscolaridade = escolaridadesDAO.getEscolaridades();
        }
        listaEscolaridades = new ListDataModel(listaEscolaridade);
        return listaEscolaridades;
    }
     public List<SelectItem> getSelectItems() {
      EscolaridadeDAO   escolaridadeDAO = new EscolaridadeDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        Escolaridade e = new Escolaridade();
        e.setId(0);
        e.setGrau("TODOS");
        toReturn.add(new SelectItem(e,e.getGrau()));
        for (Escolaridade p : escolaridadeDAO.getEscolaridades()) {
            toReturn.add(new SelectItem(p, p.getGrau()));
        }
        return toReturn;
    }
    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridades) {
        this.escolaridade = escolaridades;
    }

    public void newEscolaridade(ActionEvent actionEvent) {

        this.escolaridade = new Escolaridade();


    }

    public void saveEscolaridade(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        EscolaridadeDAO escolaridadesDAO = new EscolaridadeDAO();
        try {
            if (edit) {
                this.updateEscolaridade();
                edit = false;
            } else {
                if (listaEscolaridade == null) {
                    listaEscolaridade = escolaridadesDAO.getEscolaridades();
                }
                if (this.listaEscolaridade.contains(this.escolaridade)) {
                    throw new Exception(this.escolaridade.getGrau() + " Já Existe");
                } else {
                    escolaridadesDAO.saveEscolaridade(escolaridade);
                    this.listaEscolaridade = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editEscolaridade(ActionEvent actionEvent) {
        this.escolaridade = (Escolaridade) (this.listaEscolaridades.getRowData());
        this.edit = true;

    }

    public void updateEscolaridade() {
        context = FacesContext.getCurrentInstance();
       EscolaridadeDAO escolaridadesDAO = new EscolaridadeDAO();
        try {

            escolaridadesDAO.updateEscolaridade(escolaridade);
            this.listaEscolaridade = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Atualização Efetuada !"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelEscolaridade() {
        this.escolaridade = null;
    }
    
}
