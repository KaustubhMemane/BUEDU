package com.kmema.android.buedu;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.kmema.android.buedu.DataModel.CSCourseInfoDataModel;
import com.kmema.android.buedu.networkClient.BUClient;
import com.kmema.android.buedu.networkClient.CSCourses;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Presenter implements VPContracts.Presenter_Work {


    private VPContracts.Fragment_Info infoFragment;
    private VPContracts.FragmentCourseList courseListFragment;
    private String BASE_URL = "http://kaustubhmemane.com";
    private Context context;

    public Presenter(Context context, VPContracts.Fragment_Info infoFragment) {
        this.infoFragment = infoFragment;
        this.context = context;
    }

    public Presenter(Context context, VPContracts.FragmentCourseList courseListFragment) {
        this.courseListFragment = courseListFragment;
        this.context = context;
    }


    @Override
    public void requestCSInformation() {
        Retrofit mRetrofit = getBuilder();
        BUClient buClient = mRetrofit.create(BUClient.class);
        Call<CSCourseInfoDataModel> call = buClient.getCSInfo();
        call.enqueue(new Callback<CSCourseInfoDataModel>() {
            @Override
            public void onResponse(Call<CSCourseInfoDataModel> call, Response<CSCourseInfoDataModel> response) {
                displayData(response);
            }

            @Override
            public void onFailure(Call<CSCourseInfoDataModel> call, Throwable t) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void requestCSCourseList(final View view) {
        Retrofit mRetrofit = getBuilder();
        BUClient buClient = mRetrofit.create(BUClient.class);

        Call<CSCourses> call = buClient.getCSCourseInfo();

        call.enqueue(new Callback<CSCourses>() {
            @Override
            public void onResponse(Call<CSCourses> call, Response<CSCourses> response) {
                displayCourseList(view, response);
            }

            @Override
            public void onFailure(Call<CSCourses> call, Throwable t) {
                String ERROR_MESSAGE = "Error In Displaying List";
                courseListFragment.displayError(ERROR_MESSAGE + t.getMessage());
            }
        });
    }

    private void displayCourseList(View view, Response<CSCourses> response) {

        if (response != null) {
            courseListFragment.displayCourseList(view, response.body().getListOfCourses());
        } else {
            String ERROR_MESSAGE = "Error In Displaying List";
            courseListFragment.displayError(ERROR_MESSAGE);
        }
    }


    private Retrofit getBuilder() {
        int cacheSize = 10 * 1024;
        okhttp3.Cache cache = new okhttp3.Cache(context.getCacheDir(), cacheSize);

        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        OkHttpClient okHttpClient1 = okHttpClient.cache(cache).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient1)
                .addConverterFactory(GsonConverterFactory
                        .create());
        return builder.build();
    }


    private void displayData(Response<CSCourseInfoDataModel> response) {
        if (response == null) {
            String ERROR_MESSAGE = "No response from server!";
            infoFragment.displayError(ERROR_MESSAGE);
        } else {
            infoFragment.displayInformation(response);
        }
    }
}
