package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable{

	/**
	 * 发布公告实体类   T_Notice表名
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;//标题
	private String article; //协议
	private String spokesMan;//发布人
	private Date creatTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getSpokesMan() {
		return spokesMan;
	}

	public void setSpokesMan(String spokesMan) {
		this.spokesMan = spokesMan;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", article=" + article + ", spokesMan=" + spokesMan
				+ ", creatTime=" + creatTime + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
