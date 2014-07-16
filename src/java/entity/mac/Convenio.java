/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mac;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name = "mac_convenio")
public class Convenio implements Serializable {

   
    @Id
     @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TipoConvenio tipoconvenio;
    private String codigo;
    private String siconv;
    private String area;
    @ManyToOne
    private Ministerio ministerio;
    @Column(columnDefinition = "text")
    private String objetivos;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataContraPartida;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date relMonitoramento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date PresContas;
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    @OrderBy(value = "seq desc")
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Vigencia> vigencias = new ArrayList<Vigencia>();
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    @OrderBy(value = "seq desc")
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Aditivo> aditivos = new ArrayList<Aditivo>();
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Contato> contatos = new ArrayList<Contato>();
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Investimento> investimentos = new ArrayList<Investimento>();
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    @OrderBy(value = "datainc desc")
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<ConvHistorico> historicos = new ArrayList<ConvHistorico>();
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    @OrderBy(value = "datainc desc")
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<HistoricoInterno> historicoInternos = new ArrayList<HistoricoInterno>();
    
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    @OrderBy(value = "datacon desc")
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Concedente> concedentes = new ArrayList<Concedente>();
    
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)
    @OrderBy(value = "datacontra desc")
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<ContraPartida> contrapartidas = new ArrayList<ContraPartida>();
    
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)    
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<FluxoFinConcedente> listafluxofinconcedente = new ArrayList<FluxoFinConcedente>();
    
    @OneToMany(mappedBy = "convenio", fetch = FetchType.LAZY)    
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<FluxoFinProponente> listafluxofinproponente = new ArrayList<FluxoFinProponente>();
    @Transient
    private int diasrelatorio;

    public Convenio() {
        this.codigo = "0";
        tipoconvenio = TipoConvenio.FEDERAL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoConvenio getTipoconvenio() {
        return tipoconvenio;
    }

    public void setTipoconvenio(TipoConvenio tipoconvenio) {
        this.tipoconvenio = tipoconvenio;
    }

    public List<Aditivo> getAditivos() {
        return aditivos;
    }

    public void setAditivos(List<Aditivo> aditivos) {
        this.aditivos = aditivos;
    }

    public List<Vigencia> getVigencias() {
        return vigencias;
    }

    public void setVigencias(List<Vigencia> vigencias) {
        this.vigencias = vigencias;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<ConvHistorico> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<ConvHistorico> historicos) {
        this.historicos = historicos;
    }

    public List<HistoricoInterno> getHistoricoInternos() {
        return historicoInternos;
    }

    public void setHistoricoInternos(List<HistoricoInterno> historicoInternos) {
        this.historicoInternos = historicoInternos;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }

    public Ministerio getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(Ministerio ministerio) {
        this.ministerio = ministerio;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getSiconv() {
        return siconv;
    }

    public void setSiconv(String siconv) {
        this.siconv = siconv;
    }

    public Date getPresContas() {
        return PresContas;
    }

    public void setPresContas(Date PresContas) {
        this.PresContas = PresContas;
    }

    public Date getDataContraPartida() {
        return dataContraPartida;
    }

    public void setDataContraPartida(Date dataContraPartida) {
        this.dataContraPartida = dataContraPartida;
    }

    public Date getRelMonitoramento() {
        return relMonitoramento;
    }

    public void setRelMonitoramento(Date relMonitoramento) {
        this.relMonitoramento = relMonitoramento;
    }

    public List<ContraPartida> getContrapartidas() {
        return contrapartidas;
    }

    public void setContrapartidas(List<ContraPartida> contrapartidas) {
        this.contrapartidas = contrapartidas;
    }

    public List<Concedente> getConcedentes() {
        return concedentes;
    }

    public void setConcedentes(List<Concedente> concedentes) {
        this.concedentes = concedentes;
    }
    
    

    public List<FluxoFinConcedente> getListafluxofinconcedente() {
        return listafluxofinconcedente;
    }

    public void setListafluxofinconcedente(List<FluxoFinConcedente> listafluxofinconcedente) {
        this.listafluxofinconcedente = listafluxofinconcedente;
    }

    public List<FluxoFinProponente> getListafluxofinproponente() {
        return listafluxofinproponente;
    }

    public void setListafluxofinproponente(List<FluxoFinProponente> listafluxofinproponente) {
        this.listafluxofinproponente = listafluxofinproponente;
    }

     @Transient    
    public double getTotalFluxoFinProp() {
        double valor = 0;
        for (FluxoFinProponente f : this.listafluxofinproponente) {
            valor = valor + f.getValor();            
        }
        return valor;
    }
       @Transient    
    public double getTotalFluxoFinCon() {
        double valor = 0;
        for (FluxoFinConcedente f : this.listafluxofinconcedente) {
            valor = valor + f.getValor();            
        }
        return valor;
    }
    
    
    @Transient    
    public double getContrapartida() {
        double valor = 0;
        for (Investimento i : this.investimentos) {
            valor = valor + i.getValorproponente();
        }
        return valor;
    }

    @Transient    
    public double getTotal() {
        return this.getContrapartida() + this.getValorministerio();
    }

    @Transient    
    public double getValorministerio() {
        double valor = 0;
        for (Investimento i : this.investimentos) {
            valor = valor + i.getValorconcedente();
        }
        return valor;
    }

    @Transient   
    public int getDiasrelatorio() {
        long tempo1 = new Date().getTime();
        long tempo2 = this.relMonitoramento.getTime();
        long difTempo = tempo2 - tempo1;
        diasrelatorio = (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000)) + 1;
        return diasrelatorio;
    }
}
