package com.cbalt.prueba03.view.activity;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.cbalt.prueba03.R;
import com.cbalt.prueba03.models.Pet;

public class PetActivity extends AppCompatActivity {

    TextView petName, petType;
    ImageView foodIcon, playIcon, cuddlesIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Pet pet = (Pet) getIntent().getSerializableExtra(MainActivity.PET);

        petName = findViewById(R.id.petDetailsName);
        petType = findViewById(R.id.petDetailsType);
        foodIcon = findViewById(R.id.foodIcon);
        playIcon = findViewById(R.id.playIcon);
        cuddlesIcon = findViewById(R.id.cuddlesIcon);

        setPetInfo(pet.getName(), pet.getType(), pet.likes.isFood(), pet.likes.isPlay(), pet.likes.isCuddles());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:

                Intent upIntent=NavUtils.getParentActivityIntent(this);
                //You might need to add some Launch Parameters like
                upIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setPetInfo(String name, String type, boolean food, boolean play, boolean cuddles ) {
        petName.setText(name);
        petType.setText(type);
        if(food){
            foodIcon.setImageResource(R.mipmap.baseline_check_green);
        }
        if(play){
            playIcon.setImageResource(R.mipmap.baseline_check_green);
        }
        if(cuddles){
            cuddlesIcon.setImageResource(R.mipmap.baseline_check_green);
        }
    }
}
