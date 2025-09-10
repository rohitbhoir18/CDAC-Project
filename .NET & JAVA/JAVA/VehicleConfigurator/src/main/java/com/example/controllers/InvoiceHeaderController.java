package com.example.controllers;

import com.example.entities.InvoiceHeader;
import com.example.entities.InvoiceWrapper;
import com.example.services.InvoiceHeaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoices")
public class InvoiceHeaderController {

    @Autowired
    private InvoiceHeaderService invoiceHeaderService;

    @GetMapping
    public List<InvoiceHeader> getAllInvoices() {
        return invoiceHeaderService.getAllInvoices();
    }

    @GetMapping("/{invId}")
    public ResponseEntity<InvoiceHeader> getInvoiceById(@PathVariable int invId) {
        return invoiceHeaderService.getInvoiceById(invId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deprecated basic save
    @PostMapping
    public InvoiceHeader createInvoice(@RequestBody InvoiceHeader invoiceHeader) {
        return invoiceHeaderService.createInvoice(invoiceHeader);
    }

    // Main method used by React app
    @PostMapping("/create")
    public ResponseEntity<InvoiceHeader> createInvoice(@RequestBody InvoiceWrapper wrapper) {
        InvoiceHeader savedHeader = invoiceHeaderService.createInvoice(wrapper);
        return new ResponseEntity<>(savedHeader, HttpStatus.CREATED);
    }
}
