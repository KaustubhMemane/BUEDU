package com.kmema.android.buedu;
import android.view.View;

import com.kmema.android.buedu.DataModel.CSCourseInfoDataModel;
import com.kmema.android.buedu.DataModel.ComputerScienceCoursesDataModel;

import java.util.List;

import retrofit2.Response;

public interface VPContracts {

    interface Presenter_Work{
        void requestCSInformation();
        void requestCSCourseList(View view);
    }

    interface Fragment_Info{
        void displayInformation(Response<CSCourseInfoDataModel> csCourseInfoDataModelResponse);
        void displayError(String errorMessage);
    }

    interface FragmentCourseList{
        void displayCourseList(View view, List<ComputerScienceCoursesDataModel> csCourses);
        void displayError(String errorMessage);
    }
}
