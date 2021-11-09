package com.technohub.melifeapp.Interfaces;
import android.content.Context;

import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.User;

public interface IProfile {

    interface View {

        void init();

        void showLoading();

        void hideLoading();

        void UpdateMessage(String msg);

        void goToDashboard();

        void initClicks();

        void alert();

        void setProfile(ProfileResponse profile);

        Context getContext();

    }

    interface Presenter {

        void created();

//        void UpdateButtonClick(User user);

        void getProfile(String userid);
    }

    }
