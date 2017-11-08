package com.sdwfqin.sample.dagger.controller;

import com.blankj.utilcode.util.LogUtils;

/**
 * 描述：Controller
 *
 * @author sdwfqin
 * @date 2017/4/24
 */
public class OrderController {

    String s;

    public OrderController(String s) {
        this.s = s;
    }

    public void order() {
        LogUtils.e("order" + s);
    }

}
