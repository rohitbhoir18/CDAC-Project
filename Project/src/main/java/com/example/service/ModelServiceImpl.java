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
	public List<ModelMaster> getAll()
	{
		return repository.findAll();
	}

	@Override
	public ModelMaster getById(int id) 
	{
		return repository.findById(id).orElse(null);
	}
	

}
