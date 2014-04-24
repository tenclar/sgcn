/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDAO;
import entity.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import util.FacesUtils;

/**
 *
 * @author NOTE-04
 */
@ManagedBean
@ViewScoped
public class UsuarioLoginBean implements Serializable{
 private Usuario usuario = null;
    private Usuario usersec = null;
    private FacesUtils facesutis = new FacesUtils();
    /**
     * Creates a new instance of UsuarioLoginBean
     */
    
   public UsuarioLoginBean() {

        usersec = new Usuario();
        SecurityContext scontext = SecurityContextHolder.getContext();
        if (scontext instanceof SecurityContext) {
            Authentication authentication = scontext.getAuthentication();
            if (authentication instanceof Authentication) {
                // usersec.setLogin(((User)authentication.getPrincipal()).getUsername());
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usersec = usuarioDAO.getUsuario(((User) authentication.getPrincipal()).getUsername());
                
            }
        }
    }
    public Usuario getUsersec() {
        return usersec;
    }

    public void setUsersec(Usuario usersec) {
        this.usersec = usersec;
    }
    
     public void alterarSenha() {
        this.usuario =  this.usersec;
    }

       public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void updateUsuario() {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            
            usuarioDAO.saveUsuario(this.usuario);
           
           facesutis.info("Cadastro Efetuado!");
        } catch (Exception e) {
            facesutis.erro("Cadastro Não Efetuado! " + e.getMessage());
        }

    }
    public void cancelUsuario(){
        this.usuario = new Usuario();
    }
}
