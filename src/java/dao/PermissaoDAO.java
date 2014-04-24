package dao;

import entity.Permissao;
import java.util.List;

public class PermissaoDAO extends GenericDAO {

    public PermissaoDAO() {
    }

    public long savePermissao(Permissao permissao) {
        saveOrUpdatePojo(permissao);
        return permissao.getId();
    }

    public void updatePermissao(Permissao permissao) {
        saveOrUpdatePojo(permissao);
    }

    public Permissao getPermissao(int permissaoId) {
        Permissao Permissao = getPojo(Permissao.class, permissaoId);
        return Permissao;
    }

    public List<Permissao> getPermissaos() {
        return getPureList(Permissao.class, "from Permissao permissao");
    }
//    public PermissaoDAO(Session ses) {
//        super(ses);
//    }
//    public void removePermissao(Permissao permissao) {
//        removePojo(permissao);
//    }
}
