package com.technohub.melifeapp.presenter;
import android.util.Log;

import com.technohub.melifeapp.Interfaces.IExam;
import com.technohub.melifeapp.classes.DataDATA;
import com.technohub.melifeapp.classes.Root;
import com.technohub.melifeapp.services.ApiClient;
import com.technohub.melifeapp.services.IRetrofitApi;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestPresenter implements IExam.Presenter {
    private IExam.View view;
    Root r=new Root();
    List<DataDATA> datumList;
    String htmltext;

    public TestPresenter(IExam.View view, String htmltext) {
        this.view = view;
        this.htmltext=htmltext;
    }

    @Override
    public void created() {
        view.init();
        view.initClicks();
        datumList=new ArrayList<>();

//        datumList.add(new DataDATA(1,htmltext,"option1","option2","option3","option4"));
//        datumList.add(new DataDATA(2,"<p>the mass of water produced from <span class=\"math-tex\">\\(445 \\, \\mathrm{g}\\)</span> of <span class=\"math-tex\">\\(\\mathrm{C}_{57} \\mathrm{H}_{110} \\mathrm{O}_{6}\\)</span> is:</p>","option1","option2","option3","option4"));
//        datumList.add(new DataDATA(3,"<p>For the reaction, <span class=\"math-tex\">\\(2 \\mathrm{C}_{57} \\mathrm{H}_{110} \\mathrm{O}_{6}(\\mathrm{s})+163 \\mathrm{O}_{2}(\\mathrm{g}) \\rightarrow 114 \\mathrm{CO}_{2}(\\mathrm{g})+110 \\mathrm{H}_{2} \\mathrm{O}_{(\\rm{l})}\\)</span></p>\n","option1","option2","option3","option4"));
//        datumList.add(new DataDATA(4,"<p><span class=\"math-tex\">\\(x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}\\)</span>abcd?</p><p>Given,</p>\n" +
//                "\n" +
//                "<p><span class=\"math-tex\">\\(\\mathrm{E}_{\\mathrm{Cr}^{3+} / \\mathrm{Cr}}^{0}=-0.74 \\mathrm{V} ; \\mathrm{E}_{\\mathrm{MnO}_{\\mathrm{i}} / \\mathrm{Mn}^{2+}}^{0}=1.51 \\mathrm{V}\\)</span></p>","option1","option2","option3","option4"));
//        datumList.add(new DataDATA(5,htmltext,"option1","option2","option3","option4"));
////        datumList.add(new DataDATA(2,"<p>A particle of mass m is moving along the side of a square of side &lsquo;a&rsquo;, with a uniform speed v in the x-y plane as shown in the&nbsp;<br />\n" +
////                "figure :<br />\n" +
////
// "<img alt=\"\" src=\"http://192.168.1.4/prepido/ckfinder/userfiles/files/2016Q2.png\" style=\"height:166px; width:223px\" /></p>","option1","option2","option3","option4"));
        String d="<h1>fig.1</h1><img src='https://homepages.cae.wisc.edu/~ece533/images/boat.png'>";
        String e="<h1>fig.2</h1><img src='https://homepages.cae.wisc.edu/~ece533/images/boat.png'>";
        datumList.add(new DataDATA(3,htmltext,d,e,"option3","option4"));
        datumList.add(new DataDATA(4,htmltext,d,e,"option2","option3"));
        datumList.add(new DataDATA(5,d,e,"option2","option3","option4"));
        datumList.add(new DataDATA(6,"what is your name???","option1","option2","option3","option4"));
        datumList.add(new DataDATA(7,"Hi how are you","option1","option2","option3","option4"));
        datumList.add(new DataDATA(8,htmltext,d,e,"option3","option4"));
        datumList.add(new DataDATA(9,htmltext,e,e,"option3","option4"));
        datumList.add(new DataDATA(10,"Be ambitioussssssssssssssssss","option1","option2","option3","option4"));

        //test with dummy data
        view.ShowQuestionList(datumList);
        Log.e("showload","loading");
        view.showLoading();
        Log.e("showload","loadingcomplete");
        view.setQuestion();

//      getQuestionsFromServer();
    }


    @Override
    public void getQuestionsFromServer() {
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);
        Call<Root> call = retrofitApi.getQuestions("2");
        call.enqueue(new Callback<Root>()
        {
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
                    for(DataDATA d:datumList)
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
                else if (response.errorBody() != null)
                {
                    try {
                        view.hideLoading();
                        Log.e("Qname","failed");
                        } catch (Exception e) {

                    }
                }

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t)
            {
                view.hideLoading();
            }

        });

    }

}
