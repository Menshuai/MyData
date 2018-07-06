package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
 * 缴费表12
 * @author Administrator
 *
 */
public class Jf  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String yhbh;//用户编号
	private int jfje;//缴费金额
	private int hjje;//合计金额
	private int syje;//剩余金额
	private Date time;//缴费时间
	 
	private YhMessage yhMessage;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	public int getJfje() {
		return jfje;
	}

	public void setJfje(int jfje) {
		this.jfje = jfje;
	}

	public int getHjje() {
		return hjje;
	}

	public void setHjje(int hjje) {
		this.hjje = hjje;
	}

	public int getSyje() {
		return syje;
	}

	public void setSyje(int syje) {
		this.syje = syje;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public YhMessage getYhMessage() {
		return yhMessage;
	}

	public void setYhMessage(YhMessage yhMessage) {
		this.yhMessage = yhMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Jf [id=" + id + ", yhbh=" + yhbh + ", jfje=" + jfje + ", hjje=" + hjje + ", syje=" + syje + ", time="
				+ time + ", yhMessage=" + yhMessage + "]";
	}

	
	
	
	
}
