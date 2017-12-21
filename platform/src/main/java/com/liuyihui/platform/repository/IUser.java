package com.liuyihui.platform.repository;


import com.liuyihui.platform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 映射文件对应的接口
 *
 * @author liuyh
 */
public interface IUser {
    /**
     * 方法名为映射文件中的id
     *
     * @param id
     * @return
     */
    User selectUserByID(@Param("id") int id);

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    User selectUserByName(@Param("userName") String userName);

    /**
     * 查询用户实体集合
     *
     * @return
     */
    List<User> queryUser(@Param("userName") String userName);

    /**
     * 查询用户实体集合2 测试使用resultMap
     *
     * @return
     */
    List<User> queryUser2(@Param("userName") String userName);

    /**
     * 插入用户表的方法
     */
    void addUser(@Param("user") User user);

    /**
     * 更新用户表的方法
     */
    Integer updateUser(@Param("user") User user);

    /**
     * 删除 用户表记录的方法
     */
    Integer deleteUser(@Param("id") Integer id);
}
