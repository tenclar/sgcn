package entity.mgc;

import entity.mci.Cidadao;
import entity.mci.enumerator.EnumStatusCurso;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "mgc_turcidadaos")
public class TurCidadaos implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String status;
    @ManyToOne
    @JoinColumn(name = "cidadao_id")
    private Cidadao cidadao;
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cidadao getCidadao() {
        return cidadao;
    }

    public void setCidadao(Cidadao cidadao) {
        this.cidadao = cidadao;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public void setStatus(Class<EnumStatusCurso> aClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
