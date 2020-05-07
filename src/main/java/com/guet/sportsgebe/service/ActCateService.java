package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.ActCate;
import java.util.List;

/**
 * (ActCate)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 22:24:31
 */
public interface ActCateService {

    /**
     * 通过ID查询单条数据
     *
     * @param actid 主键
     * @return 实例对象
     */
    List<ActCate> queryById(String actid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ActCate> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param actCate 实例对象
     * @return 实例对象
     */
    ActCate insert(ActCate actCate);

    void inserts(List actCate);

    /**
     * 修改数据
     *
     * @param actCate 实例对象
     * @return 实例对象
     */
    void update(ActCate actCate);

    /**
     * 通过主键删除数据
     *
     * @param actid 主键
     * @return 是否成功
     */
    boolean deleteById(String actid);

    boolean deleteByInIds(List actid);

}