package com.koreait.myjwtapp.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtil {

    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        Log.d("TAG", "Patterns.EMAIL_ADDRESS.matcher(target).matches() : " + Patterns.EMAIL_ADDRESS.matcher(target).matches());
        Log.d("TAG", "TextUtils.isEmpty(target) : " + TextUtils.isEmpty(target));
        return (! TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
