package dao.mpm;

import dao.GenericDAO;
import entity.mpm.PontoForteInterno;
import java.util.List;

public class PontoForteInternoDAO extends GenericDAO {

    public long savePontoForteInterno(PontoForteInterno pontoforteExterno) {
        saveOrUpdatePojo(pontoforteExterno);
        return pontoforteExterno.getId();
    }

    public void updatePontoForteInterno(PontoForteInterno pontoforteExterno) {
        saveOrUpdatePojo(pontoforteExterno);
    }

    public PontoForteInterno getPontoForteInterno(int pontoforteExternoId) {
        PontoForteInterno PontoForteInterno = getPojo(PontoForteInterno.class, pontoforteExternoId);
        return PontoForteInterno;
    }

    public List<PontoForteInterno> getPontoForteInternos() {
        return getPureList(PontoForteInterno.class, "from PontoForteInterno pontoforteExterno");
    }
//    public void removePontoForteInterno(PontoForteInterno pontoforteExterno) {
//        removePojo(pontoforteExterno);
//    }
}
