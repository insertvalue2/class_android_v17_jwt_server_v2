package com.koreait.myjwtapp.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

public class BlogUtil {

    public static String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("token", MODE_PRIVATE);
        return preferences.getString("jwt", "");
    }

    public static String getMyId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("token", MODE_PRIVATE);
        return preferences.getString("userId", "");
    }
}
