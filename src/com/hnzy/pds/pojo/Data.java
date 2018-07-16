package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.util.Date;

public class Data implements Serializable {

	/**
	 * 事实数据表10
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String fpbh;// 风盘编号
	private String ms; // 模式
	
	private String dw; // 档位 00冷 01热
	private String gdtime; // 档位 高档时间
	private String zdtime;// 档位 中档时间
	private String ddtime;// 档位 低档时间
	
	private String dgdtime;//冬季高档时间
	private String dzdtime;//冬季中档时间
	private String dddtime;//冬季抵挡时间
	
	private String jf; // 计费状态
	private String sdwd; // 设定温度
	private String snwd; // 室内温度
	private String kg; // 开关 远程状态	 00：远程关 01：自动
	private String bj;//报警
	private String jj;//季节
	private String time; // 采集时间
	private String fpdz;
	private String yhbh;
	
	
	public String getFpdz() {
		return fpdz;
	}

	public void setFpdz(String fpdz) {
		this.fpdz = fpdz;
	}

	/*private String jzqip; // ip地址
	private int jzqport; // 端口号
	private String jzqid; // id
*/
	private String js; // 计算数据 计算夏季冷量 已用当量值（时间*风盘高速） da.time_h*fp.C_h 
	 private String dyydl;      //冬季已用当量
	private FpLogin fpLogin; // 风盘注册表
	private Fp fp;// 风盘参数表
	private YhMessage yhMessage;// 用户表
	private Gl gl;//功率表
	
	private  Jzq jzq;
	
	
	public Jzq getJzq() {
		return jzq;
	}

	public void setJzq(Jzq jzq) {
		this.jzq = jzq;
	}

	public String getFpbh() {
		return fpbh;
	}

	public void setFpbh(String fpbh) {
		this.fpbh = fpbh;
	}

	public Gl getGl() {
		return gl;
	}

	public void setGl(Gl gl) {
		this.gl = gl;
	}

	public FpLogin getFpLogin() {
		return fpLogin;
	}

	public void setFpLogin(FpLogin fpLogin) {
		this.fpLogin = fpLogin;
	}

	public String getYhbh()
	{
		return yhbh;
	}

	public void setYhbh(String yhbh)
	{
		this.yhbh = yhbh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfpbh() {
		return fpbh;
	}

	public void setfpbh(String fpbh) {
		this.fpbh = fpbh;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getGdtime() {
		return gdtime;
	}

	public void setGdtime(String gdtime) {
		this.gdtime = gdtime;
	}

	public String getZdtime() {
		return zdtime;
	}

	public void setZdtime(String zdtime) {
		this.zdtime = zdtime;
	}

	public String getDdtime() {
		return ddtime;
	}

	public void setDdtime(String ddtime) {
		this.ddtime = ddtime;
	}
	
	

	public String getDgdtime() {
		return dgdtime;
	}

	public void setDgdtime(String dgdtime) {
		this.dgdtime = dgdtime;
	}

	public String getDzdtime() {
		return dzdtime;
	}

	public void setDzdtime(String dzdtime) {
		this.dzdtime = dzdtime;
	}

	public String getDddtime() {
		return dddtime;
	}

	public void setDddtime(String dddtime) {
		this.dddtime = dddtime;
	}

	public String getJf() {
		return jf;
	}

	public void setJf(String jf) {
		this.jf = jf;
	}

	public String getSdwd() {
		return sdwd;
	}

	public void setSdwd(String sdwd) {
		this.sdwd = sdwd;
	}

	public String getSnwd() {
		return snwd;
	}

	public void setSnwd(String snwd) {
		this.snwd = snwd;
	}

	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	public String getBj() {
		return bj;
	}
	

	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time2)
	{
		this.time = time2;
	}

	public String getJs() {
		return js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	
	public String getDyydl() {
		return dyydl;
	}

	public void setDyydl(String dyydl) {
		this.dyydl = dyydl;
	}

	public Fp getFp() {
		return fp;
	}

	public void setFp(Fp fp) {
		this.fp = fp;
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

	
	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", fpbh=" + fpbh + ", ms=" + ms + ", dw=" + dw + ", gdtime=" + gdtime + ", zdtime="
				+ zdtime + ", ddtime=" + ddtime + ", dgdtime=" + dgdtime + ", dzdtime=" + dzdtime + ", dddtime="
				+ dddtime + ", jf=" + jf + ", sdwd=" + sdwd + ", snwd=" + snwd + ", kg=" + kg + ", bj=" + bj + ", jj="
				+ jj + ", time=" + time + ", fpdz=" + fpdz + ", js=" + js + ", dyydl=" + dyydl + ", fpLogin=" + fpLogin
				+ ", fp=" + fp + ", yhMessage=" + yhMessage + ", gl=" + gl + ", jzq=" + jzq + "]";
	}

	 

	 

	 

	 

	 

	 

	

}
