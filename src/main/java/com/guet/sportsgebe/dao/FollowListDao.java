package com.guet.sportsgebe.dao;

import com.guet.sportsgebe.entity.FollowList;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (FollowList)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 14:39:07
 */
public interface FollowListDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    List<FollowList> queryById(String userid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FollowList> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param followList 实例对象
     * @return 对象列表
     */
    List<FollowList> queryAll(FollowList followList);

    /**
     * 新增数据
     *
     * @param followList 实例对象
     * @return 影响行数
     */
    int insert(FollowList followList);

    /**
     * 修改数据
     *
     * @param followList 实例对象
     * @return 影响行数
     */
    int update(FollowList followList);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(String userid);


    int deleteByFollowId(String followerId);
}