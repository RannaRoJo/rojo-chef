package com.rojodev.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Media {
	
	private String thumbnail;
	private String photo;
	
	@JsonProperty
	public String getThumbnail() {
		return thumbnail;
	}
	
	@JsonProperty
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	@JsonProperty
	public String getPhoto() {
		return photo;
	}
	
	@JsonProperty
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
