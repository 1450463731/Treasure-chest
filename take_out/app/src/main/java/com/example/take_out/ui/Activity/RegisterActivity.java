package com.example.take_out.ui.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.take_out.R;
import com.example.take_out.UserInfoHolder;
import com.example.take_out.bean.User;
import com.example.take_out.biz.UserBiz;
import com.example.take_out.net.CommonCallback;
import com.example.take_out.utils.T;


public class RegisterActivity extends BaseActivity {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mEtRePassword;
    private Button mRtnRegister;


    private UserBiz mUserBiz = new UserBiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


//        获取 setUpToolbar()
        setUpToolbar();
//        初始化创建initView类
        initView();
//        初始化创建initEvent类
        initEvent();

        setTitle("注册");
    }
    private void initView () {
        mEtUsername = findViewById(R.id.id_et_username);
        mEtPassword = findViewById(R.id.id_et_password);
        mEtRePassword = findViewById(R.id.id_et_repassword);
        mRtnRegister = findViewById(R.id.id_btn_register);
    }


        private void initEvent () {
        mRtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoadingProgress();
                String Username = mEtUsername.getText().toString();
                String Password = mEtPassword.getText().toString();
                String RePassword = mEtRePassword.getText().toString();

                if(TextUtils.isEmpty(Username)||TextUtils.isEmpty(Password)){
                    T.showToast("账户或者密码不能为空");
                    return;
                }
                if (!Password.equals(RePassword)){
                    T.showToast("两次输入的密码不一致");
                    return;
                }

                startLoadingProgress();


                mUserBiz.register(Username, Password, new CommonCallback<User>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        T.showToast(e.getMessage());

                    }

                    @Override
                    public void onSuccess(User response) {
                        stopLoadingProgress();
                        T.showToast("注册成功,用户名为："+response.getUsernaem());

                        LoginActivity.launch(RegisterActivity.this,response.getUsernaem(),response.getPasswrod());
                        finish();

                    }
                });


            }
        });
        }





    }









