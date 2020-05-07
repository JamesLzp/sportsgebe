package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.FollowList;
import java.util.List;

/**
 * (FollowList)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 14:39:07
 */
public interface FollowListService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return
     */
    List<FollowList> queryById(String userid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FollowList> queryAllByLimit(int offset, int limit);

    List<FollowList> queryAll(FollowList followList);

    /**
     * 新增数据
     *
     * @param followList 实例对象
     * @return 实例对象
     */
    FollowList insert(FollowList followList);

    /**
     * 修改数据
     *
     * @param followList 实例对象
     * @return 实例对象
     */
    void update(FollowList followList);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(String userid);

    boolean deleteByFollowId(String followerId);
}