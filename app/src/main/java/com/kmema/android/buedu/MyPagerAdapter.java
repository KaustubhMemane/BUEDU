package com.kmema.android.buedu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MyPagerAdapter extends PagerAdapter {

    private int[] mLayouts;
    private LayoutInflater mLayoutInflater;
    private Context mContext;


    MyPagerAdapter(int[] mLayouts,Context mContext)
    {
        this.mContext = mContext;
        this.mLayouts = mLayouts;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mLayouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = mLayoutInflater.inflate(mLayouts[position],container,false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        View view = (View)object;
        container.removeView(view);
    }
}
