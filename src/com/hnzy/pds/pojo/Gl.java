package com.hnzy.pds.pojo;

import java.io.Serializable;
/**
 * 功率表6
 * @author Administrator
 *
 */
public class Gl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private  String xh;//型号
	private Double xgkw;//夏季高档
	private Double xzkw;//夏季中档
	private Double xdkw;//夏季低档
	
	private Double dgkw;//冬季高档
	private Double dzkw;//冬季中档
	private Double ddkw;//冬季低档
	private String bz;//备注
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public Double getXgkw() {
		return xgkw;
	}
	public void setXgkw(Double xgkw) {
		this.xgkw = xgkw;
	}
	public Double getXzkw() {
		return xzkw;
	}
	public void setXzkw(Double xzkw) {
		this.xzkw = xzkw;
	}
	public Double getXdkw() {
		return xdkw;
	}
	public void setXdkw(Double xdkw) {
		this.xdkw = xdkw;
	}
	public Double getDgkw() {
		return dgkw;
	}
	public void setDgkw(Double dgkw) {
		this.dgkw = dgkw;
	}
	public Double getDzkw() {
		return dzkw;
	}
	public void setDzkw(Double dzkw) {
		this.dzkw = dzkw;
	}
	public Double getDdkw() {
		return ddkw;
	}
	public void setDdkw(Double ddkw) {
		this.ddkw = ddkw;
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
		return "GL [id=" + id + ", xh=" + xh + ", xgkw=" + xgkw + ", xzkw=" + xzkw + ", xdkw=" + xdkw + ", dgkw=" + dgkw
				+ ", dzkw=" + dzkw + ", ddkw=" + ddkw + ", bz=" + bz + "]";
	}
	
	
	
}
