package dao.mci;

import dao.GenericDAO;
import entity.mci.Equipamento;
import java.util.List;

public class EquipamentoDAO extends GenericDAO {

    public long saveEquipamento(Equipamento equipamento) {
        saveOrUpdatePojo(equipamento);
        return equipamento.getId();
    }

    public void updateEquipamento(Equipamento equipamento) {
        saveOrUpdatePojo(equipamento);
    }

    public Equipamento getEquipamento(int equipamentoId) {
        Equipamento Equipamento = getPojo(Equipamento.class, equipamentoId);
        return Equipamento;
    }

    public List<Equipamento> getEquipamentos() {
        return getPureList(Equipamento.class, "from Equipamento equipamento");
    }
    public List<Equipamento> getEquipamentos(String equip) {
        String logr = "%"+equip+"%";
        String query = "from Equipamento e "
                + "where e.nome like ?";
        return getPureList(Equipamento.class, query,logr);
    }
//    public void removeEquipamento(Equipamento equipamento) {
//        removePojo(equipamento);
//    }
}
