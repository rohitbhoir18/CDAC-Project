package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.InvoiceDetail;
import com.example.service.InvoiceDetailsService;

@RestController
@RequestMapping("/api/invoice-details")
public class InvoiceDetailController {
    @Autowired
    private InvoiceDetailsService service;

    @GetMapping
    public List<InvoiceDetail> getAllInvoiceDetails() {
        return service.getAll();
    }
}