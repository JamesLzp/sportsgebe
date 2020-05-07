package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.GeneralAdmin;
import com.guet.sportsgebe.dao.GeneralAdminDao;
import com.guet.sportsgebe.service.GeneralAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GeneralAdmin)表服务实现类
 *
 * @author makejava
 * @since 2020-05-05 14:39:28
 */
@Service("generalAdminService")
public class GeneralAdminServiceImpl implements GeneralAdminService {
    @Resource
    private GeneralAdminDao generalAdminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param gadminid 主键
     * @return 实例对象
     */
    @Override
    public GeneralAdmin queryById(String gadminid) {
        return this.generalAdminDao.queryById(gadminid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GeneralAdmin> queryAllByLimit(int offset, int limit) {
        return this.generalAdminDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<GeneralAdmin> queryAll(GeneralAdmin generalAdmin) {
        return this.generalAdminDao.queryAll(generalAdmin);
    }

    /**
     * 新增数据
     *
     * @param generalAdmin 实例对象
     * @return 实例对象
     */
    @Override
    public GeneralAdmin insert(GeneralAdmin generalAdmin) {
        this.generalAdminDao.insert(generalAdmin);
        return generalAdmin;
    }

    /**
     * 修改数据
     *
     * @param generalAdmin 实例对象
     * @return 实例对象
     */
    @Override
    public GeneralAdmin update(GeneralAdmin generalAdmin) {
        this.generalAdminDao.update(generalAdmin);
        return this.queryById(generalAdmin.getGadminid());
    }

    /**
     * 通过主键删除数据
     *
     * @param gadminid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String gadminid) {
        return this.generalAdminDao.deleteById(gadminid) > 0;
    }
}