package com.cbalt.prueba03.models;

import java.io.Serializable;

public class Pet implements Serializable {

    public String name, photo, type;
    //public HashMap<String, String> likes;
    public Likes likes;
    int stars;

    public Pet() {
    }

    public Pet(String name, String photo, String type, Likes likes, int stars) {
        this.name = name;
        this.photo = photo;
        this.type = type;
        this.likes = likes;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
