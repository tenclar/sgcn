package beans.mgc;

import dao.mci.CidadaoDAO;
import dao.mci.CursoSecretariaDAO;
import dao.mgc.CursoDAO;
import dao.mgc.TurmaDAO;
import entity.mci.Cidadao;
import entity.mci.CursosSecretaria;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCurso;
import entity.mci.enumerator.EnumStatusTurma;

import entity.mci.enumerator.EnumTipoPessoa;

import entity.mgc.Curso;
import entity.mgc.TurCidadaos;
import entity.mgc.Turma;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import util.FacesUtils;

@ManagedBean
@ViewScoped
public class TurmaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String busca;
    private String buscaCidadao;
    private String buscaCidadaoCoop;
    private String tipoBusca = "nome";
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

        TurmaDAO turmaDAO = new TurmaDAO();
        listaTurmas = turmaDAO.getTurmas(this.busca);
        this.busca = "";
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
            this.listaCidadaos = turmaDAO.getCidadaosByCnp(this.buscaCidadao, EnumTipoPessoa.COOP, false, this.turma.getCurso().getId());
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
}
