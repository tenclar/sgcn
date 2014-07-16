package dao.mpm;

import dao.GenericDAO;
import entity.mci.enumerator.EnumTipoPessoa;
import entity.mpm.Ano;
import entity.mpm.Plano;
import entity.mpm.Rendimento;
import java.util.List;

public class PlanoDAO extends GenericDAO {

    public PlanoDAO() {
       
    }

    
    
    public long savePlano(Plano plano) {
        saveOrUpdatePojo(plano);
        return plano.getId();
    }

    public void updatePlano(Plano plano) {
        saveOrUpdatePojo(plano);
    }

    public Plano getPlano(int planoId) {
        Plano Plano = getPojo(Plano.class, planoId);
        return Plano;
    }

    public List<Plano> getPlanos() {
        return getPureList(Plano.class, "from Plano plano");
    }
    public List<Plano> getListByCidId(int id) {
        
        String query = "from Plano p fetch all properties where p.cidadao.id = ?";
        return getPureList(Plano.class, query, id);
    }
     public List<Plano> getListByRespCnp(String cnp) {
        
        String query = "from Plano p  where p.cidadao.cpf = ?";
        return getPureList(Plano.class, query, cnp);
    }
    
    public List<Plano> getListByRespCnp(String cnp,EnumTipoPessoa tipopessoa) {
        
        String query = "from Plano p  where p.cidadao.cpf = ? and p.cidadao.tipopessoa = ?";
        return getPureList(Plano.class, query, cnp, tipopessoa);
    }
    
     public List<Plano> getListByRespName(String nome, EnumTipoPessoa tipopessoa) {
        String param1 = "%" + nome + "%";
        String query = " from Plano p  where  p.cidadao.nome like ? and p.ciadao.tipopessoa = ?";
        return getPureList(Plano.class, query, param1, tipopessoa);
    }
     public List<Plano> getListByPlanoName(String nome, EnumTipoPessoa tipopessoa) {
        String param1 = "%" + nome + "%";
        String query = " from Plano p  where  p.nomeempreendimento like ? and p.cidadao.tipopessoa = ?";
        return getPureList(Plano.class, query, param1,tipopessoa);
    }
     public List<Plano> getListByPlanoNameBeneficiado(String nome, EnumTipoPessoa tipopessoa) {
        String param1 = "%" + nome + "%";
        String query = " from Plano p  where  p.cidadao.nome like ? and p.cidadao.tipopessoa = ?";
        return getPureList(Plano.class, query, param1,tipopessoa);
    }
    
    
//    public void removePlano(Plano plano) {
//        removePojo(plano);
//    }
}
