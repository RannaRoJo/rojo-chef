package com.rojodev.models;

import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipe {
	
	private String _id;
	private String recipeName;
	private List<Ingredient> ingredients;
	private int prepTime;
	private int cookTime;
	private List<String> directions;
	private Media media;
	
	public Recipe() {}

	public Recipe(ObjectId id, List<Ingredient> ingredients, int prepTime, int cookTime, List<String> directions) {

		this.ingredients = ingredients;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.directions = directions;
	}

	@JsonProperty
	public String get_id() {
		return _id;
	}

	@JsonProperty
	public void set_id(String id) {
		this._id = id;
	}
	
	@JsonProperty
	public String getRecipeName() {
		return recipeName;
	}
	
	@JsonProperty
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	@JsonProperty
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	@JsonProperty
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@JsonProperty
	public int getPrepTime() {
		return prepTime;
	}

	@JsonProperty
	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	@JsonProperty
	public int getCookTime() {
		return cookTime;
	}

	@JsonProperty
	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	@JsonProperty
	public List<String> getDirections() {
		return directions;
	}

	@JsonProperty
	public void setDirections(List<String> directions) {
		this.directions = directions;
	}

	@JsonProperty
	public Media getMedia() {
		return media;
	}

	@JsonProperty
	public void setMedia(Media media) {
		this.media = media;
	}
	
}
