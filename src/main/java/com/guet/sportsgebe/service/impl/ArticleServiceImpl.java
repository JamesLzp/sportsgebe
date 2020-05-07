package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.Article;
import com.guet.sportsgebe.dao.ArticleDao;
import com.guet.sportsgebe.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2020-04-19 19:53:52
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    @Override
    public Article queryByIds(String articleid) {
        return articleDao.queryByIds(articleid);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param articlename
     * @return 实例对象
     */
    @Override
    public Article queryById(String articlename) {
        return this.articleDao.queryById(articlename);
    }

    @Override
    public List<Article> queryByIdLike(String articleid) {
        return this.articleDao.queryByIdLike(articleid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Article> queryAllByLimit(int offset, int limit) {
        return this.articleDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Article> queryAll(Article article) {
        return this.articleDao.queryAll(article);
    }

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article insert(Article article) {
        this.articleDao.insert(article);
        return article;
    }

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article update(Article article) {
        this.articleDao.update(article);
        return this.queryById(article.getArticleid());
    }

    @Override
    public void updateByName(Article article) {
        this.articleDao.updateByName(article);
    }

    /**
     * 通过主键删除数据
     *
     * @param articleid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String articleid) {
        return this.articleDao.deleteById(articleid) > 0;
    }
}