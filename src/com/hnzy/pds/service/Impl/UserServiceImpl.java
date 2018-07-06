package com.hnzy.pds.service.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hnzy.pds.dao.UserDao;
import com.hnzy.pds.pojo.User;
import com.hnzy.pds.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	 
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

	

	

	
	

	
}
