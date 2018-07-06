package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.DjbDao;
import com.hnzy.pds.pojo.Djb;
import com.hnzy.pds.service.DjbService;

@Service
public class DjbServiceImpl  implements DjbService{

	@Autowired
	private DjbDao djbDao;

	@Override
	public List<Djb> find() {
		return djbDao.find();
	}

	@Override
	public void Insert(Djb djb) {
		djbDao.Insert(djb);
		
	}

	@Override
	public void delete(String id) {
		djbDao.delete(Integer.parseInt(id));
		
	}

	@Override
	public void update(Djb djb) {
		djbDao.update(djb);
		
	}
	
	 
	
}
