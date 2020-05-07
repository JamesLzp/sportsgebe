package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.ActCate;
import com.guet.sportsgebe.dao.ActCateDao;
import com.guet.sportsgebe.service.ActCateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ActCate)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 22:24:31
 */
@Service("actCateService")
public class ActCateServiceImpl implements ActCateService {
    @Resource
    private ActCateDao actCateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param actid 主键
     * @return 实例对象
     */
    @Override
    public List<ActCate> queryById(String actid) {
        return this.actCateDao.queryById(actid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ActCate> queryAllByLimit(int offset, int limit) {
        return this.actCateDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param actCate 实例对象
     * @return 实例对象
     */
    @Override
    public ActCate insert(ActCate actCate) {
        this.actCateDao.insert(actCate);
        return actCate;
    }

    @Override
    public void inserts(List actCate){
        this.actCateDao.inserts(actCate);
    }

    /**
     * 修改数据
     *
     * @param actCate 实例对象
     * @return 实例对象
     */
    @Override
    public void update(ActCate actCate) {
        this.actCateDao.update(actCate);
        this.queryById(actCate.getActid());
    }

    /**
     * 通过主键删除数据
     *
     * @param actid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String actid) {
        return this.actCateDao.deleteById(actid) > 0;
    }

    @Override
    public boolean deleteByInIds(List actid) {
        return this.actCateDao.deleteByInIds(actid) > 0;
    }
}