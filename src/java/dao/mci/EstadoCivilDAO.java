package dao.mci;

import dao.GenericDAO;
import entity.mci.EstadoCivil;
import java.util.List;

public class EstadoCivilDAO extends GenericDAO {

    public long saveEstadoCivil(EstadoCivil estadocivil) {
        saveOrUpdatePojo(estadocivil);
        return estadocivil.getId();
    }

    public void updateEstadoCivil(EstadoCivil estadocivil) {
        saveOrUpdatePojo(estadocivil);
    }

    public EstadoCivil getEstadoCivil(int estadocivilId) {
        EstadoCivil EstadoCivil = getPojo(EstadoCivil.class, estadocivilId);
        return EstadoCivil;
    }

    public List<EstadoCivil> getEstadosCivis() {
        return getPureList(EstadoCivil.class, "from EstadoCivil estadocivil");
    }
//    public void removeEstadoCivil(EstadoCivil estadocivil) {
//        removePojo(estadocivil);
//    }
}
