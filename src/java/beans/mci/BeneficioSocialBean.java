/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.BeneficioSocialDAO;
import entity.mci.BeneficioSocial;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
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
public class BeneficioSocialBean implements Serializable {

    private FacesContext context;
    private DataModel listaBeneficioSocials;
    private BeneficioSocial beneficioSocial;
    private List<BeneficioSocial> listaBeneficioSocial = null;
    
    private boolean edit=false;

    public void BeneficioSocialBean() {
    }

    @SuppressWarnings("unchecked")
    public DataModel getListaBeneficioSocials() {
       BeneficioSocialDAO beneficioSocialDAO = new BeneficioSocialDAO();
        if (listaBeneficioSocial == null) {
            listaBeneficioSocial = beneficioSocialDAO.getBeneficioSocials();
        }
        listaBeneficioSocials = new ListDataModel(listaBeneficioSocial);
        return listaBeneficioSocials;
    }

    public List<SelectItem> getSelectItemsBeneficioSocial() {
       BeneficioSocialDAO  beneficioSocialDAO = new BeneficioSocialDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        if (listaBeneficioSocial == null);
        {
            listaBeneficioSocial = beneficioSocialDAO.getBeneficioSocials();
        }
        for (BeneficioSocial uf : listaBeneficioSocial) {
            toReturn.add(new SelectItem(uf, uf.getNome()));
        }
        return toReturn;
    }

    public BeneficioSocial getBeneficioSocial() {
        return beneficioSocial;
    }

    public void setBeneficioSocial(BeneficioSocial beneficioSocial) {
        this.beneficioSocial = beneficioSocial;
    }

    public void newBeneficioSocial() {

        this.beneficioSocial = new BeneficioSocial();



    }

    public void saveBeneficioSocial(ActionEvent actionEvent) {

        context = FacesContext.getCurrentInstance();
       BeneficioSocialDAO  beneficioSocialDAO = new BeneficioSocialDAO();
        try {
            if (edit) {
                this.updateBeneficioSocial();
                edit = false;
            } else {

                if (listaBeneficioSocial == null) {
                    listaBeneficioSocial = beneficioSocialDAO.getBeneficioSocials();
                }
                if (this.listaBeneficioSocial.contains(this.beneficioSocial)) {
                    throw new Exception(this.beneficioSocial.getNome() + " Já Existe");
                } else {
                    beneficioSocialDAO.saveBeneficioSocial(this.beneficioSocial);
                    this.listaBeneficioSocial = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", " Não Efetuado! " + e.getMessage()));
        }


    }

    public void editBeneficioSocial(ActionEvent actionEvent) {
        this.beneficioSocial = (BeneficioSocial) (this.listaBeneficioSocials.getRowData());
        edit = true;

    }

    public void updateBeneficioSocial() {
        context = FacesContext.getCurrentInstance();
      BeneficioSocialDAO  beneficioSocialDAO = new BeneficioSocialDAO();
        try {

            beneficioSocialDAO.updateBeneficioSocial(beneficioSocial);
            this.listaBeneficioSocial = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelBeneficioSocial() {
        this.beneficioSocial = null;
        
    }
}
