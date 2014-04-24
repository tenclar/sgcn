package dao.mpm;

import dao.GenericDAO;
import entity.mpm.Empreendimento;
import java.util.List;

public class EmpreendimentoDAO extends GenericDAO {

    public long saveEmpreendimento(Empreendimento empreendimento) {
        saveOrUpdatePojo(empreendimento);
        return empreendimento.getId();
    }

    public void updateEmpreendimento(Empreendimento empreendimento) {
        saveOrUpdatePojo(empreendimento);
    }

    public Empreendimento getEmpreendimento(int empreendimentoId) {
        Empreendimento Empreendimento = getPojo(Empreendimento.class, empreendimentoId);
        return Empreendimento;
    }

    public List<Empreendimento> getEmpreendimentos() {
        return getPureList(Empreendimento.class, "from Empreendimento empreendimento");
    }
//    public void removeEmpreendimento(Empreendimento empreendimento) {
//        removePojo(empreendimento);
//    }
}
