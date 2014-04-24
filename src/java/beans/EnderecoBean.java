/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.BairroDAO;
import dao.CidadeDAO;
import dao.EnderecoDAO;
import dao.EstadoDAO;
import entity.Bairro;
import entity.Cidade;
import entity.Endereco;
import entity.Estado;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.model.DataModel;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import util.FacesUtils;

/**
 *
 * @author tecnologia01
 */
@ManagedBean
@ViewScoped
public class EnderecoBean implements Serializable {

    private FacesUtils facesUtils = new FacesUtils();
    private String buscaendereco;
    private FacesContext context;
    private DataModel dmLista;
    private Endereco endereco;
    private BairroDAO bairroDAO = null;
    private CidadeDAO cidadeDAO = null;
    private EstadoDAO estadoDAO = null;
    private List<Bairro> listabairro = null;
    private List<Cidade> listacidade = null;
    private List<Estado> listaestado = null;
    private Estado pesquisaEstado;
    private Cidade pesquisaCidade;
    private Bairro pesquisaBairro;
    private List<Endereco> listaendereco = null;
    private List<Endereco> lista = null;
    private Bairro bairro;

    public Bairro getPesquisaBairro() {
        return pesquisaBairro;
    }

    public void setPesquisaBairro(Bairro pesquisaBairro) {
        this.pesquisaBairro = pesquisaBairro;
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

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getBuscaendereco() {
        return buscaendereco;
    }

    public void setBuscaendereco(String buscaendereco) {
        this.buscaendereco = buscaendereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public DataModel getDataModelListaEndereco() {
        dmLista = null;
        if (listaendereco != null) {
            dmLista = new ListDataModel(listaendereco);
        }
        return dmLista;
    }

    @SuppressWarnings("unchecked")
    public DataModel getDataModelLista() {

        if (lista == null) {
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            lista = enderecoDAO.getEnderecos();
        }
        dmLista = new ListDataModel(lista);
        return dmLista;
    }

    public List<SelectItem> getSelectItemsEstado() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        estadoDAO = new EstadoDAO();
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
        cidadeDAO = new CidadeDAO();
        if (this.endereco.getBairro().getCidade().getEstado().getId() != 0) {
            this.listacidade = cidadeDAO.getCidades(this.endereco.getBairro().getCidade().getEstado().getId());
        } else {
            listacidade = null;
            listabairro = null;
            getSelectItemsCidade().clear();
            getSelectItemsBairro().clear();
        }

    }

    public void handlePesquisaEstadoChange() {
        cidadeDAO = new CidadeDAO();
        if (this.pesquisaEstado.getId() != 0) {
            this.listacidade = cidadeDAO.getCidades(this.pesquisaEstado.getId());
        } else {
            listacidade = null;
            listabairro = null;
            getSelectItemsCidade().clear();
            getSelectItemsBairro().clear();
        }

    }

    public List<SelectItem> getSelectItemsCidade() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        Cidade c = new Cidade();
        c.setId(0);
        c.setNome("Selecione");
        toReturn.add(new SelectItem(c, c.getNome(), null, false, true, true));
        if (this.listacidade != null) {
            for (Cidade cid : listacidade) {
                toReturn.add(new SelectItem(cid, cid.getNome()));
            }
        }
        return toReturn;
    }

    public void handleCidadeChange() {
        bairroDAO = new BairroDAO();
        if (endereco.getBairro().getCidade().getId() != 0) {
            this.listabairro = bairroDAO.getBairros(endereco.getBairro().getCidade().getId());
        } else {
            listabairro = null;
        }

    }

    public void handlePesquisaCidadeChange() {
        bairroDAO = new BairroDAO();
        if (pesquisaCidade.getId() != 0) {
            this.listabairro = bairroDAO.getBairros(pesquisaCidade.getId());
        } else {
            listabairro = null;
        }

    }

