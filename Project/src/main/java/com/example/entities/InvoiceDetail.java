package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail {
    private int invdtId;
    private InvoiceHeader invoice;
    private ComponentMaster component;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getInvdtId() {
		return invdtId;
	}

	public void setInvdtId(int invdtId) {
		this.invdtId = invdtId;
	}

	@ManyToOne
    @JoinColumn(name = "inv_id")
	public InvoiceHeader getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceHeader invoice) {
		this.invoice = invoice;
	}

	@ManyToOne
    @JoinColumn(name = "comp_id")
	@JsonIgnore
	public ComponentMaster getComponent() {
		return component;
	}

	public void setComponent(ComponentMaster component) {
		this.component = component;
	}

	@Override
	public String toString() {
		return "InvoiceDetail [invdtId=" + invdtId + ", invoice=" + invoice + ", component=" + component + "]";
	}
    

}

