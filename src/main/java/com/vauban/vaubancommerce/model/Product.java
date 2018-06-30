package com.vauban.vaubancommerce.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Product {

	@Id
	@GeneratedValue
	private Long Id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	private Double price;

	@NotNull
	private int amount;

	private boolean active = true;

	public Long getId() {
		return Id;
	}

	public void setId( Long id ) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice( Double price ) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount( int amount ) {
		this.amount = amount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive( boolean active ) {
		this.active = active;
	}

}
