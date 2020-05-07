package com.guet.sportsgebe.dao;

import com.guet.sportsgebe.entity.ActUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ActUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-27 22:58:00
 */
public interface ActUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    ActUser queryById(String userid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ActUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param actUser 实例对象
     * @return 对象列表
     */
    List<ActUser> queryAll(ActUser actUser);

    /**
     * 新增数据
     *
     * @param actUser 实例对象
     * @return 影响行数
     */
    int insert(ActUser actUser);

    /**
     * 修改数据
     *
     * @param actUser 实例对象
     * @return 影响行数
     */
    int update(ActUser actUser);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(ActUser actUser);

    int deleteByActId(String actid);
}