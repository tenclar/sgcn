package dao.mpm;

import dao.GenericDAO;
import entity.mpm.Responsavel;
import java.util.List;

public class ResponsavelDAO extends GenericDAO {

    public long saveResponsavel(Responsavel responsavel) {
        saveOrUpdatePojo(responsavel);
        return responsavel.getId();
    }

    public void updateResponsavel(Responsavel responsavel) {
        saveOrUpdatePojo(responsavel);
    }

    public Responsavel getResponsavel(int responsavelId) {
        Responsavel Responsavel = getPojo(Responsavel.class, responsavelId);
        return Responsavel;
    }

    public List<Responsavel> getResponsavels() {
        return getPureList(Responsavel.class, "from Responsavel r");
    }

    public List<Responsavel> getList(String log) {
        String params = "%" + log + "%";

        String query = "from Responsavel c  where c.nome like ?";
        return getPureList(Responsavel.class, query, params);
    }
//    public void removeResponsavel(Responsavel responsavel) {
//        removePojo(responsavel);
//    }
}
