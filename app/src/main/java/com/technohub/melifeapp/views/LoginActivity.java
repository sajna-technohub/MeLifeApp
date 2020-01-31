package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import com.technohub.melifeapp.Interfaces.ILogin;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.ErrorModel;
import com.technohub.melifeapp.presenter.LoginPresenter;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements ILogin.View {


    EditText loginEditTxtEmail,loginEditTxtPassword;
    Button loginBtnLogin;
    TextView loginTxtEmailError,loginTxtPasswordError,loginTxtForgotPassword,loginBtnRegister;
    private SpinKitView loginSpinKit;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        loginPresenter = new LoginPresenter(this);
        loginPresenter.created();
    }
    @Override
    public void LoginFailed(){
        Toast.makeText(this, "Bad Credentials,Login Failed!!!!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void LoginSuccessful(){
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
//        startService(new Intent(getBaseContext(), MyService.class));
    }
    @Override
    public void init() {
        loginSpinKit = (SpinKitView) findViewById(R.id.loginSpinKit);
        loginEditTxtEmail = (EditText) findViewById(R.id.loginEdtTextEmail);
        loginEditTxtPassword = (EditText) findViewById(R.id.loginEdtTextPassword);
        loginTxtEmailError = (TextView) findViewById(R.id.loginTxtEmailError);
        loginTxtPasswordError = (TextView) findViewById(R.id.loginTxtPasswordError);
        loginBtnLogin = (Button) findViewById(R.id.loginBtnSignin);
        loginBtnRegister = (TextView) findViewById(R.id.loginBtnSignup);
        loginTxtForgotPassword=(TextView)findViewById(R.id.loginTxtForgotPassword);
    }

    @Override
    public void initClicks() {

        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    loginPresenter.loginButtonClick(loginEditTxtEmail.getText().toString().trim(), loginEditTxtPassword.getText().toString().trim());
                }
            }
        });

        loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginPresenter.registerButtonClick();
            }
        });
        loginTxtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.forgotButtonClick();
            }
        });
    }
    public boolean validate() {
        boolean valid = true;
        String email = loginEditTxtEmail.getText().toString();
        String password = loginEditTxtPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEditTxtEmail.setError("Enter a valid email address");
            valid = false;
        } else {
            loginEditTxtEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            loginEditTxtPassword.setError("Password should be minimum 6 charecters");
            valid = false;
        } else {
            loginEditTxtPassword.setError(null);
        }
        return valid;
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

    @Override
    public void goToProfileEdit() {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.loginlayout, new ProfileFragment())
                        .commit();

    }

    @Override
    public void goToForgotActivity() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
        finish();
    }



}
