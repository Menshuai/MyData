package com.hnzy.pds.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.JfDao;
import com.hnzy.pds.pojo.Jf;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.JfService;
import com.hnzy.pds.util.ExcelUtilJf;
 

@Service
public class JfServiceImpl  implements JfService{

	@Autowired
	private JfDao jfDao;
	
	@Override
	public List<Jf> find() {
		// TODO Auto-generated method stub
		return jfDao.find();
	}

	@Override
	public void Insert(Jf jf) {
		jfDao.Insert(jf);
	}

	@Override
	public void delete(int id) {
		jfDao.delete(id);
	}

//	@Override
//	public void update(Jf jf) {
//		jfDao.update(jf);
//	}

	@Override
	public Jf findzje(String yzbh)
	{
		return jfDao.findzje(yzbh);
	}

	@Override
	public void updateJf(Jf jf)
	{
		jfDao.updateJf(jf);
	}

	@Override
	public void updateJfHjje(String yzbh, Integer jfje, Double hjje, Date time,String userName,Integer type,String typeBz,String startTime,String endTime)
	{
		jfDao.updateJfHjje(yzbh, jfje, hjje, time,userName,type,typeBz,startTime,endTime);
	}

	@Override
	public void InsertJfHistory(String yzbh, Integer jfje, Double hjje, Date time,
			String userName,Integer type,String startTime,String endTime)
	{
		jfDao.InsertJfHistory(yzbh, jfje, hjje, time, userName,type,startTime,endTime);
	}

	@Override
	public List<Jf> Sear(String xqm, String ldh, String dyh, String hh,Integer type)
	{
		return jfDao.Sear(xqm, ldh, dyh, hh,type);
	}

	@Override
	public void InsertJf(Jf jf)
	{
		jfDao.Insert(jf);
	}

	@Override
	public List<Jf> JffindHistory()
	{
		return jfDao.JffindHistory();
	}

	@Override
	public List<Jf> JffindHistorySear(String xqm, String ldh, String dyh, String hh, String time1, String time2,Integer type)
	{
		return jfDao.JffindHistorySear(xqm, ldh, dyh, hh, time1, time2,type);
	}

	@Override
	public void exportExcel(List<Jf> jfList, ServletOutputStream outputStream) throws IOException
	{
		ExcelUtilJf.exportExcel(jfList, outputStream);
	}

	@Override
	public Jf findYf(String yhbhS)
	{
		// TODO Auto-generated method stub
		return jfDao.findYfS(yhbhS);
	}
	/**
	 * 根据yhbh查询缴费表用户信息。
	 * @param yhbh
	 * @return ymparm
	 * @author ms
	 */
	@Override
	public List<Jf> findjfByYhbh(Jf jfparm) {
		//链接数据库要tar catch，捕获链接数据库连不上或者查询出错的异常。
		List<Jf> jfList=new ArrayList<>();
				try {
					jfList=jfDao.findjfByYhbh(jfparm);
				} catch (Exception e) {
					//如果有日志打印日志，
					System.out.println("根据用户编号查询用户所在小区-楼栋-单元-门牌号出错啦！");
				}
				 return jfList;
	}

	@Override
	public int findJfbs( ) {
		return jfDao.findJfbs();
	}

	@Override
	public int findJfzje( ) {
		return jfDao.findJfzje();
	}

	@Override
	public int findBzJfbs() {
		return jfDao.findBzJfbs();
	}

	@Override
	public int findBzJfzje() {
		return jfDao.findBzJfzje();
	}

	 

//	@Override
//	public Jf findHjje(String yhbh, String yzbh)
//	{
//		return jfDao.findHjje(yhbh, yzbh);
//	}
}
