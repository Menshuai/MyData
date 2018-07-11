package com.hnzy.pds.service.Impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.hnzy.pds.dao.YhMessageDao;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.YhMessageService;

@Service("yhMessageService")
public class YhMessageServiceImpl implements YhMessageService{
	
	@Autowired
	private YhMessageDao yhMessageDao;
	
	@Override
	public List<YhMessage> find() {
		return yhMessageDao.find();
	}

 	@Override
	public List<YhMessage> findLDH(String xqm) {
		return yhMessageDao.findLDH(xqm);
	}

	@Override
	public List<YhMessage> findDYH(String xqm, String ldh) {
		return yhMessageDao.findDYH(xqm, ldh);
	} 

	@Override
	public List<YhMessage> findZ(String xqm, String ldh, String dyh) {
		return yhMessageDao.findZ(xqm, ldh, dyh);
	}

   	@Override
	public List<YhMessage> findXQ() {
		return yhMessageDao.findXQ();
	} 

 	@Override
	public List<YhMessage> findXQN() {
		return yhMessageDao.findXQN();
	} 

	@Override
	public List<YhMessage> findBOByXQ(YhMessage yhMessage) {
		return yhMessageDao.findBO(yhMessage);
	}

	@Override
	public List<YhMessage> findCOByXQAndBO(YhMessage yhMessage) {
		return yhMessageDao.findCO(yhMessage);
	}

	@Override
	public void Insert(YhMessage yhMesasge) {
		yhMessageDao.Insert(yhMesasge);		
	}

	@Override
	public void delete(String id) {
		yhMessageDao.delete(Integer.parseInt(id));		
	}

	@Override
	public void update(YhMessage yhMesasge) {
		yhMessageDao.update(yhMesasge);
	}

	@Override
	public YhMessage findJzq(String cgbh) {
		return yhMessageDao.findJzq(cgbh);
	}

	@Override
	public YhMessage finldh(String yhbh,String fpdz) {
		return yhMessageDao.finldh(yhbh,fpdz);
	}

	@Override
	public YhMessage findyh(String yhbh) {
		return yhMessageDao.findyh(yhbh);
	}

	@Override
	public List<YhMessage> findYhNameList() {
		// TODO Auto-generated method stub
		return yhMessageDao.findYhNameList();
	}

	@Override
	public List<YhMessage> findYhBuildNObyXqm(String xqm) {
		// TODO Auto-generated method stub
		return yhMessageDao.findYhBuildNObyXqm(xqm);
	}

	@Override
	public List<YhMessage> findYhCellNOByBuild(int ldh, String xqm) {
		// TODO Auto-generated method stub
		return yhMessageDao.findYhCellNOByBuild(ldh, xqm);
	}

	@Override
	public void exportExcel(List<YhMessage> yhInfosList, ServletOutputStream outputStream) throws IOException {
		yhMessageDao.exportExcel(yhInfosList, outputStream);		
	}

	 

	@Override
	public List<YhMessage> findXqName() {
		return yhMessageDao.findXqName();
	}

	@Override
	public List<YhMessage> searchInfo(String xqm, int ldh, int dyh, int hh, String time1, String time2) {
		return yhMessageDao.searchInfo(xqm, ldh, dyh, hh, time1, time2);
	}

	@Override
	public List<YhMessage> searchHistory(String xqm, int ldh, int dyh, int hh, String time1, String time2) {
		return yhMessageDao.searchHistory(xqm, ldh, dyh, hh, time1, time2);
	}
 

	 

	
	
	
}
