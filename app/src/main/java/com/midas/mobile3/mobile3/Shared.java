package com.midas.mobile3.mobile3;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by myRoom on 2017-05-28.
 */

public class Shared {

    private static SharedPreferences preferences;

    public static void setIsAdmin(Context context, boolean is){
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isAdmin",is);
        editor.commit();
    }

    public static boolean getIsAdmin(Context context){
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return preferences.getBoolean("isAdmin",false);
    }

    public static void setIsFirst(Context context, boolean is){
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirst",is);
        editor.commit();
    }

    public static boolean getIsFirst(Context context){
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return preferences.getBoolean("isFirst",true);
    }


    public static void setIsLogin(Context context, boolean is){
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLogin",is);
        editor.commit();
    }

    public static boolean getIsLogin(Context context){
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return preferences.getBoolean("isLogin",false);
    }


}