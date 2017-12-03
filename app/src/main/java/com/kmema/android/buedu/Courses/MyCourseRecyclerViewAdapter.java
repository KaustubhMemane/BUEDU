package com.kmema.android.buedu.Courses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmema.android.buedu.R;
import com.kmema.android.buedu.networkClient.CSCourses;
import com.kmema.android.buedu.DataModel.ComputerScienceCoursesDataModel;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCourseRecyclerViewAdapter extends RecyclerView.Adapter<MyCourseRecyclerViewAdapter.ViewHolder> {

    private final List<ComputerScienceCoursesDataModel> mValues;
    CourseClickListener mListener;
    Context context;
    public MyCourseRecyclerViewAdapter(List<ComputerScienceCoursesDataModel> courses, CourseClickListener listener, Context context) {
        mValues = courses;
        this.context = context;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.setIsRecyclable(false);
        holder.mName.append(mValues.get(position).getName());
        holder.mSecotionName.append(mValues.get(position).getSection_name());
        holder.mSecotionName.setSelected(true);
        holder.mSecotionName.setMaxLines(1);
        holder.mTime.append(mValues.get(position).getTime());
        holder.mWhere.append(mValues.get(position).getWhere());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.courseRecyclerViewListner(v, mValues.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mName;
        final TextView mSecotionName;
        public CSCourses mItem;
        final TextView mTime;
        final TextView mWhere;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.tvName);
            mSecotionName = view.findViewById(R.id.tvSectionName);
            mTime = view.findViewById(R.id.tvTime);
            mWhere = view.findViewById(R.id.tvWhere);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}
