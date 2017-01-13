package com.sdwfqin.mvpdemo.presenter;

import android.os.Handler;
import android.util.Log;

import com.sdwfqin.mvpdemo.bean.User;
import com.sdwfqin.mvpdemo.model.IUserModel;
import com.sdwfqin.mvpdemo.model.OnLoginListener;
import com.sdwfqin.mvpdemo.model.UserModel;
import com.sdwfqin.mvpdemo.view.IUserLogenView;

/**
 * Created by sdwfqin on 2017/1/13.
 */

public class UserLoginPresenter {

    private IUserModel iUserModel;
    private IUserLogenView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLogenView userLogenView){
        this.userLoginView = userLogenView;
        this.iUserModel = new UserModel();
    };

    public void login(){
        Log.e("test", "login");

        userLoginView.showLoading();
        iUserModel.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            // 登陆成功
            @Override
            public void onSuccess(final User user) {
                Log.e("test", "onSuccess:");
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            // 登陆失败
            @Override
            public void onFailed() {
                Log.e("test", "onFailed:");
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });

    }

    public void clean(){
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
