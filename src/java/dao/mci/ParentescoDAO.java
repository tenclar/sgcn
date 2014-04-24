package dao.mci;

import dao.GenericDAO;
import entity.mci.Parentesco;
import java.util.List;

public class ParentescoDAO extends GenericDAO {

    public ParentescoDAO() {
    }

    public long saveParentesco(Parentesco parentesco) {
        saveOrUpdatePojo(parentesco);
        return parentesco.getId();
    }

    public void updateParentesco(Parentesco parentesco) {
        saveOrUpdatePojo(parentesco);
    }

    public Parentesco getParentesco(int parentescoId) {
        Parentesco Parentesco = getPojo(Parentesco.class, parentescoId);
        return Parentesco;
    }

    public List<Parentesco> getParentescos() {
        return getPureList(Parentesco.class, "from Parentesco p order by p.nome asc");
    }
//    public ParentescoDAO(Session ses) {
//        super(ses);
//    }
//    public void removeParentesco(Parentesco parentesco) {
//        removePojo(parentesco);
//    }
}
