package dao.mac;

import dao.GenericDAO;
import entity.mac.Convenio;
import entity.mac.TipoConvenio;
import java.util.List;

public class ConvenioDAO extends GenericDAO {

   

    public long saveConvenio(Convenio convenio) {
        saveOrUpdatePojo(convenio);
        return convenio.getId();
    }

    public void updateConvenio(Convenio convenio) {
        
        saveOrUpdatePojo(convenio);
    }
    public void merge(Convenio convenio){
        mergePojo(convenio);
    }

    public Convenio getConvenio(int convenioId) {
        Convenio Convenio = getPojo(Convenio.class, convenioId);
        return Convenio;
    }

    public List<Convenio> getConvenios() {
        return getPureList(Convenio.class, "from Convenio c");
    }
    public List<Convenio> getConvenios(String codigo) {
        String params = "%" + codigo + "%";
        String query = " from Convenio c  where  c.codigo like ?";
        return getPureList(Convenio.class, query, params);
    }
    public List<Convenio> getConvenios(String codigo,TipoConvenio tipo) {
        String params = "%" + codigo + "%";
        String query = " from Convenio c  where  c.codigo like ? and c.tipoconvenio = ?";
        return getPureList(Convenio.class, query, params,tipo);
    }
    
    public List<Convenio> getConveniosByDescricao(String descricao ) {
        String params = "%" + descricao + "%";
        String query = " from Convenio c  where  c.descricao like ?";
        return getPureList(Convenio.class, query, params);
    }
}
