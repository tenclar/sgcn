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
import entity.mci.enumerator.EnumTipoRecurso;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import net.sf.jasperreports.engine.JRException;
import util.FacesUtils;
import util.RelatorioUtil;

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
    private final FacesUtils facesutils = new FacesUtils();
    private List<EquipamentosSecretaria> listaEquip = null;
    private UIForm formBen;
    private DataModel dmLista = null;
    private String enableQtd = "false";
    private String enableConv = "false";
    private boolean editEquip = false;
    private Date dataentrega;
    private String codgrp;
    private List<EquipamentosSecretaria> filterLista;

    private String busca;
    private boolean buscacod = false;
    private boolean buscacpf = true;
    private boolean buscanomeresp = false;
    private boolean buscanome = false;
    
    public BeneficiadoBean() {
        this.listacid = null;
        this.cidadao = null;
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
                if ((cidadao.getTipopessoa() == EnumTipoPessoa.COOP) || (cidadao.getTipopessoa() == EnumTipoPessoa.GRUPO)) {
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
            Cidadao c = cidadaoDAO.getEntity(cidadao.getId());
            this.equipamentosecretaria.setCidadao(c);
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
    public void handleTipoRecurso() {
        if (this.equipamentosecretaria.getTipoRecurso() == EnumTipoRecurso.CONVENIO) {
            this.enableConv = "true";
        }
        if (this.equipamentosecretaria.getTipoRecurso() == EnumTipoRecurso.RP) {
            this.enableConv = "false";
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
    public void localizaGrupo() {
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

    public void editBen() {
        facesutils.cleanSubmittedValues(formBen);
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        Cidadao c = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(c.getId());
        if (cidadao.getTipopessoa() != EnumTipoPessoa.CID) {
            this.cidadao.getAssociados().toString();
        }
        cidadao.getCidbBenSociais().toString();
    }

    public void selectCid() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        Cidadao c = this.cidadao;
        this.cidadao = cidadaoDAO.getEntity(c.getId());
        if (cidadao.getTipopessoa() != EnumTipoPessoa.CID) {
            this.cidadao.getAssociados().toString();
        }
    }

    public void limpa() {
        this.listacid = null;
        this.campoPesquisa = null;

        //this.facesutils.cleanSubmittedValues(formBen);
    }
    
    //GRUPOS
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

    public void imprimir() throws IOException, JRException {

        List<Cidadao> lista = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());
        lista.add(cidadao);
        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("urlficha");
        String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nomeficha");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, nomerelatorio);

    }

    public void imprimirtermo() throws IOException, JRException {

        List<Cidadao> lista = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
//        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());
        this.cidadao.getTelefones().toString();
        lista.add(cidadao);

        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("urltermoindividual");
        String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nometermoindividual");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, nomerelatorio);

    }

    public void imprimirtermoass() throws IOException, JRException {

        List<Cidadao> lista = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
//        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());
        this.cidadao.getTelefones().toString();
        lista.add(cidadao);

        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_termo_coletivo");
        String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nome_termo_coletivo");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, nomerelatorio);

    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public boolean isBuscacod() {
        return buscacod;
    }

    public void setBuscacod(boolean buscacod) {
        this.buscacod = buscacod;
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

    public String getEnableConv() {
        return enableConv;
    }

    public void setEnableConv(String enableConv) {
        this.enableConv = enableConv;
    }
    
    
}
