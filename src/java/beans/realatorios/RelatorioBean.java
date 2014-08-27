/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.realatorios;

import dao.BairroDAO;
import dao.CidadeDAO;
import dao.mci.CidadaoDAO;
import dao.mci.PublicoDAO;
import entity.Bairro;
import entity.Cidade;
import entity.mci.Cidadao;
import entity.mci.Escolaridade;
import entity.mci.EstadoCivil;
import entity.mci.Publico;
import entity.mci.RamoEmpreendimento;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCid;
import entity.mci.enumerator.EnumTipoPessoa;
import entity.relatorio.QuadroQuantitativo;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.component.panel.Panel;
import util.RelatorioUtil;

/**
 *
 * @author tenclar
 */
@ManagedBean
@ViewScoped
public class RelatorioBean implements Serializable {

    private EnumStatusBeneficio statusben;
    private EnumStatusCid statuscid;
    private final RelatorioUtil relatorioutil = new RelatorioUtil();

    private String possuiatividade;
    private String possuicursos;
    private String possuiequip;
    private String possuibensociais;
    private String possuicursossec;
    private String possuiequipsec;
    private String genero;

    private List<Cidade> listacidade = null;
    private List<Bairro> listabairro = null;
    private Cidade pesquisaCidade;
    private Bairro pesquisaBairro;

    private Publico publico;
    private RamoEmpreendimento ramo;
    private EstadoCivil estadocivil;
    private Escolaridade escolaridade;

    private int tiporelatorio = 1;
    private int periodo = 1;

    private List<Cidadao> listacidadao = null;

    private List<String> selectresumo = new ArrayList<String>();
    private List<String> selectgroup = new ArrayList<String>();
    private String selectonegroup = "CIDADE";

    private Panel paneldetalhado = new Panel();
    private Panel paneldetalhadogeral = new Panel();
    private Panel panelresumo = new Panel();
    private Panel panelperiodo = new Panel();

    private boolean disableGroup = false;
    private boolean colunacidade = false;
    private boolean colunabairro;
    private boolean colunaramo;
    private boolean colunalogradouro;
    private boolean colunarg;
    private boolean colunanis;
    private boolean colunasexo;
    private boolean colunaescolaridade;
    private boolean colunatelefone;
    private boolean colunaequip;
    private boolean colunapublico;
    private boolean colunaidade;

