package dao.mpm;

import dao.GenericDAO;
import entity.mpm.PontoFracoInterno;
import java.util.List;

public class PontoFracoInternoDAO extends GenericDAO {

    public long savePontoFracoInterno(PontoFracoInterno pontoFracoInterno) {
        saveOrUpdatePojo(pontoFracoInterno);
        return pontoFracoInterno.getId();
    }

    public void updatePontoFracoInterno(PontoFracoInterno pontoFracoInterno) {
        saveOrUpdatePojo(pontoFracoInterno);
    }

    public PontoFracoInterno getPontoFracoInterno(int pontoFracoInternoId) {
        PontoFracoInterno PontoFracoInterno = getPojo(PontoFracoInterno.class, pontoFracoInternoId);
        return PontoFracoInterno;
    }

    public List<PontoFracoInterno> getPontoFracoInternos() {
        return getPureList(PontoFracoInterno.class, "from PontoFracoInterno pontoFracoInterno");
    }
//    public void removePontoFracoInterno(PontoFracoInterno pontoFracoInterno) {
//        removePojo(pontoFracoInterno);
//    }
}
