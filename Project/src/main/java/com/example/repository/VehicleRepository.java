package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.VehicleDetail;

public interface VehicleRepository extends JpaRepository<VehicleDetail, Integer>
{
	@Query(value = "SELECT * FROM vehicle_detail WHERE model_id = :modelId", nativeQuery = true)
    List<VehicleDetail> findByModelId(@Param("modelId") Integer modelId);

    @Query(value = "SELECT * FROM vehicle_detail WHERE comp_id = :compId", nativeQuery = true)
    List<VehicleDetail> findByComponentId(@Param("compId") Integer compId);

}
