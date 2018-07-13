package com.hnzy.pds.service.Impl;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.RepairDao;
import com.hnzy.pds.pojo.Repair;
import com.hnzy.pds.service.RepairService;

@Service
public class RepairServiceImpl implements RepairService{
	
	@Autowired
	private RepairDao repairDao;

	@Override
	public List<Repair> findXqName() {
		return repairDao.findXqName();
	}

	@Override
	public List<Repair> findYhBuildNObyXqm(String xqm) {
		return repairDao.findYhBuildNObyXqm(xqm);
	}

	@Override
	public List<Repair> findYhCellNOByBuild(String ldh, String xqm) {
		return repairDao.findYhCellNOByBuild(ldh, xqm);
	}

	@Override
	public List<Repair> findRepair(int kffl) {
		return repairDao.findRepair(kffl);
	}

	@Override
	public List<Repair> Search(String xqm, String ldh, String dyh, String hh, String fl, String lxdh) {
		return repairDao.Search(xqm, ldh, dyh, hh, fl, lxdh);
	}

	@Override
	public int sum(Repair repair, int kffl) {
		return repairDao.sum(repair, kffl);
	}

	@Override
	public int state0(int kffl) {
		return repairDao.state0(kffl);
	}

	@Override
	public int state1(int kffl) {
		return repairDao.state1(kffl);
	}

	@Override
	public int state2(int kffl) {
		return repairDao.state2(kffl);
	}

	@Override
	public int sum1(String xqm, String ldh, String dyh, String hh, String fl, String lxdh) {
		return repairDao.sum1(xqm, ldh, dyh, hh, fl, lxdh);
	}

	@Override
	public int state00(String xqm, String ldh, String dyh, String hh, String fl, String lxdh) {
		return repairDao.state00(xqm, ldh, dyh, hh, fl, lxdh);
	}

	@Override
	public int state11(String xqm, String ldh, String dyh, String hh, String fl, String lxdh) {
		return repairDao.state11(xqm, ldh, dyh, hh, fl, lxdh);
	}

	@Override
	public int state22(String xqm, String ldh, String dyh, String hh, String fl, String lxdh) {
		return repairDao.state22(xqm, ldh, dyh, hh, fl, lxdh);
	}

	@Override
	public void updateRepair(Repair repair) {
		repairDao.updateRepair(repair);		
	}

	@Override
	public List<Map<String, Object>> chartSearch(String xqm, int kffl) {
		List<Map<String,Object>> wxList=new ArrayList<Map<String,Object>>();
		   Map<String,Object> map=new HashMap<String,Object>();
		   map.put("未接单", repairDao.statePlace0(xqm,kffl));
		   map.put("已接单", repairDao.statePlace1(xqm,kffl));
		   map.put("已完成", repairDao.statePlace2(xqm,kffl));
		   wxList.add(map);
		return wxList;
	}

 

 
	 

 
	 
 
	 

	
}
