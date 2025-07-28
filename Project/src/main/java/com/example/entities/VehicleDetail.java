package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_detail")
public class VehicleDetail {
    private int confiId;
    private ModelMaster model;
    private ComponentMaster component;
    private ComponentType compType;
    private boolean isConfigurable;

    public enum ComponentType {
        C, 
        S, 
        I, 
        E  
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getConfiId() {
		return confiId;
	}

	public void setConfiId(int confiId) {
		this.confiId = confiId;
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

	@Enumerated(EnumType.STRING)
    @Column(name = "comp_type")
	public ComponentType getCompType() {
		return compType;
	}

	public void setCompType(ComponentType compType) {
		this.compType = compType;
	}

	@Column(name = "is_configurable")
	public boolean isConfigurable() {
		return isConfigurable;
	}

	public void setConfigurable(boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
	}
    
}

