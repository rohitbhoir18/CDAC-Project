package com.example.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "alternate_component_master")
public class AlternateComponentMaster {
    private int altId;
    private ModelMaster model;
    private ComponentMaster component;
    private ComponentMaster altComponent;
    private BigDecimal deltaPrice;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAltId() {
		return altId;
	}
	public void setAltId(int altId) {
		this.altId = altId;
	}
	
	@ManyToOne
	@JoinColumn(name = "model_id")
	public ModelMaster getModel() {
		return model;
	}
	public void setModel(ModelMaster model) {
		this.model = model;
	}
	
	@ManyToOne
    @JoinColumn(name = "comp_id")
	public ComponentMaster getComponent() {
		return component;
	}
	public void setComponent(ComponentMaster component) {
		this.component = component;
	}
	
	@ManyToOne
	@JoinColumn(name = "alt_comp_id")
	public ComponentMaster getAltComponent() {
		return altComponent;
	}
	public void setAltComponent(ComponentMaster altComponent) {
		this.altComponent = altComponent;
	}
	
	
	public BigDecimal getDeltaPrice() {
		return deltaPrice;
	}
	public void setDeltaPrice(BigDecimal deltaPrice) {
		this.deltaPrice = deltaPrice;
	}
	@Override
	public String toString() {
		return "AlternateComponentMaster [altId=" + altId + ", model=" + model + ", component=" + component
				+ ", altComponent=" + altComponent + ", deltaPrice=" + deltaPrice + "]";
	}
    
}

