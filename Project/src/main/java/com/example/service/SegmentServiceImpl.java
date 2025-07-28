package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.SegmentMaster;
import com.example.repository.SegmentRepository;

@Service
public class SegmentServiceImpl implements SegmentService
{
	@Autowired
	private SegmentRepository repository;
	
	@Override
	public List<SegmentMaster> getAllSeg() 
	{
		return repository.findAll();
	}
	
}
