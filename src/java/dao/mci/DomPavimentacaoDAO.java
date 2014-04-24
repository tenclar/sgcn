package dao.mci;

import dao.GenericDAO;
import entity.mci.DomPavimentacao;
import java.util.List;

public class DomPavimentacaoDAO extends GenericDAO {

    public Integer savePavimentacao(DomPavimentacao pavimentacao) {
        saveOrUpdatePojo(pavimentacao);
        return pavimentacao.getId();
    }

    public void updatePavimentacao(DomPavimentacao pavimentacao) {
        saveOrUpdatePojo(pavimentacao);
    }

    public DomPavimentacao getPavimentacao(int pavimentacaoId) {
        DomPavimentacao pavimentacao = getPojo(DomPavimentacao.class, pavimentacaoId);
        return pavimentacao;
    }

    public List<DomPavimentacao> getPavimentacaos() {
        return getPureList(DomPavimentacao.class, "from DomPavimentacao dompavimentacao");
    }
//    public void removePavimentacao(DomPavimentacao pavimentacao) {
//        removePojo(pavimentacao);
//    }
}
