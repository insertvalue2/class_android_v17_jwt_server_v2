package com.koreait.myjwtapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.koreait.myjwtapp.interfaces.OnBlogListRefresh;
import com.koreait.myjwtapp.utils.OnSingleClickListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebFragment extends Fragment implements OnBlogListRefresh {

    OnBlogListRefresh onBlogListRefresh;


    @Override
    public void refresh() {

    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WebFragment(OnBlogListRefresh onBlogListRefresh) {
        this.onBlogListRefresh = onBlogListRefresh;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        Button blogSaveOkBtn = view.findViewById(R.id.blogSaveOkBtn);
        blogSaveOkBtn.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                onBlogListRefresh.refresh();

            }
        });

        return view;
    }
}