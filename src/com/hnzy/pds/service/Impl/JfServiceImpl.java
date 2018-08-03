package com.hnzy.pds.service.Impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.JfDao;
import com.hnzy.pds.pojo.Jf;
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
	public Jf findzje(String yhbh)
	{
		return jfDao.findzje(yhbh);
	}

	@Override
	public void updateJf(Jf jf)
	{
		jfDao.updateJf(jf);
	}

	@Override
	public void updateJfHjje(String yhbh, Double jfje, Double hjje, Date time,String userName)
	{
		jfDao.updateJfHjje(yhbh, jfje, hjje, time,userName);
	}

	@Override
	public void InsertJfHistory(String yhbh, Double yyje, Double syje, Double jfje, Double hjje, Date time,
			String userName)
	{
		jfDao.InsertJfHistory(yhbh, yyje, syje, jfje, hjje, time, userName);
	}

	@Override
	public List<Jf> Sear(String xqm, String ldh, String dyh, String hh)
	{
		return jfDao.Sear(xqm, ldh, dyh, hh);
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
	public List<Jf> JffindHistorySear(String xqm, String ldh, String dyh, String hh, String time1, String time2)
	{
		return jfDao.JffindHistorySear(xqm, ldh, dyh, hh, time1, time2);
	}

	@Override
	public void exportExcel(List<Jf> jfList, ServletOutputStream outputStream) throws IOException
	{
		ExcelUtilJf.exportExcel(jfList, outputStream);
	}
}
