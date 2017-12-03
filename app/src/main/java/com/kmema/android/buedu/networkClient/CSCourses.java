package com.kmema.android.buedu.networkClient;

import com.kmema.android.buedu.DataModel.ComputerScienceCoursesDataModel;

import java.util.List;

/**
 * Created by kmema on 11/2/2017.
 */

public class CSCourses {

    List<ComputerScienceCoursesDataModel> courses;

    public List<ComputerScienceCoursesDataModel> getListOfCourses() {
        return courses;
    }

    public void setListOfCourses(List<ComputerScienceCoursesDataModel> courses) {
        this.courses = courses;
    }

}
