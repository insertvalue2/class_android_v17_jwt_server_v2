package com.koreait.myjwtapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.koreait.myjwtapp.adapter.BlogListAdapter;
import com.koreait.myjwtapp.repository.JwtService;
import com.koreait.myjwtapp.repository.models.response.ResPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogListFragment extends Fragment {

    private JwtService jwtService;
    private String token;
    private RecyclerView recyclerView;


    public BlogListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 토큰 : SharedPreferences
        SharedPreferences preferences = getActivity().getSharedPreferences("token", MODE_PRIVATE);
        token = preferences.getString("jwt", "");
        jwtService = JwtService.retrofit.create(JwtService.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_list, container, false);
        recyclerView = view.findViewById(R.id.blogListRv);
        requestPostData(token);

        return view;
    }


    private void requestPostData(String token) {
        jwtService.getPostList(token).enqueue(new Callback<ResPost>() {
            @Override
            public void onResponse(Call<ResPost> call, Response<ResPost> response) {
                ResPost resPost = response.body();

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                BlogListAdapter adapter = new BlogListAdapter(getContext());
                adapter.setItemData(resPost.data);

                recyclerView.hasFixedSize();
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResPost> call, Throwable t) {
                Toast.makeText(getContext(), "fail ~~ ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}