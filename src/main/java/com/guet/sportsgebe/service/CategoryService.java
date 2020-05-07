package com.guet.sportsgebe.service;

import com.guet.sportsgebe.entity.Category;
import java.util.List;

/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 23:27:42
 */
public interface CategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param cateid 主键
     * @return 实例对象
     */
    Category queryById(String cateid);

    List<Category> queryByInIds(List CateList);

    List<Category> queryByInNames(List cateName);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Category> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @return 实例对象
     */
    void insert(List<Category> categoryList);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param cateid 主键
     * @return 是否成功
     */
    boolean deleteById(String cateid);

}