/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.EnderecoDAO;
import dao.mci.CidadaoDAO;

import entity.mci.Cidadao;
import entity.Cidade;

import entity.Endereco;
import entity.Telefone;
import entity.mci.AnoDemanda;
import entity.mci.CidAssociados;
import entity.mci.RamoEmpreendimento;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCid;
import entity.mci.enumerator.EnumTipoPessoa;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
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
import org.primefaces.context.RequestContext;
import util.FacesUtils;

@ManagedBean
@ViewScoped
public class GruposBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String busca;
    private String tipoBusca ="cpf" ;
    private FacesUtils facesutils = new FacesUtils();
    private Cidadao cooperativa;
    private Cidadao cidadao;
    private CidAssociados cidassociados = new CidAssociados();
    private List<Cidade> listacidade = null;
    private List<Cidadao> listacid = null;
    private DataModel<Cidadao> dmLista = null;
    //private CidadaoDAO cidadaoDAO = new CidadaoDAO();        
    //private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private AnoDemanda anodemanda;
    private UIForm form;
    private Telefone telefone = new Telefone();
    private boolean buscacod = false;
    private boolean buscacpf = true;
    private boolean buscanomeresp = false;
    private boolean buscanome = false;

    public boolean isBuscacod() {
        return buscacod;
    }

    public void setBuscacod(boolean buscacod) {
        this.buscacod = buscacod;
    }

    public AnoDemanda getAnodemanda() {
        return anodemanda;
    }

    public void setAnodemanda(AnoDemanda anodemanda) {
        this.anodemanda = anodemanda;
    }

    
    
    

    public boolean isBuscacpf() {
        return buscacpf;
    }

    public void setBuscacpf(boolean buscacpf) {
        this.buscacpf = buscacpf;
    }

    public boolean isBuscanomeresp() {
        return buscanomeresp;
    }

    public void setBuscanomeresp(boolean buscanomeresp) {
        this.buscanomeresp = buscanomeresp;
    }

    public boolean isBuscanome() {
        return buscanome;
    }

    public void setBuscanome(boolean buscanome) {
        this.buscanome = buscanome;
    }
    
    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public GruposBean() {
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public UIForm getForm() {
        return form;
    }

    public void setForm(UIForm form) {
        this.form = form;
    }

    public String getTipoBusca() {
        return tipoBusca;
    }

    public void setTipoBusca(String tipoBusca) {
        this.tipoBusca = tipoBusca;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public CidAssociados getCidassociados() {
        return cidassociados;
    }

    public void setCidassociados(CidAssociados cidassociados) {
        this.cidassociados = cidassociados;
    }

    public Cidadao getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(Cidadao cooperativa) {
        this.cooperativa = cooperativa;
    }
    
    public void handleSelectBusca() {
      
        
         if ("cod".equals(this.tipoBusca)) {
            buscacod = true; 
            buscacpf = false;
            buscanome = false;
            buscanomeresp = false;
            this.busca = new String();
        }
         if ("nome".equals(this.tipoBusca)) {
          buscacod = false; 
            buscacpf = false;
            buscanome = true;
            buscanomeresp = false;
            this.busca = new String();
        }
        if ("cpf".equals(this.tipoBusca)) {
            buscacod = false; 
            buscacpf = true;
            buscanome = false;
            buscanomeresp = false;
            this.busca = new String();
        }
        if ("nomeresp".equals(this.tipoBusca)) {
            buscacod = false; 
            buscacpf = false;
            buscanome = false;
            buscanomeresp = true;
            this.busca = new String();
        }
       
    }

    public List<SelectItem> getSelectItemsCidade() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        if (listacidade != null) {
            for (Cidade cid : listacidade) {
                toReturn.add(new SelectItem(cid, cid.getNome()));
            }
        }
        return toReturn;
    }

    public List<Cidadao> getListaAssociados() {
        List lista = null;
        if (cooperativa.getAssociados() == null) {
            lista = cooperativa.getAssociados();
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public DataModel<Cidadao> getDataModelListaCoop() {
        dmLista = null;
        if (listacid != null) {
            listacid.toString();
            dmLista = new ListDataModel(listacid);
        }

        return dmLista;
    }

    public void localiza() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {
            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.busca, EnumTipoPessoa.GRUPO);
            }
            if ("cod".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListById(Integer.parseInt(busca),EnumTipoPessoa.GRUPO);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(busca,EnumTipoPessoa.GRUPO);
            }
            
             if ("nomeresp".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByNomeResp(this.busca, EnumTipoPessoa.GRUPO);
            }
            if (listacid.isEmpty()) {
                throw new Exception();
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }

    }



    public void selectEndereco() {
        EnderecoDAO edao = new EnderecoDAO();
        this.cooperativa.setEndereco(edao.getEndereco(this.cooperativa.getEndereco().getId()));

    }

    public String addTelefones() {
        if (this.cooperativa.getTelefones() == null) {
            this.cooperativa.setTelefones(new ArrayList<Telefone>());
        }

        this.cooperativa.getTelefones().add(this.telefone);
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        //cidadaoDAO.save(cooperativa);
        this.telefone = new Telefone();
        return null;
    }

    public String cancelCoop() {

        this.busca = null;
        this.listacid = null;
        this.cooperativa = null;
        this.cidassociados = null;
        dmLista = null;

        facesutils.cleanSubmittedValues(form);
        form.getChildren().clear();
        return "gotocadins";

    }

    public String addAssociado() {
        try {

            if (this.cidadao == null) {
                
                throw  new Exception("Cidadão Não Selecionado !!");

            } else {
                CidadaoDAO cidadaoDAO = new CidadaoDAO();
                this.setCidadao(cidadaoDAO.getEntity(this.cidadao.getId()));
                this.cidassociados.setAssociado(cidadao);
                this.cidassociados.setCidadao(cooperativa);
                if (this.cooperativa.getAssociados().contains(cidassociados)) {
                     throw  new Exception("Associado já existe !!");
                } else {
                    this.cooperativa.getAssociados().add(cidassociados);
                    this.cidadao.setStatuscid(EnumStatusCid.COLETIVO);
                    if (cooperativa.getBenstatus().equals(EnumStatusBeneficio.BENEFICIADO)) {
                        cidadao.setBenstatus(EnumStatusBeneficio.BENEFICIADO);
                    } else {
                        if (cooperativa.getBenstatus().equals(EnumStatusBeneficio.RESERVA)) {
                            cidadao.setBenstatus(EnumStatusBeneficio.RESERVA);
                        }
                    }

                    cidadaoDAO.save(cidadao);
                    cidadaoDAO.save(cooperativa);

                    facesutils.info("adicionado novo associado !!");
                }
                cidadao = null;
                this.cidassociados = new CidAssociados();
            }
        } catch (Exception e) {
            facesutils.erro(e.getMessage());
        }
        return null;
    }

    public void remAssociado() {

        CidadaoDAO cdao = new CidadaoDAO();

        //index = this.cooperativa.getAssociados().indexOf(cidassociados);      
        //  cidassociados.getAssociado().setStatuscid(EnumStatusCid.INDIVIDUAL);
        //this.cooperativa.getAssociados().set(index, cidassociados);     
        this.cooperativa.getAssociados().remove(cidassociados);
        Cidadao c = cdao.getEntity(cidassociados.getAssociado().getId());
        cdao.save(cooperativa);
        c.setStatuscid(EnumStatusCid.INDIVIDUAL);
        c.setBenstatus(EnumStatusBeneficio.RESERVA);

        cdao.save(c);

    }

    public void newAssociado(ActionEvent actionEvent) {

        this.cidassociados = new CidAssociados();
        

    }
        public void novo(ActionEvent actionEvent) {

        this.cooperativa = new Cidadao();
        this.anodemanda = new AnoDemanda();
        this.cooperativa.setBenstatus(EnumStatusBeneficio.RESERVA);
        this.cooperativa.setStatuscid(EnumStatusCid.COLETIVO);
        this.cidassociados = new CidAssociados();
        //this.cidadao = new Cidadao();
        this.telefone = new Telefone();
        this.cooperativa.setRepresentante(new Cidadao());

    }

    public void saveCoopNew(ActionEvent actionEvent) {
        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        boolean success;
       // this.cooperativa.setCpf(this.cooperativa.getCpf());
        
        if (cidadaoDAO.getListByCnp(this.cooperativa.getCpf(),EnumTipoPessoa.GRUPO).isEmpty()) {

            facesutils.cleanSubmittedValues(form);
            telefone = new Telefone();
            cooperativa.setEndereco(new Endereco());
            cooperativa.setTipopessoa(EnumTipoPessoa.GRUPO);
            cooperativa.setCpf(this.cooperativa.getRepresentante().getCpf());
            
            this.cooperativa.setRamoempreendimento(new RamoEmpreendimento());

            this.cidassociados = new CidAssociados();
            
            success = true;

        } else {
            facesutils.aviso("CPF Representante já Cadastrado " + this.cooperativa.getCpf() + " Já Existe ");
            success = false;
        }
        requestContext.addCallbackParam("success", success);
    }

    public void editCoop(ActionEvent actionEvent) {
        CidadaoDAO cdao = new CidadaoDAO();
        facesutils.cleanSubmittedValues(form);

        Cidadao c = (Cidadao) (this.dmLista.getRowData());
        this.cooperativa = cdao.getEntity(c.getId());
        this.cooperativa.getTelefones().toString();
        this.cidassociados = new CidAssociados();
        //this.cidadao = new Cidadao();

    }

    public String saveCoop(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {

            cidadaoDAO.save(cooperativa);
            //this.cooperativa = null;
            this.listacid = null;
            this.busca = new String();
            facesutils.info("Cadastro Efetuado");

        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! ");
            //System.out.println(e.getMessage());
            return null;

        } finally {

            facesutils.cleanSubmittedValues(form);

        }

        return "gotocadins";

    }

    public void saveCoopNext(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {

            cidadaoDAO.save(cooperativa);
            this.busca = new String();
            facesutils.info("Cadastro Efetuado");

        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! ");

        }

    }

    @SuppressWarnings("unchecked")
    public String imprimir() throws IOException, JRException {

        List<Cidadao> listausr = new ArrayList<Cidadao>();
        this.cooperativa = (Cidadao) (this.dmLista.getRowData());
        CidadaoDAO cdao = new CidadaoDAO();
        this.cooperativa = cdao.getEntity(this.cooperativa.getId());
        listausr.add(cooperativa);

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();
        String relJasper = scontext.getRealPath("/mci/cadastro/grupo/impressao/relfichacadastro.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=fichacadastro.pdf");
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

        return null;

    }

    public String imprimiritem() throws IOException, JRException {

        List<Cidadao> listausr = new ArrayList<Cidadao>();
        //  this.cooperativa = (Cidadao) (this.dmLista.getRowData());
        listausr.add(cooperativa);

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();
        String relJasper = scontext.getRealPath("/mci/cadastro/cooperativa/impressao/relcooperativa.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=relcooperativa.pdf");
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

        return null;

    }

    public void corretivoStatus() {

        try {
            CidadaoDAO cdao = new CidadaoDAO();
            List<Cidadao> listcid = cdao.getList("%", EnumTipoPessoa.COOP);
            for (Cidadao cid : listcid) {
                Cidadao c = cdao.getEntity(cid.getId());
                c.getAssociados().toString();

                if (c.getBenstatus().equals(EnumStatusBeneficio.BENEFICIADO)) {
                    for (CidAssociados assoc : c.getAssociados()) {
                        assoc.getAssociado().setBenstatus(EnumStatusBeneficio.BENEFICIADO);
                        cdao.save(c);

                    }

                }
            }
            facesutils.info("Correção Efetuada ");
        } catch (Exception e) {
            facesutils.erro("Correção Não Efetuado! " + e.getMessage());
        }

    }
}
