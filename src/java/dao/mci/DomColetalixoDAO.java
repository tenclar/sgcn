package dao.mci;

import dao.GenericDAO;
import entity.mci.DomColetaLixo;
import java.util.List;

public class DomColetalixoDAO extends GenericDAO {

    public Integer saveColetaLixo(DomColetaLixo coletalixo) {
        saveOrUpdatePojo(coletalixo);
        return coletalixo.getId();
    }

    public void updateColetaLixo(DomColetaLixo coletalixo) {
        saveOrUpdatePojo(coletalixo);
    }

    public DomColetaLixo getColetaLixo(int coletalixoId) {
        DomColetaLixo coletalixo = getPojo(DomColetaLixo.class, coletalixoId);
        return coletalixo;
    }

    public List<DomColetaLixo> getColetaLixos() {
        return getPureList(DomColetaLixo.class, "from DomColetaLixo domcoletalixo");
    }
//    public void removeColetaLixo(DomColetaLixo coletalixo) {
//        removePojo(coletalixo);
//    }
}
