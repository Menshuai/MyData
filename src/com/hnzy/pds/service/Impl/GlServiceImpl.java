package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.GlDao;
import com.hnzy.pds.pojo.Gl;
import com.hnzy.pds.service.GlService;

@Service
public class GlServiceImpl implements  GlService{

	@Autowired
	private GlDao glDao;
	
	
	@Override
	public List<Gl> find() {
		return glDao.find();
	}

	@Override
	public void Insert(Gl gl) {
		glDao.Insert(gl);		
	}

	@Override
	public void delete(String id) {
		glDao.delete(Integer.parseInt(id));		
	}

	@Override
	public void update(Gl gl) {
		glDao.update(gl);		
	}

}
