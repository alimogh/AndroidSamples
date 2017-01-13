package com.sdwfqin.mvpdemo.view;

import com.sdwfqin.mvpdemo.model.User;

/**
 * Created by sdwfqin on 2017/1/13.
 */

public interface UserLoginView {

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
