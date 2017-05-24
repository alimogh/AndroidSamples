package com.sdwfqin.sample.dagger.model;

import android.content.Context;

import com.sdwfqin.sample.dagger.Controller.MoneyController;
import com.sdwfqin.sample.dagger.Controller.OrderController;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by sdwfqin on 2017/4/24.
 * <p>
 * 通过这个Module绑定Controller
 */
// @Modules 类似于我们的模块，提供各种实例跟对象
// includes引用
// @Module(includes = HttpModule.class)
@Module
public class UserModule {

    private Context mContext;

    public UserModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public OrderController providerOrderController() {
        return new OrderController("lisi");
    }

    @Provides
    public MoneyController providerMoneyController(OkHttpClient okHttpClient) {
        return new MoneyController(okHttpClient);
    }
}