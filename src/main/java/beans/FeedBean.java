package beans;

import dao.FeedDAO;
import entidades.Feed;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import util.jsf.FacesUtil;

/**
 * @author deividnn
 */
@Named
@ViewScoped
public class FeedBean implements Serializable {

    @Inject
    FeedDAO feedDAO;

    private Feed feed;
    private List<Feed> feeds;
    private boolean edicao;
    private boolean verfeed;

    @PostConstruct
    public void init() {
        edicao = false;
        verfeed = false;
        feeds = feedDAO.listar();
    }

    public void ver(Feed p) {
        this.feed = p;
        verfeed = true;
    }

    public void salvar() {
        try {
            feed.setDatahora(Calendar.getInstance().getTime());
            feed.setDescricao(feed.getDescricao().toUpperCase());
            if (feed.getId() == null) {
                feedDAO.inserir(feed);
                FacesUtil.mensagem("feed " + feed.getDescricao() + " salvo");

            } else {
                feedDAO.atualizar(feed);
                FacesUtil.mensagemAviso("feed " + feed.getDescricao() + " atualizado");

            }
            edicao = false;
        } catch (Exception e) {
            FacesUtil.mensagemErro(e.toString());
        } finally {
            feeds = feedDAO.listar();
            FacesUtil.atualizaform("form");
        }
    }

    public void excluir(Feed p) {
        try {
            String desc = p.getDescricao();
            feedDAO.excluir(p);
            FacesUtil.mensagemAviso("feed " + desc + " excluido");
        } catch (Exception e) {
            FacesUtil.mensagemErro(e.toString());
        } finally {
            feeds = feedDAO.listar();
            FacesUtil.atualizaform("form");
        }
    }

    public void editar() {
        feed = new Feed();
        edicao = true;
        verfeed = false;
    }

    public void editar(Feed p) {
        feed = p;
        edicao = true;
        verfeed = false;
    }

    public void cancelar() {
        edicao = false;
        verfeed = false;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public boolean isVerfeed() {
        return verfeed;
    }

    public void setVerfeed(boolean verfeed) {
        this.verfeed = verfeed;
    }

    
}
