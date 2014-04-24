/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.BairroDAO;
import dao.CidadeDAO;
import dao.EstadoDAO;
import dao.RegionalDAO;
import entity.*;
  


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

/**
 *
 * @author tecnologia01
 */
@ManagedBean
@ViewScoped
public class BairroBean implements Serializable {

    private FacesContext context;
    private FacesUtils facesutils = new FacesUtils();
    private DataModel listabairros = null;
    private Bairro bairro;
    
    
    private List<Bairro> listabairro = null;
    private List<Cidade> listacidade = null;
    private List<Estado> listaestado = null;
    private List<Regional> listaregional = null;
    private Endereco endereco;
    private Cidade pesquisaCidade;
    private Estado pesquisaEstado;
    private String busca;
    private boolean edit = false;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public Cidade getPesquisaCidade() {
        return pesquisaCidade;
    }

    public void setPesquisaCidade(Cidade pesquisaCidade) {
        this.pesquisaCidade = pesquisaCidade;
    }

    public Estado getPesquisaEstado() {
        return pesquisaEstado;
    }

    public void setPesquisaEstado(Estado pesquisaEstado) {
        this.pesquisaEstado = pesquisaEstado;
    }

    @SuppressWarnings("unchecked")
    public DataModel getListaBairros() {
        listabairros = null;
        if (listabairro != null) {
            listabairros = new ListDataModel(listabairro);
        }
        return listabairros;
    }
    
    public List<SelectItem> getSelectItemsRegional() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        RegionalDAO rdao = new RegionalDAO();                    
        for (Regional r : rdao.getRegionals()) {
            toReturn.add(new SelectItem(r, r.getNome()));
        }

        return toReturn;

    }

    public List<SelectItem> getSelectItemsEstado() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado est = new Estado();
        est.setId(0);
        est.setNome("Selecione");
        toReturn.add(new SelectItem(est, est.getNome()));
        if (listaestado == null) {
            listaestado = estadoDAO.getEstados();
        }
        for (Estado uf : listaestado) {
            toReturn.add(new SelectItem(uf, uf.getSigla()));
        }

        return toReturn;

    }

    public void handleEstadoChange() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        if (bairro.getCidade().getEstado().getId() != 0) {
            this.listacidade = cidadeDAO.getCidades(bairro.getCidade().getEstado().getId());
        } else {
            this.listacidade = null;
            this.getSelectItemsCidade().clear();
        }

    }

    public List<SelectItem> getSelectItemsCidade() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        Cidade c = new Cidade();
        c.setId(0);
        c.setNome("Selecione");
        toReturn.add(new SelectItem(c, c.getNome(), null, false, true, true));
        if (listacidade != null) {

            for (Cidade cid : listacidade) {
                toReturn.add(new SelectItem(cid, cid.getNome()));
            }
        }
        return toReturn;
    }

    public void handlePesquisaEstadoChange() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        if (this.pesquisaEstado.getId() != 0) {
            this.listacidade = cidadeDAO.getCidades(this.pesquisaEstado.getId());
        } else {
            listacidade = null;
            getSelectItemsCidade().clear();

        }

    }

    public Bairro getBairro() {
        return this.bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public void newBairro(ActionEvent actionEvent) {
        this.bairro = new Bairro();



    }

    public void newBairroEnd(Estado e, Cidade c) {

        this.bairro = new Bairro();
        if ((e != null) || (c != null)) {
            this.bairro.getCidade().setEstado(e);
            this.handleEstadoChange();
            this.bairro.setCidade(c);
        }

    }

    public void saveBairro(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        BairroDAO bairroDAO = new BairroDAO();
        try {
            if (this.edit == true) {
                
                 bairroDAO.saveBairro(this.bairro);                
                 context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Alterado!"));
                 this.edit = false;
                 
            } else {
                listabairro = bairroDAO.getBairros(this.bairro.getNome(), this.bairro.getCidade().getId());
                if (listabairro.size() > 0) {
                    throw new Exception("Já Existe");
                } else {
                    bairroDAO.saveBairro(this.bairro);                    
                    context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
                }
                listabairro = null;
                this.bairro = null;
            }


        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }

    public void editBairro(ActionEvent actionEvent) {
        BairroDAO bairroDAO = new BairroDAO();
        this.bairro = (Bairro) (this.listabairros.getRowData());
        this.bairro = bairroDAO.getBairro(bairro.getId());
        this.handleEstadoChange();
        this.edit = true;

    }

    public void updateBairro(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        BairroDAO bairroDAO = new BairroDAO();

        try {
            bairroDAO.updateBairro(bairro);
            listabairro = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelBairro() {
        this.bairro = null;
    }

    public void buscaDetalhado(ActionEvent actionEvent) {
        /// listaendereco = null;
        BairroDAO bairrodao = new BairroDAO();
        try {
            listabairro = bairrodao.getBairro(this.busca, this.pesquisaCidade.getId());

            if (listabairro.isEmpty()) {

                throw new Exception("Bairro não encontrado");
            }
        } catch (Exception e) {
            facesutils.erro(e.getLocalizedMessage());
        }
    }
}