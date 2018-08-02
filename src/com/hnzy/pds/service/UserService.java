package com.hnzy.pds.service;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.User;

public  interface UserService {

	/**
	 * ��¼
	 * @param user
	 */
	public void regist(User user);
	
	
	   /**
	    * ������Ϣ
	    * @return
	    */
		public List<User> find();
		
		
		/**
		 * @author Administrator �����û���Ϣ
		 */
		public void InsertUser(User user);
		
		/**
		 * @author Administrator ɾ���û�
		 */
		public void deleteUser(String id);
		public void delete(String id);

		/**
		 * @author Administrator ����id�����Ñ���Ϣ
		 */
		public User findById(int id);

		/**
		 * @author Administrator �����û���Ϣ
		 */
		public void updateUse(User user);
		
		
		
		public User findUserByNameAndMD5(String username, String password);

		public User findUserByName(String username);
		 
		/**
		 * @author Administrator
		 *����id��ѯ
		 */
		public User findUserById(int id);
		
		public List<User> findJSName();
		
		public int findID(@Param("userName")String userName);


		 
}
