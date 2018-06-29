package com.vauban.vaubancommerce.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class PurchaseItem {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private Product product;

	@NotNull
	private int amount;

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct( Product product ) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount( int amount ) {
		this.amount = amount;
	}

}
