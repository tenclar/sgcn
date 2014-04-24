/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.CidadaoDAO;
import entity.mci.CidAssociados;
import entity.mci.Cidadao;
import entity.mci.CursosSecretaria;
import entity.mci.EquipamentosSecretaria;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumTipoEquipamento;
import entity.mci.enumerator.EnumTipoPessoa;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
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
import util.FacesUtils;

/**
 *
 * @author NOTE-04
 */
@ManagedBean
@ViewScoped
public class BeneficiadoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Cidadao cidadao;
    private List<Cidadao> listacid = null;
    private String tipoBusca = "nome";
    private String campoPesquisa;
    private EquipamentosSecretaria equipamentosecretaria;
    private CursosSecretaria cursosSecretaria;
    private FacesUtils facesutils = new FacesUtils();
    private List<EquipamentosSecretaria> listaEquip = null;
    private UIForm formBen;
    private DataModel dmLista = null;
    private String enableQtd = "false";
    private boolean editEquip = false;
    private Date dataentrega;
    private String codgrp;
    private List<EquipamentosSecretaria> filterLista;

    public BeneficiadoBean() {
        this.listacid = null;
        this.cidadao = null;
    }

    public List<EquipamentosSecretaria> getFilterLista() {
        return filterLista;
    }

    public void setFilterLista(List<EquipamentosSecretaria> filterLista) {
        this.filterLista = filterLista;
    }
    
    public String getEnableQtd() {
        return enableQtd;
    }

    public void setEnableQtd(String enableQtd) {
        this.enableQtd = enableQtd;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public UIForm getFormBen() {
        return formBen;
    }

    public void setFormBen(UIForm formBen) {
        this.formBen = formBen;
    }

    public String getCampoPesquisa() {
        return campoPesquisa;
    }

    public void setCampoPesquisa(String campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
    }

    public String getTipoBusca() {
        return tipoBusca;
    }

    public void setTipoBusca(String tipoBusca) {
        this.tipoBusca = tipoBusca;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public EquipamentosSecretaria getEquipamentosecretaria() {
        return equipamentosecretaria;
    }

    public void setEquipamentosecretaria(EquipamentosSecretaria equipamentosecretaria) {
        this.equipamentosecretaria = equipamentosecretaria;
    }

    public CursosSecretaria getCursosSecretaria() {
        return cursosSecretaria;
    }

    public void setCursosSecretaria(CursosSecretaria cursosSecretaria) {
        this.cursosSecretaria = cursosSecretaria;
    }

    public void equipSecrNew() {
        this.equipamentosecretaria = new EquipamentosSecretaria();
        this.equipamentosecretaria.setCodgrp(this.codgrp);
        this.equipamentosecretaria.setDataentrega(this.dataentrega);
        editEquip = false;
    }

    public void editEquip() {
        this.editEquip = true;
    }

    public void equipSecrAdd() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        if (editEquip == true) {
            cidadaoDAO.save(cidadao);
            this.equipamentosecretaria = null;
            editEquip = false;
        } else if (editEquip == false) {

            if (cidadao.getTipopessoa() == EnumTipoPessoa.CID) {
                this.cidadao.setBenstatus(EnumStatusBeneficio.BENEFICIADO);
                cidadaoDAO.save(cidadao);
            } else {
                if (cidadao.getTipopessoa() == EnumTipoPessoa.COOP) {
                    this.cidadao.setBenstatus(EnumStatusBeneficio.BENEFICIADO);
                    cidadaoDAO.save(cidadao);
                    for (CidAssociados a : this.cidadao.getAssociados()) {
                        //a.getAssociado().setBenstatus(EnumStatusBeneficio.COLETIVO);

                        Cidadao c = cidadaoDAO.getEntity(a.getAssociado().getId());
                        c.setBenstatus(EnumStatusBeneficio.BENEFICIADO);
                        cidadaoDAO.save(c);

                    }

                }
            }
            this.equipamentosecretaria.setCidadao(cidadao);
            this.cidadao.getEquipamentossecretarias().add(equipamentosecretaria);
            cidadaoDAO.save(cidadao);
            this.codgrp = this.equipamentosecretaria.getCodgrp();
            this.dataentrega = this.equipamentosecretaria.getDataentrega();
            this.equipamentosecretaria = null;
        }
    }

    public void equipRemove() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        cidadaoDAO.save(cidadao);
    }

    public void novoBeneficiado() {
        this.cidadao = new Cidadao();
    }

    public void handleTipoEquip() {
        if (this.equipamentosecretaria.getTipoequipamento() == EnumTipoEquipamento.CONSUMO) {
            this.enableQtd = "true";
        }
        if (this.equipamentosecretaria.getTipoequipamento() == EnumTipoEquipamento.PERMANENTE) {
            this.enableQtd = "false";
        }
    }

    public void saveBeneficiado(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {
            cidadaoDAO.save(cidadao);
            facesutils.info("Cadastro Efetuado!");
            this.cidadao = null;
            this.limpa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelBeneficiado() {
        this.cidadao = null;
        this.limpa();
    }

    public List getListaEquipamentos() {
        if (this.cidadao != null) {
            this.cidadao.getEquipamentossecretarias().toString();
            this.listaEquip = this.cidadao.getEquipamentossecretarias();
        }
        return this.listaEquip;
    }

    @SuppressWarnings("unchecked")
    public DataModel<Cidadao> getLista() {
        this.dmLista = null;
        if (this.listacid != null) {
            this.dmLista = new ListDataModel(listacid);
        }
        return this.dmLista;
    }

    public void localizaInd(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {

            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusBeneficio.BENEFICIADO);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusBeneficio.BENEFICIADO);
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
            this.campoPesquisa = new String();
        }

    }

    public void localizaCol(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {

            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.COOP, EnumStatusBeneficio.BENEFICIADO);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.COOP, EnumStatusBeneficio.BENEFICIADO);
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
            this.campoPesquisa = new String();
        }
    }

    public void editBen(ActionEvent actionEvent) {
        facesutils.cleanSubmittedValues(formBen);
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());
        if (cidadao.getTipopessoa() == EnumTipoPessoa.COOP) {
            this.cidadao.getAssociados().toString();
        }

    }

    public void selectCid() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());
        if (cidadao.getTipopessoa() == EnumTipoPessoa.COOP) {
            this.cidadao.getAssociados().toString();
        }
    }

    public void limpa() {
        this.listacid = null;
        this.campoPesquisa = null;

        this.facesutils.cleanSubmittedValues(formBen);

    }

    public void imprimir() throws IOException, JRException {

        List<Cidadao> listausr = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());

        listausr.add(cidadao);

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath("/mci/impressao/relcidadao.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=beneficiado.pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        JasperExportManager.exportReportToPdf(jasperPrint);
        //response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();
        this.cidadao = null;

    }

    public void imprimirtermo() throws IOException, JRException {

        List<Cidadao> listausr = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
//        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());
        this.cidadao.getTelefones().toString();
        listausr.add(cidadao);

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath("/mci/beneficio/impressao/termo/termo.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=termo.pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        JasperExportManager.exportReportToPdf(jasperPrint);
        //response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();
        this.cidadao = null;

    }

    public void imprimirtermoass() throws IOException, JRException {

        List<Cidadao> listausr = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
//        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());
        this.cidadao.getTelefones().toString();
        listausr.add(cidadao);

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath("/mci/beneficio/impressao/termo/termoass.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=termo.pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        JasperExportManager.exportReportToPdf(jasperPrint);
        //response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();
        this.cidadao = null;

    }

    public void imprimirequipind() throws IOException, JRException {

        List<Cidadao> listausr = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
//        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        listausr = cidadaoDAO.getListEquipamentosSecretaria(EnumTipoPessoa.CID);

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath("/mci/beneficio/impressao/equipamentos/equip_individual.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=lista_equip_ind.pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        JasperExportManager.exportReportToPdf(jasperPrint);
        //response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();
        this.cidadao = null;

    }

    public void imprimirequipcol() throws IOException, JRException {

        // List<Cidadao> listausr = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
//        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        List<Cidadao> listausr = cidadaoDAO.getListEquipamentosSecretaria(EnumTipoPessoa.COOP);

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath("/mci/beneficio/impressao/equipamentos/equip_coletivo.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=lista_equip_coletivo.pdf");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        JasperExportManager.exportReportToPdf(jasperPrint);
        //response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();
        this.cidadao = null;

    }
}
