package com.example.service;

import java.util.List;

import com.example.entities.SegMfgMaster;

public interface SegMfgService 
{
	List<SegMfgMaster> getAll();
	
	SegMfgMaster getById(int id);

}
