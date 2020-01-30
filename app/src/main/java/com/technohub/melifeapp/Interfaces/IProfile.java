package com.technohub.melifeapp.Interfaces;
import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.User;

public interface IProfile {

    interface View {

        void init();

        void showLoading();

        void hideLoading();

        void UpdateMessage();

        void goToDashboard();

        void initClicks();

        void setProfile(ProfileResponse profile);

    }

    interface Presenter {

        void created();

        void UpdateButtonClick(User user);

        void getProfile();
    }

    }
