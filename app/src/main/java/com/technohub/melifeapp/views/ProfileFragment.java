package com.technohub.melifeapp.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.technohub.melifeapp.Interfaces.IProfile;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.Country;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.ProfileModel;
import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.Qualification;
import com.technohub.melifeapp.models.State;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.presenter.ProfilePresenter;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment implements IProfile.View {

    View v;
    TextView profileTxtDob,profileTxtName,profileTxtmobile,profileTxtemail,profileTxtpincode;
    TextView ProfileTxtEditqualification,profileContactEdit;
    Spinner profileSprstate,profileSprCountry,profileSprQuali;
    ImageView profilenameEdit;
    CircularImageView profileImgphoto;
    Button profileBtnSave;
    ProfilePresenter profilePresenter;
    int mYear,mMonth,mDay;
    User user=new User();
    String completion_status,userid;
    String strcountry,strstate,strqualification;
    SpinKitView profProgressspin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        v=inflater.inflate(R.layout.fragment_profile, container, false);
        v.setBackgroundColor(Color.WHITE);

        completion_status=new LoginResponse().getSharedPreferences(getContext(),"completion_status");
        userid=new LoginResponse().getSharedPreferences(getContext(),"userid");

        Log.e("Sessions Profile",userid+"  "+completion_status);

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
                  profileSprstate=v.findViewById(R.id.profileSprstate);
                  profileSprCountry=v.findViewById(R.id.profileSprCountry);
                profileTxtpincode=v.findViewById(R.id.profileTxtpincode);
                 profileSprQuali=v.findViewById(R.id.profileSprQuali);
                profilenameEdit=v.findViewById(R.id.profilenameEdit);
                ProfileTxtEditqualification=v.findViewById(R.id.ProfileTxtEditqualification);
                profileContactEdit=v.findViewById(R.id.profileContactEdit);
                profileBtnSave=v.findViewById(R.id.profileBtnSave);
                profileImgphoto=v.findViewById(R.id.profileImgphoto);
                 profProgressspin=v.findViewById(R.id.profileSpinKit);
                profileSprCountry.setSelection(0);
    }

public boolean validate() {

    boolean valid = true;
    String name = profileTxtName.getText().toString();
    String email = profileTxtemail.getText().toString();
    String mobile = profileTxtmobile.getText().toString();
    String pincode = profileTxtpincode.getText().toString();

    if (name.isEmpty() || name.length() < 4) {
        profileTxtName.setError("Name should be minimum 3 charecters");
        valid = false;
    } else {
        profileTxtName.setError(null);
    }

    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        profileTxtemail.setError("Enter a valid email address");
        valid = false;
    } else {
        profileTxtemail.setError(null);
    }


    if (mobile.isEmpty() || !mobile.matches("^[1-9][0-9]{9}$") ) {
        profileTxtmobile.setError("Enter a valid mobile number");
        valid = false;
    } else {
        profileTxtmobile.setError(null);
    }

    if (pincode.isEmpty() || !pincode.matches("^[1-9][0-9]{5}$") ) {
        profileTxtpincode.setError("Enter a valid pin");
        valid = false;
    } else {
        profileTxtpincode.setError(null);
    }
    if (strcountry.isEmpty())
    {
        valid = false;
    } if (strstate.isEmpty())
    {
        valid = false;
    }if (strqualification.isEmpty())
    {
        valid = false;
    }
    return valid;

}
    @Override
    public void initClicks() {

        profileSprCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                             strcountry=profileSprCountry.getItemAtPosition(position).toString();
                             Toast.makeText(getContext(),strcountry,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

        profileSprstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strstate=profileSprstate.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), strstate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        profileSprQuali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Qualification qualification = (Qualification) parent.getSelectedItem();
//                Toast.makeText(getContext(), "state ID: "+qualification.getQualification_id()+",  state Name : "+qualification.getQualification(), Toast.LENGTH_SHORT).show();
                strqualification=profileSprQuali.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),strqualification,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        profileBtnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        if(validate()) {

                            user.setName(profileTxtName.getText().toString());
                            user.setEmail(profileTxtemail.getText().toString());
                            user.setMobno(profileTxtmobile.getText().toString());
                            user.setPincode(profileTxtpincode.getText().toString());
                            user.setDate(profileTxtDob.getText().toString());
                            user.setUser_id(Integer.parseInt(userid));
                            user.setDeviceType("1");
                            user.setDeviceToken("dfsdfs");
                            user.setCountry("1");
                            user.setState(strstate);
                            user.setQualification(strqualification);


                            profilePresenter.UpdateButtonClick(user);

                        }
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
                        profileTxtpincode.setEnabled(true);

                        }
                    });


                    profileTxtemail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            Toast.makeText(getContext(), "You cant edit email", Toast.LENGTH_SHORT).show();
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
                             Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                             startActivityForResult(i, 100);
                         }
                     });




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //the image URI
            Uri selectedImage = data.getData();

            //calling the upload file method after choosing the file
            File file = new File(getRealPathFromURI(selectedImage));
            profilePresenter.uploadFile(file,selectedImage, "My Image",getContext());
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    @Override
    public void setProfile(ProfileResponse profile) {

        profileTxtName.setText(profile.getData().get(0).getName());
        profileTxtemail.setText(profile.getData().get(0).getEmail());
        profileTxtpincode.setText(profile.getData().get(0).getPincode());
        profileTxtDob.setText(profile.getData().get(0).getDob());


            List<String> countrylist=new ArrayList<>();
            List<String> statelist=new ArrayList<>();
            List<String> qualificatiobnlist=new ArrayList<>();

            for (int i = 0; i < profile.getCountry().size(); i++){
            countrylist.add(profile.getCountry().get(i).getCountry_name());
        }
        for (int i = 0; i < profile.getState().size(); i++){
            statelist.add(profile.getState().get(i).getState_name());
        }
        for (int i = 0; i < profile.getQualification().size(); i++){
            qualificatiobnlist.add(profile.getQualification().get(i).getQualification());
        }

        ArrayAdapter<String> countryArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,countrylist);
        countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        profileSprCountry.setAdapter(countryArrayAdapter);

        ArrayAdapter<String> stateArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,statelist);
        stateArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        profileSprstate.setAdapter(stateArrayAdapter);


        ArrayAdapter<String> qualificationArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,qualificatiobnlist);
        qualificationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        profileSprQuali.setAdapter(qualificationArrayAdapter);
        profileTxtmobile.setText(profile.getData().get(0).getMobile_no());

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
        profProgressspin.setVisibility(View.GONE);
    }

    @Override
    public void UpdateMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToDashboard() {

        startActivity(new Intent(getContext(),DashBoardActivity.class));

    }

    @Override
    public void showLoading() {
        profProgressspin.setVisibility(View.VISIBLE);
    }
}