    public List<SelectItem> getSelectItemsBairro() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        Bairro b = new Bairro();
        b.setId(0);
        b.setNome("Todos");
        toReturn.add(new SelectItem(b, b.getNome(), null, false, true, true));

        if (listabairro != null) {
            for (Bairro ba : listabairro) {
                toReturn.add(new SelectItem(ba, ba.getNome()));
            }
        }
        return toReturn;
    }

    public void newEndereco(ActionEvent actionEvent) {
        this.endereco = new Endereco();
        //this.listabairro = null;
        //this.listacidade = null;
    }
    public void newEnd(Estado e, Cidade c, String logradouro) {
        this.endereco = new Endereco();
        if ((e != null) || (c != null)) {
            this.endereco.getBairro().getCidade().setEstado(e);
            this.handleEstadoChange();
            this.endereco.getBairro().setCidade(c);
            this.endereco.setLogradouro(logradouro);
        }

        
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

        try {
            bairroDAO = new BairroDAO();
            listabairro = bairroDAO.getBairros(this.bairro.getNome(), this.bairro.getCidade().getId());

            if (listabairro.size() > 0) {
                throw new Exception("Já Existe");
            } else {
                bairroDAO.saveBairro(this.bairro);
                listabairro = null;
                this.handleCidadeChange();

                context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
            }


        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }


    }
    public void saveEnderecos(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        try {

            enderecoDAO.saveEndereco(this.endereco);
            this.lista = null;

            // this.handlePesquisaCidadeChange();
            this.buscaendereco = this.endereco.getLogradouro();
            this.endereco = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));



        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! "));

        }


    }
    public void saveEndereco(ActionEvent actionEvent) {
        context = FacesContext.getCurrentInstance();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        try {

            enderecoDAO.saveEndereco(this.endereco);
            this.lista = null;

            // this.handlePesquisaCidadeChange();
            this.buscaendereco = this.endereco.getLogradouro();
            this.endereco = null;
            this.buscaEnderecoDetalhado(actionEvent);
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));



        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! "));

        }


    }

    public void editEndereco(ActionEvent actionEvent) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        this.endereco = (Endereco) (this.dmLista.getRowData());
        this.endereco = enderecoDAO.getEndereco(this.endereco.getId());
        handleEstadoChange();
        handleCidadeChange();

    }

    public void updateEndereco() {
        context = FacesContext.getCurrentInstance();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        try {

            enderecoDAO.updateEndereco(endereco);
            this.lista = null;
            context.addMessage(null, new FacesMessage("Sucesso", "Cadastro Efetuado!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Cadastro Não Efetuado! " + e.getMessage()));
        }

    }

    public void cancelEndereco() {
        this.endereco = null;
    }

    public void cancelBairro() {
        this.bairro = null;
    }

    public void localizaEndereco(ActionEvent actionEvent) {
        listaendereco = null;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        listaendereco = enderecoDAO.getEnderecos(this.buscaendereco);
    }

    public void buscaEnderecoPorNome(ActionEvent actionEvent) {
        listaendereco = null;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        listaendereco = enderecoDAO.getEnderecosMaxResult(this.buscaendereco);
    }

    public void buscaEnderecoDetalhado(ActionEvent actionEvent) {
       /// listaendereco = null;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        try{
        listaendereco = enderecoDAO.getEnderecosDetalhado(pesquisaEstado, pesquisaCidade, pesquisaBairro, this.buscaendereco);
        if (listaendereco.isEmpty()) {
           
            throw  new Exception("Endereço não encontrado");
        }
        }catch(Exception e){
             facesUtils.erro(e.getLocalizedMessage());
        }
    }

    public void enderecoClear() {
        listaendereco = null;
        this.buscaendereco = null;
        this.pesquisaBairro = new Bairro();
        this.pesquisaCidade = new Cidade();
        this.pesquisaEstado = new Estado();
        this.pesquisaEstado.setId(0);
        this.handlePesquisaEstadoChange();


    }
}
