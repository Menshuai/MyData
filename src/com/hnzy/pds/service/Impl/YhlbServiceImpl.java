package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.YhlbDao;
import com.hnzy.pds.pojo.Yhlb;
import com.hnzy.pds.service.YhlbService;

@Service
public class YhlbServiceImpl implements  YhlbService{

	 
	@Autowired
	private YhlbDao yhlbDao;

	
	@Override
	public List<Yhlb> find() {
		return yhlbDao.find();
	}

	@Override
	public void Insert(Yhlb yhlb) {
		yhlbDao.Insert(yhlb);		
	}

	@Override
	public void delete(String id) {
		yhlbDao.delete(Integer.parseInt(id));		
	}

	@Override
	public void update(Yhlb yhlb) {
		yhlbDao.update(yhlb);		
	}

}
