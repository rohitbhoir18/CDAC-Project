package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.MfgMaster;

public interface MfgRepository extends JpaRepository<MfgMaster, Integer>
{
	@Query(value = "SELECT * FROM mfg_master WHERE mfg_id = :mfgId", nativeQuery = true)
    MfgMaster findManufacturerById(@Param("mfgId") Integer mfgId);

    @Query(value = "SELECT * FROM mfg_master WHERE seg_id = :segId", nativeQuery = true)
    List<MfgMaster> findManufacturersBySegmentId(@Param("segId") Integer segId);
}
