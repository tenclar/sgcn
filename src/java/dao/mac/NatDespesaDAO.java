package dao.mac;

import dao.GenericDAO;
import entity.mac.NatDespesa;

import java.util.List;

public class NatDespesaDAO extends GenericDAO {

    public NatDespesaDAO() {
    }

    public long saveNatDespesa(NatDespesa natDespesa) {
        saveOrUpdatePojo(natDespesa);
        return natDespesa.getId();
    }

    public void updateNatDespesa(NatDespesa natDespesa) {
        saveOrUpdatePojo(natDespesa);
    }

    public NatDespesa getNatDespesa(int natDespesaId) {
        NatDespesa NatDespesa = getPojo(NatDespesa.class, natDespesaId);
        return NatDespesa;
    }

    public List<NatDespesa> getNatDespesas() {
        return getPureList(NatDespesa.class, "from NatDespesa c");
    }
    public List<NatDespesa> getNatDespesas(String codigo) {
        String params = "%" + codigo + "%";
        String query = " from NatDespesa c  where  c.codigo like ?";
        return getPureList(NatDespesa.class, query, params);
    }
    
    
    public List<NatDespesa> getNatDespesasByDescricao(String descricao ) {
        String params = "%" + descricao + "%";
        String query = " from NatDespesa c  where  c.descricao like ?";
        return getPureList(NatDespesa.class, query, params);
    }
}
