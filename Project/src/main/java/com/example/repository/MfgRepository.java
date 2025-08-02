package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.MfgMaster;

public interface MfgRepository extends JpaRepository<MfgMaster, Integer>
{
	@Query("SELECT s.manufacturer FROM SegMfgMaster s WHERE s.segment.segId = :segId")
	List<MfgMaster> findMfgBySegmentId(@Param("segId") int segId);

}
