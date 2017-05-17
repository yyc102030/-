package com.tedu.cloudnote.dao;

import org.springframework.stereotype.Repository;

import com.tedu.cloudnote.entity.User;
/**
 * 用户信息访问层 
 */
@Repository("userdao")
public interface UserDao {	
	/**
	 * 根据用户名查找用户信息
	 * @param name 用户名
	 * @return 如果找到返回用户信息,否正确返回null 
	 */
	public User findUserByName(String name);
	
	/**
	 * 将用户信息保存到数据库中
	 * @param user 被保存的用户信息
	 * @return 更新数据行数, 返回1表示更新成功
	 */
	public int saveUser(User user);
}
