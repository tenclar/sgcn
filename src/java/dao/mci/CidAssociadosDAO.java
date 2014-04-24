package dao.mci;

import dao.GenericDAO;
import entity.mci.CidAssociados;
import entity.mci.Cidadao;
import java.util.List;

public class CidAssociadosDAO extends GenericDAO {

    public CidAssociadosDAO() {
    }

    public long saveCidAssociados(CidAssociados cidAssociados) {
        saveOrUpdatePojo(cidAssociados);
        return cidAssociados.getId();
    }

    public void updateCidAssociados(CidAssociados cidAssociados) {
        saveOrUpdatePojo(cidAssociados);
    }

    public CidAssociados getCidAssociados(int cidAssociadosId) {
        CidAssociados cidAssociados = getPojo(CidAssociados.class, cidAssociadosId);
        return cidAssociados;
    }

    public List<CidAssociados> getCidAssociadoss() {
        return getPureList(CidAssociados.class, "from CidAssociados cidAssociados");
    }
    public CidAssociados getCidAssociadosbyId(int assid) {
        String query = "from CidAssociados c where c.associado.id = ?";
         CidAssociados cidAssociados = getPurePojo(CidAssociados.class, query, assid);
        return cidAssociados;
    }
//    public CidAssociadosDAO(Session ses) {
//        super(ses);
//    }
//    public void removeCidAssociados(CidAssociados cidAssociados) {
//        removePojo(cidAssociados);
//    }
}
