package dao.mci;

import dao.GenericDAO;
import entity.mci.CidBenSociais;
import java.util.List;

public class CidBenSociaisDAO extends GenericDAO {

    public Integer saveCidBenSociais(CidBenSociais cidbensociais) {
        saveOrUpdatePojo(cidbensociais);
        return cidbensociais.getId();
    }

    public void updateCidBenSociais(CidBenSociais cidbensociais) {
        saveOrUpdatePojo(cidbensociais);
    }

    public CidBenSociais getCidBenSociais(int cidbensociaisId) {
        CidBenSociais CidBenSociais = getPojo(CidBenSociais.class, cidbensociaisId);
        return CidBenSociais;
    }

    public List<CidBenSociais> getListaCidBenSociais() {
        return getPureList(CidBenSociais.class, "from CidBenSociais cidbensociais");
    }
//    public CidBenSociaisDAO(Session ses) {
//        super(ses);
//    }
//    public void removeCidBenSociais(CidBenSociais cidbensociais) {
//        removePojo(cidbensociais);
//    }
}
