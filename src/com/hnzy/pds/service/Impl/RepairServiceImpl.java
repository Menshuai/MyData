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

 

/*	@Override
	public List<Repair> findHESName(String place) {
		return repairDao.findHESName(place) ;
	}

	

	@Override
	public void InsertRepair(Repair repair) {
		repairDao.Insert(repair);
	}

	@Override
	public void deleteRepair(String id) {
		repairDao.delete(Integer.parseInt(id));
	}

	@Override
	public Repair findRepairById(int id) {
		return repairDao.findById(id);
	}

	@Override
	public void updateRepair(Repair repair) {
		repairDao.update(repair);
	}

	@Override
	public List<Repair> findByState(String place, String hesName, String state, int kffl) {
		return repairDao.findByState(place, hesName, state, kffl);
	}

	@Override
	public List<Repair> findState(int kffl) {
		return repairDao.findState(kffl);
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
	public List<Map<String, Object>> chartSearch(String xqName, int kffl) {
		List<Map<String,Object>> wxList=new ArrayList<Map<String,Object>>();
		   Map<String,Object> map=new HashMap<String,Object>();
		   map.put("δ�ӵ�", repairDao.statePlace0(xqName,kffl));
		   map.put("�ѽӵ�", repairDao.statePlace1(xqName,kffl));
		   map.put("�����", repairDao.statePlace2(xqName,kffl));
		   wxList.add(map);
			return wxList;
	}

	@Override
	public int stateCx(String place, String hesName, String state, int kffl) {
		// TODO Auto-generated method stub
		return repairDao.stateCx(place, hesName, state, kffl);
	}

	@Override
	public int stateywc(String place, String hesName, String state, int kffl) {
		return repairDao.stateywc(place, hesName, state, kffl);
	}

	@Override
	public int stateyjd(String place, String hesName, String state, int kffl) {

		return repairDao.stateyjd(place, hesName, state, kffl);
	}

	@Override
	public int statewjd(String place, String hesName, String state, int kffl) {

		return repairDao.statewjd(place, hesName, state, kffl);
	}

	@Override
	public Repair findHes(String xqName, String buildNo, String cellNo, String cs, String sh) {
		// TODO Auto-generated method stub
		return repairDao.findHes(xqName, buildNo, cellNo, cs, sh);
	}

	 

 	@Override
	public List<Repair> findRepair(int kffl) {
		return repairDao.findRepair(kffl);
	} 


	@Override
	public List<Repair> findplace() {
		// TODO Auto-generated method stub
		return  repairDao.find();
	}



	@Override
	public List<Repair> findXqName() {
		// TODO Auto-generated method stub
		return repairDao.findXqName();
	}



	@Override
	public List<Repair> findBuildNo(String xqName) {
		// TODO Auto-generated method stub
		return repairDao.findBuildNo(xqName);
	}



	@Override
	public List<Repair> findCellNo(String xqName, String BuildNo) {
		// TODO Auto-generated method stub
		return repairDao.findCellNo(xqName, BuildNo);
	}



	 

	@Override
	public List<Repair> Search(String xqName, String buildNo, String cellNo, String SH,String fl,String telephone) {
		// TODO Auto-generated method stub
		return repairDao.Search(xqName, buildNo, cellNo, SH,fl,telephone);
	}



	@Override
	public int sum1(String xqName, String buildNo, String cellNo, String sh,String fl,String telephone) {
		// TODO Auto-generated method stub
		return repairDao.sum1(xqName, buildNo, cellNo, sh,fl,telephone);
	}



	@Override
	public int state00(String xqName, String buildNo, String cellNo, String sh,String fl,String telephone) {
		// TODO Auto-generated method stub
		return repairDao.state00(xqName, buildNo, cellNo, sh,fl,telephone);
	}



	@Override
	public int state11(String xqName, String buildNo, String cellNo, String sh,String fl,String telephone) {
		// TODO Auto-generated method stub
		return repairDao.state11(xqName, buildNo, cellNo, sh,fl,telephone);
	}



	@Override
	public int state22(String xqName, String buildNo, String cellNo, String sh,String fl,String telephone) {
		// TODO Auto-generated method stub
		return repairDao.state22(xqName, buildNo, cellNo, sh,fl,telephone);
	}

*/

	 

 
	 
 
	 

	
}
