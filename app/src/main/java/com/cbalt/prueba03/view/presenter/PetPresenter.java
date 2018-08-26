package com.cbalt.prueba03.view.presenter;

import android.view.View;

import com.cbalt.prueba03.data.ManagePet;
import com.cbalt.prueba03.models.Likes;
import com.cbalt.prueba03.view.listener.OnLoadPet;
import com.cbalt.prueba03.view.presenter.contract.PetContract;

public class PetPresenter implements PetContract.Presenter, OnLoadPet {

    PetContract.View view;
    ManagePet managePet;

    public PetPresenter(PetContract.View view) {
        this.view = view;
        managePet = new ManagePet(this);
    }

    @Override
    public void loadPet(String name, String type, Likes likes) {
        view.setPetInfo(name, type, likes);
    }

    @Override
    public void getPetInformation(String userID) {
        managePet.getPetInfo(userID);
    }
}
