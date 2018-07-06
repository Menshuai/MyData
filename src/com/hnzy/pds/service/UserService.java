package com.hnzy.pds.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.User;

public  interface UserService {

	/**
	 * 登录
	 * @param user
	 */
	public void regist(User user);
	
	
	   /**
	    * 查找信息
	    * @return
	    */
		public List<User> find();
		
		
		/**
		 * @author Administrator 插入用户信息
		 */
		public void InsertUser(User user);
		
		/**
		 * @author Administrator 删除用户
		 */
		public void deleteUser(String id);

		/**
		 * @author Administrator 根据id查找用戶信息
		 */
		public User findById(int id);

		/**
		 * @author Administrator 更新用户信息
		 */
		public void updateUse(User user);
		
		
		
		public User findUserByNameAndMD5(String username, String password);

		public User findUserByName(String username);
		 
		/**
		 * @author Administrator
		 *根据id查询
		 */
		public User findUserById(int id);
		
		public List<User> findJSName();
		
		public int findID(@Param("userName")String userName);
}
