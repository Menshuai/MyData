package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 缴费表12
 * @author Administrator
 *
 */
public class Jf  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String yhbh;//用户编号
	private double jfje;//缴费金额
	private double hjje;//合计金额
	private String time;//缴费时间
	private double syje;//剩余金额
	private double yyje;//已用金额
	private String getime;//金额更新时间
	private String userName;//缴费人
	

	public String getGetime()
	{
		return getime;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setGetime(String getime)
	{
		this.getime = getime;
	}

	private YhMessage yhMessage;

	
	public double getSyje()
	{
		return syje;
	}

	public double getYyje()
	{
		return yyje;
	}

	public void setYyje(double yyje)
	{
		this.yyje = yyje;
	}

	public void setSyje(double syje)
	{
		this.syje = syje;
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


	public double getJfje()
	{
		return jfje;
	}

	public void setJfje(double jfje)
	{
		this.jfje = jfje;
	}

	public double getHjje()
	{
		return hjje;
	}

	public void setHjje(double hjje)
	{
		this.hjje = hjje;
	}


	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
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
		return "Jf [id=" + id + ", yhbh=" + yhbh + ", jfje=" + jfje + ", hjje=" + hjje + ", syje=" + syje + ", time="
				+ time + ", yhMessage=" + yhMessage + "]";
	}	
}
