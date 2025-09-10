package com.example.email.model;

import java.util.List;

public class InvoiceWrapper {

    private InvoiceHeader invoiceHeader;
    private List<InvoiceDetail> invoiceDetails;

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

    public static class InvoiceHeader {
        private Integer invId;
        private String custDetails;
        private String invDate;
        private Integer quantity;
        private Double finalAmount;
        private Double tax;
        private Double totalAmount;

        // Getters and setters

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

    public static class InvoiceDetail {
        private Component component;
        private String isAlternate;

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

    public static class Component {
        private Integer compId;
        private String compName;
        private Double price;

        // Getters and setters
        public Integer getCompId() {
            return compId;
        }

        public void setCompId(Integer compId) {
            this.compId = compId;
        }

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
