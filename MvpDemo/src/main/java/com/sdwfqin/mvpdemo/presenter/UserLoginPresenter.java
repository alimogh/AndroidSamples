package com.sdwfqin.mvpdemo.presenter;

/**
 * Created by sdwfqin on 2017/1/13.
 */

public interface UserLoginPresenter {
    void validateCredentials(String username, String password);

    void clean();

    void onDestroy();
}
