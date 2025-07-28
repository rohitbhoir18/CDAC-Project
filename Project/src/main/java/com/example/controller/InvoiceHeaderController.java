package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.InvoiceHeader;
import com.example.service.InvoiceHeaderService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceHeaderController {
    @Autowired
    private InvoiceHeaderService service;

    @GetMapping
    public List<InvoiceHeader> getAllInvoices() {
        return service.getAll();
    }
}
