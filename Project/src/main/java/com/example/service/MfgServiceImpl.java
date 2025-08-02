package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.MfgMaster;
import com.example.repository.MfgRepository;

@Service
public class MfgServiceImpl implements MfgService
{
	@Autowired
	private MfgRepository repository;
	@Override
	public List<MfgMaster> getAll() 
	{		
		return repository.findAll();
	}
	
	@Override
    public List<MfgMaster> getBySegmentId(int segId) {
        return repository.findMfgBySegmentId(segId); 
    }

}
