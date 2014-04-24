package dao.mpm;

import dao.GenericDAO;
import entity.mpm.Produto;
import java.util.List;

public class ProdutoDAO extends GenericDAO {

    public long saveProduto(Produto produto) {
        saveOrUpdatePojo(produto);
        return produto.getId();
    }

    public void updateProduto(Produto produto) {
        saveOrUpdatePojo(produto);
    }

    public Produto getProduto(int produtoId) {
        Produto Produto = getPojo(Produto.class, produtoId);
        return Produto;
    }

    public List<Produto> getProdutos() {
        return getPureList(Produto.class, "from Produto produto");
    }
//    public void removeProduto(Produto produto) {
//        removePojo(produto);
//    }
}
