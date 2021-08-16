package com.example.take_out;

import android.app.Application;

import com.example.take_out.utils.SPUtils;
import com.example.take_out.utils.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class ResApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        T.init(this);
        SPUtils.init(this,"sp_user.pref");

        CookieJarImpl cookieJar = new CookieJarImpl(
                new PersistentCookieStore(getApplicationContext()));

        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(100000L,TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                .build();



        OkHttpUtils.initClient(okHttpClient);

    }
}
