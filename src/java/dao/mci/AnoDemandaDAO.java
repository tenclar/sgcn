package dao.mci;

import dao.GenericDAO;
import entity.mci.AnoDemanda;
import entity.mci.AnoDemanda;
import java.util.List;

public class AnoDemandaDAO extends GenericDAO {

    public AnoDemandaDAO() {
    }

    public long save(AnoDemanda anodemanda) {
        saveOrUpdatePojo(anodemanda);
        return anodemanda.getId();
    }

    public void update(AnoDemanda anodemanda) {
        saveOrUpdatePojo(anodemanda);
    }

    public AnoDemanda getPojo(int anodemandaId) {
        AnoDemanda AnoDemanda = getPojo(AnoDemanda.class, anodemandaId);
        return AnoDemanda;
    }

    public List<AnoDemanda> getList() {
        return getPureList(AnoDemanda.class, "from AnoDemanda anodemanda");
    }
    public List<AnoDemanda> getListStatus() {
        return getPureList(AnoDemanda.class, "from AnoDemanda a where a.status = true order by a.ano desc ");
    }
//    public AnoDemandaDAO(Session ses) {
//        super(ses);
//    }
//    public void removeAnoDemanda(AnoDemanda anodemanda) {
//        removePojo(anodemanda);
//    }
}
