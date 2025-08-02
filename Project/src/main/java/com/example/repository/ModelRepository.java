package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.ModelMaster;

public interface ModelRepository extends JpaRepository<ModelMaster, Integer>
{
	@Query(value="select * FROM model_master WHERE seg_id = :segId AND mfg_id = :mfgId",nativeQuery = true)
	List<ModelMaster> findModelsBySegIdandMfgId(@Param("segId") Integer segId, @Param("mfgId") Integer mfgId);

	@Query(value="select * from model_master where mfg_id =:mfgId",nativeQuery = true)
	List<ModelMaster> findModelsByMfgId(@Param("mfgId") Integer mfgId);
	
//	@Query(value="select min_qty from model_master where seg_id=:segId",nativeQuery = true)
//	ModelMaster findMinQtyBySegId(@Param("segId") int segId);
}
