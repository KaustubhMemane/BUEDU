package com.kmema.android.buedu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.kmema.android.buedu.networkClient.BUClient;
import com.kmema.android.buedu.networkClient.CourseInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String baseURL = "http://kaustubhmemane.com";


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ///Creating Android project
        Retrofit.Builder  builder = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory
                        .create());


        Retrofit mRetrofit = builder.build();

        BUClient buClient = mRetrofit.create(BUClient.class);
        Call<CourseInfo> call = buClient.getCSInfo();
        call.enqueue(new Callback<CourseInfo>() {
            @Override
            public void onResponse(Call<CourseInfo> call, Response<CourseInfo> response) {
                String s = response.body().getCore_courses_note();
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
                displayData(response);
            }

            @Override
            public void onFailure(Call<CourseInfo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayData(Response<CourseInfo> response) {
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
