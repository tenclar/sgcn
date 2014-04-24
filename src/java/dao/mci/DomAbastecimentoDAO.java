package dao.mci;

import dao.GenericDAO;
import entity.mci.DomAbastecimento;
import java.util.List;

public class DomAbastecimentoDAO extends GenericDAO {

    public long save(DomAbastecimento domabastecimento) {
        saveOrUpdatePojo(domabastecimento);
        return domabastecimento.getId();
    }

    public void update(DomAbastecimento domabastecimento) {
        saveOrUpdatePojo(domabastecimento);
    }

    public DomAbastecimento getPojo(int domabastecimentoId) {
        DomAbastecimento DomAbastecimento = getPojo(DomAbastecimento.class, domabastecimentoId);
        return DomAbastecimento;
    }

    public List<DomAbastecimento> getListas() {
        return getPureList(DomAbastecimento.class, "from DomAbastecimento domabastecimento");
    }
//    public void remove(DomAbastecimento domabastecimento) {
//        removePojo(domabastecimento);
//    }
}
