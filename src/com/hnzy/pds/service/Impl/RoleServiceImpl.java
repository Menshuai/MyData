package com.hnzy.pds.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.RoleDao;
import com.hnzy.pds.pojo.Role;
import com.hnzy.pds.pojo.User;
import com.hnzy.pds.pojo.UserRole;
import com.hnzy.pds.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> findAllRole() {
		return roleDao.findAllRole();
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);		
	}

	@Override
	public void edit(Role role) {
		roleDao.edit(role);
		
	}

	@Override
	public void delete(String id) {
		roleDao.delete(id);
	}

	@Override
	public List<User> findUsers(String id) {
		return roleDao.findUsers(id);
	}
	
	//编辑指定用户的角色
	@Override
	public int editURole(String[] roleId, String id) {
		roleDao.delete(id);
		int addURole=0;
		UserRole userRole=new UserRole();
		for(int i=0;i<roleId.length;i++){
			userRole.setUserId(id);
			userRole.setRoleId(roleId[i]);
			addURole=roleDao.addURole(userRole)+addURole;
		}
		return addURole;
	}

	@Override
	public List<Role> findRoleId(String id) {
		return roleDao.findRoleId(id);
	}
	
}
