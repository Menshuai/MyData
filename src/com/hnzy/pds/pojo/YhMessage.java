package com.hnzy.pds.pojo;

import java.io.Serializable;

public class YhMessage implements Serializable{

	/**
	 * 用户表1
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String  yhbh  ;  //用户编号
	private String  cgbh  ;   //层管编号
	private String  xqm  ;   //小区
	private String ldh  ;  //楼栋
	
	private String dyh  ;  //单元
	private Integer hh  ;  //户号
	private Integer fpdz ;  //风盘个数
	private Double  mj   ;  //面积
	
	private String  yhlx ;  //用户类型
	
	private String  yhxm  ;  //用户姓名
	private String  bz    ;     //备注
	
	private String  lxdh  ;  //联系电话
	private Cg cg;
	private int yf;
	
	
	public int getYf()
	{
		return yf;
	}
	public void setYf(int yf)
	{
		this.yf = yf;
	}
	public Integer getFpdz()
	{
		return fpdz;
	}
	public void setFpdz(Integer fpdz)
	{
		this.fpdz = fpdz;
	}
	
	public Cg getCg() {
		return cg;
	}
	public void setCg(Cg cg) {
		this.cg = cg;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getYhxm() {
		return yhxm;
	}
	public void setYhxm(String yhxm) {
		this.yhxm = yhxm;
	}
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
	public String getCgbh() {
		return cgbh;
	}
	public void setCgbh(String cgbh) {
		this.cgbh = cgbh;
	}
	public String getXqm() {
		return xqm;
	}
	public void setXqm(String xqm) {
		this.xqm = xqm;
	}

	public String getLdh()
	{
		return ldh;
	}
	public void setLdh(String ldh)
	{
		this.ldh = ldh;
	}
	public String getDyh()
	{
		return dyh;
	}
	public void setDyh(String dyh)
	{
		this.dyh = dyh;
	}
	public Integer getHh() {
		return hh;
	}
	public void setHh(Integer hh) {
		this.hh = hh;
	}
	public Double getMj() {
		return mj;
	}
	public void setMj(Double mj) {
		this.mj = mj;
	}
	public Integer getfpdz() {
		return fpdz;
	}
	public void setfpdz(Integer fpdz) {
		this.fpdz = fpdz;
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
	public String getYhlx() {
		return yhlx;
	}
	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}
	@Override
	public String toString() {
		return "YhMessage [id=" + id + ", yhbh=" + yhbh + ", cgbh=" + cgbh + ", xqm=" + xqm + ", ldh=" + ldh + ", dyh="
				+ dyh + ", hh=" + hh + ", fpdz=" + fpdz + ", mj=" + mj + ", yhlx=" + yhlx + ", yhxm=" + yhxm + ", bz="
				+ bz + ", lxdh=" + lxdh + ", cg=" + cg + "]";
	}
	 
	
	
	
	
	
}