    public RelatorioBean() {
        periodo = 1;
        selectgroup.add("CIDADE");
        panelresumo.setRendered(false);
        paneldetalhado.setRendered(false);
        paneldetalhadogeral.setRendered(false);
        panelperiodo.setRendered(false);

//        selectresumo.add("CIDADE");
//        selectresumo.add("BAIRRO");
//        selectresumo.add("RAMO");
//        selectresumo.add("NOME");
//        selectresumo.add("LOGRADOURO");
//        selectresumo.add("CPF");
//        selectresumo.add("IDADE");
//        selectresumo.add("ESCOLARIDADE");
//        selectresumo.add("TELEFONE");
//        selectresumo.add("EQUIP");
//        selectresumo.add("DEMANDA");
    }

   
    public String pesquisa() throws IOException, JRException {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        List<Cidadao> lista;
        String urlrelatorio;
        String nomearquivo = "relatorio";
        Map parameters = new HashMap();
        parameters.put("grupo",selectonegroup);
        parameters.put("tipobeneficio", statuscid.toString());
        parameters.put("statusben", statusben.toString());        
         parameters.put("possuicursossec", possuicursossec);
         parameters.put("possuiequipsec",possuiequipsec);
         parameters.put("publico",publico.getNome());
         parameters.put("ramo", ramo.getNome());
         parameters.put("genero", genero);
         parameters.put("estadocivil", estadocivil.getDescricao());
         parameters.put("cidade", pesquisaCidade.getNome());
         parameters.put("bairro", pesquisaBairro.getNome());
         parameters.put("escolaridade", escolaridade.getAno()+"/"+escolaridade.getGrau());
 
        if (this.periodo == 1) {
        

            if (selectonegroup.equals("CIDADE")) {
                
//                System.out.println(" StatusBen: " + statusben);
//                System.out.println(" StatusCid: " + statuscid);
//                System.out.println(" Cursos: " + possuicursossec);
//                System.out.println(" Equipamentos: " + possuiequipsec);
//                System.out.println(" Publico: " + publico);
//                System.out.println(" Ramo: " + ramo);
//                System.out.println(" genero: " + genero);
//                System.out.println(" estadocivil: " + estadocivil);
//                System.out.println(" escolaridade: " + escolaridade);
//                System.out.println(" cidade: " + pesquisaCidade);
//                System.out.println(" bairro: " + pesquisaBairro);
                lista = cidadaoDAO.getListRelatorio(EnumTipoPessoa.CID, statusben, statuscid, possuicursossec, possuiequipsec, publico, ramo, genero, estadocivil, pesquisaCidade, pesquisaBairro, escolaridade,selectonegroup);
                urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_lista_ben_por_cidade");
                new RelatorioUtil().criaRelatorio(lista, urlrelatorio, nomearquivo,parameters);
            }
            if (selectonegroup.equals("CIDADEBAIRRO")) {
                lista = cidadaoDAO.getListRelatorio(EnumTipoPessoa.CID, statusben, statuscid, possuicursossec, possuiequipsec, publico, ramo, genero, estadocivil, pesquisaCidade, pesquisaBairro, escolaridade, selectonegroup);
                urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_lista_ben_por_cidade_bairro");
                new RelatorioUtil().criaRelatorio(lista, urlrelatorio, nomearquivo,parameters);
                
            }
            
            
            if (selectonegroup.equals("DEMANDACIDADEBAIRRO")) {
                lista = cidadaoDAO.getListRelatorio(EnumTipoPessoa.CID, statusben, statuscid, possuicursossec, possuiequipsec, publico, ramo, genero, estadocivil, pesquisaCidade, pesquisaBairro, escolaridade, selectonegroup);
                urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_lista_ben_por_demanda_cidade_bairro");
                new RelatorioUtil().criaRelatorio(lista, urlrelatorio, nomearquivo,parameters);
                
            }

        }

        return null;

    }

    public List<String> getSelectresumo() {
        return selectresumo;
    }

    public void setSelectresumo(List<String> selectresumo) {
        this.selectresumo = selectresumo;
    }

    public List<String> getSelectgroup() {
        return selectgroup;
    }

    public void setSelectgroup(List<String> selectgroup) {
        this.selectgroup = selectgroup;
    }

    public void handlePesquisaCidadeChange() {
        BairroDAO bairroDAO = new BairroDAO();
        if (pesquisaCidade.getId() != 0) {
            this.listabairro = bairroDAO.getBairros(pesquisaCidade.getId());
        } else {
            listabairro = null;
            getSelectItemsCidade().clear();
            getSelectItemsBairro().clear();
        }

    }

    public void handleSelectItemTipoRelatorio() {
        int valor = this.getTiporelatorio();
        if (valor == 1) {
            panelresumo.setRendered(true);
            paneldetalhado.setRendered(false);
            paneldetalhadogeral.setRendered(false);

        }
        if (valor == 2) {
            paneldetalhado.setRendered(true);
            paneldetalhadogeral.setRendered(false);
            panelresumo.setRendered(false);

        }
        if (valor == 3) {
            paneldetalhadogeral.setRendered(true);
            paneldetalhado.setRendered(false);
            panelresumo.setRendered(false);

        }
    }

    public void handleSelectItemPeriodo() {
        int valor = this.getPeriodo();
        if (valor == 1) {
            panelperiodo.setRendered(false);

        }
        if (valor == 2) {
            panelperiodo.setRendered(true);

        }
    }

    public void listenerSelectGroup() {
        if (selectgroup.size() >= 3) {
            this.disableGroup = true;
        }

    }

    public void ativagroup() {
        this.disableGroup = false;
        selectgroup.clear();
    }

    public List<Cidadao> getListaBeneficiarios() {
        return listacidadao;
    }

