package dao.mac;

import dao.GenericDAO;
import entity.mac.Ministerio;
import java.util.List;

public class MinisterioDAO extends GenericDAO {

    public MinisterioDAO() {
    }

    public long saveMinisterio(Ministerio ministerio) {
        saveOrUpdatePojo(ministerio);
        return ministerio.getId();
    }

    public void updateMinisterio(Ministerio ministerio) {
        saveOrUpdatePojo(ministerio);
    }

    public Ministerio getMinisterio(int ministerioId) {
        Ministerio Ministerio = getPojo(Ministerio.class, ministerioId);
        return Ministerio;
    }

    public List<Ministerio> getMinisterios() {
        return getPureList(Ministerio.class, "from Ministerio ministerio");
    }

}
