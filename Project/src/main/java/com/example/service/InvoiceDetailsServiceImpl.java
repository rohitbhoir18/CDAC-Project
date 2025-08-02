package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.InvoiceDetail;
import com.example.repository.InvoiceDetailsRepository;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService
{
	@Autowired
	private InvoiceDetailsRepository repository;

	@Override
	public List<InvoiceDetail> getAll()
	{
		return repository.findAll();
	}

}
