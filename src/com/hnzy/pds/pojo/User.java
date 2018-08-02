package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private String UserName;
	private String PassWord;
	private String password1;// 
	private String password2;// 
	private Date CreateTime;
	private int root; // 
	private List<Menu> menus;
	private List<Role> roles;
	 
	private List<UserRole> ur;
	
	
	public User(int iD, String userName, String passWord, String password1, String password2, Date createTime, int root,
			List<Menu> menus, List<Role> roles, List<UserRole> ur) {
		super();
		ID = iD;
		UserName = userName;
		PassWord = passWord;
		this.password1 = password1;
		this.password2 = password2;
		CreateTime = createTime;
		this.root = root;
		this.menus = menus;
		this.roles = roles;
		this.ur = ur;
	}


	public List<Menu> getMenus() {
		return menus;
	}


	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public List<UserRole> getUr() {
		return ur;
	}


	public void setUr(List<UserRole> ur) {
		this.ur = ur;
	}


	public User() {
		super();
	}


	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public User(int iD, String userName, String passWord, Date createTime, int root) {
		super();
		ID = iD;
		UserName = userName;
		PassWord = passWord;
		CreateTime = createTime;
	}

	 

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CreateTime == null) ? 0 : CreateTime.hashCode());
		result = prime * result + ID;
		result = prime * result + ((PassWord == null) ? 0 : PassWord.hashCode());
		result = prime * result + ((UserName == null) ? 0 : UserName.hashCode());
		result = prime * result + root;
		return result;
	}

	public User(int iD, String userName, String passWord, String password1, String password2, Date createTime,
			int root) {
		super();
		ID = iD;
		UserName = userName;
		PassWord = passWord;
		this.password1 = password1;
		this.password2 = password2;
		CreateTime = createTime;
		this.root = root;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (CreateTime == null) {
			if (other.CreateTime != null)
				return false;
		} else if (!CreateTime.equals(other.CreateTime))
			return false;
		if (ID != other.ID)
			return false;
		if (PassWord == null) {
			if (other.PassWord != null)
				return false;
		} else if (!PassWord.equals(other.PassWord))
			return false;
		if (UserName == null) {
			if (other.UserName != null)
				return false;
		} else if (!UserName.equals(other.UserName))
			return false;
		if (root != other.root)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", UserName=" + UserName + ", PassWord=" + PassWord + ", password1=" + password1
				+ ", password2=" + password2 + ", CreateTime=" + CreateTime + ", root=" + root + "]";
	}
	

}
