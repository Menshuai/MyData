package com.hnzy.pds.pojo;

import java.io.Serializable;
/**
 * 风盘表2
 * @author Administrator
 *
 */
public class Fp implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private String fpbh;//风盘编号
	private String yhbh;//用户编号
	private String xh;//型号
	private String wz;//客厅 餐厅 主卧 次卧 书房
	private String bz;//备注
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFpbh() {
		return fpbh;
	}
	public void setFpbh(String fpbh) {
		this.fpbh = fpbh;
	}
	public String getYhbh() {
		return yhbh;
	}
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	
	public String getWz() {
		return wz;
	}
	public void setWz(String wz) {
		this.wz = wz;
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
		return "Fp [id=" + id + ", fpbh=" + fpbh + ", yhbh=" + yhbh + ", xh=" + xh + ", wz=" + wz + ", bz=" + bz + "]";
	}
	 
	
	 
	 
	
}
