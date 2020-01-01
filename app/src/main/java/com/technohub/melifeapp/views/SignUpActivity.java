package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.technohub.melifeapp.Interfaces.IRegister;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.ErrorModel;
import com.technohub.melifeapp.presenter.RegisterPresenter;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements IRegister.View{
    EditText registerEditTxtName,registerEditTxtPincode, registerEditTxtEmail, registerEditTxtMobile;
    TextView registerTxtNameError, registerTxtEmailError, registerTxtPinError,registerTxtMobileError;
    Button registerBtnRegister;
    SpinKitView registerSpinKit;
    RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        registerPresenter = new RegisterPresenter(this);
        registerPresenter.created();

    }

    @Override
    public void init() {
        registerEditTxtName = (EditText) findViewById(R.id.registerEditTxtName);
        registerEditTxtEmail = (EditText) findViewById(R.id.registerEditTxtEmail);
        registerEditTxtMobile = (EditText) findViewById(R.id.registerEditTxtMobile);
        registerEditTxtPincode = (EditText) findViewById(R.id.registerEditTxtPincode);
        registerTxtNameError = (TextView) findViewById(R.id.registerTxtNameError);
        registerTxtEmailError = (TextView) findViewById(R.id.registerTxtEmailError);
        registerTxtMobileError = (TextView) findViewById(R.id.registerTxtMobileError);
        registerTxtPinError = (TextView) findViewById(R.id.registerTxtpinError);
        registerBtnRegister = (Button) findViewById(R.id.registerBtnRegister);
        registerSpinKit = (SpinKitView) findViewById(R.id.registerSpinKit);
    }

    @Override
    public void initClicks() {
        registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        registerPresenter.registerButtonClick(registerEditTxtName.getText().toString().trim(),
                        registerEditTxtEmail.getText().toString().trim(), registerEditTxtMobile.getText().toString().trim(),registerEditTxtPincode.getText().toString().trim());
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {

        registerSpinKit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        registerSpinKit.setVisibility(View.GONE);
    }
    @Override
    public void showErrorMessages(List<ErrorModel> errorList) {

        for (int i=0; i<errorList.size(); i++) {

            if (errorList.get(i).getParam().equals("name")) {

                registerTxtNameError.setText(errorList.get(i).getMessage());
                registerTxtNameError.setVisibility(View.VISIBLE);
            } else if(errorList.get(i).getParam().equals("email")) {

                registerTxtEmailError.setText(errorList.get(i).getMessage());
                registerTxtEmailError.setVisibility(View.VISIBLE);
            } else if(errorList.get(i).getParam().equals("mobile")) {

                registerTxtMobileError.setText(errorList.get(i).getMessage());
                registerTxtMobileError.setVisibility(View.VISIBLE);
            }
            else if(errorList.get(i).getParam().equals("pincode")) {

                registerTxtPinError.setText(errorList.get(i).getMessage());
                registerTxtPinError.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void clearErrors() {

        registerTxtNameError.setVisibility(View.GONE);
        registerTxtEmailError.setVisibility(View.GONE);
        registerTxtMobileError.setVisibility(View.GONE);
        registerTxtPinError.setVisibility(View.GONE);
    }
    @Override
    public void goToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToLoginActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        registerPresenter.backPressed();
        super.onBackPressed();
    }


}
