package com.sdwfqin.sample.dagger;

import android.util.Log;

/**
 * Created by sdwfqin on 2017/4/24.
 */

public class UserController {

    public UserController(String name) {
        Log.e("UserController", "name:" + name);
    }
    public UserController(String name, int age) {
        Log.e("UserController", "name:" + name + "age:" + age);
    }
}
