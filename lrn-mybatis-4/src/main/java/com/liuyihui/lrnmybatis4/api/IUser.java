package com.liuyihui.lrnmybatis4.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuyihui.lrnmybatis4.entity.User;


/**
 * 映射文件对应的接口
 * 
 * @author liuyh
 */
public interface IUser {
	/**
	 * 方法名为映射文件中的id
	 * @param id
	 * @return 
	 */
	public User selectUserByID(int id);
	/**
	 * 查询用户实体集合
	 * @return 
	 */
	public List<User> queryUser(@Param("userName")String userName);
	/**
	 * 查询用户实体集合2 测试使用resultMap
	 * @return 
	 */
	public List<User> queryUser2(@Param("userName")String userName);
	
	/**
	 *  插入用户表的方法
	 */
	public void addUser(@Param("user")User user);
	/**
	 *  更新用户表的方法
	 */
	public Integer updateUser(@Param("user")User user);
	/**
	 *  删除 用户表记录的方法
	 */
	public Integer deleteUser(@Param("id")Integer id);
}
