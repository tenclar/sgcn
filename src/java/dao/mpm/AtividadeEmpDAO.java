package dao.mpm;

import dao.GenericDAO;
import entity.mpm.AtividadeEmp;
import java.util.List;

public class AtividadeEmpDAO extends GenericDAO {

    public long saveAtividadeEmp(AtividadeEmp atividadeEmp) {
        saveOrUpdatePojo(atividadeEmp);
        return atividadeEmp.getId();
    }

    public void updateAtividadeEmp(AtividadeEmp atividadeEmp) {
        saveOrUpdatePojo(atividadeEmp);
    }

    public AtividadeEmp getAtividadeEmp(int atividadeEmpId) {
        AtividadeEmp AtividadeEmp = getPojo(AtividadeEmp.class, atividadeEmpId);
        return AtividadeEmp;
    }

    public List<AtividadeEmp> getAtividadeEmps() {
        return getPureList(AtividadeEmp.class, "from AtividadeEmp atividadeEmp");
    }
//    public void removeAtividadeEmp(AtividadeEmp atividadeEmp) {
//        removePojo(atividadeEmp);
//    }
}
