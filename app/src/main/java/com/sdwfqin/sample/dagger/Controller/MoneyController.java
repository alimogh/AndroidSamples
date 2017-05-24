package com.sdwfqin.sample.dagger.Controller;

import okhttp3.OkHttpClient;

/**
 * Created by sdwfqin on 2017/4/24.
 */

public class MoneyController {

    public MoneyController(OkHttpClient okHttpClient) {

    }

    public String payMoney() {
        return "payMoney";
    }
}
