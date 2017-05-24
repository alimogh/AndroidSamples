package com.sdwfqin.sample.dagger.Controller;

import android.util.Log;

/**
 * Created by sdwfqin on 2017/4/24.
 */
public class OrderController {

    String s;

    public OrderController(String s) {
        this.s = s;
    }

    public void order() {
        Log.e("OrderController", "order" + s);
    }

}
