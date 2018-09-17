package com.hnzy.pds.pojo;

import java.io.Serializable;

public class Price implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Double price;
	private int msid;
	
	public int getMsid() {
		return msid;
	}
	public void setMsid(int msid) {
		this.msid = msid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Double getPrice()
	{
		return price;
	}
	public void setPrice(Double price)
	{
		this.price = price;
	}
}
