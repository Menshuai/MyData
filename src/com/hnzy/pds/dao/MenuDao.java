package com.hnzy.pds.dao;

import java.util.List;

import com.hnzy.hot.base.BaseDao;
 
import com.hnzy.pds.pojo.Menu;
import com.hnzy.pds.pojo.RoleMenu;

public interface MenuDao extends BaseDao<Menu>{
	
	// 增加用户角色
	public int addURole(RoleMenu userRole);
	 
	// 删除用户角色
	public int delURole(String id);
	
	//根据角色的id查询该用户的菜单
	public List<Menu> findMenuByRId(Integer id);
	
	//查询所有的菜单
    public List<Menu>find();
}
