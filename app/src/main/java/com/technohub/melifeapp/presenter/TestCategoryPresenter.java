package com.technohub.melifeapp.presenter;

import android.util.Log;
import com.technohub.melifeapp.Interfaces.Itestcategory;
import com.technohub.melifeapp.models.ExamRequest;
import com.technohub.melifeapp.models.ExamResponse;
import com.technohub.melifeapp.models.LoadQuestionRequest;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.TestCategoriesModel;
import com.technohub.melifeapp.models.TestcategoryResponse;
import com.technohub.melifeapp.models.Tests;
import com.technohub.melifeapp.models.User;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestCategoryPresenter implements Itestcategory.Presenter
{
    private Itestcategory.View view;
    ExamResponse examResponse;
    TestCategoriesModel testCategoriesModel=new TestCategoriesModel();
    List<TestCategoriesModel> testList;
    ExamRequest examRequest;
    User user;

    public TestCategoryPresenter(Itestcategory.View view,User user)
    {
        this.view = view;
        this.user=user;
    }

    public TestCategoryPresenter(ExamRequest examRequest,Itestcategory.View view) {

        this.examRequest = examRequest;
        this.view = view;

    }

    @Override
    public void created() {

        view.init();
        view.initClicks();
        load(user);

    }
    @Override
    public void load(User user) {

        view.showLoading();
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        testCategoriesModel.setUser_email(user.getUser_email());
        testCategoriesModel.setUser_id(user.getUserid());

        Log.e("Test category userid", user.getUserid());
        Log.e("Test category email", user.getUser_email());


        Call<TestcategoryResponse> call = retrofitApi.dashboard(testCategoriesModel);
        call.enqueue(new Callback<TestcategoryResponse>() {
            
            @Override
            public void onResponse(Call<TestcategoryResponse> call, Response<TestcategoryResponse> response) {
                TestcategoryResponse testcategoryResponse = response.body();
                if (response.isSuccessful() && response.body() != null) {
                    if(response.body().getMessage().equals("Success")) 
                    {
                        Log.e("Test category res", testcategoryResponse.getMessage());
                        Log.e("Test category res", testcategoryResponse.getUseremail());
                        Log.e("Test category res", testcategoryResponse.getUserid());

                        List<Tests> tests=testcategoryResponse.getData();
                        Log.e("Test category ",tests.size()+"");
                        for(Tests t:tests)
                       {
                           Log.e("Test category ","af");
                           Log.e("id",t.getTest_id()+"");
                           Log.e("name",t.getTest_name()+"");
                           Log.e("complete status",t.getCmplts_status()+"");
                           Log.e("examid",t.getExam_id()+"");
                       }
                        view.loadTestList(testcategoryResponse);
                        view.hideLoading();

                    }
                    else
                    {
                        Log.e("Test category res", testcategoryResponse.getMessage());

                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<TestcategoryResponse> call, Throwable t) {
                Log.e("Test category res","faileddd");
                view.hideLoading();

            }
        });
    }
    @Override
    public void initiateExam() {
        view.showLoading();
        Log.e("Test category testid",examRequest.getTest_id());
        Log.e("Test category userid", examRequest.getUser_id());
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        Call<ExamResponse> call = retrofitApi.displayquestions(examRequest);
        call.enqueue(new Callback<ExamResponse>()
        {
            @Override
            public void onResponse(Call<ExamResponse> call, Response<ExamResponse> response)
            {
                Log.e("presenter","kkvk");
                examResponse=response.body();
                if (response.isSuccessful() && response.body() .getMessage().equals("Success"))
                {
                    Log.e("exam initiate","success");
                    Log.e("exam completion ",response.body().getExamCompletionsts());
                    Log.e("exam qn exists",response.body().getQnExiststs()+"");
                    Log.e("exam response",examResponse.getDisplayData().getLast_qn_no());
                    if(response.body().getExamCompletionsts().equals("N"))
                    { if(response.body().getQnExiststs().equals("Y"))
                      {
                          Log.e("load exam","qnexists");
                          getQuestionsFromServer(response.body().getDisplayData().getExam_id(),response.body().getDisplayData().getTest_id(),examRequest.getUser_id(),examRequest.getUser_email(),"0","1","kgk","0");
//
                      }
                      else
                      {
                          Log.e("load noqns","no qns");
                          view.loadNoQnsFragment();
                      }
                    }
                    else if(response.body().getExamCompletionsts().equals('Y'))
                    {
                        Log.e("load report","status y");
                        view.loadReportFragment();
                    }

                }
                else
                {
                    Log.e("exam initiate","no res");
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<ExamResponse> call, Throwable t)
            {
                view.hideLoading();
            }

        });

    }

    public void getQuestionsFromServer(String exam_id,String test_id,String user_id,String user_email,String logid,String DeviceType,String DeviceToken,String record ) {

        Log.e("in exam presenter","get method");
        Log.e("in load qns exid",exam_id+"test "+test_id+" user"+user_id+" email "+user_email+" logid "+logid+"rec"+record);
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        LoadQuestionRequest loadQuestionRequest=new LoadQuestionRequest();
        loadQuestionRequest.setExam_id(exam_id);
        loadQuestionRequest.setTest_id(test_id);
        loadQuestionRequest.setUser_id(user_id);
        loadQuestionRequest.setUser_email(user_email);
        loadQuestionRequest.setLogid(logid);
        loadQuestionRequest.setDeviceType(DeviceType);
        loadQuestionRequest.setDeviceToken(DeviceToken);
        loadQuestionRequest.setRecord(record);
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
                        Log.e("load qns logid", loadQuestionResponse.getIdle_log_id());
                        Log.e("load qns noqns", loadQuestionResponse.getTotal_no_questions());
                        Log.e("load qns pop", loadQuestionResponse.getIs_popup_display());
                        Log.e("load qns lmt", loadQuestionResponse.getLimit());
                        Log.e("load qns revcnt", loadQuestionResponse.getReviewCount());
                        Log.e("load qns log_opid", loadQuestionResponse.getLog_option_id());

                        view.loadExamFragment(loadQuestionResponse);



                    }
                    else
                    {


                    }
                }
            }

            @Override
            public void onFailure(Call<LoadQuestionResponse> call, Throwable t)
            {

            }
        });
    }
}
