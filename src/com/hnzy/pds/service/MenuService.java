package com.hnzy.pds.service;

import java.util.List;

import com.hnzy.pds.pojo.Menu;

public interface MenuService {

	int editURole(String[] roleId, String id);
	
	//根据角色的id查询该用户的菜单
	public List<Menu> findMenuByRId(Integer id);
	
	 //查询所有的菜单
    public List<Menu>find();
    
    
    //根据用户姓名查找用户菜单
    public List<Menu>findMenuByUserName(String userName);
}
