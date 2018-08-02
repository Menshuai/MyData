package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


  
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private List<Menu> menus;//菜单列表
	private List<User> users;//用户列表
	private Date createTime;
	private Date lastEditTime;
	private User user;//与用户表关联的字段
	private List<RoleMenu> roleMenus;
	
	
	
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<RoleMenu> getRoleMenus() {
		return roleMenus;
	}
	public void setRoleMenus(List<RoleMenu> roleMenus) {
		this.roleMenus = roleMenus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", menus=" + menus + ", users=" + users + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + ", user=" + user + ", roleMenus=" + roleMenus + "]";
	}
	 
	
	
}
