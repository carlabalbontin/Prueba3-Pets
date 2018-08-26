package com.cbalt.prueba03.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cbalt.prueba03.R;
import com.cbalt.prueba03.data.UserFirebase;
import com.cbalt.prueba03.view.presenter.AccountPresenter;
import com.cbalt.prueba03.view.presenter.contract.AccountContract;

public class AccountActivity extends AppCompatActivity implements AccountContract.View {

    // Aquí el usuario podrá ver y editar su información y la de su mascota

    TextView userNameTv, userEmailTv, petNameTv, petTypeTv, petLikesTv;
    Button editAccount;
    AccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        userNameTv = findViewById(R.id.userName);
        userEmailTv = findViewById(R.id.userEmail);
        petNameTv = findViewById(R.id.petName);
        petTypeTv = findViewById(R.id.petType);
        petLikesTv = findViewById(R.id.petLikes);
        editAccount = findViewById(R.id.editAccount);
        presenter = new AccountPresenter(this);



        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, EditAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getUserInfo();
        presenter.getPetInfo(new UserFirebase().uid());
    }

    @Override
    public void setUserInfo(String name, String email) {
        userNameTv.setText(name);
        userEmailTv.setText(email);
    }

    @Override
    public void setPetInfo(String name, String type, String likes) {
        petNameTv.setText(name);
        petTypeTv.setText(type);
        petLikesTv.setText(likes);
    }
}
