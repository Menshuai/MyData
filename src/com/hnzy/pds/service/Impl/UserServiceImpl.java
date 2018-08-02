package com.hnzy.pds.service.Impl;

 
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.controller.UserController;
import com.hnzy.pds.dao.UserDao;
import com.hnzy.pds.pojo.User;
import com.hnzy.pds.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	private final static Logger logger = Logger.getLogger(UserServiceImpl .class);
	@Override
	public List<User> find() {
		return userDao.find();
	}

	@Override
	public void InsertUser(User user) {
		userDao.Insert(user);
		
	}

 
	@Override
	public void deleteUser(String id) {
	 
		userDao.delete(Integer.parseInt(id));
		
	}

	@Override
	public User findById(int id) {
		 
		return userDao.findById(id);
	}

	@Override
	public void updateUse(User user) {
		 userDao.update(user);
		
	}

	@Override
	public User findUserByNameAndMD5(String username, String password) {
		return userDao.findUserByNameAndMD5(username, password);
	}

	@Override
	public User findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	@Override
	public void regist(User user) {
		userDao.addUser(user);
		
	}

	@Override
	public User findUserById(int id) {
		return userDao.findUserById(id);
	}

	

	@Override
	public List<User> findJSName() {
		return userDao.findJSName();
	}

	@Override
	public int findID(String userName) {
		return userDao.findID(userName);
	}

	@Override
	public void delete(String id) {
		try {
			int result=userDao.delete(Integer.parseInt(id));
			if(result>0){
				logger.info("删除成功一条，ID为:"+id);
			}
		} catch (Exception e) {
			logger.info("删除失败！:"+e);
		}
		
	}

	 
	 

	

	

	
	

	
}
