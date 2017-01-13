package com.sdwfqin.mvpdemo.interactor;

import com.sdwfqin.mvpdemo.model.User;

/**
 * Created by sdwfqin on 2017/1/13.
 */

public interface UserLoginInteractor {

    interface OnLoginListener{
        void onSuccess(User user);
        void onFailed();
    }

    void login(String username, String password, UserLoginInteractor.OnLoginListener loginListener);
}
