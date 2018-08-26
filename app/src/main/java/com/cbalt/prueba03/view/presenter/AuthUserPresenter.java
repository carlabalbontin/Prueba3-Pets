package com.cbalt.prueba03.view.presenter;

import com.cbalt.prueba03.data.ManageUser;
import com.cbalt.prueba03.view.presenter.contract.AuthUserContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthUserPresenter implements AuthUserContract.Presenter {

    public FirebaseAuth auth;
    AuthUserContract.View contract;
    ManageUser manageUser;

    public AuthUserPresenter(AuthUserContract.View contract) {
        this.contract = contract;
    }

    public AuthUserPresenter() {

    }

    @Override
    public void userExists() {
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
            contract.loginSuccessful();
        } else {
            // not signed in
            contract.logInUser();
        }
    }

    @Override
    public void saveNewUser() {
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String userEmail = currentUser.getEmail();
        new ManageUser().newUser(currentUser.getDisplayName(), userEmail, currentUser.getUid());
        contract.firstTimeUser();
    }
}
