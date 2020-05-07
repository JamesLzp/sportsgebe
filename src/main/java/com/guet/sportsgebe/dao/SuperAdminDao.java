package com.guet.sportsgebe.dao;

import com.guet.sportsgebe.entity.SuperAdmin;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SuperAdmin)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-07 21:48:37
 */
public interface SuperAdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param sadminid 主键
     * @return 实例对象
     */
    SuperAdmin queryById(String sadminid);

    SuperAdmin queryByName(String sadminname);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SuperAdmin> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param superAdmin 实例对象
     * @return 对象列表
     */
    List<SuperAdmin> queryAll(SuperAdmin superAdmin);

    /**
     * 新增数据
     *
     * @param superAdmin 实例对象
     * @return 影响行数
     */
    int insert(SuperAdmin superAdmin);

    /**
     * 修改数据
     *
     * @param superAdmin 实例对象
     * @return 影响行数
     */
    int update(SuperAdmin superAdmin);

    /**
     * 通过主键删除数据
     *
     * @param sadminid 主键
     * @return 影响行数
     */
    int deleteById(String sadminid);

}