package com.hnzy.pds.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Fm implements Serializable {
	
	private static final long serialVersionUID = -4072646355182930123L;
	
	private Integer id;
	
	private String valAd;//		���ŵ�ַ 
	
	private String qgID;//����id
	
	private String status;//����״̬
	
	private Integer famKd;// /���ſ���
	
	private String lockSt;// ������ʶ
	
	private String jFSt	;//	 	�ɷ�״̬
	
	private BigDecimal valTemp;
	
	private BigDecimal roomTemp;	 
	
	private Integer hTemSet;		  
	
	private Integer mTemSet;//		 
	
	private Integer lTemSet;//		 
	
	private Date recordTime;//	 
	private String type;
	//private Qg qg;
	private Yh yh;
	private Integer tqyb;
	
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public Integer getTqyb()
	{
		return tqyb;
	}
	public void setTqyb(Integer tqyb)
	{
		this.tqyb = tqyb;
	}

	/*public Qg getQg() {
		return qg;
	}

	

	public void setQg(Qg qg) {
		this.qg = qg;
	}*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValAd() {
		return valAd;
	}

	public void setValAd(String valAd) {
		this.valAd = valAd;
	}

	public String getQgID() {
		return qgID;
	}

	public void setQgID(String qgID) {
		this.qgID = qgID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getFamKd() {
		return famKd;
	}

	public void setFamKd(Integer famKd) {
		this.famKd = famKd;
	}

	public String getLockSt() {
		return lockSt;
	}

	public void setLockSt(String lockSt) {
		this.lockSt = lockSt;
	}

	public String getjFSt() {
		return jFSt;
	}

	public void setjFSt(String jFSt) {
		this.jFSt = jFSt;
	}

	public BigDecimal getValTemp() {
		return valTemp;
	}

	public void setValTemp(BigDecimal valTemp) {
		this.valTemp = valTemp;
	}

	public BigDecimal getRoomTemp() {
		return roomTemp;
	}

	public void setRoomTemp(BigDecimal roomTemp) {
		this.roomTemp = roomTemp;
	}

	public Integer gethTemSet() {
		return hTemSet;
	}

	public void sethTemSet(Integer hTemSet) {
		this.hTemSet = hTemSet;
	}

	public Integer getmTemSet() {
		return mTemSet;
	}

	public void setmTemSet(Integer mTemSet) {
		this.mTemSet = mTemSet;
	}

	public Integer getlTemSet() {
		return lTemSet;
	}

	public void setlTemSet(Integer lTemSet) {
		this.lTemSet = lTemSet;
	}

	

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Yh getYh() {
		return yh;
	}

	public void setYh(Yh yh) {
		this.yh = yh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
