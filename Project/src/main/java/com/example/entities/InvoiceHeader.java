package com.example.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_header")
public class InvoiceHeader {
    private int invId;
    private LocalDate invDate;
    private ModelMaster model;
    private BigDecimal totalAmt;
    private BigDecimal tax;
    private BigDecimal amt;
    private String custDetails;
    private Set<InvoiceDetail> invoiceDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getInvId() {
		return invId;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

	public LocalDate getInvDate() {
		return invDate;
	}

	public void setInvDate(LocalDate invDate) {
		this.invDate = invDate;
	}

	@ManyToOne
    @JoinColumn(name = "model_id")
	public ModelMaster getModel() {
		return model;
	}

	public void setModel(ModelMaster model) {
		this.model = model;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getCustDetails() {
		return custDetails;
	}

	public void setCustDetails(String custDetails) {
		this.custDetails = custDetails;
	}

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	public Set<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	@Override
	public String toString() {
		return "InvoiceHeader [invId=" + invId + ", invDate=" + invDate + ", model=" + model + ", totalAmt=" + totalAmt
				+ ", tax=" + tax + ", amt=" + amt + ", custDetails=" + custDetails + "]";
	}
    
}

