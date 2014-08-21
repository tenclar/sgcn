package dao.mgc;

import dao.GenericDAO;
import entity.Bairro;
import entity.Cidade;
import entity.mci.Cidadao;
import entity.mci.enumerator.EnumStatusCurso;
import entity.mci.enumerator.EnumTipoPessoa;
import entity.mgc.Turma;
import java.util.List;

public class TurmaDAO extends GenericDAO {

    private static final long serialVersionUID = 1L;

    public long saveTurma(Turma turma) {
        saveOrUpdatePojo(turma);
        return turma.getId();
    }

    public void updateTurma(Turma turma) {
        saveOrUpdatePojo(turma);
    }

    public Turma getTurma(int turmaId) {
        Turma turma = getPojo(Turma.class, turmaId);
        return turma;
    }

    public List<Turma> getTurmas() {
        return getPureList(Turma.class, "from Turma turma");
    }

    public List<Turma> getTurmas(String nome) {
        String params = "%" + nome + "%";
        String query = " from Turma t "
                + " left join fetch t.curso c "
                + " where t.nome like ? "
                + " group by t "
                + " order by t.id ";

        return getPureList(Turma.class, query, params);
    }

    public List<Turma> getTurmasFiltro(String tipobusca, String nome, int anodemanda, Cidade cidade, Bairro bairro) {

        String query = "select t from Turma t "
                + " left join fetch t.curso cr "
                + "  inner join t.turcidadaos tc  "
                + "  inner join tc.cidadao c  ";
        
        
        if (tipobusca.equals("cpf")){
                query=query+ " where c.cpf = '"+nome+"' ";
        }
         if (tipobusca.equals("turma")){
                query=query+ " where t.nome like '%"+nome+"%'" ;
        }
         if (anodemanda != 0) {
                query = query + " and year(t.datainicio) = " + anodemanda;
            }
        if (cidade != null) {
            if (!cidade.getId().equals(0)) {
                query = query + " and t.endereco.bairro.cidade.id = " + cidade.getId();
            }
        }
        if (bairro != null) {
            if (!bairro.getId().equals(0)) {
                query = query + " and t.endereco.bairro.id = " + bairro.getId();
            }
        }

        query = query + " group by t "
                + " order by t.id ";

        return getPureList(Turma.class, query);
    }

    public List<Cidadao> getCidadaosInTurma(EnumTipoPessoa tipopessoa, int turmaId) {
        String query = " from Cidadao c "
                + " where c.tipopessoa = ? "
                + " and c.id in (select distinct(tc.cidadao.id) from TurCidadaos tc "
                + " where tc.turma.id = ? "
                + " group by tc.cidadao.id "
                + " order by tc.cidadao.id) "
                + " order by c.nome ";
        return getPureListMaxResult(Cidadao.class, query, tipopessoa, turmaId);
    }

    public List<Cidadao> getCidadaosByNom(String nome, EnumTipoPessoa tipopessoa, boolean cidTodos, int cursoId) {
        String param1 = "%" + nome + "%";
        EnumStatusCurso ecurso = EnumStatusCurso.RESERVA;
        String query = " select c from Cidadao c "
                + " inner join c.cursosSecretarias cs "
                + " where  c.nome like ? "
             //   + " and c.tipopessoa = ? "
                + " and cast(cs.status as string) = '" + ecurso.toString() + "'" 
                //+ EnumStatusCurso.RESERVA + "'"
                + " and cs.curso.id = " + cursoId
                + " group by c.nome "
                + " order by c.nome ";

        return getPureListMaxResult(Cidadao.class, query, param1 );
    }

    public List<Cidadao> getCidadaosByCnp(String cnp, EnumTipoPessoa tipopessoa, boolean cidTodos, int cursoId) {
         
        String query = "select c from Cidadao c "
                + "  inner join c.cursosSecretarias cs"
                + " where  c.cpf = '"+cnp+"' "
               // + " and c.tipopessoa = ? "
               + " and cast(cs.status as string) = '" + EnumStatusCurso.RESERVA + "'"
                + " and cs.curso.id = " + cursoId
                + " group by c.nome "
                + " order by c.nome ";

//        String query = "from Cidadao c "
//                + " where c.cpf = ? and c.tipopessoa = ?";
//     
//        if (cidTodos) {
//            query += " and c.id in (select distinct(cs.cidadao.id) from CursosSecretaria cs "
//                    + " where cs.curso.id = " + cursoId
//                    + " and cs.status = '" + EnumStatusCurso.RESERVA + "'"
//                    + " group by cs.cidadao.id "
//                    + " order by cs.cidadao.id) ";
//        }
//        query += " order by c.nome ";
        return getPureListMaxResult(Cidadao.class, query);
    }

    public List<Cidadao> getAssocidos(int coop, EnumTipoPessoa tipopessoa) {
        String query = " from Cidadao c "
                + " where c.tipopessoa = ? "
                //+ " and c.id not in (select distinct(tc.cidadao.id) from TurCidadaos tc "
                //+ " group by tc.cidadao.id "
                // + " order by tc.cidadao.id) "
                + " and c.id in (select distinct(ca.associado.id) from CidAssociados ca "
                + " where ca.cidadao.id = ? "
                + " group by ca.associado.id "
                + " order by ca.associado.id) "
                + " order by c.nome ";
        return getPureListMaxResult(Cidadao.class, query, tipopessoa, coop);
    }

    public List<Cidadao> getAssocidosByNom(int coop, EnumTipoPessoa tipopessoa, String param) {
        String args = "%" + param + "%";
        String query = " from Cidadao c "
                + " where c.nome like ? and c.tipopessoa = ?"
                //+ " and c.id not in (select distinct(tc.cidadao.id) from TurCidadaos tc group by tc.cidadao.id order by tc.cidadao.id) "
                + " and c.id in (select distinct(ca.associado.id) from CidAssociados ca "
                + " where ca.cidadao.id = ? "
                + " group by ca.associado.id "
                + " order by ca.associado.id) "
                + " order by c.nome ";
        return getPureListMaxResult(Cidadao.class, query, args, tipopessoa, coop);
    }

    public List<Cidadao> getAssocidosByCnp(int coop, EnumTipoPessoa tipopessoa, String param) {

        String query = " from Cidadao c "
                + " where c.cpf = ? and c.tipopessoa = ? "
                //+ " and c.id not in (select distinct(tc.cidadao.id) from TurCidadaos tc group by tc.cidadao.id  order by tc.cidadao.id) "
                + " and c.id in (select distinct(ca.associado.id) from CidAssociados ca "
                + " where ca.cidadao.id = ? "
                + " group by ca.associado.id "
                + " order by ca.associado.id) "
                + " order by c.nome ";
        return getPureListMaxResult(Cidadao.class, query, param, tipopessoa, coop);
    }
}
