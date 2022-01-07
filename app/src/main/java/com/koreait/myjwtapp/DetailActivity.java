package com.koreait.myjwtapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.koreait.myjwtapp.interfaces.OnBlogListRefresh;
import com.koreait.myjwtapp.repository.JwtService;
import com.koreait.myjwtapp.repository.models.request.ReqPost;
import com.koreait.myjwtapp.repository.models.response.ResPost;
import com.koreait.myjwtapp.repository.models.response.ResUpdatePost;
import com.koreait.myjwtapp.repository.models.response.common.Result;
import com.koreait.myjwtapp.utils.BlogUtil;
import com.koreait.myjwtapp.utils.OnSingleClickListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    static final String TAG = DetailActivity.class.getName();
    private EditText detailTitleEt;
    private EditText detailContentEt;
    private Button bolgUpdateBtn;
    private Button blogDeleteBtn;
    private Button blogUpdateOkBtn;
    private JwtService jwtService;

    private ResPost.Data data;
    private String myId;
    private OnBlogListRefresh onBlogListRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        if (getIntent() != null) {
            data = (ResPost.Data) getIntent().getSerializableExtra("postData");
            initData();
            setData();
            addEventListener();

        }
    }

    private void initData() {
        detailTitleEt = findViewById(R.id.detailTitleEt);
        detailContentEt = findViewById(R.id.detailContentEt);

        bolgUpdateBtn = findViewById(R.id.blogUpdateBtn);
        blogDeleteBtn = findViewById(R.id.blogDeleteBtn);
        blogUpdateOkBtn = findViewById(R.id.blogUpdateOkBtn);

        SharedPreferences preferences = getSharedPreferences("token", MODE_PRIVATE);
        myId = preferences.getString("userId", "");
        jwtService = JwtService.retrofit.create(JwtService.class);

    }

    private void setData() {
        detailTitleEt.setText(data.title);
        detailContentEt.setText(data.content);

        if (myId.equals(data.user.id.toString())) {
            bolgUpdateBtn.setVisibility(View.VISIBLE);
            blogDeleteBtn.setVisibility(View.VISIBLE);
        } else {
            bolgUpdateBtn.setVisibility(View.GONE);
            blogDeleteBtn.setVisibility(View.GONE);
        }
    }

    private void addEventListener() {
        bolgUpdateBtn.setOnClickListener(view -> {
            blogUpdateOkBtn.setVisibility(View.VISIBLE);
            detailTitleEt.setEnabled(true);
            detailContentEt.setEnabled(true);
        });

        blogDeleteBtn.setOnClickListener(view -> {
            jwtService.deletePost(BlogUtil.getToken(view.getContext()), data.id).enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Snackbar.make(view, response.body().getMsg(), Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Snackbar.make(view, view.getContext().getResources().getString(R.string.str_connect_fail), Snackbar.LENGTH_SHORT).show();
                }
            });
        });

        blogUpdateOkBtn.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                ReqPost reqPost = new ReqPost(detailTitleEt.getText().toString(),
                        detailContentEt.getText().toString());

                jwtService.updatePost(BlogUtil.getToken(v.getContext()), reqPost, data.id).enqueue(new Callback<ResUpdatePost>() {
                    @Override
                    public void onResponse(Call<ResUpdatePost> call, Response<ResUpdatePost> response) {
                        ResUpdatePost resUpdatePost = response.body();
                        if (resUpdatePost != null) {
                            Snackbar.make(v, resUpdatePost.msg, Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResUpdatePost> call, Throwable t) {
                        Snackbar.make(v, v.getContext().getResources()
                                .getString(R.string.str_connect_fail), Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}



