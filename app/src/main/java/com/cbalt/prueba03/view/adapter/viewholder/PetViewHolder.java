package com.cbalt.prueba03.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cbalt.prueba03.R;

public class PetViewHolder extends RecyclerView.ViewHolder {

    public TextView petNameTv, petTypeTv;

    public PetViewHolder(View itemView) {
        super(itemView);

        petNameTv = itemView.findViewById(R.id.petNameItem);
        petTypeTv = itemView.findViewById(R.id.petTypeItem);

    }
}
