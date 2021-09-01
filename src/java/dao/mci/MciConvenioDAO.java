package dao.mci;

import dao.GenericDAO;
import entity.mci.MciConvenio;
import java.util.List;

public class MciConvenioDAO extends GenericDAO {

    public MciConvenioDAO() {
    }

    public long save(MciConvenio mciConvenio) {
        saveOrUpdatePojo(mciConvenio);
        return mciConvenio.getId();
    }

    public void update(MciConvenio mciConvenio) {
        saveOrUpdatePojo(mciConvenio);
    }

    public MciConvenio getPojo(int mciConvenioId) {
        MciConvenio MciConvenio = getPojo(MciConvenio.class, mciConvenioId);
        return MciConvenio;
    }

    public List<MciConvenio> getList() {
        return getPureList(MciConvenio.class, "from MciConvenio mciConvenio");
    }
    public List<MciConvenio> getListStatus() {
        return getPureList(MciConvenio.class, "from MciConvenio a where a.status = true ");
    }
//    public MciConvenioDAO(Session ses) {
//        super(ses);
//    }
//    public void removeMciConvenio(MciConvenio mciConvenio) {
//        removePojo(mciConvenio);
//    }
}
