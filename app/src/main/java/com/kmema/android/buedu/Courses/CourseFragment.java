package com.kmema.android.buedu.Courses;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kmema.android.buedu.CourseListDetailsFragment;
import com.kmema.android.buedu.R;
import com.kmema.android.buedu.networkClient.BUClient;
import com.kmema.android.buedu.networkClient.CSCourses;
import com.kmema.android.buedu.DataModel.ComputerScienceCoursesDataModel;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class CourseFragment extends Fragment implements CourseClickListener {
    private static final String baseURL = "http://kaustubhmemane.com";
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CourseFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        requestCSInfo(view);
        // Set the adapter
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void requestCSInfo(final View view) {
        int cacheSize = 10 * 1024;
        okhttp3.Cache cache = new okhttp3.Cache(getContext().getCacheDir(), cacheSize);

        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        OkHttpClient okHttpClient1 = okHttpClient.cache(cache).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient1)
                .addConverterFactory(GsonConverterFactory
                        .create());

        Retrofit mRetrofit = builder.build();

        BUClient buClient = mRetrofit.create(BUClient.class);
        Call<CSCourses> call = buClient.getCSCourseInfo();
        call.enqueue(new Callback<CSCourses>() {
            @Override
            public void onResponse(Call<CSCourses> call, Response<CSCourses> response) {
                attachListToRecycler(view, response.body().getListOfCourses());
            }

            @Override
            public void onFailure(Call<CSCourses> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                Log.e("COURSE LIST ERROR", t.getLocalizedMessage());
            }
        });
    }


    private void attachListToRecycler(View view, List<ComputerScienceCoursesDataModel> csCourses) {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyCourseRecyclerViewAdapter(csCourses, this, getContext()));
        }
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
}
