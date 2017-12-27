package com.kmema.android.buedu.Information;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kmema.android.buedu.Presenter;
import com.kmema.android.buedu.R;
import com.kmema.android.buedu.VPContracts;
import com.kmema.android.buedu.DataModel.CSCourseInfoDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment implements VPContracts.Fragment_Info{

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

    Unbinder unbinder;
    VPContracts.Presenter_Work presenter_work;


    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        presenter_work.requestCSInformation();
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
        presenter_work = new Presenter(context,this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void displayInformation(Response<CSCourseInfoDataModel> response) {

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

    @Override
    public void displayError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
