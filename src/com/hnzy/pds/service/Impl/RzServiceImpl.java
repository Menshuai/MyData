package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.RzDao;
import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.service.RzService;
 
 @Service
 public class RzServiceImpl implements RzService {

 	@Autowired
	private RzDao rzdao;
 	
	@Override
	public List<Rz> rzList() {
		return rzdao.find();
	}

	@Override
	public List<Rz> Search(String cz)
	{
		return rzdao.Search(cz);
	}

	@Override
	public void insert(Rz rz) {
		rzdao.Insert(rz);		
	}
 }
