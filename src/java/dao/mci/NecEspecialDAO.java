package dao.mci;

import dao.GenericDAO;
import entity.mci.NecEspecial;
import java.util.List;

public class NecEspecialDAO extends GenericDAO {

    public Integer saveNecEspecial(NecEspecial necespecial) {
        saveOrUpdatePojo(necespecial);
        return necespecial.getId();
    }

    public void updateNecEspecial(NecEspecial necespecial) {
        saveOrUpdatePojo(necespecial);
    }

    public NecEspecial getNecEspecial(int necespecialId) {
        NecEspecial necespecial = getPojo(NecEspecial.class, necespecialId);
        return necespecial;
    }

    public List<NecEspecial> getNecEspecials() {
        return getPureList(NecEspecial.class, "from NecEspecial necespecial");
    }
//    public void removeNecEspecial(NecEspecial necespecial) {
//        removePojo(necespecial);
//    }
}
