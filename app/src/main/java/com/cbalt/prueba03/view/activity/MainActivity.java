package com.cbalt.prueba03.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cbalt.prueba03.R;
import com.cbalt.prueba03.data.DataSource;
import com.cbalt.prueba03.view.adapter.viewholder.PetViewHolder;
import com.cbalt.prueba03.models.Pet;
import com.cbalt.prueba03.view.adapter.PetAdapter;
import com.cbalt.prueba03.view.listener.OnClickPet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements OnClickPet {

    // Pantalla principal de la aplicación
    // El usuario verá la lista de mascotas, y tendrá acceso a su info y a logout

    public static final String PET = "com.cbalt.prueba03.data.PET";
    public static final String PET_NAME = "com.cbalt.prueba03.data.PET_NAME";
    public static final String PET_TYPE = "com.cbalt.prueba03.data.PET_TYPE";
    public static final String PET_FOOD = "com.cbalt.prueba03.data.PET_FOOD";
    public static final String PET_PLAY = "com.cbalt.prueba03.data.PET_PLAY";
    public static final String PET_CUDDLES = "com.cbalt.prueba03.data.PET_CUDDLES";
    RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<Pet, PetViewHolder> petAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<Pet> options = new FirebaseRecyclerOptions.Builder<Pet>()
                .setQuery(new DataSource().petsQuery(), Pet.class)
                .setLifecycleOwner(this)
                .build();

        petAdapter = new PetAdapter(options, this);
        recyclerView.setAdapter(petAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_account:
                Intent intentAccount = new Intent(this, AccountActivity.class);
                startActivity(intentAccount);
                return true;

            case R.id.action_close_session:
                FirebaseAuth.getInstance().signOut();
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override protected void onStart() {
        super.onStart();
        petAdapter.startListening();
    }

    @Override protected void onStop() {
        super.onStop();
        petAdapter.stopListening();
    }

    /*private HashMap<String,String> getData() {
        HashMap <String,String> list = new HashMap <String,String>();
        for (int i = 0; i < 25; i++) {
            list.put(String.valueOf(i),"Text");
        }
        return list;
    }*/

    @Override
    public void clickPet(Pet pet) {
        Intent intent = new Intent(MainActivity.this, PetActivity.class);
        intent.putExtra(PET, pet);
        startActivity(intent);
        finish();
    }
}
