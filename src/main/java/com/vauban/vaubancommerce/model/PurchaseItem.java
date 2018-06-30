package com.vauban.vaubancommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PurchaseItem {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@OneToOne
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
