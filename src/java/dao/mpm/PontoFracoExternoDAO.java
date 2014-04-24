package dao.mpm;

import dao.GenericDAO;
import entity.mpm.PontoFracoExterno;
import java.util.List;

public class PontoFracoExternoDAO extends GenericDAO {

    public long savePontoFracoExterno(PontoFracoExterno pontoFracoExterno) {
        saveOrUpdatePojo(pontoFracoExterno);
        return pontoFracoExterno.getId();
    }

    public void updatePontoFracoExterno(PontoFracoExterno pontoFracoExterno) {
        saveOrUpdatePojo(pontoFracoExterno);
    }

    public PontoFracoExterno getPontoFracoExterno(int pontoFracoExternoId) {
        PontoFracoExterno PontoFracoExterno = getPojo(PontoFracoExterno.class, pontoFracoExternoId);
        return PontoFracoExterno;
    }

    public List<PontoFracoExterno> getPontoFracoExternos() {
        return getPureList(PontoFracoExterno.class, "from PontoFracoExterno pontoFracoExterno");
    }
//    public void removePontoFracoExterno(PontoFracoExterno pontoFracoExterno) {
//        removePojo(pontoFracoExterno);
//    }
}
