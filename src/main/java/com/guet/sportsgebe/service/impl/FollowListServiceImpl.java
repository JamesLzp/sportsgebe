package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.FollowList;
import com.guet.sportsgebe.dao.FollowListDao;
import com.guet.sportsgebe.service.FollowListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FollowList)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 14:39:07
 */
@Service("followListService")
public class FollowListServiceImpl implements FollowListService {
    @Resource
    private FollowListDao followListDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public List<FollowList> queryById(String userid) {
        return this.followListDao.queryById(userid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FollowList> queryAllByLimit(int offset, int limit) {
        return this.followListDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<FollowList> queryAll(FollowList followList) {
        return this.followListDao.queryAll(followList);
    }

    /**
     * 新增数据
     *
     * @param followList 实例对象
     * @return 实例对象
     */
    @Override
    public FollowList insert(FollowList followList) {
        this.followListDao.insert(followList);
        return followList;
    }

    /**
     * 修改数据
     *
     * @param followList 实例对象
     * @return 实例对象
     */
    @Override
    public void update(FollowList followList) {
        this.followListDao.update(followList);
        this.queryById(followList.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userid) {
        return this.followListDao.deleteById(userid) > 0;
    }

    @Override
    public boolean deleteByFollowId(String followerId) {
        return this.followListDao.deleteByFollowId(followerId) > 0;
    }
}