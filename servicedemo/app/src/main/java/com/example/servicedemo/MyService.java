package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


public class MyService extends Service {
    private  Intent intent =new Intent();
    private int i;

    public MyService(){


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.e("TAG","服务绑定了");
// 通过Binder代替IBinder
        return new MyBinder();
    }
//创建MyBinder继承Binder
    class MyBinder extends Binder{
        public int getProcess(){
            return i;
        }

    }

//    创建服务
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG","服务创建成功");
        new Thread(){
            @Override
            public void run() {
                super.run();
                    try {
                        for (i = 0; i <100; i++){
                        sleep(1000);}
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }


//    启动服务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG","服务启动");
        return super.onStartCommand(intent, flags, startId);
    }

//    解绑服务
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG","服务解绑了");
        return super.onUnbind(intent);

    }

//    摧毁服务
    @Override
    public void onDestroy() {
        Log.e("TAG","服务销毁了");
        super.onDestroy();
    }


}
