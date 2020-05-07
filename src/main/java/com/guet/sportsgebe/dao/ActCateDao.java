package com.guet.sportsgebe.dao;

import com.guet.sportsgebe.entity.ActCate;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ActCate)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 22:24:31
 */
public interface ActCateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param actid 主键
     * @return 实例对象
     */
    List<ActCate> queryById(String actid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ActCate> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param actCate 实例对象
     * @return 对象列表
     */
    List<ActCate> queryAll(ActCate actCate);

    /**
     * 新增数据
     *
     * @param actCate 实例对象
     * @return 影响行数
     */
    int insert(ActCate actCate);

    int inserts(List actCate);

    /**
     * 修改数据
     *
     * @param actCate 实例对象
     * @return 影响行数
     */
    int update(ActCate actCate);

    /**
     * 通过主键删除数据
     *
     * @param actid 主键
     * @return 影响行数
     */
    int deleteById(String actid);

    int deleteByInIds(List actid);

}