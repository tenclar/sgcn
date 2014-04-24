package dao.mpm;

import dao.GenericDAO;
import entity.mpm.Projeto;
import java.util.List;

public class ProjetoDAO extends GenericDAO {

    public long saveProjeto(Projeto projeto) {
        saveOrUpdatePojo(projeto);
        return projeto.getId();
    }

    public void updateProjeto(Projeto projeto) {
        saveOrUpdatePojo(projeto);
    }

    public Projeto getProjeto(int projetoId) {
        Projeto Projeto = getPojo(Projeto.class, projetoId);
        return Projeto;
    }

    public List<Projeto> getProjetos() {
        return getPureList(Projeto.class, "from Projeto projeto");
    }
//    public void removeProjeto(Projeto projeto) {
//        removePojo(projeto);
//    }
}
