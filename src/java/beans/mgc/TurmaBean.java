package beans.mgc;

import dao.BairroDAO;
import dao.CidadeDAO;
import dao.mci.CidadaoDAO;
import dao.mci.CursoSecretariaDAO;
import dao.mgc.CursoDAO;
import dao.mgc.TurmaDAO;
import entity.Bairro;
import entity.Cidade;
import entity.mci.Cidadao;
import entity.mci.CursosSecretaria;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCurso;
import entity.mci.enumerator.EnumStatusTurma;

import entity.mci.enumerator.EnumTipoPessoa;

import entity.mgc.Curso;
import entity.mgc.TurCidadaos;
import entity.mgc.Turma;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import util.FacesUtils;
import util.RelatorioUtil;

@ManagedBean
@ViewScoped
public class TurmaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String busca;
    private String campoBusca;
    private String buscaCidadao;
    private String buscaCidadaoCoop;
    private String tipoBusca = "turma";
    private String tipoBuscaCidadao = "nome";
    private String tipoBuscaCidadaoCoop = "nome";
    private FacesUtils facesutils = new FacesUtils();
    private FacesContext context;
    private Turma turma;
    private List<Turma> listaTurmas = null;
    private List<Cidadao> listaCidadaos;
    private List<Cidadao> listaAlunos = null;
    private Curso curso;
    private Cidadao cooperativa = new Cidadao();
    private DataModel<Turma> dmLista = null;
    private DataModel<Cidadao> dmListaCidadao = null;
    private DataModel<Cidadao> dmListaAlunos = null;
    private TurCidadaos turCidadaos = null;
    private boolean cidByCurso = false;

    private boolean buscacpf = false;
    private boolean buscaturma = true;
    private boolean buscanome = false;
    
    private Cidade cidadeSelect;
    private Bairro bairroSelect;
     private List<Cidade> listacidade = null;
    private List<Bairro> listabairro = null;
    private int  anocurso;
    

    public void newTurma() {
        this.turma = new Turma();
        this.turma.setStatus(EnumStatusTurma.ANDAMENTO.toString());
    }

    public void save() {
        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.saveTurma(turma);
            facesutils.info("Cadastro Efetuado! ");

        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! ");
        }

    }

    public void saveTurma() {

        TurmaDAO turmaDAO = new TurmaDAO();
        CursoSecretariaDAO cdao = new CursoSecretariaDAO();
        turmaDAO.saveTurma(turma);
        try {

            if (turma.getTurcidadaos().size() > 0) {
                //   System.out.println(" maior que zero");

                for (TurCidadaos t : turma.getTurcidadaos()) {

                    if (!t.getStatus().equals("DESISTENTE")) {

                        CursosSecretaria csec = cdao.getEntityByIdCurso(this.turma.getCurso().getId(), t.getCidadao().getId());

                        if (turma.getStatus().equals(EnumStatusTurma.FINALIZADO.toString())) {
                            t.setStatus(EnumStatusCurso.CONCLUSO.toString());
                            csec.setStatus(EnumStatusCurso.CONCLUSO);

                        } else if (turma.getStatus().equals(EnumStatusTurma.ANDAMENTO.toString())) {
                            t.setStatus(EnumStatusCurso.CURSANDO.toString());
                            csec.setStatus(EnumStatusCurso.CURSANDO);

                        } else if (turma.getStatus().equals(EnumStatusTurma.CANCELADO.toString())) {
                            t.setStatus(EnumStatusCurso.RESERVA.toString());
                            csec.setStatus(EnumStatusCurso.RESERVA);

                        }
                        cdao.saveCursoSecretaria(csec);
                        // turmaDAO.saveTurma(turma);

                    }

                }
            }

            this.turma = turmaDAO.getTurma(turma.getId());
            this.turma.getTurcidadaos().toString();
            facesutils.info("Cadastro Salvo");
            //this.turma = new Turma();

            this.listaTurmas = null;
            this.busca = null;
        } catch (Exception e) {
            facesutils.erro("Cadastro Não Efetuado! ");
            //System.out.println(e.getMessage());
        }
    }

    public void editTurma() {
        Turma t = (Turma) (this.dmLista.getRowData());
        TurmaDAO turmaDAO = new TurmaDAO();
        this.turma = turmaDAO.getTurma(t.getId());
        this.turma.getTurcidadaos().toString();
        this.listaAlunos = turmaDAO.getCidadaosInTurma(EnumTipoPessoa.CID, turma.getId());
    }

    public void editTurmaUnico() {
        // this.turma = (Turma) this.dmLista.getRowData();
        TurmaDAO turmaDAO = new TurmaDAO();
        this.listaAlunos = turmaDAO.getCidadaosInTurma(EnumTipoPessoa.CID, turma.getId());
    }

    public void editTurmaCidadao(ActionEvent actionEvent) {
        TurmaDAO turmaDAO = new TurmaDAO();
        Turma t = (Turma) (this.dmLista.getRowData());
        this.turma = turmaDAO.getTurma(t.getId());
        this.turma.getTurcidadaos().toString();

    }

    public void updateTurma() {
        context = FacesContext.getCurrentInstance();
        TurmaDAO turmaDAO = new TurmaDAO();
        try {
            turmaDAO.updateTurma(turma);
            this.listaTurmas = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }
    }
