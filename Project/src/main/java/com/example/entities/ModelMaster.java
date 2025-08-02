package com.example.entities;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "model_master")
public class ModelMaster {
    private int modelId;
    private String modelName;
    private MfgMaster manufacturer;
    private SegmentMaster segment;
    private int minQty;
    private String imagePath;
    private Set<VehicleDetail> vehicleDetails;
    private InvoiceHeader invoices;
    private Set<AlternateComponentMaster> alternates;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

    @ManyToOne
    @JoinColumn(name = "mfg_id")
	public MfgMaster getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(MfgMaster manufacturer) {
		this.manufacturer = manufacturer;
	}

    @ManyToOne
    @JoinColumn(name = "seg_id")
	public SegmentMaster getSegment() {
		return segment;
	}

	public void setSegment(SegmentMaster segment) {
		this.segment = segment;
	}

	public int getMinQty() {
		return minQty;
	}

	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<VehicleDetail> getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(Set<VehicleDetail> vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

    @OneToOne(mappedBy = "model", cascade = CascadeType.ALL)
    @JsonIgnore
	public InvoiceHeader getInvoices() {
		return invoices;
	}

	public void setInvoices(InvoiceHeader invoices) {
		this.invoices = invoices;
	}

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<AlternateComponentMaster> getAlternates() {
		return alternates;
	}

	public void setAlternates(Set<AlternateComponentMaster> alternates) {
		this.alternates = alternates;
	}

	@Override
	public String toString() {
		return "ModelMaster [modelId=" + modelId + ", modelName=" + modelName + ", manufacturer=" + manufacturer
				+ ", segment=" + segment + ", minQty=" + minQty + ", imagePath=" + imagePath + "]";
	}
    

}
