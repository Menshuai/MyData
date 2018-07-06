package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 集中器表5
 * 
 * @author Administrator
 *
 */
public class Jzq implements Serializable {

	private static final long serialVersionUID = -4072646355182930123L;
	private int id;
	//private String jzqid;
	private String jzqnet; // 集中器net
	private String jzqip;
	private String jzqport;
	private String azdz; // 安装地址

	private YhMessage yhMessage;

	public YhMessage getYhMessage() {
		return yhMessage;
	}

	public void setYhMessage(YhMessage yhMessage) {
		this.yhMessage = yhMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public String getJzqid() {
		return jzqid;
	}

	public void setJzqid(String jzqid) {
		this.jzqid = jzqid;
	}*/

	public String getJzqnet() {
		return jzqnet;
	}

	public void setJzqnet(String jzqnet) {
		this.jzqnet = jzqnet;
	}

	public String getJzqip() {
		return jzqip;
	}

	public void setJzqip(String jzqip) {
		this.jzqip = jzqip;
	}

	public String getJzqport() {
		return jzqport;
	}

	public void setJzqport(String jzqport) {
		this.jzqport = jzqport;
	}

	public String getAzdz() {
		return azdz;
	}

	public void setAzdz(String azdz) {
		this.azdz = azdz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Jzq [id=" + id + ", jzqnet=" + jzqnet + ", jzqip=" + jzqip + ", jzqport=" + jzqport + ", azdz=" + azdz
				+ ", yhMessage=" + yhMessage + "]";
	}

	 

	 

}
