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

<<<<<<< HEAD
=======
	@Override
	public void InsertYh(Data data)
	{
		dataDao.InsertYh(data);
	}

>>>>>>> 990cc4c6d7a497bab62f519edc682a272acf0df6
	 

	 

	  

	/*@Override
	public void updateS(String fpbh) {
		dataDao.updateS(fpbh);
		
	}*/

	 

	 

}
