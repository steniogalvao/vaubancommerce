package com.vauban.vaubancommerce.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

public class Purchase {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private LocalDateTime purchaseDate;

	@OneToMany
	private List<PurchaseItem> itens;

	@NotNull
	private Double totalPrice;

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate( LocalDateTime purchaseDate ) {
		this.purchaseDate = purchaseDate;
	}

	public List<PurchaseItem> getItens() {
		return itens;
	}

	public void setItens( List<PurchaseItem> itens ) {
		this.itens = itens;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice( Double totalPrice ) {
		this.totalPrice = totalPrice;
	}

}
