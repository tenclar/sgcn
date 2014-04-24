/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.NecEspecialDAO;
import entity.mci.NecEspecial;
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
public class NecEspeciaisBean  implements Serializable{

    private FacesContext context;
    private DataModel listaNecEspecials;
    private NecEspecial necEspecials;
    private List<NecEspecial> listaNecEspecial = null;
    
    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaNecEspecials() {
      NecEspecialDAO  necEspecialsDAO = new NecEspecialDAO();
        if (listaNecEspecial == null) {
            listaNecEspecial = necEspecialsDAO.getNecEspecials();
        }
        listaNecEspecials = new ListDataModel(listaNecEspecial);
        return listaNecEspecials;
    }

    public NecEspecial getNecEspecial() {
        return necEspecials;
    }

    public void setNecEspecial(NecEspecial necEspecials) {
        this.necEspecials = necEspecials;
    }

    public void newNecEspecial(ActionEvent actionEvent) {

        this.necEspecials = new NecEspecial();


    }

    public void saveNecEspecial(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
       NecEspecialDAO necEspecialsDAO = new NecEspecialDAO();
        try {
            if (edit) {
                this.updateNecEspecial();
                edit = false;
            } else {
                if (listaNecEspecial == null) {
                    listaNecEspecial = necEspecialsDAO.getNecEspecials();
                }
                if (this.listaNecEspecial.contains(this.necEspecials)) {
                    throw new Exception(this.necEspecials.getDescricao() + " Já Existe");
                } else {
                    necEspecialsDAO.saveNecEspecial(necEspecials);
                    this.listaNecEspecial = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editNecEspecial(ActionEvent actionEvent) {
        this.necEspecials = (NecEspecial) (this.listaNecEspecials.getRowData());
        this.edit=true;

    }

    public void updateNecEspecial() {
        context = FacesContext.getCurrentInstance();
      NecEspecialDAO  necEspecialsDAO = new NecEspecialDAO();
        try {

            necEspecialsDAO.updateNecEspecial(necEspecials);
            this.listaNecEspecial = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Atualização Efetuada !"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelNecEspecial() {
        this.necEspecials = null;
    }
}
