package dao;

import entity.Bairro;
import java.util.List;

public class BairroDAO extends GenericDAO {

    public BairroDAO() {
    }

    public Integer saveBairro(Bairro bairro) {
        saveOrUpdatePojo(bairro);
        return bairro.getId();
    }

    public void updateBairro(Bairro bairro) {
        saveOrUpdatePojo(bairro);
    }

    public Bairro getBairro(int bairroId) {
        Bairro bairro = getPojo(Bairro.class, bairroId);
        return bairro;
    }
    public Bairro getBairro(String nome){
        String param = "%"+nome+"%";
        String query = "from Bairro b where b.nome = ?";
        Bairro bairro = getPurePojo(Bairro.class,query, param);
        return bairro;
    }
    public List<Bairro> getBairros() {
        return getPureListMaxResult(Bairro.class, "from Bairro bairro order by bairro.nome asc");
    }

    public List<Bairro> getBairros(int id) {
        return getPureList(Bairro.class, "from Bairro b where b.cidade.id = ? order by b.nome asc", id);
    }
    public List<Bairro> getBairros(String nome,int cidade) {
        
        String query = "from Bairro b where b.nome = ? and b.cidade.id = ?";
        return getPureList(Bairro.class, query, nome, cidade);
    }
    public List<Bairro> getBairro(String nome,int cidade) {
        if (nome.isEmpty()){
            nome = "%";
            
        }
        String s="%"+nome+"%";
        String query = "from Bairro b where b.nome like ? and b.cidade.id = ?";
        return getPureList(Bairro.class, query, s, cidade);
    }
//    public BairroDAO(Session ses) {
//        super(ses);
//    }
//    public void removeBairro(Bairro bairro) {
//        removePojo(bairro);
//    }
}
