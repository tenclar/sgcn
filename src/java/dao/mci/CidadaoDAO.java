package dao.mci;

import dao.GenericDAO;
import entity.Bairro;
import entity.Cidade;
import entity.mci.Cidadao;
import entity.mci.enumerator.EnumStatusBeneficio;
import entity.mci.enumerator.EnumStatusCid;
import entity.mci.enumerator.EnumTipoPessoa;
import entity.relatorio.QuadroQuantitativo;
import java.util.ArrayList;
import java.util.List;

public class CidadaoDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public CidadaoDAO() {
    }

    public long save(Cidadao cidadao) {
        saveOrUpdatePojo(cidadao);
        return cidadao.getId();
    }

    public void update(Cidadao cidadao) {
        saveOrUpdatePojo(cidadao);
    }

    public void merge(Cidadao cidadao) {
        mergePojo(cidadao);

    }

    public Cidadao getEntity(int cidadaoId) {
        Cidadao cidadao = getPojo(Cidadao.class, cidadaoId);
        return cidadao;
    }

    public Cidadao getEntity(String cidadaoName) {
        Cidadao cidadao = getPojo(Cidadao.class, cidadaoName);
        return cidadao;
    }

    public Cidadao getEntityByCnp(String cidadaoCnp) {
        String query = "from Cidadao c  where c.cpf = ?";
        Cidadao cidadao = getPurePojo(Cidadao.class, query, cidadaoCnp);
        return cidadao;
    }

    public List<Cidadao> getList() {
        return getPureList(Cidadao.class, "from Cidadao cidadao");
    }

    public List<Cidadao> getListByCnp(String cnp) {
        //  String params = "%"+log+"%";
        String query = "from Cidadao c  where c.cpf = ?";
        return getPureList(Cidadao.class, query, cnp);

    }

    public List<Cidadao> getList(String nome) {
        String params = "%" + nome + "%";
        String query = " from Cidadao c  where  c.nome like ?";
        return getPureList(Cidadao.class, query, params);
    }

    public List<Cidadao> getListByCnp(String cnp, EnumTipoPessoa tipopessoa) {

        String query = "from Cidadao c  where c.cpf = ? and c.tipopessoa = ?";
        return getPureList(Cidadao.class, query, cnp, tipopessoa);
    }

    public List<Cidadao> getList(EnumTipoPessoa tipopessoa) {

        String query = " from Cidadao c  where  c.tipopessoa = ?";
        return getPureList(Cidadao.class, query, tipopessoa);
    }

    public List<Cidadao> getList(String nome, EnumTipoPessoa tipopessoa) {
        String param1 = "%" + nome + "%";
        String query = " from Cidadao c  where  c.nome like ? and c.tipopessoa = ?";
        return getPureList(Cidadao.class, query, param1, tipopessoa);
    }

    public List<Cidadao> getListByCnp(String cnp, EnumTipoPessoa tipopessoa, EnumStatusBeneficio statusbeneficio) {

        String query = "from Cidadao c  where c.cpf = ? and c.tipopessoa = ? and c.benstatus = ?";
        return getPureList(Cidadao.class, query, cnp, tipopessoa, statusbeneficio);
    }

    public List<Cidadao> getList(String nome, EnumTipoPessoa tipopessoa, EnumStatusBeneficio statusBeneficio) {
        String param1 = "%" + nome + "%";
        String query = " from Cidadao c  where  c.nome like ? and c.tipopessoa = ? and c.benstatus = ?";
        return getPureList(Cidadao.class, query, param1, tipopessoa, statusBeneficio);
    }

    public List<Cidadao> getListByCnp(String cnp, EnumTipoPessoa tipopessoa, EnumStatusBeneficio statusbeneficio, EnumStatusCid statuscid) {

        String query = "from Cidadao c  where c.cpf = ? and c.tipopessoa = ? and c.benstatus = ? and c.statuscid = ?";
        return getPureList(Cidadao.class, query, cnp, tipopessoa, statusbeneficio, statuscid);
    }

    public List<Cidadao> getList(String nome, EnumTipoPessoa tipopessoa, EnumStatusBeneficio statusBeneficio, EnumStatusCid statuscid) {
        String param1 = "%" + nome + "%";
        String query = " from Cidadao c  where  c.nome like ? and c.tipopessoa = ? and c.benstatus = ? and c.statuscid =?";
        return getPureList(Cidadao.class, query, param1, tipopessoa, statusBeneficio, statuscid);
    }
    public List<Cidadao> getList(String nome, EnumTipoPessoa tipopessoa, EnumStatusBeneficio statusBeneficio, 
             EnumStatusCid statuscid,String cursosec,String equipsec,int publico, int ramo, String sexo, 
             int civil, int escolaridade, String atividade, String possuicursos, String possuiequip,String possuibensociais, String possuibenabits,
             Cidade cidade, Bairro bairro) {
        String param1 = "%" + nome + "%";
        String query = " from Cidadao c  where  c.nome like ? and c.tipopessoa = ? and c.benstatus = ? and c.statuscid =?";
        return getPureList(Cidadao.class, query, param1, tipopessoa, statusBeneficio, statuscid);
    }

    public List<Cidadao> getListByCnp(String cnp, EnumTipoPessoa tipopessoa, EnumStatusCid statuscid) {

        String query = "from Cidadao c  where c.cpf = ? and c.tipopessoa = ?  and c.statuscid = ?";
        return getPureList(Cidadao.class, query, cnp, tipopessoa, statuscid);
    }

    public List<Cidadao> getList(String nome, EnumTipoPessoa tipopessoa, EnumStatusCid statuscid) {
        String param1 = "%" + nome + "%";
        String query = " from Cidadao c  where  c.nome like ? and c.tipopessoa = ? and c.statuscid =?";
        return getPureList(Cidadao.class, query, param1, tipopessoa, statuscid);
    }

    public List<Cidadao> getListEquipamentosSecretaria(EnumTipoPessoa tipopessoa) {

        String query = " from Cidadao cid "
                + "where cid.equipamentossecretarias.size > 0 "
                + "and cid.tipopessoa = ? "
                + "group by cid.endereco.bairro.cidade.nome, cid.nome "
                + "order by cid.endereco.bairro.cidade.nome, cid.nome asc";
        return getPureList(Cidadao.class, query, tipopessoa);
    }

    public List<QuadroQuantitativo> getQuadroQuantitativo() {

//        String query = "select cast(c.tipopessoa as string) as tipopessoa, "
//                + " cast(c.statuscid as string) as statuscid, "
//                + "cast(c.benstatus as string) as benstatus "
//                + " from Cidadao c "
//                + "group by c.tipopessoa, c.statuscid, c.benstatus "
//                + "order by c.tipopessoa desc";
          String query = "select new entity.relatorio.QuadroQuantitativo(cast(c.tipopessoa as string) as tipopessoa, "
                + " cast(c.statuscid as string) as statuscid, "
                + "cast(c.benstatus as string) as benstatus, cast(count(c) as int) as total) "
                + " from Cidadao c "
                + "group by c.tipopessoa, c.statuscid, c.benstatus "
                + "order by c.tipopessoa desc";
        return getPureList(QuadroQuantitativo.class, query);
    }
    
    public List<Cidadao> getListaAnoDemandas(){
        
        
        try {
            String query =" select distinct c.anodemanda as anodemanda from Cidadao c where c.tipopessoa = 'CID'";
       
        
        return   getPureList(Cidadao.class, query)  ;
            
        } catch (Exception e) {
            System.out.println("erro anodemanda "+e.getMessage());
          return null;
        }
        
    }
   
//    public List<Cidadao> getList(String log) {
//        String params = "%" + log + "%";
//        /*
//         * String query = "from Cidadao c " + "left join fetch c.cursos " +
//         * "left join fetch c.cursosSecretarias " + "left join fetch
//         * c.equipamentosproprios " + "left join fetch c.equipamentossecretarias
//         * " + "left join fetch c.cidbBenSociais " + "left join fetch
//         * c.cidBenHabits " + "left join fetch c.cidAtividades " + "left join
//         * fetch c.despesas " + "left join fetch c.endereco " + "left join fetch
//         * c.domicilio " + "left join fetch c.dependentes " + "where c.nome like
//         * ? " + "group by c.id";
//         */
//        String query = " from Cidadao c  where  c.nome like ?";
//        return getPureList(Cidadao.class, query, params);
//    }
//    public List<Cidadao> getListCriteria(String argumento, String expressao) {
//        return getListCriteria(Cidadao.class, argumento, expressao);
//    }
//    public CidadaoDAO(Session ses) {
//        super(ses);
//    }
//    public void remove(Cidadao cidadao) {
//        removePojo(cidadao);
//    }
}
