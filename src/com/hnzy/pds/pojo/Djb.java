package com.hnzy.pds.pojo;

import java.io.Serializable;

public class Djb  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String yhlb;//用户类别
	private Double mjdj;//面积单价
	private Double nldj;//能量单价
	private String bz;//备注
	
	private YhMessage yhMessage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYhlb() {
		return yhlb;
	}

	public void setYhlb(String yhlb) {
		this.yhlb = yhlb;
	}

	public Double getMjdj() {
		return mjdj;
	}

	public void setMjdj(Double mjdj) {
		this.mjdj = mjdj;
	}

	public Double getNldj() {
		return nldj;
	}

	public void setNldj(Double nldj) {
		this.nldj = nldj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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
		return "Djb [id=" + id + ", yhlb=" + yhlb + ", mjdj=" + mjdj + ", nldj=" + nldj + ", bz=" + bz + ", yhMessage="
				+ yhMessage + "]";
	}
	
	
}
