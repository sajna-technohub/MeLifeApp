package com.technohub.melifeapp.presenter;
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

        datumList.add(new Data(1,htmltext,"option1","option2","option3","option4"));
        datumList.add(new Data(1,"<p>the mass of water produced from <span class=\"math-tex\">\\(445 \\, \\mathrm{g}\\)</span> of <span class=\"math-tex\">\\(\\mathrm{C}_{57} \\mathrm{H}_{110} \\mathrm{O}_{6}\\)</span> is:</p>","option1","option2","option3","option4"));
        datumList.add(new Data(1,"<p>For the reaction, <span class=\"math-tex\">\\(2 \\mathrm{C}_{57} \\mathrm{H}_{110} \\mathrm{O}_{6}(\\mathrm{s})+163 \\mathrm{O}_{2}(\\mathrm{g}) \\rightarrow 114 \\mathrm{CO}_{2}(\\mathrm{g})+110 \\mathrm{H}_{2} \\mathrm{O}_{(\\rm{l})}\\)</span></p>\n","option1","option2","option3","option4"));
        datumList.add(new Data(1,"<p><span class=\"math-tex\">\\(x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}\\)</span>abcd?</p><p>Given,</p>\n" +
                "\n" +
                "<p><span class=\"math-tex\">\\(\\mathrm{E}_{\\mathrm{Cr}^{3+} / \\mathrm{Cr}}^{0}=-0.74 \\mathrm{V} ; \\mathrm{E}_{\\mathrm{MnO}_{\\mathrm{i}} / \\mathrm{Mn}^{2+}}^{0}=1.51 \\mathrm{V}\\)</span></p>","option1","option2","option3","option4"));
        datumList.add(new Data(1,htmltext,"option1","option2","option3","option4"));
//        datumList.add(new Data(2,"<p>A particle of mass m is moving along the side of a square of side &lsquo;a&rsquo;, with a uniform speed v in the x-y plane as shown in the&nbsp;<br />\n" +
//                "figure :<br />\n" +
//                "<img alt=\"\" src=\"http://192.168.1.4/prepido/ckfinder/userfiles/files/2016Q2.png\" style=\"height:166px; width:223px\" /></p>","option1","option2","option3","option4"));
//        datumList.add(new Data(3,"<p><span class=\"math-tex\">\\(\\mathrm{E}_{\\mathrm{Cr}^{3+} / \\mathrm{Cr}}^{0}=-0.74 \\mathrm{V} ; \\mathrm{E}_{\\mathrm{MnO}_{\\mathrm{i}} / \\mathrm{Mn}^{2+}}^{0}=1.51 \\mathrm{V}\\)</span></p>\n","option1","option2","option3","option4"));
//        datumList.add(new Data(4,"<p>For the reaction, <span class=\"math-tex\">\\(2 \\mathrm{C}_{57} \\mathrm{H}_{110} \\mathrm{O}_{6}(\\mathrm{s})+163 \\mathrm{O}_{2}(\\mathrm{g}) \\rightarrow 114 \\mathrm{CO}_{2}(\\mathrm{g})+110 \\mathrm{H}_{2} \\mathrm{O}_{(\\rm{l})}\\)</span></p>","option1","option2","option3","option4"));
//        datumList.add(new Data(5,"sdhsdgdkfggf","option1","option2","option3","option4"));
//        datumList.add(new Data(6,"what is your name???","option1","option2","option3","option4"));
//        datumList.add(new Data(7,"Hi how are you","option1","option2","option3","option4"));
//        datumList.add(new Data(8,"Hellooooooo","option1","option2","option3","option4"));
//        datumList.add(new Data(9,"Pls be quiettttt","option1","option2","option3","option4"));
//        datumList.add(new Data(10,"Be ambitioussssssssssssssssss","option1","option2","option3","option4"));

        //test with dummy data

        view.ShowQuestionList(datumList);
        view.setQuestion();
//      getQuestionsFromServer();
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
