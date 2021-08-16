package com.example.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection conn = new ServiceConnection() {

        //当客户端正常连接服务时，执行服务绑定操作会被调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("TAG","慕课");
            MyService.MyBinder mb= (MyService.MyBinder) service;
            int step = mb.getProcess();
            Log.e("TAG","当前进度是："+step);
        }


//        当客户端和服务的连接丢失了
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void operate(View view) {
        switch (view.getId()) {
            case R.id.start:
//                通过Intent将it1传给startService()启动服务
                Intent it1 = new Intent(this, MyService.class);
                startService(it1);
                break;
            case R.id.stop:
//                通过Intent将it2传给stopService()停止服务
                Intent it2 = new Intent(this, MyService.class);
                stopService(it2);
                break;
            case R.id.bind:
//                通过Intent将it3传给bindService绑定服务,实现conn里面的方法,BIND_AUTO_CREATE自动绑定服务
                Intent it3 = new Intent(this, MyService.class);
                bindService(it3, conn, BIND_AUTO_CREATE);

                break;
            case R.id.unBind:
//               传入conn来停止服务并解绑
                unbindService(conn);


                break;
        }

    }
}