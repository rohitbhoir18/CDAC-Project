package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mfg_master")
public class MfgMaster {
    private int mfgId;
    private String mfgName;
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMfgId() {
		return mfgId;
	}
	public void setMfgId(int mfgId) {
		this.mfgId = mfgId;
	}
	public String getMfgName() {
		return mfgName;
	}
	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}
	
    
	@Override
	public String toString() {
		return "MfgMaster [mfgId=" + mfgId + ", mfgName=" + mfgName + ", segment=" + "]";
	}
    

}

