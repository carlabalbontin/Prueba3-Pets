package com.cbalt.prueba03.view.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.cbalt.prueba03.R;
import com.cbalt.prueba03.data.UserFirebase;
import com.cbalt.prueba03.models.Likes;
import com.cbalt.prueba03.view.presenter.contract.EditAccountContract;
import com.cbalt.prueba03.view.presenter.EditAccountPresenter;

public class EditAccountActivity extends AppCompatActivity implements EditAccountContract.View {

    Likes likes;
    EditAccountPresenter presenter;
    TextInputEditText userNameEt, petNameEt;
    Spinner petTypeSpinner;
    CheckBox foodCb, playCb, cuddlesCb;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        likes = new Likes();

        userNameEt = findViewById(R.id.editUserName);
        petNameEt = findViewById(R.id.editPetName);
        petTypeSpinner = findViewById(R.id.editPetType);
        saveButton = findViewById(R.id.saveAccount);
        foodCb = findViewById(R.id.checkbox_edit_food);
        playCb = findViewById(R.id.checkbox_edit_play);
        cuddlesCb = findViewById(R.id.checkbox_edit_cuddles);

        // Poblar el spinner con data
        ArrayAdapter<CharSequence> spinnerOptions = ArrayAdapter.createFromResource(EditAccountActivity.this,
                R.array.pet_types, android.R.layout.simple_spinner_item);
        spinnerOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petTypeSpinner.setAdapter(spinnerOptions);

        presenter = new EditAccountPresenter(this, getResources().getStringArray(R.array.pet_types));
        presenter.petInformation(new UserFirebase().uid());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEt.getText().toString();
                String petName = petNameEt.getText().toString();
                String petPhoto = "";
                String petType = petTypeSpinner.getSelectedItem().toString();
                presenter.processForm(userName, petName, petPhoto, petType, likes);
            }
        });
    }

    public void onEditLikes(View view){
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_edit_food:
                likes.setFood(checked);
                break;
            case R.id.checkbox_edit_play:
                likes.setPlay(checked);
                break;
            case R.id.checkbox_edit_cuddles:
                likes.setCuddles(checked);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.findUserName();
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        Intent intent = new Intent(EditAccountActivity.this, AccountActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setUserName(String name) {
        userNameEt.setText(name);
    }

    @Override
    public void setPetInfo(String name, int type, Likes likes) {
        petNameEt.setText(name);
        petTypeSpinner.setSelection(type);
        foodCb.setChecked(likes.isFood());
        this.likes.setFood(likes.isFood());
        playCb.setChecked(likes.isPlay());
        this.likes.setPlay(likes.isPlay());
        cuddlesCb.setChecked(likes.isCuddles());
        this.likes.setCuddles(likes.isCuddles());
    }
}
