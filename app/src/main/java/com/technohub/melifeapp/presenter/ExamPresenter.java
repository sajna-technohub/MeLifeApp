package com.technohub.melifeapp.presenter;

import android.graphics.Movie;
import android.util.Log;

import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.classes.Data;
import com.technohub.melifeapp.classes.Root;
import com.technohub.melifeapp.models.ExamModel;
import com.technohub.melifeapp.models.ExamResponse;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        Call<Root> call = retrofitApi.getQuestions("2");
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
            Root r=response.body();
                if (response.isSuccessful() && response.body() != null) {
                            Log.e("tag",response.body().toString());
                    Integer text = r.getPage();
                    Integer total = r.getTotal();
                    Integer totalPages = r.getTotal_pages();
                    Log.e("te",text+"   "+total+"  "+totalPages);
                    List<Data> datumList = r.getData();
                    for(Data d:datumList)
                    {
                        Log.e("te",d.getAvatar()+"");
                        Log.e("te",d.getEmail()+"");
                    }
//                    for(Root e:examResponse)
//                    {
////                        Log.e("Qno",e.getAnsId()+"");
////                        Log.e("Qname",e.getQname()+"");
//                    }
                       view.hideLoading();
                } else if (response.errorBody() != null) {

                    try {
                        view.hideLoading();
                        Log.e("Qname","failed");

                    } catch (Exception e) {}
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                view.hideLoading();
            }
        });

    }

}
