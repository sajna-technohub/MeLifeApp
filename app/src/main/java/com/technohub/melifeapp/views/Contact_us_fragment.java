package com.technohub.melifeapp.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.technohub.melifeapp.Interfaces.IFaq;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.FaqResponse;
import com.technohub.melifeapp.models.StreamFinderRequest;
import com.technohub.melifeapp.presenter.FaqPresenter;


public class Contact_us_fragment extends Fragment implements IFaq.View {
View v;
TextView txt_contact_call,txt_contact_mail,txt_contact_facebook,txt_contact_twitter,txt_contact_youtube,txt_contact_playstore,txt_contact_insta,txt_contact_github;
    FaqPresenter faqPresenter;
    ImageView profileBtnBack;
    FaqResponse faqResponse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_contact_us_fragment, container, false);
        v.setBackgroundColor(Color.WHITE);

        faqPresenter=new FaqPresenter(this);
        faqPresenter.created();
        faqPresenter.contact_us_Data();

        return v;
    }

    @Override
    public void init() {

        txt_contact_github=v.findViewById(R.id.txt_contact_github);
        txt_contact_call=v.findViewById(R.id.txt_contact_call);
        txt_contact_mail=v.findViewById(R.id.txt_contact_mail);
        txt_contact_facebook=v.findViewById(R.id.txt_contact_facebook);
        txt_contact_twitter=v.findViewById(R.id.txt_contact_twitter);
        txt_contact_youtube=v.findViewById(R.id.txt_contact_youtube);
        txt_contact_playstore=v.findViewById(R.id.txt_contact_playstore);
        txt_contact_insta=v.findViewById(R.id.txt_contact_insta);
        profileBtnBack=v.findViewById(R.id.profileBtnBack);

    }

    @Override
    public void initClicks() {
        profileBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),DashBoardActivity.class));
            }
        });
        txt_contact_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+faqResponse.getData().getContact_number()));
                startActivity(intent);
            }
        });
        txt_contact_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LoadUrl(faqResponse.getData().getFacebook_link());
            }
        });
        txt_contact_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(faqResponse.getData().getEmail()));
                startActivity(Intent.createChooser(emailIntent, "Connect with us"));
            }
        });
        txt_contact_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadUrl(faqResponse.getData().getFacebook_link());
            }
        });
        txt_contact_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadUrl("https://twitter.com/explore");
            }
        });
        txt_contact_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadUrl(faqResponse.getData().getYoutube_link());
            }
        });
        txt_contact_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadUrl(faqResponse.getData().getInstagram());
            }
        });
        txt_contact_playstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent
                        .FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    void LoadUrl(String url)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        Log.e("url",url);
        Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    @Override
    public void LoadFaq(FaqResponse faqResponse) {
        this.faqResponse=faqResponse;

    }
}
