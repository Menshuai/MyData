package com.hnzy.pds.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.User;

public  interface UserDao {
	
	/**
	 * @author Administrator
	 *根据id查询
	 */
	public User findUserById(int id);
	
	/**
	 * 注册
	 * @param user
	 */
	
	public void addUser(User user);
	
   /**
    * 查找信息
    * @return
    */
	public List<User> find();
	
	
	/**
	 * @author   插入信息
	 */
	public void Insert( User user);
	
	/**
	 * @author Administrator 删除
	 */
	public int delete(int id);

	/**
	 * @author Administrator 根据id查找信息
	 */
	public User findById(int id);

	/**
	 * @author Administrator 更新信息update
	 * 
	 */
	public void update(User  user);
	
	public User findUserByNameAndMD5(@Param("userName") String userName, @Param("passWord")String passWord);

	public User findUserByName(@Param("name")String name);
	
	public List<User> findJSName();
	
	public int findID(@Param("userName")String userName);
}
