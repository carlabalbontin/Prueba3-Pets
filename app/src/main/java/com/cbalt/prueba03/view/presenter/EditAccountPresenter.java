package com.cbalt.prueba03.view.presenter;

import android.util.Log;

import com.cbalt.prueba03.R;
import com.cbalt.prueba03.data.ManagePet;
import com.cbalt.prueba03.data.ManageUser;
import com.cbalt.prueba03.models.Likes;
import com.cbalt.prueba03.view.listener.OnLoadPet;
import com.cbalt.prueba03.view.listener.OnLoadUser;
import com.cbalt.prueba03.view.presenter.contract.EditAccountContract;

public class EditAccountPresenter implements EditAccountContract.Presenter, OnLoadPet, OnLoadUser {

    EditAccountContract.View view;
    ManagePet managePet;
    ManageUser manageUser;
    String[] petTypeArray;

    public EditAccountPresenter(EditAccountContract.View view, String[] petTypeArray) {
        this.view = view;
        this.petTypeArray = petTypeArray;
        managePet = new ManagePet(this);
        manageUser = new ManageUser(this);
    }

    @Override
    public void processForm(String userName, String petName, String photo, String type, Likes likes) {
        if(userName.trim().length() > 0){
            if(petName.trim().length() > 0){

                if (type.trim().length() > 0){
                    manageUser.editUserName(userName);
                    managePet.newPet(petName, photo, type, likes);
                    view.success();

                } else {
                    view.error("Must include your pet's type");
                }

            } else {
                view.error("Must include your pet's name");
            }

        } else {
            view.error("Must include your name");
        }
    }

    private int getTypePosition(String petType){
        int position = 0;
        int index = 0;
        if(petTypeArray != null){
            for(String myType: petTypeArray){
                if(myType.toLowerCase().equals(petType.toLowerCase())){
                    position = index;
                }
                index++;
            }
        } else {
            Log.d("TYPE", "--------------- array is null --------------");
        }
        return position;
    }

    @Override
    public void findUserName() {
        manageUser.getUserInfo();
    }

    @Override
    public void petInformation(String userID) {
        managePet.getPetInfo(userID);
    }

    @Override
    public void loadPet(String name, String type, Likes likes) {

        view.setPetInfo(name, getTypePosition(type), likes);
    }

    @Override
    public void loadUser(String name, String email, String uid) {
        view.setUserName(name);
    }
}
