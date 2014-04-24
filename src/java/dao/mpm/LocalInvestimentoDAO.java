package dao.mpm;

import dao.GenericDAO;
import entity.mpm.LocalInvestimento;
import java.util.List;

public class LocalInvestimentoDAO extends GenericDAO {

    public long saveLocalInvestimento(LocalInvestimento localInvestimento) {
        saveOrUpdatePojo(localInvestimento);
        return localInvestimento.getId();
    }

    public void updateLocalInvestimento(LocalInvestimento localInvestimento) {
        saveOrUpdatePojo(localInvestimento);
    }

    public LocalInvestimento getLocalInvestimento(int localInvestimentoId) {
        LocalInvestimento LocalInvestimento = getPojo(LocalInvestimento.class, localInvestimentoId);
        return LocalInvestimento;
    }

    public List<LocalInvestimento> getLocalInvestimentos() {
        return getPureList(LocalInvestimento.class, "from LocalInvestimento l");
    }
//    public void removeLocalInvestimento(LocalInvestimento localInvestimento) {;;
//        removePojo(localInvestimento);
//    }
}
