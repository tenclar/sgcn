/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.AtividadeDAO;
import dao.mci.RamoEmpreendimentoDAO;
import entity.mci.Atividade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class AtividadeBean implements Serializable {

    private FacesContext context;
    private DataModel listaAtividades;
    private Atividade atividade;
    private List<Atividade> listaAtividade = null;
    
    private boolean edit=false;

    @SuppressWarnings("unchecked")
    public DataModel getListaAtividades() {
      AtividadeDAO  atividadeDAO = new AtividadeDAO();
        if (listaAtividade == null) {
            listaAtividade = atividadeDAO.getAtividades();
        }
        listaAtividades = new ListDataModel(listaAtividade);
        return listaAtividades;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;


    }

    public void newAtividade(ActionEvent actionEvent) {

        this.atividade = new Atividade();


    }

    public void saveAtividade(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
       AtividadeDAO atividadeDAO = new AtividadeDAO();
        try {
            if (edit) {
                this.updateAtividade();
                edit = false;
            } else {
                if (listaAtividade == null) {
                    listaAtividade = atividadeDAO.getAtividades();
                }
                if (this.listaAtividade.contains(this.atividade)) {
                    throw new Exception("Já Existe");
                } else {
                    atividadeDAO.saveAtividade(this.atividade);
                    this.listaAtividade = null;
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editAtividade(ActionEvent actionEvent) {
        this.atividade = (Atividade) (this.listaAtividades.getRowData());
        edit=true;

    }

    public void updateAtividade() {
        context = FacesContext.getCurrentInstance();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        try {

            atividadeDAO.updateAtividade(atividade);
            this.listaAtividade = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelAtividade() {
        this.atividade = null;
    }
   
    
     @SuppressWarnings("unchecked")
    public void imprimeRelatorioWebTodos() throws IOException, JRException {
        List lista = new ArrayList();
        AtividadeDAO rdao = new AtividadeDAO();
        lista.addAll(rdao.getAtividades());
        try {
            context = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
            String relJasper = scontext.getRealPath("/mci/cadastro/relatorios/lista_atividades.jasper");
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            ServletOutputStream responseStream = response.getOutputStream();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
            
            Map parameters = new HashMap();
            response.setHeader("Content-Disposition", "inline; filename=impressao.pdf");
            response.setContentType("application/pdf");
            response.setHeader("Cache-Control", "no-cache");
            JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
            JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
            responseStream.flush();
            responseStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
}
