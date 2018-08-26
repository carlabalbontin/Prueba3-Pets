package com.cbalt.prueba03.view.presenter.contract;

public interface AccountContract {

    interface View {
        void setUserInfo(String name, String email);
        void setPetInfo(String name, String type, String likes);

    }

    interface Presenter {
        void getUserInfo();
        void getPetInfo(String userID);
    }
}
