package com.example.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.AlternateComponent;
import com.example.entities.InvoiceDetail;
import com.example.entities.InvoiceHeader;
import com.example.entities.InvoiceWrapper;
import com.example.entities.Model;
import com.example.entities.User;
import com.example.entities.VehicleDetail;
import com.example.repositories.AlternateComponentRepository;
import com.example.repositories.InvoiceDetailRepository;
import com.example.repositories.InvoiceHeaderRepository;
import com.example.repositories.ModelRepository;
import com.example.repositories.UserRepository;
import com.example.repositories.VehicleDetailRepository;

import jakarta.transaction.Transactional;

@Service
public class InvoiceHeaderService {

    @Autowired
    private InvoiceHeaderRepository invoiceHeaderRepo;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepo;

    @Autowired
    private AlternateComponentRepository alternateComponentRepo;

    @Autowired
    private ModelRepository modelRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VehicleDetailRepository vehicleDetailRepo;

    public List<InvoiceHeader> getAllInvoices() {
        return invoiceHeaderRepo.findAll();
    }

    public Optional<InvoiceHeader> getInvoiceById(int invId) {
        return invoiceHeaderRepo.findById(invId);
    }

    public InvoiceHeader createInvoice(InvoiceHeader invoice) {
        return invoiceHeaderRepo.save(invoice);
    }

    @Transactional
    public InvoiceHeader createInvoice(InvoiceWrapper wrapper) {
        InvoiceHeader header = new InvoiceHeader();

        Model model = modelRepo.findById(wrapper.getModelId())
                .orElseThrow(() -> new RuntimeException("Model not found"));
        User user = userRepo.findById(wrapper.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        header.setUser(user);
        header.setModel(model);
        header.setQuantity(wrapper.getQuantity());

        // Customer details string (same as before)
        String customerInfo =
                "Name: " + user.getAuth_name() +
                ", Tel: " + user.getAuth_tel() +
                ", Cell: " + user.getCell() +
                ", Email: " + user.getEmail() +
                ", Fax: " + user.getFax() +
                ", Designation: " + user.getDesignation() +
                ", Company: " + user.getCompany_name() +
                ", Company ST No: " + user.getCompany_st_no() +
                ", Company VAT No: " + user.getCompany_vat_no() +
                ", Holding Type: " + user.getHolding_type() +
                ", Tax PAN: " + user.getTax_pan() +
                ", Address: " + user.getAdd1() + ", " + user.getAdd2() +
                ", City: " + user.getCity() +
                ", State: " + user.getState() +
                ", PIN: " + user.getPin();

        header.setCustDetails(customerInfo);

        // Calculate base and alternate price
        BigDecimal baseAmount = model.getPrice().multiply(BigDecimal.valueOf(wrapper.getQuantity()));
        BigDecimal altAmount = BigDecimal.ZERO;

        List<InvoiceDetail> details = new ArrayList<>();

        // Create a map of alternates from wrapper for quick lookup: compId -> isAlternate
        Map<Integer, String> selectedAlternatesMap = new HashMap<>();
        for (InvoiceWrapper.InvoiceComponentDetail compDetail : wrapper.getDetails()) {
            selectedAlternatesMap.put(compDetail.getCompId(), compDetail.getIsAlternate());
        }

        // Get all default components for the model (configurable and non-configurable)
        List<VehicleDetail> defaultComponents = vehicleDetailRepo.findByModel_ModelId(model.getModelId());

        for (VehicleDetail vd : defaultComponents) {
            int baseCompId = vd.getComponent().getCompId();

            InvoiceDetail detail = new InvoiceDetail();
            detail.setInvoiceHeader(header);

            // Check if this component is replaced by an alternate
            String isAlternate = selectedAlternatesMap.getOrDefault(baseCompId, "N");

            if ("Y".equalsIgnoreCase(isAlternate)) {
                // Find the alternate component for this base component and model
                AlternateComponent altComp = alternateComponentRepo
                        .findByBaseComponentCompIdAndModelModelId(baseCompId, model.getModelId())
                        .orElseThrow(() -> new RuntimeException(
                                "Alternate Component not found for modelId: " + model.getModelId() + " and compId: " + baseCompId
                        ));
                altAmount = altAmount.add(
                        altComp.getDeltaPrice().multiply(BigDecimal.valueOf(wrapper.getQuantity()))
                );
                detail.setComponent(altComp.getAlternateComponent());
                detail.setIsAlternate("Y");
            } else {
                // No alternate selected, use default component
                detail.setComponent(vd.getComponent());
                detail.setIsAlternate("N");
            }
            details.add(detail);
        }

        // Final amounts
        BigDecimal finalAmt = baseAmount.add(altAmount);
        BigDecimal tax = finalAmt.multiply(BigDecimal.valueOf(0.18)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = finalAmt.add(tax);

        header.setFinalAmount(finalAmt);
        header.setTax(tax);
        header.setTotalAmount(total);
        header.setInvDate(LocalDateTime.now());

        // Save header and details
        header = invoiceHeaderRepo.save(header);
        invoiceDetailRepo.saveAll(details);

        return header;
    }


}
