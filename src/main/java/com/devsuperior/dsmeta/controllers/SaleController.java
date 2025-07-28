package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SellerSumaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
    @RequestParam(name = "name", defaultValue = "") String name,
    @RequestParam(name = "minDate", defaultValue = "") String minDate,
    @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
    @PageableDefault(size = 5, page = 0, sort = "id") Pageable pageable
  ) {
    return ResponseEntity.ok(service.getSaleReport(name, minDate, maxDate, pageable));
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SellerSumaryDTO>> getSummary(
    @RequestParam(name = "minDate", defaultValue = "") String minDate,
    @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
    @PageableDefault(page = 0, size = 5) Pageable pageable
  ) {
    return ResponseEntity.ok(service.getSellerSummary(minDate, maxDate, pageable));
	}

}
