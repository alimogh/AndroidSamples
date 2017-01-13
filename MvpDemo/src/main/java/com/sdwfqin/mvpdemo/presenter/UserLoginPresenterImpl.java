package com.sdwfqin.mvpdemo.presenter;

import android.os.Handler;
import android.util.Log;

import com.sdwfqin.mvpdemo.model.User;
import com.sdwfqin.mvpdemo.interactor.UserLoginInteractor;
import com.sdwfqin.mvpdemo.view.UserLoginView;

/**
 * Created by sdwfqin on 2017/1/13.
 */

public class UserLoginPresenterImpl implements UserLoginPresenter, UserLoginInteractor.OnLoginListener {

    private UserLoginInteractor mLoginInteractor;
    private UserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenterImpl(UserLoginView userLogenView, UserLoginInteractor mLoginInteractor) {
        this.userLoginView = userLogenView;
        this.mLoginInteractor = mLoginInteractor;
    }

    ;

    @Override
    public void validateCredentials(String username, String password) {
        if (userLoginView != null) {
            userLoginView.showLoading();
        }

        mLoginInteractor.login(username, password, this);
    }

    public void clean() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }

    @Override
    public void onSuccess(final User user) {
        Log.e("test", "onSuccess:");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                userLoginView.toMainActivity(user);
                userLoginView.hideLoading();
            }
        });
    }

    @Override
    public void onFailed() {
        Log.e("test", "onFailed:");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                userLoginView.showFailedError();
                userLoginView.hideLoading();
            }
        });
    }

    @Override
    public void onDestroy() {
        userLoginView = null;
    }
}
