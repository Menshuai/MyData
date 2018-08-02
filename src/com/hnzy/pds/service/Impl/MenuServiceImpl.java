package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.MenuDao;
import com.hnzy.pds.pojo.Menu;
import com.hnzy.pds.pojo.RoleMenu;
import com.hnzy.pds.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao menuDao;
	@Override
	public int editURole(String[] roleId, String id) {
		menuDao.delURole(id);
		int addURole=0;
		RoleMenu userRole=new RoleMenu();
		for(int i=0;i<roleId.length;i++){
			userRole.setRoleId(id);
			userRole.setMenuId(roleId[i]);;
			addURole=menuDao.addURole(userRole)+addURole;
		}
		return  addURole;
	}
	@Override
	public List<Menu> findMenuByRId(Integer id) {
		return menuDao.findMenuByRId(id);
	}
	@Override
	public List<Menu> find() {
		return menuDao.find();
	}

	
	
}
