package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entities.InvoiceHeader;
import com.example.entities.Model;

@Repository
public interface InvoiceHeaderRepository extends JpaRepository<InvoiceHeader, Integer> {
    List<InvoiceHeader> findByModel(Model model);
    List<InvoiceHeader> findByCustDetailsContaining(String keyword);
    @EntityGraph(attributePaths = {"model", "user", "invoiceDetails", "invoiceDetails.component"})
    Optional<InvoiceHeader> findById(int id);
}
