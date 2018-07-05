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
	private long productId;

	@NotNull
	private int amount;

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId( long productId ) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount( int amount ) {
		this.amount = amount;
	}

}
