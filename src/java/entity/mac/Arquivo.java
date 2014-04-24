/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mac;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import javax.persistence.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author tenclar
 */

@Entity
@Table(name="mac_arquivo")
public class Arquivo implements Serializable {
    
    @Id    
    @GeneratedValue
    private Integer id;
    @Column(name="descricao", columnDefinition="TEXT")
    private String descricao;
    private String arquivo;
    
    @Transient
    private StreamedContent file;
    
   
    @ManyToOne
    private HistoricoInterno historico;
    
        public StreamedContent getFile() {
        try {
            InputStream stream = new FileInputStream(this.getArquivo());
            file = new DefaultStreamedContent(stream, "application/pdf", this.getDescricao());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         
        return file;
    }

   
   
   
    
    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HistoricoInterno getHistorico() {
        return historico;
    }

    public void setHistorico(HistoricoInterno historico) {
        this.historico = historico;
    }

   

        public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Arquivo other = (Arquivo) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    

    
    
    
    
    
}
