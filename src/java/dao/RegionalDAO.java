package dao;

import entity.Regional;
import java.util.List;

public class RegionalDAO extends GenericDAO {

    public RegionalDAO() {
    }

    public Integer saveRegional(Regional regional) {
        saveOrUpdatePojo(regional);
        return regional.getId();
    }

    public void updateRegional(Regional regional) {
        saveOrUpdatePojo(regional);
    }

    public Regional getRegional(int regionalId) {
        Regional regional = getPojo(Regional.class, regionalId);
        return regional;
    }
    
    public List<Regional> getRegionals() {
        return getPureListMaxResult(Regional.class, "from Regional regional order by regional.nome asc");
    }

    
//    public RegionalDAO(Session ses) {
//        super(ses);
//    }
//    public void removeRegional(Regional regional) {
//        removePojo(regional);
//    }
}
