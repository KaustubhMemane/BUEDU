package com.kmema.android.buedu.Suggestion;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kmema.android.buedu.MyItemRecyclerViewAdapter;
import com.kmema.android.buedu.R;
import com.kmema.android.buedu.networkClient.BUClient;
import com.kmema.android.buedu.DataModel.CoursesGradeADataModel;
import com.kmema.android.buedu.DataModel.GradeA;

import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class GradeFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GradeFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GradeFragment newInstance(int columnCount) {
        GradeFragment fragment = new GradeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_grade_suggestion_list, container, false);

        requestGradeSuggetion(view);
/*        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }*/
        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
/*
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name

    }
    private static final String baseURL = "http://kaustubhmemane.com";
    private void requestGradeSuggetion(final View view) {
        int cacheSize = 10 * 1024;

        Cache cache = new Cache(getContext().getCacheDir(),cacheSize);

        OkHttpClient.Builder  okHttpClient =  new OkHttpClient().newBuilder();
        OkHttpClient okHttpClient1 = okHttpClient.cache(cache).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient1)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit mRetrofit = builder.build();

        BUClient buClient = mRetrofit.create(BUClient.class);
        Call<GradeA> call = buClient.getCSCourseGradeA();
        call.enqueue(new Callback<GradeA>() {
            @Override
            public void onResponse(Call<GradeA> call, Response<GradeA> response) {
                attachSuggestionsToRecycler(view, response.body().getCoursesGradeADataModels());
            }

            @Override
            public void onFailure(Call<GradeA> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                Log.e("Suggestion ERROR",t.getLocalizedMessage());
            }
        });
    }

    private void attachSuggestionsToRecycler(View view, List<CoursesGradeADataModel> coursesGradeADataModels) {
        if(view instanceof RecyclerView)
        {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(coursesGradeADataModels, mListener));
        }
    }
}
