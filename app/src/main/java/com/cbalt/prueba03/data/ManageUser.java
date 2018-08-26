package com.cbalt.prueba03.data;

import android.support.annotation.NonNull;

import com.cbalt.prueba03.models.User;
import com.cbalt.prueba03.view.listener.OnLoadUser;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ManageUser {

    DataSource source;
    OnLoadUser listener;

    public ManageUser() {
    }

    public ManageUser(OnLoadUser listener) {
        this.listener = listener;
    }

    public ManageUser(DataSource source) {
        this.source = source;
    }

    public void newUser(String name, String email, String uid){
        User user = new User(name, email, uid);
        new DataSource().save(user);
    }

    public void editUserName(String name){
        FirebaseUser currentUser = new UserFirebase().getFirebaseUser();
        User user = new User();
        user.setName(name);
        user.setKey(currentUser.getUid());
        user.setEmail(currentUser.getEmail());
        new DataSource().save(user);

    }

    public User getUser(){
        FirebaseUser currentUser = new UserFirebase().getFirebaseUser();
        User user = new User(currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getUid());
        return user;
    }

    public void getUserInfo(){
        FirebaseUser currentUser = new UserFirebase().getFirebaseUser();
        new DataSource().usersReference().child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                listener.loadUser(user.getName(), user.getEmail(), user.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
