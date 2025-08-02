package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.SegmentMaster;

public interface SegmentRepository extends JpaRepository<SegmentMaster, Integer>
{
	 @Query(value = "SELECT * FROM segment_master WHERE seg_id = :segId", nativeQuery = true)
	 SegmentMaster findSegmentById(@Param("segId") Integer segId);
}
