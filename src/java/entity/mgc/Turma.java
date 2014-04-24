package entity.mgc;

import entity.Endereco;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "mgc_turma")
public class Turma implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String status;    
    private String horario;
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Temporal(TemporalType.DATE)
    private Date datafim;
    @Temporal(TemporalType.DATE)
    private Date datacreated;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataupdate;
     @ManyToOne
    @JoinColumn(name = "turmalocal_id")
     private TurmaLocal turmalocal;
    @ManyToOne
    @JoinColumn(name = "curso_id")    
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "endereco_id")    
    private Endereco endereco;
    
    @OneToMany(mappedBy = "turma", fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<TurCidadaos> turcidadaos = new ArrayList<TurCidadaos>();

    public Turma() {
        datacreated = new Date();
        endereco = new Endereco();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getDatacreated() {
        return datacreated;
    }

    public void setDatacreated(Date datacreated) {
        this.datacreated = datacreated;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

        

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDataupdate() {
        return dataupdate;
    }

    public void setDataupdate(Date dataupdate) {
        this.dataupdate = dataupdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TurCidadaos> getTurcidadaos() {
        return turcidadaos;
    }

    public void setTurcidadaos(List<TurCidadaos> turcidadaos) {
        this.turcidadaos = turcidadaos;
    }

    public TurmaLocal getTurmalocal() {
        return turmalocal;
    }

    public void setTurmalocal(TurmaLocal turmalocal) {
        this.turmalocal = turmalocal;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
    
}
