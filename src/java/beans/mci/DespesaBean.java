/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.DespesaDAO;
import entity.mci.Despesa;
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
public class DespesaBean implements Serializable {

    private FacesContext context;
    private DataModel listaDespesas;
    private Despesa despesa;
    private List<Despesa> listaDespesa;
    
    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaDespesas() {
      DespesaDAO  despesaDAO = new DespesaDAO();
        listaDespesa = despesaDAO.getDespesas();
        listaDespesas = new ListDataModel(listaDespesa);
        return listaDespesas;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public void newDespesa(ActionEvent actionEvent) {

        this.despesa = new Despesa();


    }

    public void saveDespesa(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
      DespesaDAO  despesaDAO = new DespesaDAO();
        try {
            if (edit) {
                this.updateDespesa();
                edit = false;
            } else {
                if (listaDespesa == null) {
                    listaDespesa = despesaDAO.getDespesas();
                }
                if (this.listaDespesa.contains(this.despesa)) {
                    throw new Exception("Item " + this.despesa.getDescricao() + " Já Existe");
                } else {
                    despesaDAO.saveDespesa(this.despesa);
                    this.listaDespesa = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editDespesa(ActionEvent actionEvent) {
        this.despesa = (Despesa) (this.listaDespesas.getRowData());
        edit = true;

    }

    public void updateDespesa() {
        context = FacesContext.getCurrentInstance();
        DespesaDAO despesaDAO = new DespesaDAO();
        try {

            despesaDAO.updateDespesa(despesa);
            this.listaDespesa = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelDespesa() {
        this.despesa = null;
    }
}
