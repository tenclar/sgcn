package dao.mci;

import dao.GenericDAO;
import entity.mci.DomSituacao;
import java.util.List;

public class DomSituacaoDAO extends GenericDAO {

    public long save(DomSituacao domsituacao) {
        saveOrUpdatePojo(domsituacao);
        return domsituacao.getId();
    }

    public void update(DomSituacao domsituacao) {
        saveOrUpdatePojo(domsituacao);
    }

    public DomSituacao getPojo(int domsituacaoId) {
        DomSituacao DomSituacao = getPojo(DomSituacao.class, domsituacaoId);
        return DomSituacao;
    }

    public List<DomSituacao> getListas() {
        return getPureList(DomSituacao.class, "from DomSituacao domsituacao");
    }
//    public void remove(DomSituacao domsituacao) {
//        removePojo(domsituacao);
//    }  
}
