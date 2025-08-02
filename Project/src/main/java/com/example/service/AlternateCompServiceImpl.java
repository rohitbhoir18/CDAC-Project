package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.AlternateComponentMaster;
import com.example.repository.AlternateCompRepository;

@Service
public class AlternateCompServiceImpl implements AlternateCompService
{
	@Autowired
	private AlternateCompRepository repository;

	@Override
	public List<AlternateComponentMaster> getAll()
	{
		return repository.findAll();
	}

}
