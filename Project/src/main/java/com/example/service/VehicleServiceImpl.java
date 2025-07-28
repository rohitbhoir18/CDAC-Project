package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.VehicleDetail;
import com.example.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService
{
	@Autowired
	private VehicleRepository repository;

	@Override
	public List<VehicleDetail> getAll() 
	{
		return repository.findAll();
	}

}
