package dao.mci;

import dao.GenericDAO;
import entity.mci.RamoEmpreendimento;
import java.util.List;

public class RamoEmpreendimentoDAO extends GenericDAO {

    public RamoEmpreendimentoDAO() {
    }

    public long saveRamoEmpreendimento(RamoEmpreendimento ramoEmpreendimento) {
        saveOrUpdatePojo(ramoEmpreendimento);
        return ramoEmpreendimento.getId();
    }

    public void updateRamoEmpreendimento(RamoEmpreendimento ramoEmpreendimento) {
        saveOrUpdatePojo(ramoEmpreendimento);
    }

    public RamoEmpreendimento getRamoEmpreendimento(int ramoEmpreendimentoId) {
        RamoEmpreendimento RamoEmpreendimento = getPojo(RamoEmpreendimento.class, ramoEmpreendimentoId);
        return RamoEmpreendimento;
    }

    public List<RamoEmpreendimento> getRamoEmpreendimentos() {
        return getPureList(RamoEmpreendimento.class, "from RamoEmpreendimento r order by r.nome");
    }
//    public RamoEmpreendimentoDAO(Session ses) {
//        super(ses);
//    }
//    public void removeRamoEmpreendimento(RamoEmpreendimento ramoEmpreendimento) {
//        removePojo(ramoEmpreendimento);
//    }
}
