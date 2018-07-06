package com.hnzy.pds.service;

import java.util.List;

import com.hnzy.pds.pojo.Rz;

 

public interface RzService {
	
	public List<Rz> rzList();
	public void insert(Rz rz);
	public List<Rz> Search(String cz); 
}
