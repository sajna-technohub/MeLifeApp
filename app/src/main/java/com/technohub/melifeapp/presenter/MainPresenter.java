package com.technohub.melifeapp.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.technohub.melifeapp.Interfaces.IMain;

public class MainPresenter implements IMain.Presenter{
    private IMain.View view;
    private  String userToken;

    public MainPresenter(IMain.View view) {
        this.view = view;
    }
    @Override
    public void created() {

         view.init();
         view.showLoading();
         view.showLoading();
         this.getUserToken();
        /**For first page**/

    }


    @Override
    public void getUserToken() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        userToken = sharedPreferences.getString("apiToken", "");
    }

}
