package com.kmema.android.buedu;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.kmema.android.buedu.Courses.CourseFragment;
import com.kmema.android.buedu.Information.InformationFragment;
import com.kmema.android.buedu.Suggestion.SuggestionFragment;

public class MainActivity extends AppCompatActivity {

    InformationFragment informationFragment;
    CourseFragment courseFragment;
    SuggestionFragment suggestionFragment;

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId())
            {
                case R.id.navInformation:
                    if(informationFragment != null && !informationFragment.isVisible())
                        getFragmentManager().beginTransaction().replace(R.id.containerLayout, informationFragment, informationFragment.getClass().getSimpleName()).commit();

                    return true;

                case R.id.navListCourses:
                    if(courseFragment != null && !courseFragment.isVisible())
                        getFragmentManager().beginTransaction().replace(R.id.containerLayout, courseFragment, courseFragment.getClass().getSimpleName()).commit();

                    return true;

                case R.id.navSuggestion:
                    if(suggestionFragment != null && !suggestionFragment.isVisible())
                        getFragmentManager().beginTransaction().replace(R.id.containerLayout,suggestionFragment, suggestionFragment.getClass().getSimpleName()).commit();

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        informationFragment = new InformationFragment();
        courseFragment = new CourseFragment();
        suggestionFragment = new SuggestionFragment();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        InformationFragment informationFragment = new InformationFragment();
        getFragmentManager().beginTransaction().replace(R.id.containerLayout, informationFragment, informationFragment.getTag()).commit();
    }
}
