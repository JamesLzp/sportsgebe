package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.Activity;
import com.guet.sportsgebe.dao.ActivityDao;
import com.guet.sportsgebe.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Activity)表服务实现类
 *
 * @author makejava
 * @since 2020-04-12 00:45:08
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    DataSourceTransactionManager dataSourceTransactionManager;

    @Resource
    private ActivityDao activityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param activityid 主键
     * @return 实例对象
     */
    @Override
    public Activity queryById(String activityid) {
        return this.activityDao.queryById(activityid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Activity> queryAllByLimit(int offset, int limit) {
        return this.activityDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Activity> queryHot4Limit(int offset, int limit, Activity activity) {
        return this.activityDao.queryHot4Limit(offset, limit, activity);
    }

    /**
     * 查询简介数据
     *
     * @return
     */
    @Override
    public List<Activity> queryAllDes(){
        return this.activityDao.queryAllDes();

    }

    /**
     * 使用实例作为条件查询数据
     * @param activity
     * @return
     */
    public List<Activity> queryAll(Activity activity){
        return this.activityDao.queryAll(activity);
    }

    public List<Activity> queryByLike(Activity activity){
        return this.activityDao.queryByLike(activity);
    }

    public List<Activity> queryByInIds(List originatorName){
        return this.activityDao.queryByInIds(originatorName);
    }

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public Activity insert(Activity activity) {
        this.activityDao.insert(activity);
        return activity;
    }

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public Activity update(Activity activity) {
        this.activityDao.update(activity);
        return this.queryById(activity.getActivityid());
    }

    /**
     * 通过主键删除数据
     *
     * @param activityid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String activityid) {
        return this.activityDao.deleteById(activityid) > 0;
    }

    @Override
    public boolean deleteByInIds(List activityid) {
        return this.activityDao.deleteByInIds(activityid) > 0;
    }
}