package com.rojodev.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	@JsonProperty
	private String email;
	@JsonProperty
	private String firstName;
	@JsonProperty
	private String lastName;
	@JsonProperty
	private String middleName;
	@JsonProperty
	private String password;
	
	@JsonProperty
	private List<String> favoriteRecipes;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public List<String> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<String> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}
	
	
}