    public List<SelectItem> getSelectItemStatusCid() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (EnumStatusCid c : EnumStatusCid.values()) {
            toReturn.add(new SelectItem(c.toString(), c.toString()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemStatusBen() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        for (EnumStatusBeneficio c : EnumStatusBeneficio.values()) {
            toReturn.add(new SelectItem(c.toString(), c.toString()));
        }
        return toReturn;
    }

    public List<SelectItem> getSelectItemsPublico() {
        PublicoDAO publicoDAO = new PublicoDAO();
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        Publico p = new Publico();
        p.setId(0);
        p.setNome("TODOS");
        toReturn.add(new SelectItem(p, p.getNome()));
        for (Publico pb : publicoDAO.getPublicos()) {
            toReturn.add(new SelectItem(pb,pb.getId()+" | "+ pb.getNome()));
        }
        return toReturn;
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

    //relatorios
    public void quadroQuantitativo() throws IOException, JRException {

        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        List<QuadroQuantitativo> lista = cidadaoDAO.getQuadroQuantitativo();
        //String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("urlrelqq");
        //String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nomerelqq");
        String urlrelatorio = "mci/relatorios/quantitativos/qtdstatus.jasper";
        String nomerelatorio = "quadroquantitativo.pdf";
        

        relatorioutil.criaRelatorio(lista, urlrelatorio, nomerelatorio);

    }

    public void imprimirequipind() throws IOException, JRException {

        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        List<Cidadao> lista = cidadaoDAO.getListEquipamentosSecretaria(EnumTipoPessoa.CID);

        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_lita_equip_ind");
        String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nome_lista_equip");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, nomerelatorio);
    }

    public void imprimirequipcol() throws IOException, JRException {

        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        List<Cidadao> lista = cidadaoDAO.getListEquipamentosSecretaria(EnumTipoPessoa.COOP);
        String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("url_lita_equip_col");
        String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nome_lista_equip");
        new RelatorioUtil().criaRelatorio(lista, urlrelatorio, nomerelatorio);

    }

//    if (selectresumo.contains("BAIRRO")) {
//                    this.colunabairro = true;
//                }
//                if (selectresumo.contains("RAMO")) {
//                    this.colunaramo = true;
//                }
//
//                if (selectresumo.contains("LOGRADOURO")) {
//                    this.colunalogradouro = true;
//                }
//                if (selectresumo.contains("RG")) {
//                    this.colunarg = true;
//                }
//                if (selectresumo.contains("NIS")) {
//                    this.colunanis = true;
//                }
//                if (selectresumo.contains("SEXO")) {
//                    this.colunasexo = true;
//                }
//                if (selectresumo.contains("IDADE")) {
//                    this.colunaidade = true;
//                }
//                if (selectresumo.contains("ESCOLARIDADE")) {
//                    this.colunaescolaridade = true;
//                }
//                if (selectresumo.contains("TELEFONE")) {
//                    this.colunatelefone = true;
//                }
//                if (selectresumo.contains("EQUIP")) {
//                    this.colunaequip = true;
//                }
//                if (selectresumo.contains("PUBLICO")) {
//                    this.colunapublico = true;
//                }
    
    
    
     public boolean isDisableGroup() {
        return disableGroup;
    }

    public void setDisableGroup(boolean disableGroup) {
        this.disableGroup = disableGroup;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Panel getPanelperiodo() {
        return panelperiodo;
    }

    public void setPanelperiodo(Panel panelperiodo) {
        this.panelperiodo = panelperiodo;
    }

    public Panel getPanelresumo() {
        return panelresumo;
    }

    public void setPanelresumo(Panel panelresumo) {
        this.panelresumo = panelresumo;
    }

    public Panel getPaneldetalhado() {
        return paneldetalhado;
    }

    public void setPaneldetalhado(Panel paneldetalhado) {
        this.paneldetalhado = paneldetalhado;
    }

    public Panel getPaneldetalhadogeral() {
        return paneldetalhadogeral;
    }

    public void setPaneldetalhadogeral(Panel paneldetalhadogeral) {
        this.paneldetalhadogeral = paneldetalhadogeral;
    }

    public int getTiporelatorio() {
        return tiporelatorio;
    }

    public void setTiporelatorio(int tiporelatorio) {
        this.tiporelatorio = tiporelatorio;
    }

    public Cidade getPesquisaCidade() {
        return pesquisaCidade;
    }

    public void setPesquisaCidade(Cidade pesquisaCidade) {
        this.pesquisaCidade = pesquisaCidade;
    }

    public Bairro getPesquisaBairro() {
        return pesquisaBairro;
    }

    public void setPesquisaBairro(Bairro pesquisaBairro) {
        this.pesquisaBairro = pesquisaBairro;
    }

    public EnumStatusBeneficio getStatusben() {
        return statusben;
    }

    public void setStatusben(EnumStatusBeneficio statusben) {
        this.statusben = statusben;
    }

    public EnumStatusCid getStatuscid() {
        return statuscid;
    }

    public void setStatuscid(EnumStatusCid statuscid) {
        this.statuscid = statuscid;
    }

    public Publico getPublico() {
        return publico;
    }

    public void setPublico(Publico publico) {
        this.publico = publico;
    }

    public RamoEmpreendimento getRamo() {
        return ramo;
    }

    public void setRamo(RamoEmpreendimento ramo) {
        this.ramo = ramo;
    }

    public EstadoCivil getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(EstadoCivil estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getPossuiatividade() {
        return possuiatividade;
    }

    public void setPossuiatividade(String possuiatividade) {
        this.possuiatividade = possuiatividade;
    }

    public String getPossuicursos() {
        return possuicursos;
    }

    public void setPossuicursos(String possuicursos) {
        this.possuicursos = possuicursos;
    }

    public String getPossuiequip() {
        return possuiequip;
    }

    public void setPossuiequip(String possuiequip) {
        this.possuiequip = possuiequip;
    }

    public String getPossuibensociais() {
        return possuibensociais;
    }

    public void setPossuibensociais(String possuibensociais) {
        this.possuibensociais = possuibensociais;
    }

    public String getPossuicursossec() {
        return possuicursossec;
    }

    public void setPossuicursossec(String possuicursossec) {
        this.possuicursossec = possuicursossec;
    }

    public String getPossuiequipsec() {
        return possuiequipsec;
    }

    public void setPossuiequipsec(String possuiequipsec) {
        this.possuiequipsec = possuiequipsec;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isColunacidade() {
        return colunacidade;
    }

    public void setColunacidade(boolean colunacidade) {
        this.colunacidade = colunacidade;
    }

    public boolean isColunabairro() {
        return colunabairro;
    }

    public void setColunabairro(boolean colunabairro) {
        this.colunabairro = colunabairro;
    }

    public boolean isColunaramo() {
        return colunaramo;
    }

    public void setColunaramo(boolean colunaramo) {
        this.colunaramo = colunaramo;
    }

    public boolean isColunalogradouro() {
        return colunalogradouro;
    }

    public void setColunalogradouro(boolean colunalogradouro) {
        this.colunalogradouro = colunalogradouro;
    }

    public boolean isColunarg() {
        return colunarg;
    }

    public void setColunarg(boolean colunarg) {
        this.colunarg = colunarg;
    }

    public boolean isColunaidade() {
        return colunaidade;
    }

    public void setColunaidade(boolean colunaidade) {
        this.colunaidade = colunaidade;
    }

    public boolean isColunanis() {
        return colunanis;
    }

    public void setColunanis(boolean colunanis) {
        this.colunanis = colunanis;
    }

    public boolean isColunasexo() {
        return colunasexo;
    }

    public void setColunasexo(boolean colunasexo) {
        this.colunasexo = colunasexo;
    }

    public boolean isColunaescolaridade() {
        return colunaescolaridade;
    }

    public void setColunaescolaridade(boolean colunaescolaridade) {
        this.colunaescolaridade = colunaescolaridade;
    }

    public boolean isColunatelefone() {
        return colunatelefone;
    }

    public void setColunatelefone(boolean colunatelefone) {
        this.colunatelefone = colunatelefone;
    }

    public boolean isColunaequip() {
        return colunaequip;
    }

    public void setColunaequip(boolean colunaequip) {
        this.colunaequip = colunaequip;
    }

    public boolean isColunapublico() {
        return colunapublico;
    }

    public void setColunapublico(boolean colunapublico) {
        this.colunapublico = colunapublico;
    }

    public String getSelectonegroup() {
        return selectonegroup;
    }

    public void setSelectonegroup(String selectonegroup) {
        this.selectonegroup = selectonegroup;
    }

}
