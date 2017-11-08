package com.kmema.android.buedu.networkClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kmema on 10/31/2017.
 */

public interface BUClient {

    @GET("/bu_computer_science_master")
    Call<CSCourseInfo> getCSInfo();

    @GET("/bu_computer_science_master_courses")
    Call<CSCourses> getCSCourseInfo();

}
