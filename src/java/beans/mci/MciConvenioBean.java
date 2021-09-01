/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.MciConvenioDAO;
import entity.mci.MciConvenio;
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
public class MciConvenioBean implements Serializable {

    private FacesContext context;
    private DataModel listaMciConvenios;
    private MciConvenio mciConvenio;
    private List<MciConvenio> listaMciConvenio = null;
    
    private boolean edit=false;

     public List<SelectItem> getSelectItems() {
       MciConvenioDAO  mciConvenioDAO = new MciConvenioDAO();
        
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
      
       
        for (MciConvenio p : mciConvenioDAO.getListStatus()) {            
            toReturn.add(new SelectItem(p,p.getNome()));
        }
        return toReturn;
    }
    @SuppressWarnings("unchecked")
    public DataModel getListaMciConvenios() {
      MciConvenioDAO  mciConvenioDAO = new MciConvenioDAO();
        if (listaMciConvenio == null) {
            listaMciConvenio = mciConvenioDAO.getList();
        }
        listaMciConvenios = new ListDataModel(listaMciConvenio);
        return listaMciConvenios;
    }

    public MciConvenio getMciConvenio() {
        return mciConvenio;
    }

    public void setMciConvenio(MciConvenio mciConvenio) {
        this.mciConvenio = mciConvenio;


    }

    public void newMciConvenio(ActionEvent actionEvent) {

        this.mciConvenio = new MciConvenio();


    }

    public void saveMciConvenio(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
       MciConvenioDAO mciConvenioDAO = new MciConvenioDAO();
        try {
            if (edit) {
                this.updateMciConvenio();
                edit = false;
            } else {
                if (listaMciConvenio == null) {
                    listaMciConvenio = mciConvenioDAO.getList();
                }
                if (this.listaMciConvenio.contains(this.mciConvenio)) {
                    throw new Exception("Já Existe");
                } else {
                    mciConvenioDAO.save(this.mciConvenio);
                    this.listaMciConvenio = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editMciConvenio(ActionEvent actionEvent) {
        this.mciConvenio = (MciConvenio) (this.listaMciConvenios.getRowData());
        edit=true;

    }

    public void updateMciConvenio() {
        context = FacesContext.getCurrentInstance();
        MciConvenioDAO mciConvenioDAO = new MciConvenioDAO();
        try {

            mciConvenioDAO.update(mciConvenio);
            this.listaMciConvenio = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelMciConvenio() {
        this.mciConvenio = null;
    }

    public List<MciConvenio> getListaMciConvenio() {
        return listaMciConvenio;
    }

    public void setListaMciConvenio(List<MciConvenio> listaMciConvenio) {
        this.listaMciConvenio = listaMciConvenio;
    }
   
    
  
    
}
