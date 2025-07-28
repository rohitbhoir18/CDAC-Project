package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.InvoiceHeader;
import com.example.repository.InvoiceHeaderRepository;

@Service
public class InvoiceHeaderServiceImpl implements InvoiceHeaderService
{
	@Autowired
	private InvoiceHeaderRepository repository;

	@Override
	public List<InvoiceHeader> getAll() 
	{
		return repository.findAll();
	}

}
