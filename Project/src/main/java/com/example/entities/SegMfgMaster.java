package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "seg_mfg_master")
public class SegMfgMaster 
{
    private int smId;
    private SegmentMaster segment;
    private MfgMaster manufacturer;

    
    public SegMfgMaster() 
    {
    	
    }

    public SegMfgMaster(SegmentMaster segment, MfgMaster manufacturer) 
    {
        this.segment = segment;
        this.manufacturer = manufacturer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sm_id")
	public int getSmId() {
		return smId;
	}

	public void setSmId(int smId) {
		this.smId = smId;
	}

	@ManyToOne
    @JoinColumn(name = "seg_id", nullable = false)
	public SegmentMaster getSegment() {
		return segment;
	}

	public void setSegment(SegmentMaster segment) {
		this.segment = segment;
	}

	@ManyToOne
    @JoinColumn(name = "mfg_id", nullable = false)
	public MfgMaster getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(MfgMaster manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "SegMfgMaster [smId=" + smId + ", segment=" + segment + ", manufacturer=" + manufacturer + "]";
	}

}

