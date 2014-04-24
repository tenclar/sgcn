package dao.mgc;

import dao.GenericDAO;
import entity.mgc.TurCidadaos;
import java.util.List;

public class TurCidadaosDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public TurCidadaosDAO() {
    }
    
    public long saveTurCidadaos(TurCidadaos turCidadaos) {
        saveOrUpdatePojo(turCidadaos);
        return turCidadaos.getId();
    }

    public void updateTurCidadaos(TurCidadaos turCidadaos) {
        saveOrUpdatePojo(turCidadaos);
    }

    public TurCidadaos getTurCidadaos(int turCidadaosId) {
        TurCidadaos turCidadaos = getPojo(TurCidadaos.class, turCidadaosId);
        return turCidadaos;
    }

    public List<TurCidadaos> getTurCidadaos() {
        return getPureList(TurCidadaos.class, "from TurCidadaos turcidadaos");
    }
    
}
