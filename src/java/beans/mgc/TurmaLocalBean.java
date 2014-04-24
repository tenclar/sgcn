package beans.mgc;

import dao.mgc.TurmaLocalDAO;
import entity.mgc.TurmaLocal;
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

@ManagedBean
@ViewScoped
public class TurmaLocalBean implements Serializable {

    private FacesContext context;
    private DataModel dmListaTurmaLocals;
    private TurmaLocal turmaLocal;
    private List<TurmaLocal> listaTurmaLocals = null;
    

    public void TurmaLocalBean() {
    }

    @SuppressWarnings("unchecked")
    public DataModel getListaTurmaLocals() {
        TurmaLocalDAO turmaLocalDAO = new TurmaLocalDAO();
        if (listaTurmaLocals == null) {
            listaTurmaLocals = turmaLocalDAO.getTurmaLocals();
        }
        dmListaTurmaLocals = new ListDataModel(listaTurmaLocals);
        return dmListaTurmaLocals;
    }

    public List<SelectItem> getSelectItemsTurmaLocal() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        TurmaLocalDAO turmaLocalDAO = new TurmaLocalDAO();
        for (TurmaLocal c : turmaLocalDAO.getTurmaLocals()) {
            toReturn.add(new SelectItem(c, c.getNome()));
        }
        return toReturn;
    }

    public TurmaLocal getTurmaLocal() {
        return turmaLocal;
    }

    public void setTurmaLocal(TurmaLocal turmaLocal) {
        this.turmaLocal = turmaLocal;
    }

    public void newTurmaLocal() {
        this.turmaLocal = new TurmaLocal();
    }

    public void saveTurmaLocal() {
        context = FacesContext.getCurrentInstance();
        TurmaLocalDAO turmaLocalDAO = new TurmaLocalDAO();
        try {
            if (listaTurmaLocals == null) {
                listaTurmaLocals = turmaLocalDAO.getTurmaLocals();
            }
            if (this.listaTurmaLocals.contains(this.turmaLocal)) {
                throw new Exception("Já Existe");
            } else {
                turmaLocalDAO.saveTurmaLocal(this.turmaLocal);
                this.listaTurmaLocals = null;
                context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }
    }

    public void editTurmaLocal(ActionEvent actionEvent) {
        this.turmaLocal = (TurmaLocal) (this.dmListaTurmaLocals.getRowData());
    }

    public void updateTurmaLocal() {
        context = FacesContext.getCurrentInstance();
        TurmaLocalDAO turmaLocalDAO = new TurmaLocalDAO();
        try {
            turmaLocalDAO.updateTurmaLocal(turmaLocal);
            this.listaTurmaLocals = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }
    }

    public void cancelTurmaLocal() {
        this.turmaLocal = null;
    }
}
