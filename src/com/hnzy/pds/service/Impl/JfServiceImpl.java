package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.JfDao;
import com.hnzy.pds.pojo.Jf;
import com.hnzy.pds.service.JfService;

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
	public void delete(String id) {
		jfDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(Jf jf) {
		jfDao.update(jf);
	}

}
