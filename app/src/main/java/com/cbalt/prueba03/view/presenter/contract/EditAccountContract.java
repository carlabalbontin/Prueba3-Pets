package com.cbalt.prueba03.view.presenter.contract;

import com.cbalt.prueba03.models.Likes;

public interface EditAccountContract {

    interface View {
        void error(String error);
        void success();
        void setUserName(String name);
        void setPetInfo(String name, String type, Likes likes);
    }

    interface Presenter {
        void processForm(String userName, String petName, String photo, String type, Likes likes);
        void findUserName();
        void petInformation(String userID);
    }
}
