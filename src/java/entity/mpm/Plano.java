/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mpm;
import entity.Endereco;
import entity.mci.Cidadao;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author tenclar
 */

@Entity
@Table(name="mpm_plano")
public class Plano implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datacreated;
    @Column(name="historico", columnDefinition="TEXT")
    private String historico;
    private String propaganda;
    private String propagandaforma;
    private String comentario;    
    private String nomeempreendimento;
    private int tempoatividade;
    private int endnum;     
    
    @ManyToOne
    private Responsavel responsavel;
    @ManyToOne
    private AtividadeEmp atividade;
    @ManyToOne
    private Projeto projeto;
    @ManyToOne
    private Endereco endereco;
     @ManyToOne
    private Cidadao cidadao;
    @ManyToOne
    @JoinColumn(name="microcredito_id")
    @Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private Microcredito microcredito;
    
    @ManyToOne
    private PontoComercial pontocomercial;
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<Produtos> produtos;
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<Servicos> servicos;    
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<MetasPessoais> metaspessoais;
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<MetasEmpresariais> metasempresariais;
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<PontosFracosInternos> pontosfracosinternos;
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<PontosFracosExternos> pontosfracosexternos;
    
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<PontosFortesInternos> pontosfortesinternos;
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<PontosFortesExternos> pontosfortesexternos;
    
    
    
        
    @OneToMany(mappedBy = "plano")    
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<Rendimento> rendimentos = new ArrayList<Rendimento>();
    
    @OneToMany(mappedBy = "plano")    
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<HistoricoVisita> historicovisitas = new ArrayList<HistoricoVisita>();
    
    
    @OneToMany(mappedBy = "plano")
    @Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
    private List<Foto> fotos;

    public Plano() {
        
          this.datacreated = new Date();  
          this.atividade = new AtividadeEmp();
          this.pontocomercial = new PontoComercial();
          this.microcredito = new Microcredito();
          this.responsavel = new Responsavel();
          this.cidadao = new Cidadao();
          this.endereco = new Endereco();
          this.projeto = new Projeto();
    }
    
    
    
    
    public int getEndnum() {
        return endnum;
    }

    public void setEndnum(int endnum) {
        this.endnum = endnum;
    }

    public Date getDatacreated() {
        return datacreated;
    }

    public void setDatacreated(Date datacreated) {
        this.datacreated = datacreated;
    }
    
    
    
    

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }
    
    

   

    public AtividadeEmp  getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeEmp atividade) {
        this.atividade = atividade;
    }
    
    

    public String getNomeempreendimento() {
        return nomeempreendimento;
    }

    public void setNomeempreendimento(String nomeempreendimento) {
        this.nomeempreendimento = nomeempreendimento;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

   

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HistoricoVisita> getHistoricovisitas() {
        return historicovisitas;
    }

    public void setHistoricovisitas(List<HistoricoVisita> historicovisitas) {
        this.historicovisitas = historicovisitas;
    }
    
    

    public List<MetasEmpresariais> getMetasempresariais() {
        return metasempresariais;
    }

    public void setMetasempresariais(List<MetasEmpresariais> metasempresariais) {
        this.metasempresariais = metasempresariais;
    }

    public List<MetasPessoais> getMetaspessoais() {
        return metaspessoais;
    }

    public void setMetaspessoais(List<MetasPessoais> metaspessoais) {
        this.metaspessoais = metaspessoais;
    }

    public Microcredito getMicrocredito() {
        return microcredito;
    }

    public void setMicrocredito(Microcredito microcredito) {
        this.microcredito = microcredito;
    }

    public PontoComercial getPontocomercial() {
        return pontocomercial;
    }

    public void setPontocomercial(PontoComercial pontocomercial) {
        this.pontocomercial = pontocomercial;
    }

    public List<PontosFortesExternos> getPontosfortesexternos() {
        return pontosfortesexternos;
    }

    public void setPontosfortesexternos(List<PontosFortesExternos> pontosfortesexternos) {
        this.pontosfortesexternos = pontosfortesexternos;
    }

    public List<PontosFortesInternos> getPontosfortesinternos() {
        return pontosfortesinternos;
    }

    public void setPontosfortesinternos(List<PontosFortesInternos> pontosfortesinternos) {
        this.pontosfortesinternos = pontosfortesinternos;
    }

    public List<PontosFracosExternos> getPontosfracosexternos() {
        return pontosfracosexternos;
    }

    public void setPontosfracosexternos(List<PontosFracosExternos> pontosfracosexternos) {
        this.pontosfracosexternos = pontosfracosexternos;
    }

    public List<PontosFracosInternos> getPontosfracosinternos() {
        return pontosfracosinternos;
    }

    public void setPontosfracosinternos(List<PontosFracosInternos> pontosfracosinternos) {
        this.pontosfracosinternos = pontosfracosinternos;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
    
     public String getProdutosTotal() {
        DecimalFormat df = new DecimalFormat("R$  ###,##0.00");        
           Double valor = 0.0; 
            for (Produtos p :getProdutos()){
                valor = valor + p.getValor();
            }            
        return  df.format(valor);
    }
    
     public String getServicosTotal() {
        DecimalFormat df = new DecimalFormat("R$  ###,##0.00");        
           Double valor = 0.0; 
            for (Servicos ob :getServicos()){
                valor = valor + ob.getValor();
            }            
        return  df.format(valor);
    }
      public String getRendimentosTotal() {
        DecimalFormat df = new DecimalFormat("R$  ###,##0.00");        
           Double valor = 0.0; 
            for (Rendimento ob :getRendimentos()){
                valor = valor + ob.getTotalRenda();
            }            
        return  df.format(valor);
    }
     
    public String getPropaganda() {
        return propaganda;
    }

    public void setPropaganda(String propaganda) {
        this.propaganda = propaganda;
    }

    public String getPropagandaforma() {
        return propagandaforma;
    }

    public void setPropagandaforma(String propagandaforma) {
        this.propagandaforma = propagandaforma;
    }

    public List<Rendimento> getRendimentos() {
        return rendimentos;
    }
//    public List<Rendimento> getRendimentosGroupByData(){
//        int ano = 0;
//        int mes = 0;
//        List<Rendimento> lista;
//        for(Rendimento r: rendimentos){
//         r.   
//        }
//    }

    public void setRendimentos(List<Rendimento> rendimentos) {
        this.rendimentos = rendimentos;
    }

    public List<Servicos> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servicos> servicos) {
        this.servicos = servicos;
    }

    public int getTempoatividade() {
        return tempoatividade;
    }

    public void setTempoatividade(int tempoatividade) {
        this.tempoatividade = tempoatividade;
    }
    
    
    
    
    
    
    
    
}
