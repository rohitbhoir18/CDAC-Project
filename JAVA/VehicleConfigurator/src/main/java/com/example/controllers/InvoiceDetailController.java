package com.example.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entities.Component;
import com.example.entities.InvoiceDetail;
import com.example.entities.InvoiceWrapper;
import com.example.entities.AlternateComponent;
import com.example.repositories.AlternateComponentRepository;
import com.example.repositories.ComponentRepository;
import com.example.services.AlternateComponentService;
import com.example.services.InvoiceDetailService;
import com.example.entities.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/invoice-details")
public class InvoiceDetailController {

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private AlternateComponentService alternateComponentService;

    @Autowired
    private AlternateComponentRepository alternateComponentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<InvoiceDetail> getAllInvoiceDetails() {
        return invoiceDetailService.getAllInvoiceDetails();
    }

    @PostMapping
    public InvoiceDetail addInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail) {
        return invoiceDetailService.createInvoiceDetail(invoiceDetail);
    }

    @GetMapping("/by-invoice/{invoiceId}")
    public List<InvoiceDetail> getInvoiceDetailsByInvoice(@PathVariable int invoiceId) {
        return invoiceDetailService.getInvoiceDetailsByInvoiceId(invoiceId);
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmailViaMicroservice(@RequestBody InvoiceWrapper invoiceWrapper) {
        if (invoiceWrapper == null || invoiceWrapper.getInvoiceDetails() == null || invoiceWrapper.getInvoiceDetails().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invoice details are missing or invalid.");
        }

        Map<String, Object> emailPayload = new HashMap<>();

        // Prepare invoice header
        Map<String, Object> headerMap = new HashMap<>();
        if (invoiceWrapper.getInvoiceHeader() != null) {
            headerMap.put("invId", invoiceWrapper.getInvoiceHeader().getInvId());
            headerMap.put("custDetails", invoiceWrapper.getInvoiceHeader().getCustDetails());
            headerMap.put("invDate", invoiceWrapper.getInvoiceHeader().getInvDate() != null
                    ? invoiceWrapper.getInvoiceHeader().getInvDate().toString() : null);
            headerMap.put("quantity", invoiceWrapper.getInvoiceHeader().getQuantity());
            headerMap.put("finalAmount", invoiceWrapper.getInvoiceHeader().getFinalAmount());
            headerMap.put("tax", invoiceWrapper.getInvoiceHeader().getTax());
            headerMap.put("totalAmount", invoiceWrapper.getInvoiceHeader().getTotalAmount());
        }
        emailPayload.put("invoiceHeader", headerMap);

        // Prepare invoice details
        List<Map<String, Object>> detailsList = new ArrayList<>();
        int modelId = invoiceWrapper.getModelId(); // safer than accessing invoiceHeader.getModel().getModelId()

        for (InvoiceDetail detail : invoiceWrapper.getInvoiceDetails()) {
            if (detail == null || detail.getComponent() == null) continue;

            Map<String, Object> detailMap = new HashMap<>();
            Map<String, Object> compMap = new HashMap<>();

            Component component = detail.getComponent();
            compMap.put("compId", component.getCompId());
            compMap.put("compName", component.getCompName());

            if ("Y".equalsIgnoreCase(detail.getIsAlternate())) {
                List<AlternateComponent> alternates = alternateComponentService
                        .getAlternatesByComponentId(component.getCompId());

                AlternateComponent match = alternates.stream()
                	    .filter(alt -> alt.getModel() != null
                	            && alt.getModel().getModelId() == modelId)
                	    .findFirst()
                	    .orElse(null);

                if (match != null && match.getDeltaPrice() != null) {
                    compMap.put("deltaPrice", match.getDeltaPrice());
                } else {
                    compMap.put("deltaPrice", BigDecimal.ZERO);
                }
            } else {
                compMap.put("deltaPrice", BigDecimal.ZERO);
            }

            detailMap.put("component", compMap);
            detailMap.put("isAlternate", detail.getIsAlternate());
            detailsList.add(detailMap);
        }

        emailPayload.put("invoiceDetails", detailsList);

        // Send email via microservice
        String microserviceUrl = "http://localhost:9090/email/send";
        try {
            String response = restTemplate.postForObject(microserviceUrl, emailPayload, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}
