package com.technohub.melifeapp.presenter;
import android.content.Context;
import android.util.Log;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.models.LoadQuestionRequest;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.SaveAnswerRequest;
import com.technohub.melifeapp.models.SaveAnswerResponse;
import com.technohub.melifeapp.models.SaveExamReponse;
import com.technohub.melifeapp.models.SaveExamRequest;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamPresenter implements IExam.Presenter {

    private IExam.View view;
    private String email;
    Context con;

    public ExamPresenter(IExam.View view,String email,Context con) {
        this.view = view;
        this.email=email;
        this.con=con;

    }

    @Override
    public void created()
    {
        Log.e("load","exam presenter");
        view.init();
        view.initClicks();
    }

    @Override
    public void saveAnswer(String qnorder,String exam_id,String test_id,String user_id ,String logid,String DeviceType,String DeviceToken,String quesid,String optid,String totqns ) {

        view.showLoading();
        Log.e("Request","save answer");
        Log.e("email",email);
        Log.e("exam id",exam_id);
        Log.e("testid",test_id);
        Log.e("userid",user_id);
        Log.e("qid",quesid);
        Log.e("optid",optid);
        Log.e("totqns",totqns);
        Log.e("qnorder",qnorder);

        if(totqns.equals(qnorder))
        {
//            submitexam(exam_id,user_id,email);
            view.hideLoading();
        }
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        SaveAnswerRequest saveAnswerRequest=new SaveAnswerRequest();
        saveAnswerRequest.setExam_id(exam_id);
        saveAnswerRequest.setTest_id(test_id);
        saveAnswerRequest.setUser_id(user_id);
        saveAnswerRequest.setDeviceToken(DeviceToken);
        saveAnswerRequest.setDeviceType(DeviceType);
        saveAnswerRequest.setQuestion_id(quesid);
        saveAnswerRequest.setLog_id("0");
        saveAnswerRequest.setOption_id(optid);
        saveAnswerRequest.setChoose_option(optid);
        Call<SaveAnswerResponse> call = retrofitApi.saveanswer(saveAnswerRequest);
        call.enqueue(new Callback<SaveAnswerResponse>() {
            @Override
            public void onResponse(Call<SaveAnswerResponse> call, Response<SaveAnswerResponse> response) {
                SaveAnswerResponse saveAnswerResponse = response.body();
                Log.e("in exam presenter b4msg", saveAnswerResponse.getMessage());
                if(response.isSuccessful()) {
                    Log.e("in exam presenter", "save answer");
                    Log.e("in exam presenter msg", saveAnswerResponse.getMessage());

                    if (response.body().getMessage().equals("success".trim())) {

                        if(totqns.equals(qnorder))
                        {
                            Log.e("in exam presenter", "success");
                            Log.e("save answer msg", saveAnswerResponse.getMessage());
                            Log.e("save answer qn", saveAnswerResponse.getRecord().getQn_dts().getQuestion());
                            Log.e("save answer qnid", saveAnswerResponse.getRecord().getQn_dts().getQuestion_id());
                            Log.e("save answer optnid", saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id());
                            Log.e("save answer opt desc", saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp());
                            Log.e("save answer qn order", saveAnswerResponse.getRecord().getQn_dts().getQuestion_order());

//                            submitexam(exam_id,user_id,email);
                            view.hideLoading();
                        }
                        else {
                            Log.e("in exam presenter", "success");
                            Log.e("save answer msg", saveAnswerResponse.getMessage());
                            Log.e("save answer qn", saveAnswerResponse.getRecord().getQn_dts().getQuestion());
                            Log.e("save answer qnid", saveAnswerResponse.getRecord().getQn_dts().getQuestion_id());
                            Log.e("save answer optnid", saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id());
                            Log.e("save answer opt desc", saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp());
                            Log.e("save answer qn order", saveAnswerResponse.getRecord().getQn_dts().getQuestion_order());


                            view.showNextQuestion(saveAnswerResponse);
                            view.hideLoading();
                        }
                    } else {
                        Log.e("in exam presenter", "failed");
//                        submitexam(exam_id, user_id, email);
                        view.hideLoading();
                    }
                }
                else
                {
                    Log.e("in exam presenter", "notsuccessfull");
                    view.hideLoading();
                }

            }


            @Override
            public void onFailure(Call<SaveAnswerResponse> call, Throwable t)
            {

                       view.hideLoading();
                Log.e("in exam presenter", "SAnotsuccessfull");
            }
        });
    }


    @Override
    public void saveanswerbeforeidealtime(String qnorder,String exam_id,String test_id,String user_id ,String logid,String DeviceType,String DeviceToken,String quesid,String optid ,String totqns) {
        view.showLoading();
        Log.e("in exam presenter b4","saveanswerb4ideal");
        Log.e("Request","save answerb4ideal");
        Log.e("email",email);
        Log.e("exam id",exam_id);
        Log.e("testid",test_id);
        Log.e("userid",user_id);
        Log.e("iqid",quesid);
        Log.e("optid",optid);
        Log.e("totqns",totqns);
        Log.e("qnorder",qnorder);
        if(totqns.equals(qnorder))
        {
//            submitexam(exam_id,user_id,email);
            view.hideLoading();
        }
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        SaveAnswerRequest saveAnswerRequest=new SaveAnswerRequest();
        saveAnswerRequest.setExam_id(exam_id);
        saveAnswerRequest.setTest_id(test_id);
        saveAnswerRequest.setUser_id(user_id);
        saveAnswerRequest.setDeviceToken(DeviceToken);
        saveAnswerRequest.setDeviceType(DeviceType);
        saveAnswerRequest.setQuestion_id(quesid);
        saveAnswerRequest.setLog_id("0");
        saveAnswerRequest.setOption_id(optid);
        saveAnswerRequest.setChoose_option(optid);

        Call<SaveAnswerResponse> call = retrofitApi.saveanswerbeforeidealtime(saveAnswerRequest);
        call.enqueue(new Callback<SaveAnswerResponse>() {
            @Override
            public void onResponse(Call<SaveAnswerResponse> call, Response<SaveAnswerResponse> response) {
                SaveAnswerResponse saveAnswerResponse = response.body();
                if(response.isSuccessful()) {
                    if (response.body().getMessage().equals("success".trim())) {
                        if(totqns.equals(qnorder))
                        {
                            Log.e("in exam presenter", "success");
                            Log.e("save answer msg", saveAnswerResponse.getMessage());
                            Log.e("save answer qn", saveAnswerResponse.getRecord().getQn_dts().getQuestion());
                            Log.e("save answer qnid", saveAnswerResponse.getRecord().getQn_dts().getQuestion_id());
                            Log.e("save answer optnid", saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id());
                            Log.e("save answer opt desc", saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp());
                            Log.e("save answer qn order", saveAnswerResponse.getRecord().getQn_dts().getQuestion_order());
//                            submitexam(exam_id,user_id,email);
                            view.hideLoading();
                        }else {
                            Log.e("in exam presenter b4", "got response");
                            Log.e("Response", "got response");
                            Log.e("save answer b4 msg", saveAnswerResponse.getMessage());
                            Log.e("save answer b4 qn", saveAnswerResponse.getRecord().getQn_dts().getQuestion());
                            Log.e("save answer b4 qnid", saveAnswerResponse.getRecord().getQn_dts().getQuestion_id());
                            Log.e("save answer b4 optnid", saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_id());
                            Log.e("save answer b4 opt desc", saveAnswerResponse.getRecord().getOpt_dts().get(0).getOption_descp());
                            Log.e("save answer b4 qn order", saveAnswerResponse.getRecord().getQn_dts().getQuestion_order());

                            view.showNextQuestion(saveAnswerResponse);

                            view.hideLoading();
                        }

                    } else
                    {
                        Log.e("in exam presenter b4", "failed else");
//                        submitexam(exam_id, user_id, email);
                        view.hideLoading();
                    }
                }
                else
                {
                    Log.e("in exam presenter", "notsuccessfull");
                    view.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<SaveAnswerResponse> call, Throwable t)
            {
                view.hideLoading();
                Log.e("in exam presenter", "SABInotsuccessfull");
            }

        });
    }

    @Override
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
        KProgressHUD kProgressHUD= KProgressHUD.create(con);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait..")
                .setCancellable(true)
                .setAnimationSpeed(5)
                .setDimAmount(0.5f)
                .show();
        Call<SaveExamReponse> call = retrofitApi.saveexamdetails(saveExamRequest);
        call.enqueue(new Callback<SaveExamReponse>()
        {
            @Override
            public void onResponse(Call<SaveExamReponse> call, Response<SaveExamReponse> response) {


                    if(response.isSuccessful())

                    {
                        SaveExamReponse saveExamReponse = response.body();
                        Log.e("in submit exam",saveExamReponse.getMessage()+"");
                        view.hideLoading();
                        kProgressHUD.dismiss();
                        view.goToDashboard();
                    }
                    else
                    {
                        view.hideLoading();
                        kProgressHUD.dismiss();
                        Log.e("in submit exam","cantsubmit");
                    }

            }

            @Override
            public void onFailure(Call<SaveExamReponse> call, Throwable t)
            {
                Log.e("saveexam","failed");
                Log.e("saveexamexcp",t.toString());
                view.hideLoading();
                kProgressHUD.dismiss();
//                view.goToDashboard();

            }
        });
    }
}
