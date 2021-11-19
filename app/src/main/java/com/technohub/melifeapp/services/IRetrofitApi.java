package com.technohub.melifeapp.services;

import com.technohub.melifeapp.models.AIJobResponse;
import com.technohub.melifeapp.models.CommerceResponse;
import com.technohub.melifeapp.models.EnggResponse;
import com.technohub.melifeapp.models.FaqResponse;
import com.technohub.melifeapp.models.HumanityResponse;
import com.technohub.melifeapp.models.ProfileNew;
import com.technohub.melifeapp.models.ProfileRes;
import com.technohub.melifeapp.models.RightCareerInterest;
import com.technohub.melifeapp.models.ExamRequest;
import com.technohub.melifeapp.models.ExamResponse;
import com.technohub.melifeapp.models.InstructionRequest;
import com.technohub.melifeapp.models.InstructionResponse;
import com.technohub.melifeapp.models.LoadQuestionRequest;
import com.technohub.melifeapp.models.LoadQuestionResponse;
import com.technohub.melifeapp.models.LoginResponse;
import com.technohub.melifeapp.models.ProfileResponse;
import com.technohub.melifeapp.models.RegisterResponse;
import com.technohub.melifeapp.models.RightCareerAptitude;
import com.technohub.melifeapp.models.RightCareerChart;
import com.technohub.melifeapp.models.RightCareerIntelligence;
import com.technohub.melifeapp.models.RightCareerPersonality;
import com.technohub.melifeapp.models.RightRecord;
import com.technohub.melifeapp.models.RightUserDetail;
import com.technohub.melifeapp.models.SaveAnswerRequest;
import com.technohub.melifeapp.models.SaveAnswerResponse;
import com.technohub.melifeapp.models.SaveExamReponse;
import com.technohub.melifeapp.models.SaveExamRequest;
import com.technohub.melifeapp.models.SignUpModel;
import com.technohub.melifeapp.models.SkillReportRequest;
import com.technohub.melifeapp.models.SkillReportResponse;
import com.technohub.melifeapp.models.StreamFinderRequest;
import com.technohub.melifeapp.models.StreamFinderResponse;
import com.technohub.melifeapp.models.StreamPieChartResponse;
import com.technohub.melifeapp.models.TestCategoriesModel;
import com.technohub.melifeapp.models.TestcategoryResponse;
import com.technohub.melifeapp.models.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IRetrofitApi {

    /*for register on RegisterActivity*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("register")
    Call<RegisterResponse> Register(@Body SignUpModel user);


    /*for Log in on LoginActivity*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("signin")
    Call<LoginResponse> Login(@Body User user);

    //  for  forgotpassword
      @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
      @POST("forgot_password")
      Call<LoginResponse> forgot_password(@Body SignUpModel user);

    /*for  TestCategory*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("dashboard")
    Call<TestcategoryResponse> dashboard(@Body TestCategoriesModel testCategoriesModel);

    /*for  reports */
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("dashboard")
    Call<TestcategoryResponse> dashboardcompleted(@Body TestCategoriesModel testCategoriesModel);
    /*for  Get userprofile*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("userdetails")
    Call<ProfileResponse> userdetails(@Body User profileRequest);

    /*for   userprofileUpdate*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("userprofile")
    Call<ProfileRes> userprofile(@Body User user);

    /*for  upload image*/
    @Multipart
    @Headers({"Content-Type:multipart/form-data;","X-Api-Key:16f794caa2ae9a38","Host:<calculated when request is sent>"})
    @POST("userprofile")
    Call<ProfileNew> userprofile(@Part MultipartBody.Part image,
                                 @Part("user_id") RequestBody userid,
                                 @Part("name") RequestBody name,
                                 @Part("mobno") RequestBody mobno,
                                 @Part("date") RequestBody date,
                                 @Part("pincode") RequestBody pincode,
                                 @Part("state") RequestBody state,
                                 @Part("qualification") RequestBody qualification,
                                 @Part("country") RequestBody country,
                                 @Part("DeviceType") RequestBody DeviceType,
                                 @Part("DeviceToken") RequestBody DeviceToken);

    /*for  Get examinitiate*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("displayquestions")
    Call<ExamResponse> displayquestions(@Body ExamRequest examRequest);

    /*for  loading questions*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadquestions")
    Call<LoadQuestionResponse> loadquestions(@Body LoadQuestionRequest loadQuestionRequest);

    /*for  instruction pop up*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("saveinstructionpopupstatus")
    Call<InstructionResponse> saveinstructionpopupstatus(@Body InstructionRequest instructionRequest);

    /*for  save answer*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("saveanswer")
    Call<SaveAnswerResponse> saveanswer(@Body SaveAnswerRequest saveAnswerRequest);

    /*for  save answer b4 ideal time*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("saveanswerbeforeidealtime")
    Call<SaveAnswerResponse> saveanswerbeforeidealtime(@Body SaveAnswerRequest saveAnswerRequest);

    /*for  submit exam*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("saveexamdetails")
    Call<SaveExamReponse> saveexamdetails(@Body SaveExamRequest saveExamRequest);

    /*for  skillfinder report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadreportskill")
    Call<SkillReportResponse> loadreportskill(@Body SkillReportRequest skillReportRequest);

    /*for  rightcareer report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadreportskill")
    Call<RightUserDetail> loadrightuserdata(@Body SkillReportRequest skillReportRequest);

    /*for  Rightcarrer aptitude chart data report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("rightcareeraptitudechartdata")
    Call<RightCareerAptitude> rightcareeraptitudechartdata(@Body SkillReportRequest skillReportRequest);

    /*for  Rightcarrer  chart data report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("rightcareercareerchartdata")
    Call<RightCareerChart> rightcareercareerchartdata(@Body SkillReportRequest skillReportRequest);

    /*for  Rightcarrer  intelligence chart data report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("rightcareerintelligencechartdata")
    Call<RightCareerIntelligence> rightcareerintelligencechartdata(@Body SkillReportRequest skillReportRequest);

    /*for  Rightcarrer interest chart data report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("rightcareerinterestchartdata")
    Call<RightCareerInterest> rightcareerinterestchartdata(@Body SkillReportRequest skillReportRequest);

    /*for  Rightcarrer personality chart data report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("rightcareerpersonalitychartdata")
    Call<RightCareerPersonality> rightcareerpersonalitychartdata(@Body SkillReportRequest skillReportRequest);

   /*for  Rightcarrer career field chart data report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("rightcareerfieldchartdata")
    Call<RightCareerInterest> rightcareerfieldchartdata(@Body SkillReportRequest skillReportRequest);

    /*for  stream report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadreportskill")
    Call<StreamFinderResponse> loadreportstream(@Body StreamFinderRequest streamFinderRequest);

    /*for  engg report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadreportskill")
    Call<EnggResponse> loadreportengg(@Body StreamFinderRequest streamFinderRequest);

    /*for  AI report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadreportskill")
    Call<AIJobResponse> loadreportAI(@Body StreamFinderRequest streamFinderRequest);

    /*for  commerce report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadreportskill")
    Call<CommerceResponse> loadreportCommerce(@Body StreamFinderRequest streamFinderRequest);

    /*for  humanity report*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("loadreportskill")
    Call<HumanityResponse> loadreportHumanity(@Body StreamFinderRequest streamFinderRequest);

    /*for  faq*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("faq")
    Call<FaqResponse> faq(@Body StreamFinderRequest streamFinderRequest);

    /*for  faq*/
    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("contact_us")
    Call<FaqResponse> contact_us(@Body StreamFinderRequest streamFinderRequest);

    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("interestareaimage")
    Call<LoadQuestionResponse> interestareaimage(@Body SkillReportRequest skillReportRequest);

    @Headers({"Content-Type:application/json","X-Api-Key:16f794caa2ae9a38"})
    @POST("piechart")
    Call<StreamPieChartResponse> piechart(@Body StreamFinderRequest skillReportRequest);
}

