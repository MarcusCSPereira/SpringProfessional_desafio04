package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SellerSumaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(s.id, s.date, ROUND(s.amount, 1), s.seller.name) " +
         "FROM Sale s " +
         "WHERE s.date BETWEEN :minDate AND :maxDate " +
         "AND (:name IS NULL OR :name = '' OR UPPER(s.seller.name) LIKE UPPER(CONCAT('%', :name, '%')))")
  Page<SaleReportDTO> searchSalesReport(Pageable pageable, String name, LocalDate minDate, LocalDate maxDate);

  @Query("SELECT new com.devsuperior.dsmeta.dto.SellerSumaryDTO(s.seller.name, ROUND(SUM(s.amount), 1)) " +
         "FROM Sale s " +
         "WHERE s.date BETWEEN :minDate AND :maxDate " +
         "GROUP BY s.seller.name")
  Page<SellerSumaryDTO> searchSellerSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);

}
