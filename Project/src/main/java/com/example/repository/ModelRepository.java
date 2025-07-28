package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.ModelMaster;

public interface ModelRepository extends JpaRepository<ModelMaster, Integer>
{
	@Query(value = "SELECT * FROM model_master WHERE mfg_id = :mfgId", nativeQuery = true)
    List<ModelMaster> findModelsByManufacturerId(@Param("mfgId") Integer mfgId);

    @Query(value = "SELECT * FROM model_master WHERE seg_id = :segId", nativeQuery = true)
    List<ModelMaster> findModelsBySegmentId(@Param("segId") Integer segId);
}
