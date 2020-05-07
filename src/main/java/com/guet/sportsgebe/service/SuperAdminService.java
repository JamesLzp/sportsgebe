package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.SuperAdmin;
import java.util.List;

/**
 * (SuperAdmin)表服务接口
 *
 * @author makejava
 * @since 2020-05-07 21:48:37
 */
public interface SuperAdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param sadminid 主键
     * @return 实例对象
     */
    SuperAdmin queryById(String sadminid);

    SuperAdmin queryByName(String sadminname);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SuperAdmin> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param superAdmin 实例对象
     * @return 实例对象
     */
    SuperAdmin insert(SuperAdmin superAdmin);

    /**
     * 修改数据
     *
     * @param superAdmin 实例对象
     * @return 实例对象
     */
    SuperAdmin update(SuperAdmin superAdmin);

    /**
     * 通过主键删除数据
     *
     * @param sadminid 主键
     * @return 是否成功
     */
    boolean deleteById(String sadminid);

}