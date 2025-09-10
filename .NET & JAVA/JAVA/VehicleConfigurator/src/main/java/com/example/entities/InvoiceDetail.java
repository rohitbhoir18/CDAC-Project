package com.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_detail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "invoice"})
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inv_dtl_id")
    private int invDtlId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "inv_id", nullable = false)
    private InvoiceHeader invoiceHeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_id", nullable = false)
    private Component component;
    
    @Column(name = "is_alternate", length = 1, nullable = false)
    private String isAlternate;

    // Getters and Setters

    public int getInvDtlId() {
        return invDtlId;
    }

    public void setInvDtlId(int invDtlId) {
        this.invDtlId = invDtlId;
    }

    public InvoiceHeader getInvoice() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoice) {
        this.invoiceHeader = invoice;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public String getIsAlternate() {
        return isAlternate;
    }

    public void setIsAlternate(String isAlternate) {
        this.isAlternate = isAlternate;
    }
}
