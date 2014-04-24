/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author tenclar
 */
@Entity
@Table(name="adm_usuario")
public class Usuario implements Serializable {
     
    @Id
    @GeneratedValue
    private Integer id;
    private String login;
    private String nome;
    private String senha;
    @Size(min = 1, message = "Informe E-mail")
    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",   message = "Email formato inválido.")
    private String email;
    
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datalogin;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataloginold;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datacreate;
    
    private boolean status;    
    
    @OneToMany(mappedBy = "usuario",fetch= FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN, org.hibernate.annotations.CascadeType.SAVE_UPDATE})      
    private List<UsrPermissao> permissaos = new ArrayList<UsrPermissao>();

    public Usuario() {
        this.senha = "@c1506";
        this.datacreate = new Date();
        this.datalogin = new Date();
        this.dataloginold = new Date();
        this.status = true;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   
    public Date getDatacreate() {
        return datacreate;
    }

    public void setDatacreate(Date datacreate) {
        this.datacreate = datacreate;
    }

    public Date getDatalogin() {
        return datalogin;
    }

    public void setDatalogin(Date datalogin) {
        this.datalogin = datalogin;
    }

    public Date getDataloginold() {
        return dataloginold;
    }

    public void setDataloginold(Date dataloginold) {
        this.dataloginold = dataloginold;
    }

    public List<UsrPermissao> getPermissaos() {
        return permissaos;
    }

    public void setPermissaos(List<UsrPermissao> permissaos) {
        this.permissaos = permissaos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
   

    
    
    
    
}
