/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.RamoEmpreendimentoDAO;
import entity.mci.RamoEmpreendimento;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import util.RelatorioUtil;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class RamoEmpreendimentoBean implements Serializable {

    private FacesContext context;
    private DataModel listaRamoEmpreendimentos;
    private RamoEmpreendimento ramoEmpreendimento;
    private List<RamoEmpreendimento> listaRamoEmpreendimento = null;

    private boolean edit = false;

    @SuppressWarnings("unchecked")
    public DataModel getListaRamoEmpreendimentos() {
        RamoEmpreendimentoDAO ramoEmpreendimentoDAO = new RamoEmpreendimentoDAO();
        if (listaRamoEmpreendimento == null) {
            listaRamoEmpreendimento = ramoEmpreendimentoDAO.getRamoEmpreendimentos();
        }
        listaRamoEmpreendimentos = new ListDataModel(listaRamoEmpreendimento);
        return listaRamoEmpreendimentos;
    }

    public List<SelectItem> getSelectItemsRamo() {
        RamoEmpreendimentoDAO ramoEmpreendimentoDAO = new RamoEmpreendimentoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        RamoEmpreendimento rr = new RamoEmpreendimento();
        rr.setId(0); rr.setNome("TODOS");
        toReturn.add(new SelectItem(rr, rr.getNome()));
        for (RamoEmpreendimento r : ramoEmpreendimentoDAO.getRamoEmpreendimentos()) {
            toReturn.add(new SelectItem(r, r.getNome()));
        }
        return toReturn;
    }
    public List<SelectItem> getSelItemRamo() {
        RamoEmpreendimentoDAO ramoEmpreendimentoDAO = new RamoEmpreendimentoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        RamoEmpreendimento rr = new RamoEmpreendimento();
        
        toReturn.add(new SelectItem(rr, rr.getNome()));
        for (RamoEmpreendimento r : ramoEmpreendimentoDAO.getRamoEmpreendimentos()) {
            toReturn.add(new SelectItem(r, r.getNome()));
        }
        return toReturn;
    }

    public RamoEmpreendimento getRamoEmpreendimento() {
        return ramoEmpreendimento;
    }

    public void setRamoEmpreendimento(RamoEmpreendimento ramoEmpreendimento) {
        this.ramoEmpreendimento = ramoEmpreendimento;

    }

    public void newRamoEmpreendimento(ActionEvent actionEvent) {

        this.ramoEmpreendimento = new RamoEmpreendimento();

    }

    public void saveRamoEmpreendimento(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        RamoEmpreendimentoDAO ramoEmpreendimentoDAO = new RamoEmpreendimentoDAO();
        try {
            if (edit) {
                this.updateRamoEmpreendimento();
                edit = false;
            } else {
                if (listaRamoEmpreendimento == null) {
                    listaRamoEmpreendimento = ramoEmpreendimentoDAO.getRamoEmpreendimentos();
                }
                if (this.listaRamoEmpreendimento.contains(this.ramoEmpreendimento)) {
                    throw new Exception("Já Existe");
                } else {
                    ramoEmpreendimentoDAO.saveRamoEmpreendimento(this.ramoEmpreendimento);
                    this.listaRamoEmpreendimento = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void editRamoEmpreendimento(ActionEvent actionEvent) {
        this.ramoEmpreendimento = (RamoEmpreendimento) (this.listaRamoEmpreendimentos.getRowData());
        edit = true;

    }

    public void updateRamoEmpreendimento() {
        context = FacesContext.getCurrentInstance();
        RamoEmpreendimentoDAO ramoEmpreendimentoDAO = new RamoEmpreendimentoDAO();
        try {

            ramoEmpreendimentoDAO.updateRamoEmpreendimento(ramoEmpreendimento);
            this.listaRamoEmpreendimento = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelRamoEmpreendimento() {
        this.ramoEmpreendimento = null;
    }

    @SuppressWarnings("unchecked")
    public void imprimeRelatorioWebTodos() throws IOException, JRException {
        List lista = new ArrayList();
        RamoEmpreendimentoDAO rdao = new RamoEmpreendimentoDAO();
        lista.addAll(rdao.getRamoEmpreendimentos());

        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("urllistaramo");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, "lista_ramo");

    }
}
