package com.kmema.android.buedu;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.solver.Cache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kmema.android.buedu.networkClient.BUClient;
import com.kmema.android.buedu.networkClient.CSCourseInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    @BindView(R.id.tvInformation)
    public TextView textViewInformation;

    @BindView(R.id.tvOptionNote)
    public TextView textViewOptionNote;

    @BindView(R.id.tvOptions)
    public TextView textViewOptions;

    @BindView(R.id.tvNote)
    public TextView textViewNote;

    @BindView(R.id.tvCoreCourseNote)
    public TextView textViewCoreCoursesNote;

    @BindView(R.id.tvCoreCoursesList)
    public TextView textViewCoreCoursesList;

    @BindView(R.id.tvCoursesNote)
    public TextView textViewCoursesNote;

    @BindView(R.id.tvCoursesList)
    public TextView textViewCoursesList;

    public static final String baseURL = "http://kaustubhmemane.com";

    Unbinder unbinder;

    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_information, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        requestCSInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void requestCSInfo() {
        int cacheSize  = 10 * 1024;
        okhttp3.Cache cache = new okhttp3.Cache(getContext().getCacheDir(),cacheSize);

        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        OkHttpClient okHttpClient1 = okHttpClient.cache(cache).build();

        Retrofit.Builder  builder = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient1)
                .addConverterFactory(GsonConverterFactory
                        .create());


        Retrofit mRetrofit = builder.build();

        BUClient buClient = mRetrofit.create(BUClient.class);
        Call<CSCourseInfo> call = buClient.getCSInfo();
        call.enqueue(new Callback<CSCourseInfo>() {
            @Override
            public void onResponse(Call<CSCourseInfo> call, Response<CSCourseInfo> response) {
                String s = response.body().getCore_courses_note();
//                Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
                displayData(response);
            }

            @Override
            public void onFailure(Call<CSCourseInfo> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayData(Response<CSCourseInfo> response) {
        if (response == null || textViewInformation == null)
        {
            return;
        }

        textViewInformation.setText(response.body().getInformation());
        textViewOptionNote.setText(response.body().getOption_note());

        List<String> Options = response.body().getOptions();
        int i = 0;
        for (String option : Options)
        {
            i++;
            textViewOptions.append(i+" "+option+"\n");
        }

        textViewNote.setText(response.body().getNote());
        textViewCoreCoursesNote.setText(response.body().getCore_courses_note());

        List<String> coreCourses = response.body().getCore_courses();
        for (String coreCourse : coreCourses)
        {
            textViewCoreCoursesList.append(coreCourse+"\n");
        }
        textViewCoursesNote.setText(response.body().getCore_courses_note());
        List<String> courses = response.body().getCourses();
        for(String course : courses)
        {
            textViewCoursesList.append(course+"\n");
        }
    }
}
