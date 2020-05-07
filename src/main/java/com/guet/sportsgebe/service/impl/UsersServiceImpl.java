package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.Users;
import com.guet.sportsgebe.dao.UsersDao;
import com.guet.sportsgebe.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author makejava
 * @since 2020-03-15 01:52:14
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersDao usersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public Users queryById(String userid) {
        return this.usersDao.queryById(userid);
    }

    /**
     * 通过Name查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    @Override
    public Users queryByName(String username) {
        return this.usersDao.queryByName(username);
    }

    @Override
    public List<Users> queryByInIds(List UsersId){
        return this.usersDao.queryByInIds(UsersId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Users> queryAllByLimit(int offset, int limit) {
        return this.usersDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Users> queryAll(Users users) {
        return this.usersDao.queryAll(users);
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users insert(Users users) {
        this.usersDao.insert(users);
        return users;
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users update(Users users) {
        this.usersDao.update(users);
        return this.queryById(users.getUserid());
    }

    @Override
    public Users updateTop(Users users) {
        this.usersDao.updateTop(users);
        return this.queryById(users.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userid) {
        return this.usersDao.deleteById(userid) > 0;
    }
}