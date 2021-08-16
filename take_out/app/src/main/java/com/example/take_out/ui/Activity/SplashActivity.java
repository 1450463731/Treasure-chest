package com.example.take_out.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.take_out.R;

public class SplashActivity extends AppCompatActivity {
//创建全局按键
    private Button mBtnSkip;
//创建Handler并实例化来
    private Handler mHandler = new Handler();
//创建Runnble来运行跳转
    private Runnable mRunnableToLogin = new Runnable() {
        @Override
        public void run() {
            toLoginActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//创建初始化查找按键事件类
        initView();
//创建初始化点击事件类
        initEvent();
//通过Handler设定3秒以后运行Runnble
        mHandler.postDelayed(mRunnableToLogin,3000);
    }
//设置点击事件并运行mRunnableToLogin,3秒以后到LoginActivity
    private void initEvent() {
        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                运行Runnable
                mHandler.removeCallbacks(mRunnableToLogin);
//                跳转LoginActivity
                toLoginActivity();
            }
        });
    }
//查找控件
    private void initView() {
//        通过findViewById查找控件
        mBtnSkip =findViewById(R.id.id_btn_skip);
    }

//    创建toLoginActivity方法，利用Intent从本Activity跳转到LoginActivity
    private void toLoginActivity(){
//      初始化Intent并创建
        Intent intent =new Intent(this,LoginActivity.class);
//        发送intent
        startActivity(intent);
//        跳转后销毁结束
        finish();
    }

    @Override
/*因为调用Handler的Runnable在未使用静态情况下会造成内存泄漏
 *使用onDestroy来及时销毁避免内存泄漏
 */
    protected void onDestroy() {
        super.onDestroy();
//        利用Handler.removeCallbacks方法销毁mRunnableToLogin
        mHandler.removeCallbacks(mRunnableToLogin);
    }
}