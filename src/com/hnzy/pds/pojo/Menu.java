package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7041904293044105481L;

	private int id;
	private String name;
	private String url;
	private Integer pid;
	private List<Menu> childMenus;
	private Date createTime;
	private Date lastEditTime;
	private List<Role> roles;
	
	public Menu(){
		
	}

	public Menu(int id, String name, String url, Integer pid, List<Menu> childMenus, Date createTime, Date lastEditTime,
			List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.pid = pid;
		this.childMenus = childMenus;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<Menu> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<Menu> childMenus) {
		this.childMenus = childMenus;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", url=" + url + ", pid=" + pid + ", childMenus=" + childMenus
				+ ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", roles=" + roles + "]";
	}

	
}
