/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mpm;

import dao.mci.CidadaoDAO;
import dao.mpm.PlanoDAO;
import entity.mci.*;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumTipoPessoa;
import entity.mpm.HistoricoVisita;
import entity.mpm.MetasEmpresariais;
import entity.mpm.MetasPessoais;
import entity.mpm.MicroLocalInvest;
import entity.mpm.MicroLocalInvestFin;
import entity.mpm.Plano;
import entity.mpm.PontosFortesExternos;
import entity.mpm.PontosFortesInternos;
import entity.mpm.PontosFracosExternos;
import entity.mpm.PontosFracosInternos;
import entity.mpm.Produtos;
import entity.mpm.Rendimento;
import entity.mpm.Servicos;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import util.FacesUtils;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class PlanoBean implements Serializable {

    private FacesUtils facesutils = new FacesUtils();
    private String campoBusca;
    private DataModel listaPlanos = null;
    private Plano plano;
    private Produtos produtos;
    private Servicos servicos;
    private Rendimento rendimento;
    private MetasEmpresariais metasempresarais;
    private MetasPessoais metaspessoais;
    private PontosFracosInternos pontosfracosinternos;
    private PontosFracosExternos pontosfracosexternos;
    private PontosFortesInternos pontosfortesinternos;
    private PontosFortesExternos pontosfortesexternos;
    private MicroLocalInvest microlocalinvest;
    private MicroLocalInvestFin microlocalinvesfin;
    private HistoricoVisita historicovisita;
    private Panel panelPlano;
    private Panel panelMicro;
    private Panel panelFin;
    private Panel panelFinInv;
    private List<Plano> listaPlano = null;
    private String tipoBusca = "cpf";
    private CidBensDuraveis cidBensDuraveis;
    private BensDuraveis bensDuraveis;
    private Domicilio domicilio;
    private CursosSecretaria cursossecretaria;
    private boolean editar = false;
    private boolean mon = true;
    private boolean buscacpf = true;
    private boolean buscanomeresp = false;
    private boolean buscanome = false;
    private boolean inserirHist = false;

    public PlanoBean() {
    }

    public HistoricoVisita getHistoricovisita() {
        return historicovisita;
    }

    public void setHistoricovisita(HistoricoVisita historicovisita) {
        this.historicovisita = historicovisita;
    }

    public boolean isBuscacpf() {
        return buscacpf;
    }

    public void setBuscacpf(boolean buscacpf) {
        this.buscacpf = buscacpf;
    }

    public boolean isBuscanome() {
        return buscanome;
    }

    public void setBuscanome(boolean buscanome) {
        this.buscanome = buscanome;
    }

    public boolean isBuscanomeresp() {
        return buscanomeresp;
    }

    public void setBuscanomeresp(boolean buscanomeresp) {
        this.buscanomeresp = buscanomeresp;
    }

    public CursosSecretaria getCursossecretaria() {
        return cursossecretaria;
    }

    public void setCursossecretaria(CursosSecretaria cursossecretaria) {
        this.cursossecretaria = cursossecretaria;
    }

    public String getTipoBusca() {
        return tipoBusca;
    }

    public void setTipoBusca(String tipoBusca) {
        this.tipoBusca = tipoBusca;
    }

    public String getCampoBusca() {
        return campoBusca;
    }

    public void setCampoBusca(String campoBusca) {
        this.campoBusca = campoBusca;
    }

    public Panel getPanelPlano() {
        return panelPlano;
    }

    public void setPanelPlano(Panel panelPlano) {
        this.panelPlano = panelPlano;
    }

    public Panel getPanelFin() {
        return panelFin;
    }

    public void setPanelFin(Panel panelFin) {
        this.panelFin = panelFin;
    }

    public Panel getPanelMicro() {
        return panelMicro;
    }

    public void setPanelMicro(Panel panelMicro) {
        this.panelMicro = panelMicro;
    }

    public Panel getPanelFinInv() {
        return panelFinInv;
    }

    public void setPanelFinInv(Panel panelFinInv) {
        this.panelFinInv = panelFinInv;
    }

    public MicroLocalInvestFin getMicrolocalinvesfin() {
        return microlocalinvesfin;
    }

    public void setMicrolocalinvesfin(MicroLocalInvestFin microlocalinvesfin) {
        this.microlocalinvesfin = microlocalinvesfin;
    }

    public MicroLocalInvest getMicrolocalinvest() {
        return microlocalinvest;
    }

    public void setMicrolocalinvest(MicroLocalInvest microlocalinvest) {
        this.microlocalinvest = microlocalinvest;
    }

    public MetasPessoais getMetaspessoais() {
        return metaspessoais;
    }

    public void setMetaspessoais(MetasPessoais metaspessoais) {
        this.metaspessoais = metaspessoais;
    }

    public MetasEmpresariais getMetasempresarais() {
        return metasempresarais;
    }

    public void setMetasempresarais(MetasEmpresariais metasempresarais) {
        this.metasempresarais = metasempresarais;
    }

    public Rendimento getRendimento() {
        return rendimento;
    }

    public void setRendimento(Rendimento rendimento) {
        this.rendimento = rendimento;
    }

    public PontosFortesExternos getPontosfortesexternos() {
        return pontosfortesexternos;
    }

    public void setPontosfortesexternos(PontosFortesExternos pontosfortesexternos) {
        this.pontosfortesexternos = pontosfortesexternos;
    }

    public PontosFortesInternos getPontosfortesinternos() {
        return pontosfortesinternos;
    }

    public void setPontosfortesinternos(PontosFortesInternos pontosfortesinternos) {
        this.pontosfortesinternos = pontosfortesinternos;
    }

    public PontosFracosInternos getPontosfracosinternos() {
        return pontosfracosinternos;
    }

    public void setPontosfracosinternos(PontosFracosInternos pontosfracosinternos) {
        this.pontosfracosinternos = pontosfracosinternos;
    }

    public PontosFracosExternos getPontosfracosexternos() {
        return pontosfracosexternos;
    }

    public void setPontosfracosexternos(PontosFracosExternos pontosfracosExternos) {
        this.pontosfracosexternos = pontosfracosExternos;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Servicos getServicos() {
        return servicos;
    }

    public void setServicos(Servicos servicos) {
        this.servicos = servicos;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @SuppressWarnings("unchecked")
    public DataModel getListaPlanos() {
        listaPlanos = null;
        if (listaPlano != null) {
            listaPlanos = new ListDataModel(listaPlano);
        }

        return listaPlanos;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public BensDuraveis getBensDuraveis() {
        return bensDuraveis;
    }

    public void setBensDuraveis(BensDuraveis bensDuraveis) {
        this.bensDuraveis = bensDuraveis;
    }

    public CidBensDuraveis getCidBensDuraveis() {
        return cidBensDuraveis;
    }

    public void setCidBensDuraveis(CidBensDuraveis cidBensDuraveis) {
        this.cidBensDuraveis = cidBensDuraveis;
    }

    public void localiza() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        PlanoDAO planoDAO = new PlanoDAO();
        if ("nomeben".equals(this.tipoBusca)) {
            listaPlano = planoDAO.getListByPlanoNameBeneficiado(campoBusca, EnumTipoPessoa.CID);
            if (listaPlano.isEmpty()) {
                mon = false;
            } else {
                mon = true;
            }
        }
        if ("nome".equals(this.tipoBusca)) {
            listaPlano = planoDAO.getListByPlanoName(campoBusca, EnumTipoPessoa.CID);
            if (listaPlano.isEmpty()) {
                mon = false;
            } else {
                mon = true;
            }
        }
        if ("cpf".equals(this.tipoBusca)) {
            listaPlano = planoDAO.getListByRespCnp(campoBusca, EnumTipoPessoa.CID);
            if (listaPlano.isEmpty()) {
                mon = false;
            } else {
                mon = true;
            }

        }
        requestContext.addCallbackParam("mon", mon);

    }

    public void localizaCoop(ActionEvent actionEvent) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        PlanoDAO planoDAO = new PlanoDAO();

        if ("nome".equals(this.tipoBusca)) {
            listaPlano = planoDAO.getListByPlanoName(campoBusca, EnumTipoPessoa.COOP);

        }else
        if ("emp".equals(this.tipoBusca)) {
            listaPlano = planoDAO.getListByRespName(tipoBusca, EnumTipoPessoa.COOP);

        }else
        if ("cpf".equals(this.tipoBusca)) {
            listaPlano = planoDAO.getListByRespCnp(campoBusca, EnumTipoPessoa.COOP);

        }else
        if (listaPlano.isEmpty()) {
            mon = false;
        } else {
            mon = true;
        }

        requestContext.addCallbackParam("mon", mon);
    }

    public void newPlano() {
        this.plano = new Plano();
    }

    public void savePlanoNew() {
        PlanoDAO planoDAO = new PlanoDAO();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        boolean success;

        if (planoDAO.getListByRespCnp(this.plano.getCidadao().getCpf()).isEmpty()) {
            success = false;

            facesutils.cleanSubmittedValues(panelPlano);
            this.panelFin.setVisible(false);
            this.panelFinInv.setVisible(false);
            this.panelMicro.setVisible(false);
            //this.plano.getCidadao().setBenstatus(EnumStatusBeneficio.INDIVIDUAL);
            this.plano.setProdutos(new ArrayList<Produtos>());
            this.plano.setServicos(new ArrayList<Servicos>());
            this.plano.setPontosfortesexternos(new ArrayList<PontosFortesExternos>());
            this.plano.setPontosfortesinternos(new ArrayList<PontosFortesInternos>());

            this.plano.setPontosfracosexternos(new ArrayList<PontosFracosExternos>());
            this.plano.setPontosfracosinternos(new ArrayList<PontosFracosInternos>());
            this.plano.setMetasempresariais(new ArrayList<MetasEmpresariais>());
            this.plano.setMetaspessoais(new ArrayList<MetasPessoais>());
            //this.plano.setRendimentos(new ArrayList<Rendimento>());

            this.produtos = new Produtos();
            this.servicos = new Servicos();
            this.metasempresarais = new MetasEmpresariais();
            this.metaspessoais = new MetasPessoais();
            this.pontosfortesexternos = new PontosFortesExternos();
            this.pontosfortesinternos = new PontosFortesInternos();
            this.pontosfracosexternos = new PontosFracosExternos();
            this.pontosfracosinternos = new PontosFracosInternos();
            this.rendimento = new Rendimento();

            this.microlocalinvesfin = new MicroLocalInvestFin();
            this.microlocalinvest = new MicroLocalInvest();
            //planoDAO.savePlano(this.plano);
        } else {
            success = true;
            facesutils.aviso("Inclusão não efetudada,  CPF: " + this.plano.getCidadao().getCpf() + " Já Existe ");
        }

        requestContext.addCallbackParam("success", success);

    }

    public void savePlanoCoop() {
        PlanoDAO planoDAO = new PlanoDAO();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        boolean success;

        if (planoDAO.getListByRespCnp(this.plano.getCidadao().getCpf()).isEmpty()) {
            success = false;
            facesutils.cleanSubmittedValues(panelPlano);
            this.panelFin.setVisible(false);
            this.panelFinInv.setVisible(false);
            this.panelMicro.setVisible(false);
            //this.plano.getCidadao().setBenstatus(EnumStatusBeneficio.COLETIVO);
            this.plano.setProdutos(new ArrayList<Produtos>());
            this.plano.setServicos(new ArrayList<Servicos>());
            this.plano.setPontosfortesexternos(new ArrayList<PontosFortesExternos>());
            this.plano.setPontosfortesinternos(new ArrayList<PontosFortesInternos>());

            this.plano.setPontosfracosexternos(new ArrayList<PontosFracosExternos>());
            this.plano.setPontosfracosinternos(new ArrayList<PontosFracosInternos>());
            this.plano.setMetasempresariais(new ArrayList<MetasEmpresariais>());
            this.plano.setMetaspessoais(new ArrayList<MetasPessoais>());
            //this.plano.setRendimentos(new ArrayList<Rendimento>());

            this.produtos = new Produtos();
            this.servicos = new Servicos();
            this.metasempresarais = new MetasEmpresariais();
            this.metaspessoais = new MetasPessoais();
            this.pontosfortesexternos = new PontosFortesExternos();
            this.pontosfortesinternos = new PontosFortesInternos();
            this.pontosfracosexternos = new PontosFracosExternos();
            this.pontosfracosinternos = new PontosFracosInternos();
            this.rendimento = new Rendimento();

            this.microlocalinvesfin = new MicroLocalInvestFin();
            this.microlocalinvest = new MicroLocalInvest();
            //planoDAO.savePlano(this.plano);
        } else {
            success = true;
            listaPlano = planoDAO.getListByRespCnp(this.plano.getCidadao().getCpf());
            //facesutils.aviso("Inclusão não efetudada,  CPF: " + this.plano.getCidadao().getCpf() + " Já Existe ");
        }

        requestContext.addCallbackParam("success", success);

    }

    public void saveProx() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        Cidadao cidadao = cidadaoDAO.getEntity(plano.getCidadao().getId());

        if (cidadao.getTipopessoa() == EnumTipoPessoa.CID) {
            cidadao.setBenstatus(EnumStatusBeneficio.BENEFICIADO);
        }
        if (cidadao.getTipopessoa() == EnumTipoPessoa.COOP) {
            cidadao.setBenstatus(EnumStatusBeneficio.BENEFICIADO);
            for (CidAssociados a : cidadao.getAssociados()) {
                a.getAssociado().setBenstatus(EnumStatusBeneficio.BENEFICIADO);
                cidadaoDAO.save(cidadao);
            }
        }

        PlanoDAO planoDAO = new PlanoDAO();
        planoDAO.savePlano(this.plano);

    }

    public void savePlano() {
        try {
            PlanoDAO planoDAO = new PlanoDAO();
            planoDAO.savePlano(this.plano);
            this.listaPlano = null;
            facesutils.info("Salvo com sucesso");

        } catch (Exception e) {
            facesutils.erro(" Não Efetuado! " + e.getMessage());
            System.out.println(e.getMessage());

        } finally {
            // facesutils.cleanSubmittedValues(panelPlano);
        }

    }

    public void save() {
        PlanoDAO planoDAO = new PlanoDAO();
        planoDAO.savePlano(this.plano);
    }

    public void editPlano() {

        PlanoDAO planoDAO = new PlanoDAO();
        //facesutils.cleanSubmittedValues(panelPlano);
        Plano p = (Plano) (this.listaPlanos.getRowData());
        this.plano = planoDAO.getPlano(p.getId());
        plano.getMicrocredito().getMicrolocaisinvestidos().toString();
        plano.getMicrocredito().getMicrolocaisinvestfin().toString();
        plano.getCidadao().getHistdomicilio().toString();
        plano.getCidadao().getCidbensduraveis().toString();
        plano.getCidadao().getCidBenHabits().toString();
        plano.getCidadao().getCidbBenSociais().toString();
        plano.getCidadao().getCursosSecretarias();
        plano.getCidadao().getEquipamentossecretarias();
        
        this.produtos = new Produtos();
        this.servicos = new Servicos();
        this.metasempresarais = new MetasEmpresariais();
        this.metaspessoais = new MetasPessoais();
        this.pontosfortesexternos = new PontosFortesExternos();
        this.pontosfortesinternos = new PontosFortesInternos();
        this.pontosfracosexternos = new PontosFracosExternos();
        this.pontosfracosinternos = new PontosFracosInternos();
        this.rendimento = new Rendimento();

        //this.microlocalinvesfin = new MicroLocalInvestFin();
        //this.microlocalinvest = new MicroLocalInvest();
        
        this.handleRadioFinInv2();
        this.handleRadioMicro2();

    }

    public void updatePlano() {
        PlanoDAO planoDAO = new PlanoDAO();
        try {

            planoDAO.updatePlano(plano);
            this.listaPlano = null;
            facesutils.info("Cadastro Efetudado !");
        } catch (Exception e) {
            facesutils.erro("Não foi possível cadastrar!");
            facesutils.erro(e.getMessage());
        }

    }

    public void cancelPlanoNew() {
        this.plano = null;
    }

    public void cancelPlano() {
        this.plano = null;
        this.campoBusca = null;
        listaPlano = null;
        facesutils.cleanSubmittedValues(panelPlano);

    }

    public List<Produtos> getListaProdutos() {
        List<Produtos> prod = new ArrayList<Produtos>();
        if (this.plano != null) {
            prod = this.plano.getProdutos();
        }
        return prod;
    }

    public String addProduto(ActionEvent actionEvent) {
        this.produtos.setPlano(plano);
        this.plano.getProdutos().add(produtos);
        this.produtos = new Produtos();
        this.save();
        facesutils.info(" Produto incluido ");
        return null;
    }

    public String addServico() {
        this.servicos.setPlano(plano);
        this.servicos = new Servicos();
        this.save();
        facesutils.info(" Serviço incluido ");
        return null;
    }

    public String addPontoFracoInterno() {

        this.pontosfracosinternos.setPlano(plano);
        this.plano.getPontosfracosinternos().add(pontosfracosinternos);
        this.pontosfracosinternos = new PontosFracosInternos();
        facesutils.info(" Ponto Fraco Interno incluido ");
        this.save();
        return null;
    }

    public String addPontoFracoExterno() {
        this.pontosfracosexternos.setPlano(plano);
        this.plano.getPontosfracosexternos().add(pontosfracosexternos);
        this.pontosfracosexternos = new PontosFracosExternos();
        this.save();
        facesutils.info(" Ponto Fraco Externo incluido ");
        return null;
    }

    public String addPontoForteExterno() {
        this.pontosfortesexternos.setPlano(plano);
        this.plano.getPontosfortesexternos().add(pontosfortesexternos);
        this.pontosfortesexternos = new PontosFortesExternos();
        facesutils.info(" Ponto Forte externo incluido ");
        this.save();
        return null;
    }

    public String addPontoForteInterno() {
        this.pontosfortesinternos.setPlano(plano);
        this.plano.getPontosfortesinternos().add(pontosfortesinternos);
        this.pontosfortesinternos = new PontosFortesInternos();
        facesutils.info(" Ponto Fortes Interno incluido ");
        this.save();
        return null;
    }

    public String addMetaPes() {
        this.metaspessoais.setPlano(plano);
        this.plano.getMetaspessoais().add(metaspessoais);
        this.metaspessoais = new MetasPessoais();
        this.save();
        facesutils.info(" Metas pessoais incluido ");
        return null;
    }

    public String addMetaEmp() {
        this.metasempresarais.setPlano(plano);
        this.plano.getMetasempresariais().add(metasempresarais);
        this.metasempresarais = new MetasEmpresariais();
        this.save();
        facesutils.info(" Metas empresariais incluido ");
        return null;
    }

    public String addRend() {
        this.rendimento.setPlano(plano);
        this.plano.getRendimentos().add(rendimento);
        this.rendimento = new Rendimento();
        this.save();
        facesutils.info(" rendimento incluido ");
        return null;
    }

    public String addLocalMicro() {

        this.microlocalinvest.setMicrocredito(this.plano.getMicrocredito());
        if (this.plano.getMicrocredito().getMicrolocaisinvestidos() == null) {
            this.plano.getMicrocredito().setMicrolocaisinvestidos(new ArrayList<MicroLocalInvest>());
        }

        this.plano.getMicrocredito().getMicrolocaisinvestidos().add(microlocalinvest);
        this.microlocalinvest = new MicroLocalInvest();
        this.save();
        facesutils.info(" Local Investido incluido ");
        return null;
    }

    public String addLocalMicroFin() {
        this.microlocalinvesfin.setMicrocredito(this.plano.getMicrocredito());
        if (this.plano.getMicrocredito().getMicrolocaisinvestfin() == null) {
            this.plano.getMicrocredito().setMicrolocaisinvestfin(new ArrayList<MicroLocalInvestFin>());
        }

        this.plano.getMicrocredito().getMicrolocaisinvestfin().add(microlocalinvesfin);
        this.microlocalinvesfin = new MicroLocalInvestFin();
        this.save();
        facesutils.info(" Local de Investimento incluido ");
        return null;
    }

    public void handleRadioMicro() {
        String valor = this.plano.getMicrocredito().getRecebeu();
        if ("SIM".equals(valor)) {
            if (this.plano.getMicrocredito().getMicrolocaisinvestfin() == null) {
                this.plano.getMicrocredito().setMicrolocaisinvestfin(new ArrayList<MicroLocalInvestFin>());
            } else {
                this.plano.getMicrocredito().getMicrolocaisinvestfin().clear();
            }
            panelMicro.setRendered(true);
            panelFin.setRendered(false);
            this.microlocalinvest = new MicroLocalInvest();

        }
        if ("NAO".equals(valor)) {
            if (this.plano.getMicrocredito().getMicrolocaisinvestidos() == null) {
                this.plano.getMicrocredito().setMicrolocaisinvestidos(new ArrayList<MicroLocalInvest>());
            } else {
                this.plano.getMicrocredito().getMicrolocaisinvestidos().clear();
            }
            //this.microlocalinvest = null;
            panelMicro.setRendered(false);
            panelFin.setRendered(true);

        }

    }

    public void handleRadioFinInv() {
        String valor = this.plano.getMicrocredito().getIntobterfin();
        if ("SIM".equals(valor)) {
            panelFinInv.setRendered(true);
            this.microlocalinvesfin = new MicroLocalInvestFin();

        }
        if ("NAO".equals(valor)) {
            this.plano.getMicrocredito().getMicrolocaisinvestfin().clear();
            this.microlocalinvesfin = null;
            panelFinInv.setRendered(false);

        }

    }

    public void handleSelectBusca() {
        if ("cpf".equals(this.tipoBusca)) {
            buscacpf = true;
            buscanome = false;
            buscanomeresp = false;
            this.campoBusca = new String();
        }
        if ("nomeben".equals(this.tipoBusca)) {
            buscacpf = false;
            buscanome = false;
            buscanomeresp = true;
            this.campoBusca = new String();
        }
        if ("nome".equals(this.tipoBusca)) {
            buscacpf = false;
            buscanome = true;
            buscanomeresp = false;
            this.campoBusca = new String();
        }
    }

    public void handleRadioMicro2() {
        String valor = this.plano.getMicrocredito().getRecebeu();
        if ("SIM".equals(valor)) {
            panelMicro.setRendered(true);
            panelFin.setRendered(false);
            this.microlocalinvest = new MicroLocalInvest();

        }
        if ("NAO".equals(valor)) {
            this.microlocalinvest = null;
            panelMicro.setRendered(false);
            panelFin.setRendered(true);

        }

    }

    public void handleRadioFinInv2() {
        String valor = this.plano.getMicrocredito().getIntobterfin();
        if ("SIM".equals(valor)) {
            //panelFinInv.setVisible(true);
            panelFinInv.setRendered(true);
            this.microlocalinvesfin = new MicroLocalInvestFin();
        }
        if ("NAO".equals(valor)) {
            this.microlocalinvesfin = null;
            panelFinInv.setRendered(false);

        }

    }

    public void addBemDuravel(ActionEvent actionEvent) {
        if (this.bensDuraveis == null) {
            this.bensDuraveis = new BensDuraveis();
        }
        this.bensDuraveis.setCidBensDuraveis(cidBensDuraveis);
        this.cidBensDuraveis.getBensduraveis().add(bensDuraveis);
        this.bensDuraveis = new BensDuraveis();
    }

    public void editBemDuravel(ActionEvent actionEvent) {
        this.editar = true;
        this.bensDuraveis = new BensDuraveis();
    }

    public String newCidBensDuraveis(ActionEvent actionEvent) {
        this.cidBensDuraveis = new CidBensDuraveis();
        this.bensDuraveis = new BensDuraveis();
        return null;
    }

    public String saveCidBensDuraveis() {
        PlanoDAO planoDAO = new PlanoDAO();

        try {
            if (this.editar) {
                facesutils.aviso("Cadastro Alterado!");
            } else {
                this.cidBensDuraveis.setCidadao(plano.getCidadao());
                this.plano.getCidadao().getCidbensduraveis().add(cidBensDuraveis);
                planoDAO.savePlano(plano);
                this.cidBensDuraveis = null;
                facesutils.info("Cadastro Efetuado!");
            }
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado");
        }
        return null;
    }

    public void cancelCidBensDuraveis() {
        this.cidBensDuraveis = null;
        this.bensDuraveis = null;
        facesutils.info("Cancelado");
    }

    public String newDomicilio(ActionEvent actionEvent) {
        this.domicilio = new Domicilio();
        return null;
    }

    public String saveDomicilio(ActionEvent actionEvent) {
        PlanoDAO planoDAO = new PlanoDAO();
        try {
            if (this.editar) {
                facesutils.aviso("Cadastro Editado!");
                this.editar = false;
            }
            this.plano.getCidadao().getHistdomicilio().add(domicilio);
            planoDAO.savePlano(plano);
//            this.domicilio = new Domicilio();
            facesutils.info("Cadastro Efetuado!");
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado");
        }
        return null;
    }

    public void editDomicilio(ActionEvent actionEvent) {
        this.editar = true;
    }

    public void cancelDomicilio() {
        this.domicilio = null;
    }

    public String addCursoSec() {
        this.cursossecretaria.setCidadao(plano.getCidadao());
        this.plano.getCidadao().getCursosSecretarias().add(cursossecretaria);

        return null;
    }

    public String newCursoSec() {
        this.cursossecretaria = new CursosSecretaria();

        return null;
    }

    public void histNew() {
        this.historicovisita = new HistoricoVisita();

        inserirHist = true;

    }

    public void histAdd() {
        if (inserirHist == true) {
            this.plano.getHistoricovisitas().add(historicovisita);
            this.historicovisita.setPlano(plano);
            inserirHist = false;
        }
        this.savePlano();

    }

    public void histEdit() {
        this.inserirHist = false;

    }

    public void histCancel() {
        this.historicovisita = null;
    }

    protected void clear() {
        facesutils.cleanSubmittedValues(panelPlano);
        this.panelFin.setVisible(false);
        this.panelFinInv.setVisible(false);
        this.panelMicro.setVisible(false);
        this.panelPlano.setRendered(false);
    }

    public void selectCid() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        Cidadao c = this.plano.getCidadao();
        Cidadao cooperativa = cidadaoDAO.getEntity(c.getId());
        cooperativa.getCursos().toString();
        cooperativa.getTelefones().toString();
        this.plano.setCidadao(cooperativa);
    }

    @SuppressWarnings("unchecked")
    public void imprimir() throws IOException, JRException {
        PlanoDAO planoDAO = new PlanoDAO();
        List<Plano> listausr = new ArrayList<Plano>();
        Plano c = (Plano) (this.listaPlanos.getRowData());
        this.plano = planoDAO.getPlano(c.getId());
        listausr.add(plano);

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();
        String relJasper = scontext.getRealPath("/mpm/cadastro/individual/impressao/impplano.jasper");
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listausr);
        Map parameters = new HashMap();

        response.setHeader("Content-Disposition", "inline; filename=mpmindividual.pdf");
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
        //  this.cidadao = null;

    }
}
