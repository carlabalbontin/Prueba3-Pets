package com.cbalt.prueba03.view.presenter.contract;

import com.cbalt.prueba03.models.Likes;

public interface PetContract {

    interface View {
        void setPetInfo(String name, String type, Likes likes);
    }

    interface Presenter {
        void getPetInformation(String userID);
    }
}
