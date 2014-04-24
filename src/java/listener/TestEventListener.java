package listener;


import dao.UsuarioDAO;
import entity.Usuario;
import java.util.Date;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;


 public class TestEventListener implements  ApplicationListener  { 
   
     @Override
  public void onApplicationEvent(ApplicationEvent event) {
         UsuarioDAO udao = new UsuarioDAO();
         Usuario user = new Usuario();
         Usuario usuario;         
         
    if (event instanceof AuthenticationSuccessEvent) {
        AuthenticationSuccessEvent au = (AuthenticationSuccessEvent) event;
        Authentication authentication = au.getAuthentication();
            if (authentication instanceof Authentication){
                user.setLogin(((User)authentication.getPrincipal()).getUsername());                
                usuario = udao.getUsuario(user.getLogin());
                usuario.setDataloginold(usuario.getDatalogin());                
                usuario.setDatalogin(new Date());
                udao.saveUsuario(usuario);
            }
      //System.out.println("Usuario autenticado com sucesso");
    }
    if (event instanceof AbstractAuthenticationFailureEvent) {
      //System.out.println("Usuario nao autenticado");
    }
    
  }
 }