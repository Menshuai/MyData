package com.hnzy.pds.service;

import java.util.List;
import java.util.Map;

import com.hnzy.pds.pojo.Role;
import com.hnzy.pds.pojo.User;


public interface RoleService {

	/**
	 * 查询角色
	 * @return
	 */
	 public List<Role> findAllRole();
	 
	 
	 /**
	  * 添加一个角色
	  * @param role
	  */
	 public void save(Role role);
	 
	 /**
	  * 修改
	  * @param role
	  */
	 public void edit(Role role);
	 
	 
	 /**
	  * 删除角色，支持一次删除多个
	  * @param id
	  */
	 public void delete(String id);
	 
	 
	 /**
	  * 查询某个角色下的所有用户
	  * @param id
	  * @return
	  */
	public List<User> findUsers(String id);
	 
	/**
	 * 修改用户角色
	 * @param roleId
	 * @param id
	 * @return
	 */
	int editURole(String[] roleId, String id);
	
	/**
	 * 查询角色 id
	 */
	public List<Role> findRoleId(String id);
}