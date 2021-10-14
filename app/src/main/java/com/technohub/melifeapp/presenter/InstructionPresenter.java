package com.technohub.melifeapp.presenter;

import android.util.Log;

import com.technohub.melifeapp.Interfaces.IForgot;
import com.technohub.melifeapp.Interfaces.IInstruction;
import com.technohub.melifeapp.models.InstructionRequest;
import com.technohub.melifeapp.models.InstructionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.SignUpModel;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstructionPresenter implements IInstruction.Presenter {
    private IInstruction.View view;

    public InstructionPresenter(IInstruction.View view) {
        this.view = view;
    }

    @Override
    public void created() {
        view.init();
        view.initClicks();
    }

    @Override
    public void proceedButtonClick(String sec_id,String exam_id )
    {
        view.showLoading();
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        InstructionRequest instructionRequest=new InstructionRequest();
             Log.e("ins presenter sid eid",sec_id+" "+exam_id);
        instructionRequest.setSec_ids(sec_id);
        instructionRequest.setExam_id(exam_id);
        instructionRequest.setDeviceType("1");
        instructionRequest.setDeviceToken("hgcf");

        Call<InstructionResponse> call = retrofitApi.saveinstructionpopupstatus(instructionRequest);

        call.enqueue(new Callback<InstructionResponse>() {
            @Override
            public void onResponse(Call<InstructionResponse> call, Response<InstructionResponse> response) {
                InstructionResponse instructionResponse = response.body();
                if (response.isSuccessful() && response.body() != null)
                {
                    if(response.body().getStatus().equals("success".trim()))
                    {
                        Log.e("Response instruction", instructionResponse.getStatus());

                        view.hideLoading();

                    }
//
                    view.loadExamFragment();
                }
                else
                {
                    Log.e("Response instruction", "null");
                    view.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<InstructionResponse> call, Throwable t)
            {
                view.hideLoading();
            }
        });
    }
}
