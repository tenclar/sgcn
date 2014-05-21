package dao.mgc;

import dao.GenericDAO;
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
        String query = " from Cidadao c"
                + " where  c.nome like ? and c.tipopessoa = ?";
              //  + " and c.id not in (select distinct(tc.cidadao.id) from TurCidadaos tc group by tc.cidadao.id order by tc.cidadao.id)"; 

        if (cidTodos) {
            query += " and c.id in (select distinct(cs.cidadao.id) from CursosSecretaria cs "
                    + " where cs.curso.id = " + cursoId
                    + " and cs.status = '"+EnumStatusCurso.RESERVA +"'"
                    + " group by cs.cidadao.id "
                    + " order by cs.cidadao.id) ";
        }
        query += " order by c.nome ";
        return getPureListMaxResult(Cidadao.class, query, param1, tipopessoa);
    }

    public List<Cidadao> getCidadaosByCnp(String cnp, EnumTipoPessoa tipopessoa, boolean cidTodos, int cursoId) {
        String query = "from Cidadao c "
                + " where c.cpf = ? and c.tipopessoa = ?";
               /* + " and c.id not in (select distinct(tc.cidadao.id) from TurCidadaos tc "
                + " group by tc.cidadao.id "
                + " order by tc.cidadao.id) ";*/ 
        if (cidTodos) {
            query += " and c.id in (select distinct(cs.cidadao.id) from CursosSecretaria cs "
                    + " where cs.curso.id = " + cursoId 
                    + " and cs.status = '"+EnumStatusCurso.RESERVA +"'"
                    + " group by cs.cidadao.id "
                    + " order by cs.cidadao.id) ";
        }
        query += " order by c.nome ";
        return getPureListMaxResult(Cidadao.class, query, cnp, tipopessoa);
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
        String args = "%"+param+"%";
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
