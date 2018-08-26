package com.cbalt.prueba03.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cbalt.prueba03.models.Likes;
import com.cbalt.prueba03.models.Pet;
import com.cbalt.prueba03.view.listener.OnLoadPet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ManagePet {

    OnLoadPet listener;

    public ManagePet() {
    }

    public ManagePet(OnLoadPet listener){
        this.listener = listener;
    }

    public void newPet(String name, String photo, String type, Likes likes){
        String userID = new UserFirebase().uid();
        Pet pet = new Pet(name, photo, type, likes, 0);
        new DataSource().savePet(userID, pet);
    }

    public void getPetInfo(String userID){
        new DataSource().petsReference().child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pet pet = dataSnapshot.getValue(Pet.class);
                Likes likes = dataSnapshot.child("likes").getValue(Likes.class);
                listener.loadPet(pet.getName(), pet.getType(), likes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
