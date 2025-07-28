package com.example.service;

import java.util.List;

import com.example.entities.ModelMaster;

public interface ModelService 
{
	 public List<ModelMaster> getAll();
	 
	 public ModelMaster getById(int id);
}
