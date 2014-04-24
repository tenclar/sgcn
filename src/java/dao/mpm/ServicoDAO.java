package dao.mpm;

import dao.GenericDAO;
import entity.mpm.Servico;
import java.util.List;

public class ServicoDAO extends GenericDAO {

    public long saveServico(Servico servico) {
        saveOrUpdatePojo(servico);
        return servico.getId();
    }

    public void updateServico(Servico servico) {
        saveOrUpdatePojo(servico);
    }

    public Servico getServico(int servicoId) {
        Servico Servico = getPojo(Servico.class, servicoId);
        return Servico;
    }

    public List<Servico> getServicos() {
        return getPureList(Servico.class, "from Servico servico");
    }
//    public void removeServico(Servico servico) {
//        removePojo(servico);
//    }
}
