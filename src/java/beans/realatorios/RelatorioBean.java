/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.realatorios;

import dao.BairroDAO;
import dao.CidadeDAO;
import dao.mci.CidadaoDAO;
import entity.Bairro;
import entity.Cidade;
import entity.mci.Cidadao;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCid;
import entity.mci.enumerator.EnumTipoPessoa;
import entity.relatorio.QuadroQuantitativo;
import entity.relatorio.RelBeneficiario;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
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
    private RelatorioUtil relatorioutil = new RelatorioUtil();
    private int publico;
    private int ramo;
    private int estadocivil;
    private int escolaridade;
    private String possuiatividade;
    private String possuicursos;
    private String possuiequip;
    private String possuibensociais;
    private String possuicursossec;
    private String possuiequipsec;
    private String sexo;
    
    private List<Cidade> listacidade = null;
    private List<Bairro> listabairro = null;
    private Cidade pesquisaCidade;
    private Bairro pesquisaBairro;
    
    private int tiporelatorio;
    private int periodo;
    
    private List<RelBeneficiario> listarelatorio = null;  
    private List<Cidadao> listacidadao = null;
    
    private Panel paneldetalhado = new Panel();
    private Panel paneldetalhadogeral = new Panel();
    private Panel panelresumo = new Panel();
    private Panel panelperiodo = new Panel();

    public RelatorioBean() {
        panelresumo.setRendered(false);
        paneldetalhado.setRendered(false);
        paneldetalhadogeral.setRendered(false);
        panelperiodo.setRendered(false);
    }
    
   

   

    public List<SelectItem> getSelectItemsCidade() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        Cidade c = new Cidade();
        CidadeDAO cidadeDAO = new CidadeDAO();
        c.setId(0);
        c.setNome("TODAS");
        toReturn.add(new SelectItem(c, c.getNome(), null, false, true, true));
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
        toReturn.add(new SelectItem(b, b.getNome(), null, false, true, true));

        if (listabairro != null) {
            for (Bairro ba : listabairro) {
                toReturn.add(new SelectItem(ba, ba.getNome()));
            }
        }
        return toReturn;
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
        if (valor==3) {
         paneldetalhadogeral.setRendered(true);
         paneldetalhado.setRendered(false);       
         panelresumo.setRendered(false);


            
        }
    }
    public void handleSelectItemPeriodo() {
        int valor = this.getPeriodo();
        if (valor == 1) {
            panelperiodo.setRendered(false);
           
            
        }if (valor == 2) {
            panelperiodo.setRendered(true);
           
            
        }
    }
    public List<RelBeneficiario> getListaRelatorio(){
        
         return listarelatorio;
        
    }
    public List<Cidadao> getListaBeneficiarios(){
        return listacidadao;
    }
    
     public void pesquisa(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        
        if (this.tiporelatorio ==1) {
            
            listacidadao = cidadaoDAO.getList("%", EnumTipoPessoa.CID, statusben, statuscid);
        }
        
    }
    //relatorios
    
     public void quadroQuantitativo() throws IOException, JRException {


        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        List<QuadroQuantitativo> lista = cidadaoDAO.getQuadroQuantitativo();
        //String urlrelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("urlrelqq");
        //String nomerelatorio = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString("nomerelqq");
        String urlrelatorio = "mci/cadastro/relatorios/qtdstatus.jasper";
        String nomerelatorio = "quadroquantitativo.pdf";

        relatorioutil.criaRelatorio(lista, urlrelatorio, nomerelatorio);




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

    public int getPublico() {
        return publico;
    }

    public void setPublico(int publico) {
        this.publico = publico;
    }

    public int getRamo() {
        return ramo;
    }

    public void setRamo(int ramo) {
        this.ramo = ramo;
    }

    public int getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(int estadocivil) {
        this.estadocivil = estadocivil;
    }

    public int getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(int escolaridade) {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
