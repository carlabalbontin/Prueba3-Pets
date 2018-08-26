package com.cbalt.prueba03.view.presenter.contract;

import com.cbalt.prueba03.models.Likes;

public interface WelcomeContract {

    interface View {
        void error(String error);
        void success();
        void setUserName(String name);
    }

    interface Presenter {
        void processPetForm(String userName, String petName, String photo, String type, Likes likes);
        void findUserName();
        void petInformation(String userID);
    }
}
