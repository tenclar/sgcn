package dao.mac;

import dao.GenericDAO;
import entity.mac.Contato;
import java.util.List;

public class ContatoDAO extends GenericDAO {

    public ContatoDAO() {
    }

    public long saveContato(Contato contato) {
        saveOrUpdatePojo(contato);
        return contato.getId();
    }

    public void updateContato(Contato contato) {
        saveOrUpdatePojo(contato);
    }

    public Contato getContato(int contatoId) {
        Contato Contato = getPojo(Contato.class, contatoId);
        return Contato;
    }

    public List<Contato> getContatos() {
        return getPureList(Contato.class, "from Contato contato");
    }

}
