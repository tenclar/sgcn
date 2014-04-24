package dao;

import entity.Estado;
import java.util.List;

public class EstadoDAO extends GenericDAO {

    public EstadoDAO() {
    }

    public Integer saveEstado(Estado estado) {
        saveOrUpdatePojo(estado);
        return estado.getId();
    }

    public void updateEstado(Estado estado) {
        saveOrUpdatePojo(estado);
    }

    public Estado getEstado(int estadoId) {
        Estado estado = getPojo(Estado.class, estadoId);
        return estado;
    }

    public List<Estado> getEstados() {
        return getPureList(Estado.class, "from Estado estado");
    }
//    public EstadoDAO(Session ses) {
//        super(ses);
//    }
//    public void removeEstado(Estado estado) {
//        removePojo(estado);
//    }
}
