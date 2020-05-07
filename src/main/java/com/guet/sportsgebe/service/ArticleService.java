package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.Article;
import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2020-04-19 19:53:52
 */
public interface ArticleService {

    /**
     * 通过ID查询单条数据
     *
     * @param articleid
     * @return 实例对象
     */
    Article queryByIds(String articleid);

    Article queryById(String articlename);

    List<Article> queryByIdLike(String articleid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Article> queryAllByLimit(int offset, int limit);

    List<Article> queryAll(Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    Article insert(Article article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    Article update(Article article);

    void updateByName(Article article);

    /**
     * 通过主键删除数据
     *
     * @param articleid 主键
     * @return 是否成功
     */
    boolean deleteById(String articleid);

}