package com.sdwfqin.sample.dagger.Controller;

import android.util.Log;

/**
 * Created by sdwfqin on 2017/4/24.
 */
public class OrderController {

    String s;
    int age;

    public OrderController(String s) {
        this.s = s;
    }

    public OrderController(String s, int age) {
        this.s = s;
        this.age = age;
    }

    public void order() {
        Log.e("OrderController", "order" + s);
    }

    public void age() {
        Log.e("OrderController", "order" + s + "age" + age);
    }
}
