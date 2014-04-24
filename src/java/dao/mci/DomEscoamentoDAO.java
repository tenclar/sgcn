package dao.mci;

import dao.GenericDAO;
import entity.mci.DomEscoamento;
import java.util.List;

public class DomEscoamentoDAO extends GenericDAO {

    public Integer saveEscoamento(DomEscoamento domescoamento) {
        saveOrUpdatePojo(domescoamento);
        return domescoamento.getId();
    }

    public void updateEscoamento(DomEscoamento domescoamento) {
        saveOrUpdatePojo(domescoamento);
    }

    public DomEscoamento getEscoamento(int domescoamentoId) {
        DomEscoamento domescoamento = getPojo(DomEscoamento.class, domescoamentoId);
        return domescoamento;
    }

    public List<DomEscoamento> getEscoamentos() {
        return getPureList(DomEscoamento.class, "from DomEscoamento domescoamento");
    }
//    public void removeEscoamento(DomEscoamento domescoamento) {
//        removePojo(domescoamento);
//    }
}
