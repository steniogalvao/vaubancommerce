package com.vauban.vaubancommerce.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Purchase {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime purchaseDate;

	@NotNull
	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private List<PurchaseItem> itens;

	private Double totalPrice;

	@OneToOne
	private User buyer;

	private boolean canceled = false;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
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

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer( User buyer ) {
		this.buyer = buyer;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled( boolean canceled ) {
		this.canceled = canceled;
	}

}
