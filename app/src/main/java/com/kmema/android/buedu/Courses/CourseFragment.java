package com.kmema.android.buedu.Courses;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kmema.android.buedu.CourseListDetailsFragment;
import com.kmema.android.buedu.Presenter;
import com.kmema.android.buedu.R;
import com.kmema.android.buedu.VPContracts;
import com.kmema.android.buedu.DataModel.ComputerScienceCoursesDataModel;

import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class CourseFragment extends Fragment implements CourseClickListener, VPContracts.FragmentCourseList {

    VPContracts.Presenter_Work presenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CourseFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new Presenter(context, this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        presenter.requestCSCourseList(view);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void courseRecyclerViewListner(View view, ComputerScienceCoursesDataModel computerScienceCoursesDataModel) {

        android.app.FragmentManager fragmentManager = getFragmentManager();
        CourseListDetailsFragment courseListDetailsFragment = new CourseListDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("CourseDetailsModel", computerScienceCoursesDataModel);
        courseListDetailsFragment.setArguments(bundle);
        courseListDetailsFragment.show(fragmentManager, "courseDialog");
    }

    @Override
    public void displayCourseList(View view, List<ComputerScienceCoursesDataModel> csCourses) {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyCourseRecyclerViewAdapter(csCourses, this, getContext()));
        }
    }

    @Override
    public void displayError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
