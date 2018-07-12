package com.hnzy.pds.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.SbSucDao;
import com.hnzy.pds.pojo.SbSuc;
import com.hnzy.pds.service.SbSucService;

@Service
public class SbSucServiceImpl implements SbSucService
{
	@Autowired
	private SbSucDao sbSucDao;

	@Override
	public void update(SbSuc sbSuc)
	{
		sbSucDao.update(sbSuc);
	}

}
