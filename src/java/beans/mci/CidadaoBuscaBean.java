/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mci;

import dao.mci.CidadaoDAO;
import entity.mci.Cidadao;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCid;
import entity.mci.enumerator.EnumTipoPessoa;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import util.FacesUtils;

/**
 *
 * @author NOTE-04
 */
@ManagedBean
@ViewScoped
public class CidadaoBuscaBean {

    private List<Cidadao> listacid;
    private String tipoBusca;
    private String campoPesquisa;
    private FacesUtils facesutils;

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

    public CidadaoBuscaBean() {

        this.listacid = null;
        this.tipoBusca = "cpf";
    }

    @SuppressWarnings("unchecked")
    public DataModel<Cidadao> getLista() {
        DataModel dmLista = null;
        if (listacid != null) {
            dmLista = new ListDataModel(listacid);

        }

        return dmLista;
    }

    public void localiza(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();

        if ("nome".equals(this.tipoBusca)) {
            listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusBeneficio.RESERVA);
        }
        if ("cpf".equals(this.tipoBusca)) {
            listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusBeneficio.RESERVA);
        }
    }

    public void localizaCid(ActionEvent actionEvent) throws Exception {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        try {

            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.CID);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.CID);
            }
            if (listacid.isEmpty()) {
                throw new Exception();
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }
    }

    public void localizaCoop(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        listacid = null;
        try {

            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.COOP, EnumStatusBeneficio.RESERVA);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.COOP, EnumStatusBeneficio.RESERVA);
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }
    }

    public void localizaCoopGeral(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        listacid = null;
        try {

            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.COOP);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.COOP);
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
            this.campoPesquisa = new String();
        }
    }

    public void localizaCoopBen(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        listacid = null;
        try {
            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.COOP, EnumStatusBeneficio.BENEFICIADO);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.COOP, EnumStatusBeneficio.BENEFICIADO);
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }
    }

    public void localizaCidNaoBenNaoAssoc() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        listacid = null;
        try {
            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusBeneficio.RESERVA, EnumStatusCid.INDIVIDUAL);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusBeneficio.RESERVA, EnumStatusCid.INDIVIDUAL);
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }

    }

    public void localizaCidNaoAssoc() {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();
        listacid = null;
        try {
            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusCid.INDIVIDUAL);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusCid.INDIVIDUAL);
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }

    }

    public void localizaCidBenInd(ActionEvent actionEvent) {
        CidadaoDAO cidadaoDAO = new CidadaoDAO();

        listacid = null;
        try {
            if ("nome".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getList(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusBeneficio.BENEFICIADO, EnumStatusCid.INDIVIDUAL);
            }
            if ("cpf".equals(this.tipoBusca)) {
                listacid = cidadaoDAO.getListByCnp(this.campoPesquisa, EnumTipoPessoa.CID, EnumStatusBeneficio.BENEFICIADO, EnumStatusCid.INDIVIDUAL);
            }
        } catch (Exception e) {
            facesutils.erro("Pesquisa não encontrada");
        }

    }

    public void clear() {
        this.campoPesquisa = null;
        this.listacid = null;

    }
}
