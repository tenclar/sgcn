package dao;

import entity.Usuario;
import java.util.List;

public class UsuarioDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;


    public Integer saveUsuario(Usuario usuario) {
        saveOrUpdatePojo(usuario);
        return usuario.getId();
    }

    public void updateUsuario(Usuario usuario) {
        saveOrUpdatePojo(usuario);
    }

    public Usuario getUsuario(String usuarioLogin) {
        String query = "from Usuario u  where u.login = ?";
        Usuario Usuario = getPurePojo(Usuario.class,query, usuarioLogin);
        return Usuario;
    }    
   
     public Usuario getEntity(int id) {
        Usuario usuario = getPojo(Usuario.class, id);
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return getPureList(Usuario.class, "from Usuario usuario");
    }
    public List<Usuario> getUsuarios(String nome) {
        String params = "%" + nome + "%";
        String query = " from Usuario u  where  u.nome like ?";
        return getPureList(Usuario.class, query, params);
        
    }
//    public UsuarioDAO(Session ses) {
//        super(ses);
//    }
//    public void removeUsuario(Usuario usuario) {
//        removePojo(usuario);
//    }
}
