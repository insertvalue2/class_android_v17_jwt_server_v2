package com.koreait.myjwtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.koreait.myjwtapp.repository.JwtService;
import com.koreait.myjwtapp.repository.models.request.ReqSignup;
import com.koreait.myjwtapp.repository.models.response.ResSignup;
import com.koreait.myjwtapp.utils.KeyboardUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    static final String TAG = SignupActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        EditText userIdEt = findViewById(R.id.userIdEt);
        EditText userEmailEt = findViewById(R.id.userEmailEt);
        EditText passwordEt = findViewById(R.id.passwordEt);
        EditText rePasswordEt = findViewById(R.id.rePasswordEt);

        Button signUpBtn = findViewById(R.id.signUpBtn);
        TextView goSignInPage = findViewById(R.id.goSignInPage);

        signUpBtn.setOnClickListener(view -> {
            // 지역변수
            String id = userIdEt.getText().toString();
            String pw = passwordEt.getText().toString();
            String rePw = rePasswordEt.getText().toString();
            String email = userEmailEt.getText().toString();

            if (id.length() > 3 && pw.length() > 3 && email.length() > 3) {
                KeyboardUtil.hideKeyboard(view.getContext(), view);
                if (!KeyboardUtil.isValidEmail(email)) {
                    Snackbar.make(view, "이메일 형식이 아닙니다.", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (pw.equals(rePw)) {
                    // todo
                    // 여기서 회원가입 요청하기
                    JwtService jwtService = JwtService.retrofit.create(JwtService.class);
                    ReqSignup reqSignup = new ReqSignup(id, pw, email);
                    jwtService.saveMember(reqSignup).enqueue(new Callback<ResSignup>() {
                        @Override
                        public void onResponse(Call<ResSignup> call, Response<ResSignup> response) {
                            ResSignup resSignup = response.body();
                            Snackbar.make(view, resSignup.getMsg(), Snackbar.LENGTH_SHORT).show();
                            // 콜백메서드 만들어서 msg 전달
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ResSignup> call, Throwable t) {
                            Snackbar.make(view, "회원가입 실패", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Snackbar.make(view, "비밀번호가 다릅니다.", Snackbar.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(view, "잘못된 입력입니다.", Snackbar.LENGTH_SHORT).show();
            }

        });

        goSignInPage.setOnClickListener(view -> {
            finish();
        });

    }
}