package com.technohub.melifeapp.presenter;


import android.os.Handler;
import android.util.Log;

import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.classes.Data;
import com.technohub.melifeapp.classes.Root;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamPresenter implements IExam.Presenter {
    private IExam.View view;
    Root r=new Root();
    List<Data> datumList;
    String htmltext;



    public ExamPresenter(IExam.View view,String htmltext) {
        this.view = view;
        this.htmltext=htmltext;
    }

    @Override
    public void created() {
        view.init();
        view.initClicks();

        datumList=new ArrayList<>();
        datumList.add(new Data(1,htmltext));
        datumList.add(new Data(2,htmltext));
        datumList.add(new Data(3,htmltext));
        datumList.add(new Data(4,htmltext));

        //test with dummy data

        view.ShowQuestionList(datumList);
        view.setQuestion();
//        getQuestionsFromServer();
    }


    @Override
    public void getQuestionsFromServer() {
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        Call<Root> call = retrofitApi.getQuestions("2");
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response)
            {
                        r=response.body();
                if (response.isSuccessful() && response.body() != null)
                {
                    datumList=response.body().getData();
                    Integer text = r.getPage();
                    Integer total = r.getTotal();
                    Integer totalPages = r.getTotal_pages();
                    Log.e("teeee",text+"   "+total+"  "+totalPages);
                    datumList = r.getData();
                    for(Data d:datumList)
                    {
                        Log.e("teeee",d.getAvatar()+"");
                        Log.e("teeee",d.getEmail()+"");
                    }

                       view.hideLoading();
                    r.setData(datumList);
                    Log.e("Loading","list");
                    view.ShowQuestionList(datumList);
                    view.setQuestion();
                    Log.e("size",datumList.size()+"");

                }
                else if (response.errorBody() != null) {
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
