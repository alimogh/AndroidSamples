package com.sdwfqin.sample.dagger.Controller;

import android.content.Context;
import android.util.Log;

/**
 * Created by sdwfqin on 2017/4/24.
 */

public class MoneyController {

    private Context mContext;
    //需要传入参数，虽然没什么用，但是为了演示Dagger2而用
    public MoneyController(Context context) {
        this.mContext = context;
    }

    public String payMoney() {
        return "payMoney";
    }
}
