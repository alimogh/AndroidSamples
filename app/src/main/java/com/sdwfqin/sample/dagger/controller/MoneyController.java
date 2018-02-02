package com.sdwfqin.sample.dagger.controller;

import okhttp3.OkHttpClient;

/**
 * Created by zhangqin on 2017/4/24.
 */

/**
 * 描述：Controller
 *
 * @author zhangqin
 * @date 2017/4/24
 */
public class MoneyController {

    public MoneyController(OkHttpClient okHttpClient) {

    }

    public String payMoney() {
        return "payMoney";
    }
}
