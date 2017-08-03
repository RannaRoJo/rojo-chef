package com.rojodev.rojochef;

import java.io.Serializable;

public class Media implements Serializable{

    private static final long serialVersionUID = 3L;

    private String thumbnailUrl;
    private String photoUrl;

    public Media(String recipeThumbnail, String recipePhoto) {
        this.thumbnailUrl = recipeThumbnail;
        this.photoUrl = recipePhoto;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "Media{" +
                "thumbnailUrl='" + thumbnailUrl + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
