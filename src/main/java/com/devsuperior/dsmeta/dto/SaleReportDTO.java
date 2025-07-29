package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleReportDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	private String sellerName;

  public SaleReportDTO() {}

	public SaleReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.sellerName = sellerName;
	}

	// Construtor para facilitar conversão de entidade
	public SaleReportDTO(Sale entity) {
		this.id = entity.getId();
		this.date = entity.getDate();
		this.amount = entity.getAmount();
		this.sellerName = entity.getSeller().getName();
	}

	public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }
}
