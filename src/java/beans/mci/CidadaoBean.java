package beans.mci;

import beans.UsuarioLoginBean;
import dao.CidadeDAO;
import dao.EnderecoDAO;
import dao.EstadoDAO;
import dao.mci.*;
import dao.mgc.CursoDAO;
import entity.Cidade;
import entity.Endereco;
import entity.Estado;
import entity.mci.*;
import entity.Telefone;
import entity.Usuario;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCid;
import entity.mci.enumerator.EnumTipoPessoa;
import entity.mgc.Curso;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import util.FacesUtils;
import util.RelatorioUtil;

@ManagedBean
@ViewScoped
public class CidadaoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String busca;
    private String tipoBusca = "nome";
    private FacesUtils facesutils = new FacesUtils();
    private Cidadao cidadao;
    private Cursos cursos;
    private CursosSecretaria cursossecretaria;
    private Dependente dependente;
    private Domicilio domicilio;
    private CidBenSociais cidBenSocial;
    private CidBenHabits cidBenHabit;
    private CidAtividades cidatividade;
    private CidDespesas cidDespesas;
    private CidBensDuraveis cidBensDuraveis;
    private BensDuraveis bensDuraveis;
    private Estado selectedEstado = new Estado();
    private Estado selectedDepEstado = new Estado();
    private List<Cidade> listacidade = null;
    private List<Cidade> listacidadedep = null;
    private List<Estado> listaestado = null;
    private List<Endereco> listaendereco = null;
    private List<Cidadao> listaCidadaos = null;
    private List<Cidadao> listacid = null;
    private DataModel dmListaCidadaos = null;
    private DataModel dmLista = null;
    private DataModel dmListasDep = null;
    private EquipamentosProprios equipamentosproprios;
    private EquipamentosSecretaria equipamentossecretaria;
    private Panel panelCid;
    private Panel panelEquip;
    private Panel panelEquipSecr;
    private Panel panelCursos;
    private Panel panelBenSocial;
    private Panel panelBenHabit;
    private Panel panelEmprego;
    private Panel panelAtividade;
    private Panel panelView;
    private Panel panelCursoSecr;

    private UIForm formCid;
    private boolean editar = false;
    private Telefone telefone;
    private Cidadao cooper;
    private boolean inserircid = true;
    private final RelatorioUtil relatorioutil = new RelatorioUtil();

    public CidadaoBean() {
        this.listacid = null;
    }

    public Cidadao getCooper() {
        return cooper;
    }

    public void setCooper(Cidadao cooper) {
        this.cooper = cooper;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
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

    public Panel getPanelCursoSecr() {
        return panelCursoSecr;
    }

    public void setPanelCursoSecr(Panel panelCursoSecr) {
        this.panelCursoSecr = panelCursoSecr;
    }

    public String getTipoBusca() {
        return tipoBusca;
    }

    public void setTipoBusca(String tipoBusca) {
        this.tipoBusca = tipoBusca;
    }

    public Panel getPanelEquipSecr() {
        return panelEquipSecr;
    }

    public void setPanelEquipSecr(Panel panelEquipSecr) {
        this.panelEquipSecr = panelEquipSecr;
    }

    public Panel getPanelEquip() {
        return panelEquip;
    }

    public void setPanelEquip(Panel panelEquip) {
        this.panelEquip = panelEquip;
    }

    public Panel getPanelView() {
        return panelView;
    }

    public void setPanelView(Panel panelView) {
        this.panelView = panelView;
    }

    public UIForm getFormCid() {
        return formCid;
    }

    public void setFormCid(UIForm formCid) {
        this.formCid = formCid;
    }

    public void setPanelCid(Panel panelCid) {
        this.panelCid = panelCid;
    }

    public Panel getPanelCid() {
        return this.panelCid;
    }

    public Estado getSelectedDepEstado() {
        return selectedDepEstado;
    }

    public void setSelectedDepEstado(Estado selectedDepEstado) {
        this.selectedDepEstado = selectedDepEstado;
    }

    // Getters e Setters 
    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public Panel getPanelAtividade() {
        return panelAtividade;
    }

    public void setPanelAtividade(Panel panelAtividade) {
        this.panelAtividade = panelAtividade;
    }

    public Panel getPanelEmprego() {
        return panelEmprego;
    }

    public void setPanelEmprego(Panel panelEmprego) {
        this.panelEmprego = panelEmprego;
    }

    public Panel getPanelCursos() {
        return panelCursos;
    }

    public void setPanelCursos(Panel panelCursos) {
        this.panelCursos = panelCursos;
    }

    public CursosSecretaria getCursossecretaria() {
        return cursossecretaria;
    }

    public void setCursossecretaria(CursosSecretaria cursossecretaria) {
        this.cursossecretaria = cursossecretaria;
    }

    public Panel getPanelBenHabit() {
        return panelBenHabit;
    }

    public void setPanelBenHabit(Panel panelBenHabit) {
        this.panelBenHabit = panelBenHabit;
    }

    public Panel getPanelBenSocial() {
        return panelBenSocial;
    }

    public void setPanelBenSocial(Panel panelBenSocial) {
        this.panelBenSocial = panelBenSocial;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public Estado getSelectedEstado() {
        return selectedEstado;
    }

    public void setSelectedEstado(Estado selectedEstado) {
        this.selectedEstado = selectedEstado;
    }

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }

    public CidBenSociais getCidBenSocial() {
        return cidBenSocial;
    }

    public void setCidBenSocial(CidBenSociais cidBenSociais) {
        this.cidBenSocial = cidBenSociais;
    }

    public CidBenHabits getCidBenHabit() {
        return cidBenHabit;
    }

    public void setCidBenHabit(CidBenHabits cidBenHabit) {
        this.cidBenHabit = cidBenHabit;
    }

    public CidAtividades getCidatividade() {
        return cidatividade;
    }

    public void setCidatividade(CidAtividades cidatividade) {
        this.cidatividade = cidatividade;
    }

    public CidDespesas getCidDespesas() {
        return cidDespesas;
    }

    public void setCidDespesas(CidDespesas cidDespesas) {
        this.cidDespesas = cidDespesas;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public EquipamentosProprios getEquipamentosproprios() {
        return equipamentosproprios;
    }

    public void setEquipamentosproprios(EquipamentosProprios equipamentosproprios) {
        this.equipamentosproprios = equipamentosproprios;
    }

    public EquipamentosSecretaria getEquipamentossecretaria() {
        return equipamentossecretaria;
    }

    public void setEquipamentossecretaria(EquipamentosSecretaria equipamentossecretaria) {
        this.equipamentossecretaria = equipamentossecretaria;
    }

    public List<SelectItem> getSelectItemsEstado() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        EstadoDAO estadoDAO = new EstadoDAO();
        if (listaestado == null) {
            listaestado = estadoDAO.getEstados();
        }
        for (Estado uf : listaestado) {
            toReturn.add(new SelectItem(uf, uf.getNome()));
        }
        return toReturn;
    }

    public void handleEstadoChange() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        if (cidadao != null) {

            if (cidadao.getNaturalidade() != null) {
                if (cidadao.getNaturalidade().getEstado().getId() != null) {
                    this.listacidade = cidadeDAO.getCidades(this.cidadao.getNaturalidade().getEstado().getId());

                }
            } else {
                cidadao.setNaturalidade(cidadeDAO.getCidade(1));
            }
        }
    }

    public void handleEstadoDepChange() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        if (dependente != null) {
            if (dependente.getNaturalidade().getEstado() != null) {
                this.listacidadedep = cidadeDAO.getCidades(dependente.getNaturalidade().getEstado().getId());
            }
        }

    }

    public List<SelectItem> getSelectItemsDepCidade() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        if (listacidadedep != null) {
            for (Cidade cid : listacidadedep) {
                toReturn.add(new SelectItem(cid, cid.getNome()));
            }
        }
        return toReturn;
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

    public List<SelectItem> getSelectItemsEstadoCivil() {
        EstadoCivilDAO estadocivilDAO = new EstadoCivilDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (EstadoCivil ec : estadocivilDAO.getEstadosCivis()) {
            toReturn.add(new SelectItem(ec, ec.getDescricao()));
        }
        return toReturn;

    }

    public List<SelectItem> getSelectItemsCurso() {
        CursoDAO cursoDAO = new CursoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Curso c : cursoDAO.getCursos()) {
            toReturn.add(new SelectItem(c, c.getNome()));
        }
        return toReturn;

    }

    public List<SelectItem> getSelectItemsBeneficioSocial() {
        BeneficioSocialDAO beneficiosocialDAO = new BeneficioSocialDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (BeneficioSocial be : beneficiosocialDAO.getBeneficioSocials()) {
            toReturn.add(new SelectItem(be, be.getNome()));
        }
        return toReturn;

    }

    public List<SelectItem> getSelectItemsBeneficioHabitacional() {
        BeneficioHabitacionalDAO beneficiohabitacionalDAO = new BeneficioHabitacionalDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (BeneficioHabitacional be : beneficiohabitacionalDAO.getBeneficioHabitacionais()) {
            toReturn.add(new SelectItem(be, be.getNome()));
        }
        return toReturn;

    }

    public void handleSelectItemBeneficioSocial() {

        if (cidadao != null) {
            if ("true".equals(this.cidadao.getBeneficioSocial())) {
                panelBenSocial.setVisible(true);
                cidBenSocial = new CidBenSociais();
                if (this.cidadao.getCidbBenSociais() == null) {
                    this.cidadao.setCidbBenSociais(new ArrayList<CidBenSociais>());
                }
            }
            if ("false".equals(this.cidadao.getBeneficioSocial())) {
                this.cidadao.getCidbBenSociais().clear();
                panelBenSocial.setVisible(false);
                cidBenSocial = null;
            }
        }
    }

    public void handleSelectItemEquip() {
        String valor = this.cidadao.getEquipamento();
        if ("true".equals(valor)) {
            panelEquip.setVisible(true);
            this.equipamentosproprios = new EquipamentosProprios();
            if (this.cidadao.getEquipamentosproprios() == null) {
                this.cidadao.setEquipamentosproprios(new ArrayList<EquipamentosProprios>());
            }
        }
        if ("false".equals(valor)) {
            if (this.cidadao.getEquipamentosproprios() != null) {
                this.cidadao.getEquipamentosproprios().clear();
            }
            this.equipamentosproprios = null;
            panelEquip.setVisible(false);
        }
    }

    public void handleSelectItemEquipSecr() {
        String valor = this.cidadao.getEquipsecr();
        if ("true".equals(valor)) {
            panelEquipSecr.setVisible(true);
            this.equipamentossecretaria = new EquipamentosSecretaria();
            if (this.cidadao.getEquipamentossecretarias() == null) {
                this.cidadao.setEquipamentossecretarias(new ArrayList<EquipamentosSecretaria>());
            }
        }
        if ("false".equals(valor)) {
            this.cidadao.getEquipamentossecretarias().clear();
            this.equipamentossecretaria = null;
            panelEquipSecr.setVisible(false);
        }
    }

    public void handleSelectItemCursoSecr() {
        String valor = this.cidadao.getCursosecr();
        System.out.println(cidadao.getCursosecr());
        if ("true".equals(valor)) {
            panelCursoSecr.setVisible(true);
            this.cursossecretaria = new CursosSecretaria();
            if (this.cidadao.getCursosSecretarias() == null) {
                this.cidadao.setCursosSecretarias(new ArrayList<CursosSecretaria>());
            }
        }
        if ("false".equals(valor)) {
            this.cidadao.getCursosSecretarias().clear();
            this.cursossecretaria = null;
            panelCursoSecr.setVisible(false);
        }
    }

    public void handleSelectItemCurso() {
        String valor = this.cidadao.getCurso();
        if ("true".equals(valor)) {
            panelCursos.setVisible(true);
            this.cursos = new Cursos();
            if (this.cidadao.getCursos() == null) {
                this.cidadao.setCursos(new ArrayList<Cursos>());
            }
        }
        if ("false".equals(valor)) {
            this.cidadao.getCursos().clear();
            this.cursos = null;
            panelCursos.setVisible(false);
        }
    }

    public void handleSelectItemBeneficioHabitacional() {
        String valor = this.cidadao.getBeneficiohabitacional();
        if ("true".equals(valor)) {
            panelBenHabit.setVisible(true);
            this.cidBenHabit = new CidBenHabits();
            if (this.cidadao.getCidBenHabits() == null) {
                this.cidadao.setCidBenHabits(new ArrayList<CidBenHabits>());
            }
        }
        if ("false".equals(valor)) {
            this.cidadao.getCidBenHabits().clear();
            panelBenHabit.setVisible(false);
            this.cidBenHabit = null;
        }
    }

    public void handleSelectItemAtividade() {
        String valor = this.cidadao.getAtividade();
        if ("true".equals(valor)) {
            panelAtividade.setRendered(true);
            this.cidatividade = new CidAtividades();
            if (this.cidadao.getCidAtividades() == null) {
                this.cidadao.setCidAtividades(new ArrayList<CidAtividades>());
            }
        }
        if ("false".equals(valor)) {

            this.cidadao.getCidAtividades().clear();

            this.cidatividade = null;
            panelAtividade.setRendered(false);
        }
    }

    public List<SelectItem> getSelectItemsAtividades() {
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Atividade a : atividadeDAO.getAtividades()) {
            toReturn.add(new SelectItem(a, a.getDescricao()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsEscolaridade() {
        EscolaridadeDAO escolaridadeDAO = new EscolaridadeDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Escolaridade es : escolaridadeDAO.getEscolaridades()) {
            toReturn.add(new SelectItem(es, es.getGrau()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsNecEspeciais() {
        NecEspecialDAO necespecialDAO = new NecEspecialDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (NecEspecial nec : necespecialDAO.getNecEspecials()) {
            toReturn.add(new SelectItem(nec, nec.getDescricao()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsDespesas() {
        DespesaDAO despesaDAO = new DespesaDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Despesa d : despesaDAO.getDespesas()) {
            toReturn.add(new SelectItem(d, d.getDescricao()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsMoradia() {
        DomMoradiaDAO dommoradiaDAO = new DomMoradiaDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (DomMoradia d : dommoradiaDAO.getMoradias()) {
            toReturn.add(new SelectItem(d, d.getDescricao()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsBemDuravel() {
        BemDuravelDAO bemduravelDAO = new BemDuravelDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (BemDuravel d : bemduravelDAO.getBemDuravels()) {
            toReturn.add(new SelectItem(d, d.getNome()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsSituacao() {
        DomSituacaoDAO domsituacaoDAO = new DomSituacaoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (DomSituacao d : domsituacaoDAO.getListas()) {
            toReturn.add(new SelectItem(d, d.getNome()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsConstrucao() {
        DomConstrucaoDAO domconstrucaoDAO = new DomConstrucaoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (DomConstrucao d : domconstrucaoDAO.getListas()) {
            toReturn.add(new SelectItem(d, d.getNome()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsEscoamento() {
        DomEscoamentoDAO domescoamentoDAO = new DomEscoamentoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (DomEscoamento d : domescoamentoDAO.getEscoamentos()) {
            toReturn.add(new SelectItem(d, d.getNome()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsAbastecimento() {
        DomAbastecimentoDAO domabastecimentoDAO = new DomAbastecimentoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (DomAbastecimento d : domabastecimentoDAO.getListas()) {
            toReturn.add(new SelectItem(d, d.getNome()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsColetaLixo() {
        DomColetalixoDAO domColetaLixoDAO = new DomColetalixoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (DomColetaLixo d : domColetaLixoDAO.getColetaLixos()) {
            toReturn.add(new SelectItem(d, d.getNome()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsPavimentacao() {
        DomPavimentacaoDAO domPavimentacaoDAO = new DomPavimentacaoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (DomPavimentacao d : domPavimentacaoDAO.getPavimentacaos()) {
            toReturn.add(new SelectItem(d, d.getNome()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsIluminacao() {
        DomIluminacaoDAO domIluminacaoDAO = new DomIluminacaoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (DomIluminacao d : domIluminacaoDAO.getIluminacaos()) {
            toReturn.add(new SelectItem(d, d.getNome()));
        }
        return toReturn;
    }

    public List<Cursos> getListaCursos() {
        List<Cursos> listacursos = null;
        if (cidadao != null) {
            listacursos = cidadao.getCursos();
        }

        return listacursos;
    }

    @SuppressWarnings("unchecked")
    public DataModel getDataModelListaEndereco() {
        ListDataModel dmListas = null;
        if (listaendereco != null) {
            dmListas = new ListDataModel(listaendereco);
        }
        return dmListas;
    }

    @SuppressWarnings("unchecked")
    public DataModel getListaCidadaos() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        listaCidadaos = cidadaoDAO.getList();
        dmListaCidadaos = new ListDataModel(listaCidadaos);
        return dmListaCidadaos;
    }

    @SuppressWarnings("unchecked")
    public DataModel getDataModelListaCid() {
        dmLista = null;
        if (listacid != null) {

            dmLista = new ListDataModel(listacid);
        }
        return dmLista;
    }

    public void localiza(ActionEvent actionEvent) {
        try {

            CidadaoDAO cidadaoDAO = new CidadaoDAO();
            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(busca, EnumTipoPessoa.CID);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.busca, EnumTipoPessoa.CID);
            }
            if (listacid.isEmpty()) {
                throw new Exception();
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }
    }

    public void cancel() {

        this.busca = null;
        this.listacid = null;
        this.cidadao = null;

        //panelCid.setVisible(false);        
        //panelCid.setRendered(false);
    }

    public void buscaEndereco(ActionEvent actionEvent) {
        listaendereco = null;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        listaendereco = enderecoDAO.getEnderecos(this.busca);
    }

    public void enderecoClear() {
        listaendereco = null;
        this.busca = null;
    }

    public void buscaEnderecoPorNome(ActionEvent actionEvent) {
        listaendereco = null;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        listaendereco = enderecoDAO.getEnderecosMaxResult(this.busca);
    }

    public String addTelefones() {
        if (this.cidadao.getTelefones() == null) {
            this.cidadao.setTelefones(new ArrayList<Telefone>());
        }
        this.cidadao.getTelefones().add(this.telefone);
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        //cidadaoDAO.save(cidadao);
        this.telefone = new Telefone();
        return null;
    }

    public String addCurso() {
        this.cursos.setCidadao(cidadao);
        this.cidadao.getCursos().add(cursos);
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        cidadaoDAO.save(cidadao);
        this.cursos = new Cursos();
        return null;
    }

    public String addCursoSec() {
        this.cursossecretaria.setCidadao(cidadao);

        if (this.cidadao.getCursosSecretarias().contains(this.cursossecretaria)) {
            facesutils.erro("Curso já incluido !");
        } else {
            this.cidadao.getCursosSecretarias().add(cursossecretaria);
            CidadaoDAO cidadaoDAO = new CidadaoDAO();
            cidadaoDAO.save(cidadao);
            facesutils.info(" Curso Secretaria incluido ");

        }
        this.cursossecretaria = new CursosSecretaria();

        return null;
    }

    public String addEquipPro() {
        this.equipamentosproprios.setCidadao(cidadao);
        this.cidadao.getEquipamentosproprios().add(equipamentosproprios);
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        cidadaoDAO.save(cidadao);
        this.equipamentosproprios = new EquipamentosProprios();
        facesutils.info(" Equipamento próprio incluido ");
        return null;
    }

    public String addEquipSecr() {
        this.equipamentossecretaria.setCidadao(cidadao);
        this.cidadao.getEquipamentossecretarias().add(equipamentossecretaria);
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        cidadaoDAO.save(cidadao);
        this.equipamentossecretaria = new EquipamentosSecretaria();

        facesutils.info(" Equipamento Secretaria incluido ");
        return null;
    }

    public String newBenSocial(ActionEvent actionEvent) {
        if (this.cidBenSocial == null) {
            this.cidBenSocial = new CidBenSociais();
        }
        this.cidBenSocial.setCidadao(this.cidadao);
        this.cidadao.getCidbBenSociais().add(this.cidBenSocial);
        this.cidBenSocial = new CidBenSociais();

        return null;
    }

    public String newBenHabit(ActionEvent actionEvent) {
        if (this.cidBenHabit == null) {
            this.cidBenHabit = new CidBenHabits();
        }
        this.cidBenHabit.setCidadao(cidadao);
        this.cidadao.getCidBenHabits().add(cidBenHabit);
        this.cidBenHabit = new CidBenHabits();
        return null;
    }

    public String newAtividade(ActionEvent actionEvent) {
        if (this.cidatividade == null) {
            this.cidatividade = new CidAtividades();
        }
        this.cidatividade.setCidadao(cidadao);
        this.cidadao.getCidAtividades().add(cidatividade);
        this.cidatividade = new CidAtividades();
        this.save(actionEvent);
        return null;
    }

    public void newDespesa(ActionEvent actionEvent) {
        if (this.cidDespesas == null) {
            this.cidDespesas = new CidDespesas();
        }
        this.cidDespesas.setCidadao(cidadao);
        this.cidadao.getDespesas().add(cidDespesas);
        this.cidDespesas = new CidDespesas();
    }

    public String newDependente(ActionEvent actionEvent) {
        this.dependente = new Dependente();
        return null;
    }

    public String newDomicilio(ActionEvent actionEvent) {
        this.domicilio = new Domicilio();
        this.domicilio.setDataadd(this.cidadao.getDatacreated());
        return null;
    }

    public String newCidBensDuraveis(ActionEvent actionEvent) {
        this.cidBensDuraveis = new CidBensDuraveis();
        this.bensDuraveis = new BensDuraveis();
        this.cidBensDuraveis.setDataadd(this.cidadao.getDatacreated());
        
        this.editar = false;
        return null;
    }

    public void addBemDuravel(ActionEvent actionEvent) {
        if (this.bensDuraveis == null) {
            this.bensDuraveis = new BensDuraveis();
        }

        this.cidBensDuraveis.setCidadao(cidadao);
        this.bensDuraveis.setCidBensDuraveis(cidBensDuraveis);
        this.cidBensDuraveis.getBensduraveis().add(bensDuraveis);
        this.bensDuraveis = new BensDuraveis();
    }

    public String saveCidBensDuraveis() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {
            if (this.editar == true) {
                facesutils.aviso("Cadastro Alterado!");
                this.editar = false;
                this.cidBensDuraveis = null;
            } else {
                this.cidBensDuraveis.setCidadao(cidadao);
                this.cidadao.getCidbensduraveis().add(cidBensDuraveis);

                this.cidBensDuraveis = null;
                facesutils.info("Cadastro Efetuado!");
            }
            cidadaoDAO.save(cidadao);
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado");
        }
        return null;
    }

    public void editBemDuravel(ActionEvent actionEvent) {
        this.editar = true;
        this.bensDuraveis = new BensDuraveis();
    }

    public void cancelCidBensDuraveis() {
        this.cidBensDuraveis = null;
        this.bensDuraveis = null;
        facesutils.info("Cancelado");
    }

    public String saveDomicilio(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {
            if (this.editar == true) {
                facesutils.aviso("Cadastro Editado!");
                this.editar = false;
                this.domicilio = null;
            } else {
                domicilio.setDataadd(this.cidadao.getDatacreated());
                this.cidadao.getHistdomicilio().add(domicilio);

                cidadaoDAO.save(cidadao);
                this.domicilio = null;
                facesutils.info("Cadastro Efetuado!");
            }
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

    public String saveDependente(ActionEvent actionEvent) {
        CidadaoDAO cdao = new CidadaoDAO();
        try {
            if (this.editar) {
                facesutils.aviso("Cadastro Editado!");
                this.editar = false;
            } else {
                if (this.cidadao.getDependentes().contains(this.dependente)) {
                    throw new Exception("Já Existe");
                } else {
                    this.cidadao.getDependentes().add(dependente);
                    cdao.save(cidadao);
                    this.dependente = new Dependente();
                    facesutils.info("Cadastro Efetuado!");
                }

            }
        } catch (Exception e) {
            System.out.println(" exp" + e.getMessage());
            facesutils.aviso("Cadastro Não Efetuado");

        }
        return null;
    }

    public DataModel getListaCompFamiliar() {
        dmListasDep = null;
        if (cidadao != null) {
            dmListasDep = new ListDataModel(cidadao.getDependentes());
        }
        return dmListasDep;

    }

    public void editDependente(ActionEvent actionEvent) {
        this.editar = true;
        this.dependente = (Dependente) (dmListasDep.getRowData());
        this.handleEstadoDepChange();
    }

    public void cancelDependente() {
        this.dependente = null;
    }

    public void saveCidadaoNew(ActionEvent actionEvent) {

        boolean success;
        CidadaoDAO cidadaoDAO = new CidadaoDAO();

        if (cidadaoDAO.getListByCnp(this.cidadao.getCpf()).isEmpty()) {

            success = false;

            // facesutils.cleanSubmittedValues(formCid);
            cidadao.setEstadocivil(new EstadoCivil());
            cidadao.setEndereco(new Endereco());
            cidadao.setEscolaridade(new Escolaridade());
            cidadao.setNecespecial(new NecEspecial());
            cidadao.setNaturalidade(new Cidade());
            this.cidDespesas = new CidDespesas();
            this.cursossecretaria = new CursosSecretaria();
            this.telefone = new Telefone();
            //this.cidBenHabit = new CidBenHabits();
            //this.cidBenSocial = new CidBenSociais();

            this.cidadao.getTelefones().toString();
            //this.cidadao.getCursos().toString();
            this.cidadao.getCidAtividades().toString();
            this.cidadao.getCidBenHabits().toString();
            this.cidadao.getCidbBenSociais().toString();
            //this.cidadao.getCidbensduraveis().toString();
            this.cidadao.getCursosSecretarias().toString();          
           // this.cidadao.getEquipamentosproprios().toString();
            this.cidadao.getHistdomicilio().toString();
            this.cidadao.getDependentes().toString();
            this.cidadao.getDespesas().toString();

            this.handleEstadoChange();
            this.handleSelectItemAtividade();
            this.handleSelectItemBeneficioHabitacional();
            this.handleSelectItemBeneficioSocial();
            //this.handleSelectItemCurso();
            //this.handleSelectItemEquip();

            this.cidadao.setBenstatus(EnumStatusBeneficio.RESERVA);
            this.cidadao.setStatuscid(EnumStatusCid.INDIVIDUAL);
            this.cidadao.setTipopessoa(EnumTipoPessoa.CID);

        } else {
            success = true;
            facesutils.aviso("Inclusão não efetudada  CPF: " + this.cidadao.getCpf() + " Já Existe ");
        }
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addCallbackParam("success", success);
    }

    public void saveCidadao(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {
            cidadaoDAO.save(cidadao);
            facesutils.info("Cadastro Efetuado");
            //this.cidadao = null;
            this.listacid = null;
            this.busca = null;

            //return "gotocadins";
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! ");
            System.out.println(e.getMessage());
            // return null;
        } finally {
            facesutils.cleanSubmittedValues(formCid);
        }

    }

    public void save(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {
            cidadaoDAO.save(cidadao);
            facesutils.info("Cadastro Efetuado");

        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! ");

        }
    }

    public void salva() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {
            cidadaoDAO.save(cidadao);
            this.inserircid = false;
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Salvo! " + e.getMessage());

        }
    }

    public boolean isInserircid() {
        return inserircid;
    }

    public void newCidadao(ActionEvent actionEvent) {

        //tabView.setActiveIndex(0);
        //tabViewCaracteristicas.setActiveIndex(0);
        this.cidadao = new Cidadao();
        Usuario u = new UsuarioLoginBean().getUsersec();
        this.cidadao.setUsuario(u);
        this.inserircid = true;
        //this.cidadao.setBenstatus(EnumStatusBeneficio.RESERVA);
        //this.cidadao.setStatuscid(EnumStatusCid.INDIVIDUAL);
        //this.cidDespesas = new CidDespesas();
        //this.telefone = new Telefone();
    }

    public String cancelCidadao() {

        this.cidDespesas = null;
        this.busca = null;
        this.listacid = null;
        this.cidadao = null;

        //panelCid.setVisible(false);
        facesutils.cleanSubmittedValues(formCid);
        //panelCid.setRendered(false);
        return "gotocadins";
    }

    public void editCidadao(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        this.inserircid = false;

        //this.tabView.setActiveIndex(0);
        //this.tabViewCaracteristicas.setActiveIndex(0);
        facesutils.cleanSubmittedValues(formCid);
        Cidadao c = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(c.getId());

        if (cidadao.getStatuscid().equals(EnumStatusCid.COLETIVO)) {
            CidAssociadosDAO cidassdao = new CidAssociadosDAO();
            CidAssociados cass = cidassdao.getCidAssociadosbyId(c.getId());
            this.cooper = cidadaoDAO.getEntity(cass.getCidadao().getId());
        }

        this.cidadao.getTelefones().toString();
      //  this.cidadao.getCursos().toString();
        this.cidadao.getCidAtividades().toString();
        this.cidadao.getCidBenHabits().toString();
        this.cidadao.getCidbBenSociais().toString();
      //  this.cidadao.getCidbensduraveis().toString();
        this.cidadao.getCursosSecretarias().toString();
      //  this.cidadao.getEquipamentosproprios().toString();
        this.cidadao.getHistdomicilio().toString();
        this.cidadao.getDependentes().toString();
        this.cidadao.getDespesas().toString();

        this.handleEstadoChange();
        this.handleSelectItemAtividade();
        this.handleSelectItemBeneficioHabitacional();
        this.handleSelectItemBeneficioSocial();
       // this.handleSelectItemCurso();
        //this.handleSelectItemEquip();
        this.cidDespesas = new CidDespesas();
        this.cursossecretaria = new CursosSecretaria();
        this.telefone = new Telefone();

    }

    public void editBeneficio(ActionEvent actionEvent) {
        //this.tabView.setActiveIndex(0);        
        facesutils.cleanSubmittedValues(formCid);
        this.cidadao = (Cidadao) (this.dmLista.getRowData());
        this.equipamentossecretaria = new EquipamentosSecretaria();

    }

    @SuppressWarnings("unchecked")
    public void saveImprimir() throws IOException, JRException {

        List<Cidadao> lista = new ArrayList<Cidadao>();
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        this.cidadao = cidadaoDAO.getEntity(this.cidadao.getId());
        lista.add(cidadao);

        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("urlficha");
        String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nomeficha");
        relatorioutil.criaRelatorio(lista, urlrelatorio, nomerelatorio);

    }

    @SuppressWarnings("unchecked")
    public void imprimir() throws IOException, JRException {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        List<Cidadao> lista = new ArrayList<Cidadao>();
        Cidadao c = (Cidadao) (this.dmLista.getRowData());
        this.cidadao = cidadaoDAO.getEntity(c.getId());
        lista.add(cidadao);

        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("urlficha");
        String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nomeficha");
        relatorioutil.criaRelatorio(lista, urlrelatorio, nomerelatorio);

    }

    public List<Cidadao> getListaAnoDemandas() {
        CidadaoDAO cdao = new CidadaoDAO();
        return cdao.getListaAnoDemandas();

    }

    public List<SelectItem> getSelectItemsAnoDemanda() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        
        for (Cidadao a : this.getListaAnoDemandas()) {
            toReturn.add(new SelectItem(a,a.toString()));

        }
        return toReturn;
    }
}
