package com.sdwfqin.mvpdemo.model;

import android.util.Log;

import com.sdwfqin.mvpdemo.bean.User;

/**
 * Created by sdwfqin on 2017/1/13.
 */

public class UserModel implements IUserModel {

    public UserModel(){
        Log.e("test", "创建：UserModel");
    }

    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {

        Log.e("test", "UserModel=login");

        // 模拟登陆
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("test","run方法");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("test","开始判断");
                if(username.equals("sdwfqin") && password.equals("123")){
                    User user = new User();
                    user.setName(username);
                    user.setPass(password);
                    loginListener.onSuccess(user);
                } else {
                    loginListener.onFailed();
                }

            }
        }).start();
    }
}
