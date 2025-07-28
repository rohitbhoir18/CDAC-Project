package com.example.entities;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "component_master")
public class ComponentMaster {
    private int compId;
    private String compName;
    private Set<VehicleDetail> vehicleDetails ;
    private Set<AlternateComponentMaster> componentAlternates;
    private Set<AlternateComponentMaster> alternateFor ;
    private Set<InvoiceDetail> invoiceDetails ;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
    @OneToMany(mappedBy = "component")
	public Set<VehicleDetail> getVehicleDetails() {
		return vehicleDetails;
	}
	public void setVehicleDetails(Set<VehicleDetail> vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
	
	@OneToMany(mappedBy = "component")
	public Set<AlternateComponentMaster> getComponentAlternates() {
		return componentAlternates;
	}
	public void setComponentAlternates(Set<AlternateComponentMaster> componentAlternates) {
		this.componentAlternates = componentAlternates;
	}
	
	@OneToMany(mappedBy = "altComponent")
	public Set<AlternateComponentMaster> getAlternateFor() {
		return alternateFor;
	}
	public void setAlternateFor(Set<AlternateComponentMaster> alternateFor) {
		this.alternateFor = alternateFor;
	}
	
	@OneToMany(mappedBy = "component")
	public Set<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}
	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	@Override
	public String toString() {
		return "ComponentMaster [compId=" + compId + ", compName=" + compName + "]";
	}
    
    
}

