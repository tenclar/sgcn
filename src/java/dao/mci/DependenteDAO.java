package dao.mci;

import dao.GenericDAO;
import entity.mci.Dependente;
import java.util.List;

public class DependenteDAO extends GenericDAO {

    public long saveDependente(Dependente dependente) {
        saveOrUpdatePojo(dependente);
        return dependente.getId();
    }

    public void updateDependente(Dependente dependente) {
        saveOrUpdatePojo(dependente);
    }

    public Dependente getDependente(int dependenteId) {
        Dependente Dependente = getPojo(Dependente.class, dependenteId);
        return Dependente;
    }

    public List<Dependente> getDependentes() {
        return getPureList(Dependente.class, "from Dependente dependente");
    }
//    public DependenteDAO(Session ses) {
//        super(ses);
//    }
//    public void removeDependente(Dependente dependente) {
//        removePojo(dependente);
//    }
}
