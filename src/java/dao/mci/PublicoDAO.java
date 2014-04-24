package dao.mci;

import dao.GenericDAO;
import entity.mci.Publico;
import java.util.List;

public class PublicoDAO extends GenericDAO {

    public PublicoDAO() {
    }

    public long savePublico(Publico publico) {
        saveOrUpdatePojo(publico);
        return publico.getId();
    }

    public void updatePublico(Publico publico) {
        saveOrUpdatePojo(publico);
    }

    public Publico getPublico(int publicoId) {
        Publico Publico = getPojo(Publico.class, publicoId);
        return Publico;
    }

    public List<Publico> getPublicos() {
        return getPureList(Publico.class, "from Publico publico");
    }
//    public PublicoDAO(Session ses) {
//        super(ses);
//    }
//    public void removePublico(Publico publico) {
//        removePojo(publico);
//    }
}
