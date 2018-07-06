package com.hnzy.pds.dao;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable{

	private Integer id;//�û�-��ɫ�������ID
	private String userId;//�û��������ID
	private String roleId;//��ɫ�������ID
	private Date createTime;//����ʱ��
	private Date lastEditTime;//����޸�ʱ��
	
	
	public UserRole() {
		super();
	}
	public UserRole(Integer id, String userId, String roleId, Date createTime, Date lastEditTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", roleId=" + roleId + ", createTime=" + createTime
				+ ", lastEditTime=" + lastEditTime + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

}
