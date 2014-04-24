package dao.mci;

import dao.GenericDAO;
import entity.mci.DomIluminacao;
import java.util.List;

public class DomIluminacaoDAO extends GenericDAO {

    public Integer saveIluminacao(DomIluminacao iluminacao) {
        saveOrUpdatePojo(iluminacao);
        return iluminacao.getId();
    }

    public void updateIluminacao(DomIluminacao iluminacao) {
        saveOrUpdatePojo(iluminacao);
    }

    public DomIluminacao getIluminacao(int iluminacaoId) {
        DomIluminacao iluminacao = getPojo(DomIluminacao.class, iluminacaoId);
        return iluminacao;
    }

    public List<DomIluminacao> getIluminacaos() {
        return getPureList(DomIluminacao.class, "from DomIluminacao domIluminacao");
    }
//    public void removeIluminacao(DomIluminacao iluminacao) {
//        removePojo(iluminacao);
//    }
}
