package dao.mci;

import dao.GenericDAO;
import entity.mci.Atividade;
import java.util.List;

public class AtividadeDAO extends GenericDAO {

    public AtividadeDAO() {
    }

    public long saveAtividade(Atividade atividade) {
        saveOrUpdatePojo(atividade);
        return atividade.getId();
    }

    public void updateAtividade(Atividade atividade) {
        saveOrUpdatePojo(atividade);
    }

    public Atividade getAtividade(int atividadeId) {
        Atividade Atividade = getPojo(Atividade.class, atividadeId);
        return Atividade;
    }

    public List<Atividade> getAtividades() {
        return getPureList(Atividade.class, "from Atividade atividade");
    }
//    public AtividadeDAO(Session ses) {
//        super(ses);
//    }
//    public void removeAtividade(Atividade atividade) {
//        removePojo(atividade);
//    }
}
