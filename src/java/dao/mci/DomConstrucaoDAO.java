package dao.mci;

import dao.GenericDAO;
import java.util.List;
import entity.mci.DomConstrucao;

public class DomConstrucaoDAO extends GenericDAO {

    public long save(DomConstrucao domcituacao) {
        saveOrUpdatePojo(domcituacao);
        return domcituacao.getId();
    }

    public void update(DomConstrucao domcituacao) {
        saveOrUpdatePojo(domcituacao);
    }

    public DomConstrucao getPojo(int domcituacaoId) {
        DomConstrucao DomConstrucao = getPojo(DomConstrucao.class, domcituacaoId);
        return DomConstrucao;
    }

    public List<DomConstrucao> getListas() {
        return getPureList(DomConstrucao.class, "from DomConstrucao domcituacao");
    }
//    public void remove(DomConstrucao domcituacao) {
//        removePojo(domcituacao);
//    }
}
