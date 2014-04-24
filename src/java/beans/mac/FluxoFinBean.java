/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.mac;

import dao.mac.ConvenioDAO;
import entity.mac.Convenio;
import entity.mac.FluxoFinConcedente;
import entity.mac.FluxoFinProponente;
import entity.mac.TipoConvenio;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.model.chart.PieChartModel;
import util.FacesUtils;

/**
 *
 * @author tenclar
 */
@ManagedBean
@SessionScoped
public final class FluxoFinBean implements Serializable {

    private final FacesUtils facesutils = new FacesUtils();
    private FluxoFinProponente fluxofinprop;
    private FluxoFinConcedente fluxofincon;
    private DataModel DMlistaConvenios = null;
    private List<Convenio> listaConv = null;
    private boolean novofluxo = false;
    private PieChartModel pieModel;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;
    private boolean viewPie = false;
    private String busca;
    private Convenio convenio;

    public FluxoFinBean() {
        createPieModel();
        //createPieteste();
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public void localiza() {
        if ("".equals(this.busca)) {
            this.busca = "%";
        }
        ConvenioDAO convenioDAO = new ConvenioDAO();
        listaConv = convenioDAO.getConvenios(this.busca, TipoConvenio.FEDERAL);
        this.busca = new String();
    }

    public List<Convenio> getListaGeral() {
        ConvenioDAO convenioDAO = new ConvenioDAO();
        List<Convenio> lista = convenioDAO.getConvenios("%");
        //listaConvenios = new ListDataModel(lista);
        return lista;
    }

    @SuppressWarnings("unchecked")
    public DataModel getListaConveniosFederal() {
        //ConvenioDAO cDAO = new ConvenioDAO();
        DMlistaConvenios = null;
        if (listaConv != null) {
            DMlistaConvenios = new ListDataModel(listaConv);
        } 
        //else {
//            listaConv = cDAO.getConvenios("%", TipoConvenio.FEDERAL);
//            DMlistaConvenios = new ListDataModel(listaConv);
//        }
            return DMlistaConvenios;
        }

     

    public List<Convenio> getLista() {

        List<Convenio> lista = new ArrayList<Convenio>();
        lista.add(convenio);
        return lista;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public boolean isViewPie() {
        return viewPie;
    }

    public void setViewPie(boolean viewPie) {
        this.viewPie = viewPie;
    }

    public void editConvenio() {

        ConvenioDAO convenioDAO = new ConvenioDAO();
        Convenio conv = (Convenio) (this.DMlistaConvenios.getRowData());
        this.convenio = convenioDAO.getConvenio(conv.getId());

        this.convenio.getContatos().toString();
        this.convenio.getHistoricos().toString();
        this.convenio.getInvestimentos().toString();
        this.convenio.getVigencias().toString();
        this.convenio.getAditivos().toString();
        //  createPieteste(); gotoviewchart
        createPieModel();
    }

    public String viewFluxoFin() {

        this.editConvenio();
        return "gotoviewchart";
    }

    public FluxoFinProponente getFluxofinprop() {
        return fluxofinprop;
    }

    public void setFluxofinprop(FluxoFinProponente fluxofinprop) {
        this.fluxofinprop = fluxofinprop;
    }

    public FluxoFinConcedente getFluxofincon() {
        return fluxofincon;
    }

    public void setFluxofincon(FluxoFinConcedente fluxofincon) {
        this.fluxofincon = fluxofincon;
    }

    public void fluxofinpronew() {
        this.fluxofinprop = new FluxoFinProponente();
        novofluxo = true;
    }

    public void fluxofinpropadd() {
        try {
            ConvenioDAO cdao = new ConvenioDAO();
            if (novofluxo == true) {
                if ((this.getConvenio().getTotalFluxoFinProp() + this.fluxofinprop.getValor()) <= this.getConvenio().getContrapartida()) {
                    this.fluxofinprop.setConvenio(this.getConvenio());
                    this.getConvenio().getListafluxofinproponente().add(fluxofinprop);
                    cdao.saveConvenio(this.getConvenio());
                    fluxofinprop = null;
                    facesutils.info("Fluxo Cadastrado!");
                } else if ((this.getConvenio().getTotalFluxoFinProp() + this.fluxofinprop.getValor()) > this.getConvenio().getContrapartida()) {
                    throw new Exception("Valor Ultrapassa Limite!");
                }
            } else {
                cdao.saveConvenio(this.getConvenio());
                novofluxo = false;
                fluxofinprop = null;
                facesutils.info("Fluxo Alterado!");
            }
        } catch (Exception e) {
            facesutils.erro(e.getMessage());
        }
    }

    public void fluxofinpropremove() {
        try {
            ConvenioDAO cdao = new ConvenioDAO();
            this.getConvenio().getListafluxofinproponente().remove(fluxofinprop);
            cdao.saveConvenio(this.getConvenio());
            fluxofinprop = null;
            facesutils.info("Item Removido!");

        } catch (Exception e) {
            facesutils.erro("item Não Removido, Motivo:" + e.getMessage());
        }
    }

    public void fluxofinpropcancel() {
        fluxofinprop = null;
    }

    public void fluxofinconnew() {
        this.fluxofincon = new FluxoFinConcedente();
        novofluxo = true;
    }

    public void fluxofinconadd() {
        try {

            ConvenioDAO cdao = new ConvenioDAO();
            if (novofluxo == true) {

                if ((this.getConvenio().getTotalFluxoFinCon() + this.fluxofincon.getValor()) <= this.getConvenio().getValorministerio()) {
                    this.fluxofincon.setConvenio(this.getConvenio());
                    this.getConvenio().getListafluxofinconcedente().add(fluxofincon);
                    cdao.saveConvenio(this.getConvenio());
                    fluxofincon = null;
                    facesutils.info("Fluxo Cadastrado!");
                } else if ((this.getConvenio().getTotalFluxoFinCon() + this.fluxofincon.getValor()) > this.getConvenio().getValorministerio()) {
                    throw new Exception("Valor Ultrapassa Limite!");
                }
            } else {
                cdao.saveConvenio(this.getConvenio());
                novofluxo = false;
                fluxofincon = null;
                facesutils.info("Fluxo Alterado ! ");

            }
        } catch (Exception e) {
            facesutils.erro(e.getMessage());
        }
    }

    public void fluxofinconremove() {
        try {
            ConvenioDAO cdao = new ConvenioDAO();
            this.getConvenio().getListafluxofinconcedente().remove(fluxofincon);
            cdao.saveConvenio(this.getConvenio());
            fluxofincon = null;
            facesutils.info("Item Removido!");

        } catch (Exception e) {
            facesutils.erro("item Não Removido, Motivo:" + e.getMessage());
        }
    }

    public void fluxofinconcancel() {
        fluxofinprop = null;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public PieChartModel getPieModel3() {
        return pieModel3;
    }

    public void setPieModel3(PieChartModel pieModel3) {
        this.pieModel3 = pieModel3;
    }

    private static double converterDoubleDoisDecimais(double precoDouble) {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(precoDouble);
        String[] part = string.split("[,]");
        String string2 = part[0] + "." + part[1];
        double preco = Double.parseDouble(string2);
        return preco;
    }

    public void createPieModel() {
        pieModel = new PieChartModel();
        Map<String, Number> model = new LinkedHashMap<String, Number>();
        pieModel2 = new PieChartModel();
        pieModel3 = new PieChartModel();
        if (this.getConvenio() != null) {
            viewPie = true;
            //  System.out.println("possui convenio");
            double soma = this.getConvenio().getTotalFluxoFinCon() + this.getConvenio().getTotalFluxoFinProp();
            double saldo = converterDoubleDoisDecimais(this.getConvenio().getTotal() - soma);
            model.put("Utilizado", soma);
            model.put("Saldo", saldo);
            pieModel.setData(model);
            pieModel2.set("Utilizado", converterDoubleDoisDecimais(this.getConvenio().getTotalFluxoFinCon()));
            pieModel2.set("Saldo", converterDoubleDoisDecimais(this.getConvenio().getValorministerio() - this.getConvenio().getTotalFluxoFinCon()));

            pieModel3.set("Utilizado", converterDoubleDoisDecimais(this.getConvenio().getTotalFluxoFinProp()));
            pieModel3.set("Saldo", converterDoubleDoisDecimais(this.getConvenio().getContrapartida() - this.getConvenio().getTotalFluxoFinProp()));

        } else {
            //  System.out.println("convenio nulo");
            viewPie = false;
            pieModel.set("nulo1", 4);
            pieModel2.set("nulo2", 5);
            pieModel3.set("nulo3", 3);
        }
    }

    public void clearBean() {
        this.setConvenio(null);
        this.listaConv = null;
        

    }

}
