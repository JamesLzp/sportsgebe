package com.guet.sportsgebe.dao;

import com.guet.sportsgebe.entity.Article;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-19 19:53:52
 */
public interface ArticleDao {

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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Article> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param article 实例对象
     * @return 对象列表
     */
    List<Article> queryAll(Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int insert(Article article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int update(Article article);

    int updateByName(Article article);

    /**
     * 通过主键删除数据
     *
     * @param articleid 主键
     * @return 影响行数
     */
    int deleteById(String articleid);

}