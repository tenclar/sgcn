/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mac;

import dao.mac.NatDespesaDAO;
import entity.mac.NatDespesa;
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
public class NatDespesaBean implements Serializable {

    private FacesContext context;
    private DataModel listaNatDespesas;
    private NatDespesa natDespesa;
    private List<NatDespesa> listaNatDespesa = null;
    private boolean insert;

    @SuppressWarnings("unchecked")
    public DataModel getListaNatDespesas() {
        NatDespesaDAO natDespesaDAO = new NatDespesaDAO();
        if (listaNatDespesa == null) {
            listaNatDespesa = natDespesaDAO.getNatDespesas();
        }
        listaNatDespesas = new ListDataModel(listaNatDespesa);
        return listaNatDespesas;
    }

    public NatDespesa getNatDespesa() {
        return natDespesa;
    }

    public void setNatDespesa(NatDespesa natDespesa) {
        this.natDespesa = natDespesa;

    }

    public void newNatDespesa(ActionEvent actionEvent) {

        this.natDespesa = new NatDespesa();
        this.insert = true;

    }

    public void saveNatDespesa(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();

        NatDespesaDAO natDespesaDAO = new NatDespesaDAO();

        try {
           

                if (listaNatDespesa == null) {
                    listaNatDespesa = natDespesaDAO.getNatDespesas();
                }
                if (this.listaNatDespesa.contains(this.natDespesa)) {
                   // throw new Exception("Já Existe");
                     context.addMessage(null, new FacesMessage("Sucesso", "Alteração Efetuada!"));
                } else {
                    natDespesaDAO.saveNatDespesa(this.natDespesa);
                    this.listaNatDespesa = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            
            }catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Cadastro Não Efetuado! "+e.getMessage()));  
        }

        }
    
    

    public void editNatDespesa(ActionEvent actionEvent) {
        this.natDespesa = (NatDespesa) (this.listaNatDespesas.getRowData());

    }

    public void updateNatDespesa() {
        context = FacesContext.getCurrentInstance();
        NatDespesaDAO natDespesaDAO = new NatDespesaDAO();
        try {

            natDespesaDAO.updateNatDespesa(natDespesa);
            this.listaNatDespesa = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelNatDespesa() {
        this.natDespesa = null;
    }

    public List<SelectItem> getSelectItems() {
        NatDespesaDAO natDespesaDAO = new NatDespesaDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (NatDespesa m : natDespesaDAO.getNatDespesas()) {
            toReturn.add(new SelectItem(m, m.getId() + "-" + m.getDescricao()));
        }
        return toReturn;
    }

}
