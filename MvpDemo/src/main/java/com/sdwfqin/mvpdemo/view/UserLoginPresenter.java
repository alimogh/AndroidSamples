package com.sdwfqin.mvpdemo.view;

import com.sdwfqin.mvpdemo.bean.User;

/**
 * Created by sdwfqin on 2017/1/13.
 */

public interface UserLoginPresenter {
    public String getUserName();

    public String getPassword();

    public void clearUserName();

    public void clearPassword();

    public void showLoading();

    public void hideLoading();

    public void toMainActivity(User user);

    public void showFailedError();
}
