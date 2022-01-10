package com.koreait.myjwtapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.koreait.myjwtapp.repository.JwtService;
import com.koreait.myjwtapp.repository.models.response.ResLogin;
import com.koreait.myjwtapp.utils.BlogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*

 {"code":1,"msg":"회원정보확인완료",
     "data":{
        "id":5,
         "username":"questzz1",
         "password":"5678",
         "email":"test1@gmail.com",
         "created":"2022-25-05T05:25:11",
         "updated":"2022-36-05T05:36:10"
         }
 }

* */
public class MyInfoFragment extends Fragment {

    private static final String TAG = MyInfoFragment.class.getName();
    private JwtService jwtService;
    private ResLogin myInfo;
    private ImageView userImg;
    private TextView userNameTv;
    private TextView userEmailTv;
    private Button myInfoUpdateBtn;
    private Button logoutBtn;


    public MyInfoFragment() {
        // Required empty public constructor
    }


    public static MyInfoFragment newInstance(String param1, String param2) {
        return new MyInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jwtService = JwtService.retrofit.create(JwtService.class);
        jwtService.myInfo(BlogUtil.getToken(getContext()),
                Integer.parseInt(BlogUtil.getMyId(getContext()))).enqueue(new Callback<ResLogin>() {
            @Override
            public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                myInfo = response.body();

                Log.d(TAG, myInfo.getData().getEmail());
            }

            @Override
            public void onFailure(Call<ResLogin> call, Throwable t) {
                Log.d(TAG, "오류 확인 : " + t.toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInfoView = inflater.inflate(R.layout.fragment_my_info, container, false);
        requestMyInfo();
        initData(myInfoView);
        addEventListener();
        return myInfoView;
    }

    private void initData(View view) {
        userImg = view.findViewById(R.id.userImage);
        userNameTv = view.findViewById(R.id.userNameTv);
        userEmailTv = view.findViewById(R.id.userEmailTv);
        myInfoUpdateBtn = view.findViewById(R.id.myInfoUpdateBtn);
        logoutBtn = view.findViewById(R.id.logoutBtn);


    }

    private void addEventListener() {
        myInfoUpdateBtn.setOnClickListener(view -> {

        });
        logoutBtn.setOnClickListener(view -> {
            SharedPreferences preferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("jwt", "");
            editor.apply();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }

    private void requestMyInfo() {
        jwtService.myInfo(BlogUtil.getToken(getContext()),
                Integer.parseInt(BlogUtil.getMyId(getContext()))).enqueue(new Callback<ResLogin>() {
            @Override
            public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                myInfo = response.body();
                userNameTv.setText(myInfo.getData().getUsername());
                userEmailTv.setText(myInfo.getData().getEmail());
                Log.d(TAG, myInfo.getData().getEmail());
            }

            @Override
            public void onFailure(Call<ResLogin> call, Throwable t) {
                Log.d(TAG, "오류 확인 : " + t.toString());
            }
        });
    }
}