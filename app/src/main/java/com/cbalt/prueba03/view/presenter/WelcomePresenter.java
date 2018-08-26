package com.cbalt.prueba03.view.presenter;


import com.cbalt.prueba03.data.ManagePet;
import com.cbalt.prueba03.data.ManageUser;
import com.cbalt.prueba03.models.Likes;
import com.cbalt.prueba03.models.User;
import com.cbalt.prueba03.view.presenter.contract.WelcomeContract;

public class WelcomePresenter implements WelcomeContract.Presenter {

    WelcomeContract.View view;

    public WelcomePresenter(WelcomeContract.View view) {
        this.view = view;
    }

    @Override
    public void processPetForm(String userName, String petName, String photo, String type, Likes likes) {
        if(userName.trim().length() > 0){
            if(petName.trim().length() > 0){

                if (type.trim().length() > 0){
                        new ManageUser().editUserName(userName);
                        new ManagePet().newPet(petName, photo, type, likes);
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

    @Override
    public void findUserName() {
        User user = new ManageUser().getUser();
        if(user.getName() != null && user.getName().trim().length() > 0){
            view.setUserName(user.getName());
        }
    }

    @Override
    public void petInformation(String userID) {

    }
}
