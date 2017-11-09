package com.kmema.android.buedu;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Introduction extends AppCompatActivity implements View.OnClickListener{


    private ViewPager mPager;
    private int[] layouts = {R.layout.intro_one,R.layout.intro_two, R.layout.intro_three};
    private MyPagerAdapter myPagerAdapter;
    private LinearLayout dotsLayout;
    private ImageView[] dots;
    private Button buttonNext, buttonSkip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(new PreferenceManager(this).checkPreference())
        {
            loadMainActivity();
        }
        setContentView(R.layout.activity_introduction);


        if (Build.VERSION.SDK_INT >= 19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mPager = findViewById(R.id.viewPager);
        myPagerAdapter = new MyPagerAdapter(layouts, this);
        mPager.setAdapter(myPagerAdapter);
        dotsLayout = findViewById(R.id.dotLayout);
        buttonNext = findViewById(R.id.btnNext);
        buttonSkip = findViewById(R.id.btnSkip);
        buttonSkip.setOnClickListener(this);
        buttonNext.setOnClickListener(this);

        createDots(0);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position == layouts.length-1)
                {
                    buttonNext.setText("Start");
                    buttonSkip.setVisibility(View.INVISIBLE);
                }
                else {
                    buttonNext.setText("Next");
                    buttonSkip.setVisibility(View.VISIBLE);
                }
                }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDots(int current_position)
    {
        if(dotsLayout != null)
        {
            dotsLayout.removeAllViews();
        }

        dots = new ImageView[layouts.length];
        int size = layouts.length;
        for(int i = 0; i < size;i++)
        {
            dots[i] = new ImageView(this);
            if(i == current_position)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_dots));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            dotsLayout.addView(dots[i],params);
        }
    }

    @Override
    public void onClick(View view) {
    switch (view.getId())
    {
        case R.id.btnNext:
            loadNextSlide();
            break;

        case R.id.btnSkip:
            loadMainActivity();
            new PreferenceManager(this).updateSharedPreference();
            break;
    }
    }

    private void loadMainActivity()
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void loadNextSlide()
    {
        int next = mPager.getCurrentItem()+1;
        if (next<layouts.length)
        {
            mPager.setCurrentItem(next);
        }
        else
        {
            loadMainActivity();
            new PreferenceManager(this).updateSharedPreference();
        }
    }
}
