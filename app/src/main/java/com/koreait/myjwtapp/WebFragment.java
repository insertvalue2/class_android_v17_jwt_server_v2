package com.koreait.myjwtapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.koreait.myjwtapp.interfaces.OnBlogListRefresh;
import com.koreait.myjwtapp.repository.JwtService;
import com.koreait.myjwtapp.repository.models.request.ReqPost;
import com.koreait.myjwtapp.repository.models.response.ResPost;
import com.koreait.myjwtapp.repository.models.response.ResUpdatePost;
import com.koreait.myjwtapp.utils.BlogUtil;
import com.koreait.myjwtapp.utils.OnSingleClickListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/*


<activity android:name=".MainActivity" android:screenOrientation="portrait" android:windowSoftInputMode="adjustResize|adjustPan">
 </activity>

getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

stateUnspecified : 키보드 디폴트 설정 값
stateUnchanged : 키보드 마지막 상태 유지
stateHidden : 사용자 액티비티 선택 시 키보드 숨김
stateAlwaysHidden : 액티비티 메인 윈도우가 입력 포커스를 가질 때 키보드는 항상 숨김
stateVisible : 사용자가 액티비티 메인 윈도우 앞으로 갈 때 키보드 숨김
stateAlwayVisible : 사용자가 액티비티 선택할 때 키보드 보여줌
adjustUnspecified : 스크롤 할 수 있는 레이아웃 화면을 가지고 있다면 윈도우 크기 재 조정, 메인 윈도우 디폴트 값
adjustResize : 스크린 키보드 공간을 만들기 위해 메인 윈도우 크기를 재 조정
adjustPan : 키보드 공간을 만들기 위해 메인 윈도우의 크기가 재조정 되지 않음
* */

public class WebFragment extends Fragment {

    OnBlogListRefresh onBlogListRefresh;
    JwtService jwtService;


    public WebFragment(OnBlogListRefresh onBlogListRefresh) {
        this.onBlogListRefresh = onBlogListRefresh;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jwtService = JwtService.retrofit.create(JwtService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        Button blogSaveOkBtn = view.findViewById(R.id.blogSaveOkBtn);
        EditText writeTitleEt = view.findViewById(R.id.writeTitleEt);
        EditText writeContentEt = view.findViewById(R.id.writeContentEt);
        blogSaveOkBtn.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                ReqPost reqPost = new ReqPost(writeTitleEt.getText().toString(), writeContentEt.getText().toString());

                jwtService.savePost(BlogUtil.getToken(view.getContext()), reqPost).enqueue(new Callback<ResUpdatePost>() {
                    @Override
                    public void onResponse(Call<ResUpdatePost> call, Response<ResUpdatePost> response) {
                        writeTitleEt.setText("");
                        writeContentEt.setText("");
                        onBlogListRefresh.refresh(response.body().msg);
                    }

                    @Override
                    public void onFailure(Call<ResUpdatePost> call, Throwable t) {

                    }
                });
            }
        });

        return view;
    }
}