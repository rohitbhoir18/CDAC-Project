package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entities.InvoiceDetail;
import com.example.entities.InvoiceHeader;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
    List<InvoiceDetail> findByComponent_CompId(int compId);
    @EntityGraph(attributePaths = "component")
    List<InvoiceDetail> findByInvoiceHeaderInvId(int invId);
}

