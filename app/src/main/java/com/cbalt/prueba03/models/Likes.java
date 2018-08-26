package com.cbalt.prueba03.models;

import java.io.Serializable;

public class Likes implements Serializable {
    public boolean food, play, cuddles;

    public Likes() {
    }

    public Likes(boolean food, boolean play, boolean cuddles) {
        this.food = food;
        this.play = play;
        this.cuddles = cuddles;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isCuddles() {
        return cuddles;
    }

    public void setCuddles(boolean cuddles) {
        this.cuddles = cuddles;
    }
}
