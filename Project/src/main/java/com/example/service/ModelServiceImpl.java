package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.ModelMaster;
import com.example.repository.ModelRepository;

@Service
public class ModelServiceImpl implements ModelService
{
	@Autowired
	private ModelRepository repository;

	@Override
	public List<ModelMaster> getByMfgId(int id) 
	{
		return repository.findModelsByMfgId(id);
	}
	
	@Override
	public List<ModelMaster> getBySegIdandMfgId(int segid,int mfgid) 
	{
		return repository.findModelsBySegIdandMfgId(segid, mfgid);
	}
	
//	@Override
//	public ModelMaster getMinQtyBySegId(int segid) 
//	{
//		return repository.findMinQtyBySegId(segid);
//	}
}
