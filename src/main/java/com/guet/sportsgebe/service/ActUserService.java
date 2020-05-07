package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.ActUser;
import java.util.List;

/**
 * (ActUser)表服务接口
 *
 * @author makejava
 * @since 2020-04-27 22:58:00
 */
public interface ActUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    ActUser queryById(String userid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ActUser> queryAllByLimit(int offset, int limit);

    List<ActUser> queryAll(ActUser actUser);

    /**
     * 新增数据
     *
     * @param actUser 实例对象
     * @return 实例对象
     */
    ActUser insert(ActUser actUser);

    /**
     * 修改数据
     *
     * @param actUser 实例对象
     * @return 实例对象
     */
    ActUser update(ActUser actUser);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(ActUser actUser);

    boolean deleteByActId(String actid);
}