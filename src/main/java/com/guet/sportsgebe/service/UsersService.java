package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.Users;
import java.util.List;

/**
 * (Users)表服务接口
 *
 * @author makejava
 * @since 2020-03-15 01:52:13
 */
public interface UsersService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    Users queryById(String userid);

    /**
     * 通过Name查询单条数据
     *
     * @param username
     * @return 实例对象
     */
    Users queryByName(String username);

    List<Users> queryByInIds(List UsersId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Users> queryAllByLimit(int offset, int limit);

    List<Users> queryAll(Users users);

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users insert(Users users);

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users update(Users users);

    Users updateTop(Users users);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(String userid);

}