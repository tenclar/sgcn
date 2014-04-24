package dao.mci;

import dao.GenericDAO;
import entity.mci.Despesa;
import java.util.List;

public class DespesaDAO extends GenericDAO {

    public long saveDespesa(Despesa despesa) {
        saveOrUpdatePojo(despesa);
        return despesa.getId();
    }

    public void updateDespesa(Despesa despesa) {
        saveOrUpdatePojo(despesa);
    }

    public Despesa getDespesa(int despesaId) {
        Despesa Despesa = getPojo(Despesa.class, despesaId);
        return Despesa;
    }

    public List<Despesa> getDespesas() {
        return getPureList(Despesa.class, "from Despesa despesa");
    }
//    public void removeDespesa(Despesa despesa) {;;
//        removePojo(despesa);
//    }
}
