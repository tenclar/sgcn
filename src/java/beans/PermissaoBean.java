/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PermissaoDAO;
import entity.Permissao;
import java.io.IOException;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import javax.faces.model.SelectItem;
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
public class PermissaoBean implements Serializable {

    // fonte : http://jamacedo.com/2011/01/crud-jsf-2-parte-3-seguna-com-spring-security-3/ 
    private static final long serialVersionUID = 1L;
    private FacesContext context;
    private Permissao permissao = null;
    private DataModel listaPermissaosDM;
    private List<Permissao> listaPermissao;
   // private PermissaoDAO permissaoDAO = new PermissaoDAO();

    public PermissaoBean() {
    }

    @SuppressWarnings("unchecked")
    public DataModel getListaPermissoes() {
        PermissaoDAO permissaoDAO = new PermissaoDAO();
        if (listaPermissao == null) {
            listaPermissao = permissaoDAO.getPermissaos();
        }
        listaPermissaosDM = new ListDataModel(listaPermissao);
        return listaPermissaosDM;
    }

    public List<SelectItem> getSelectItems() {
        PermissaoDAO dao = new PermissaoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Permissao p : dao.getPermissaos()) {
            toReturn.add(new SelectItem(p, p.getNome()));
        }

        return toReturn;

    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public void newPermissao(ActionEvent actionEvent) {

        this.permissao = new Permissao();


    }

    public void savePermissao(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        
        PermissaoDAO permissaoDAO = new PermissaoDAO();
       
        try {

            permissaoDAO.savePermissao(this.permissao);
            this.listaPermissao = null;
            
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));



        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editPermissao(ActionEvent actionEvent) {
        this.permissao = (Permissao) (this.listaPermissaosDM.getRowData());

    }

    public void updatePermissao() {
        context = FacesContext.getCurrentInstance();
        PermissaoDAO permissaoDAO = new PermissaoDAO();
        try {

            permissaoDAO.updatePermissao(this.permissao);
            this.listaPermissao = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelPermissao() {
        this.permissao = null;
    }
    
     @SuppressWarnings("unchecked")
    public void imprimeRelatorioWebTodos() throws IOException, JRException {
        List<Permissao> listausr = new ArrayList<Permissao>();
        PermissaoDAO permissaoDAO = new PermissaoDAO();
        listausr.addAll(permissaoDAO.getPermissaos());
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
            String relJasper = scontext.getRealPath("/admin/permissao/relatorio/permissao.jasper");
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            ServletOutputStream responseStream = response.getOutputStream();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
            Map parameters = new HashMap();
            response.setHeader("Content-Disposition", "inline; filename=relperm.pdf");
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
