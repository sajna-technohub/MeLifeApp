package com.technohub.melifeapp.presenter;

import android.util.Log;
import com.technohub.melifeapp.Interfaces.Itestcategory;
import com.technohub.melifeapp.models.LoginResponse;
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

public class TestCategoryPresenter implements Itestcategory.Presenter {
    private Itestcategory.View view;
    TestCategoriesModel testCategoriesModel=new TestCategoriesModel();
    List<TestCategoriesModel> testList;
    User user;

    public TestCategoryPresenter(Itestcategory.View view,User user)
    {
        this.view = view;
        this.user=user;
    }
    @Override
    public void created() {

        view.init();
        load(user);

    }
    @Override
    public void load(User user) {

        view.showLoading();
        IRetrofitApi retrofitApi = ApiClient.getApiClient().create(IRetrofitApi.class);

        testCategoriesModel.setUser_email(user.getUser_email());
        testCategoriesModel.setUser_id(Integer.parseInt(user.getUserid()));

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
}
