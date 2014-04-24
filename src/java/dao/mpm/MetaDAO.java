package dao.mpm;

import dao.GenericDAO;
import entity.mpm.Meta;
import java.util.List;

public class MetaDAO extends GenericDAO {

    public long saveMeta(Meta meta) {
        saveOrUpdatePojo(meta);
        return meta.getId();
    }

    public void updateMeta(Meta meta) {
        saveOrUpdatePojo(meta);
    }

    public Meta getMeta(int metaId) {
        Meta Meta = getPojo(Meta.class, metaId);
        return Meta;
    }

    public List<Meta> getMetas() {
        return getPureList(Meta.class, "from Meta meta");
    }
//    public void removeMeta(Meta meta) {
//        removePojo(meta);
//    }
}
