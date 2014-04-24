package dao.mgc;

import dao.GenericDAO;
import entity.mgc.TurmaLocal;
import java.util.List;

public class TurmaLocalDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public TurmaLocalDAO() {
    }

    public long saveTurmaLocal(TurmaLocal turmaLocal) {
        saveOrUpdatePojo(turmaLocal);
        return turmaLocal.getId();
    }

    public void updateTurmaLocal(TurmaLocal turmaLocal) {
        saveOrUpdatePojo(turmaLocal);
    }

    public TurmaLocal getTurmaLocal(int turmaLocalId) {
        TurmaLocal TurmaLocal = getPojo(TurmaLocal.class, turmaLocalId);
        return TurmaLocal;
    }

    public List<TurmaLocal> getTurmaLocals() {
        return getPureList(TurmaLocal.class, "from TurmaLocal turmaLocal Order by turmaLocal.nome");
    }
//    public TurmaLocalDAO(Session ses) {
//        super(ses);
//    }
//    public void removeTurmaLocal(TurmaLocal turmaLocal) {
//        removePojo(turmaLocal);
//    }
}
