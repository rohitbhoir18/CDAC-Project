package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.ComponentMaster;
import com.example.repository.ComponentRepository;

@Service
public class ComponentServiceImpl implements ComponentService
{
	@Autowired
	private ComponentRepository repository;

	@Override
	public List<ComponentMaster> getAll() 
	{
		return repository.findAll();
	}

}
