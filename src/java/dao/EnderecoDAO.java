package dao;

import entity.Bairro;
import entity.Cidade;
import entity.Endereco;
import entity.Estado;
import java.util.List;

public class EnderecoDAO extends GenericDAO {

    public EnderecoDAO() {
    }

    public long saveEndereco(Endereco endereco) {
        saveOrUpdatePojo(endereco);
        return endereco.getId();
    }

    public void updateEndereco(Endereco endereco) {
        saveOrUpdatePojo(endereco);
    }

    public Endereco getEndereco(int enderecoId) {
        Endereco Endereco = getPojo(Endereco.class, enderecoId);
        return Endereco;
    }

    public List<Endereco> getEnderecos() {
        return getPureListMaxResult(Endereco.class, "from Endereco endereco");
    }

    public List<Endereco> getEnderecos(String log) {
        String params = "%" + log + "%";
        String query = "from Endereco e fetch all properties  where e.logradouro like ?";
        return getPureListMaxResult(Endereco.class, query, params);
    }
    
    public List<Endereco> getEnderecosMaxResult(String log) {
        String param1 = "%" + log + "%";
//        String query = "from Endereco e fetch all properties  where e.logradouro like ? ";
        String query = "from Endereco e "
                + " left join fetch e.bairro b "
                + " left join fetch b.cidade c "
                + " left join fetch c.estado est "
                + " where e.logradouro like ? "
                + " group by e "
                + " order by est.sigla ";
        return getPureListMaxResult(Endereco.class, query, param1);
    }
    public List<Endereco> getEnderecosDetalhado(Estado e, Cidade c,Bairro b, String logradouro) {
        String param1 = "%" + logradouro + "%";
        String query;
        if (b.getId() == 0){
            query = "from Endereco e "            
                + " where  e.bairro.cidade = ? and e.bairro.cidade.estado = ? and "
                + " e.logradouro like ? "
                + " group by e "
                + " order by e.bairro.cidade.estado.sigla ";
            return getPureListMaxResult(Endereco.class, query,c,e, param1);
        }else {
         query = "from Endereco e "            
                + " where e.bairro like ? and e.bairro.cidade = ? and e.bairro.cidade.estado = ? and "
                + " e.logradouro like ? "
                + " group by e "
                + " order by e.bairro.cidade.estado.sigla ";
         return getPureListMaxResult(Endereco.class, query, b,c,e, param1);
        }
        
    }
//    public void removeEndereco(Endereco endereco) {
//        removePojo(endereco);
//    }
}
