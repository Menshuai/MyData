package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.FpDao;
import com.hnzy.pds.pojo.Fp;
import com.hnzy.pds.service.FpService;

@Service
public class FpServiceImpl implements  FpService{

	@Autowired
	private FpDao  fpDao;

	
	@Override
	public List<Fp> find() {
		return fpDao.find();
	}

	@Override
	public void Insert(Fp fp) {
		fpDao.Insert(fp);
		
	}

	@Override
	public void delete(String id) {
		fpDao.delete(Integer.parseInt(id));
	}

	@Override
	public void update(Fp fp) {
		fpDao.update(fp);
	}

	@Override
	public Fp findfpbh(String yhbh) {
		// TODO Auto-generated method stub
		return fpDao.findfpbh(yhbh);
	}

}
