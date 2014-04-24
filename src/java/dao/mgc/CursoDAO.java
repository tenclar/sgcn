package dao.mgc;

import dao.GenericDAO;
import entity.mgc.Curso;
import java.util.List;

public class CursoDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public CursoDAO() {
    }

    public long saveCurso(Curso curso) {
        saveOrUpdatePojo(curso);
        return curso.getId();
    }

    public void updateCurso(Curso curso) {
        saveOrUpdatePojo(curso);
    }

    public Curso getCurso(int cursoId) {
        Curso Curso = getPojo(Curso.class, cursoId);
        return Curso;
    }

    public List<Curso> getCursos() {
        return getPureList(Curso.class, "from Curso curso Order by curso.nome");
    }
//    public CursoDAO(Session ses) {
//        super(ses);
//    }
//    public void removeCurso(Curso curso) {
//        removePojo(curso);
//    }

    public List<Curso> getCurso(String argumento) {
          String params = "%" + argumento + "%";
        return getPureList(Curso.class, "from Curso c where c.nome like ? Order by c.nome",params);
    }
}
