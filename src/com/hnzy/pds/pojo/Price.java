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
	
<<<<<<< HEAD
	public int getMsid() {
		return msid;
	}
	public void setMsid(int msid) {
		this.msid = msid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
=======
	public int getMsid()
	{
		return msid;
	}
	public void setMsid(int msid)
	{
		this.msid = msid;
	}
>>>>>>> b1958f309e0ea700dde7916e40dcc6e41934b4bc
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
