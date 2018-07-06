package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.JzqDao;
import com.hnzy.pds.pojo.Jzq;
import com.hnzy.pds.service.JzqService;

@Service
public class JzqServiceImpl implements JzqService{
	
	@Autowired
	private JzqDao jzqDao;

	@Override
	public List<Jzq> find() {
		return jzqDao.find();
	}

	@Override
	public void Insert(Jzq jzq) {
		jzqDao.Insert(jzq);
	}

	@Override
	public void delete(String id) {
		jzqDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(Jzq jzq) {
		jzqDao.update(jzq);
	}

	 

	@Override
	public void updateIpPort(String jzqip, Integer jzqport, String jzqnet) {
		jzqDao.updateIpPort(jzqip, jzqport, jzqnet);
		
	}

	@Override
	public Jzq findJzqnet(String jzqip, Integer jzqport) {
		return jzqDao.findJzqnet(jzqip, jzqport);
	}

}
