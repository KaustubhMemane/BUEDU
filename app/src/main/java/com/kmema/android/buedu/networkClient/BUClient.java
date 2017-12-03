package com.kmema.android.buedu.networkClient;

import com.kmema.android.buedu.DataModel.CSCourseInfoDataModel;
import com.kmema.android.buedu.DataModel.GradeA;
import com.kmema.android.buedu.DataModel.GradeB;
import com.kmema.android.buedu.DataModel.GradeC;

import retrofit2.Call;
import retrofit2.http.GET;


public interface BUClient {

    @GET("/bu_computer_science_master")
    Call<CSCourseInfoDataModel> getCSInfo();

    @GET("/bu_computer_science_master_courses")
    Call<CSCourses> getCSCourseInfo();

    @GET("/bu_computer_science_grade_a")
    Call<GradeA> getCSCourseGradeA();

    @GET("/bu_computer_science_grade_a")
    Call<GradeB> getCSCourseGradeB();

    @GET("/bu_computer_science_grade_a")
    Call<GradeC> getCSCourseGradeC();

}
