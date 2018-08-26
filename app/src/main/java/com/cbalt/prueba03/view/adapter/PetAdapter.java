package com.cbalt.prueba03.view.adapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cbalt.prueba03.R;
import com.cbalt.prueba03.view.adapter.viewholder.PetViewHolder;
import com.cbalt.prueba03.models.Pet;
import com.cbalt.prueba03.view.listener.OnClickPet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class PetAdapter extends FirebaseRecyclerAdapter<Pet, PetViewHolder> {

    OnClickPet listener;

    public PetAdapter(@NonNull FirebaseRecyclerOptions<Pet> options, OnClickPet listener) {
        super(options);
        this.listener = listener;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final PetViewHolder holder, int position, @NonNull final Pet model) {
        holder.petNameTv.setText(model.getName());
        holder.petTypeTv.setText(model.getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pet auxPet = getItem(holder.getAdapterPosition());
                listener.clickPet(model);
            }
        });
    }
    @Override
    public void onError(DatabaseError e) {
    }
}
