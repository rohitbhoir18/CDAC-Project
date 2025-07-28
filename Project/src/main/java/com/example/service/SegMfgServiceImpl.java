package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.SegMfgMaster;
import com.example.repository.SegMfgRepository;

@Service
public class SegMfgServiceImpl implements SegMfgService
{
	@Autowired
	SegMfgRepository repository;

	@Override
	public List<SegMfgMaster> getAll() 
	{	
		return repository.findAll();
	}

	@Override
	public SegMfgMaster getById(int id) 
	{
		return repository.findById(id).orElse(null);
	}

}
