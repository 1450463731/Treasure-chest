package com.example.take_out;

import android.text.TextUtils;

import com.example.take_out.bean.User;
import com.example.take_out.utils.SPUtils;

public class UserInfoHolder {

    private  static  UserInfoHolder mInstance= new UserInfoHolder();
    private  User mUser;
    private  static final  String KEY_USERNAME="key_username";

    public static UserInfoHolder getInstance(){
        return mInstance;
    }

    public User getUser() {
        User u = mUser;
        if(u == null){
            String username = (String) SPUtils.getInstance().get(KEY_USERNAME,"");
            if (!TextUtils.isEmpty(username)){
                u=new User();
                u.setUsernaem(username);
            }
        }
        mUser = u;
        return u;
    }

    public void setUser(User user) {
        mUser = user;
        if ( user != null)
        SPUtils.getInstance().put(KEY_USERNAME,user.getUsernaem());
    }
}
