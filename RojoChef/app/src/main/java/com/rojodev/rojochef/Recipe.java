package com.rojodev.rojochef;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

class Recipe implements Serializable{

    private static final long serialVersionUID = 1L;

    @SerializedName("_id")
    private String id;

    @SerializedName("ingredients")
    private List<Ingredient> ingredientList;

    private int prepTime;
    private int cookTime;

    @SerializedName("directions")
    private List<String> instructionsList;

    @SerializedName("media")
    private Media media;

    @SerializedName("recipeName")
    private String title;

    public Recipe(String id, List<Ingredient> ingredientList, int prepTime, int cookTime, List<String> instructionsList, Media mediaList, String title) {
        this.id = id;
        this.ingredientList = ingredientList;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.instructionsList = instructionsList;
        this.media = mediaList;
        this.title = title;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public List<String> getInstructionsList() {
        return instructionsList;
    }

    public void setInstructionsList(List<String> instructionsList) {
        this.instructionsList = instructionsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredientList=" + ingredientList +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", instructionsList=" + instructionsList +
                ", id=" + id +
                ", media=" + media +
                '}';
    }
}
