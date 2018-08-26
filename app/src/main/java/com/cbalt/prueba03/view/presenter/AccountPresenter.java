package com.cbalt.prueba03.view.presenter;

import com.cbalt.prueba03.data.ManagePet;
import com.cbalt.prueba03.data.ManageUser;
import com.cbalt.prueba03.models.Likes;
import com.cbalt.prueba03.view.listener.OnLoadPet;
import com.cbalt.prueba03.view.listener.OnLoadUser;
import com.cbalt.prueba03.view.presenter.contract.AccountContract;

public class AccountPresenter implements AccountContract.Presenter, OnLoadPet, OnLoadUser {

    AccountContract.View view;
    ManagePet managePet;
    ManageUser manageUser;

    public AccountPresenter(AccountContract.View view) {
        this.view = view;
        managePet = new ManagePet(this);
        manageUser = new ManageUser(this);
    }

    @Override
    public void getUserInfo() {
        manageUser.getUserInfo();
    }

    @Override
    public void getPetInfo(String userID) {
        managePet.getPetInfo(userID);
    }


    private String likesToString(Likes likesObj){
        StringBuilder s = new StringBuilder(100);
        if(likesObj.isFood()){
            s.append("food");
            if(likesObj.isPlay() || likesObj.isCuddles()){
                s.append("/ ");
            }
        }
        if(likesObj.isPlay()){
            s.append("play");
            if(likesObj.isCuddles()){
                s.append("/ ");
            }
        }
        if (likesObj.isCuddles()){
            s.append("cuddles");
        }

        return s.toString();
    }

    @Override
    public void loadPet(String name, String type, Likes likes) {
        String likesString = likesToString(likes);
        view.setPetInfo(name, type, likesString);
    }

    @Override
    public void loadUser(String name, String email, String uid) {
        view.setUserInfo(name, email);
    }
}
