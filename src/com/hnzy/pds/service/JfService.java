package com.hnzy.pds.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Jf;

public interface JfService {

	/**
	 * 查找信息
	 * @return
	 */
	public List<Jf> find();
	
	/**
	 * 插入信息
	 * @param Jf
	 */
	public  void Insert(Jf jf);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);

	//根据用户编码查找用户总金额
	public Jf findzje(String yhbh);
	//根据用户编号更新缴费信息
	public void updateJf(Jf jf);
	//更新缴费金额和缴费合计金额
	public void updateJfHjje(@Param("yhbh")String yhbh,@Param("jfje")Double jfje,@Param("hjje")Double hjje,@Param("time") Date time,@Param("userName")String userName);
	//缴费信息插入历史表
	public void InsertJfHistory(@Param("yhbh")String yhbh, @Param("yyje")Double yyje, @Param("syje")Double syje, @Param("jfje")Double jfje, @Param("hjje")Double hjje,@Param("time")Date time,@Param("userName")String userName);
	//搜索缴费信息
	public List<Jf>Sear(@Param("xqm") String xqm,@Param("ldh") String ldh,@Param("dyh") String dyh,@Param("hh") String hh);
	//添加缴费信息
	public void InsertJf(Jf jf);
	//缴费历史数据查询
	public List<Jf>JffindHistory();
	//搜索缴费历史数据查询
	public List<Jf>JffindHistorySear(@Param("xqm") String xqm,@Param("ldh") String ldh,@Param("dyh") String dyh,@Param("hh") String hh,@Param("time1") String time1,@Param("time2") String time2);
    //导出------
    public void exportExcel(List<Jf>jfList,ServletOutputStream outputStream) throws IOException;
}
