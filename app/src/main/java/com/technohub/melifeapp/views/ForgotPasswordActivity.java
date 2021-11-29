package com.technohub.melifeapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.technohub.melifeapp.Interfaces.IForgot;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.presenter.ForgotPwdPresenter;

public class ForgotPasswordActivity extends AppCompatActivity implements IForgot.View {

    Button forgotBtn;
    EditText forgotEditTxtEmail;
    ForgotPwdPresenter fogotPwdPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();

        fogotPwdPresenter=new ForgotPwdPresenter(this);
        fogotPwdPresenter.created();

    }
    @Override
    public void init() {
         forgotBtn=findViewById(R.id.forgotBtn);
         forgotEditTxtEmail=findViewById(R.id.forgotEditTxtEmail);
    }

    @Override
    public void ForgotToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initClicks() {
                forgotBtn.setOnClickListener(
                        new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(validate())
                        fogotPwdPresenter.forgotButtonClick(forgotEditTxtEmail.getText().toString());
                    }
                });
    }
    public boolean validate() {
        boolean valid = true;
        String email = forgotEditTxtEmail.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            forgotEditTxtEmail.setError("Enter a valid email address");
            valid = false;
        }
        return valid;
    }
    @Override
    public void showLoading() {

//        forgotSpinKit.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        fogotPwdPresenter.backPressed();
        super.onBackPressed();
    }

    @Override
    public void hideLoading() {

//        forgotSpinKit.setVisibility(View.GONE);
    }
    @Override
    public void goToLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
