/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mci;

import entity.Telefone;
import entity.Cidade;
import entity.Endereco;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCid;
import entity.mci.enumerator.EnumTipoPessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author tecnologia01
 */
@Entity
@Table(name = "cidadao")
public class Cidadao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id    
    @GeneratedValue
    private Integer id;
    private String cpf;
    private String rg;
    private String expedidor;
    @Temporal(TemporalType.DATE)
    private Date datacreated;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataupdate;
    private String nome;
    private String apelido;
    private String sexo;
    private int anodemanda;
    @Temporal(TemporalType.DATE)
    private Date datanasc;
    private String numero;
    private String complemento;
    private String tempomorada; 
    private String curso;
    private String cursosecr;
    private String beneficioSocial;
    private String beneficiohabitacional;
    private String atividade;
    private String equipamento;
    private String equipsecr;
    private String trabLocal;
    private String trabFuncao;
    @Column(nullable=true)
    private double trabRem;
    @Column(nullable=true)
    private long nis;
    @Column(nullable=true)
    private int ano;
    private String instituicao;
    
    @Enumerated(EnumType.STRING)
    private EnumTipoPessoa tipopessoa;
    @Enumerated(EnumType.STRING)
    private EnumStatusBeneficio benstatus;
    @Enumerated(EnumType.STRING)
    private EnumStatusCid statuscid;
    @Column(name="resumo", columnDefinition="TEXT")
    private String resumo;
     @ManyToOne
    private Cidadao representante;
    
    @ManyToOne
    private Publico publico;
    @ManyToOne
    private EstadoCivil estadocivil;
    @ManyToOne
    private Cidade naturalidade;
    @ManyToOne
    private Escolaridade escolaridade;
    @ManyToOne
    private NecEspecial necespecial;
    @ManyToOne
    private Endereco endereco;
    @ManyToOne
    private RamoEmpreendimento ramoempreendimento;
    
    @OneToMany
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Telefone> telefones = new ArrayList<Telefone>();
    
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CidAssociados> associados = new ArrayList<CidAssociados>();
    
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Domicilio> histdomicilio = new ArrayList<Domicilio>();
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = CursosSecretaria.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CursosSecretaria> cursosSecretarias = new ArrayList<CursosSecretaria>();
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = EquipamentosProprios.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<EquipamentosProprios> equipamentosproprios = new ArrayList<EquipamentosProprios>(0);
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = EquipamentosSecretaria.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<EquipamentosSecretaria> equipamentossecretarias = new ArrayList<EquipamentosSecretaria>(0);
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = Cursos.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Cursos> cursos = new ArrayList<Cursos>(0);
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = CidBenSociais.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CidBenSociais> cidbBenSociais = new ArrayList<CidBenSociais>();
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = CidBenHabits.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CidBenHabits> cidBenHabits = new ArrayList<CidBenHabits>();
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = CidAtividades.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CidAtividades> cidAtividades = new ArrayList<CidAtividades>();
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = CidDespesas.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CidDespesas> despesas = new ArrayList<CidDespesas>();
    @OneToMany
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Dependente> dependentes = new ArrayList<Dependente>();
    
    @OneToMany(mappedBy = "cidadao", fetch = FetchType.LAZY, targetEntity = CidBensDuraveis.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CidBensDuraveis> cidbensduraveis = new ArrayList<CidBensDuraveis>();

    public Cidadao() {

        //datacreated = new Date();
        curso = "false";
        cursosecr = "false";
        beneficioSocial = "false";
        beneficiohabitacional = "false";
        atividade = "false";
        equipamento = "false";
        equipsecr = "false";
        //benstatus = EnumStatusBeneficio.CADASTRO;
        //statuscid = EnumStatusCid.INDIVIDUAL;
       

    }

    public int getAnodemanda() {
        return anodemanda;
    }

    public void setAnodemanda(int anodemanda) {
        this.anodemanda = anodemanda;
    }

    
    public Cidadao getRepresentante() {
        return representante;
    }

    public void setRepresentante(Cidadao representante) {
        this.representante = representante;
    }
    
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    
    public long getNis() {
        return nis;
    }

    public void setNis(long nis) {
        this.nis = nis;
    }
    

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    
    public EnumStatusCid getStatuscid() {
        return statuscid;
    }

    public void setStatuscid(EnumStatusCid statuscid) {
        this.statuscid = statuscid;
    }

    public String getCursosecr() {
        return cursosecr;
    }

    public void setCursosecr(String cursosecr) {
        this.cursosecr = cursosecr;
    }

        public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDatacreated() {
        return datacreated;
    }

    public void setDatacreated(Date datacreated) {
        this.datacreated = datacreated;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public Date getDataupdate() {
        return dataupdate;
    }

    public void setDataupdate(Date dataupdate) {
        this.dataupdate = dataupdate;
    }

    public Publico getPublico() {
        return publico;
    }

    public void setPublico(Publico publico) {
        this.publico = publico;
    }
    
    public List<Cursos> getCursos() {
        return cursos;
    }

    public void setCursos(List<Cursos> cursos) {
        this.cursos = cursos;
    }

    public List<CidBenSociais> getCidbBenSociais() {
        return cidbBenSociais;
    }

    public void setCidbBenSociais(List<CidBenSociais> cidbBenSociais) {
        this.cidbBenSociais = cidbBenSociais;
    }

    public List<CidBenHabits> getCidBenHabits() {
        return cidBenHabits;
    }

    public List<CidAtividades> getCidAtividades() {
        return cidAtividades;
    }

    public void setCidAtividades(List<CidAtividades> cidAtividades) {
        this.cidAtividades = cidAtividades;
    }

    public void setCidBenHabits(List<CidBenHabits> cidBenHabits) {
        this.cidBenHabits = cidBenHabits;
    }

    public List<CidDespesas> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<CidDespesas> despesas) {
        this.despesas = despesas;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public String getTrabLocal() {
        return trabLocal;
    }

    public void setTrabLocal(String localTrabalho) {
        this.trabLocal = localTrabalho;
    }

    public String getTrabFuncao() {
        return trabFuncao;
    }

    public void setTrabFuncao(String trabFuncao) {
        this.trabFuncao = trabFuncao;
    }

    public double getTrabRem() {
        return trabRem;
    }

    public void setTrabRem(double trabRem) {
        this.trabRem = trabRem;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public EstadoCivil getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(EstadoCivil estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Cidade getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(Cidade naturalidade) {
        this.naturalidade = naturalidade;
    }

    public List<Domicilio> getHistdomicilio() {
        return histdomicilio;
    }

    public void setHistdomililio(List<Domicilio> histdomicilio) {
        this.histdomicilio = histdomicilio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

   

    public String getExpedidor() {
        return expedidor;
    }

    public void setExpedidor(String expedidor) {
        this.expedidor = expedidor;
    }

    public RamoEmpreendimento getRamoempreendimento() {
        return ramoempreendimento;
    }

    public void setRamoempreendimento(RamoEmpreendimento ramoempreendimento) {
        this.ramoempreendimento = ramoempreendimento;
    }

    public int getIdade() {
        int idade = 0;
        Calendar cData = Calendar.getInstance();
        Calendar cHoje = Calendar.getInstance();
        if (this.getDatanasc() != null) {
            cData.set(Calendar.YEAR, cHoje.get(Calendar.YEAR));
            cData.setTime(this.getDatanasc());
            idade = cData.after(cHoje) ? -1 : 0;
            idade += cHoje.get(Calendar.YEAR) - cData.get(Calendar.YEAR);
        }

        return idade;
    }

    public NecEspecial getNecespecial() {
        return necespecial;
    }

    public void setNecespecial(NecEspecial necespecial) {
        this.necespecial = necespecial;
    }

    

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getBeneficiohabitacional() {
        return beneficiohabitacional;
    }

    public void setBeneficiohabitacional(String beneficiohabitacional) {
        this.beneficiohabitacional = beneficiohabitacional;
    }

    public String getBeneficioSocial() {
        return beneficioSocial;
    }

    public void setBeneficioSocial(String beneficiosocial) {
        this.beneficioSocial = beneficiosocial;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getEquipsecr() {
        return equipsecr;
    }

    public void setEquipsecr(String equipsecr) {
        this.equipsecr = equipsecr;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTempomorada() {
        return tempomorada;
    }

    public void setTempomorada(String tempomorada) {
        this.tempomorada = tempomorada;
    }

    public EnumStatusBeneficio getBenstatus() {
        return benstatus;
    }

    public void setBenstatus(EnumStatusBeneficio benstatus) {
        this.benstatus = benstatus;
    }

    public List<EquipamentosProprios> getEquipamentosproprios() {
        return equipamentosproprios;
    }

    public void setEquipamentosproprios(List<EquipamentosProprios> equipamentosproprios) {
        this.equipamentosproprios = equipamentosproprios;
    }

    public List<EquipamentosSecretaria> getEquipamentossecretarias() {
        return equipamentossecretarias;
    }

    public void setEquipamentossecretarias(List<EquipamentosSecretaria> equipamentossecretarias) {
        this.equipamentossecretarias = equipamentossecretarias;
    }

    public List<CursosSecretaria> getCursosSecretarias() {
        return cursosSecretarias;
    }

    public void setCursosSecretarias(List<CursosSecretaria> cursosSecretarias) {
        this.cursosSecretarias = cursosSecretarias;
    }

    public List<CidBensDuraveis> getCidbensduraveis() {
        return cidbensduraveis;
    }

    public void setCidbensduraveis(List<CidBensDuraveis> cidbensduraveis) {
        this.cidbensduraveis = cidbensduraveis;
    }

    public List<CidAssociados> getAssociados() {
        return associados;
    }

    public void setAssociados(List<CidAssociados> associados) {
        this.associados = associados;
    }

    public EnumTipoPessoa getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(EnumTipoPessoa tipopessoa) {
        this.tipopessoa = tipopessoa;
    }



    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

   
   
    
    

    public double getAtividadeTotal() {
        // DecimalFormat df = new DecimalFormat("R$  ###,##0.00");        
        Double valor = 0.0;
        for (CidAtividades c : getCidAtividades()) {
            valor = valor + c.getRenda();
        }


        //return  df.format(valor);
        return valor;
    }

    public double getDespesasTotal() {
        //DecimalFormat df = new DecimalFormat("R$  ###,##0.00"); 
        Double valor = 0.0;
        for (CidDespesas c : getDespesas()) {
            valor = valor + c.getValor();
        }
        return valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cidadao other = (Cidadao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
