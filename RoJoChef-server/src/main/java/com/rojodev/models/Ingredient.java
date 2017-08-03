package com.rojodev.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {

	private float amount;
	private String measurement;
	private String name;
	
	public Ingredient() {}
	
	public Ingredient(long id, float amount, String measurement, String name) {
		this.amount = amount;
		this.measurement = measurement;
		this.name = name;
	}
	
	@JsonProperty
	public float getAmount() {
		return amount;
	}
	
	@JsonProperty
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	@JsonProperty
	public String getMeasurement() {
		return measurement;
	}
	
	@JsonProperty
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
	
	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}
}
