package com.kmema.android.buedu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmema.android.buedu.CourseFragment.OnListFragmentInteractionListener;
import com.kmema.android.buedu.dummy.DummyContent.DummyItem;
import com.kmema.android.buedu.networkClient.CSCourses;
import com.kmema.android.buedu.networkClient.ComputerScienceCourses;

import org.w3c.dom.Text;

import java.util.List;
import java.util.logging.Handler;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCourseRecyclerViewAdapter extends RecyclerView.Adapter<MyCourseRecyclerViewAdapter.ViewHolder> {

    private final List<ComputerScienceCourses> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyCourseRecyclerViewAdapter(List<ComputerScienceCourses> courses, OnListFragmentInteractionListener listener) {
        mValues = courses;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        // TODO: 11/9/2017 remove all setText and add seperate text box and check data before displaying
        holder.mName.setText(R.string.name);
        holder.mName.append(mValues.get(position).getName());

        holder.mSecotionName.setText(R.string.SectionName);
        holder.mSecotionName.append(mValues.get(position).getSection_name());

        holder.mDescription.setText(R.string.Description);
        holder.mDescription.append(mValues.get(position).getDescription());

        holder.mAssociatedTerm.setText(R.string.AssociatedTerm);
        holder.mAssociatedTerm.append(mValues.get(position).getAssociated_Term());

        holder.mRegistrationDates.setText(R.string.RegistrationDate);
        holder.mRegistrationDates.append(mValues.get(position).getRegistration_dates());

        holder.mLevels.setText(R.string.Levels);
        holder.mLevels.append(mValues.get(position).getLevels());

        holder.mNote.setText(R.string.Note);
        holder.mNote.append(mValues.get(position).getNote());

        holder.mCredit.setText(R.string.Credit);
        holder.mCredit.append(mValues.get(position).getCredit());

        holder.mType.setText(R.string.Type);
        holder.mType.append(mValues.get(position).getType());

        holder.mProfessor.setText(R.string.Professor);
        holder.mProfessor.append(mValues.get(position).getProfessor());

        holder.mTime.setText(R.string.Time);
        holder.mTime.append(mValues.get(position).getTime());

        holder.mDays.setText(R.string.Days);
        holder.mDays.append(mValues.get(position).getDays());

        holder.mWhere.setText(R.string.Where);
        holder.mWhere.append(mValues.get(position).getWhere());

        holder.mDateRange.setText(R.string.DateRange);
        holder.mDateRange.append(mValues.get(position).getDate_range());

        holder.mScheduleType.setText(R.string.ScheduleType);
        holder.mScheduleType.append(mValues.get(position).getSchedule_type());

/*        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         final View mView;
         final TextView mName;
         final TextView mSecotionName;
        final  TextView mDescription;
        final TextView mAssociatedTerm;
        final TextView mRegistrationDates;
        final TextView mLevels;
        final TextView mNote;
        final TextView mCredit;
        final TextView mType;
        final TextView mProfessor;
        final TextView mTime;
        final TextView mDays;
        final TextView mWhere;
        final TextView mDateRange;
        final TextView mScheduleType;
        public CSCourses mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.tvName);
            mSecotionName = view.findViewById(R.id.tvSectionName);
            mDescription = view.findViewById(R.id.tvDescription);
            mAssociatedTerm = view.findViewById(R.id.tvAssociatedTerm);
            mRegistrationDates = view.findViewById(R.id.tvRegistrationDates);
            mLevels = view.findViewById(R.id.tvLevels);
            mNote = view.findViewById(R.id.tvNote);
            mCredit = view.findViewById(R.id.tvCredit);
            mType = view.findViewById(R.id.tvType);
            mProfessor = view.findViewById(R.id.tvProfessor);
            mTime = view.findViewById(R.id.tvTime);
            mDays = view.findViewById(R.id.tvDays);
            mWhere = view.findViewById(R.id.tvWhere);
            mDateRange = view.findViewById(R.id.tvDateRange);
            mScheduleType = view.findViewById(R.id.tvScheduleType);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}
