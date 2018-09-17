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

	@Override
	public List<Data> searchInfo(String xqm, int ldh, int dyh, int hh, String time1, String time2)
	{
		return dataDao.searchInfo(xqm, ldh, dyh, hh, time1, time2);
	}

	@Override
	public List<Data> searchHistory(String xqm, int ldh, int dyh, int hh, String time1, String time2)
	{
		return dataDao.searchHistory(xqm, ldh, dyh, hh, time1, time2);
	}

	@Override
	public Data findData(String yhbh, Integer fpdz)
	{
		return dataDao.findData(yhbh, fpdz);
	}

	@Override
	public Data findYh(String yhbh, Integer fpdz)
	{
		return dataDao.findYh(yhbh, fpdz);
	}

	@Override
	public void updateJf(Data datajf)
	{
		dataDao.updateJf(datajf);
	}

	@Override
	public void InsertYhSSb(Data data)
	{
		dataDao.InsertYhSSb(data);
	}
	public List<Data> Search(String bj) {
		return dataDao.Search(bj);
	}

	@Override
	public void update(String bj,String yhbh,String fpdz)
	{
		dataDao.update(bj,yhbh, fpdz);
	}

	@Override
	public List<Data> SearchHistoryYc( String xqm, String ldh, String dyh, Integer hh,String bj)
	{
		return dataDao.SearchHistoryYc( xqm, ldh, dyh, hh,bj);
	}

	@Override
	public int TxycNum(String time)
	{
		return dataDao.TxycNum(time);
	}

	@Override
	public int DrycNum(String bj)
	{
		return dataDao.DrycNum(bj);
	}

	@Override
	public Double findZyydl(String yzbh)
	{
		return dataDao.findZyydl(yzbh);
	}
 }

