package com.cbalt.prueba03.view.presenter.contract;

public interface AuthUserContract {

    interface View {
        void logInUser();
        void loginSuccessful();
        void firstTimeUser();
    }

    interface Presenter {
        void userExists();
        void saveNewUser();
    }
}
