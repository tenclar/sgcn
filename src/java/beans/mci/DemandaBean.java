/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.AnoDemandaDAO;
import entity.mci.AnoDemanda;
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
public class DemandaBean implements Serializable {

    private FacesContext context;
    private DataModel listaAnoDemandas;
    private AnoDemanda anodemanda;
    private List<AnoDemanda> listaAnoDemanda = null;
    
    private boolean edit=false;

     public List<SelectItem> getSelectItems() {
       AnoDemandaDAO  anodemandaDAO = new AnoDemandaDAO();
        
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
      
       
        for (AnoDemanda p : anodemandaDAO.getListStatus()) {
            Integer valor = p.getAno();
            toReturn.add(new SelectItem(valor,valor.toString()));
        }
        return toReturn;
    }
    @SuppressWarnings("unchecked")
    public DataModel getListaAnoDemandas() {
      AnoDemandaDAO  anodemandaDAO = new AnoDemandaDAO();
        if (listaAnoDemanda == null) {
            listaAnoDemanda = anodemandaDAO.getList();
        }
        listaAnoDemandas = new ListDataModel(listaAnoDemanda);
        return listaAnoDemandas;
    }

    public AnoDemanda getAnoDemanda() {
        return anodemanda;
    }

    public void setAnoDemanda(AnoDemanda anodemanda) {
        this.anodemanda = anodemanda;


    }

    public void newAnoDemanda(ActionEvent actionEvent) {

        this.anodemanda = new AnoDemanda();


    }

    public void saveAnoDemanda(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
       AnoDemandaDAO anodemandaDAO = new AnoDemandaDAO();
        try {
            if (edit) {
                this.updateAnoDemanda();
                edit = false;
            } else {
                if (listaAnoDemanda == null) {
                    listaAnoDemanda = anodemandaDAO.getList();
                }
                if (this.listaAnoDemanda.contains(this.anodemanda)) {
                    throw new Exception("Já Existe");
                } else {
                    anodemandaDAO.save(this.anodemanda);
                    this.listaAnoDemanda = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editAnoDemanda(ActionEvent actionEvent) {
        this.anodemanda = (AnoDemanda) (this.listaAnoDemandas.getRowData());
        edit=true;

    }

    public void updateAnoDemanda() {
        context = FacesContext.getCurrentInstance();
        AnoDemandaDAO anodemandaDAO = new AnoDemandaDAO();
        try {

            anodemandaDAO.update(anodemanda);
            this.listaAnoDemanda = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelAnoDemanda() {
        this.anodemanda = null;
    }
   
    
  
    
}
