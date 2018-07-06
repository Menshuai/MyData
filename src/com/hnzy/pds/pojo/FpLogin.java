package com.hnzy.pds.pojo;

import java.io.Serializable;

//风盘注册数据
public class FpLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String fp_no;// 风盘编号
	private String FP_shift;// 风盘型号
	private String yh_bh; // 所属用户编号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 

	public String getFp_no() {
		return fp_no;
	}

	public void setFp_no(String fp_no) {
		this.fp_no = fp_no;
	}

	public String getFP_shift() {
		return FP_shift;
	}

	public void setFP_shift(String fP_shift) {
		FP_shift = fP_shift;
	}

	public String getYh_bh() {
		return yh_bh;
	}

	public void setYh_bh(String yh_bh) {
		this.yh_bh = yh_bh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FpLogin [id=" + id + ", fp_bh=" + fp_no + ", FP_shift=" + FP_shift + ", yh_bh=" + yh_bh + "]";
	}

	 

}
