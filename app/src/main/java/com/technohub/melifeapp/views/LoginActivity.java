package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.technohub.melifeapp.Interfaces.ILogin;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.ErrorModel;
import com.technohub.melifeapp.presenter.LoginPresenter;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements ILogin.View {


    EditText loginEditTxtEmail,loginEditTxtPassword;
    Button loginBtnRegister,loginBtnLogin;
    TextView loginTxtEmailError,loginTxtPasswordError;
    private ProgressBar loginSpinKit;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);
        loginPresenter.created();
    }
    @Override
    protected void onStart() {
        super.onStart();
//        startService(new Intent(getBaseContext(), MyService.class));
    };
    @Override
    public void init() {
        loginSpinKit = (SpinKitView) findViewById(R.id.loginSpinKit);
        DoubleBounce doubleBounce = new DoubleBounce();
        loginSpinKit.setIndeterminateDrawable(doubleBounce);

        loginEditTxtEmail = (EditText) findViewById(R.id.loginEdtTextEmail);
        loginEditTxtPassword = (EditText) findViewById(R.id.loginEdtTextPassword);
        loginTxtEmailError = (TextView) findViewById(R.id.loginTxtEmailError);
        loginTxtPasswordError = (TextView) findViewById(R.id.loginTxtPasswordError);
//        loginTxtGitHub = (TextView) findViewById(R.id.loginTxtGitHub);
//        loginTxtPrivacyPolicy = (TextView) findViewById(R.id.loginTxtPrivacyPolicy);
        loginBtnLogin = (Button) findViewById(R.id.loginBtnSignin);
        loginBtnRegister = (Button) findViewById(R.id.loginBtnSignup);
    }
    @Override
    public void initClicks() {

        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginPresenter.loginButtonClick(loginEditTxtEmail.getText().toString().trim(), loginEditTxtPassword.getText().toString().trim());
            }
        });

        loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginPresenter.registerButtonClick();
            }
        });

//        loginTxtGitHub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                loginPresenter.githubLinkClick();
//            }
//        });

//        loginTxtPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                loginPresenter.privacyPolicyLinkClick();
//            }
//        });
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void goToRegisterActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoading() {

        loginSpinKit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        loginSpinKit.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessages(List<ErrorModel> errorList) {

        for (int i=0; i<errorList.size(); i++) {

            if (errorList.get(i).getParam().equals("email")) {

                loginTxtEmailError.setText(errorList.get(i).getMessage());
                loginTxtEmailError.setVisibility(View.VISIBLE);
            } else if(errorList.get(i).getParam().equals("password")) {

                loginTxtPasswordError.setText(errorList.get(i).getMessage());
                loginTxtPasswordError.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void clearErrors() {
        loginTxtEmailError.setVisibility(View.GONE);
        loginTxtPasswordError.setVisibility(View.GONE);
    }

    @Override
    public void goToMainActivity() {

        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);
        finish();
    }


}
