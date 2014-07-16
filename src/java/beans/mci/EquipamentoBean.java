package beans.mci;

import dao.mci.EquipamentoDAO;
import entity.mci.Equipamento;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import util.FacesUtils;
import util.RelatorioUtil;

@ManagedBean
@ViewScoped
public class EquipamentoBean implements Serializable {

    private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;
    private DataModel listaEquipamentos;
    private Equipamento equipamento;
    private List<Equipamento> listaEquipamento = null;
    private boolean edit = false;
    private String campoBusca;

    public void EquipamentoBean() {
    }

    public String getCampoBusca() {
        return campoBusca;
    }

    public void setCampoBusca(String campoBusca) {
        this.campoBusca = campoBusca;
    }
    
    @SuppressWarnings("unchecked")
    public DataModel getListaEquipamentos() {
        listaEquipamentos = null;

        if (listaEquipamento != null) {
        listaEquipamentos = new ListDataModel(listaEquipamento);
        }
        
        return listaEquipamentos;
    }

    public void localizar() {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        if ("".equals(this.campoBusca)) {
            listaEquipamento = equipamentoDAO.getEquipamentos();
        }else{
            listaEquipamento = equipamentoDAO.getEquipamentos(this.campoBusca);
        }
    }

    public List<SelectItem> getSelectItemsEquipamentos() {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Equipamento p : equipamentoDAO.getEquipamentos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }
        return toReturn;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void newEquipamento() {
        this.equipamento = new Equipamento();
    }

    public void saveEquipamento() {
        context = FacesContext.getCurrentInstance();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        try {
            if (edit) {
                this.updateEquipamento();
                edit = false;
            } else {
                this.listaEquipamento = equipamentoDAO.getEquipamentos();
                if (this.listaEquipamento.contains(this.equipamento)) {
                    throw new Exception("Já Existe");
                } else {
                    equipamentoDAO.saveEquipamento(this.equipamento);
                    this.listaEquipamento = null;
                    facesutils.info("Cadastro efetuado com sucesso");
                }
            }
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado!");
            System.out.println(e.getMessage());
        }
    }

    public void editEquipamento() {
        this.equipamento = (Equipamento) (this.listaEquipamentos.getRowData());
        edit = true;
    }

    public void updateEquipamento() {
        context = FacesContext.getCurrentInstance();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        try {
            equipamentoDAO.updateEquipamento(equipamento);
            this.listaEquipamento = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }
    }

    public void cancelEquipamento() {
        this.equipamento = null;
    }
    
    
    public void imprimeRelatorioWebTodos() throws IOException, JRException {
        
          EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        List lista =  equipamentoDAO.getEquipamentos();
        

        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_lista_equipamentos");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, "lista_equipamentos");

    }
}
