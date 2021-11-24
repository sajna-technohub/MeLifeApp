package com.technohub.melifeapp.views;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.codesgood.views.JustifiedTextView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.skydoves.elasticviews.ElasticButton;
import com.technohub.melifeapp.Interfaces.IInstruction;
import com.technohub.melifeapp.R;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.presenter.InstructionPresenter;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class InstructionFragment extends Fragment implements IInstruction.View {

        View v;
        JustifiedTextView    instructionTxtDesc;
        CheckBox             instructionCheckBox;
        ElasticButton        instructionBtnProceed;
        LoadQuestionResponse loadQuestionResponse;
        SpinKitView          instructionSpinKit;
        InstructionPresenter instructionPresenter;
        String               exam_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            loadQuestionResponse = (LoadQuestionResponse) getArguments().getParcelable("examResponse");
            exam_id              =  (String) getArguments().getString("exam_id");

                Log.e("instruction frag e_id",exam_id);
                Log.e("instruction frag s_id",loadQuestionResponse.getExamquestionData().get(0).getSection_id()+loadQuestionResponse.getExamquestionData().get(0).getDescription());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_instruction, container, false);

        v.setBackgroundColor(Color.WHITE);

        instructionPresenter=new InstructionPresenter(this);
        instructionPresenter.created();

        instructionTxtDesc.setText(Html.fromHtml(loadQuestionResponse.getExamquestionData().get(0).getDescription()));
       return v;
    }


    @Override
    public void initClicks() {

        instructionBtnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(instructionCheckBox.isChecked())
                {
                    instructionPresenter.proceedButtonClick(loadQuestionResponse.getExamquestionData().get(0).getSection_id(), exam_id);
                }
                else
                {
                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Alert!!")
                            .setContentText("Please Accept the Terms & Conditions ")
                            .setConfirmText("Ok!")
                            .setConfirmClickListener(sDialog -> sDialog.dismissWithAnimation())
                            .show();

                }
            }
        });


    }

    @Override
    public void init() {
        instructionTxtDesc    = v.findViewById(R.id.instructionTxtdescription);
        instructionCheckBox   = v.findViewById(R.id.instructionCheck);
        instructionBtnProceed = v.findViewById(R.id.instructionBtnProceed);
        instructionSpinKit    = v.findViewById(R.id.instructionSpinKit);
    }

    @Override
    public void loadExamFragment()
    {
        ExamFragment examFragment=new ExamFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("examResponse", loadQuestionResponse);
        bundle.putString("exam_id",exam_id);
        examFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.testcatlayout, examFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showLoading() {

            instructionSpinKit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

             instructionSpinKit.setVisibility(View.GONE);
    }
}
