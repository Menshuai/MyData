package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.DataDao;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.service.DataService;

@Service
public class DataServiceImpl implements DataService{

	@Autowired
	private DataDao dataDao;
	
	@Override
	public List<Data> find() {
		return dataDao.find();
	}

	@Override
	public Data findf(String fpbh) {
		return dataDao.findf(fpbh);
	}

	@Override
	public void updateS(String ms, String dw, double gdg, double zdS, double gdd, double dgdg, double dzdS, double dgdd,
			String jf, int sdw, int sw, String kg, int bj, String jj, String time) {
		dataDao.updateS(ms, dw, gdg, zdS, gdd, dgdg, dzdS, dgdd, jf, sdw, sw, kg, bj, jj, time);		
	}

	@Override
	public void updateYhbhF(Data data)
	{
		dataDao.updateYhbhF(data);
	}

 
	@Override
	public void InsertYh(Data data)
	{
		dataDao.InsertYh(data);
	}
 

	  
 
	/*@Override
	public void updateS(String fpbh) {
		dataDao.updateS(fpbh);
		
	}*/

	@Override
	public List<Data> searchInfo(String xqm, int ldh, int dyh, int hh, String time1, String time2)
	{
		// TODO Auto-generated method stub
		return dataDao.searchInfo(xqm, ldh, dyh, hh, time1, time2);
	}

	@Override
	public List<Data> searchHistory(String xqm, int ldh, int dyh, int hh, String time1, String time2)
	{
		// TODO Auto-generated method stub
		return dataDao.searchHistory(xqm, ldh, dyh, hh, time1, time2);
	}

	@Override
	public Data findData(String yhbh, String fpdz)
	{
		return dataDao.findData(yhbh, fpdz);
	}

	@Override
	public List<Data> Search(String bj) {
		return dataDao.Search(bj);
	}

}
