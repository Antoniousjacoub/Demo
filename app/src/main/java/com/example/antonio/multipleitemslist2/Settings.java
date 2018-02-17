package com.example.antonio.multipleitemslist2;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by antonio on 2/10/18.
 */

public class Settings {
    public static  boolean isConnectingToInternet(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }

   public static boolean setPreference(Context c, String value, String key) {
        SharedPreferences settings = c.getSharedPreferences("photoPref", 0);
        settings = c.getSharedPreferences("photoPref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

   public static String getPreference(Context c, String key) {
        SharedPreferences settings = c.getSharedPreferences("photoPref", 0);
        settings = c.getSharedPreferences("photoPref", 0);
        String value = settings.getString(key, "");
        return value;
    }
}
