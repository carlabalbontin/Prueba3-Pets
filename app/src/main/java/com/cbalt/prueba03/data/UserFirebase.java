package com.cbalt.prueba03.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserFirebase {

    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public String uid(){
        return firebaseUser.getUid();
    }

    public String email() {
        return getFirebaseUser().getEmail();
    }
}
