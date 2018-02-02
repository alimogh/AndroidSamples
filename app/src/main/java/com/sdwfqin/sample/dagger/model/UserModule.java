package com.sdwfqin.sample.dagger.model;

import android.content.Context;

import com.sdwfqin.sample.dagger.controller.MoneyController;
import com.sdwfqin.sample.dagger.controller.OrderController;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * 描述：通过这个Module绑定Controller
 *
 * @author zhangqin
 * @date 2017/4/24
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