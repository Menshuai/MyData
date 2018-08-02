package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//�û�-��ɫ�������ID
	private String user_id;//�û��������ID
	private String role_id;//��ɫ�������ID
	private Date createTime;//����ʱ��
	private Date lastEditTime;//����޸�ʱ��
	
	
	public UserRole() {
		super();
	}
	public UserRole(Integer id, String userId, String roleId, Date createTime, Date lastEditTime) {
		super();
		this.id = id;
		this.user_id = userId;
		this.role_id = roleId;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + user_id + ", roleId=" + role_id + ", createTime=" + createTime
				+ ", lastEditTime=" + lastEditTime + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String userId) {
		this.user_id = userId;
	}
	public String getRoleId() {
		return role_id;
	}
	public void setRoleId(String roleId) {
		this.role_id = roleId;
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
