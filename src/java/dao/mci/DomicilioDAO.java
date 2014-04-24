package dao.mci;

import dao.GenericDAO;
import entity.mci.Domicilio;
import java.util.List;

public class DomicilioDAO extends GenericDAO {

    public Integer saveDomicilio(Domicilio domicilio) {
        saveOrUpdatePojo(domicilio);
        return domicilio.getId();
    }

    public void updateDomicilio(Domicilio domicilio) {
        saveOrUpdatePojo(domicilio);
    }

    public Domicilio getDomicilio(int domicilioId) {
        Domicilio domicilio = getPojo(Domicilio.class, domicilioId);
        return domicilio;
    }

    public List<Domicilio> getDomicilios() {
        return getPureList(Domicilio.class, "from Domicilio domicilio");
    }
//    public void removeDomicilio(Domicilio domicilio) {
//        removePojo(domicilio);
//    }
}
