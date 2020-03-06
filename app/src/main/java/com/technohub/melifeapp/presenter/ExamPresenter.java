package com.technohub.melifeapp.presenter;



import android.util.Log;

import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.models.LoadQuestionRequest;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamPresenter implements IExam.Presenter {

    private IExam.View view;
    public ExamPresenter(IExam.View view) {
        this.view = view;

    }

    @Override
    public void created() {
        Log.e("load","exam presenter");
        view.init();
        view.initClicks();
        getQuestionsFromServer();
    }

    @Override
    public void getQuestionsFromServer() {
        view.showLoading();
        Log.e("in exam presenter","get method");
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        LoadQuestionRequest loadQuestionRequest=new LoadQuestionRequest();
        Call<LoadQuestionResponse> call = retrofitApi.loadquestions(loadQuestionRequest);
        call.enqueue(new Callback<LoadQuestionResponse>() {
            @Override
            public void onResponse(Call<LoadQuestionResponse> call, Response<LoadQuestionResponse> response) {
                LoadQuestionResponse loadQuestionResponse = response.body();
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("in exam presenter","got response");
                    if(response.body().getMessage().equals("success".trim()))
                    {
                        Log.e("Responseloadqns", loadQuestionResponse.getMessage());

                          view.hideLoading();
//                        view.goToLoginActivity();
                    }
                    else
                    {
                        Log.e("Responseloadqns", loadQuestionResponse.getMessage());

                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoadQuestionResponse> call, Throwable t)
            {
                view.hideLoading();

            }
        });
    }
}
