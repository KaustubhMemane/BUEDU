package com.kmema.android.buedu;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmema.android.buedu.DataModel.ComputerScienceCoursesDataModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kmema on 12/2/2017.
 */

public class CourseListDetailsFragment extends DialogFragment {


    @BindView(R.id.tvAssociatedTermDetail)
    TextView textViewAssociatedTerm;

    @BindView(R.id.tvCreditDetail)
    TextView textViewCredit;

    @BindView(R.id.tvDateRangeDetail)
    TextView textViewDateRange;

    @BindView(R.id.tvDaysDetail)
    TextView textViewDays;

    @BindView(R.id.tvDescriptionDetail)
    TextView textViewDescription;

    @BindView(R.id.tvLevelsDetail)
    TextView textViewLevels;

    @BindView(R.id.tvNoteDetail)
    TextView textViewNote;

    @BindView(R.id.tvProfessorDetail)
    TextView textViewProfessor;

    @BindView(R.id.tvRegistrationDatesDetail)
    TextView textViewRegistrationDates;

    @BindView(R.id.tvScheduleTypeDetail)
    TextView textViewScheduleType;

    @BindView(R.id.tvTypeDetail)
    TextView textViewTypeDetail;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_course_fragment, container);
        unbinder = ButterKnife.bind(this,view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ComputerScienceCoursesDataModel computerScienceCoursesDataModel = (ComputerScienceCoursesDataModel) getArguments().getSerializable("CourseDetailsModel");
        textViewDescription.setText(computerScienceCoursesDataModel.getDescription());
        textViewDescription.setMovementMethod(new ScrollingMovementMethod());
        textViewAssociatedTerm.setText(computerScienceCoursesDataModel.getAssociated_Term());
        textViewAssociatedTerm.setSelected(true);

        textViewCredit.setText(computerScienceCoursesDataModel.getCredit());
        textViewCredit.setSelected(true);
        textViewDateRange.setText(computerScienceCoursesDataModel.getDate_range());
        textViewDateRange.setSelected(true);
        textViewDays.setText(computerScienceCoursesDataModel.getDays());
        textViewDays.setSelected(true);
        textViewLevels.setText(computerScienceCoursesDataModel.getLevels());
        textViewLevels.setSelected(true);
        textViewNote.setText(computerScienceCoursesDataModel.getNote());
        textViewNote.setSelected(true);
        textViewProfessor.setText(computerScienceCoursesDataModel.getProfessor());
        textViewProfessor.setSelected(true);
        textViewRegistrationDates.setText(computerScienceCoursesDataModel.getRegistration_dates());
        textViewRegistrationDates.setSelected(true);
        textViewScheduleType.setText(computerScienceCoursesDataModel.getSchedule_type());
        textViewScheduleType.setSelected(true);
        textViewTypeDetail.setText(computerScienceCoursesDataModel.getType());
        textViewTypeDetail.setSelected(true);
        return view;
    }
}
