package dao.mci;

import dao.GenericDAO;
import entity.mci.CursosSecretaria;
import java.util.List;

public class CursoSecretariaDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public long saveCursoSecretaria(CursosSecretaria cursosSecretaria) {
        saveOrUpdatePojo(cursosSecretaria);
        return cursosSecretaria.getId();
    }

    public void updateCursosSecretaria(CursosSecretaria cursosSecretaria) {
        saveOrUpdatePojo(cursosSecretaria);
    }
    
     public CursosSecretaria getEntityByIdCurso(int idcurso,int idcidadao) {
         String query = "from CursosSecretaria c  where c.curso.id = ? and c.cidadao.id = ?";
        CursosSecretaria cursosSecretaria =  getPurePojo(CursosSecretaria.class,query,idcurso,idcidadao);
        return cursosSecretaria;
    }

    public CursosSecretaria getCursosSecretaria(int cursosSecretariaId) {
        CursosSecretaria CursoSecretaria = getPojo(CursosSecretaria.class, cursosSecretariaId);
        return CursoSecretaria;
    }

    public List<CursosSecretaria> getCursosSecretarias() {
        return getPureList(CursosSecretaria.class, "from CursosSecretaria cursoSecretaria");
    }
    

//    public CursoSecretariaDAO(Session ses) {
//        super(ses);
//    }
//    public void removeCursoSecretaria(CursoSecretaria cursoSecretaria) {
//        removePojo(cursoSecretaria);
//    }
}
