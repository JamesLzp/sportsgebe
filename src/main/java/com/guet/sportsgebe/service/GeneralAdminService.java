package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.GeneralAdmin;
import java.util.List;

/**
 * (GeneralAdmin)表服务接口
 *
 * @author makejava
 * @since 2020-05-05 14:39:28
 */
public interface GeneralAdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param gadminid 主键
     * @return 实例对象
     */
    GeneralAdmin queryById(String gadminid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GeneralAdmin> queryAllByLimit(int offset, int limit);

    List<GeneralAdmin> queryAll(GeneralAdmin generalAdmin);

    /**
     * 新增数据
     *
     * @param generalAdmin 实例对象
     * @return 实例对象
     */
    GeneralAdmin insert(GeneralAdmin generalAdmin);

    /**
     * 修改数据
     *
     * @param generalAdmin 实例对象
     * @return 实例对象
     */
    GeneralAdmin update(GeneralAdmin generalAdmin);

    /**
     * 通过主键删除数据
     *
     * @param gadminid 主键
     * @return 是否成功
     */
    boolean deleteById(String gadminid);

}