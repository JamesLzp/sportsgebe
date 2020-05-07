package com.guet.sportsgebe.dao;

import com.guet.sportsgebe.entity.Activity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Activity)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-12 00:45:08
 */
public interface ActivityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param activityid 主键
     * @return 实例对象
     */
    Activity queryById(String activityid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Activity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<Activity> queryHot4Limit(@Param("offset") int offset, @Param("limit") int limit, Activity activity);

    /**
     * 查询简介数据
     *
     * @return
     */
    List<Activity> queryAllDes();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param activity 实例对象
     * @return 对象列表
     */
    List<Activity> queryAll(Activity activity);

    List<Activity> queryByLike(Activity activity);

    List<Activity> queryByInIds(List originatorName);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int insert(Activity activity);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param activityid 主键
     * @return 影响行数
     */
    int deleteById(String activityid);

    int deleteByInIds(List activityid);

}