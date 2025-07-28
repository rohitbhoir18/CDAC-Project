package com.example.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "segment_master")
public class SegmentMaster {
    private int segId;
    private String segName;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSegId() {
		return segId;
	}
	public void setSegId(int segId) {
		this.segId = segId;
	}
	
	
	public String getSegName() {
		return segName;
	}
	public void setSegName(String segName) {
		this.segName = segName;
	}
	

	@Override
	public String toString() {
		return "SegmentMaster [segId=" + segId + ", segName=" + segName + "]";
	}

}

