package com.hnzy.pds.pojo;

import java.io.Serializable;

/**
 * 层管表3
 * 
 * @author Administrator
 *
 */
public class Cg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String cgbh; // 层管编号
	private String jzqnet; // 集中器net
	private String azdz; // 安装地址
	private String bz; // 备注
	
	private Jzq jzq;
	
	private YhMessage yhMessage;
	
	
	
	 

	public YhMessage getYhMessage() {
		return yhMessage;
	}

	public void setYhMessage(YhMessage yhMessage) {
		this.yhMessage = yhMessage;
	}

	public Jzq getJzq() {
		return jzq;
	}

	public void setJzq(Jzq jzq) {
		this.jzq = jzq;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCgbh() {
		return cgbh;
	}

	public void setCgbh(String cgbh) {
		this.cgbh = cgbh;
	}

	public String getJzqnet() {
		return jzqnet;
	}

	public void setJzqnet(String jzqnet) {
		this.jzqnet = jzqnet;
	}

	public String getAzdz() {
		return azdz;
	}

	public void setAzdz(String azdz) {
		this.azdz = azdz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Cg [id=" + id + ", cgbh=" + cgbh + ", jzqnet=" + jzqnet + ", azdz=" + azdz + ", bz=" + bz + ", jzq="
				+ jzq + ", yhMessage=" + yhMessage + "]";
	}

	 

	 

}
