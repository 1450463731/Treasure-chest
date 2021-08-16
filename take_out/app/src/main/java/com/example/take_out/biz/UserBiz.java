package com.example.take_out.biz;

import com.example.take_out.Config.Config;
import com.example.take_out.bean.User;
import com.example.take_out.net.CommonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;

public class UserBiz {

    public void  login(String username, String password,
                       CommonCallback<User> commonCallback ){

        OkHttpUtils
                .post()
                .url(Config.baseUrl+"user_login")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallback);

    }

    public void  register(String username, String password,
                          CommonCallback<User> commonCallback){
        OkHttpUtils
                .post()
                .url(Config.baseUrl+"user_register")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallback);

    }
}
