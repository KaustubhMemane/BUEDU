package com.kmema.android.buedu;

import android.content.Context;
import android.content.SharedPreferences;

import com.kmema.android.buedu.R;

/**
 * Created by kmema on 11/8/2017.
 */

public class PreferenceManager {
    private Context context;
    private SharedPreferences sharedPreferences;

    PreferenceManager(Context context)
    {
        this.context = context;
        getSharedPreference();
    }

    private void getSharedPreference()
    {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.opened),Context.MODE_PRIVATE);
    }

    public void updateSharedPreference()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.opened),"INIT_OPENED");
        editor.commit();
    }

    public boolean checkPreference()
    {
        boolean status = false;
        if(sharedPreferences.getString(context.getString(R.string.opened),"null").equals("null"))
        {
         status = false;
        }
        else {
            status = true;
        }
        return status;
    }
    public void clearSharedPreferece()
    {
        sharedPreferences.edit().clear().commit();
    }
}
