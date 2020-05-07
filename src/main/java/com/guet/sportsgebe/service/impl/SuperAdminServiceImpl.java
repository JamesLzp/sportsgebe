package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.SuperAdmin;
import com.guet.sportsgebe.dao.SuperAdminDao;
import com.guet.sportsgebe.service.SuperAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SuperAdmin)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 21:48:37
 */
@Service("superAdminService")
public class SuperAdminServiceImpl implements SuperAdminService {
    @Resource
    private SuperAdminDao superAdminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sadminid 主键
     * @return 实例对象
     */
    @Override
    public SuperAdmin queryById(String sadminid) {
        return this.superAdminDao.queryById(sadminid);
    }

    @Override
    public SuperAdmin queryByName(String sadminname) {
        return this.superAdminDao.queryByName(sadminname);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SuperAdmin> queryAllByLimit(int offset, int limit) {
        return this.superAdminDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param superAdmin 实例对象
     * @return 实例对象
     */
    @Override
    public SuperAdmin insert(SuperAdmin superAdmin) {
        this.superAdminDao.insert(superAdmin);
        return superAdmin;
    }

    /**
     * 修改数据
     *
     * @param superAdmin 实例对象
     * @return 实例对象
     */
    @Override
    public SuperAdmin update(SuperAdmin superAdmin) {
        this.superAdminDao.update(superAdmin);
        return this.queryById(superAdmin.getSadminid());
    }

    /**
     * 通过主键删除数据
     *
     * @param sadminid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String sadminid) {
        return this.superAdminDao.deleteById(sadminid) > 0;
    }
}