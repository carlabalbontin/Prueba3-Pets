package com.cbalt.prueba03.view.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;


import com.cbalt.prueba03.R;
import com.cbalt.prueba03.models.Likes;
import com.cbalt.prueba03.view.presenter.contract.WelcomeContract;
import com.cbalt.prueba03.view.presenter.WelcomePresenter;


public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.View {

    // Sólo se accederá cuando está signing up
    // Aquí el usuario agregará la información de su mascota

    Likes petLikes;
    WelcomePresenter presenter;
    TextInputEditText userNameEt, petNameEt;
    Spinner petTypeSpinner;
    Button addPhotoButton, submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        presenter = new WelcomePresenter(this);
        petLikes = new Likes();

        userNameEt = findViewById(R.id.newUserName);
        petNameEt = findViewById(R.id.newPetName);
        petTypeSpinner = findViewById(R.id.newPetType);
        addPhotoButton = findViewById(R.id.addPhotoBtn);
        submitButton = findViewById(R.id.submitNewPet);

        // Poblar el spinner con data
        ArrayAdapter<CharSequence> spinnerOptions = ArrayAdapter.createFromResource(WelcomeActivity.this,
                R.array.pet_types, android.R.layout.simple_spinner_item);
        spinnerOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petTypeSpinner.setAdapter(spinnerOptions);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEt.getText().toString();
                String petName = petNameEt.getText().toString();
                String petPhoto = "";
                String petType = petTypeSpinner.getSelectedItem().toString();

                presenter.processPetForm(userName, petName, petPhoto, petType, petLikes);

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        presenter.findUserName();
    }


    @Override
    protected void onPause() {
        if(petTypeSpinner.getSelectedItemPosition() != 0){
            String text = petTypeSpinner.getSelectedItem().toString();
            Log.d("PAUSE", text);
        }
        super.onPause();
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        Toast.makeText(this, "Congratulations! Your pet was successfully created!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setUserName(String name) {
        userNameEt.setText(name);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_food:
                petLikes.setFood(checked);
                break;
            case R.id.checkbox_play:
                petLikes.setPlay(checked);
                break;
            case R.id.checkbox_cuddles:
                petLikes.setCuddles(checked);
                break;
        }
    }
}
