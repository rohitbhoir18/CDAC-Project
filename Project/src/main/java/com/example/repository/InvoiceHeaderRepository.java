package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.InvoiceHeader;

public interface InvoiceHeaderRepository extends JpaRepository<InvoiceHeader, Integer>
{
	@Query(value = "SELECT * FROM invoice_header WHERE model_id = :modelId", nativeQuery = true)
    List<InvoiceHeader> findInvoicesByModelId(@Param("modelId") Integer modelId);

    @Query(value = "SELECT * FROM invoice_header WHERE inv_id = :invId", nativeQuery = true)
    InvoiceHeader findInvoiceById(@Param("invId") Integer invId);

}
