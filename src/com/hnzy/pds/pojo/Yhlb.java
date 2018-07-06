package com.hnzy.pds.pojo;

import java.io.Serializable;

/**
 * 用户类别4
 * 
 * @author Administrator
 *
 */
public class Yhlb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String zz;
	private String sy;
	private String lzf;
	private String qt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZz() {
		return zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public String getSy() {
		return sy;
	}

	public void setSy(String sy) {
		this.sy = sy;
	}

	public String getLzf() {
		return lzf;
	}

	public void setLzf(String lzf) {
		this.lzf = lzf;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Yhlb [id=" + id + ", zz=" + zz + ", sy=" + sy + ", lzf=" + lzf + ", qt=" + qt + "]";
	}

}
