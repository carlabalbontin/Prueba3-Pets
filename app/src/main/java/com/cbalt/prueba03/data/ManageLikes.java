package com.cbalt.prueba03.data;

import com.cbalt.prueba03.models.Likes;

import java.util.HashMap;

public class ManageLikes {



    public HashMap<String, Boolean> convertToMap(Likes likes){

        HashMap<String, Boolean> likesMap = new HashMap<String, Boolean>();
        likesMap.put("food", likes.isFood());
        likesMap.put("play", likes.isFood());
        likesMap.put("cuddles", likes.isFood());
        return likesMap;

    }

    public Likes convertToObject(HashMap<String, Boolean> likesMap){
        Likes likes = new Likes();
        if(likesMap.containsKey("food")){
            likes.setFood(true);
        } else {
            likes.setFood(false);
        }
        if(likesMap.containsKey("play")){
            likes.setPlay(true);
        } else {
            likes.setPlay(false);
        }
        if(likesMap.containsKey("cuddles")){
            likes.setCuddles(true);
        } else {
            likes.setCuddles(false);
        }

        return likes;

    }

}
