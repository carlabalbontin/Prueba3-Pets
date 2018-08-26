package com.cbalt.prueba03.data;

import android.util.Log;

import com.cbalt.prueba03.models.Pet;
import com.cbalt.prueba03.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DataSource {

    private DatabaseReference root =  FirebaseDatabase.getInstance().getReference();

    public DatabaseReference petsReference() {
        return root.child("pets");
    }

    public DatabaseReference usersReference() {
        return root.child("users");
    }

    public void save(User user){
        usersReference().child(user.getKey()).setValue(user);
    }

    public void savePet(String userUI, Pet pet){
        Log.d("SAVE", "Save");
        petsReference().child(userUI).setValue(pet);
    }

    public void addPetLike(String userUI, String like){
        petsReference().child(userUI).child("likes").push().setValue(like);
    }

    public Query petsQuery(){
        return petsReference().orderByChild("name");
    }

}
