package dao;

import entity.Cidade;
import java.util.List;

public class CidadeDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public CidadeDAO() {
    }

    public long saveCidade(Cidade Cidade) {
        saveOrUpdatePojo(Cidade);
        return Cidade.getId();
    }

    public void updateCidade(Cidade Cidade) {
        saveOrUpdatePojo(Cidade);
    }

    public Cidade getCidade(int CidadeId) {
        Cidade Cidade = getPojo(Cidade.class, CidadeId);
        return Cidade;
    }

    public List<Cidade> getCidades() {
        return getPureList(Cidade.class, "from Cidade cidade order by cidade.nome asc");
    }

    public List<Cidade> getCidades(int estadoid) {
        return getPureList(Cidade.class, "from Cidade cidade where cidade.estado.id = ? order by cidade.nome asc", estadoid);
    }
//    public CidadeDAO(Session ses) {
//        super(ses);
//    }
//    public void removeCidade(Cidade Cidade) {
//        removePojo(Cidade);
//    }
}
