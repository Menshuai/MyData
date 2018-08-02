package com.hnzy.pds.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.User;

public  interface UserDao {
	
	/**
	 * @author Administrator
	 *����id��ѯ
	 */
	public User findUserById( int id);
	
	/**
	 * ע��
	 * @param user
	 */
	
	public void addUser(User user);
	
   /**
    * ������Ϣ
    * @return
    */
	public List<User> find();
	
	
	/**
	 * @author   ������Ϣ
	 */
	public void Insert( User user);
	
	/**
	 * @author Administrator ɾ��
	 */
	public int delete(int id);

	/**
	 * @author Administrator ����id������Ϣ
	 */
	public User findById(@Param("ID")int id);

	/**
	 * @author Administrator ������Ϣupdate
	 * 
	 */
	public void update(User  user);
	
	public User findUserByNameAndMD5(@Param("userName") String userName, @Param("passWord")String passWord);

	public User findUserByName(@Param("name")String name);
	
	public List<User> findJSName();
	
	public int findID(@Param("userName")String userName);
}
