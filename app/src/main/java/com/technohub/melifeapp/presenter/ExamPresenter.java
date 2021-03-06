package com.technohub.melifeapp.presenter;
import android.util.Log;
import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.models.LoadQuestionRequest;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.SaveAnswerRequest;
import com.technohub.melifeapp.models.SaveAnswerResponse;
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
    }

    @Override
    public void saveAnswer(String exam_id,String test_id,String user_id,String user_email,String logid,String DeviceType,String DeviceToken,String record ) {
        view.showLoading();
        Log.e("in exam presenter","get method");
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        SaveAnswerRequest saveAnswerRequest=new SaveAnswerRequest();
        saveAnswerRequest.setExam_id(exam_id);

        Call<SaveAnswerResponse> call = retrofitApi.saveanswer(saveAnswerRequest);
        call.enqueue(new Callback<SaveAnswerResponse>() {
            @Override
            public void onResponse(Call<SaveAnswerResponse> call, Response<SaveAnswerResponse> response) {
                SaveAnswerResponse saveAnswerResponse = response.body();
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("in exam presenter","got response");
//                    if(response.body().getMessage().equals("success".trim()))
                    {
//                        Log.e("load qns logid", loadQuestionResponse.getIdle_log_id());
//                        Log.e("load qns noqns", loadQuestionResponse.getTotal_no_questions());
//                        Log.e("load qns pop", loadQuestionResponse.getIs_popup_display());
//                        Log.e("load qns lmt", loadQuestionResponse.getLimit());
//                        Log.e("load qns revcnt", loadQuestionResponse.getReviewCount());
//                        Log.e("load qns log_opid", loadQuestionResponse.getLog_option_id());

                          view.hideLoading();

                    }
//                    else
                    {
//                        Log.e("Responseloadqns", loadQuestionResponse.getMessage());
                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<SaveAnswerResponse> call, Throwable t)
            {
                       view.hideLoading();
            }
        });
    }

    @Override
    public void saveanswerbeforeidealtime() {

    }
}