public void handlePesquisaCidadeChange() {
        BairroDAO bairroDAO = new BairroDAO();
        if (cidadeSelect.getId() != 0) {
            this.listabairro = bairroDAO.getBairros(cidadeSelect.getId());
        } else {
            listabairro = null;
            getSelectItemsCidade().clear();
            getSelectItemsBairro().clear();
        }

    }
public List<SelectItem> getSelectItemsCidade() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        Cidade c = new Cidade();
        CidadeDAO cidadeDAO = new CidadeDAO();
        c.setId(0);
        c.setNome("TODOS");
        toReturn.add(new SelectItem(c, c.getNome()));
        if (this.listacidade == null) {
            this.listacidade = cidadeDAO.getCidades(1);
        }
        if (this.listacidade != null) {
            for (Cidade cid : listacidade) {
                toReturn.add(new SelectItem(cid, cid.getNome()));
            }

        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsBairro() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        Bairro b = new Bairro();
        b.setId(0);
        b.setNome("TODOS");
        toReturn.add(new SelectItem(b, b.getNome()));

        if (listabairro != null) {
            for (Bairro ba : listabairro) {
                toReturn.add(new SelectItem(ba, ba.getNome()));
            }
        }
        return toReturn;
    }
    public void cancelTurma() {
        this.turma = null;
    }

    public List<SelectItem> getSelectItemsCurso() {
        CursoDAO cursoDAO = new CursoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (Curso c : cursoDAO.getCursos()) {
            toReturn.add(new SelectItem(c, c.getNome()));
        }
        return toReturn;

    }

    public List<SelectItem> getSelectItemStatusTurma() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (EnumStatusTurma c : EnumStatusTurma.values()) {
            toReturn.add(new SelectItem(c.toString(), c.toString()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemStatusCurso() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (EnumStatusCurso c : EnumStatusCurso.values()) {
            toReturn.add(new SelectItem(c.toString(), c.toString()));
        }
        return toReturn;
    }

    public void localiza() {
        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            if ("turma".equals(this.tipoBusca)) {
                  listaTurmas = turmaDAO.getTurmasFiltro(this.tipoBusca, this.busca,this.anocurso,this.cidadeSelect,this.bairroSelect);
                this.busca = new String();
            }
            if ("cpf".equals(this.tipoBusca)) {
                
                
                listaTurmas = turmaDAO.getTurmasFiltro(this.tipoBusca, this.busca,this.anocurso,this.cidadeSelect,this.bairroSelect);
                this.busca = new String();
            }
             if (listaTurmas.isEmpty()) {
                throw new Exception();
            }
             
             System.out.println("Ano Curso "+ this.anocurso );
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }
    }

    public void handleSelectBusca() {
        if ("turma".equals(this.tipoBusca)) {
            buscacpf = false;
            buscanome = false;
            buscaturma = true;
            this.campoBusca = new String();
        }
        if ("cpf".equals(this.tipoBusca)) {
            buscacpf = true;
            buscanome = false;
            buscaturma = false;
            this.campoBusca = new String();
        }
        if ("nomeben".equals(this.tipoBusca)) {
            buscacpf = false;
            buscanome = true;
            buscaturma = false;
            this.campoBusca = new String();
        }

    }

    public void localizaCidadaos() {
        this.listaCidadaos = null;

        TurmaDAO turmaDAO = new TurmaDAO();
        if ("nome".equals(this.tipoBuscaCidadao)) {
            this.listaCidadaos = turmaDAO.getCidadaosByNom(this.buscaCidadao, EnumTipoPessoa.CID, true, this.turma.getCurso().getId());

        }
        if ("cpf".equals(this.tipoBuscaCidadao)) {
            this.listaCidadaos = turmaDAO.getCidadaosByCnp(this.buscaCidadao, EnumTipoPessoa.CID, true, this.turma.getCurso().getId());

        }

        this.buscaCidadao = null;
    }

    public void localizaCooperativas() {
        this.listaCidadaos = null;

        TurmaDAO turmaDAO = new TurmaDAO();
        if ("nome".equals(this.tipoBuscaCidadao)) {

            this.listaCidadaos = turmaDAO.getCidadaosByNom(this.buscaCidadao, EnumTipoPessoa.COOP, false, this.turma.getCurso().getId());

        } else if ("cpf".equals(this.tipoBuscaCidadao)) {
            this.listaCidadaos = turmaDAO.getCidadaosByCnp(this.buscaCidadao, EnumTipoPessoa.COOP,false , this.turma.getCurso().getId());
        }

        this.buscaCidadao = null;
    }

    public void selectCooperativa() {
        this.cooperativa = new Cidadao();
        this.cooperativa = (Cidadao) this.dmListaCidadao.getRowData();
        this.listaCidadaos = null;
    }

    public void localizaCidadaosByCoop() {
        this.listaCidadaos = null;

        TurmaDAO turmaDAO = new TurmaDAO();
        if ("nome".equals(this.tipoBuscaCidadaoCoop)) {
            this.listaCidadaos = turmaDAO.getAssocidosByNom(cooperativa.getId(), EnumTipoPessoa.CID, this.buscaCidadaoCoop);
        } else if ("cpf".equals(this.tipoBuscaCidadaoCoop)) {
            this.listaCidadaos = turmaDAO.getAssocidosByCnp(cooperativa.getId(), EnumTipoPessoa.CID, this.buscaCidadaoCoop);
        }
        this.buscaCidadaoCoop = "";

    }

    public void clearListCidadao() {
        this.listaCidadaos = null;
        this.listaAlunos = null;
    }

    public DataModel getDataModelListaTurma() {
        dmLista = null;
        if (listaTurmas != null) {
            dmLista = new ListDataModel(this.listaTurmas);
        }
        return dmLista;
    }

    public DataModel getDataModelListaCid() {
        this.dmListaCidadao = null;
        if (this.listaCidadaos != null) {
            this.dmListaCidadao = new ListDataModel(this.listaCidadaos);
        }

        return this.dmListaCidadao;
    }

    public DataModel getDataModelListaAlunos() {
        this.dmListaAlunos = null;
        TurmaDAO turmaDAO = new TurmaDAO();
        if (turma != null) {
            if (this.turma.getId() != null) {
                this.listaAlunos = turmaDAO.getCidadaosInTurma(EnumTipoPessoa.CID, this.turma.getId());
            }
//            for (Cidadao c : listaAlunos) {
//                System.out.println("ttttt " + c.getId() + c.getNome());
//            }
            if (this.listaAlunos != null) {
                this.dmListaAlunos = new ListDataModel(this.listaAlunos);
            }
        }

        return this.dmListaAlunos;
    }

    public void delCidadao() {
        this.turma.getTurcidadaos().remove(turCidadaos);
        CursoSecretariaDAO cdao = new CursoSecretariaDAO();
        CursosSecretaria csec = cdao.getEntityByIdCurso(this.turma.getCurso().getId(), this.turCidadaos.getCidadao().getId());
        csec.setStatus(EnumStatusCurso.RESERVA);
        cdao.saveCursoSecretaria(csec);
        // CidadaoDAO cidadaoDAO = new CidadaoDAO();
        //Cidadao c = cidadaoDAO.getEntity(this.turCidadaos.getCidadao().getId());
        //c.setBenstatus(EnumStatusBeneficio.INDIVIDUAL);
        //cidadaoDAO.save(c);
        TurmaDAO turmaDAO = new TurmaDAO();
        turmaDAO.saveTurma(this.turma);

        this.turCidadaos = null;

    }

    public void addCidadao() {

        this.turCidadaos = new TurCidadaos();
        this.turCidadaos.setTurma(this.turma);
        this.turCidadaos.setCidadao((Cidadao) this.dmListaCidadao.getRowData());
        this.turCidadaos.setStatus(EnumStatusCurso.CURSANDO.toString());
        CursoSecretariaDAO cdao = new CursoSecretariaDAO();
        //System.out.println("Codigo curso: " + this.turma.getCurso().getId());
        // System.out.println("Codigo Cidadao: " + this.turCidadaos.getCidadao().getId());
        CursosSecretaria csec = cdao.getEntityByIdCurso(this.turma.getCurso().getId(), this.turCidadaos.getCidadao().getId());
        csec.setStatus(EnumStatusCurso.CURSANDO);
        cdao.saveCursoSecretaria(csec);
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        Cidadao c = cidadaoDAO.getEntity(this.turCidadaos.getCidadao().getId());
        c.setBenstatus(EnumStatusBeneficio.BENEFICIADO);
        cidadaoDAO.save(c);

        try {

            this.turma.getTurcidadaos().add(this.turCidadaos);
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.saveTurma(this.turma);

            this.turCidadaos = null;
            facesutils.info("Cadastro Efetuado!");
        } catch (Exception e) {
            //  System.out.println(" exp" + e.getMessage());
            facesutils.aviso("Cadastro Não Efetuado");
        }
        this.listaCidadaos = null;
        this.dmListaCidadao = null;
    }

    public void addCooperativa(ActionEvent actionEvent) {
        TurmaDAO turmaDAO = new TurmaDAO();
        cooperativa = new Cidadao();
        cooperativa = (Cidadao) this.dmListaCidadao.getRowData();
        for (Cidadao cid : turmaDAO.getAssocidos(cooperativa.getId(), EnumTipoPessoa.CID)) {
            this.turCidadaos = new TurCidadaos();
            this.turCidadaos.setTurma(this.turma);
            this.turCidadaos.setCidadao(cid);
            this.turma.getTurcidadaos().add(this.turCidadaos);
        }
        try {
            turmaDAO.saveTurma(this.turma);
            this.turCidadaos = null;
            facesutils.info("Cadastro Efetuado!");
        } catch (Exception e) {
            System.out.println(" exp" + e.getMessage());
            facesutils.aviso("Cadastro Não Efetuado");
        }
        this.dmListaCidadao = null;
    }
    
    @SuppressWarnings("unchecked")
    public void imprimir() throws IOException, JRException {
        TurmaDAO turmaDAO = new TurmaDAO();
        List<Turma> lista = new ArrayList<Turma>();
        Turma c = (Turma) (this.dmLista.getRowData());
        this.turma = turmaDAO.getTurma(c.getId());
        lista.add(turma);
        
        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("urlturma");
        //String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nomearqturma");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, "lista_turma");

       
    }

    public String getCampoBusca() {
        return campoBusca;
    }

    public void setCampoBusca(String campoBusca) {
        this.campoBusca = campoBusca;
    }

    public boolean isBuscacpf() {
        return buscacpf;
    }

    public void setBuscacpf(boolean buscacpf) {
        this.buscacpf = buscacpf;
    }

    public boolean isBuscaturma() {
        return buscaturma;
    }

    public void setBuscaturma(boolean buscaturma) {
        this.buscaturma = buscaturma;
    }

    public boolean isBuscanome() {
        return buscanome;
    }

    public void setBuscanome(boolean buscanome) {
        this.buscanome = buscanome;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public DataModel<Turma> getDmLista() {
        return dmLista;
    }

    public void setDmLista(DataModel<Turma> dmLista) {
        this.dmLista = dmLista;
    }

    public DataModel<Cidadao> getDmListaCidadao() {
        return dmListaCidadao;
    }

    public void setDmListaCidadao(DataModel<Cidadao> dmListaCidadao) {
        this.dmListaCidadao = dmListaCidadao;
    }

    public List<Turma> getListaTurmas() {
        return listaTurmas;
    }

    public void setListaTurmas(List<Turma> listaTurmas) {
        this.listaTurmas = listaTurmas;
    }

    public FacesUtils getFacesutils() {
        return facesutils;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public void setFacesutils(FacesUtils facesutils) {
        this.facesutils = facesutils;
    }

    public String getTipoBusca() {
        return tipoBusca;
    }

    public void setTipoBusca(String tipoBusca) {
        this.tipoBusca = tipoBusca;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public TurCidadaos getTurCidadaos() {
        return turCidadaos;
    }

    public void setTurCidadaos(TurCidadaos turCidadaos) {
        this.turCidadaos = turCidadaos;
    }

    public String getTipoBuscaCidadao() {
        return tipoBuscaCidadao;
    }

    public void setTipoBuscaCidadao(String tipoBuscaCidadao) {
        this.tipoBuscaCidadao = tipoBuscaCidadao;
    }

    public List<Cidadao> getListaCidadaos() {
        return listaCidadaos;
    }

    public void setListaCidadaos(List<Cidadao> listaCidadaos) {
        this.listaCidadaos = listaCidadaos;
    }

    public String getBuscaCidadao() {
        return buscaCidadao;
    }

    public void setBuscaCidadao(String buscaCidadao) {
        this.buscaCidadao = buscaCidadao;
    }

    public boolean isCidByCurso() {
        return cidByCurso;
    }

    public void setCidByCurso(boolean cidByCurso) {
        this.cidByCurso = cidByCurso;
    }

    public Cidadao getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(Cidadao cooperativa) {
        this.cooperativa = cooperativa;
    }

    public String getBuscaCidadaoCoop() {
        return buscaCidadaoCoop;
    }

    public void setBuscaCidadaoCoop(String buscaCidadaoCoop) {
        this.buscaCidadaoCoop = buscaCidadaoCoop;
    }

    public String getTipoBuscaCidadaoCoop() {
        return tipoBuscaCidadaoCoop;
    }

    public void setTipoBuscaCidadaoCoop(String tipoBuscaCidadaoCoop) {
        this.tipoBuscaCidadaoCoop = tipoBuscaCidadaoCoop;
    }

    public DataModel<Cidadao> getDmListaAlunos() {
        return dmListaAlunos;
    }

    public void setDmListaAlunos(DataModel<Cidadao> dmListaAlunos) {
        this.dmListaAlunos = dmListaAlunos;
    }

    public List<Cidadao> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Cidadao> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public Cidade getCidadeSelect() {
        return cidadeSelect;
    }

    public void setCidadeSelect(Cidade cidadeSelect) {
        this.cidadeSelect = cidadeSelect;
    }

    public Bairro getBairroSelect() {
        return bairroSelect;
    }

    public void setBairroSelect(Bairro bairroSelect) {
        this.bairroSelect = bairroSelect;
    }

    public int getAnocurso() {
        return anocurso;
    }

    public void setAnocurso(int anocurso) {
        this.anocurso = anocurso;
    }
    
    
    
    
    
}
