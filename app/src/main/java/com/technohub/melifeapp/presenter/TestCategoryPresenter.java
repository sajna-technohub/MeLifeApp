package com.technohub.melifeapp.presenter;

import android.content.Context;
import android.util.Log;

import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.Interfaces.Itestcategory;
import com.technohub.melifeapp.models.ExamRequest;
import com.technohub.melifeapp.models.ExamResponse;
import com.technohub.melifeapp.models.LoadQuestionRequest;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.SaveExamReponse;
import com.technohub.melifeapp.models.SaveExamRequest;
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

    public TestCategoryPresenter(Itestcategory.View view) {
        this.view=view;
    }

    public TestCategoryPresenter(Itestcategory.View view, User user)
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
        Log.e("in  presenter","dashboard");
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
        Log.e("in  presenter","displayquestions");
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

        Log.e("in  presenter","loadquestions");
        Log.e("in load questions exid=",exam_id+"testid= "+test_id+" userid="+user_id+" email= "+user_email+" logid= "+logid+"rec="+record);
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
                    if(loadQuestionResponse.getMessage().equals("NO question available"))
                    {
                        view.loadExamFragment(loadQuestionResponse, exam_id);
                    }
                    else{
                    Log.e("in  presenter","got response");
//                    if(response.body().getMessage().equals(""))
//                    {
                        Log.e("load qns response", loadQuestionResponse.getMessage());
                        Log.e("load qnsno", loadQuestionResponse.getTotal_no_questions());
                        Log.e("load qns question_id", loadQuestionResponse.getExamquestionData().get(0).getMcq_question_id());
                        Log.e("load qns question", loadQuestionResponse.getExamquestionData().get(0).getQuestion());
                        Log.e("load qns test", loadQuestionResponse.getExamquestionData().get(0).getTest_name());
                        Log.e("load qns qn order", loadQuestionResponse.getExamquestionData().get(0).getQuestion_order());
                        Log.e("load qns optionid1", loadQuestionResponse.getExamquestionData().get(0).getOptions().get(0).getOption_id());
                        Log.e("load qns optiondesc", loadQuestionResponse.getExamquestionData().get(0).getOptions().get(0).getOption_descp());
                        Log.e("load qns optiondesc", loadQuestionResponse.getExamquestionData().get(0).getOptions().get(1).getOption_descp());
//                        Log.e("load qns optiondesc", loadQuestionResponse.getExamquestionData().get(0).getOptions().get(0).getOption_descp());
//                        Log.e("load qns optiondesc", loadQuestionResponse.getExamquestionData().get(0).getOptions().get(0).getOption_descp());
//                        Log.e("load qns optiondesc", loadQuestionResponse.getExamquestionData().get(0).getOptions().get(0).getOption_descp());
//                        new LoginResponse().setSecSharedPreferences(loadQuestionResponse.getExamquestionData().get(0).getSection_id());
                        view.loadExamFragment(loadQuestionResponse, exam_id);

//                    }
//                    else
//                    {
//
//
                    }
                }
            }

            @Override
            public void onFailure(Call<LoadQuestionResponse> call, Throwable t)
            {

            }
        });
    }
    public void submitexam(String examid,String userid,String useremail) {

        view.showLoading();
        Log.e("in exam presenter","submitexam"+examid+" "+useremail+" "+userid);

        Log.e("in submt exam id",examid);
        Log.e("in submt useremIL",useremail);
        Log.e("in submt userid",userid);

        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        SaveExamRequest saveExamRequest=new SaveExamRequest();
        saveExamRequest.setExam_id(examid);
        saveExamRequest.setLogid("0");
        saveExamRequest.setUser_email(useremail);
        saveExamRequest.setUser_id(userid);
        saveExamRequest.setDeviceToken("hsj");
        saveExamRequest.setDeviceType("1");
        Call<SaveExamReponse> call = retrofitApi.saveexamdetails(saveExamRequest);
        call.enqueue(new Callback<SaveExamReponse>() {
            @Override
            public void onResponse(Call<SaveExamReponse> call, Response<SaveExamReponse> response) {
                SaveExamReponse saveExamReponse = response.body();
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("in exam presenter","gotrespose");
                    if(response.body().getStatus()==1)
                    {
                        Log.e("in submit exam",response.body().getStatus()+"");

                        view.hideLoading();
                        view.goToDashboard();
                    }
                    else
                    {
                        view.hideLoading();
                    }
                }
            }

            @Override
            public void onFailure(Call<SaveExamReponse> call, Throwable t)
            {
                Log.e("saveexam","failed");
                view.hideLoading();
                view.hideLoading();
            }
        });
    }
}
