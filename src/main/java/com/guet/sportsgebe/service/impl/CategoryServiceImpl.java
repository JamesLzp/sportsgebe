package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.Category;
import com.guet.sportsgebe.dao.CategoryDao;
import com.guet.sportsgebe.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 23:27:44
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cateid 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(String cateid) {
        return this.categoryDao.queryById(cateid);
    }

    @Override
    public List<Category> queryByInIds(List CateList){
        return this.categoryDao.queryByInIds(CateList);
    }

    @Override
    public List<Category> queryByInNames(List cateName){
        return this.categoryDao.queryByInNames(cateName);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Category> queryAllByLimit(int offset, int limit) {
        return this.categoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     */
    @Override
    public void insert(List<Category> categoryList){
        this.categoryDao.insert(categoryList);
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryDao.update(category);
        return this.queryById(category.getCateid());
    }

    /**
     * 通过主键删除数据
     *
     * @param cateid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String cateid) {
        return this.categoryDao.deleteById(cateid) > 0;
    }
}