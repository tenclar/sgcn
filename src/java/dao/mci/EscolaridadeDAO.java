package dao.mci;

import dao.GenericDAO;
import entity.mci.Escolaridade;
import java.util.List;

public class EscolaridadeDAO extends GenericDAO {

    public Integer saveEscolaridade(Escolaridade escolaridade) {
        saveOrUpdatePojo(escolaridade);
        return escolaridade.getId();
    }

    public void updateEscolaridade(Escolaridade escolaridade) {
        saveOrUpdatePojo(escolaridade);
    }

    public Escolaridade getEscolaridade(int escolaridadeId) {
        Escolaridade escolaridade = getPojo(Escolaridade.class, escolaridadeId);
        return escolaridade;
    }

    public List<Escolaridade> getEscolaridades() {
        return getPureList(Escolaridade.class, "from Escolaridade e order by e.id");
    }
//    public void removeEscolaridade(Escolaridade escolaridade) {
//        removePojo(escolaridade);
//    }
}
