package com.sdwfqin.mvpdemo.interactor;

import com.sdwfqin.mvpdemo.model.User;

/**
 * Created by sdwfqin on 2017/1/13.
 * <p>
 * 获取Model(从本地数据库，或者是服务器),转换成ViewModel，回调通知把ViewModel传递给Presenter
 */

public interface UserLoginInteractor {

    interface OnLoginListener {
        void onSuccess(User user);

        void onFailed();
    }

    void login(String username, String password, UserLoginInteractor.OnLoginListener loginListener);
}
