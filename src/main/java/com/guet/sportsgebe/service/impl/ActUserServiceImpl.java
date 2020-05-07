package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.ActUser;
import com.guet.sportsgebe.dao.ActUserDao;
import com.guet.sportsgebe.service.ActUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ActUser)表服务实现类
 *
 * @author makejava
 * @since 2020-04-27 22:58:00
 */
@Service("actUserService")
public class ActUserServiceImpl implements ActUserService {
    @Resource
    private ActUserDao actUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public ActUser queryById(String userid) {
        return this.actUserDao.queryById(userid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ActUser> queryAllByLimit(int offset, int limit) {
        return this.actUserDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<ActUser> queryAll(ActUser actUser) {
        return this.actUserDao.queryAll(actUser);
    }

    /**
     * 新增数据
     *
     * @param actUser 实例对象
     * @return 实例对象
     */
    @Override
    public ActUser insert(ActUser actUser) {
        this.actUserDao.insert(actUser);
        return actUser;
    }

    /**
     * 修改数据
     *
     * @param actUser 实例对象
     * @return 实例对象
     */
    @Override
    public ActUser update(ActUser actUser) {
        this.actUserDao.update(actUser);
        return this.queryById(actUser.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(ActUser actUser) {
        return this.actUserDao.deleteById(actUser) > 0;
    }

    @Override
    public boolean deleteByActId(String actid) {
        return this.actUserDao.deleteByActId(actid) > 0;
    }
}