package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.AlternateComponentMaster;

public interface AlternateCompRepository extends JpaRepository<AlternateComponentMaster, Integer>
{
	@Query(value = "SELECT * FROM alternate_component_master WHERE model_id = :modelId", nativeQuery = true)
    List<AlternateComponentMaster> findAlternatesByModelId(@Param("modelId") Integer modelId);

    @Query(value = "SELECT * FROM alternate_component_master WHERE comp_id = :compId", nativeQuery = true)
    List<AlternateComponentMaster> findAlternatesByComponentId(@Param("compId") Integer compId);

}
