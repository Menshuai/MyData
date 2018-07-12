package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.util.Date;


public class Repair implements Serializable{

	private int id;
	private String xqm;
	private String ldh;
	private String dyh;
 	private String hh;
 	
	private String yhxm; //用户姓名
	private String lxdh; //联系电话
	private String wt;  //问题
	private String tjr; //提交人
	private Date tjsj; //提交时间
	
	private String jsr; //接收人
	private Date jssj;//接收时间
	private String zt; //状态
	private String lx; //类型
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getXqm() {
		return xqm;
	}
	public void setXqm(String xqm) {
		this.xqm = xqm;
	}
	public String getLdh() {
		return ldh;
	}
	public void setLdh(String ldh) {
		this.ldh = ldh;
	}
	public String getDyh() {
		return dyh;
	}
	public void setDyh(String dyh) {
		this.dyh = dyh;
	}
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	public String getYhxm() {
		return yhxm;
	}
	public void setYhxm(String yhxm) {
		this.yhxm = yhxm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getWt() {
		return wt;
	}
	public void setWt(String wt) {
		this.wt = wt;
	}
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	public Date getTjsj() {
		return tjsj;
	}
	public void setTjsj(Date tjsj) {
		this.tjsj = tjsj;
	}
	
	public String getJsr() {
		return jsr;
	}
	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	public Date getJssj() {
		return jssj;
	}
	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	@Override
	public String toString() {
		return "Repair [id=" + id + ", xqm=" + xqm + ", ldh=" + ldh + ", dyh=" + dyh + ", hh=" + hh + ", yhxm=" + yhxm
				+ ", lxdh=" + lxdh + ", wt=" + wt + ", tjr=" + tjr + ", tjsj=" + tjsj + ", jsr=" + jsr + ", jssj="
				+ jssj + ", zt=" + zt + ", lx=" + lx + "]";
	}
	 
	 
	
  

}
