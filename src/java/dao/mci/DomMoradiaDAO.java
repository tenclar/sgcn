package dao.mci;

import dao.GenericDAO;
import entity.mci.DomMoradia;
import java.util.List;

public class DomMoradiaDAO extends GenericDAO {

    public long saveMoradia(DomMoradia dommoradia) {
        saveOrUpdatePojo(dommoradia);
        return dommoradia.getId();
    }

    public void updateMoradia(DomMoradia dommoradia) {
        saveOrUpdatePojo(dommoradia);
    }

    public DomMoradia getMoradia(int dommoradiaId) {
        DomMoradia Moradia = getPojo(DomMoradia.class, dommoradiaId);
        return Moradia;
    }

    public List<DomMoradia> getMoradias() {
        return getPureList(DomMoradia.class, "from DomMoradia dommoradia");
    }
//    public void removeMoradia(DomMoradia dommoradia) {;
//        removePojo(dommoradia);
//    };
}
