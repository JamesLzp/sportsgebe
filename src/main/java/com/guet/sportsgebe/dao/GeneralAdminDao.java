package com.guet.sportsgebe.dao;

import com.guet.sportsgebe.entity.GeneralAdmin;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (GeneralAdmin)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-05 14:39:28
 */
public interface GeneralAdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param gadminid 主键
     * @return 实例对象
     */
    GeneralAdmin queryById(String gadminid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GeneralAdmin> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param generalAdmin 实例对象
     * @return 对象列表
     */
    List<GeneralAdmin> queryAll(GeneralAdmin generalAdmin);

    /**
     * 新增数据
     *
     * @param generalAdmin 实例对象
     * @return 影响行数
     */
    int insert(GeneralAdmin generalAdmin);

    /**
     * 修改数据
     *
     * @param generalAdmin 实例对象
     * @return 影响行数
     */
    int update(GeneralAdmin generalAdmin);

    /**
     * 通过主键删除数据
     *
     * @param gadminid 主键
     * @return 影响行数
     */
    int deleteById(String gadminid);

}