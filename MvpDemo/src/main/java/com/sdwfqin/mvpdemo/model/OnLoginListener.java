package com.sdwfqin.mvpdemo.model;

import com.sdwfqin.mvpdemo.bean.User;

/**
 * Created by sdwfqin on 2017/1/13.
 */

public interface OnLoginListener {

    public void onSuccess(User user);
    public void onFailed();
}
