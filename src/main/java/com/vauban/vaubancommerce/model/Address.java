package com.vauban.vaubancommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String Country;

	@NotNull
	private String city;

	@NotNull
	private String neighborhood;

	@NotNull
	private String street;

	@NotNull
	private String number;

	@NotNull
	private String zipCode;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry( String country ) {
		Country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood( String neighborhood ) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet( String street ) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber( String number ) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode( String zipCode ) {
		this.zipCode = zipCode;
	}

}