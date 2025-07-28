package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.InvoiceDetail;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetail, Integer>
{
	@Query(value = "SELECT * FROM invoice_detail WHERE inv_id = :invId", nativeQuery = true)
	List<InvoiceDetail> findDetailsByInvoiceId(@Param("invId") Integer invId);

	@Query(value = "SELECT * FROM invoice_detail WHERE comp_id = :compId", nativeQuery = true)
	List<InvoiceDetail> findDetailsByComponentId(@Param("compId") Integer compId);

}
