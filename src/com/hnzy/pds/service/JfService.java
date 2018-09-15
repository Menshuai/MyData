package com.hnzy.pds.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Jf;
import com.hnzy.pds.pojo.YhMessage;

public interface JfService {
	
	//根据 缴费时间查询缴费笔数
		public int findJfbs( );
		
		//根据 缴费时间查询缴费总金额
		public int findJfzje( );
		
		//根据 缴费时间查询缴费笔数
		public int findBzJfbs( );
		
		//根据 缴费时间查询缴费总金额
		public int findBzJfzje( );

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
	public Jf findzje(String yzbh);
//	//根据用户编号更新缴费信息
	public void updateJf(Jf jf);
	//更新缴费金额和缴费合计金额
	public void updateJfHjje(@Param("yzbh")String yzbh,@Param("jfje")Integer jfje,@Param("hjje")Double hjje,@Param("time") Date time,@Param("userName")String userName,@Param("jf")Integer jf,@Param("yf")String yf,@Param("startTime")String startTime,@Param("endTime")String endTime);
	//缴费信息插入历史表
	public void InsertJfHistory(@Param("yzbh")String yzbh,  @Param("jfje")Integer jfje, @Param("hjje")Double hjje,@Param("time")Date time,@Param("userName")String userName,@Param("type")Integer type,@Param("startTime")String startTime,@Param("endTime")String endTime);
	//搜索缴费信息
	public List<Jf>Sear(@Param("xqm") String xqm,@Param("ldh") String ldh,@Param("dyh") String dyh,@Param("hh") String hh,@Param("type") Integer type);
	//添加缴费信息
	public void InsertJf(Jf jf);
	//缴费历史数据查询
	public List<Jf>JffindHistory();
	
	//搜索缴费历史数据查询
	public List<Jf>JffindHistorySear(@Param("xqm") String xqm,@Param("ldh") String ldh,@Param("dyh") String dyh,@Param("hh") String hh,@Param("time1") String time1,@Param("time2") String time2,@Param("type") Integer type);
    //导出------
    public void exportExcel(List<Jf>jfList,ServletOutputStream outputStream) throws IOException;
    //查找合计金额
//  	public Jf findHjje(String yhbh,String yzbh);
  //查找月份
  	public Jf  findYf(String yhbhS);

	/**
	 *  根据用户编号查询用户所在小区-楼栋-单元-门牌号
	 * @param yhbh
	 * @return ymparm
	 * @author ms
	 */
	public List<Jf> findjfByYhbh(Jf jfparm);
}
