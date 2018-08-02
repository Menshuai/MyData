package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.CgDao;
import com.hnzy.pds.pojo.Cg;
import com.hnzy.pds.service.CgService;

@Service
public class CgServiceImpl implements CgService{
	
	@Autowired
	private CgDao cgDao;

	@Override
	public List<Cg> find() {
		return cgDao.find();
	}

	@Override
	public void Insert(Cg cg) {
		cgDao.Insert(cg);
		
	}

	@Override
	public void delete(String id) {
		cgDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(Cg cg) {
		cgDao.update(cg);
		
	}

	@Override
	public Cg findLdDyByCg(String cgbh)
	{
		return cgDao.findLdDyByCg(cgbh);
	}

	@Override
	public Cg findJzqByCg(String cgbh)
	{
		return cgDao.findJzqByCg(cgbh);
	}

}
