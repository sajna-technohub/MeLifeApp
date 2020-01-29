package com.technohub.melifeapp.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.technohub.melifeapp.Interfaces.IProfile;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.ProfileModel;
import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.presenter.ProfilePresenter;

import java.util.Calendar;


public class ProfileFragment extends Fragment implements IProfile.View {

    View v;
    TextView profileTxtDob,profileTxtName,profileTxtmobile,profileTxtemail;
    TextView profileTxtstate,profileTxtCountry,profileTxtpincode,profileTxtQuali;
    TextView ProfileTxtEditqualification,profileContactEdit;
    ImageView profilenameEdit;
    CircularImageView profileImgphoto;
    Button profileBtnSave;
    ProfilePresenter profilePresenter;
    int mYear,mMonth,mDay;
    String completion_status,userid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        v=inflater.inflate(R.layout.fragment_profile, container, false);
        v.setBackgroundColor(Color.WHITE);

        completion_status=new LoginResponse().getSharedPreferences(getContext(),"completion_status");
        userid=new LoginResponse().getSharedPreferences(getContext(),"userid");

        Log.e("sessions profile",userid+"  "+completion_status);

        User user=new User();
        user.setUser_id(Integer.parseInt(userid));
        user.setCompletion_status(completion_status);
        user.setDeviceType("1");
        user.setDeviceToken("dfsdfs");

        profilePresenter = new ProfilePresenter(this,user);
        profilePresenter.created();

        return v;
    }

    @Override
    public void init()
    {
                profileTxtName=v.findViewById(R.id.profileTxtName);
                profileTxtDob=v.findViewById(R.id.profileTxtDob);
                profileTxtmobile=v.findViewById(R.id.profileTxtmobile);
                profileTxtemail=v.findViewById(R.id.profileTxtemail);
                profileTxtstate=v.findViewById(R.id.profileTxtstate);
                profileTxtCountry=v.findViewById(R.id.profileTxtCountry);
                profileTxtpincode=v.findViewById(R.id.profileTxtpincode);
                profileTxtQuali=v.findViewById(R.id.profileTxtQuali);
                profilenameEdit=v.findViewById(R.id.profilenameEdit);
                ProfileTxtEditqualification=v.findViewById(R.id.ProfileTxtEditqualification);
                profileContactEdit=v.findViewById(R.id.profileContactEdit);
                profileBtnSave=v.findViewById(R.id.profileBtnSave);
                profileImgphoto=v.findViewById(R.id.profileImgphoto);
    }

    @Override
    public void initClicks() {


                    profileBtnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            profilePresenter.UpdateButtonClick();
                        }
                    });


                    profileTxtDob.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Get Current Date
                            final Calendar c = Calendar.getInstance();
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                                    new DatePickerDialog.OnDateSetListener() {

                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {

                                            profileTxtDob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                        }
                                    }, mYear, mMonth, mDay);
                            datePickerDialog.show();
                        }
                    });


                    profileContactEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        profileTxtmobile.setEnabled(true);
                        profileTxtemail.setEnabled(true);
                        profileTxtstate.setEnabled(true);
                        profileTxtCountry.setEnabled(true);
                        profileTxtpincode.setEnabled(true);

                        }
                    });



                    profilenameEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            profileTxtName.setEnabled(true);
                        }
                    });



                     profileImgphoto.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Toast.makeText(getContext(), "Choose Photo", Toast.LENGTH_SHORT).show();
                         }
                     });




              ProfileTxtEditqualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileTxtQuali.setEnabled(true);
            }
        });


    }

    @Override
    public void setProfile(ProfileResponse profile) {

        profileTxtName.setText(profile.getData().get(0).getName());
        profileTxtemail.setText(profile.getData().get(0).getEmail());
        profileTxtpincode.setText(profile.getData().get(0).getPincode());
        profileTxtDob.setText(profile.getData().get(0).getDob());
        profileTxtCountry.setText(profile.getData().get(0).getCountry());

        if(profile.getData().get(0).getState()==null)
        {
            profileTxtstate.setText("");
        }
        else
        {
            profileTxtstate.setText(profile.getData().get(0).getState());
        }
        if(profile.getData().get(0).getCountry()==null) {

            profileTxtCountry.setText("");
        }
        else
        {
            profileTxtCountry.setText(profile.getData().get(0).getCountry());
        }
        profileTxtmobile.setText(profile.getData().get(0).getMobile_no());
        profileTxtQuali.setText(profile.getData().get(0).getQualification());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void UpdateMessage() {

    }

    @Override
    public void goToDashboard() {

    }

    @Override
    public void showLoading() {

    }
}
