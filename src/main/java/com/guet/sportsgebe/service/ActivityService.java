package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.Activity;
import java.util.List;

/**
 * (Activity)表服务接口
 *
 * @author makejava
 * @since 2020-04-12 00:45:08
 */
public interface ActivityService {

    /**
     * 通过ID查询单条数据
     *
     * @param activityid 主键
     * @return 实例对象
     */
    Activity queryById(String activityid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Activity> queryAllByLimit(int offset, int limit);

    List<Activity> queryHot4Limit(int offset, int limit, Activity activity);

    /**
     * 查询简介数据
     *
     * @return
     */
    List<Activity> queryAllDes();

    /**
     * 使用实例作为条件查询数据
     *
     * @param activity
     * @return
     */
    List<Activity> queryAll(Activity activity);

    List<Activity> queryByLike(Activity activity);

    List<Activity> queryByInIds(List originatorName);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    Activity insert(Activity activity);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    Activity update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param activityid 主键
     * @return 是否成功
     */
    boolean deleteById(String activityid);

    boolean deleteByInIds(List activityid);

}