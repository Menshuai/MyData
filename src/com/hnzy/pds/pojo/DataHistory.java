package com.hnzy.pds.pojo;

import java.io.Serializable;

public class DataHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String yh_no;// 风盘地址
	private String mode; // 模式
	private String xh;	//
	private String dw;   //档位    00冷 01热  
	private String time_h; // 档位高档时间
	private String time_m;// 档位 中档时间
	private String time_l;// 档位 低档时间
	private String jf; // 计费状态
	private String temp_set; // 设定温度
	private String temp_room; // 室内温度
	private String onoff; // 远程状态 00：远程关     01：自动
	private String time; // 采集时间
	private String jzqip;  //ip地址
	private int jzqport;  //端口号
	private String jzqid;   //id
	
	private String js;   //计算数据  计算夏季冷量  已用当量值（时间*风盘高速） da.time_h*fp.C_h

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYh_no() {
		return yh_no;
	}

	public void setYh_no(String yh_no) {
		this.yh_no = yh_no;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getTime_h() {
		return time_h;
	}

	public void setTime_h(String time_h) {
		this.time_h = time_h;
	}

	public String getTime_m() {
		return time_m;
	}

	public void setTime_m(String time_m) {
		this.time_m = time_m;
	}

	public String getTime_l() {
		return time_l;
	}

	public void setTime_l(String time_l) {
		this.time_l = time_l;
	}

	public String getJf() {
		return jf;
	}

	public void setJf(String jf) {
		this.jf = jf;
	}

	public String getTemp_set() {
		return temp_set;
	}

	public void setTemp_set(String temp_set) {
		this.temp_set = temp_set;
	}

	public String getTemp_room() {
		return temp_room;
	}

	public void setTemp_room(String temp_room) {
		this.temp_room = temp_room;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getJzqip() {
		return jzqip;
	}

	public void setJzqip(String jzqip) {
		this.jzqip = jzqip;
	}

	public int getJzqport() {
		return jzqport;
	}

	public void setJzqport(int jzqport) {
		this.jzqport = jzqport;
	}

	public String getJzqid() {
		return jzqid;
	}

	public void setJzqid(String jzqid) {
		this.jzqid = jzqid;
	}

	public String getJs() {
		return js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
