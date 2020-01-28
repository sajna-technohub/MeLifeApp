package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.technohub.melifeapp.Interfaces.IProfile;
import com.technohub.melifeapp.R;


public class ProfileFragment extends Fragment implements IProfile.View {

    View v;
    TextView profileTxtDob,profileTxtName,profileTxtmobile,profileTxtemail;
    TextView profileTxtstate,profileTxtCountry,profileTxtpincode,profileTxtQuali;
    TextView ProfileTxtEditqualification,profileContactEdit;
    ImageView profilepictureEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_profile, container, false);
        v.setBackgroundColor(Color.WHITE);

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
                profilepictureEdit=v.findViewById(R.id.profilepictureEdit);
                ProfileTxtEditqualification=v.findViewById(R.id.ProfileTxtEditqualification);
                profileContactEdit=v.findViewById(R.id.profileContactEdit);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
