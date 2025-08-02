package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.ComponentMaster;

public interface ComponentRepository extends JpaRepository<ComponentMaster, Integer>
{
	 @Query(value = "SELECT * FROM component_master WHERE comp_id = :compId", nativeQuery = true)
	 ComponentMaster findComponentById(@Param("compId") Integer compId);

	 @Query(value = "SELECT c.* FROM component_master c JOIN vehicle_detail v ON c.comp_id = v.comp_id WHERE v.model_id = :modelId", nativeQuery = true)
	 List<ComponentMaster> findComponentsByModelId(@Param("modelId") Integer modelId);

}
