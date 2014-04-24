package dao.mci;

import dao.GenericDAO;
import entity.mci.Cursos;
import java.util.List;

public class CursosDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public long saveCursos(Cursos cursos) {
        saveOrUpdatePojo(cursos);
        return cursos.getId();
    }

    public void updateCursos(Cursos cursos) {
        saveOrUpdatePojo(cursos);
    }

    public Cursos getCursos(int cursosId) {
        Cursos Cursos = getPojo(Cursos.class, cursosId);
        return Cursos;
    }

    public List<Cursos> getCursoss() {
        return getPureList(Cursos.class, "from Cursos cursos");
    }

//    public CursosDAO(Session ses) {
//        super(ses);
//    }
//    public void removeCursos(Cursos cursos) {
//        removePojo(cursos);
//    }
}
