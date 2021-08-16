package com.example.take_out.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.take_out.R;
import com.example.take_out.UserInfoHolder;
import com.example.take_out.bean.User;
import com.example.take_out.biz.UserBiz;
import com.example.take_out.net.CommonCallback;
import com.example.take_out.utils.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import okhttp3.CookieJar;

public class LoginActivity extends BaseActivity {

    private UserBiz mUserBiz = new UserBiz();

    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private TextView mTvRegister;

    private static  final String KEY_USERNAME="key_username";
    private static  final String KEY_PASSWORD="key_password";

    @Override
    protected void onResume() {
        super.onResume();
        CookieJarImpl cookieJar = (CookieJarImpl) OkHttpUtils
                .getInstance().getOkHttpClient().cookieJar();
        cookieJar.getCookieStore().removeAll();
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntent(intent);
    }

    private void initIntent(Intent intent) {
        if(intent == null){
            return;
        }
        String username =intent.getStringExtra(KEY_USERNAME);
        String password = intent.getStringExtra(KEY_PASSWORD);

        if (TextUtils.isEmpty(username)|| TextUtils.isEmpty(password)){
            return;
        }

        mEtUsername.setText(username);
        mEtPassword.setText(password);
    }


    public static void launch(Context context, String usernaem, String passwrod) {
        Intent intent = new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KEY_USERNAME, usernaem);
        intent.putExtra(KEY_PASSWORD, passwrod);
        context.startActivity(intent);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       


//        获取注册控件类
        initView();

//        点击事件类
        initEvent();


        initIntent(getIntent());

    }
//    点击事件类
    private void initEvent() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startLoadingProgress();
                String Username = mEtUsername.getText().toString();
                String Password = mEtPassword.getText().toString();

                if(TextUtils.isEmpty(Username)||TextUtils.isEmpty(Password)){
                    T.showToast("账户或者密码不能为空");
                    return;
                }

                mUserBiz.login(Username, Password, new CommonCallback<User>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        T.showToast(e.getMessage());

                    }

                    @Override
                    public void onSuccess(User response) {
                        stopLoadingProgress();
                        T.showToast("登录成功");
                        //保存用户信息
                        UserInfoHolder.getInstance().setUser(response);
                        toOrderActivity();

            }
        });


            }
        });
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterActivity();

            }


        });
    }




    //    跳转到RegisterActivity类
    private void toRegisterActivity() {
//        创建Intent
        Intent intent = new Intent(this,RegisterActivity.class);
//        跳转Activity
        startActivity(intent);

    }

    private void toOrderActivity() {
//        创建Intent
        Intent intent = new Intent(this,OrderActivity.class);
//        跳转Activity
        startActivity(intent);
//        销毁Activity
        finish();
    }

    private void initView() {
//        查找控件
        mEtUsername = findViewById(R.id.id_et_LoginUsername);
        mEtPassword = findViewById(R.id.id_et_LoginPassword);

        mBtnLogin = findViewById(R.id.id_btn_login);
        mTvRegister = findViewById(R.id.id_tv_register);
        
    }
}