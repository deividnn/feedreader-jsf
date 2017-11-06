package dao;
/**
 * @author deividnn
 */
import entidades.Feed;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import util.hibernate.HibernateSF;
import util.hibernate.Transactional;

public class FeedDAO implements Serializable {

    @Inject
    private HibernateSF sessionFactory;

    @Transactional
    public void inserir(Feed produto) {
        sessionFactory.getCurrentSession().save(produto);
    }

    @Transactional
    public void atualizar(Feed produto) {
        sessionFactory.getCurrentSession().update(produto);
    }

    @Transactional
    public void excluir(Feed produto) {
        sessionFactory.getCurrentSession().delete(produto);
    }

    public Feed pegar(int id) {
        Feed produto = sessionFactory.getCurrentSession().get(Feed.class, id);
        return produto;
    }

    @SuppressWarnings("unchecked")
    public List<Feed> listar() {
        List<Feed> produtos = sessionFactory.getCurrentSession().
                createQuery("select vo from Feed vo order by id desc").list();
        return produtos;
    }

}
