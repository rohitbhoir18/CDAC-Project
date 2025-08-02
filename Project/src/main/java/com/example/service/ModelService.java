package com.example.service;

import java.util.List;

import com.example.entities.ModelMaster;

public interface ModelService 
{
	public List<ModelMaster> getByMfgId(int id);
	
	public List<ModelMaster> getBySegIdandMfgId(int segid,int mfgid);
	
//	public ModelMaster getMinQtyBySegId(int segid) ;


}
