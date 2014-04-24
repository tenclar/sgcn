package dao.mpm;

import dao.GenericDAO;
import entity.mpm.PontoForteExterno;
import java.util.List;

public class PontoForteExternoDAO extends GenericDAO {

    public long savePontoForteExterno(PontoForteExterno pontoforteExterno) {
        saveOrUpdatePojo(pontoforteExterno);
        return pontoforteExterno.getId();
    }

    public void updatePontoForteExterno(PontoForteExterno pontoforteExterno) {
        saveOrUpdatePojo(pontoforteExterno);
    }

    public PontoForteExterno getPontoForteExterno(int pontoforteExternoId) {
        PontoForteExterno PontoForteExterno = getPojo(PontoForteExterno.class, pontoforteExternoId);
        return PontoForteExterno;
    }

    public List<PontoForteExterno> getPontoForteExternos() {
        return getPureList(PontoForteExterno.class, "from PontoForteExterno pontoforteExterno");
    }
//    public void removePontoForteExterno(PontoForteExterno pontoforteExterno) {
//        removePojo(pontoforteExterno);
//    }
}
