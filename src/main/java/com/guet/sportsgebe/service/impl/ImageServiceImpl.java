package com.guet.sportsgebe.service.impl;

import com.guet.sportsgebe.entity.Image;
import com.guet.sportsgebe.dao.ImageDao;
import com.guet.sportsgebe.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Image)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 02:41:55
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageDao imageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param imageid 主键
     * @return 实例对象
     */
    @Override
    public Image queryById(String imageid) {
        return this.imageDao.queryById(imageid);
    }

    @Override
    public List<Image> queryAllByLike(Image image) {
        return this.imageDao.queryAllByLike(image);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Image> queryAllByLimit(int offset, int limit) {
        return this.imageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param image 实例对象
     * @return 实例对象
     */
    @Override
    public Image insert(Image image) {
        this.imageDao.insert(image);
        return image;
    }

    /**
     * 修改数据
     *
     * @param image 实例对象
     * @return 实例对象
     */
    @Override
    public Image update(Image image) {
        this.imageDao.update(image);
        return this.queryById(image.getImageid());
    }

    /**
     * 通过主键删除数据
     *
     * @param imageid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String imageid) {
        return this.imageDao.deleteById(imageid) > 0;
    }

    @Override
    public boolean deleteByLikeName(String imagename) {
        return this.imageDao.deleteByLikeName(imagename) > 0;
    }

    @Override
    public List<Image> selectNameTest(String imagename) {
        return this.imageDao.selectNameTest(imagename);
    }
}