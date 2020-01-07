package com.technohub.melifeapp.presenter;

import android.util.Log;

import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.Interfaces.ILogin;
import com.technohub.melifeapp.models.JsonModel;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.QuestionModel;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.util.List;

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

        view.init();
        view.initClicks();
    }

    @Override
    public void getQuestionsFromServer() {
        view.showLoading();
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        Call<QuestionModel> call = retrofitApi.getQuestions();
        call.enqueue(new Callback<QuestionModel>() {
            @Override
            public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {

                QuestionModel questionModel = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Qname",questionModel.getQname());
                    Log.e("Qno",questionModel.getQno()+"");
                    Log.e("Qid",questionModel.getQid()+"");

                } else if (response.errorBody() != null) {

                    try {
                        view.hideLoading();
                        Log.e("Qname","failed");

                    } catch (Exception e) {}
                }
            }

            @Override
            public void onFailure(Call<QuestionModel> call, Throwable t) {
                view.hideLoading();
            }
        });

    }

}
