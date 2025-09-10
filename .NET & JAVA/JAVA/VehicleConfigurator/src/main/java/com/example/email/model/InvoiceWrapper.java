package com.example.email.model;

import java.util.List;

public class InvoiceWrapper {

    private InvoiceHeader invoiceHeader;
    private List<InvoiceComponentDetail> details;

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public List<InvoiceComponentDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceComponentDetail> details) {
        this.details = details;
    }

    // Nested InvoiceHeader class
    public static class InvoiceHeader {
        private Integer invId;
        private String custDetails;
        private String invDate;
        private Integer quantity;
        private Double finalAmount;
        private Double tax;
        private Double totalAmount;

        public Integer getInvId() {
            return invId;
        }

        public void setInvId(Integer invId) {
            this.invId = invId;
        }

        public String getCustDetails() {
            return custDetails;
        }

        public void setCustDetails(String custDetails) {
            this.custDetails = custDetails;
        }

        public String getInvDate() {
            return invDate;
        }

        public void setInvDate(String invDate) {
            this.invDate = invDate;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(Double finalAmount) {
            this.finalAmount = finalAmount;
        }

        public Double getTax() {
            return tax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }
    }

    // Nested InvoiceComponentDetail class
    public static class InvoiceComponentDetail {
        private String compName;
        private String isAlternate;

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }

        public String getIsAlternate() {
            return isAlternate;
        }

        public void setIsAlternate(String isAlternate) {
            this.isAlternate = isAlternate;
        }
    }
}
