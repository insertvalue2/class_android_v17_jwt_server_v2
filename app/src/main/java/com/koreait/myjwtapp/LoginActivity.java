package com.koreait.myjwtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.koreait.myjwtapp.repository.JwtService;
import com.koreait.myjwtapp.repository.models.request.ReqLogin;
import com.koreait.myjwtapp.repository.models.response.ResLogin;
import com.koreait.myjwtapp.utils.KeyboardUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    static final String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText loginEt = findViewById(R.id.loginEt);
        EditText passwordEt = findViewById(R.id.passwordEt);
        Button loginBtn = findViewById(R.id.loginBtn);
        TextView moveLoginTv = findViewById(R.id.moveLoginTv);

        loginBtn.setOnClickListener(view -> {

            String id = loginEt.getText().toString();
            String pw = passwordEt.getText().toString();

            KeyboardUtil.hideKeyboard(view.getContext(), view);

            if (id.length() > 3 && pw.length() > 3) {
                JwtService jwtService = JwtService.retrofit.create(JwtService.class);
                ReqLogin reqLogin = new ReqLogin(id, pw);
                jwtService.getLogin(reqLogin).enqueue(new Callback<ResLogin>() {
                    @Override
                    public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                        if (response.isSuccessful()) {
                            ResLogin resLogin = response.body();
                            Log.d(TAG, resLogin.toString());
                            Log.d(TAG, response.headers().get("Authorization") + "");
                            //
                            SharedPreferences preferences = getSharedPreferences("token", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("jwt", response.headers().get("Authorization"));
                            Log.d(TAG, "session : " + response.headers().get("Set-Cookie"));
                            Log.d(TAG, "session : " + response.headers().get("Connection"));
                            Log.d(TAG, "session : " + response.headers().get("Transfer-Encoding"));

                            // 추가
                            editor.putString("username", resLogin.getData().getId().toString());
                            editor.putString("userId", resLogin.getData().getId().toString());
                            editor.apply();

                            Intent intent = new Intent(view.getContext(), MainActivity.class);
                            intent.putExtra("msg", resLogin.getMsg());
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<ResLogin> call, Throwable t) {
                        Snackbar.make(view, "연결 실패", Snackbar.LENGTH_SHORT).show();
                    }
                });

            } else {
//                Toast.makeText(view.getContext(), "잘못된 입력입니다.", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "잘못된 입력입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });

        moveLoginTv.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });


    }

}