package com.example.entities;

import java.util.List;

public class InvoiceWrapper {

    private int userId;
    private int modelId;
    private int quantity;

    private InvoiceHeader invoiceHeader;
    private List<InvoiceDetail> invoiceDetails;

    private List<InvoiceComponentDetail> details;

    // Getters & Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public List<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public List<InvoiceComponentDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceComponentDetail> details) {
        this.details = details;
    }

    public static class InvoiceComponentDetail {
        private int compId;
        private String isAlternate;  // "Y" or "N"

        public int getCompId() {
            return compId;
        }

        public void setCompId(int compId) {
            this.compId = compId;
        }

        public String getIsAlternate() {
            return isAlternate;
        }

        public void setIsAlternate(String isAlternate) {
            this.isAlternate = isAlternate;
        }
    }
}
