package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.technohub.melifeapp.Interfaces.IMain;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.classes.Constants;
import com.technohub.melifeapp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IMain.View {
    private MainPresenter mainPresenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        mainPresenter.created();
    }

    @Override
    public void init() {
        progressBar = (SpinKitView) findViewById(R.id.mainSpinKit);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void tokenError() {
        Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishApp() {
         this.finish();
    }

}
