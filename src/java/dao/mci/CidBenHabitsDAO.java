package dao.mci;

import dao.GenericDAO;
import entity.mci.CidBenHabits;
import java.util.List;

public class CidBenHabitsDAO extends GenericDAO {

    public Integer saveCidBenHabits(CidBenHabits cidbenhabits) {
        saveOrUpdatePojo(cidbenhabits);
        return cidbenhabits.getId();
    }

    public void updateCidBenHabits(CidBenHabits cidbenhabits) {
        saveOrUpdatePojo(cidbenhabits);
    }

    public CidBenHabits getCidBenHabits(int cidbenhabitsId) {
        CidBenHabits CidBenHabits = getPojo(CidBenHabits.class, cidbenhabitsId);
        return CidBenHabits;
    }

    public List<CidBenHabits> getListaCidBenHabits() {
        return getPureList(CidBenHabits.class, "from CidBenHabits cidbenhabits");
    }
//    public CidBenHabitsDAO(Session ses) {
//        super(ses);
//    }
//    public void removeCidBenHabits(CidBenHabits cidbenhabits) {
//        removePojo(cidbenhabits);
//    }
}
