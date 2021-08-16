package com.example.take_out.ui.Activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.take_out.R;

public class BaseActivity extends AppCompatActivity {


    private android.app.AlertDialog mLoadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(0xff000000);
        }

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setMessage("加载中。。。。。");





    }
    protected void startLoadingProgress() {
        if(mLoadingDialog!=null&&mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    protected void stopLoadingProgress() {
        mLoadingDialog.show();

    }




    //Toolbar类
    protected void setUpToolbar() {
//        查找并创建
        Toolbar toolbar =(Toolbar)findViewById(R.id.id_toolbar);
//        设置传入toolbar
        setSupportActionBar(toolbar);
//        设置点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startLoadingProgress();
        mLoadingDialog = null;
    }
}
