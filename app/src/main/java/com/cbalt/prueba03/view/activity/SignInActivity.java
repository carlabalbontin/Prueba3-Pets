package com.cbalt.prueba03.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cbalt.prueba03.R;
import com.cbalt.prueba03.view.presenter.contract.AuthUserContract;

import com.cbalt.prueba03.view.presenter.AuthUserPresenter;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity implements AuthUserContract.View {

    private static final int RC_SIGN_IN = 123;
    AuthUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        presenter = new AuthUserPresenter(this);
        presenter.userExists();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_SIGN_IN == requestCode){

            if (RESULT_OK == resultCode){
                firstTimeUser();
                // Debería crear un usuario si este no existe
                loginSuccessful();
            }
        }
    }

    @Override
    public void logInUser() {

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.FacebookBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build()))
                        .build(),
                RC_SIGN_IN);


    }

    @Override
    public void loginSuccessful() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void firstTimeUser() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
