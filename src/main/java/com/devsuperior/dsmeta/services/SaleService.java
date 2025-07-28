package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.common.exceptions.DateParseException;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SellerSumaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
  
	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

  @Transactional(readOnly = true)
  public Page<SaleReportDTO> getSaleReport(String name, String minDate, String maxDate, Pageable pageable){
    LocalDate max = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate min = max.minusYears(1L);
    
    try {
      if (minDate != null && !minDate.trim().isEmpty()) {
        min = LocalDate.parse(minDate);
      }
      
      if (maxDate != null && !maxDate.trim().isEmpty()) {
        max = LocalDate.parse(maxDate);
      }
    } catch (DateTimeParseException e) {
      throw new DateParseException("O formato da data passada está incorreto");
    }
    
    return repository.searchSalesReport(pageable, name, min, max);
  }

  @Transactional(readOnly = true)
  public Page<SellerSumaryDTO> getSellerSummary(String minDate, String maxDate, Pageable pageable){
    LocalDate max = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate min = max.minusYears(1L);

    try {
      if (minDate !=null && !minDate.trim().isEmpty()){
        min = LocalDate.parse(minDate);
      }
      if (maxDate != null && !maxDate.trim().isEmpty()) {
        max = LocalDate.parse(maxDate);
      }
    } catch (DateTimeParseException e){
      throw new DateParseException("O formato da data passada está incorreto");
    }

    return repository.searchSellerSummary(min, max, pageable);
  }
}
