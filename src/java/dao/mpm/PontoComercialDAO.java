package dao.mpm;

import dao.GenericDAO;
import entity.mpm.PontoComercial;
import java.util.List;

public class PontoComercialDAO extends GenericDAO {

    public long savePontoComercial(PontoComercial pontoComercial) {
        saveOrUpdatePojo(pontoComercial);
        return pontoComercial.getId();
    }

    public void updatePontoComercial(PontoComercial pontoComercial) {
        saveOrUpdatePojo(pontoComercial);
    }

    public PontoComercial getPontoComercial(int pontoComercialId) {
        PontoComercial PontoComercial = getPojo(PontoComercial.class, pontoComercialId);
        return PontoComercial;
    }

    public List<PontoComercial> getPontoComercials() {
        return getPureList(PontoComercial.class, "from PontoComercial pontoComercial");
    }
//    public void removePontoComercial(PontoComercial pontoComercial) {;
//        removePojo(pontoComercial);
//    }
}
